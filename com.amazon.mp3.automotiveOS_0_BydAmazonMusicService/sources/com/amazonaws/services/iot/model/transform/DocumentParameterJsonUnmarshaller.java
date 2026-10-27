package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DocumentParameter;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class DocumentParameterJsonUnmarshaller implements Unmarshaller<DocumentParameter, JsonUnmarshallerContext> {
    private static DocumentParameterJsonUnmarshaller instance;

    DocumentParameterJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DocumentParameter unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DocumentParameter documentParameter = new DocumentParameter();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("key")) {
                documentParameter.setKey(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                documentParameter.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("regex")) {
                documentParameter.setRegex(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("example")) {
                documentParameter.setExample(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("optional")) {
                documentParameter.setOptional(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return documentParameter;
    }

    public static DocumentParameterJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DocumentParameterJsonUnmarshaller();
        }
        return instance;
    }
}
