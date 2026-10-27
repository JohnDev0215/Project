package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AddThingsToThingGroupParams;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class AddThingsToThingGroupParamsJsonMarshaller {
    private static AddThingsToThingGroupParamsJsonMarshaller instance;

    AddThingsToThingGroupParamsJsonMarshaller() {
    }

    public void marshall(AddThingsToThingGroupParams addThingsToThingGroupParams, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (addThingsToThingGroupParams.getThingGroupNames() != null) {
            List<String> thingGroupNames = addThingsToThingGroupParams.getThingGroupNames();
            awsJsonWriter.name("thingGroupNames");
            awsJsonWriter.beginArray();
            for (String str : thingGroupNames) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (addThingsToThingGroupParams.getOverrideDynamicGroups() != null) {
            Boolean overrideDynamicGroups = addThingsToThingGroupParams.getOverrideDynamicGroups();
            awsJsonWriter.name("overrideDynamicGroups");
            awsJsonWriter.value(overrideDynamicGroups.booleanValue());
        }
        awsJsonWriter.endObject();
    }

    public static AddThingsToThingGroupParamsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AddThingsToThingGroupParamsJsonMarshaller();
        }
        return instance;
    }
}
