package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SalesforceAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes5.dex */
class SalesforceActionJsonUnmarshaller implements Unmarshaller<SalesforceAction, JsonUnmarshallerContext> {
    private static SalesforceActionJsonUnmarshaller instance;

    SalesforceActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SalesforceAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SalesforceAction salesforceAction = new SalesforceAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("token")) {
                salesforceAction.setToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(ImagesContract.URL)) {
                salesforceAction.setUrl(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return salesforceAction;
    }

    public static SalesforceActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SalesforceActionJsonUnmarshaller();
        }
        return instance;
    }
}
