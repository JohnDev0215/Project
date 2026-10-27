package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeprecateThingTypeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeprecateThingTypeResultJsonUnmarshaller implements Unmarshaller<DeprecateThingTypeResult, JsonUnmarshallerContext> {
    private static DeprecateThingTypeResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeprecateThingTypeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeprecateThingTypeResult();
    }

    public static DeprecateThingTypeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeprecateThingTypeResultJsonUnmarshaller();
        }
        return instance;
    }
}
