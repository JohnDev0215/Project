package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListPolicyVersionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyVersionsResultJsonUnmarshaller implements Unmarshaller<ListPolicyVersionsResult, JsonUnmarshallerContext> {
    private static ListPolicyVersionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListPolicyVersionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListPolicyVersionsResult listPolicyVersionsResult = new ListPolicyVersionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("policyVersions")) {
                listPolicyVersionsResult.setPolicyVersions(new ListUnmarshaller(PolicyVersionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listPolicyVersionsResult;
    }

    public static ListPolicyVersionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListPolicyVersionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
