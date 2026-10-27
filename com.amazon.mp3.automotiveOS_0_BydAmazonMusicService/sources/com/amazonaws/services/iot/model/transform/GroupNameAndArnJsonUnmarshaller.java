package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GroupNameAndArn;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class GroupNameAndArnJsonUnmarshaller implements Unmarshaller<GroupNameAndArn, JsonUnmarshallerContext> {
    private static GroupNameAndArnJsonUnmarshaller instance;

    GroupNameAndArnJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public GroupNameAndArn unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        GroupNameAndArn groupNameAndArn = new GroupNameAndArn();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("groupName")) {
                groupNameAndArn.setGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("groupArn")) {
                groupNameAndArn.setGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return groupNameAndArn;
    }

    public static GroupNameAndArnJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GroupNameAndArnJsonUnmarshaller();
        }
        return instance;
    }
}
