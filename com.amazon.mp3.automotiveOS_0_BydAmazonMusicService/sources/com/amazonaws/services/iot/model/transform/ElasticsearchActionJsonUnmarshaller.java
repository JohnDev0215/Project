package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazonaws.services.iot.model.ElasticsearchAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ElasticsearchActionJsonUnmarshaller implements Unmarshaller<ElasticsearchAction, JsonUnmarshallerContext> {
    private static ElasticsearchActionJsonUnmarshaller instance;

    ElasticsearchActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ElasticsearchAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ElasticsearchAction elasticsearchAction = new ElasticsearchAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                elasticsearchAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(IntlConfiguration.ENDPOINT_T)) {
                elasticsearchAction.setEndpoint(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("index")) {
                elasticsearchAction.setIndex(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                elasticsearchAction.setType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("id")) {
                elasticsearchAction.setId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return elasticsearchAction;
    }

    public static ElasticsearchActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ElasticsearchActionJsonUnmarshaller();
        }
        return instance;
    }
}
