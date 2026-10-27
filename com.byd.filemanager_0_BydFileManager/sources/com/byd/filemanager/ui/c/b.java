package com.byd.filemanager.ui.c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.filemanager.R;

/* JADX INFO: compiled from: BasePopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends PopupWindow {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected AppCompatActivity f3739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3740b;

    public b(AppCompatActivity appCompatActivity) {
        if (appCompatActivity != null) {
            this.f3740b = appCompatActivity.getResources().getDimensionPixelSize(R.dimen.dp_4);
            this.f3739a = appCompatActivity;
            View viewInflate = LayoutInflater.from(appCompatActivity).inflate(c(), (ViewGroup) null);
            setContentView(viewInflate);
            a();
            b(viewInflate);
            setWidth(-2);
            setHeight(-2);
            d();
            setFocusable(true);
            setOutsideTouchable(true);
            update();
        }
    }

    protected abstract void a();

    protected abstract void b(View view);

    protected abstract int c();

    protected abstract void d();

    public void e(View view) {
        if (isShowing()) {
            dismiss();
        } else {
            showAsDropDown(view, 0, this.f3740b, 8388613);
        }
    }
}
