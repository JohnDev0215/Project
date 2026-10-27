package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteOTAUpdateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteOTAUpdateResultJsonUnmarshaller implements Unmarshaller<DeleteOTAUpdateResult, JsonUnmarshallerContext> {
    private static DeleteOTAUpdateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteOTAUpdateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteOTAUpdateResult();
    }

    public static DeleteOTAUpdateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteOTAUpdateResultJsonUnmarshaller();
        }
        return instance;
    }
}
