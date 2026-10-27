package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.VpcDestinationSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class VpcDestinationSummaryJsonMarshaller {
    private static VpcDestinationSummaryJsonMarshaller instance;

    VpcDestinationSummaryJsonMarshaller() {
    }

    public void marshall(VpcDestinationSummary vpcDestinationSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (vpcDestinationSummary.getSubnetIds() != null) {
            List<String> subnetIds = vpcDestinationSummary.getSubnetIds();
            awsJsonWriter.name("subnetIds");
            awsJsonWriter.beginArray();
            for (String str : subnetIds) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (vpcDestinationSummary.getSecurityGroups() != null) {
            List<String> securityGroups = vpcDestinationSummary.getSecurityGroups();
            awsJsonWriter.name("securityGroups");
            awsJsonWriter.beginArray();
            for (String str2 : securityGroups) {
                if (str2 != null) {
                    awsJsonWriter.value(str2);
                }
            }
            awsJsonWriter.endArray();
        }
        if (vpcDestinationSummary.getVpcId() != null) {
            String vpcId = vpcDestinationSummary.getVpcId();
            awsJsonWriter.name("vpcId");
            awsJsonWriter.value(vpcId);
        }
        if (vpcDestinationSummary.getRoleArn() != null) {
            String roleArn = vpcDestinationSummary.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static VpcDestinationSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new VpcDestinationSummaryJsonMarshaller();
        }
        return instance;
    }
}
