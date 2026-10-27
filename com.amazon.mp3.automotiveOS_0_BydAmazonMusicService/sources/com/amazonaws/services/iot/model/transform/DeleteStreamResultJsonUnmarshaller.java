package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteStreamResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteStreamResultJsonUnmarshaller implements Unmarshaller<DeleteStreamResult, JsonUnmarshallerContext> {
    private static DeleteStreamResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteStreamResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteStreamResult();
    }

    public static DeleteStreamResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteStreamResultJsonUnmarshaller();
        }
        return instance;
    }
}
