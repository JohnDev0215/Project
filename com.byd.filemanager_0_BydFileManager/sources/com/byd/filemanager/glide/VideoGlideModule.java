package com.byd.filemanager.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.x.k;
import com.bumptech.glide.load.engine.y.a;
import com.bumptech.glide.load.engine.y.g;
import com.byd.filemanager.glide.e;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class VideoGlideModule extends com.bumptech.glide.l.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3562a;

    class a implements a.InterfaceC0065a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ File f3563a;

        a(File file) {
            this.f3563a = file;
        }

        @Override // com.bumptech.glide.load.engine.y.a.InterfaceC0065a
        public com.bumptech.glide.load.engine.y.a a() {
            return com.bumptech.glide.load.engine.y.e.c(this.f3563a, 104857600L);
        }
    }

    @Override // com.bumptech.glide.l.c
    public void a(Context context, com.bumptech.glide.c cVar, Registry registry) {
        super.a(context, cVar, registry);
        com.byd.filemanager.k.b.b("registerComponents");
        registry.o(f.class, Bitmap.class, new e.a());
    }

    @Override // com.bumptech.glide.l.a
    @SuppressLint({"CheckResult"})
    public void b(Context context, com.bumptech.glide.d dVar) {
        super.b(context, dVar);
        com.byd.filemanager.k.b.b("applyOptions");
        dVar.b(new k(5242880L));
        dVar.h(new g(5242880L));
        this.f3562a = Environment.getExternalStorageDirectory() + "/glideCatch";
        dVar.e(new a(new File(this.f3562a)));
        dVar.j(com.bumptech.glide.load.engine.z.a.g());
        dVar.f(com.bumptech.glide.load.engine.z.a.e());
        dVar.g(2);
        com.bumptech.glide.request.e eVar = new com.bumptech.glide.request.e();
        eVar.k(DecodeFormat.PREFER_RGB_565);
        dVar.d(eVar);
    }

    @Override // com.bumptech.glide.l.a
    public boolean c() {
        return false;
    }
}
