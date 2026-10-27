package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CancelAuditMitigationActionsTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class CancelAuditMitigationActionsTaskResultJsonUnmarshaller implements Unmarshaller<CancelAuditMitigationActionsTaskResult, JsonUnmarshallerContext> {
    private static CancelAuditMitigationActionsTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CancelAuditMitigationActionsTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new CancelAuditMitigationActionsTaskResult();
    }

    public static CancelAuditMitigationActionsTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CancelAuditMitigationActionsTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
