package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteProvisioningTemplateVersionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteProvisioningTemplateVersionResultJsonUnmarshaller implements Unmarshaller<DeleteProvisioningTemplateVersionResult, JsonUnmarshallerContext> {
    private static DeleteProvisioningTemplateVersionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteProvisioningTemplateVersionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteProvisioningTemplateVersionResult();
    }

    public static DeleteProvisioningTemplateVersionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteProvisioningTemplateVersionResultJsonUnmarshaller();
        }
        return instance;
    }
}
