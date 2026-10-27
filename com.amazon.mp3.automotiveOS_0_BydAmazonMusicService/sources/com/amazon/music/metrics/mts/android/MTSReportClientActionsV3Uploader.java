package com.amazon.music.metrics.mts.android;

import com.amazon.cirrus.libraryservice.external.v3.ReportClientActionsCall;
import com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback;
import com.amazon.cirrus.libraryservice.v3.ClientAction;
import com.amazon.cirrus.libraryservice.v3.ReportClientActionsRequest;
import com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponse;
import com.amazon.cirrus.shared.model.AccountNotFoundException;
import com.amazon.cirrus.shared.model.AuthenticationException;
import com.amazon.cirrus.shared.model.InvalidParameterException;
import com.amazon.cirrus.shared.model.RequestThrottledException;
import com.amazon.cirrus.shared.model.ResourceNotFoundException;
import com.amazon.cirrus.shared.model.ServiceException;
import com.amazon.hermes.AuthMethod;
import com.amazon.hermes.UnhandledError;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.MTSEventUploader;
import com.amazon.music.metrics.mts.MTSEventUploaderCallback;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MTSReportClientActionsV3Uploader implements MTSEventUploader {
    private static final Logger LOG = LoggerFactory.getLogger(MTSReportClientActionsV3Uploader.class.getSimpleName());
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private MTSEventUploadConfig mConfig;
    private RetryPolicy mRetryPolicy;

    public interface AuthProvider {
        AuthMethod provideAuth(Object obj);
    }

    public interface MTSEventUploadConfig {
        boolean doesCustomerHaveCloudPlayerAccount();

        boolean doesNetworkSupportUpload();

        AuthProvider getAuthProvider();

        String getDeviceId();

        String getDeviceType();

        String getMusicRequestIdentityContextToken();

        URL getURL();

        boolean isCompressedCoralRequestsSupported();
    }

    public MTSReportClientActionsV3Uploader(MTSEventUploadConfig mTSEventUploadConfig) {
        this(mTSEventUploadConfig, buildDefaultPolicy());
    }

    public MTSReportClientActionsV3Uploader(MTSEventUploadConfig mTSEventUploadConfig, RetryPolicy retryPolicy) {
        this.mConfig = mTSEventUploadConfig;
        this.mRetryPolicy = retryPolicy;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventUploader
    public void uploadEvents(List<MTSEvent> list, MTSEventUploaderCallback mTSEventUploaderCallback) {
        LOG.trace("uploadEvents");
        if (list.size() == 0) {
            mTSEventUploaderCallback.onSuccess();
            return;
        }
        Exception excCanUpload = canUpload(this.mConfig);
        if (excCanUpload != null) {
            mTSEventUploaderCallback.onError(excCanUpload);
        } else {
            upload(list, mTSEventUploaderCallback);
        }
    }

    private Exception canUpload(MTSEventUploadConfig mTSEventUploadConfig) {
        if (!mTSEventUploadConfig.doesCustomerHaveCloudPlayerAccount()) {
            return new IllegalStateException("Customer account does not exist.");
        }
        if (mTSEventUploadConfig.doesNetworkSupportUpload()) {
            return null;
        }
        return new IllegalStateException("Network does not support event uploading.");
    }

    private void upload(List<MTSEvent> list, MTSEventUploaderCallback mTSEventUploaderCallback) {
        buildReportClientActionsCall(this.mConfig, convertToActions(list)).execute(new ResponseCallback(mTSEventUploaderCallback), MAPPER);
    }

    private ReportClientActionsCall buildReportClientActionsCall(MTSEventUploadConfig mTSEventUploadConfig, List<ClientAction> list) {
        ReportClientActionsRequest reportClientActionsRequestBuildReportClientActionsRequest = buildReportClientActionsRequest(mTSEventUploadConfig, list);
        ReportClientActionsCall reportClientActionsCall = new ReportClientActionsCall(mTSEventUploadConfig.getAuthProvider().provideAuth(reportClientActionsRequestBuildReportClientActionsRequest), mTSEventUploadConfig.getURL(), reportClientActionsRequestBuildReportClientActionsRequest, mTSEventUploadConfig.isCompressedCoralRequestsSupported());
        setRetryPolicy(reportClientActionsCall);
        return reportClientActionsCall;
    }

    private ReportClientActionsRequest buildReportClientActionsRequest(MTSEventUploadConfig mTSEventUploadConfig, List<ClientAction> list) {
        ReportClientActionsRequest reportClientActionsRequest = new ReportClientActionsRequest();
        reportClientActionsRequest.setDeviceType(mTSEventUploadConfig.getDeviceType());
        reportClientActionsRequest.setDeviceId(mTSEventUploadConfig.getDeviceId());
        reportClientActionsRequest.setClientActionList(list);
        reportClientActionsRequest.setMusicRequestIdentityContextToken(mTSEventUploadConfig.getMusicRequestIdentityContextToken());
        return reportClientActionsRequest;
    }

    private void setRetryPolicy(ReportClientActionsCall reportClientActionsCall) {
        RetryPolicy retryPolicy = this.mRetryPolicy;
        if (retryPolicy != null) {
            reportClientActionsCall.setRetryPolicy(retryPolicy);
        }
    }

    private List<ClientAction> convertToActions(List<MTSEvent> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<MTSEvent> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convert(it.next()));
        }
        return arrayList;
    }

    private ClientAction convert(MTSEvent mTSEvent) {
        String eventName = mTSEvent.getEventName();
        ClientAction clientAction = new ClientAction();
        clientAction.setActionName(eventName);
        clientAction.setAdditionalDetails(mTSEvent.getEventAttributes());
        Map<String, String> metricsContext = mTSEvent.getMetricsContext();
        if (metricsContext != null && metricsContext.size() > 0) {
            clientAction.setMetricsContext(metricsContext);
        }
        List<Map<String, String>> contentInfoAttributes = mTSEvent.getContentInfoAttributes();
        if (contentInfoAttributes.size() > 0) {
            clientAction.setContentInfo(contentInfoAttributes);
        }
        clientAction.setTimestamp(convert(mTSEvent.getTimestamp()));
        return clientAction;
    }

    private Date convert(long j) {
        return new Date(j);
    }

    private static class ResponseCallback extends ReportClientActionsCallback {
        private final MTSEventUploaderCallback mCallback;

        public ResponseCallback(MTSEventUploaderCallback mTSEventUploaderCallback) {
            this.mCallback = mTSEventUploaderCallback;
        }

        @Override // com.amazon.hermes.Callback
        public void onSuccess(ReportClientActionsResponse reportClientActionsResponse) {
            this.mCallback.onSuccess();
        }

        @Override // com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback
        public void onRequestThrottledException(RequestThrottledException requestThrottledException) {
            super.onRequestThrottledException(requestThrottledException);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: RequestThrottledException");
            this.mCallback.onError(requestThrottledException);
        }

        @Override // com.amazon.hermes.Callback
        public void onNoConnectionError(NoConnectionError noConnectionError) {
            super.onNoConnectionError(noConnectionError);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: NoConnectionError");
            this.mCallback.onError(noConnectionError);
        }

        @Override // com.amazon.hermes.Callback
        public void onTimeoutError(TimeoutError timeoutError) {
            super.onTimeoutError(timeoutError);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: TimeoutError");
            this.mCallback.onError(timeoutError);
        }

        @Override // com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback
        public void onServiceException(ServiceException serviceException) {
            super.onServiceException(serviceException);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: ServiceException");
            this.mCallback.onError(serviceException);
        }

        @Override // com.amazon.hermes.Callback
        public void onNetworkError(NetworkError networkError) {
            super.onNetworkError(networkError);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: NetworkError");
            this.mCallback.onError(networkError);
        }

        @Override // com.amazon.hermes.Callback
        public void onParseError(ParseError parseError) {
            super.onParseError(parseError);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: ParseError");
            this.mCallback.onError(parseError);
        }

        @Override // com.amazon.hermes.Callback
        public void onServerError(ServerError serverError) {
            super.onServerError(serverError);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: ServerError");
            this.mCallback.onError(serverError);
        }

        @Override // com.amazon.hermes.Callback
        public void onUnhandledError(UnhandledError unhandledError) {
            super.onUnhandledError(unhandledError);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: UnhandledError");
            this.mCallback.onUnrecoverableError(unhandledError);
        }

        @Override // com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback
        public void onInvalidParameterException(InvalidParameterException invalidParameterException) {
            super.onInvalidParameterException(invalidParameterException);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: InvalidParameterException");
            this.mCallback.onUnrecoverableError(invalidParameterException);
        }

        @Override // com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback
        public void onAuthenticationException(AuthenticationException authenticationException) {
            super.onAuthenticationException(authenticationException);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: AuthenticationException");
            this.mCallback.onUnrecoverableError(authenticationException);
        }

        @Override // com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback
        public void onAccountNotFoundException(AccountNotFoundException accountNotFoundException) {
            super.onAccountNotFoundException(accountNotFoundException);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: AccountNotFoundException");
            this.mCallback.onUnrecoverableError(accountNotFoundException);
        }

        @Override // com.amazon.cirrus.libraryservice.internal.v3.ReportClientActionsCallback
        public void onResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
            super.onResourceNotFoundException(resourceNotFoundException);
            MTSReportClientActionsV3Uploader.LOG.debug("Upload Error: ResourceNotFoundException");
            this.mCallback.onUnrecoverableError(resourceNotFoundException);
        }
    }

    private static RetryPolicy buildDefaultPolicy() {
        return new DefaultRetryPolicy(40000, 0, 0.0f);
    }
}
