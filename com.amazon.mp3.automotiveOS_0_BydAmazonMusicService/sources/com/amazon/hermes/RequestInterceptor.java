package com.amazon.hermes;

import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes2.dex */
public interface RequestInterceptor {
    void intercept(RequestFacade requestFacade) throws Exception;

    void intercept(RequestFacade requestFacade, ObjectMapper objectMapper) throws Exception;
}
