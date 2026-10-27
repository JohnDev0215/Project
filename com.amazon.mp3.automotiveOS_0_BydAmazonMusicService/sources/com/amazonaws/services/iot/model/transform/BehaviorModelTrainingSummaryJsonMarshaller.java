package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BehaviorModelTrainingSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class BehaviorModelTrainingSummaryJsonMarshaller {
    private static BehaviorModelTrainingSummaryJsonMarshaller instance;

    BehaviorModelTrainingSummaryJsonMarshaller() {
    }

    public void marshall(BehaviorModelTrainingSummary behaviorModelTrainingSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (behaviorModelTrainingSummary.getSecurityProfileName() != null) {
            String securityProfileName = behaviorModelTrainingSummary.getSecurityProfileName();
            awsJsonWriter.name("securityProfileName");
            awsJsonWriter.value(securityProfileName);
        }
        if (behaviorModelTrainingSummary.getBehaviorName() != null) {
            String behaviorName = behaviorModelTrainingSummary.getBehaviorName();
            awsJsonWriter.name("behaviorName");
            awsJsonWriter.value(behaviorName);
        }
        if (behaviorModelTrainingSummary.getTrainingDataCollectionStartDate() != null) {
            Date trainingDataCollectionStartDate = behaviorModelTrainingSummary.getTrainingDataCollectionStartDate();
            awsJsonWriter.name("trainingDataCollectionStartDate");
            awsJsonWriter.value(trainingDataCollectionStartDate);
        }
        if (behaviorModelTrainingSummary.getModelStatus() != null) {
            String modelStatus = behaviorModelTrainingSummary.getModelStatus();
            awsJsonWriter.name("modelStatus");
            awsJsonWriter.value(modelStatus);
        }
        if (behaviorModelTrainingSummary.getDatapointsCollectionPercentage() != null) {
            Double datapointsCollectionPercentage = behaviorModelTrainingSummary.getDatapointsCollectionPercentage();
            awsJsonWriter.name("datapointsCollectionPercentage");
            awsJsonWriter.value(datapointsCollectionPercentage);
        }
        if (behaviorModelTrainingSummary.getLastModelRefreshDate() != null) {
            Date lastModelRefreshDate = behaviorModelTrainingSummary.getLastModelRefreshDate();
            awsJsonWriter.name("lastModelRefreshDate");
            awsJsonWriter.value(lastModelRefreshDate);
        }
        awsJsonWriter.endObject();
    }

    public static BehaviorModelTrainingSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorModelTrainingSummaryJsonMarshaller();
        }
        return instance;
    }
}
