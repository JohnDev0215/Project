package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class hb implements jb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ib f371a;

    public hb(ib ibVar) {
        this.f371a = ibVar;
    }

    @Override // com.amazon.identity.auth.device.jb
    public Object a(md mdVar, byte[] bArr) throws ParseErrorException, IOException {
        this.f371a.f385a.a(mdVar);
        if (bArr != null) {
            this.f371a.f385a.a(bArr, bArr.length);
        }
        Log.i(ga.a(ib.f), "Request complete");
        this.f371a.f385a.a();
        return null;
    }

    @Override // com.amazon.identity.auth.device.jb
    public String a(HttpURLConnection httpURLConnection) {
        return null;
    }
}
