package com.amazon.mp3.net.service;

import com.amazon.mp3.net.AbstractHttpClient;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface ServiceRequest {
    JSONObject execute(final JSONObject requestParams) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException;

    ServiceApi getApiVersion();

    String getRequestName();
}
