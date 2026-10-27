package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateAuditSuppressionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class CreateAuditSuppressionResultJsonUnmarshaller implements Unmarshaller<CreateAuditSuppressionResult, JsonUnmarshallerContext> {
    private static CreateAuditSuppressionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateAuditSuppressionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new CreateAuditSuppressionResult();
    }

    public static CreateAuditSuppressionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateAuditSuppressionResultJsonUnmarshaller();
        }
        return instance;
    }
}
