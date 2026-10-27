package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import i.b;
import i0.f;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends Dialog implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private e f512e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final f.a f513g;

    /* JADX INFO: compiled from: AppCompatDialog.java */
    class a implements f.a {
        a() {
        }

        @Override // i0.f.a
        public boolean h(KeyEvent keyEvent) {
            return f.this.c(keyEvent);
        }
    }

    public f(Context context, int i7) {
        super(context, b(context, i7));
        this.f513g = new a();
        e eVarA = a();
        eVarA.D(b(context, i7));
        eVarA.q(null);
    }

    private static int b(Context context, int i7) {
        if (i7 != 0) {
            return i7;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(e.a.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public e a() {
        if (this.f512e == null) {
            this.f512e = e.h(this, this);
        }
        return this.f512e;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().d(view, layoutParams);
    }

    boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean d(int i7) {
        return a().z(i7);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a().r();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return i0.f.b(this.f513g, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // androidx.appcompat.app.d
    public void e(i.b bVar) {
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i7) {
        return (T) a().i(i7);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().o();
    }

    @Override // androidx.appcompat.app.d
    public void j(i.b bVar) {
    }

    @Override // androidx.appcompat.app.d
    public i.b l(b.a aVar) {
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
    public void setContentView(int i7) {
        a().A(i7);
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
    public void setTitle(int i7) {
        super.setTitle(i7);
        a().E(getContext().getString(i7));
    }
}
