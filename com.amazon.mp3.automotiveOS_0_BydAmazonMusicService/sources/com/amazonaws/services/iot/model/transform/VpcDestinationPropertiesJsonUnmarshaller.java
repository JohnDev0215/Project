package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.VpcDestinationProperties;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class VpcDestinationPropertiesJsonUnmarshaller implements Unmarshaller<VpcDestinationProperties, JsonUnmarshallerContext> {
    private static VpcDestinationPropertiesJsonUnmarshaller instance;

    VpcDestinationPropertiesJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public VpcDestinationProperties unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        VpcDestinationProperties vpcDestinationProperties = new VpcDestinationProperties();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("subnetIds")) {
                vpcDestinationProperties.setSubnetIds(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityGroups")) {
                vpcDestinationProperties.setSecurityGroups(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("vpcId")) {
                vpcDestinationProperties.setVpcId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                vpcDestinationProperties.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return vpcDestinationProperties;
    }

    public static VpcDestinationPropertiesJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new VpcDestinationPropertiesJsonUnmarshaller();
        }
        return instance;
    }
}
