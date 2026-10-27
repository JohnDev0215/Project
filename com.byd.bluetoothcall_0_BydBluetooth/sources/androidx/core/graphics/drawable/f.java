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
    int f1263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1264b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1265c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1266d;

    f(f fVar) {
        this.f1265c = null;
        this.f1266d = d.f1259b;
        if (fVar != null) {
            this.f1263a = fVar.f1263a;
            this.f1264b = fVar.f1264b;
            this.f1265c = fVar.f1265c;
            this.f1266d = fVar.f1266d;
        }
    }

    boolean a() {
        return this.f1264b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1263a;
        Drawable.ConstantState constantState = this.f1264b;
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
