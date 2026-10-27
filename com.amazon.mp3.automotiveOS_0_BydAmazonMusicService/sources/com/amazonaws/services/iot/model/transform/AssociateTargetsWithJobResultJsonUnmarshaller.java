package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AssociateTargetsWithJobResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class AssociateTargetsWithJobResultJsonUnmarshaller implements Unmarshaller<AssociateTargetsWithJobResult, JsonUnmarshallerContext> {
    private static AssociateTargetsWithJobResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public AssociateTargetsWithJobResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AssociateTargetsWithJobResult associateTargetsWithJobResult = new AssociateTargetsWithJobResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobArn")) {
                associateTargetsWithJobResult.setJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobId")) {
                associateTargetsWithJobResult.setJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                associateTargetsWithJobResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return associateTargetsWithJobResult;
    }

    public static AssociateTargetsWithJobResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new AssociateTargetsWithJobResultJsonUnmarshaller();
        }
        return instance;
    }
}
