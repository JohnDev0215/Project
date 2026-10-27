package b.b.k;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import b.b.p.b;
import b.f.m.e;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Dialog implements c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e.a f786d;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    public class a implements e.a {
        public a() {
        }

        @Override // b.f.m.e.a
        public boolean e(KeyEvent keyEvent) {
            return e.this.c(keyEvent);
        }
    }

    public e(Context context, int i2) {
        super(context, b(context, i2));
        this.f786d = new a();
        d dVarA = a();
        dVarA.D(b(context, i2));
        dVarA.q(null);
    }

    public static int b(Context context, int i2) {
        if (i2 != 0) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(b.b.a.z, typedValue, true);
        return typedValue.resourceId;
    }

    public d a() {
        if (this.f785c == null) {
            this.f785c = d.h(this, this);
        }
        return this.f785c;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().d(view, layoutParams);
    }

    public boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean d(int i2) {
        return a().z(i2);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().r();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return b.f.m.e.e(this.f786d, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i2) {
        return (T) a().i(i2);
    }

    @Override // b.b.k.c
    public void g(b.b.p.b bVar) {
    }

    @Override // b.b.k.c
    public void i(b.b.p.b bVar) {
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().o();
    }

    @Override // b.b.k.c
    public b.b.p.b j(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().n();
        super.onCreate(bundle);
        a().q(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        a().w();
    }

    @Override // android.app.Dialog
    public void setContentView(int i2) {
        a().A(i2);
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
    public void setTitle(int i2) {
        super.setTitle(i2);
        a().E(getContext().getString(i2));
    }
}
