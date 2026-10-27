package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import com.bumptech.glide.c;
import com.bumptech.glide.d;
import com.bumptech.glide.i;
import com.bumptech.glide.integration.okhttp3.b;
import java.io.InputStream;
import u1.g;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class OkHttpGlideModule implements e2.b {
    @Override // e2.b
    public void a(Context context, c cVar, i iVar) {
        iVar.r(g.class, InputStream.class, new b.a());
    }

    @Override // e2.b
    public void b(Context context, d dVar) {
    }
}
