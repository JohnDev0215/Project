package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GroupNameAndArn;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class GroupNameAndArnJsonMarshaller {
    private static GroupNameAndArnJsonMarshaller instance;

    GroupNameAndArnJsonMarshaller() {
    }

    public void marshall(GroupNameAndArn groupNameAndArn, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (groupNameAndArn.getGroupName() != null) {
            String groupName = groupNameAndArn.getGroupName();
            awsJsonWriter.name("groupName");
            awsJsonWriter.value(groupName);
        }
        if (groupNameAndArn.getGroupArn() != null) {
            String groupArn = groupNameAndArn.getGroupArn();
            awsJsonWriter.name("groupArn");
            awsJsonWriter.value(groupArn);
        }
        awsJsonWriter.endObject();
    }

    public static GroupNameAndArnJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new GroupNameAndArnJsonMarshaller();
        }
        return instance;
    }
}
