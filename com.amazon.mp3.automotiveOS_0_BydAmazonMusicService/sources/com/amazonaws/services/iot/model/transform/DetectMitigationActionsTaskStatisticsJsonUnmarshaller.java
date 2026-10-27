package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DetectMitigationActionsTaskStatistics;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class DetectMitigationActionsTaskStatisticsJsonUnmarshaller implements Unmarshaller<DetectMitigationActionsTaskStatistics, JsonUnmarshallerContext> {
    private static DetectMitigationActionsTaskStatisticsJsonUnmarshaller instance;

    DetectMitigationActionsTaskStatisticsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DetectMitigationActionsTaskStatistics unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        DetectMitigationActionsTaskStatistics detectMitigationActionsTaskStatistics = new DetectMitigationActionsTaskStatistics();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("actionsExecuted")) {
                detectMitigationActionsTaskStatistics.setActionsExecuted(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionsSkipped")) {
                detectMitigationActionsTaskStatistics.setActionsSkipped(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("actionsFailed")) {
                detectMitigationActionsTaskStatistics.setActionsFailed(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return detectMitigationActionsTaskStatistics;
    }

    public static DetectMitigationActionsTaskStatisticsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DetectMitigationActionsTaskStatisticsJsonUnmarshaller();
        }
        return instance;
    }
}
