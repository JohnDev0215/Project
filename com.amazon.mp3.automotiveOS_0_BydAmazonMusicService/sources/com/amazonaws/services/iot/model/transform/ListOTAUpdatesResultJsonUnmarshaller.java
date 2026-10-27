package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListOTAUpdatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListOTAUpdatesResultJsonUnmarshaller implements Unmarshaller<ListOTAUpdatesResult, JsonUnmarshallerContext> {
    private static ListOTAUpdatesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListOTAUpdatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListOTAUpdatesResult listOTAUpdatesResult = new ListOTAUpdatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("otaUpdates")) {
                listOTAUpdatesResult.setOtaUpdates(new ListUnmarshaller(OTAUpdateSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextToken")) {
                listOTAUpdatesResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listOTAUpdatesResult;
    }

    public static ListOTAUpdatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListOTAUpdatesResultJsonUnmarshaller();
        }
        return instance;
    }
}
