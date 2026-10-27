package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditCheckDetails;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AuditCheckDetailsJsonUnmarshaller implements Unmarshaller<AuditCheckDetails, JsonUnmarshallerContext> {
    private static AuditCheckDetailsJsonUnmarshaller instance;

    AuditCheckDetailsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditCheckDetails unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditCheckDetails auditCheckDetails = new AuditCheckDetails();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("checkRunStatus")) {
                auditCheckDetails.setCheckRunStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("checkCompliant")) {
                auditCheckDetails.setCheckCompliant(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("totalResourcesCount")) {
                auditCheckDetails.setTotalResourcesCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nonCompliantResourcesCount")) {
                auditCheckDetails.setNonCompliantResourcesCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("suppressedNonCompliantResourcesCount")) {
                auditCheckDetails.setSuppressedNonCompliantResourcesCount(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("errorCode")) {
                auditCheckDetails.setErrorCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("message")) {
                auditCheckDetails.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditCheckDetails;
    }

    public static AuditCheckDetailsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditCheckDetailsJsonUnmarshaller();
        }
        return instance;
    }
}
