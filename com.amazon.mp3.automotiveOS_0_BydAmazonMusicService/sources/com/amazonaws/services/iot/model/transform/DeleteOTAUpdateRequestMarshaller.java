package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteOTAUpdateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteOTAUpdateRequestMarshaller implements Marshaller<Request<DeleteOTAUpdateRequest>, DeleteOTAUpdateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteOTAUpdateRequest> marshall(DeleteOTAUpdateRequest deleteOTAUpdateRequest) {
        if (deleteOTAUpdateRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(DeleteOTAUpdateRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(deleteOTAUpdateRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.DELETE);
        String strReplace = "/otaUpdates/{otaUpdateId}".replace("{otaUpdateId}", deleteOTAUpdateRequest.getOtaUpdateId() == null ? "" : StringUtils.fromString(deleteOTAUpdateRequest.getOtaUpdateId()));
        if (deleteOTAUpdateRequest.getDeleteStream() != null) {
            defaultRequest.addParameter("deleteStream", StringUtils.fromBoolean(deleteOTAUpdateRequest.getDeleteStream()));
        }
        if (deleteOTAUpdateRequest.getForceDeleteAWSJob() != null) {
            defaultRequest.addParameter("forceDeleteAWSJob", StringUtils.fromBoolean(deleteOTAUpdateRequest.getForceDeleteAWSJob()));
        }
        defaultRequest.setResourcePath(strReplace);
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
