package e0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2797a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable.ConstantState f2798b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f2799c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f2800d;

    public f(f fVar) {
        this.f2799c = null;
        this.f2800d = d.f2789b;
        if (fVar != null) {
            this.f2797a = fVar.f2797a;
            this.f2798b = fVar.f2798b;
            this.f2799c = fVar.f2799c;
            this.f2800d = fVar.f2800d;
        }
    }

    public boolean a() {
        return this.f2798b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i3 = this.f2797a;
        Drawable.ConstantState constantState = this.f2798b;
        return i3 | (constantState != null ? constantState.getChangingConfigurations() : 0);
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
