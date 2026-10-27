package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import androidx.appcompat.R$attr;
import o0.s;
import r.b0;
import r.d;
import r.h;
import r.z;
import r0.l;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton implements s, l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f558d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f559e;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f558d;
        if (dVar != null) {
            dVar.b();
        }
        h hVar = this.f559e;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // o0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f558d;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f558d;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // r0.l
    public ColorStateList getSupportImageTintList() {
        h hVar = this.f559e;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // r0.l
    public PorterDuff.Mode getSupportImageTintMode() {
        h hVar = this.f559e;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f559e.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f558d;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        d dVar = this.f558d;
        if (dVar != null) {
            dVar.g(i8);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h hVar = this.f559e;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h hVar = this.f559e;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i8) {
        this.f559e.g(i8);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h hVar = this.f559e;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f558d;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f558d;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // r0.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        h hVar = this.f559e;
        if (hVar != null) {
            hVar.h(colorStateList);
        }
    }

    @Override // r0.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h hVar = this.f559e;
        if (hVar != null) {
            hVar.i(mode);
        }
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i8) {
        super(b0.b(context), attributeSet, i8);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f558d = dVar;
        dVar.e(attributeSet, i8);
        h hVar = new h(this);
        this.f559e = hVar;
        hVar.f(attributeSet, i8);
    }
}
