package com.byd.cleardata.ui.e;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends PopupWindow {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected androidx.appcompat.app.c f2799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2800b;

    public b(androidx.appcompat.app.c cVar) {
        this.f2800b = cVar.getResources().getDimensionPixelSize(R.dimen.dp_4);
        this.f2799a = cVar;
        View viewInflate = LayoutInflater.from(cVar).inflate(c(), (ViewGroup) null);
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

    protected abstract void a();

    protected abstract void b(View view);

    protected abstract int c();

    protected abstract void d();

    public void e(View view) {
        if (isShowing()) {
            dismiss();
        } else {
            showAsDropDown(view, 0, this.f2800b, 8388613);
        }
    }
}
