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
    int f908a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f909b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f910c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f911d;

    f(f fVar) {
        this.f910c = null;
        this.f911d = d.f905b;
        if (fVar != null) {
            this.f908a = fVar.f908a;
            this.f909b = fVar.f909b;
            this.f910c = fVar.f910c;
            this.f911d = fVar.f911d;
        }
    }

    boolean a() {
        return this.f909b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f908a;
        Drawable.ConstantState constantState = this.f909b;
        return (constantState != null ? constantState.getChangingConfigurations() : 0) | i;
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
