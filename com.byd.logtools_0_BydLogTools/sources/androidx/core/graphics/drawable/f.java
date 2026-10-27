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
    int f1359a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1361c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1362d;

    f(f fVar) {
        this.f1361c = null;
        this.f1362d = d.f1352b;
        if (fVar != null) {
            this.f1359a = fVar.f1359a;
            this.f1360b = fVar.f1360b;
            this.f1361c = fVar.f1361c;
            this.f1362d = fVar.f1362d;
        }
    }

    boolean a() {
        return this.f1360b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1359a;
        Drawable.ConstantState constantState = this.f1360b;
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
