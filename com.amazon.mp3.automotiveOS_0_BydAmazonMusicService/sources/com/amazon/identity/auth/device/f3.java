package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class f3 extends d3 {
    public final a e;

    public f3(String str, String str2) {
        this.e = new a(str, str2);
    }

    @Override // com.amazon.identity.auth.device.ac
    public a a() {
        return this.e;
    }

    @Override // com.amazon.identity.auth.device.d3
    public boolean a(Context context) {
        return TextUtils.isEmpty(this.e.f165a) || TextUtils.isEmpty(this.e.b);
    }
}
