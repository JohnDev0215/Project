package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MitigationAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MitigationActionJsonUnmarshaller implements Unmarshaller<MitigationAction, JsonUnmarshallerContext> {
    private static MitigationActionJsonUnmarshaller instance;

    MitigationActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MitigationAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MitigationAction mitigationAction = new MitigationAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                mitigationAction.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("id")) {
                mitigationAction.setId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                mitigationAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionParams")) {
                mitigationAction.setActionParams(MitigationActionParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return mitigationAction;
    }

    public static MitigationActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MitigationActionJsonUnmarshaller();
        }
        return instance;
    }
}
