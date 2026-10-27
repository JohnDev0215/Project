package b.f.g.l;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1667a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable.ConstantState f1668b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f1669c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f1670d;

    public f(f fVar) {
        this.f1669c = null;
        this.f1670d = d.f1665c;
        if (fVar != null) {
            this.f1667a = fVar.f1667a;
            this.f1668b = fVar.f1668b;
            this.f1669c = fVar.f1669c;
            this.f1670d = fVar.f1670d;
        }
    }

    public boolean a() {
        return this.f1668b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i2 = this.f1667a;
        Drawable.ConstantState constantState = this.f1668b;
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
