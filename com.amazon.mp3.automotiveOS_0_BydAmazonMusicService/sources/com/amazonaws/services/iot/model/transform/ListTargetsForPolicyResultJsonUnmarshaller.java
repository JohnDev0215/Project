package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListTargetsForPolicyResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForPolicyResultJsonUnmarshaller implements Unmarshaller<ListTargetsForPolicyResult, JsonUnmarshallerContext> {
    private static ListTargetsForPolicyResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListTargetsForPolicyResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListTargetsForPolicyResult listTargetsForPolicyResult = new ListTargetsForPolicyResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("targets")) {
                listTargetsForPolicyResult.setTargets(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextMarker")) {
                listTargetsForPolicyResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listTargetsForPolicyResult;
    }

    public static ListTargetsForPolicyResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListTargetsForPolicyResultJsonUnmarshaller();
        }
        return instance;
    }
}
