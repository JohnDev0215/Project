package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteRoleAliasResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteRoleAliasResultJsonUnmarshaller implements Unmarshaller<DeleteRoleAliasResult, JsonUnmarshallerContext> {
    private static DeleteRoleAliasResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteRoleAliasResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteRoleAliasResult();
    }

    public static DeleteRoleAliasResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteRoleAliasResultJsonUnmarshaller();
        }
        return instance;
    }
}
