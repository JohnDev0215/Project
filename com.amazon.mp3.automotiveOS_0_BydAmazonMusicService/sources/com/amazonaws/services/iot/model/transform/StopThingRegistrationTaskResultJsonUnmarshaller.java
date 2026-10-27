package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StopThingRegistrationTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class StopThingRegistrationTaskResultJsonUnmarshaller implements Unmarshaller<StopThingRegistrationTaskResult, JsonUnmarshallerContext> {
    private static StopThingRegistrationTaskResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public StopThingRegistrationTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new StopThingRegistrationTaskResult();
    }

    public static StopThingRegistrationTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StopThingRegistrationTaskResultJsonUnmarshaller();
        }
        return instance;
    }
}
