package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteDimensionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDimensionResultJsonUnmarshaller implements Unmarshaller<DeleteDimensionResult, JsonUnmarshallerContext> {
    private static DeleteDimensionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteDimensionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteDimensionResult();
    }

    public static DeleteDimensionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteDimensionResultJsonUnmarshaller();
        }
        return instance;
    }
}
