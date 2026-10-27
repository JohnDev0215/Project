package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateRoleAliasResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateRoleAliasResultJsonUnmarshaller implements Unmarshaller<CreateRoleAliasResult, JsonUnmarshallerContext> {
    private static CreateRoleAliasResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateRoleAliasResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateRoleAliasResult createRoleAliasResult = new CreateRoleAliasResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleAlias")) {
                createRoleAliasResult.setRoleAlias(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleAliasArn")) {
                createRoleAliasResult.setRoleAliasArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createRoleAliasResult;
    }

    public static CreateRoleAliasResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateRoleAliasResultJsonUnmarshaller();
        }
        return instance;
    }
}
