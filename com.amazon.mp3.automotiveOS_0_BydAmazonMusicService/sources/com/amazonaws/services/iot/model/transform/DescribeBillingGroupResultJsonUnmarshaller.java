package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeBillingGroupResultJsonUnmarshaller implements Unmarshaller<DescribeBillingGroupResult, JsonUnmarshallerContext> {
    private static DescribeBillingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeBillingGroupResult describeBillingGroupResult = new DescribeBillingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("billingGroupName")) {
                describeBillingGroupResult.setBillingGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("billingGroupId")) {
                describeBillingGroupResult.setBillingGroupId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("billingGroupArn")) {
                describeBillingGroupResult.setBillingGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("version")) {
                describeBillingGroupResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("billingGroupProperties")) {
                describeBillingGroupResult.setBillingGroupProperties(BillingGroupPropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("billingGroupMetadata")) {
                describeBillingGroupResult.setBillingGroupMetadata(BillingGroupMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeBillingGroupResult;
    }

    public static DescribeBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
