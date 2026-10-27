package com.byd.cleardata.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import com.bumptech.glide.i;
import com.bumptech.glide.load.n.a0.k;
import com.bumptech.glide.load.n.b0.a;
import com.bumptech.glide.load.n.b0.g;
import com.byd.cleardata.glide.e;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class VideoGlideModule extends com.bumptech.glide.p.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2658a;

    class a implements a.InterfaceC0067a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ File f2659a;

        a(File file) {
            this.f2659a = file;
        }

        @Override // com.bumptech.glide.load.n.b0.a.InterfaceC0067a
        public com.bumptech.glide.load.n.b0.a a() {
            return com.bumptech.glide.load.n.b0.e.c(this.f2659a, 104857600L);
        }
    }

    @Override // com.bumptech.glide.p.c
    public void a(Context context, com.bumptech.glide.c cVar, i iVar) {
        super.a(context, cVar, iVar);
        com.byd.cleardata.i.c.b("registerComponents");
        iVar.o(f.class, Bitmap.class, new e.a());
    }

    @Override // com.bumptech.glide.p.a
    @SuppressLint({"CheckResult"})
    public void b(Context context, com.bumptech.glide.d dVar) {
        super.b(context, dVar);
        com.byd.cleardata.i.c.b("applyOptions");
        dVar.b(new k(5242880L));
        dVar.h(new g(5242880L));
        this.f2658a = Environment.getExternalStorageDirectory() + "/glideCatch";
        dVar.e(new a(new File(this.f2658a)));
        dVar.j(com.bumptech.glide.load.n.c0.a.g());
        dVar.f(com.bumptech.glide.load.n.c0.a.e());
        dVar.g(2);
        com.bumptech.glide.r.f fVar = new com.bumptech.glide.r.f();
        fVar.g(com.bumptech.glide.load.b.PREFER_RGB_565);
        dVar.d(fVar);
    }

    @Override // com.bumptech.glide.p.a
    public boolean c() {
        return false;
    }
}
