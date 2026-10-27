package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.VpcDestinationConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class VpcDestinationConfigurationJsonMarshaller {
    private static VpcDestinationConfigurationJsonMarshaller instance;

    VpcDestinationConfigurationJsonMarshaller() {
    }

    public void marshall(VpcDestinationConfiguration vpcDestinationConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (vpcDestinationConfiguration.getSubnetIds() != null) {
            List<String> subnetIds = vpcDestinationConfiguration.getSubnetIds();
            awsJsonWriter.name("subnetIds");
            awsJsonWriter.beginArray();
            for (String str : subnetIds) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (vpcDestinationConfiguration.getSecurityGroups() != null) {
            List<String> securityGroups = vpcDestinationConfiguration.getSecurityGroups();
            awsJsonWriter.name("securityGroups");
            awsJsonWriter.beginArray();
            for (String str2 : securityGroups) {
                if (str2 != null) {
                    awsJsonWriter.value(str2);
                }
            }
            awsJsonWriter.endArray();
        }
        if (vpcDestinationConfiguration.getVpcId() != null) {
            String vpcId = vpcDestinationConfiguration.getVpcId();
            awsJsonWriter.name("vpcId");
            awsJsonWriter.value(vpcId);
        }
        if (vpcDestinationConfiguration.getRoleArn() != null) {
            String roleArn = vpcDestinationConfiguration.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static VpcDestinationConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new VpcDestinationConfigurationJsonMarshaller();
        }
        return instance;
    }
}
