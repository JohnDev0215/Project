package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.de;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class be implements ce {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f249a;

    public be(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f249a = be.class.getName();
        } else {
            this.f249a = str;
        }
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, String str2, String... strArr) {
        StringBuilder sb = new StringBuilder("IncrementCounter: ");
        sb.append(str);
        for (String str3 : strArr) {
            sb.append(",");
            sb.append(str3);
            sb.append("=1");
        }
        Log.i(this.f249a, sb.toString());
    }

    @Override // com.amazon.identity.auth.device.ce
    public de b(String str) {
        return new de.b();
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, String str2, long j) {
        Log.i(this.f249a, String.format("RecordTiming: %s:%s=%d", str, str2, Long.valueOf(j)));
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, String... strArr) {
        Log.i(this.f249a, str);
        if (strArr != null) {
            for (String str2 : strArr) {
                Log.i(this.f249a, str + ":" + str2);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str) {
        Log.i(this.f249a, "Increment counter : " + str);
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, double d) {
        Log.i(this.f249a, "Increment counter : " + str + ", by : " + d);
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a() {
        Log.i(this.f249a, "Recording metric event object");
    }
}
