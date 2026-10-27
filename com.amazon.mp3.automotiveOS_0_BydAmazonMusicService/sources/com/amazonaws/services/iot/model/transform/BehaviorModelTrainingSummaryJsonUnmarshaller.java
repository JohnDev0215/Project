package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.BehaviorModelTrainingSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class BehaviorModelTrainingSummaryJsonUnmarshaller implements Unmarshaller<BehaviorModelTrainingSummary, JsonUnmarshallerContext> {
    private static BehaviorModelTrainingSummaryJsonUnmarshaller instance;

    BehaviorModelTrainingSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public BehaviorModelTrainingSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        BehaviorModelTrainingSummary behaviorModelTrainingSummary = new BehaviorModelTrainingSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("securityProfileName")) {
                behaviorModelTrainingSummary.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("behaviorName")) {
                behaviorModelTrainingSummary.setBehaviorName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("trainingDataCollectionStartDate")) {
                behaviorModelTrainingSummary.setTrainingDataCollectionStartDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("modelStatus")) {
                behaviorModelTrainingSummary.setModelStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("datapointsCollectionPercentage")) {
                behaviorModelTrainingSummary.setDatapointsCollectionPercentage(SimpleTypeJsonUnmarshallers.DoubleJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModelRefreshDate")) {
                behaviorModelTrainingSummary.setLastModelRefreshDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return behaviorModelTrainingSummary;
    }

    public static BehaviorModelTrainingSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new BehaviorModelTrainingSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
