package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListAuthorizersResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuthorizersResultJsonUnmarshaller implements Unmarshaller<ListAuthorizersResult, JsonUnmarshallerContext> {
    private static ListAuthorizersResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListAuthorizersResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListAuthorizersResult listAuthorizersResult = new ListAuthorizersResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("authorizers")) {
                listAuthorizersResult.setAuthorizers(new ListUnmarshaller(AuthorizerSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextMarker")) {
                listAuthorizersResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listAuthorizersResult;
    }

    public static ListAuthorizersResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListAuthorizersResultJsonUnmarshaller();
        }
        return instance;
    }
}
