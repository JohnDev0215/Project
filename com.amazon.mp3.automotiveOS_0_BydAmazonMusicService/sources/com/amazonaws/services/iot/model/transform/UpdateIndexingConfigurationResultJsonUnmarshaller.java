package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateIndexingConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateIndexingConfigurationResultJsonUnmarshaller implements Unmarshaller<UpdateIndexingConfigurationResult, JsonUnmarshallerContext> {
    private static UpdateIndexingConfigurationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateIndexingConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateIndexingConfigurationResult();
    }

    public static UpdateIndexingConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateIndexingConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }
}
