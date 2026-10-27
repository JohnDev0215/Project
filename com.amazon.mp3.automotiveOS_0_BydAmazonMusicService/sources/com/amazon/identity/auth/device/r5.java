package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.net.Uri;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class r5 implements v4<Uri> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f527a;
    public final /* synthetic */ ContentValues b;
    public final /* synthetic */ s5 c;

    public r5(s5 s5Var, Uri uri, ContentValues contentValues) {
        this.c = s5Var;
        this.f527a = uri;
        this.b = contentValues;
    }

    @Override // com.amazon.identity.auth.device.v4
    public Uri a(ContentProviderClient contentProviderClient) throws Exception {
        return this.c.b.insert(this.f527a, this.b);
    }
}
