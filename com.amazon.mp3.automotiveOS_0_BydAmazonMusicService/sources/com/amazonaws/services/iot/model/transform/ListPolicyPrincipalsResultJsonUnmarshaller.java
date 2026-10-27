package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListPolicyPrincipalsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyPrincipalsResultJsonUnmarshaller implements Unmarshaller<ListPolicyPrincipalsResult, JsonUnmarshallerContext> {
    private static ListPolicyPrincipalsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListPolicyPrincipalsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListPolicyPrincipalsResult listPolicyPrincipalsResult = new ListPolicyPrincipalsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("principals")) {
                listPolicyPrincipalsResult.setPrincipals(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextMarker")) {
                listPolicyPrincipalsResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listPolicyPrincipalsResult;
    }

    public static ListPolicyPrincipalsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListPolicyPrincipalsResultJsonUnmarshaller();
        }
        return instance;
    }
}
