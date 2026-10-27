package com.android.car.ui.rotary;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import spi.o4;

/* JADX INFO: compiled from: RotaryCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    @NonNull
    public final c a;

    @NonNull
    public final C0012a b;

    /* JADX INFO: renamed from: com.android.car.ui.rotary.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: RotaryCache.java */
    public static class C0012a extends HashMap<Integer, b> {
        public final int a;
        public final int b;

        public C0012a(int i, int i2) {
            this.a = i;
            this.b = i2;
            if (i == 2 && i2 <= 0) {
                throw new IllegalArgumentException("Expiration time must be positive if CacheType is CACHE_TYPE_EXPIRED_AFTER_SOME_TIME");
            }
        }
    }

    /* JADX INFO: compiled from: RotaryCache.java */
    public static class b {

        @NonNull
        public final o4 a;
        public final long b;

        public b(@NonNull o4 o4Var, long j) {
            this.a = o4Var;
            this.b = j;
        }
    }

    /* JADX INFO: compiled from: RotaryCache.java */
    public static class c {
        public final int a;
        public long b;

        @Nullable
        public d c;

        public c(int i, long j) {
            this.a = i;
            this.b = j;
            if (i == 2 && j <= 0) {
                throw new IllegalArgumentException("Expiration time must be positive if CacheType is CACHE_TYPE_EXPIRED_AFTER_SOME_TIME");
            }
        }

        public final View a(long j) {
            int i;
            d dVar = this.c;
            if (dVar == null) {
                return null;
            }
            boolean z = false;
            if (dVar != null && ((i = this.a) == 2 ? j < dVar.b + this.b : i == 3)) {
                z = true;
            }
            if (z) {
                return dVar.a;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: RotaryCache.java */
    public static class d {

        @NonNull
        public final View a;
        public final long b;

        public d(@NonNull View view, long j) {
            this.a = view;
            this.b = j;
        }
    }

    public a(int i, int i2, int i3, int i4) {
        this.a = new c(i, i2);
        this.b = new C0012a(i3, i4);
    }

    @Nullable
    public final o4 a(int i, long j) {
        int i2;
        C0012a c0012a = this.b;
        b bVar = c0012a.get(Integer.valueOf(i));
        boolean z = false;
        if (bVar != null && ((i2 = c0012a.a) == 2 ? j - bVar.b < c0012a.b : i2 == 3)) {
            z = true;
        }
        if (z) {
            return bVar.a;
        }
        return null;
    }
}
