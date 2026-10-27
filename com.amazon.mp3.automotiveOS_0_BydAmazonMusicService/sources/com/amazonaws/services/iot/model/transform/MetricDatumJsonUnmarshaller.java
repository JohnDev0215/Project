package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.MetricDatum;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MetricDatumJsonUnmarshaller implements Unmarshaller<MetricDatum, JsonUnmarshallerContext> {
    private static MetricDatumJsonUnmarshaller instance;

    MetricDatumJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MetricDatum unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MetricDatum metricDatum = new MetricDatum();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals(TetheredMessageKey.timestamp)) {
                metricDatum.setTimestamp(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("value")) {
                metricDatum.setValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return metricDatum;
    }

    public static MetricDatumJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MetricDatumJsonUnmarshaller();
        }
        return instance;
    }
}
