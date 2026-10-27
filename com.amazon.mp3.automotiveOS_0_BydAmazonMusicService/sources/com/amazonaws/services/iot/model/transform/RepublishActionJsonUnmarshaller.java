package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RepublishAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class RepublishActionJsonUnmarshaller implements Unmarshaller<RepublishAction, JsonUnmarshallerContext> {
    private static RepublishActionJsonUnmarshaller instance;

    RepublishActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RepublishAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RepublishAction republishAction = new RepublishAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                republishAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("topic")) {
                republishAction.setTopic(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("qos")) {
                republishAction.setQos(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return republishAction;
    }

    public static RepublishActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RepublishActionJsonUnmarshaller();
        }
        return instance;
    }
}
