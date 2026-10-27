package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BehaviorCriteria;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class BehaviorCriteriaJsonUnmarshaller implements Unmarshaller<BehaviorCriteria, JsonUnmarshallerContext> {
    private static BehaviorCriteriaJsonUnmarshaller instance;

    BehaviorCriteriaJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public BehaviorCriteria unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        BehaviorCriteria behaviorCriteria = new BehaviorCriteria();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("comparisonOperator")) {
                behaviorCriteria.setComparisonOperator(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("value")) {
                behaviorCriteria.setValue(MetricValueJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("durationSeconds")) {
                behaviorCriteria.setDurationSeconds(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("consecutiveDatapointsToAlarm")) {
                behaviorCriteria.setConsecutiveDatapointsToAlarm(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("consecutiveDatapointsToClear")) {
                behaviorCriteria.setConsecutiveDatapointsToClear(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("statisticalThreshold")) {
                behaviorCriteria.setStatisticalThreshold(StatisticalThresholdJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("mlDetectionConfig")) {
                behaviorCriteria.setMlDetectionConfig(MachineLearningDetectionConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return behaviorCriteria;
    }

    public static BehaviorCriteriaJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorCriteriaJsonUnmarshaller();
        }
        return instance;
    }
}
