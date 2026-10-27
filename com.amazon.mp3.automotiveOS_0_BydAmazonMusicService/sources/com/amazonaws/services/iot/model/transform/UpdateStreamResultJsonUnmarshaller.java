package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateStreamResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateStreamResultJsonUnmarshaller implements Unmarshaller<UpdateStreamResult, JsonUnmarshallerContext> {
    private static UpdateStreamResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateStreamResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateStreamResult updateStreamResult = new UpdateStreamResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("streamId")) {
                updateStreamResult.setStreamId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("streamArn")) {
                updateStreamResult.setStreamArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                updateStreamResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("streamVersion")) {
                updateStreamResult.setStreamVersion(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateStreamResult;
    }

    public static UpdateStreamResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateStreamResultJsonUnmarshaller();
        }
        return instance;
    }
}
