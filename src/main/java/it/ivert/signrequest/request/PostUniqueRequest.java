package it.ivert.signrequest.request;

import it.ivert.signrequest.BaseRequest;
import it.ivert.signrequest.BaseResponse;
import it.ivert.signrequest.model.SRModel;
import it.ivert.signrequest.request.method.Post;

import java.util.HashMap;
import java.util.Map;

public abstract class PostUniqueRequest<R extends BaseResponse, S extends SRModel> extends BaseRequest<R> implements Post {

    private String entity;
    private S data;

    public PostUniqueRequest(String entity, S data, Class<R> responseClass) {
        super(responseClass);
        this.entity = entity;
        this.data = data;
    }

    @Override
    public String urlPart() {
        return entity;
    }

    @Override
    public Map<String, Object> params() {
        return new HashMap<String, Object>();
    }

    public SRModel getData() {
        return data;
    }
}
