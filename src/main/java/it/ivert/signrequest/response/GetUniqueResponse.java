package it.ivert.signrequest.response;

import it.ivert.signrequest.BaseResponse;
import it.ivert.signrequest.model.SRModel;

import java.lang.reflect.Type;

public abstract class GetUniqueResponse<S extends SRModel> extends BaseResponse {

    private S data;

    public abstract Type getType();

    public S get() {
        return data;
    }

    public void setData(S data) {
        this.data = data;
    }
}
