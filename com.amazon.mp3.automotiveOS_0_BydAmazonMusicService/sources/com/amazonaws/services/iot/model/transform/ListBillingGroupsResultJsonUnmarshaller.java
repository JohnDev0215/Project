package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListBillingGroupsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListBillingGroupsResultJsonUnmarshaller implements Unmarshaller<ListBillingGroupsResult, JsonUnmarshallerContext> {
    private static ListBillingGroupsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListBillingGroupsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListBillingGroupsResult listBillingGroupsResult = new ListBillingGroupsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("billingGroups")) {
                listBillingGroupsResult.setBillingGroups(new ListUnmarshaller(GroupNameAndArnJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listBillingGroupsResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listBillingGroupsResult;
    }

    public static ListBillingGroupsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListBillingGroupsResultJsonUnmarshaller();
        }
        return instance;
    }
}
