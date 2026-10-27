package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Denied;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DeniedJsonUnmarshaller implements Unmarshaller<Denied, JsonUnmarshallerContext> {
    private static DeniedJsonUnmarshaller instance;

    DeniedJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Denied unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Denied denied = new Denied();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("implicitDeny")) {
                denied.setImplicitDeny(ImplicitDenyJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("explicitDeny")) {
                denied.setExplicitDeny(ExplicitDenyJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return denied;
    }

    public static DeniedJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeniedJsonUnmarshaller();
        }
        return instance;
    }
}
