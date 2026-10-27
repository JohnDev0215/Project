package com.bumptech.glide.load.p.h;

import android.util.Log;
import com.bumptech.glide.load.l;
import com.bumptech.glide.load.n.v;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class d implements l<c> {
    @Override // com.bumptech.glide.load.l
    public com.bumptech.glide.load.c b(com.bumptech.glide.load.i iVar) {
        return com.bumptech.glide.load.c.SOURCE;
    }

    @Override // com.bumptech.glide.load.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(v<c> vVar, File file, com.bumptech.glide.load.i iVar) throws Throwable {
        try {
            com.bumptech.glide.t.a.e(vVar.b().c(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("GifEncoder", 5)) {
                Log.w("GifEncoder", "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
