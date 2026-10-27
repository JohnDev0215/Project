package com.dee.app.contacts.common.network.gateway;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazonaws.http.HttpHeader;
import com.dee.app.contacts.common.constants.ApiNameConstant;
import com.dee.app.contacts.common.exceptions.AuthException;
import com.dee.app.contacts.common.exceptions.ClientNotSupportedException;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.GenericType;
import com.dee.app.contacts.common.utils.UserAgent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractServiceGateway {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", AbstractServiceGateway.class);
    private final ContactsMetricsRecorder mContactsMetricsRecorder;
    private final ObjectMapper mJsonConverter;
    private final INetworkingClient mNetworkingClient;
    private final UserAgent mUserAgent;

    protected AbstractServiceGateway(ObjectMapper objectMapper, INetworkingClient iNetworkingClient, ContactsMetricsRecorder contactsMetricsRecorder, UserAgent userAgent) {
        this.mJsonConverter = objectMapper;
        this.mNetworkingClient = iNetworkingClient;
        this.mContactsMetricsRecorder = contactsMetricsRecorder;
        this.mUserAgent = userAgent;
    }

    protected INetworkRequest getRequestObject(String str, ApiNameConstant apiNameConstant) throws ClientNotSupportedException {
        INetworkRequest iNetworkRequest = this.mNetworkingClient.get(str, apiNameConstant.toString());
        iNetworkRequest.withHeader(HttpHeader.USER_AGENT, this.mUserAgent.get());
        return iNetworkRequest;
    }

    protected INetworkRequest patchRequestObject(String str, ApiNameConstant apiNameConstant) throws ClientNotSupportedException {
        INetworkRequest iNetworkRequestPatch = this.mNetworkingClient.patch(str, apiNameConstant.toString());
        iNetworkRequestPatch.withHeader(HttpHeader.USER_AGENT, this.mUserAgent.get());
        return iNetworkRequestPatch;
    }

    protected INetworkRequest postRequestObject(String str, ApiNameConstant apiNameConstant) throws ClientNotSupportedException {
        INetworkRequest iNetworkRequestPost = this.mNetworkingClient.post(str, apiNameConstant.toString());
        iNetworkRequestPost.withHeader(HttpHeader.USER_AGENT, this.mUserAgent.get());
        return iNetworkRequestPost;
    }

    protected <R> R get(INetworkRequest iNetworkRequest, @NonNull GenericType<R> genericType) throws Exception {
        try {
            authenticateRequest(iNetworkRequest);
            return (R) checkAndReturnResponse(this.mNetworkingClient.execute(iNetworkRequest), genericType);
        } catch (NetworkException e) {
            e = e;
            recordMetrics("CommsContacts.get.network.Error");
            LOG.e("Exception while making Get request to service. ", e);
            throw e;
        } catch (AuthException e2) {
            e = e2;
            recordMetrics("CommsContacts.get.network.Error");
            LOG.e("Exception while making Get request to service. ", e);
            throw e;
        } catch (JsonProcessingException e3) {
            recordMetrics("CommsContacts.get.network.Error");
            LOG.e("JsonProcessingException occurred while making Get request to service. ", e3);
            throw e3;
        } catch (Exception e4) {
            recordMetrics("CommsContacts.get.network.Failure");
            LOG.e("Unknown exception occurred while making Get request to service. ", e4);
            throw e4;
        }
    }

    protected <R, P> R patch(@NonNull INetworkRequest iNetworkRequest, @NonNull P p, @NonNull GenericType<R> genericType) throws Exception {
        try {
            authenticateRequest(iNetworkRequest);
            iNetworkRequest.withPayload("application/json", serializeBody(p));
            return (R) checkAndReturnResponse(this.mNetworkingClient.execute(iNetworkRequest), genericType);
        } catch (NetworkException e) {
            e = e;
            recordMetrics("CommsContacts.patch.network.Error");
            LOG.e("Exception while making Patch request to service", e);
            throw e;
        } catch (AuthException e2) {
            e = e2;
            recordMetrics("CommsContacts.patch.network.Error");
            LOG.e("Exception while making Patch request to service", e);
            throw e;
        } catch (JsonProcessingException e3) {
            recordMetrics("CommsContacts.patch.network.Error");
            LOG.e("JsonProcessingException occurred while making Patch request to service. ", e3);
            throw e3;
        } catch (Exception e4) {
            recordMetrics("CommsContacts.patch.network.Failure");
            LOG.e("Unknown exception occurred while making Patch request to service. ", e4);
            throw e4;
        }
    }

    protected <R, P> R post(@NonNull INetworkRequest iNetworkRequest, @NonNull P p, @NonNull GenericType<R> genericType) throws Exception {
        try {
            authenticateRequest(iNetworkRequest);
            iNetworkRequest.withPayload("application/json", serializeBody(p));
            return (R) checkAndReturnResponse(this.mNetworkingClient.execute(iNetworkRequest), genericType);
        } catch (NetworkException e) {
            e = e;
            recordMetrics("CommsContacts.post.network.Error");
            LOG.e("Exception while making Post request to service", e);
            throw e;
        } catch (AuthException e2) {
            e = e2;
            recordMetrics("CommsContacts.post.network.Error");
            LOG.e("Exception while making Post request to service", e);
            throw e;
        } catch (JsonProcessingException e3) {
            recordMetrics("CommsContacts.post.network.Error");
            LOG.e("JsonProcessingException occurred while making Post request to service. ", e3);
            throw e3;
        } catch (Exception e4) {
            recordMetrics("CommsContacts.post.network.Failure");
            LOG.e("Unknown exception occurred while making Post request to service. ", e4);
            throw e4;
        }
    }

    private void authenticateRequest(INetworkRequest iNetworkRequest) throws AuthException {
        try {
            this.mNetworkingClient.getAuthProvider().authenticateRequest(iNetworkRequest);
        } catch (Exception e) {
            throw new AuthException(e);
        }
    }

    private <R> R checkAndReturnResponse(INetworkResponse iNetworkResponse, GenericType<R> genericType) throws JsonProcessingException {
        if (!iNetworkResponse.isSuccessful()) {
            LOG.e("Response from server is invalid - null");
            throw new IllegalStateException("The response returned from server is invalid");
        }
        return (R) convert(iNetworkResponse.getBody(), genericType.getType());
    }

    private <R> R convert(String str, Class<R> cls) throws JsonProcessingException {
        if (cls == String.class) {
            return cls.cast(str);
        }
        if (StringUtils.isBlank(str)) {
            throw new IllegalStateException("Cannot convert empty or null response to class type");
        }
        try {
            return (R) this.mJsonConverter.readValue(str, cls);
        } catch (JsonProcessingException e) {
            LOG.e("Error while converting JSON response to Object.");
            throw e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> String serializeBody(T t) throws JsonProcessingException {
        try {
            return !t.getClass().equals(String.class) ? this.mJsonConverter.writeValueAsString(t) : (String) t;
        } catch (JsonProcessingException e) {
            LOG.e("Failed to serialize body while making Post request to service", e);
            throw e;
        }
    }

    private void recordMetrics(String str) {
        this.mContactsMetricsRecorder.recordCounter(str, AbstractServiceGateway.class);
    }

    protected void recordMetrics(String str, Class cls) {
        this.mContactsMetricsRecorder.recordCounter(str, cls);
    }
}
