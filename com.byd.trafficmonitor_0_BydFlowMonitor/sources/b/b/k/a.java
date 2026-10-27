package b.b.k;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import b.b.p.b;

/* JADX INFO: compiled from: ActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: compiled from: ActionBar.java */
    public interface b {
        void a(boolean z);
    }

    /* JADX INFO: compiled from: ActionBar.java */
    @Deprecated
    public static abstract class c {
        public abstract CharSequence a();

        public abstract View b();

        public abstract Drawable c();

        public abstract CharSequence d();

        public abstract void e();
    }

    public boolean g() {
        return false;
    }

    public abstract boolean h();

    public abstract void i(boolean z);

    public abstract int j();

    public abstract Context k();

    public boolean l() {
        return false;
    }

    public void m(Configuration configuration) {
    }

    public void n() {
    }

    public abstract boolean o(int i2, KeyEvent keyEvent);

    public boolean p(KeyEvent keyEvent) {
        return false;
    }

    public boolean q() {
        return false;
    }

    public abstract void r(boolean z);

    public abstract void s(boolean z);

    public abstract void t(CharSequence charSequence);

    public b.b.p.b u(b.a aVar) {
        return null;
    }

    /* JADX INFO: renamed from: b.b.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionBar.java */
    public static class C0014a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1426a;

        public C0014a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1426a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.b.j.ActionBarLayout);
            this.f1426a = typedArrayObtainStyledAttributes.getInt(b.b.j.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0014a(int i2, int i3) {
            super(i2, i3);
            this.f1426a = 0;
            this.f1426a = 8388627;
        }

        public C0014a(C0014a c0014a) {
            super((ViewGroup.MarginLayoutParams) c0014a);
            this.f1426a = 0;
            this.f1426a = c0014a.f1426a;
        }

        public C0014a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1426a = 0;
        }
    }
}
