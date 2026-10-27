package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteProvisioningTemplateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteProvisioningTemplateResultJsonUnmarshaller implements Unmarshaller<DeleteProvisioningTemplateResult, JsonUnmarshallerContext> {
    private static DeleteProvisioningTemplateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteProvisioningTemplateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteProvisioningTemplateResult();
    }

    public static DeleteProvisioningTemplateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteProvisioningTemplateResultJsonUnmarshaller();
        }
        return instance;
    }
}
