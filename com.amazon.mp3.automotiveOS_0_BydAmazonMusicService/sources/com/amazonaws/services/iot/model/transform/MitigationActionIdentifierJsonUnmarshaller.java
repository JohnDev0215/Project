package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MitigationActionIdentifier;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MitigationActionIdentifierJsonUnmarshaller implements Unmarshaller<MitigationActionIdentifier, JsonUnmarshallerContext> {
    private static MitigationActionIdentifierJsonUnmarshaller instance;

    MitigationActionIdentifierJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MitigationActionIdentifier unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MitigationActionIdentifier mitigationActionIdentifier = new MitigationActionIdentifier();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionName")) {
                mitigationActionIdentifier.setActionName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionArn")) {
                mitigationActionIdentifier.setActionArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                mitigationActionIdentifier.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return mitigationActionIdentifier;
    }

    public static MitigationActionIdentifierJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MitigationActionIdentifierJsonUnmarshaller();
        }
        return instance;
    }
}
