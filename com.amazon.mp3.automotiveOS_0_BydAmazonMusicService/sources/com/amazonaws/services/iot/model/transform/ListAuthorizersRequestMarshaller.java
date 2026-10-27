package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuthorizersRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuthorizersRequestMarshaller implements Marshaller<Request<ListAuthorizersRequest>, ListAuthorizersRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuthorizersRequest> marshall(ListAuthorizersRequest listAuthorizersRequest) {
        if (listAuthorizersRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ListAuthorizersRequest)");
        }
        DefaultRequest defaultRequest = new DefaultRequest(listAuthorizersRequest, "AWSIot");
        defaultRequest.setHttpMethod(HttpMethodName.GET);
        if (listAuthorizersRequest.getPageSize() != null) {
            defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listAuthorizersRequest.getPageSize()));
        }
        if (listAuthorizersRequest.getMarker() != null) {
            defaultRequest.addParameter("marker", StringUtils.fromString(listAuthorizersRequest.getMarker()));
        }
        if (listAuthorizersRequest.getAscendingOrder() != null) {
            defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listAuthorizersRequest.getAscendingOrder()));
        }
        if (listAuthorizersRequest.getStatus() != null) {
            defaultRequest.addParameter(NotificationCompat.CATEGORY_STATUS, StringUtils.fromString(listAuthorizersRequest.getStatus()));
        }
        defaultRequest.setResourcePath("/authorizers/");
        if (!defaultRequest.getHeaders().containsKey(HttpHeader.CONTENT_TYPE)) {
            defaultRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/x-amz-json-1.0");
        }
        return defaultRequest;
    }
}
