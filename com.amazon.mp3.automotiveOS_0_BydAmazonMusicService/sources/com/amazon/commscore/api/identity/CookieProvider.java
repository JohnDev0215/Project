package com.amazon.commscore.api.identity;

import rx.Observable;

/* JADX INFO: loaded from: classes2.dex */
public interface CookieProvider {
    Observable<String[]> getCookies(String str, String str2);
}
