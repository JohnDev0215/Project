package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobTemplateSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class JobTemplateSummaryJsonUnmarshaller implements Unmarshaller<JobTemplateSummary, JsonUnmarshallerContext> {
    private static JobTemplateSummaryJsonUnmarshaller instance;

    JobTemplateSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobTemplateSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobTemplateSummary jobTemplateSummary = new JobTemplateSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobTemplateArn")) {
                jobTemplateSummary.setJobTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobTemplateId")) {
                jobTemplateSummary.setJobTemplateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                jobTemplateSummary.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("createdAt")) {
                jobTemplateSummary.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobTemplateSummary;
    }

    public static JobTemplateSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobTemplateSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
