package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MitigationActionIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class MitigationActionIdentifierJsonMarshaller {
    private static MitigationActionIdentifierJsonMarshaller instance;

    MitigationActionIdentifierJsonMarshaller() {
    }

    public void marshall(MitigationActionIdentifier mitigationActionIdentifier, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (mitigationActionIdentifier.getActionName() != null) {
            String actionName = mitigationActionIdentifier.getActionName();
            awsJsonWriter.name("actionName");
            awsJsonWriter.value(actionName);
        }
        if (mitigationActionIdentifier.getActionArn() != null) {
            String actionArn = mitigationActionIdentifier.getActionArn();
            awsJsonWriter.name("actionArn");
            awsJsonWriter.value(actionArn);
        }
        if (mitigationActionIdentifier.getCreationDate() != null) {
            Date creationDate = mitigationActionIdentifier.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }

    public static MitigationActionIdentifierJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MitigationActionIdentifierJsonMarshaller();
        }
        return instance;
    }
}
