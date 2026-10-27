package b.g.c.l;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1004a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable.ConstantState f1005b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f1006c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f1007d;

    public f(f fVar) {
        this.f1006c = null;
        this.f1007d = d.f1001b;
        if (fVar != null) {
            this.f1004a = fVar.f1004a;
            this.f1005b = fVar.f1005b;
            this.f1006c = fVar.f1006c;
            this.f1007d = fVar.f1007d;
        }
    }

    public boolean a() {
        return this.f1005b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1004a;
        Drawable.ConstantState constantState = this.f1005b;
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
