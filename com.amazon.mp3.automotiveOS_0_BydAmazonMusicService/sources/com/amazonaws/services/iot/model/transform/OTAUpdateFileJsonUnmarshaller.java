package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OTAUpdateFile;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class OTAUpdateFileJsonUnmarshaller implements Unmarshaller<OTAUpdateFile, JsonUnmarshallerContext> {
    private static OTAUpdateFileJsonUnmarshaller instance;

    OTAUpdateFileJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OTAUpdateFile unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OTAUpdateFile oTAUpdateFile = new OTAUpdateFile();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("fileName")) {
                oTAUpdateFile.setFileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("fileType")) {
                oTAUpdateFile.setFileType(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("fileVersion")) {
                oTAUpdateFile.setFileVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("fileLocation")) {
                oTAUpdateFile.setFileLocation(FileLocationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("codeSigning")) {
                oTAUpdateFile.setCodeSigning(CodeSigningJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("attributes")) {
                oTAUpdateFile.setAttributes(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return oTAUpdateFile;
    }

    public static OTAUpdateFileJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateFileJsonUnmarshaller();
        }
        return instance;
    }
}
