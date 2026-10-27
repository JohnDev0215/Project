package com.amazon.identity.kcpsdk.common;

import com.google.firebase.perf.FirebasePerformance;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum HttpVerb {
    HttpVerbConnect(FirebasePerformance.HttpMethod.CONNECT),
    HttpVerbDelete(FirebasePerformance.HttpMethod.DELETE),
    HttpVerbGet(FirebasePerformance.HttpMethod.GET),
    HttpVerbHead(FirebasePerformance.HttpMethod.HEAD),
    HttpVerbOptions(FirebasePerformance.HttpMethod.OPTIONS),
    HttpVerbPost(FirebasePerformance.HttpMethod.POST),
    HttpVerbPut(FirebasePerformance.HttpMethod.PUT),
    HttpVerbTrace(FirebasePerformance.HttpMethod.TRACE);

    private final String mValue;

    HttpVerb(String str) {
        this.mValue = str;
    }

    public static HttpVerb parse(String str) {
        HttpVerb[] httpVerbArrValues = values();
        for (int i = 0; i < 8; i++) {
            HttpVerb httpVerb = httpVerbArrValues[i];
            if (httpVerb.getValue().equals(str)) {
                return httpVerb;
            }
        }
        return null;
    }

    public String getValue() {
        return this.mValue;
    }
}
