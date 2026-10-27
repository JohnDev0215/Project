package com.amazon.alexa.identity.api;

import rx.Observable;

/* JADX INFO: loaded from: classes2.dex */
public interface IdentityService {
    Observable<String[]> getCookiesFromDirectedId(String str, String str2);
}
