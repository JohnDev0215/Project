package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeJobTemplateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobTemplateResultJsonUnmarshaller implements Unmarshaller<DescribeJobTemplateResult, JsonUnmarshallerContext> {
    private static DescribeJobTemplateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeJobTemplateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeJobTemplateResult describeJobTemplateResult = new DescribeJobTemplateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("jobTemplateArn")) {
                describeJobTemplateResult.setJobTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobTemplateId")) {
                describeJobTemplateResult.setJobTemplateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                describeJobTemplateResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("documentSource")) {
                describeJobTemplateResult.setDocumentSource(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("document")) {
                describeJobTemplateResult.setDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("createdAt")) {
                describeJobTemplateResult.setCreatedAt(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("presignedUrlConfig")) {
                describeJobTemplateResult.setPresignedUrlConfig(PresignedUrlConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobExecutionsRolloutConfig")) {
                describeJobTemplateResult.setJobExecutionsRolloutConfig(JobExecutionsRolloutConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("abortConfig")) {
                describeJobTemplateResult.setAbortConfig(AbortConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("timeoutConfig")) {
                describeJobTemplateResult.setTimeoutConfig(TimeoutConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("jobExecutionsRetryConfig")) {
                describeJobTemplateResult.setJobExecutionsRetryConfig(JobExecutionsRetryConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeJobTemplateResult;
    }

    public static DescribeJobTemplateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeJobTemplateResultJsonUnmarshaller();
        }
        return instance;
    }
}
