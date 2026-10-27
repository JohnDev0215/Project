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
import l0.s;
import n0.l;
import o.b0;
import o.d;
import o.h;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton implements s, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f457b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h f458c;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f457b;
        if (dVar != null) {
            dVar.b();
        }
        h hVar = this.f458c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f457b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f457b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // n0.l
    public ColorStateList getSupportImageTintList() {
        h hVar = this.f458c;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // n0.l
    public PorterDuff.Mode getSupportImageTintMode() {
        h hVar = this.f458c;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f458c.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f457b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        d dVar = this.f457b;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h hVar = this.f458c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h hVar = this.f458c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i3) {
        this.f458c.g(i3);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h hVar = this.f458c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f457b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f457b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // n0.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        h hVar = this.f458c;
        if (hVar != null) {
            hVar.h(colorStateList);
        }
    }

    @Override // n0.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h hVar = this.f458c;
        if (hVar != null) {
            hVar.i(mode);
        }
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f457b = dVar;
        dVar.e(attributeSet, i3);
        h hVar = new h(this);
        this.f458c = hVar;
        hVar.f(attributeSet, i3);
    }
}
