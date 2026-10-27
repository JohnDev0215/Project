package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.VpcDestinationConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class VpcDestinationConfigurationJsonUnmarshaller implements Unmarshaller<VpcDestinationConfiguration, JsonUnmarshallerContext> {
    private static VpcDestinationConfigurationJsonUnmarshaller instance;

    VpcDestinationConfigurationJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public VpcDestinationConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        VpcDestinationConfiguration vpcDestinationConfiguration = new VpcDestinationConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("subnetIds")) {
                vpcDestinationConfiguration.setSubnetIds(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityGroups")) {
                vpcDestinationConfiguration.setSecurityGroups(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("vpcId")) {
                vpcDestinationConfiguration.setVpcId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                vpcDestinationConfiguration.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return vpcDestinationConfiguration;
    }

    public static VpcDestinationConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new VpcDestinationConfigurationJsonUnmarshaller();
        }
        return instance;
    }
}
