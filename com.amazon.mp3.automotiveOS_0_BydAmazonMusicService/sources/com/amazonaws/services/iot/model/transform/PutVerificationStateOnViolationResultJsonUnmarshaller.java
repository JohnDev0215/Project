package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PutVerificationStateOnViolationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class PutVerificationStateOnViolationResultJsonUnmarshaller implements Unmarshaller<PutVerificationStateOnViolationResult, JsonUnmarshallerContext> {
    private static PutVerificationStateOnViolationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public PutVerificationStateOnViolationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new PutVerificationStateOnViolationResult();
    }

    public static PutVerificationStateOnViolationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PutVerificationStateOnViolationResultJsonUnmarshaller();
        }
        return instance;
    }
}
