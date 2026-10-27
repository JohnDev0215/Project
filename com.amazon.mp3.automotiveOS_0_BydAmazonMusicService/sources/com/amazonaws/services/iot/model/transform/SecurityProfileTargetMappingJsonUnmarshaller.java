package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SecurityProfileTargetMapping;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class SecurityProfileTargetMappingJsonUnmarshaller implements Unmarshaller<SecurityProfileTargetMapping, JsonUnmarshallerContext> {
    private static SecurityProfileTargetMappingJsonUnmarshaller instance;

    SecurityProfileTargetMappingJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public SecurityProfileTargetMapping unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        SecurityProfileTargetMapping securityProfileTargetMapping = new SecurityProfileTargetMapping();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("securityProfileIdentifier")) {
                securityProfileTargetMapping.setSecurityProfileIdentifier(SecurityProfileIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("target")) {
                securityProfileTargetMapping.setTarget(SecurityProfileTargetJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return securityProfileTargetMapping;
    }

    public static SecurityProfileTargetMappingJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new SecurityProfileTargetMappingJsonUnmarshaller();
        }
        return instance;
    }
}
