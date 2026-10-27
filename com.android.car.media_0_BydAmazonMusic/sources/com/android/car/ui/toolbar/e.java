package com.android.car.ui.toolbar;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.List;
import spi.g1;

/* JADX INFO: compiled from: SearchConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {

    @Nullable
    public final View a;

    @Nullable
    public final Drawable b;

    @Nullable
    public final List<? extends g1> c;

    /* JADX INFO: compiled from: SearchConfig.java */
    public static final class b {

        @Nullable
        public View a;

        @Nullable
        public Drawable b;

        @Nullable
        public List<? extends g1> c;

        public final e a() {
            if (this.a == null || this.c == null) {
                return new e(this);
            }
            throw new RuntimeException("Both searchResultItems and searchResultsView can't be set at the same time");
        }

        private b() {
        }
    }

    public static b a() {
        return new b();
    }

    private e(b bVar) {
        this.c = bVar.c;
        this.b = bVar.b;
        this.a = bVar.a;
    }
}
