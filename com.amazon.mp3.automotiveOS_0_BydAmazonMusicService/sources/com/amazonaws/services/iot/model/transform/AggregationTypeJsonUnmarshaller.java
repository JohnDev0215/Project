package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AggregationType;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class AggregationTypeJsonUnmarshaller implements Unmarshaller<AggregationType, JsonUnmarshallerContext> {
    private static AggregationTypeJsonUnmarshaller instance;

    AggregationTypeJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public AggregationType unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        AggregationType aggregationType = new AggregationType();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                aggregationType.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("values")) {
                aggregationType.setValues(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return aggregationType;
    }

    public static AggregationTypeJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AggregationTypeJsonUnmarshaller();
        }
        return instance;
    }
}
