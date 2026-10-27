package m1;

import android.util.Log;
import c6.b0;
import c6.c0;
import c6.e;
import c6.f;
import c6.z;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import k2.c;
import k2.j;
import o1.d;
import u1.g;

/* JADX INFO: compiled from: OkHttpStreamFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements d<InputStream>, f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e.a f8068e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final g f8069g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private InputStream f8070h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private c0 f8071i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private d.a<? super InputStream> f8072j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private volatile e f8073k;

    public a(e.a aVar, g gVar) {
        this.f8068e = aVar;
        this.f8069g = gVar;
    }

    @Override // o1.d
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // o1.d
    public void b() {
        try {
            InputStream inputStream = this.f8070h;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException unused) {
        }
        c0 c0Var = this.f8071i;
        if (c0Var != null) {
            c0Var.close();
        }
        this.f8072j = null;
    }

    @Override // o1.d
    public void c(com.bumptech.glide.g gVar, d.a<? super InputStream> aVar) {
        z.a aVarH = new z.a().h(this.f8069g.h());
        for (Map.Entry<String, String> entry : this.f8069g.e().entrySet()) {
            aVarH.a(entry.getKey(), entry.getValue());
        }
        z zVarB = aVarH.b();
        this.f8072j = aVar;
        this.f8073k = this.f8068e.a(zVarB);
        this.f8073k.m(this);
    }

    @Override // o1.d
    public void cancel() {
        e eVar = this.f8073k;
        if (eVar != null) {
            eVar.cancel();
        }
    }

    @Override // c6.f
    public void d(e eVar, IOException iOException) {
        if (Log.isLoggable("OkHttpFetcher", 3)) {
            Log.d("OkHttpFetcher", "OkHttp failed to obtain result", iOException);
        }
        this.f8072j.d(iOException);
    }

    @Override // o1.d
    public n1.a e() {
        return n1.a.REMOTE;
    }

    @Override // c6.f
    public void f(e eVar, b0 b0Var) {
        this.f8071i = b0Var.m();
        if (!b0Var.Z()) {
            this.f8072j.d(new n1.e(b0Var.a0(), b0Var.I()));
            return;
        }
        InputStream inputStreamZ = c.z(this.f8071i.m(), ((c0) j.d(this.f8071i)).z());
        this.f8070h = inputStreamZ;
        this.f8072j.f(inputStreamZ);
    }
}
