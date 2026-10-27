package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.services.iot.model.UpdateAuditSuppressionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateAuditSuppressionRequestMarshaller implements Marshaller<Request<UpdateAuditSuppressionRequest>, UpdateAuditSuppressionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateAuditSuppressionRequest> marshall(UpdateAuditSuppressionRequest updateAuditSuppressionRequest) {
        if (updateAuditSuppressionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateAuditSuppressionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(updateAuditSuppressionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.PATCH);
        defaultRequest.setResourcePath("/audit/suppressions/update");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateAuditSuppressionRequest.getCheckName() != null) {
                String checkName = updateAuditSuppressionRequest.getCheckName();
                jsonWriter.name("checkName");
                jsonWriter.value(checkName);
            }
            if (updateAuditSuppressionRequest.getResourceIdentifier() != null) {
                ResourceIdentifier resourceIdentifier = updateAuditSuppressionRequest.getResourceIdentifier();
                jsonWriter.name("resourceIdentifier");
                ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, jsonWriter);
            }
            if (updateAuditSuppressionRequest.getExpirationDate() != null) {
                Date expirationDate = updateAuditSuppressionRequest.getExpirationDate();
                jsonWriter.name("expirationDate");
                jsonWriter.value(expirationDate);
            }
            if (updateAuditSuppressionRequest.getSuppressIndefinitely() != null) {
                Boolean suppressIndefinitely = updateAuditSuppressionRequest.getSuppressIndefinitely();
                jsonWriter.name("suppressIndefinitely");
                jsonWriter.value(suppressIndefinitely.booleanValue());
            }
            if (updateAuditSuppressionRequest.getDescription() != null) {
                String description = updateAuditSuppressionRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
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
