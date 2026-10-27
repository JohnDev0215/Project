package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1257c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1258d;

    f(f fVar) {
        this.f1257c = null;
        this.f1258d = d.f1247b;
        if (fVar != null) {
            this.f1255a = fVar.f1255a;
            this.f1256b = fVar.f1256b;
            this.f1257c = fVar.f1257c;
            this.f1258d = fVar.f1258d;
        }
    }

    boolean a() {
        return this.f1256b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i2 = this.f1255a;
        Drawable.ConstantState constantState = this.f1256b;
        return i2 | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return Build.VERSION.SDK_INT >= 21 ? new e(this, resources) : new d(this, resources);
    }
}
