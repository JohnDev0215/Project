package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.services.iot.model.AbortCriteria;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class AbortConfigJsonMarshaller {
    private static AbortConfigJsonMarshaller instance;

    AbortConfigJsonMarshaller() {
    }

    public void marshall(AbortConfig abortConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (abortConfig.getCriteriaList() != null) {
            List<AbortCriteria> criteriaList = abortConfig.getCriteriaList();
            awsJsonWriter.name("criteriaList");
            awsJsonWriter.beginArray();
            for (AbortCriteria abortCriteria : criteriaList) {
                if (abortCriteria != null) {
                    AbortCriteriaJsonMarshaller.getInstance().marshall(abortCriteria, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static AbortConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AbortConfigJsonMarshaller();
        }
        return instance;
    }
}
