package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IndexingFilter;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class IndexingFilterJsonMarshaller {
    private static IndexingFilterJsonMarshaller instance;

    IndexingFilterJsonMarshaller() {
    }

    public void marshall(IndexingFilter indexingFilter, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (indexingFilter.getNamedShadowNames() != null) {
            List<String> namedShadowNames = indexingFilter.getNamedShadowNames();
            awsJsonWriter.name("namedShadowNames");
            awsJsonWriter.beginArray();
            for (String str : namedShadowNames) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }

    public static IndexingFilterJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IndexingFilterJsonMarshaller();
        }
        return instance;
    }
}
