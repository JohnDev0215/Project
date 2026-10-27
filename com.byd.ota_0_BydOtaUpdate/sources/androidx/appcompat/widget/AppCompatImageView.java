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
import b.f.m.r;
import b.f.n.l;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView implements r, l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f199d;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f198c;
        if (dVar != null) {
            dVar.b();
        }
        h hVar = this.f199d;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.f.m.r
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f198c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.f.m.r
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f198c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // b.f.n.l
    public ColorStateList getSupportImageTintList() {
        h hVar = this.f199d;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // b.f.n.l
    public PorterDuff.Mode getSupportImageTintMode() {
        h hVar = this.f199d;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f199d.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f198c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f198c;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h hVar = this.f199d;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h hVar = this.f199d;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        h hVar = this.f199d;
        if (hVar != null) {
            hVar.g(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h hVar = this.f199d;
        if (hVar != null) {
            hVar.b();
        }
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f198c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f198c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.f.n.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        h hVar = this.f199d;
        if (hVar != null) {
            hVar.h(colorStateList);
        }
    }

    @Override // b.f.n.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h hVar = this.f199d;
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
        this.f198c = dVar;
        dVar.e(attributeSet, i2);
        h hVar = new h(this);
        this.f199d = hVar;
        hVar.f(attributeSet, i2);
    }
}
