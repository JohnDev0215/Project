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
    int f1011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1013c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1014d;

    f(f fVar) {
        this.f1013c = null;
        this.f1014d = d.f1007b;
        if (fVar != null) {
            this.f1011a = fVar.f1011a;
            this.f1012b = fVar.f1012b;
            this.f1013c = fVar.f1013c;
            this.f1014d = fVar.f1014d;
        }
    }

    boolean a() {
        return this.f1012b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1011a;
        Drawable.ConstantState constantState = this.f1012b;
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
