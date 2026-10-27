package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicensembleservice.brush.PageRequest;
import com.amazon.musicensembleservice.brush.PageResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class PageCall extends CoralCall<PageRequest, PageResponse> {
    public PageCall(URL url, PageRequest pageRequest, RequestInterceptor requestInterceptor) {
        this(url, pageRequest, requestInterceptor, false);
    }

    public PageCall(URL url, PageRequest pageRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, pageRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new PageApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<PageResponse> getResponseType() {
        return PageResponse.class;
    }
}
