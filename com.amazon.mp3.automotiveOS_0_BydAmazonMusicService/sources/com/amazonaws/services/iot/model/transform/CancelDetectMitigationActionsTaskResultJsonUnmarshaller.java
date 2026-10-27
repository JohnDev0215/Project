package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CancelDetectMitigationActionsTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class CancelDetectMitigationActionsTaskResultJsonUnmarshaller implements Unmarshaller<CancelDetectMitigationActionsTaskResult, JsonUnmarshallerContext> {
    private static CancelDetectMitigationActionsTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CancelDetectMitigationActionsTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new CancelDetectMitigationActionsTaskResult();
    }

    public static CancelDetectMitigationActionsTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CancelDetectMitigationActionsTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
