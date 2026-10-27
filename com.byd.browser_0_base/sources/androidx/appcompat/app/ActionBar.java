package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActionBar {

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayOptions {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NavigationMode {
    }

    public interface OnMenuVisibilityListener {
        void a();
    }

    @Deprecated
    public interface OnNavigationListener {
        boolean a();
    }

    @Deprecated
    public static abstract class Tab {
        public abstract void a();

        public abstract void b();

        public abstract void c();

        public abstract void d();

        public abstract void e();
    }

    @Deprecated
    public interface TabListener {
    }

    public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener);

    public boolean g() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public abstract int j();

    public abstract int k();

    public Context l() {
        return null;
    }

    public boolean m() {
        return false;
    }

    public boolean p(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean q(KeyEvent keyEvent) {
        return false;
    }

    public boolean r() {
        return false;
    }

    public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener);

    public abstract void t(boolean z);

    public abstract void w();

    public ActionMode y(ActionMode.Callback callback) {
        return null;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int a;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBarLayout);
            this.a = typedArrayObtainStyledAttributes.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams() {
            super(-2, -2);
            this.a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.a = 0;
            this.a = layoutParams.a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
        }
    }

    public void n() {
    }

    public void o() {
    }

    public void i(boolean z) {
    }

    public void s(boolean z) {
    }

    public void u(int i) {
    }

    public void v(boolean z) {
    }

    public void x(CharSequence charSequence) {
    }
}
