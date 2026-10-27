package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ImplicitDeny;
import com.amazonaws.services.iot.model.Policy;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class ImplicitDenyJsonMarshaller {
    private static ImplicitDenyJsonMarshaller instance;

    ImplicitDenyJsonMarshaller() {
    }

    public void marshall(ImplicitDeny implicitDeny, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (implicitDeny.getPolicies() != null) {
            List<Policy> policies = implicitDeny.getPolicies();
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

    public static ImplicitDenyJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ImplicitDenyJsonMarshaller();
        }
        return instance;
    }
}
