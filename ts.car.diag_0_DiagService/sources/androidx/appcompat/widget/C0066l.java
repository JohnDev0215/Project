package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;

/* JADX INFO: renamed from: androidx.appcompat.widget.l, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class C0066l extends ImageButton implements androidx.core.view.r, androidx.core.widget.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final C0058d f371c;
    private final C0067m d;

    public C0066l(@NonNull Context context) {
        this(context, null);
    }

    @Override // androidx.core.widget.l
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode b() {
        C0067m c0067m = this.d;
        if (c0067m != null) {
            return c0067m.d();
        }
        return null;
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode c() {
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            return c0058d.d();
        }
        return null;
    }

    @Override // androidx.core.widget.l
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void d(@Nullable PorterDuff.Mode mode) {
        C0067m c0067m = this.d;
        if (c0067m != null) {
            c0067m.i(mode);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            c0058d.b();
        }
        C0067m c0067m = this.d;
        if (c0067m != null) {
            c0067m.b();
        }
    }

    @Override // androidx.core.widget.l
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public ColorStateList e() {
        C0067m c0067m = this.d;
        if (c0067m != null) {
            return c0067m.c();
        }
        return null;
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public ColorStateList f() {
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            return c0058d.c();
        }
        return null;
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void h(@Nullable PorterDuff.Mode mode) {
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            c0058d.j(mode);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.d.e() && super.hasOverlappingRendering();
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void i(@Nullable ColorStateList colorStateList) {
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            c0058d.i(colorStateList);
        }
    }

    @Override // androidx.core.widget.l
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void j(@Nullable ColorStateList colorStateList) {
        C0067m c0067m = this.d;
        if (c0067m != null) {
            c0067m.h(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            c0058d.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C0058d c0058d = this.f371c;
        if (c0058d != null) {
            c0058d.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0067m c0067m = this.d;
        if (c0067m != null) {
            c0067m.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        C0067m c0067m = this.d;
        if (c0067m != null) {
            c0067m.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i) {
        this.d.g(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        C0067m c0067m = this.d;
        if (c0067m != null) {
            c0067m.b();
        }
    }

    public C0066l(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.imageButtonStyle);
    }

    public C0066l(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(Q.b(context), attributeSet, i);
        O.a(this, getContext());
        C0058d c0058d = new C0058d(this);
        this.f371c = c0058d;
        c0058d.e(attributeSet, i);
        C0067m c0067m = new C0067m(this);
        this.d = c0067m;
        c0067m.f(attributeSet, i);
    }
}
