package h0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable.ConstantState f5117b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f5118c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f5119d;

    public f(f fVar) {
        this.f5118c = null;
        this.f5119d = d.f5108d;
        if (fVar != null) {
            this.f5116a = fVar.f5116a;
            this.f5117b = fVar.f5117b;
            this.f5118c = fVar.f5118c;
            this.f5119d = fVar.f5119d;
        }
    }

    public boolean a() {
        return this.f5117b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i8 = this.f5116a;
        Drawable.ConstantState constantState = this.f5117b;
        return i8 | (constantState != null ? constantState.getChangingConfigurations() : 0);
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
