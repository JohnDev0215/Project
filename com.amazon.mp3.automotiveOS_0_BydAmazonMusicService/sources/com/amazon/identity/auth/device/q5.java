package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.net.Uri;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q5 implements v4<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f509a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String[] c;
    public final /* synthetic */ s5 d;

    public q5(s5 s5Var, Uri uri, String str, String[] strArr) {
        this.d = s5Var;
        this.f509a = uri;
        this.b = str;
        this.c = strArr;
    }

    @Override // com.amazon.identity.auth.device.v4
    public Integer a(ContentProviderClient contentProviderClient) throws Exception {
        return Integer.valueOf(this.d.b.delete(this.f509a, this.b, this.c));
    }
}
