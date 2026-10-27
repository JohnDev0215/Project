package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AttributePayload;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AttributePayloadJsonUnmarshaller implements Unmarshaller<AttributePayload, JsonUnmarshallerContext> {
    private static AttributePayloadJsonUnmarshaller instance;

    AttributePayloadJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AttributePayload unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AttributePayload attributePayload = new AttributePayload();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("attributes")) {
                attributePayload.setAttributes(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("merge")) {
                attributePayload.setMerge(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return attributePayload;
    }

    public static AttributePayloadJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AttributePayloadJsonUnmarshaller();
        }
        return instance;
    }
}
