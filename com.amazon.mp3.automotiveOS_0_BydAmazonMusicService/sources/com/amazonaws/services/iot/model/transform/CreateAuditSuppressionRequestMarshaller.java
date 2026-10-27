package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateAuditSuppressionRequest;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.StringWriter;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CreateAuditSuppressionRequestMarshaller implements Marshaller<Request<CreateAuditSuppressionRequest>, CreateAuditSuppressionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateAuditSuppressionRequest> marshall(CreateAuditSuppressionRequest createAuditSuppressionRequest) {
        if (createAuditSuppressionRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(CreateAuditSuppressionRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(createAuditSuppressionRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/audit/suppressions/create");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (createAuditSuppressionRequest.getCheckName() != null) {
                String checkName = createAuditSuppressionRequest.getCheckName();
                jsonWriter.name("checkName");
                jsonWriter.value(checkName);
            }
            if (createAuditSuppressionRequest.getResourceIdentifier() != null) {
                ResourceIdentifier resourceIdentifier = createAuditSuppressionRequest.getResourceIdentifier();
                jsonWriter.name("resourceIdentifier");
                ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, jsonWriter);
            }
            if (createAuditSuppressionRequest.getExpirationDate() != null) {
                Date expirationDate = createAuditSuppressionRequest.getExpirationDate();
                jsonWriter.name("expirationDate");
                jsonWriter.value(expirationDate);
            }
            if (createAuditSuppressionRequest.getSuppressIndefinitely() != null) {
                Boolean suppressIndefinitely = createAuditSuppressionRequest.getSuppressIndefinitely();
                jsonWriter.name("suppressIndefinitely");
                jsonWriter.value(suppressIndefinitely.booleanValue());
            }
            if (createAuditSuppressionRequest.getDescription() != null) {
                String description = createAuditSuppressionRequest.getDescription();
                jsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
                jsonWriter.value(description);
            }
            if (createAuditSuppressionRequest.getClientRequestToken() != null) {
                String clientRequestToken = createAuditSuppressionRequest.getClientRequestToken();
                jsonWriter.name("clientRequestToken");
                jsonWriter.value(clientRequestToken);
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
