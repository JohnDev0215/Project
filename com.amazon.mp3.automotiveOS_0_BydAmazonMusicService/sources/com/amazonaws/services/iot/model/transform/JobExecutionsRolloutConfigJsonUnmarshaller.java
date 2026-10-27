package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class JobExecutionsRolloutConfigJsonUnmarshaller implements Unmarshaller<JobExecutionsRolloutConfig, JsonUnmarshallerContext> {
    private static JobExecutionsRolloutConfigJsonUnmarshaller instance;

    JobExecutionsRolloutConfigJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobExecutionsRolloutConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobExecutionsRolloutConfig jobExecutionsRolloutConfig = new JobExecutionsRolloutConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("maximumPerMinute")) {
                jobExecutionsRolloutConfig.setMaximumPerMinute(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("exponentialRate")) {
                jobExecutionsRolloutConfig.setExponentialRate(ExponentialRolloutRateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobExecutionsRolloutConfig;
    }

    public static JobExecutionsRolloutConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionsRolloutConfigJsonUnmarshaller();
        }
        return instance;
    }
}
