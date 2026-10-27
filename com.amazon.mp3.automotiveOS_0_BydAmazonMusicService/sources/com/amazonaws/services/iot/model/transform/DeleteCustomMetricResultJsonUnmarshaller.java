package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteCustomMetricResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteCustomMetricResultJsonUnmarshaller implements Unmarshaller<DeleteCustomMetricResult, JsonUnmarshallerContext> {
    private static DeleteCustomMetricResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteCustomMetricResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteCustomMetricResult();
    }

    public static DeleteCustomMetricResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteCustomMetricResultJsonUnmarshaller();
        }
        return instance;
    }
}
