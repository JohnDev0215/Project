package a.b.k;

import a.b.o.a;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f10a;

        public a(int i, int i2) {
            super(i, i2);
            this.f10a = 0;
            this.f10a = 8388627;
        }

        public a(a aVar) {
            super((ViewGroup.MarginLayoutParams) aVar);
            this.f10a = 0;
            this.f10a = aVar.f10a;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f10a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b.j.ActionBarLayout);
            this.f10a = typedArrayObtainStyledAttributes.getInt(a.b.j.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f10a = 0;
        }
    }

    public interface b {
        void a(boolean z);
    }

    @Deprecated
    public static abstract class c {
        public abstract void a();
    }

    public abstract boolean a();

    public abstract void b(boolean z);

    public abstract int c();

    public abstract Context d();

    public abstract void e(Configuration configuration);

    public abstract boolean f(int i, KeyEvent keyEvent);

    public abstract void g(boolean z);

    public abstract void h(boolean z);

    public abstract void i(CharSequence charSequence);

    public abstract a.b.o.a j(a.InterfaceC0001a interfaceC0001a);
}
