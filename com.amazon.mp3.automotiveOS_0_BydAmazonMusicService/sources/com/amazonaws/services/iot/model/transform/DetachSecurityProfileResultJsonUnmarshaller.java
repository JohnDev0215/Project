package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetachSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DetachSecurityProfileResultJsonUnmarshaller implements Unmarshaller<DetachSecurityProfileResult, JsonUnmarshallerContext> {
    private static DetachSecurityProfileResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DetachSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DetachSecurityProfileResult();
    }

    public static DetachSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetachSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }
}
