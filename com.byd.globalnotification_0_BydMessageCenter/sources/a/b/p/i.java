package a.b.p;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f276a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f277b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f278c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f279d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f280e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f281f;

    public i(CompoundButton compoundButton) {
        this.f276a = compoundButton;
    }

    public void a() {
        Drawable buttonDrawable = this.f276a.getButtonDrawable();
        if (buttonDrawable != null) {
            if (this.f279d || this.f280e) {
                Drawable drawableMutate = buttonDrawable.mutate();
                if (this.f279d) {
                    drawableMutate.setTintList(this.f277b);
                }
                if (this.f280e) {
                    drawableMutate.setTintMode(this.f278c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f276a.getDrawableState());
                }
                this.f276a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public void b(AttributeSet attributeSet, int i) {
        boolean z;
        int iL;
        int iL2;
        x0 x0VarQ = x0.q(this.f276a.getContext(), attributeSet, a.b.j.CompoundButton, i, 0);
        CompoundButton compoundButton = this.f276a;
        a.h.k.q.T(compoundButton, compoundButton.getContext(), a.b.j.CompoundButton, attributeSet, x0VarQ.f390b, i, 0);
        try {
            if (!x0VarQ.o(a.b.j.CompoundButton_buttonCompat) || (iL2 = x0VarQ.l(a.b.j.CompoundButton_buttonCompat, 0)) == 0) {
                z = false;
            } else {
                try {
                    this.f276a.setButtonDrawable(a.b.l.a.a.b(this.f276a.getContext(), iL2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z && x0VarQ.o(a.b.j.CompoundButton_android_button) && (iL = x0VarQ.l(a.b.j.CompoundButton_android_button, 0)) != 0) {
                this.f276a.setButtonDrawable(a.b.l.a.a.b(this.f276a.getContext(), iL));
            }
            if (x0VarQ.o(a.b.j.CompoundButton_buttonTint)) {
                this.f276a.setButtonTintList(x0VarQ.c(a.b.j.CompoundButton_buttonTint));
            }
            if (x0VarQ.o(a.b.j.CompoundButton_buttonTintMode)) {
                this.f276a.setButtonTintMode(e0.c(x0VarQ.j(a.b.j.CompoundButton_buttonTintMode, -1), null));
            }
            x0VarQ.f390b.recycle();
        } catch (Throwable th) {
            x0VarQ.f390b.recycle();
            throw th;
        }
    }
}
