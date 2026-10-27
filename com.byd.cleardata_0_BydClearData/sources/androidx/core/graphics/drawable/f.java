package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1232c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1233d;

    f(f fVar) {
        this.f1232c = null;
        this.f1233d = d.f1227b;
        if (fVar != null) {
            this.f1230a = fVar.f1230a;
            this.f1231b = fVar.f1231b;
            this.f1232c = fVar.f1232c;
            this.f1233d = fVar.f1233d;
        }
    }

    boolean a() {
        return this.f1231b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1230a;
        Drawable.ConstantState constantState = this.f1231b;
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
