package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.service.ServiceRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
interface CirrusRequest extends ServiceRequest {
    JSONObject execute() throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException;

    @Override // com.amazon.mp3.net.service.ServiceRequest
    JSONObject execute(final JSONObject requestParams) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException;

    @Override // com.amazon.mp3.net.service.ServiceRequest
    CirrusApi getApiVersion();

    /* JADX INFO: renamed from: getDispatcher */
    CirrusRequestDispatcher mo1357getDispatcher();

    @Override // com.amazon.mp3.net.service.ServiceRequest
    String getRequestName();
}
