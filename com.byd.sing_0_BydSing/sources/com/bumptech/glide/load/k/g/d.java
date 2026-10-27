package com.bumptech.glide.load.k.g;

import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.s;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: GifDrawableEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements com.bumptech.glide.load.g<c> {
    @Override // com.bumptech.glide.load.g
    public EncodeStrategy b(com.bumptech.glide.load.e eVar) {
        return EncodeStrategy.SOURCE;
    }

    @Override // com.bumptech.glide.load.a
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(s<c> sVar, File file, com.bumptech.glide.load.e eVar) throws Throwable {
        try {
            com.bumptech.glide.m.a.e(sVar.get().c(), file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("GifEncoder", 5)) {
                Log.w("GifEncoder", "Failed to encode GIF drawable data", e);
            }
            return false;
        }
    }
}
