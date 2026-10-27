package b.b.a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import b.b.e.b;
import b.g.j.e;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends Dialog implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f578b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e.a f579c;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    public class a implements e.a {
        public a() {
        }

        @Override // b.g.j.e.a
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return f.this.c(keyEvent);
        }
    }

    public f(Context context, int i) {
        super(context, b(context, i));
        this.f579c = new a();
        e eVarA = a();
        eVarA.F(b(context, i));
        eVarA.r(null);
    }

    public static int b(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public e a() {
        if (this.f578b == null) {
            this.f578b = e.h(this, this);
        }
        return this.f578b;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().d(view, layoutParams);
    }

    public boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean d(int i) {
        return a().A(i);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().s();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return b.g.j.e.e(this.f579c, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) a().i(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().p();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().o();
        super.onCreate(bundle);
        a().r(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        a().x();
    }

    @Override // b.b.a.d
    public void onSupportActionModeFinished(b.b.e.b bVar) {
    }

    @Override // b.b.a.d
    public void onSupportActionModeStarted(b.b.e.b bVar) {
    }

    @Override // b.b.a.d
    public b.b.e.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().B(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().G(charSequence);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().C(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().D(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().G(getContext().getString(i));
    }
}
