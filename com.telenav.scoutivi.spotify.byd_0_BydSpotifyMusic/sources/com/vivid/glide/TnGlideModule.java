package com.vivid.glide;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import com.bumptech.glide.c;
import com.bumptech.glide.d;
import com.bumptech.glide.i;
import com.bumptech.glide.integration.okhttp3.b;
import e2.a;
import java.io.InputStream;
import m2.g;
import n3.b;
import r1.j;
import s1.i;

/* JADX INFO: loaded from: classes.dex */
public class TnGlideModule extends a {
    @Override // e2.c
    public void a(Context context, c cVar, i iVar) {
        super.a(context, cVar, iVar);
        iVar.p(g.class, PictureDrawable.class, new b()).a(InputStream.class, g.class, new n3.a());
        iVar.r(u1.g.class, InputStream.class, new b.a(r3.a.a().b()));
    }

    @Override // e2.a
    public void b(Context context, d dVar) {
        super.b(context, dVar);
        s1.i iVarA = new i.a(context).c(1.0f).b(2.0f).a();
        dVar.c(new s1.g(iVarA.d()));
        dVar.b(new j(iVarA.b()));
    }

    @Override // e2.a
    public boolean c() {
        return false;
    }
}
