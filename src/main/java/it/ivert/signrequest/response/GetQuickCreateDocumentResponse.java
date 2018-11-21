package it.ivert.signrequest.response;

import it.ivert.signrequest.model.QuickCreateDocument;

import java.lang.reflect.Type;

public class GetQuickCreateDocumentResponse extends GetUniqueResponse<QuickCreateDocument> {

    @Override
    public Type getType() {
        return QuickCreateDocument.class;
    }

}
