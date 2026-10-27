package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazonaws.services.iot.model.OpenSearchAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class OpenSearchActionJsonUnmarshaller implements Unmarshaller<OpenSearchAction, JsonUnmarshallerContext> {
    private static OpenSearchActionJsonUnmarshaller instance;

    OpenSearchActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OpenSearchAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OpenSearchAction openSearchAction = new OpenSearchAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                openSearchAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(IntlConfiguration.ENDPOINT_T)) {
                openSearchAction.setEndpoint(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("index")) {
                openSearchAction.setIndex(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                openSearchAction.setType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("id")) {
                openSearchAction.setId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return openSearchAction;
    }

    public static OpenSearchActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OpenSearchActionJsonUnmarshaller();
        }
        return instance;
    }
}
