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
    int f882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f884c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f885d;

    f(f fVar) {
        this.f884c = null;
        this.f885d = d.f879b;
        if (fVar != null) {
            this.f882a = fVar.f882a;
            this.f883b = fVar.f883b;
            this.f884c = fVar.f884c;
            this.f885d = fVar.f885d;
        }
    }

    boolean a() {
        return this.f883b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f882a;
        Drawable.ConstantState constantState = this.f883b;
        return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
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
