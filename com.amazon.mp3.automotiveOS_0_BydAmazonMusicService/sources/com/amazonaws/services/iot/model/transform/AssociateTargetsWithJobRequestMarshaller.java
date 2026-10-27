package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AssociateTargetsWithJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AssociateTargetsWithJobRequestMarshaller implements Marshaller<Request<AssociateTargetsWithJobRequest>, AssociateTargetsWithJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AssociateTargetsWithJobRequest> marshall(AssociateTargetsWithJobRequest associateTargetsWithJobRequest) {
        if (associateTargetsWithJobRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(AssociateTargetsWithJobRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(associateTargetsWithJobRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        String strReplace = "/jobs/{jobId}/targets".replace("{jobId}", associateTargetsWithJobRequest.getJobId() == null ? "" : StringUtils.fromString(associateTargetsWithJobRequest.getJobId()));
        if (associateTargetsWithJobRequest.getNamespaceId() != null) {
            defaultRequest.addParameter("namespaceId", StringUtils.fromString(associateTargetsWithJobRequest.getNamespaceId()));
        }
        defaultRequest.setResourcePath(strReplace);
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (associateTargetsWithJobRequest.getTargets() != null) {
                List<String> targets = associateTargetsWithJobRequest.getTargets();
                jsonWriter.name("targets");
                jsonWriter.beginArray();
                for (String str : targets) {
                    if (str != null) {
                        jsonWriter.value(str);
                    }
                }
                jsonWriter.endArray();
            }
            if (associateTargetsWithJobRequest.getComment() != null) {
                String comment = associateTargetsWithJobRequest.getComment();
                jsonWriter.name("comment");
                jsonWriter.value(comment);
            }
            jsonWriter.endObject();
            jsonWriter.close();
            String string = stringWriter.toString();
            byte[] bytes = string.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new StringInputStream(string));
            defaultRequest.addHeader(HttpHeader.CONTENT_LENGTH, Integer.toString(bytes.length));
            if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
                defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
            }
            return defaultRequest;
        } catch (Throwable th) {
            throw new AmazonClientException("Unable to marshall request to JSON: " + th.getMessage(), th);
        }
    }
}
