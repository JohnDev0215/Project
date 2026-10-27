package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CancelJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class CancelJobResultJsonUnmarshaller implements Unmarshaller<CancelJobResult, JsonUnmarshallerContext> {
    private static CancelJobResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CancelJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CancelJobResult cancelJobResult = new CancelJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobArn")) {
                cancelJobResult.setJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobId")) {
                cancelJobResult.setJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                cancelJobResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return cancelJobResult;
    }

    public static CancelJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CancelJobResultJsonUnmarshaller();
        }
        return instance;
    }
}
