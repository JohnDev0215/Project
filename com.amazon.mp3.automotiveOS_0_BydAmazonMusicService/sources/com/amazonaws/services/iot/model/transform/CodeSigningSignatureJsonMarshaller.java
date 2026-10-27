package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CodeSigningSignature;
import com.amazonaws.util.json.AwsJsonWriter;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
class CodeSigningSignatureJsonMarshaller {
    private static CodeSigningSignatureJsonMarshaller instance;

    CodeSigningSignatureJsonMarshaller() {
    }

    public void marshall(CodeSigningSignature codeSigningSignature, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (codeSigningSignature.getInlineDocument() != null) {
            ByteBuffer inlineDocument = codeSigningSignature.getInlineDocument();
            awsJsonWriter.name("inlineDocument");
            awsJsonWriter.value(inlineDocument);
        }
        awsJsonWriter.endObject();
    }

    public static CodeSigningSignatureJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CodeSigningSignatureJsonMarshaller();
        }
        return instance;
    }
}
