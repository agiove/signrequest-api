package it.ivert.signrequest.request;

import it.ivert.signrequest.model.QuickCreateDocument;
import it.ivert.signrequest.response.GetQuickCreateDocumentResponse;

public class PostQuickCreateDocument extends PostUniqueRequest<GetQuickCreateDocumentResponse, QuickCreateDocument> {

    private static final String entity = "signrequest-quick-create/";

    public PostQuickCreateDocument(QuickCreateDocument data) {
        super(entity, data, GetQuickCreateDocumentResponse.class);
    }
}