package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingResultJsonUnmarshaller implements Unmarshaller<DeleteThingResult, JsonUnmarshallerContext> {
    private static DeleteThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteThingResult();
    }

    public static DeleteThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
