package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BillingGroupProperties;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class BillingGroupPropertiesJsonUnmarshaller implements Unmarshaller<BillingGroupProperties, JsonUnmarshallerContext> {
    private static BillingGroupPropertiesJsonUnmarshaller instance;

    BillingGroupPropertiesJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public BillingGroupProperties unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        BillingGroupProperties billingGroupProperties = new BillingGroupProperties();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("billingGroupDescription")) {
                billingGroupProperties.setBillingGroupDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return billingGroupProperties;
    }

    public static BillingGroupPropertiesJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BillingGroupPropertiesJsonUnmarshaller();
        }
        return instance;
    }
}
