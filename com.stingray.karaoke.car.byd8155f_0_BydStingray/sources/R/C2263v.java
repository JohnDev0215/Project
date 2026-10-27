package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.widget.ImageView;

/* JADX INFO: renamed from: r.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2263v extends ImageView {
    public final i2.q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B5.u f21564m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21565n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2263v(Context context, int i7) {
        super(context, null, i7);
        r0.a(context);
        this.f21565n = false;
        q0.a(this, getContext());
        i2.q qVar = new i2.q(this);
        this.l = qVar;
        qVar.f(null, i7);
        B5.u uVar = new B5.u(this);
        this.f21564m = uVar;
        uVar.q(null, i7);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.b();
        }
        B5.u uVar = this.f21564m;
        if (uVar != null) {
            uVar.f();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.d();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.e();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        c9.m mVar;
        B5.u uVar = this.f21564m;
        if (uVar == null || (mVar = (c9.m) uVar.o) == null) {
            return null;
        }
        return (ColorStateList) mVar.c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        c9.m mVar;
        B5.u uVar = this.f21564m;
        if (uVar == null || (mVar = (c9.m) uVar.o) == null) {
            return null;
        }
        return (PorterDuff.Mode) mVar.d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return !(((ImageView) this.f21564m.f736n).getBackground() instanceof RippleDrawable) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.g();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.h(i7);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        B5.u uVar = this.f21564m;
        if (uVar != null) {
            uVar.f();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        B5.u uVar = this.f21564m;
        if (uVar != null && drawable != null && !this.f21565n) {
            uVar.f735m = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (uVar != null) {
            uVar.f();
            if (this.f21565n) {
                return;
            }
            ImageView imageView = (ImageView) uVar.f736n;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(uVar.f735m);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i7) {
        super.setImageLevel(i7);
        this.f21565n = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i7) {
        B5.u uVar = this.f21564m;
        if (uVar != null) {
            ImageView imageView = (ImageView) uVar.f736n;
            if (i7 != 0) {
                Drawable drawableK = h6.f.k(imageView.getContext(), i7);
                if (drawableK != null) {
                    AbstractC2225M.a(drawableK);
                }
                imageView.setImageDrawable(drawableK);
            } else {
                imageView.setImageDrawable(null);
            }
            uVar.f();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        B5.u uVar = this.f21564m;
        if (uVar != null) {
            uVar.f();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.j(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.k(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        B5.u uVar = this.f21564m;
        if (uVar != null) {
            if (((c9.m) uVar.o) == null) {
                uVar.o = new c9.m();
            }
            c9.m mVar = (c9.m) uVar.o;
            mVar.c = colorStateList;
            mVar.f12588b = true;
            uVar.f();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        B5.u uVar = this.f21564m;
        if (uVar != null) {
            if (((c9.m) uVar.o) == null) {
                uVar.o = new c9.m();
            }
            c9.m mVar = (c9.m) uVar.o;
            mVar.d = mode;
            mVar.f12587a = true;
            uVar.f();
        }
    }
}
