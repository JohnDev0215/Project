package com.amazonaws.mobileconnectors.apigateway;

import com.amazonaws.AmazonServiceException;

/* JADX INFO: loaded from: classes5.dex */
public class ApiClientException extends AmazonServiceException {
    private static final long serialVersionUID = 1;

    public ApiClientException(String str) {
        super(str);
    }

    public ApiClientException(String str, Exception exc) {
        super(str, exc);
    }
}
