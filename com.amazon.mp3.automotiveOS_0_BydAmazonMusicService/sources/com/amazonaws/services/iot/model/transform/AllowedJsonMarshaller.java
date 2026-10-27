package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Allowed;
import com.amazonaws.services.iot.model.Policy;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class AllowedJsonMarshaller {
    private static AllowedJsonMarshaller instance;

    AllowedJsonMarshaller() {
    }

    public void marshall(Allowed allowed, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (allowed.getPolicies() != null) {
            List<Policy> policies = allowed.getPolicies();
            awsJsonWriter.name("policies");
            awsJsonWriter.beginArray();
            for (Policy policy : policies) {
                if (policy != null) {
                    PolicyJsonMarshaller.getInstance().marshall(policy, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static AllowedJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AllowedJsonMarshaller();
        }
        return instance;
    }
}
