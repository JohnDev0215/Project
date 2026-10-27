package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import com.amazonaws.http.HttpHeader;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class qc {
    public static final String c = "com.amazon.identity.auth.device.qc";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f517a;
    public ld b;

    public ld a() {
        if (this.f517a == null) {
            Log.e(ga.a(c), "getWebRequest: Cannot construct a WebRequest because the RenameDeviceRequest is invalid. (See previous warnings from RenameDeviceRequest::isValidDeviceName for details.)");
            return null;
        }
        ld ldVar = this.b;
        if (ldVar != null) {
            return ldVar;
        }
        ld ldVar2 = new ld();
        this.b = ldVar2;
        ldVar2.a(WebProtocol.WebProtocolHttps);
        this.b.d = EnvironmentUtils.c.g();
        this.b.c("/FirsProxy/renameFiona");
        ld ldVar3 = this.b;
        ldVar3.h = HttpVerb.HttpVerbGet;
        ldVar3.a("nickname", this.f517a);
        this.b.b(HttpHeader.CONTENT_TYPE, "text/xml");
        this.b.j = true;
        Log.i(ga.a(c), "getWebRequest: getWebRequest: constructed a web request.");
        String.format("Device new name: %s", this.f517a);
        return this.b;
    }
}
