package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Field;
import com.amazonaws.services.iot.model.ThingGroupIndexingConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class ThingGroupIndexingConfigurationJsonMarshaller {
    private static ThingGroupIndexingConfigurationJsonMarshaller instance;

    ThingGroupIndexingConfigurationJsonMarshaller() {
    }

    public void marshall(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingGroupIndexingConfiguration.getThingGroupIndexingMode() != null) {
            String thingGroupIndexingMode = thingGroupIndexingConfiguration.getThingGroupIndexingMode();
            awsJsonWriter.name("thingGroupIndexingMode");
            awsJsonWriter.value(thingGroupIndexingMode);
        }
        if (thingGroupIndexingConfiguration.getManagedFields() != null) {
            List<Field> managedFields = thingGroupIndexingConfiguration.getManagedFields();
            awsJsonWriter.name("managedFields");
            awsJsonWriter.beginArray();
            for (Field field : managedFields) {
                if (field != null) {
                    FieldJsonMarshaller.getInstance().marshall(field, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (thingGroupIndexingConfiguration.getCustomFields() != null) {
            List<Field> customFields = thingGroupIndexingConfiguration.getCustomFields();
            awsJsonWriter.name("customFields");
            awsJsonWriter.beginArray();
            for (Field field2 : customFields) {
                if (field2 != null) {
                    FieldJsonMarshaller.getInstance().marshall(field2, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static ThingGroupIndexingConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupIndexingConfigurationJsonMarshaller();
        }
        return instance;
    }
}
