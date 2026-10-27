package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetBehaviorModelTrainingSummariesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetBehaviorModelTrainingSummariesResultJsonUnmarshaller implements Unmarshaller<GetBehaviorModelTrainingSummariesResult, JsonUnmarshallerContext> {
    private static GetBehaviorModelTrainingSummariesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetBehaviorModelTrainingSummariesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetBehaviorModelTrainingSummariesResult getBehaviorModelTrainingSummariesResult = new GetBehaviorModelTrainingSummariesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("summaries")) {
                getBehaviorModelTrainingSummariesResult.setSummaries(new ListUnmarshaller(BehaviorModelTrainingSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                getBehaviorModelTrainingSummariesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getBehaviorModelTrainingSummariesResult;
    }

    public static GetBehaviorModelTrainingSummariesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetBehaviorModelTrainingSummariesResultJsonUnmarshaller();
        }
        return instance;
    }
}
