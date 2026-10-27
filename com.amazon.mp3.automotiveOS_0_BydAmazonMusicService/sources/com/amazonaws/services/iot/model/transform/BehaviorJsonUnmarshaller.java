package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class BehaviorJsonUnmarshaller implements Unmarshaller<Behavior, JsonUnmarshallerContext> {
    private static BehaviorJsonUnmarshaller instance;

    BehaviorJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Behavior unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Behavior behavior = new Behavior();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                behavior.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metric")) {
                behavior.setMetric(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("metricDimension")) {
                behavior.setMetricDimension(MetricDimensionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("criteria")) {
                behavior.setCriteria(BehaviorCriteriaJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("suppressAlerts")) {
                behavior.setSuppressAlerts(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return behavior;
    }

    public static BehaviorJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorJsonUnmarshaller();
        }
        return instance;
    }
}
