package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeRoleAliasResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeRoleAliasResultJsonUnmarshaller implements Unmarshaller<DescribeRoleAliasResult, JsonUnmarshallerContext> {
    private static DescribeRoleAliasResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeRoleAliasResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeRoleAliasResult describeRoleAliasResult = new DescribeRoleAliasResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("roleAliasDescription")) {
                describeRoleAliasResult.setRoleAliasDescription(RoleAliasDescriptionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeRoleAliasResult;
    }

    public static DescribeRoleAliasResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeRoleAliasResultJsonUnmarshaller();
        }
        return instance;
    }
}
