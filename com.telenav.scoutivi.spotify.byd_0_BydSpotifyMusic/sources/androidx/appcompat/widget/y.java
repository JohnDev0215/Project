package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: compiled from: AppCompatToggleButton.java */
/* JADX INFO: loaded from: classes.dex */
public class y extends ToggleButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f1258e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final v f1259g;

    public y(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1258e;
        if (dVar != null) {
            dVar.b();
        }
        v vVar = this.f1259g;
        if (vVar != null) {
            vVar.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1258e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1258e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.ToggleButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1258e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        d dVar = this.f1258e;
        if (dVar != null) {
            dVar.g(i7);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1258e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1258e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    public y(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        n0.a(this, getContext());
        d dVar = new d(this);
        this.f1258e = dVar;
        dVar.e(attributeSet, i7);
        v vVar = new v(this);
        this.f1259g = vVar;
        vVar.m(attributeSet, i7);
    }
}
