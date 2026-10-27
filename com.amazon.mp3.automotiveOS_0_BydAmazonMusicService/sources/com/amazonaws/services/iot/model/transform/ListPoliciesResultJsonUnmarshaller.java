package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListPoliciesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListPoliciesResultJsonUnmarshaller implements Unmarshaller<ListPoliciesResult, JsonUnmarshallerContext> {
    private static ListPoliciesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListPoliciesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListPoliciesResult listPoliciesResult = new ListPoliciesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("policies")) {
                listPoliciesResult.setPolicies(new ListUnmarshaller(PolicyJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextMarker")) {
                listPoliciesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listPoliciesResult;
    }

    public static ListPoliciesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListPoliciesResultJsonUnmarshaller();
        }
        return instance;
    }
}
