package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AddThingToThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class AddThingToThingGroupResultJsonUnmarshaller implements Unmarshaller<AddThingToThingGroupResult, JsonUnmarshallerContext> {
    private static AddThingToThingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public AddThingToThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new AddThingToThingGroupResult();
    }

    public static AddThingToThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AddThingToThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
