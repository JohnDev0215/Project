package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteDomainConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteDomainConfigurationResultJsonUnmarshaller implements Unmarshaller<DeleteDomainConfigurationResult, JsonUnmarshallerContext> {
    private static DeleteDomainConfigurationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteDomainConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteDomainConfigurationResult();
    }

    public static DeleteDomainConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteDomainConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }
}
