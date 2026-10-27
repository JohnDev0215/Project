package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IotSiteWiseAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class IotSiteWiseActionJsonUnmarshaller implements Unmarshaller<IotSiteWiseAction, JsonUnmarshallerContext> {
    private static IotSiteWiseActionJsonUnmarshaller instance;

    IotSiteWiseActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public IotSiteWiseAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        IotSiteWiseAction iotSiteWiseAction = new IotSiteWiseAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("putAssetPropertyValueEntries")) {
                iotSiteWiseAction.setPutAssetPropertyValueEntries(new ListUnmarshaller(PutAssetPropertyValueEntryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                iotSiteWiseAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return iotSiteWiseAction;
    }

    public static IotSiteWiseActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new IotSiteWiseActionJsonUnmarshaller();
        }
        return instance;
    }
}
