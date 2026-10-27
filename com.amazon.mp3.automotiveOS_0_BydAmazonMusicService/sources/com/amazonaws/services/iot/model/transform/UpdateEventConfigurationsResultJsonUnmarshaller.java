package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateEventConfigurationsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateEventConfigurationsResultJsonUnmarshaller implements Unmarshaller<UpdateEventConfigurationsResult, JsonUnmarshallerContext> {
    private static UpdateEventConfigurationsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateEventConfigurationsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateEventConfigurationsResult();
    }

    public static UpdateEventConfigurationsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateEventConfigurationsResultJsonUnmarshaller();
        }
        return instance;
    }
}
