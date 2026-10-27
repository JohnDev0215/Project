package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteThingTypeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteThingTypeResultJsonUnmarshaller implements Unmarshaller<DeleteThingTypeResult, JsonUnmarshallerContext> {
    private static DeleteThingTypeResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteThingTypeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteThingTypeResult();
    }

    public static DeleteThingTypeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteThingTypeResultJsonUnmarshaller();
        }
        return instance;
    }
}
