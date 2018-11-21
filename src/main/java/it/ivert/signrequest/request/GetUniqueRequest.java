package it.ivert.signrequest.request;


import it.ivert.signrequest.BaseRequest;
import it.ivert.signrequest.BaseResponse;
import it.ivert.signrequest.model.SRModel;
import it.ivert.signrequest.request.method.Get;

import java.util.HashMap;
import java.util.Map;

public abstract class GetUniqueRequest <R extends BaseResponse, S extends SRModel> extends BaseRequest<R> implements Get {

    private String entity;
    private long id;

    public GetUniqueRequest(String entity, long id, Class<R> responseClass) {
        super(responseClass);
        this.entity = entity;
        this.id = id;
    }

    @Override
    public String urlPart() {
        return entity.replaceAll(":id", String.valueOf(id));
    }

    @Override
    public Map<String, Object> params() {
        return new HashMap<String, Object>();
    }
}
