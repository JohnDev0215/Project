package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BillingGroupProperties;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class BillingGroupPropertiesJsonMarshaller {
    private static BillingGroupPropertiesJsonMarshaller instance;

    BillingGroupPropertiesJsonMarshaller() {
    }

    public void marshall(BillingGroupProperties billingGroupProperties, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (billingGroupProperties.getBillingGroupDescription() != null) {
            String billingGroupDescription = billingGroupProperties.getBillingGroupDescription();
            awsJsonWriter.name("billingGroupDescription");
            awsJsonWriter.value(billingGroupDescription);
        }
        awsJsonWriter.endObject();
    }

    public static BillingGroupPropertiesJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BillingGroupPropertiesJsonMarshaller();
        }
        return instance;
    }
}
