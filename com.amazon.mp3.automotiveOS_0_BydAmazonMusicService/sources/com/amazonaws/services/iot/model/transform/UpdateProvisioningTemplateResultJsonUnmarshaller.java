package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateProvisioningTemplateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateProvisioningTemplateResultJsonUnmarshaller implements Unmarshaller<UpdateProvisioningTemplateResult, JsonUnmarshallerContext> {
    private static UpdateProvisioningTemplateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateProvisioningTemplateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateProvisioningTemplateResult();
    }

    public static UpdateProvisioningTemplateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateProvisioningTemplateResultJsonUnmarshaller();
        }
        return instance;
    }
}
