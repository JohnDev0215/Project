package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CancelAuditTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class CancelAuditTaskResultJsonUnmarshaller implements Unmarshaller<CancelAuditTaskResult, JsonUnmarshallerContext> {
    private static CancelAuditTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CancelAuditTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new CancelAuditTaskResult();
    }

    public static CancelAuditTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CancelAuditTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
