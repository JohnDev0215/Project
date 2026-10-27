package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteAuditSuppressionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteAuditSuppressionResultJsonUnmarshaller implements Unmarshaller<DeleteAuditSuppressionResult, JsonUnmarshallerContext> {
    private static DeleteAuditSuppressionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteAuditSuppressionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteAuditSuppressionResult();
    }

    public static DeleteAuditSuppressionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteAuditSuppressionResultJsonUnmarshaller();
        }
        return instance;
    }
}
