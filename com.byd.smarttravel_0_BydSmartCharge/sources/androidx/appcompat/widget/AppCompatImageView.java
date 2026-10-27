package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import b.b.f.b0;
import b.b.f.d;
import b.b.f.h;
import b.b.f.z;
import b.g.j.s;
import b.g.k.l;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView implements s, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f186b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h f187c;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f186b;
        if (dVar != null) {
            dVar.b();
        }
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.g.j.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f186b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.j.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f186b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // b.g.k.l
    public ColorStateList getSupportImageTintList() {
        h hVar = this.f187c;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // b.g.k.l
    public PorterDuff.Mode getSupportImageTintMode() {
        h hVar = this.f187c;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f187c.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f186b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f186b;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f186b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f186b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.g.k.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.h(colorStateList);
        }
    }

    @Override // b.g.k.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h hVar = this.f187c;
        if (hVar != null) {
            hVar.i(mode);
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f186b = dVar;
        dVar.e(attributeSet, i);
        h hVar = new h(this);
        this.f187c = hVar;
        hVar.f(attributeSet, i);
    }
}
