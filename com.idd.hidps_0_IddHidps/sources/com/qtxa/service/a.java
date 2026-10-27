package com.qtxa.service;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected JSONObject f165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f166b;
    protected String c;

    public a(JSONObject jSONObject) {
        this.f165a = jSONObject;
        this.f166b = jSONObject.optString("module");
        this.c = this.f165a.optString("action");
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.f166b;
    }

    public a(String str) {
        this(new JSONObject(str));
    }
}
