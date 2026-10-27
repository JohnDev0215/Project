package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateAuditSuppressionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateAuditSuppressionResultJsonUnmarshaller implements Unmarshaller<UpdateAuditSuppressionResult, JsonUnmarshallerContext> {
    private static UpdateAuditSuppressionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateAuditSuppressionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateAuditSuppressionResult();
    }

    public static UpdateAuditSuppressionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateAuditSuppressionResultJsonUnmarshaller();
        }
        return instance;
    }
}
