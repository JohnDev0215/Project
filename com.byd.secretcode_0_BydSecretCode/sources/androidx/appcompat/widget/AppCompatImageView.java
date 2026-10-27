package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import defpackage.b3;
import defpackage.d3;
import defpackage.f2;
import defpackage.j2;
import defpackage.s9;
import defpackage.ta;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView implements s9, ta {
    public final f2 b;
    public final j2 c;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.b();
        }
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.b();
        }
    }

    @Override // defpackage.s9
    public ColorStateList getSupportBackgroundTintList() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.c();
        }
        return null;
    }

    @Override // defpackage.s9
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.d();
        }
        return null;
    }

    @Override // defpackage.ta
    public ColorStateList getSupportImageTintList() {
        j2 j2Var = this.c;
        if (j2Var != null) {
            return j2Var.c();
        }
        return null;
    }

    @Override // defpackage.ta
    public PorterDuff.Mode getSupportImageTintMode() {
        j2 j2Var = this.c;
        if (j2Var != null) {
            return j2Var.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.c.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.b();
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.i(colorStateList);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.j(mode);
        }
    }

    @Override // defpackage.ta
    public void setSupportImageTintList(ColorStateList colorStateList) {
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.h(colorStateList);
        }
    }

    @Override // defpackage.ta
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        j2 j2Var = this.c;
        if (j2Var != null) {
            j2Var.i(mode);
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        f2 f2Var = new f2(this);
        this.b = f2Var;
        f2Var.e(attributeSet, i);
        j2 j2Var = new j2(this);
        this.c = j2Var;
        j2Var.f(attributeSet, i);
    }
}
