package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.c.b;
import androidx.core.view.e;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c extends Dialog implements b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AppCompatDelegate f140c;
    private final e.a d;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements e.a {
        a() {
        }

        @Override // androidx.core.view.e.a
        public boolean e(KeyEvent keyEvent) {
            return c.this.c(keyEvent);
        }
    }

    public c(Context context, int i) {
        super(context, b(context, i));
        this.d = new a();
        AppCompatDelegate appCompatDelegateA = a();
        appCompatDelegateA.D(b(context, i));
        appCompatDelegateA.q(null);
    }

    private static int b(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public AppCompatDelegate a() {
        if (this.f140c == null) {
            this.f140c = AppCompatDelegate.h(this, this);
        }
        return this.f140c;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().d(view, layoutParams);
    }

    boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean d(int i) {
        return a().z(i);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().r();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.core.view.e.e(this.d, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    @Nullable
    public <T extends View> T findViewById(@IdRes int i) {
        return (T) a().i(i);
    }

    @Override // androidx.appcompat.app.b
    public void g(androidx.appcompat.c.b bVar) {
    }

    @Override // androidx.appcompat.app.b
    public void i(androidx.appcompat.c.b bVar) {
    }

    @Override // android.app.Dialog
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void invalidateOptionsMenu() {
        a().o();
    }

    @Override // androidx.appcompat.app.b
    @Nullable
    public androidx.appcompat.c.b j(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        a().n();
        super.onCreate(bundle);
        a().q(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        a().w();
    }

    @Override // android.app.Dialog
    public void setContentView(@LayoutRes int i) {
        a().A(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().E(charSequence);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().B(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().C(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().E(getContext().getString(i));
    }
}
