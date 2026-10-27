package com.android.car.ui.toolbar;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.function.Consumer;

/* JADX INFO: compiled from: Tab.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {

    @Nullable
    public final String a;

    @Nullable
    public final Drawable b;

    @Nullable
    public final Consumer<g> c;
    public final boolean d;

    public static b a() {
        return new b();
    }

    /* JADX INFO: compiled from: Tab.java */
    public static class b {

        @Nullable
        public String a;

        @Nullable
        public Drawable b;

        @Nullable
        public Consumer<g> c;
        public boolean d;

        public final g a() {
            return new g(this);
        }

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = true;
        }

        private b(g gVar) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = true;
            this.a = gVar.a;
            this.b = gVar.b;
            this.c = gVar.c;
            this.d = gVar.d;
        }
    }

    private g(@NonNull b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
    }
}
