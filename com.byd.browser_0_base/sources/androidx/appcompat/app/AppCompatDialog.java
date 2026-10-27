package androidx.appcompat.app;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentDialog;
import androidx.appcompat.R;
import androidx.appcompat.app.AppLocalesStorageHelper;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDialog extends ComponentDialog implements AppCompatCallback {
    public AppCompatDelegate h;
    public final b i;

    public AppCompatDialog(Context context) {
        this(context, 0);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c().d(view, layoutParams);
    }

    public final AppCompatDelegate c() {
        if (this.h == null) {
            AppLocalesStorageHelper.SerialExecutor serialExecutor = AppCompatDelegate.e;
            this.h = new AppCompatDelegateImpl(getContext(), getWindow(), this, this);
        }
        return this.h;
    }

    final boolean d(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        c().q();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        getWindow().getDecorView();
        b bVar = this.i;
        if (bVar == null) {
            return false;
        }
        return bVar.a(keyEvent);
    }

    @Override // android.app.Dialog
    public final <T extends View> T findViewById(int i) {
        return (T) c().g(i);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        c().m();
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        c().l();
        super.onCreate(bundle);
        c().p();
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public final void onStop() {
        super.onStop();
        c().v();
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(int i) {
        c().y(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        c().G(charSequence);
    }

    public AppCompatDialog(Context context, int i) {
        int i2;
        if (i == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
            i2 = typedValue.resourceId;
        } else {
            i2 = i;
        }
        super(context, i2);
        this.i = new b(this);
        AppCompatDelegate appCompatDelegateC = c();
        if (i == 0) {
            TypedValue typedValue2 = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue2, true);
            i = typedValue2.resourceId;
        }
        appCompatDelegateC.F(i);
        appCompatDelegateC.p();
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view) {
        c().z(view);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c().A(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        c().G(getContext().getString(i));
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public final void I0() {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public final void Y0() {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public final void j0() {
    }
}
