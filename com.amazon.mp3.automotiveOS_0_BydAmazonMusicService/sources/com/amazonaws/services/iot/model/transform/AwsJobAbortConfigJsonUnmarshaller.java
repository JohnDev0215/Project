package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobAbortConfig;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobAbortConfigJsonUnmarshaller implements Unmarshaller<AwsJobAbortConfig, JsonUnmarshallerContext> {
    private static AwsJobAbortConfigJsonUnmarshaller instance;

    AwsJobAbortConfigJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AwsJobAbortConfig unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AwsJobAbortConfig awsJobAbortConfig = new AwsJobAbortConfig();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("abortCriteriaList")) {
                awsJobAbortConfig.setAbortCriteriaList(new ListUnmarshaller(AwsJobAbortCriteriaJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return awsJobAbortConfig;
    }

    public static AwsJobAbortConfigJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobAbortConfigJsonUnmarshaller();
        }
        return instance;
    }
}
