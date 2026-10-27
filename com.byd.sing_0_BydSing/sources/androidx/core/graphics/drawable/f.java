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
    int f1431a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1432b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1433c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1434d;

    f(f fVar) {
        this.f1433c = null;
        this.f1434d = d.f1428b;
        if (fVar != null) {
            this.f1431a = fVar.f1431a;
            this.f1432b = fVar.f1432b;
            this.f1433c = fVar.f1433c;
            this.f1434d = fVar.f1434d;
        }
    }

    boolean a() {
        return this.f1432b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1431a;
        Drawable.ConstantState constantState = this.f1432b;
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
