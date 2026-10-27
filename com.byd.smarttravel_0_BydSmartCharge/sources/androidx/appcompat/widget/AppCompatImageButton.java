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
import b.b.f.b0;
import b.b.f.d;
import b.b.f.h;
import b.b.f.z;
import b.g.j.s;
import b.g.k.l;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton implements s, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f184b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h f185c;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f184b;
        if (dVar != null) {
            dVar.b();
        }
        h hVar = this.f185c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.g.j.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f184b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.j.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f184b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // b.g.k.l
    public ColorStateList getSupportImageTintList() {
        h hVar = this.f185c;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // b.g.k.l
    public PorterDuff.Mode getSupportImageTintMode() {
        h hVar = this.f185c;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f185c.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f184b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f184b;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h hVar = this.f185c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h hVar = this.f185c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.f185c.g(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h hVar = this.f185c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f184b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f184b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.g.k.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        h hVar = this.f185c;
        if (hVar != null) {
            hVar.h(colorStateList);
        }
    }

    @Override // b.g.k.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h hVar = this.f185c;
        if (hVar != null) {
            hVar.i(mode);
        }
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f184b = dVar;
        dVar.e(attributeSet, i);
        h hVar = new h(this);
        this.f185c = hVar;
        hVar.f(attributeSet, i);
    }
}
