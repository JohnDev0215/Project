package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveThingFromBillingGroupResultJsonUnmarshaller implements Unmarshaller<RemoveThingFromBillingGroupResult, JsonUnmarshallerContext> {
    private static RemoveThingFromBillingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public RemoveThingFromBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new RemoveThingFromBillingGroupResult();
    }

    public static RemoveThingFromBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RemoveThingFromBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
