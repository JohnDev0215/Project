package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.data.a;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final a.InterfaceC0016a<?> b = new a();
    public final Map<Class<?>, a.InterfaceC0016a<?>> a = new HashMap();

    public class a implements a.InterfaceC0016a<Object> {
        @Override // com.bumptech.glide.load.data.a.InterfaceC0016a
        @NonNull
        public final Class<Object> a() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // com.bumptech.glide.load.data.a.InterfaceC0016a
        @NonNull
        public final com.bumptech.glide.load.data.a<Object> b(@NonNull Object obj) {
            return new C0017b(obj);
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.data.b$b, reason: collision with other inner class name */
    public static final class C0017b implements com.bumptech.glide.load.data.a<Object> {
        public final Object a;

        public C0017b(@NonNull Object obj) {
            this.a = obj;
        }

        @Override // com.bumptech.glide.load.data.a
        public final void b() {
        }

        @Override // com.bumptech.glide.load.data.a
        @NonNull
        public final Object c() {
            return this.a;
        }
    }
}
