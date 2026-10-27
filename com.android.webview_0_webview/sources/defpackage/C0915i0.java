package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: renamed from: i0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0915i0 extends ImageView {
    public final W s;
    public final C0859h0 t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0915i0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        I0.a(context);
        W w = new W(this);
        this.s = w;
        w.b(attributeSet, 0);
        C0859h0 c0859h0 = new C0859h0(this);
        this.t = c0859h0;
        c0859h0.b(attributeSet, 0);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        W w = this.s;
        if (w != null) {
            w.a();
        }
        C0859h0 c0859h0 = this.t;
        if (c0859h0 != null) {
            c0859h0.a();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return ((this.t.a.getBackground() instanceof RippleDrawable) ^ true) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        W w = this.s;
        if (w != null) {
            w.c();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        W w = this.s;
        if (w != null) {
            w.d(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0859h0 c0859h0 = this.t;
        if (c0859h0 != null) {
            c0859h0.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        C0859h0 c0859h0 = this.t;
        if (c0859h0 != null) {
            c0859h0.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        C0859h0 c0859h0 = this.t;
        if (c0859h0 != null) {
            c0859h0.c(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C0859h0 c0859h0 = this.t;
        if (c0859h0 != null) {
            c0859h0.a();
        }
    }
}
