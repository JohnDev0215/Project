package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IndexingFilter;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class IndexingFilterJsonUnmarshaller implements Unmarshaller<IndexingFilter, JsonUnmarshallerContext> {
    private static IndexingFilterJsonUnmarshaller instance;

    IndexingFilterJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public IndexingFilter unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        IndexingFilter indexingFilter = new IndexingFilter();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("namedShadowNames")) {
                indexingFilter.setNamedShadowNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return indexingFilter;
    }

    public static IndexingFilterJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new IndexingFilterJsonUnmarshaller();
        }
        return instance;
    }
}
