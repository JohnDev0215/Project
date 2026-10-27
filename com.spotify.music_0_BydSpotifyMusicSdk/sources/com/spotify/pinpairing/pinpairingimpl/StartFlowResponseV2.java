package com.spotify.pinpairing.pinpairingimpl;

import java.io.InvalidObjectException;
import okhttp3.ResponseBody;
import p.qb2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class StartFlowResponseV2 {

    @qb2("device_code")
    private String a;

    @qb2("user_code")
    private String b;

    @qb2("expires_in")
    private int c;

    @qb2("verification_uri")
    private String d;

    @qb2("verification_uri_complete")
    private String e;

    @qb2("interval")
    private int f;

    public static StartFlowResponseV2 f(com.google.gson.a aVar, ResponseBody responseBody) throws InvalidObjectException {
        if (responseBody == null) {
            throw new InvalidObjectException("Response is null.");
        }
        String strQ = responseBody.q();
        if (v03.y(strQ)) {
            throw new InvalidObjectException("Response is empty.");
        }
        StartFlowResponseV2 startFlowResponseV2 = (StartFlowResponseV2) aVar.b(StartFlowResponseV2.class, strQ);
        if (v03.y(startFlowResponseV2.a)) {
            throw new InvalidObjectException("deviceCode is not present.");
        }
        if (v03.y(startFlowResponseV2.b)) {
            throw new InvalidObjectException("userCode is not present.");
        }
        if (v03.y(startFlowResponseV2.d)) {
            throw new InvalidObjectException("mVerificationUri is not present.");
        }
        if (v03.y(startFlowResponseV2.e)) {
            throw new InvalidObjectException("mVerificationUriComplete is not present.");
        }
        return startFlowResponseV2;
    }

    public final String a() {
        return this.a;
    }

    public final int b() {
        return this.f;
    }

    public final String c() {
        return this.b;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }
}
