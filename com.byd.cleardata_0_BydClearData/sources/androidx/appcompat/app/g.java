package androidx.appcompat.app;

import a.a.n.b;
import a.g.k.e;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class g extends Dialog implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e.a f743c;

    class a implements e.a {
        a() {
        }

        @Override // a.g.k.e.a
        public boolean i(KeyEvent keyEvent) {
            return g.this.c(keyEvent);
        }
    }

    public g(Context context, int i) {
        super(context, b(context, i));
        this.f743c = new a();
        e eVarA = a();
        eVarA.D(b(context, i));
        eVarA.q(null);
    }

    private static int b(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.a.a.y, typedValue, true);
        return typedValue.resourceId;
    }

    public e a() {
        if (this.f742b == null) {
            this.f742b = e.h(this, this);
        }
        return this.f742b;
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
        return a.g.k.e.e(this.f743c, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().i(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().o();
    }

    @Override // androidx.appcompat.app.d
    public void l(a.a.n.b bVar) {
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

    @Override // androidx.appcompat.app.d
    public void p(a.a.n.b bVar) {
    }

    @Override // androidx.appcompat.app.d
    public a.a.n.b q(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().A(i);
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

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().E(charSequence);
    }
}
