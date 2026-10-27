package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateThingGroupsForThingResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingGroupsForThingResultJsonUnmarshaller implements Unmarshaller<UpdateThingGroupsForThingResult, JsonUnmarshallerContext> {
    private static UpdateThingGroupsForThingResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateThingGroupsForThingResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateThingGroupsForThingResult();
    }

    public static UpdateThingGroupsForThingResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateThingGroupsForThingResultJsonUnmarshaller();
        }
        return instance;
    }
}
