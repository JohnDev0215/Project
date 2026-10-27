package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import b.b.q.b0;
import b.b.q.d;
import b.b.q.h;
import b.b.q.z;
import b.h.m.s;
import b.h.n.l;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView implements s, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h f414c;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f413b;
        if (dVar != null) {
            dVar.b();
        }
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.h.m.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f413b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.h.m.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f413b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // b.h.n.l
    public ColorStateList getSupportImageTintList() {
        h hVar = this.f414c;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // b.h.n.l
    public PorterDuff.Mode getSupportImageTintMode() {
        h hVar = this.f414c;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f414c.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f413b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f413b;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.g(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.h.m.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f413b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.h.m.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f413b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.h.n.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.h(colorStateList);
        }
    }

    @Override // b.h.n.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h hVar = this.f414c;
        if (hVar != null) {
            hVar.i(mode);
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i2) {
        super(b0.b(context), attributeSet, i2);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f413b = dVar;
        dVar.e(attributeSet, i2);
        h hVar = new h(this);
        this.f414c = hVar;
        hVar.f(attributeSet, i2);
    }
}
