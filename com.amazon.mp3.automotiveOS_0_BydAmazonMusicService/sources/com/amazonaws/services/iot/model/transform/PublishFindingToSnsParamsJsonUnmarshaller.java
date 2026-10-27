package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PublishFindingToSnsParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class PublishFindingToSnsParamsJsonUnmarshaller implements Unmarshaller<PublishFindingToSnsParams, JsonUnmarshallerContext> {
    private static PublishFindingToSnsParamsJsonUnmarshaller instance;

    PublishFindingToSnsParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PublishFindingToSnsParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PublishFindingToSnsParams publishFindingToSnsParams = new PublishFindingToSnsParams();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("topicArn")) {
                publishFindingToSnsParams.setTopicArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return publishFindingToSnsParams;
    }

    public static PublishFindingToSnsParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PublishFindingToSnsParamsJsonUnmarshaller();
        }
        return instance;
    }
}
