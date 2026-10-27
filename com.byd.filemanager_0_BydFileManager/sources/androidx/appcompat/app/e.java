package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import b.a.o.b;
import b.g.k.e;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Dialog implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e.a f158c;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    class a implements e.a {
        a() {
        }

        @Override // b.g.k.e.a
        public boolean i(KeyEvent keyEvent) {
            return e.this.c(keyEvent);
        }
    }

    public e(Context context, int i) {
        super(context, b(context, i));
        this.f158c = new a();
        d dVarA = a();
        dVarA.D(b(context, i));
        dVarA.q(null);
    }

    private static int b(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(b.a.a.A, typedValue, true);
        return typedValue.resourceId;
    }

    public d a() {
        if (this.f157b == null) {
            this.f157b = d.h(this, this);
        }
        return this.f157b;
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
        return b.g.k.e.e(this.f158c, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().i(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().o();
    }

    @Override // androidx.appcompat.app.c
    public void m(b.a.o.b bVar) {
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

    @Override // androidx.appcompat.app.c
    public void p(b.a.o.b bVar) {
    }

    @Override // androidx.appcompat.app.c
    public b.a.o.b q(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
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
