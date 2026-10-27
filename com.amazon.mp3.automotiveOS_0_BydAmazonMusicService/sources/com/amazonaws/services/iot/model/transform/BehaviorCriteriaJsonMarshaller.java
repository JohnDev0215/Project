package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BehaviorCriteria;
import com.amazonaws.services.iot.model.MachineLearningDetectionConfig;
import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.services.iot.model.StatisticalThreshold;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class BehaviorCriteriaJsonMarshaller {
    private static BehaviorCriteriaJsonMarshaller instance;

    BehaviorCriteriaJsonMarshaller() {
    }

    public void marshall(BehaviorCriteria behaviorCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (behaviorCriteria.getComparisonOperator() != null) {
            String comparisonOperator = behaviorCriteria.getComparisonOperator();
            awsJsonWriter.name("comparisonOperator");
            awsJsonWriter.value(comparisonOperator);
        }
        if (behaviorCriteria.getValue() != null) {
            MetricValue value = behaviorCriteria.getValue();
            awsJsonWriter.name("value");
            MetricValueJsonMarshaller.getInstance().marshall(value, awsJsonWriter);
        }
        if (behaviorCriteria.getDurationSeconds() != null) {
            Integer durationSeconds = behaviorCriteria.getDurationSeconds();
            awsJsonWriter.name("durationSeconds");
            awsJsonWriter.value(durationSeconds);
        }
        if (behaviorCriteria.getConsecutiveDatapointsToAlarm() != null) {
            Integer consecutiveDatapointsToAlarm = behaviorCriteria.getConsecutiveDatapointsToAlarm();
            awsJsonWriter.name("consecutiveDatapointsToAlarm");
            awsJsonWriter.value(consecutiveDatapointsToAlarm);
        }
        if (behaviorCriteria.getConsecutiveDatapointsToClear() != null) {
            Integer consecutiveDatapointsToClear = behaviorCriteria.getConsecutiveDatapointsToClear();
            awsJsonWriter.name("consecutiveDatapointsToClear");
            awsJsonWriter.value(consecutiveDatapointsToClear);
        }
        if (behaviorCriteria.getStatisticalThreshold() != null) {
            StatisticalThreshold statisticalThreshold = behaviorCriteria.getStatisticalThreshold();
            awsJsonWriter.name("statisticalThreshold");
            StatisticalThresholdJsonMarshaller.getInstance().marshall(statisticalThreshold, awsJsonWriter);
        }
        if (behaviorCriteria.getMlDetectionConfig() != null) {
            MachineLearningDetectionConfig mlDetectionConfig = behaviorCriteria.getMlDetectionConfig();
            awsJsonWriter.name("mlDetectionConfig");
            MachineLearningDetectionConfigJsonMarshaller.getInstance().marshall(mlDetectionConfig, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static BehaviorCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorCriteriaJsonMarshaller();
        }
        return instance;
    }
}
