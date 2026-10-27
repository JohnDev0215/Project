package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DocumentParameter;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class DocumentParameterJsonMarshaller {
    private static DocumentParameterJsonMarshaller instance;

    DocumentParameterJsonMarshaller() {
    }

    public void marshall(DocumentParameter documentParameter, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (documentParameter.getKey() != null) {
            String key = documentParameter.getKey();
            awsJsonWriter.name("key");
            awsJsonWriter.value(key);
        }
        if (documentParameter.getDescription() != null) {
            String description = documentParameter.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (documentParameter.getRegex() != null) {
            String regex = documentParameter.getRegex();
            awsJsonWriter.name("regex");
            awsJsonWriter.value(regex);
        }
        if (documentParameter.getExample() != null) {
            String example = documentParameter.getExample();
            awsJsonWriter.name("example");
            awsJsonWriter.value(example);
        }
        if (documentParameter.getOptional() != null) {
            Boolean optional = documentParameter.getOptional();
            awsJsonWriter.name("optional");
            awsJsonWriter.value(optional.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static DocumentParameterJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DocumentParameterJsonMarshaller();
        }
        return instance;
    }
}
