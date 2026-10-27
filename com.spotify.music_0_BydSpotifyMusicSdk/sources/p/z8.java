package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class z8 extends ImageButton {
    private final v7 c;
    private final a9 e;
    public boolean k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z8(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        wl2.a(context);
        this.k = false;
        ik2.a(this, getContext());
        v7 v7Var = new v7(this);
        this.c = v7Var;
        v7Var.d(attributeSet, i);
        a9 a9Var = new a9(this);
        this.e = a9Var;
        a9Var.b(attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.a();
        }
        a9 a9Var = this.e;
        if (a9Var != null) {
            a9Var.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        xl2 xl2Var;
        a9 a9Var = this.e;
        if (a9Var == null || (xl2Var = a9Var.b) == null) {
            return null;
        }
        return (ColorStateList) xl2Var.c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        xl2 xl2Var;
        a9 a9Var = this.e;
        if (a9Var == null || (xl2Var = a9Var.b) == null) {
            return null;
        }
        return (PorterDuff.Mode) xl2Var.d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return ((this.e.a.getBackground() instanceof RippleDrawable) ^ true) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        a9 a9Var = this.e;
        if (a9Var != null) {
            a9Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        a9 a9Var = this.e;
        if (a9Var != null && drawable != null && !this.k) {
            a9Var.c = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        a9 a9Var2 = this.e;
        if (a9Var2 != null) {
            a9Var2.a();
            if (this.k) {
                return;
            }
            a9 a9Var3 = this.e;
            ImageView imageView = a9Var3.a;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(a9Var3.c);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i) {
        super.setImageLevel(i);
        this.k = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        a9 a9Var = this.e;
        ImageView imageView = a9Var.a;
        if (i != 0) {
            Drawable drawableF = l13.f(imageView.getContext(), i);
            if (drawableF != null) {
                cb0.a(drawableF);
            }
            imageView.setImageDrawable(drawableF);
        } else {
            imageView.setImageDrawable(null);
        }
        a9Var.a();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        a9 a9Var = this.e;
        if (a9Var != null) {
            a9Var.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        a9 a9Var = this.e;
        if (a9Var != null) {
            if (a9Var.b == null) {
                a9Var.b = new xl2(0);
            }
            xl2 xl2Var = a9Var.b;
            xl2Var.c = colorStateList;
            xl2Var.b = true;
            a9Var.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        a9 a9Var = this.e;
        if (a9Var != null) {
            if (a9Var.b == null) {
                a9Var.b = new xl2(0);
            }
            xl2 xl2Var = a9Var.b;
            xl2Var.d = mode;
            xl2Var.a = true;
            a9Var.a();
        }
    }
}
