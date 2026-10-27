package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.resource.bitmap.a;
import com.bumptech.glide.load.resource.bitmap.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import spi.bn;
import spi.ci;
import spi.dm;
import spi.lk;
import spi.m0;
import spi.w1;
import spi.w8;
import spi.wm;

/* JADX INFO: loaded from: classes.dex */
public final class c implements bn<InputStream, Bitmap> {
    public final com.bumptech.glide.load.resource.bitmap.a a;
    public final m0 b;

    public static class a implements a.b {
        public final dm a;
        public final w8 b;

        public a(dm dmVar, w8 w8Var) {
            this.a = dmVar;
            this.b = w8Var;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.a.b
        public final void a() {
            dm dmVar = this.a;
            synchronized (dmVar) {
                dmVar.c = dmVar.a.length;
            }
        }

        @Override // com.bumptech.glide.load.resource.bitmap.a.b
        public final void b(w1 w1Var, Bitmap bitmap) throws IOException {
            IOException iOException = this.b.b;
            if (iOException != null) {
                if (bitmap == null) {
                    throw iOException;
                }
                w1Var.c(bitmap);
                throw iOException;
            }
        }
    }

    public c(com.bumptech.glide.load.resource.bitmap.a aVar, m0 m0Var) {
        this.a = aVar;
        this.b = m0Var;
    }

    @Override // spi.bn
    public final boolean a(@NonNull InputStream inputStream, @NonNull lk lkVar) throws IOException {
        Objects.requireNonNull(this.a);
        return true;
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.ArrayDeque, java.util.Queue<spi.w8>] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.ArrayDeque, java.util.Queue<spi.w8>] */
    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull InputStream inputStream, int i, int i2, @NonNull lk lkVar) throws IOException {
        dm dmVar;
        boolean z;
        w8 w8Var;
        InputStream inputStream2 = inputStream;
        if (inputStream2 instanceof dm) {
            z = false;
            dmVar = (dm) inputStream2;
        } else {
            dmVar = new dm(inputStream2, this.b);
            z = true;
        }
        ?? r4 = w8.c;
        synchronized (r4) {
            w8Var = (w8) r4.poll();
        }
        if (w8Var == null) {
            w8Var = new w8();
        }
        w8 w8Var2 = w8Var;
        w8Var2.a = dmVar;
        ci ciVar = new ci(w8Var2);
        a aVar = new a(dmVar, w8Var2);
        try {
            com.bumptech.glide.load.resource.bitmap.a aVar2 = this.a;
            wm<Bitmap> wmVarA = aVar2.a(new b.C0019b(ciVar, aVar2.d, aVar2.c), i, i2, lkVar, aVar);
            w8Var2.b = null;
            w8Var2.a = null;
            synchronized (r4) {
                r4.offer(w8Var2);
            }
            if (z) {
                dmVar.B();
            }
            return wmVarA;
        } catch (Throwable th) {
            w8Var2.b = null;
            w8Var2.a = null;
            ?? r6 = w8.c;
            synchronized (r6) {
                r6.offer(w8Var2);
                if (z) {
                    dmVar.B();
                }
                throw th;
            }
        }
    }
}
