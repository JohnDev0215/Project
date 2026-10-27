package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.Objects;

/* JADX INFO: compiled from: SuggestionDrawableState.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public final Drawable a;
    public final boolean b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: compiled from: SuggestionDrawableState.java */
    public static final class a {
        public final Drawable a;
        public boolean b;
        public boolean c;
        public boolean d;

        public a(Drawable drawable) {
            this.a = drawable;
        }

        public static a b(Context context, Bitmap bitmap) {
            return new a(new BitmapDrawable(context.getResources(), bitmap));
        }

        public static a c(Context context, int i) {
            return new a(AppCompatResources.a(context, i));
        }

        public final e a() {
            return new e(this.a, this.c, this.d, this.b);
        }
    }

    public e(Drawable drawable, boolean z, boolean z2, boolean z3) {
        this.a = drawable;
        this.c = z;
        this.d = z2;
        this.b = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.d == eVar.d && this.c == eVar.c && this.b == eVar.b && Objects.equals(this.a, eVar.a);
    }
}
