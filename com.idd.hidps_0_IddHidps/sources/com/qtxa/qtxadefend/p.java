package com.qtxa.qtxadefend;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final String[] f137b = {"NIL", "OK", "BLOCK", "DELETE"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private JSONObject f138a = new JSONObject();

    p(int i) {
        b(i);
    }

    static p a() {
        return new p(0);
    }

    p b(int i) {
        try {
            this.f138a.put("code", i);
        } catch (JSONException unused) {
        }
        return this;
    }

    public String toString() {
        return this.f138a.toString();
    }
}
