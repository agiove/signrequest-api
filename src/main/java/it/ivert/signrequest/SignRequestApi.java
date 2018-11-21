package it.ivert.signrequest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.ivert.signrequest.exc.SRApiRequestException;
import it.ivert.signrequest.request.method.Get;
import it.ivert.signrequest.request.method.Post;
import it.ivert.signrequest.response.GetUniqueResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignRequestApi {

    public static final String API_HOST = "https://signrequest.com";
    public static final String API_BASEPATH = "/api/v1";

    private String token;
    private Gson gson = new GsonBuilder()
//			.excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.S")
            .create();

    private SignRequestApi(String token) {
        this.token = token;
    }

    public static SignRequestApi build(String token) {
        return new SignRequestApi(token);
    }

    public <R extends BaseResponse> R execute(BaseRequest<R> request) throws SRApiRequestException {
        HttpResponse response;
        try {
            if(request instanceof Get) {
                response = Request
                        .Get(makeUri(request))
                        .addHeader("Authorization", "Token " + token)
                        .addHeader("Accept", "application/json")
                        .execute()
                        .returnResponse();

                StatusLine statusLine = response.getStatusLine();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(
                                    response
                                            .getEntity()
                                            .getContent()
                            )
                    );

                    if(statusLine.getStatusCode() < 400) {
                        if(GetUniqueResponse.class.isAssignableFrom(request.getResponseType())) {
                            R ret = request.getResponseType().newInstance();
                            getSetDataMethod().invoke(ret, gson.fromJson(reader, ((GetUniqueResponse)ret).getType()));
                            return ret;
                        }

                        return gson.fromJson(reader, request.getResponseType());
                    }
                    else throw new SRApiRequestException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(),
                            reader.readLine()
                    );
                } finally {
                    if(reader!=null) reader.close();
                }
            } else if(request instanceof Post) {
                response = Request
                        .Post(makeUri(request))
                        .addHeader("Authorization", "Token " + token)
                        .addHeader(HTTP.CONTENT_TYPE, "application/json")
                        .addHeader("Accept", "application/json")
                        .body(makeEntity((Post)request))
                        .execute()
                        .returnResponse();

                StatusLine statusLine = response.getStatusLine();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(
                                    response
                                            .getEntity()
                                            .getContent()
                            )
                    );
                    if(statusLine.getStatusCode() < 400) {
                        if(GetUniqueResponse.class.isAssignableFrom(request.getResponseType())) {
                            R ret = request.getResponseType().newInstance();
                            getSetDataMethod().invoke(ret, gson.fromJson(reader, ((GetUniqueResponse)ret).getType()));
                            return ret;
                        }

                        return gson.fromJson(reader, request.getResponseType());
                    }
                    else throw new SRApiRequestException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(),
                            reader.readLine()
                    );
                } finally {
                    if(reader!=null) reader.close();
                }
            }
        } catch (Throwable e) {
            if (e instanceof SRApiRequestException)
                throw (SRApiRequestException)e;
            else
                throw new SRApiRequestException(e);
        }

        return null;
    }

    private URI makeUri(BaseRequest request) throws URISyntaxException {
        URIBuilder uri = new URIBuilder(API_HOST + "/" + API_BASEPATH + "/" + request.urlPart());
        uri.addParameters(getParams(request));
        return uri.build();
    }

    private HttpEntity makeEntity(Post request) {
        return new StringEntity(gson.toJson(request.getData()), ContentType.APPLICATION_JSON);
    }

    public static boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    private Method getSetDataMethod() {
        for(Method m : GetUniqueResponse.class.getDeclaredMethods()) {
            if(m.getName().equals("setData"))
                return m;
        }
        return null;
    }

    private List<NameValuePair> getParams(BaseRequest request) {
        Map<String, Object> map = request.params();
        if(map == null) map = new HashMap<String, Object>();
        List<NameValuePair> retList = new ArrayList<NameValuePair>();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            retList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        return retList;
    }

}
