package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazonaws.services.iot.model.OpenSearchAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class OpenSearchActionJsonMarshaller {
    private static OpenSearchActionJsonMarshaller instance;

    OpenSearchActionJsonMarshaller() {
    }

    public void marshall(OpenSearchAction openSearchAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (openSearchAction.getRoleArn() != null) {
            String roleArn = openSearchAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (openSearchAction.getEndpoint() != null) {
            String endpoint = openSearchAction.getEndpoint();
            awsJsonWriter.name(IntlConfiguration.ENDPOINT_T);
            awsJsonWriter.value(endpoint);
        }
        if (openSearchAction.getIndex() != null) {
            String index = openSearchAction.getIndex();
            awsJsonWriter.name("index");
            awsJsonWriter.value(index);
        }
        if (openSearchAction.getType() != null) {
            String type = openSearchAction.getType();
            awsJsonWriter.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            awsJsonWriter.value(type);
        }
        if (openSearchAction.getId() != null) {
            String id = openSearchAction.getId();
            awsJsonWriter.name("id");
            awsJsonWriter.value(id);
        }
        awsJsonWriter.endObject();
    }

    public static OpenSearchActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new OpenSearchActionJsonMarshaller();
        }
        return instance;
    }
}
