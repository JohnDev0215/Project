package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuditSuppression;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class AuditSuppressionJsonUnmarshaller implements Unmarshaller<AuditSuppression, JsonUnmarshallerContext> {
    private static AuditSuppressionJsonUnmarshaller instance;

    AuditSuppressionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AuditSuppression unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AuditSuppression auditSuppression = new AuditSuppression();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("checkName")) {
                auditSuppression.setCheckName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("resourceIdentifier")) {
                auditSuppression.setResourceIdentifier(ResourceIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("expirationDate")) {
                auditSuppression.setExpirationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("suppressIndefinitely")) {
                auditSuppression.setSuppressIndefinitely(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                auditSuppression.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return auditSuppression;
    }

    public static AuditSuppressionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AuditSuppressionJsonUnmarshaller();
        }
        return instance;
    }
}
