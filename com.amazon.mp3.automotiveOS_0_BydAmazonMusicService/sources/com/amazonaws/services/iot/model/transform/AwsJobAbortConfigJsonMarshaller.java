package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobAbortConfig;
import com.amazonaws.services.iot.model.AwsJobAbortCriteria;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class AwsJobAbortConfigJsonMarshaller {
    private static AwsJobAbortConfigJsonMarshaller instance;

    AwsJobAbortConfigJsonMarshaller() {
    }

    public void marshall(AwsJobAbortConfig awsJobAbortConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobAbortConfig.getAbortCriteriaList() != null) {
            List<AwsJobAbortCriteria> abortCriteriaList = awsJobAbortConfig.getAbortCriteriaList();
            awsJsonWriter.name("abortCriteriaList");
            awsJsonWriter.beginArray();
            for (AwsJobAbortCriteria awsJobAbortCriteria : abortCriteriaList) {
                if (awsJobAbortCriteria != null) {
                    AwsJobAbortCriteriaJsonMarshaller.getInstance().marshall(awsJobAbortCriteria, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static AwsJobAbortConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobAbortConfigJsonMarshaller();
        }
        return instance;
    }
}
