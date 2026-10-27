package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazonaws.services.iot.model.RoleAliasDescription;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class RoleAliasDescriptionJsonUnmarshaller implements Unmarshaller<RoleAliasDescription, JsonUnmarshallerContext> {
    private static RoleAliasDescriptionJsonUnmarshaller instance;

    RoleAliasDescriptionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RoleAliasDescription unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RoleAliasDescription roleAliasDescription = new RoleAliasDescription();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleAlias")) {
                roleAliasDescription.setRoleAlias(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleAliasArn")) {
                roleAliasDescription.setRoleAliasArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                roleAliasDescription.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER)) {
                roleAliasDescription.setOwner(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("credentialDurationSeconds")) {
                roleAliasDescription.setCredentialDurationSeconds(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                roleAliasDescription.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                roleAliasDescription.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return roleAliasDescription;
    }

    public static RoleAliasDescriptionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RoleAliasDescriptionJsonUnmarshaller();
        }
        return instance;
    }
}
