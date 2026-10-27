package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.VpcDestinationSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class VpcDestinationSummaryJsonUnmarshaller implements Unmarshaller<VpcDestinationSummary, JsonUnmarshallerContext> {
    private static VpcDestinationSummaryJsonUnmarshaller instance;

    VpcDestinationSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public VpcDestinationSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        VpcDestinationSummary vpcDestinationSummary = new VpcDestinationSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("subnetIds")) {
                vpcDestinationSummary.setSubnetIds(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityGroups")) {
                vpcDestinationSummary.setSecurityGroups(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("vpcId")) {
                vpcDestinationSummary.setVpcId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("roleArn")) {
                vpcDestinationSummary.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return vpcDestinationSummary;
    }

    public static VpcDestinationSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new VpcDestinationSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
