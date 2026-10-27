package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import i.b;

/* JADX INFO: compiled from: ActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: compiled from: ActionBar.java */
    public interface b {
        void a(boolean z6);
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

    public abstract void i(boolean z6);

    public abstract int j();

    public abstract Context k();

    public boolean l() {
        return false;
    }

    public abstract void m(Configuration configuration);

    void n() {
    }

    public abstract boolean o(int i7, KeyEvent keyEvent);

    public boolean p(KeyEvent keyEvent) {
        return false;
    }

    public boolean q() {
        return false;
    }

    public abstract void r(boolean z6);

    public abstract void s(boolean z6);

    public abstract void t(CharSequence charSequence);

    public abstract i.b u(b.a aVar);

    /* JADX INFO: renamed from: androidx.appcompat.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionBar.java */
    public static class C0013a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f501a;

        public C0013a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f501a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.j.ActionBarLayout);
            this.f501a = typedArrayObtainStyledAttributes.getInt(e.j.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0013a(int i7, int i8) {
            super(i7, i8);
            this.f501a = 8388627;
        }

        public C0013a(C0013a c0013a) {
            super((ViewGroup.MarginLayoutParams) c0013a);
            this.f501a = 0;
            this.f501a = c0013a.f501a;
        }

        public C0013a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f501a = 0;
        }
    }
}
