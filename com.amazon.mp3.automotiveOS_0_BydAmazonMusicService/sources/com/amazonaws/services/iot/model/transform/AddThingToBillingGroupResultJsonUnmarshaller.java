package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AddThingToBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class AddThingToBillingGroupResultJsonUnmarshaller implements Unmarshaller<AddThingToBillingGroupResult, JsonUnmarshallerContext> {
    private static AddThingToBillingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public AddThingToBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new AddThingToBillingGroupResult();
    }

    public static AddThingToBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AddThingToBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
