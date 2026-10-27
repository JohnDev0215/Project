package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f498c;
    PorterDuff.Mode d;

    f(@Nullable f fVar) {
        this.f498c = null;
        this.d = d.i;
        if (fVar != null) {
            this.f496a = fVar.f496a;
            this.f497b = fVar.f497b;
            this.f498c = fVar.f498c;
            this.d = fVar.d;
        }
    }

    boolean a() {
        return this.f497b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f496a;
        Drawable.ConstantState constantState = this.f497b;
        return (constantState != null ? constantState.getChangingConfigurations() : 0) | i;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable(@Nullable Resources resources) {
        return Build.VERSION.SDK_INT >= 21 ? new e(this, resources) : new d(this, resources);
    }
}
