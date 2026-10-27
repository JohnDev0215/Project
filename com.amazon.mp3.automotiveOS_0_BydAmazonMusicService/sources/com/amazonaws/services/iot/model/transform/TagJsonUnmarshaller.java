package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TagJsonUnmarshaller implements Unmarshaller<Tag, JsonUnmarshallerContext> {
    private static TagJsonUnmarshaller instance;

    TagJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Tag unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Tag tag = new Tag();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("Key")) {
                tag.setKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("Value")) {
                tag.setValue(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return tag;
    }

    public static TagJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TagJsonUnmarshaller();
        }
        return instance;
    }
}
