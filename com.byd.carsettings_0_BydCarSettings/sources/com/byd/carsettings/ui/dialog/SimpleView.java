package com.byd.carsettings.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.byd.base.view.dialog.FrameDialogView;
import com.byd.carsettings.R;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleView extends FrameDialogView implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SimpleDialog f2985e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewGroup f2986f;
    public TextView g;
    public TextView h;
    public Button i;
    public Button j;
    public View k;
    public int l;
    public DampingScrollView m;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SimpleView.this.h.getLineCount() < 3) {
                SimpleView.this.h.setGravity(17);
            } else {
                SimpleView.this.h.setGravity(8388627);
            }
        }
    }

    public SimpleView(Context context, SimpleDialog simpleDialog) {
        super(context);
        this.f2985e = simpleDialog;
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void a(View view) {
        this.f2986f = (ViewGroup) view.findViewById(R.id.ll_opertion_bottom_byd_dialog_simple);
        this.g = (TextView) view.findViewById(R.id.tv_title);
        this.k = view.findViewById(R.id.view_line);
        TextView textView = (TextView) view.findViewById(R.id.tv_message);
        this.h = textView;
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        Button button = (Button) view.findViewById(R.id.btn_ok);
        this.j = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.btn_cancel);
        this.i = button2;
        button2.setOnClickListener(this);
        DampingScrollView dampingScrollView = (DampingScrollView) view.findViewById(R.id.dampingScrollView_dialog);
        this.m = dampingScrollView;
        dampingScrollView.setScroll(false);
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void e(boolean z) {
        g(this.f2986f, true);
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void f(boolean z) {
        g(this.f2986f, false);
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public int getDialogViewRes() {
        return R.layout.byd_dialog_simple;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cancel) {
            this.f2985e.dismiss();
            return;
        }
        if (view.getId() == R.id.btn_ok) {
            this.f2985e.dismiss();
            int i = this.l;
            if (i == 100) {
                EventBus.getDefault().post(new b.b.a.c.a(100));
                return;
            }
            if (i == 101) {
                EventBus.getDefault().post(new b.b.a.c.a(101));
                return;
            }
            if (i == 102) {
                EventBus.getDefault().post(new b.b.a.c.a(102));
                return;
            }
            if (i == 103) {
                EventBus.getDefault().post(new b.b.a.c.a(103));
                return;
            }
            if (i == 104) {
                EventBus.getDefault().post(new b.b.a.c.a(104));
                return;
            }
            if (i == 105) {
                EventBus.getDefault().post(new b.b.a.c.a(105));
                return;
            }
            if (i == 106) {
                EventBus.getDefault().post(new b.b.a.c.a(106));
                return;
            }
            if (i == 107) {
                EventBus.getDefault().post(new b.b.a.c.a(107));
                return;
            }
            if (i == 108) {
                EventBus.getDefault().post(new b.b.a.c.a(108));
                return;
            }
            if (i == 109) {
                EventBus.getDefault().post(new b.b.a.c.a(109));
            } else if (i == 110) {
                EventBus.getDefault().post(new b.b.a.c.a(110));
            } else if (i == 65810) {
                EventBus.getDefault().post(new b.b.a.c.a(65810));
            }
        }
    }

    public void setShowText(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            this.g.setVisibility(8);
            this.k.setVisibility(8);
        } else {
            this.g.setText(str);
            this.g.setVisibility(0);
            this.k.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str2)) {
            this.h.setText(str2);
            this.h.post(new a());
        }
        if (TextUtils.isEmpty(str3)) {
            this.j.setVisibility(8);
        } else {
            this.j.setText(str3);
            this.j.setVisibility(0);
        }
        if (TextUtils.isEmpty(str4)) {
            this.i.setVisibility(8);
        } else {
            this.i.setText(str4);
            this.i.setVisibility(0);
        }
    }

    public void setYeWuType(int i) {
        this.l = i;
    }
}
