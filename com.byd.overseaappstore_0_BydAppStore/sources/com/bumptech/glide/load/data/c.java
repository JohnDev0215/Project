package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.data.a;
import java.io.IOException;
import java.io.InputStream;
import spi.dm;
import spi.m0;

/* JADX INFO: loaded from: classes.dex */
public final class c implements com.bumptech.glide.load.data.a<InputStream> {
    public final dm a;

    public static final class a implements a.InterfaceC0016a<InputStream> {
        public final m0 a;

        public a(m0 m0Var) {
            this.a = m0Var;
        }

        @Override // com.bumptech.glide.load.data.a.InterfaceC0016a
        @NonNull
        public final Class<InputStream> a() {
            return InputStream.class;
        }

        @Override // com.bumptech.glide.load.data.a.InterfaceC0016a
        @NonNull
        public final com.bumptech.glide.load.data.a<InputStream> b(InputStream inputStream) {
            return new c(inputStream, this.a);
        }
    }

    public c(InputStream inputStream, m0 m0Var) {
        dm dmVar = new dm(inputStream, m0Var);
        this.a = dmVar;
        dmVar.mark(5242880);
    }

    @Override // com.bumptech.glide.load.data.a
    @NonNull
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final InputStream c() throws IOException {
        this.a.reset();
        return this.a;
    }

    @Override // com.bumptech.glide.load.data.a
    public final void b() {
        this.a.B();
    }
}
