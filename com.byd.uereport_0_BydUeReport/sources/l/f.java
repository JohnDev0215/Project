package l;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import o0.e;
import p.b;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends Dialog implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f5936d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e.a f5937e;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    public class a implements e.a {
        public a() {
        }

        @Override // o0.e.a
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return f.this.c(keyEvent);
        }
    }

    public f(Context context, int i8) {
        super(context, b(context, i8));
        this.f5937e = new a();
        e eVarA = a();
        eVarA.F(b(context, i8));
        eVarA.r(null);
    }

    public static int b(Context context, int i8) {
        if (i8 != 0) {
            return i8;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public e a() {
        if (this.f5936d == null) {
            this.f5936d = e.h(this, this);
        }
        return this.f5936d;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().d(view, layoutParams);
    }

    public boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean d(int i8) {
        return a().A(i8);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().s();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return o0.e.e(this.f5937e, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i8) {
        return (T) a().i(i8);
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

    @Override // l.d
    public void onSupportActionModeFinished(p.b bVar) {
    }

    @Override // l.d
    public void onSupportActionModeStarted(p.b bVar) {
    }

    @Override // l.d
    public p.b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i8) {
        a().B(i8);
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
    public void setTitle(int i8) {
        super.setTitle(i8);
        a().G(getContext().getString(i8));
    }
}
