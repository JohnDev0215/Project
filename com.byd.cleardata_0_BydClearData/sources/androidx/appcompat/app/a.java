package androidx.appcompat.app;

import a.a.n.b;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: androidx.appcompat.app.a$a, reason: collision with other inner class name */
    public static class C0029a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f710a;

        public C0029a(int i, int i2) {
            super(i, i2);
            this.f710a = 0;
            this.f710a = 8388627;
        }

        public C0029a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f710a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.t);
            this.f710a = typedArrayObtainStyledAttributes.getInt(a.a.j.u, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0029a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f710a = 0;
        }

        public C0029a(C0029a c0029a) {
            super((ViewGroup.MarginLayoutParams) c0029a);
            this.f710a = 0;
            this.f710a = c0029a.f710a;
        }
    }

    public interface b {
        void a(boolean z);
    }

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

    public abstract void m(Configuration configuration);

    void n() {
    }

    public abstract boolean o(int i, KeyEvent keyEvent);

    public boolean p(KeyEvent keyEvent) {
        return false;
    }

    public boolean q() {
        return false;
    }

    public abstract void r(boolean z);

    public abstract void s(boolean z);

    public abstract void t(CharSequence charSequence);

    public abstract a.a.n.b u(b.a aVar);
}
