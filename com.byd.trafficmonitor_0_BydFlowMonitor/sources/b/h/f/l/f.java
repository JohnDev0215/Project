package b.h.f.l;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable.ConstantState f2470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f2471c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f2472d;

    public f(f fVar) {
        this.f2471c = null;
        this.f2472d = d.f2461b;
        if (fVar != null) {
            this.f2469a = fVar.f2469a;
            this.f2470b = fVar.f2470b;
            this.f2471c = fVar.f2471c;
            this.f2472d = fVar.f2472d;
        }
    }

    public boolean a() {
        return this.f2470b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i2 = this.f2469a;
        Drawable.ConstantState constantState = this.f2470b;
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
