package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigning;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class CodeSigningJsonUnmarshaller implements Unmarshaller<CodeSigning, JsonUnmarshallerContext> {
    private static CodeSigningJsonUnmarshaller instance;

    CodeSigningJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CodeSigning unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        CodeSigning codeSigning = new CodeSigning();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("awsSignerJobId")) {
                codeSigning.setAwsSignerJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("startSigningJobParameter")) {
                codeSigning.setStartSigningJobParameter(StartSigningJobParameterJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("customCodeSigning")) {
                codeSigning.setCustomCodeSigning(CustomCodeSigningJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return codeSigning;
    }

    public static CodeSigningJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CodeSigningJsonUnmarshaller();
        }
        return instance;
    }
}
