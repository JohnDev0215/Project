package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.services.iot.model.Field;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class FieldJsonMarshaller {
    private static FieldJsonMarshaller instance;

    FieldJsonMarshaller() {
    }

    public void marshall(Field field, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (field.getName() != null) {
            String name = field.getName();
            awsJsonWriter.name("name");
            awsJsonWriter.value(name);
        }
        if (field.getType() != null) {
            String type = field.getType();
            awsJsonWriter.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            awsJsonWriter.value(type);
        }
        awsJsonWriter.endObject();
    }

    public static FieldJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new FieldJsonMarshaller();
        }
        return instance;
    }
}
