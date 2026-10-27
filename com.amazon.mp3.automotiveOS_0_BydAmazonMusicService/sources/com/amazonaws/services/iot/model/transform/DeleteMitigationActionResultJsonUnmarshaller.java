package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteMitigationActionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteMitigationActionResultJsonUnmarshaller implements Unmarshaller<DeleteMitigationActionResult, JsonUnmarshallerContext> {
    private static DeleteMitigationActionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteMitigationActionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteMitigationActionResult();
    }

    public static DeleteMitigationActionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteMitigationActionResultJsonUnmarshaller();
        }
        return instance;
    }
}
