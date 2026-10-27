package com.byd.hvac.view.demo;

import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.a.f.f1;
import b.a.a.h.i;
import b.a.a.i.h.ta;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseActivity;
import com.byd.hvac.view.demo.CommonAdapter;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DemoGreenActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1035b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView f1036c;
    public List<ta> d;
    public List<ta> e;
    public CommonAdapter f;
    public CommonAdapter g;
    public EditText h;
    public EditText i;
    public TextView j;
    public TextView k;
    public RecyclerView l;
    public CopyOnWriteArrayList<ta> m;
    public CallbackAdapter n;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends AbsBYDAutoPM2p5Listener {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i) {
            ta taVar = new ta("onNegativeIonStateChange ");
            taVar.c("state=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(int i) {
            ta taVar = new ta("onPM2p5AnionDetectChanged ");
            taVar.c("state=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(int i, int i2) {
            ta taVar = new ta("onPM2p5CheckStateChanged ");
            taVar.c("stateIn=" + i + " - - stateOut=" + i2);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(int i) {
            ta taVar = new ta("onPM2p5DualChannelDetectChanged ");
            taVar.c("state=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(int i, int i2) {
            ta taVar = new ta("onPM2p5LevelChanged ");
            taVar.c("levelIn=" + i + " - - levelOut=" + i2);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l(int i) {
            ta taVar = new ta("onPM2p5OnlineStateChanged ");
            taVar.c("state=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n(int i, int i2) {
            ta taVar = new ta("onPM2p5ValueChanged ");
            taVar.c("valueIn=" + i + " - - valueOut=" + i2);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p(int i) {
            ta taVar = new ta("onPM2p5WarningInfoChanged ");
            taVar.c("info=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void r(int i) {
            ta taVar = new ta("onPromptInfoChanged ");
            taVar.c("info=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void t(int i) {
            ta taVar = new ta("onRapidPurificationPM2p5StateChanged ");
            taVar.c("state=" + i);
            DemoGreenActivity.this.m.add(taVar);
            DemoGreenActivity.this.n.c(DemoGreenActivity.this.m);
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onNegativeIonStateChange(final int i) {
            super.onNegativeIonStateChange(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.m6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f607a.b(i);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5AnionDetectChanged(final int i) {
            super.onPM2p5AnionDetectChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.t6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f773a.d(i);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5CheckStateChanged(final int i, final int i2) {
            super.onPM2p5CheckStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.s6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f752a.f(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5DualChannelDetectChanged(final int i) {
            super.onPM2p5DualChannelDetectChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.r6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f728a.h(i);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5LevelChanged(final int i, final int i2) {
            super.onPM2p5LevelChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.p6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f683a.j(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5OnlineStateChanged(final int i) {
            super.onPM2p5OnlineStateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.q6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f706a.l(i);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5ValueChanged(final int i, final int i2) {
            super.onPM2p5ValueChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.k6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f555a.n(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5WarningInfoChanged(final int i) {
            super.onPM2p5WarningInfoChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.o6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f660a.p(i);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPromptInfoChanged(final int i) {
            super.onPromptInfoChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.l6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f582a.r(i);
                }
            });
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onRapidPurificationPM2p5StateChanged(final int i) {
            super.onRapidPurificationPM2p5StateChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.n6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f634a.t(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0(ta taVar, int i) {
        int i2;
        String str;
        Editable text = this.i.getText();
        i2 = (text == null || TextUtils.isEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
        str = taVar.f780a;
        str.hashCode();
        switch (str) {
            case "设置车内/外PM2.5检测状态(双通道)setPM2p5DualChannelDetectState":
                f1.l0().l4(i2);
                str = str + i2;
                break;
            case "设置快速净化功能状态setQuickCleanAirState":
                f1.l0().n4(i2);
                str = str + i2;
                break;
            case "设置负离子功能状态setPM2p5AnionDetectState":
                f1.l0().j4(i2);
                str = str + i2;
                break;
            case "设置车内/车外检测状态setPM2p5CheckState":
                f1.l0().k4(i2);
                str = str + "/车内/" + i2 + " -- /车车外/" + i2;
                break;
        }
        this.k.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void D0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z0(ta taVar, int i) {
        String str;
        String str2 = taVar.f780a;
        str2.hashCode();
        switch (str2) {
            case "获取PM2.5是否在线getPM2p5OnlineState":
                BydLogHelper.logD("获取PM2.5是否在线");
                str = "PM2.5是否在线 getPM2p5OnlineState = " + f1.l0().y0();
                break;
            case "获取车内/外PM2.5检测状态(双通道)getPM2p5DualChannelDetectState":
                str = "车内/外PM2.5检测状态(双通道) getPM2p5DualChannelDetectState = " + f1.l0().w0();
                break;
            case "获取车内/车外PM2.5数值getPM2p5Value":
                str = "车内/车外PM2.5数值 getPM2p5Value = " + Arrays.toString(f1.l0().z0());
                break;
            case "获取快速净化功能状态getQuickCleanAirState":
                str = "快速净化功能状态 getQuickCleanAirState = " + f1.l0().K0();
                break;
            case "获取车内/车外检测状态getPM2p5CheckState":
                str = "车内/车外检测状态 getPM2p5CheckState = " + Arrays.toString(f1.l0().v0());
                break;
            case "获取车内/车外PM2.5等级getPM2p5Level":
                str = "车内/车外PM2.5等级 getPM2p5Level = " + Arrays.toString(f1.l0().x0());
                break;
            case "获取负离子功能状态getPM2p5AnionState":
                str = "负离子功能状态 getPM2p5AnionState = " + f1.l0().u0();
                break;
            case "获取PM2.5警告信息getPM2p5WarningInfo":
                str = "PM2.5警告信息 getPM2p5WarningInfo = " + f1.l0().A0();
                break;
            default:
                str = "";
                break;
        }
        this.j.setText(str);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_green;
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void V() {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (x0(currentFocus, motionEvent)) {
                s0(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void f0() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.u6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f796a.D0(view);
            }
        });
        v0();
        w0();
        this.h = (EditText) findViewById(R.id.get_edittext1);
        this.i = (EditText) findViewById(R.id.set_edittext1);
        this.j = (TextView) findViewById(R.id.text);
        this.k = (TextView) findViewById(R.id.set_text);
        this.l = (RecyclerView) findViewById(R.id.callback_recyclerview);
        u0();
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public final void s0(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public void t0() {
        this.d.add(new ta("获取PM2.5是否在线getPM2p5OnlineState"));
        this.d.add(new ta("获取车内/车外检测状态getPM2p5CheckState"));
        this.d.add(new ta("获取车内/外PM2.5检测状态(双通道)getPM2p5DualChannelDetectState"));
        this.d.add(new ta("获取车内/车外PM2.5等级getPM2p5Level"));
        this.d.add(new ta("获取车内/车外PM2.5数值getPM2p5Value"));
        this.d.add(new ta("获取快速净化功能状态getQuickCleanAirState"));
        this.d.add(new ta("获取负离子功能状态getPM2p5AnionState"));
        this.d.add(new ta("获取PM2.5警告信息getPM2p5WarningInfo"));
        this.e.add(new ta("设置车内/车外检测状态setPM2p5CheckState"));
        this.e.add(new ta("设置车内/外PM2.5检测状态(双通道)setPM2p5DualChannelDetectState"));
        this.e.add(new ta("设置快速净化功能状态setQuickCleanAirState"));
        this.e.add(new ta("设置负离子功能状态setPM2p5AnionDetectState"));
    }

    public final void u0() {
        this.l.setLayoutManager(new LinearLayoutManager(this, 1, true));
        CopyOnWriteArrayList<ta> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.m = copyOnWriteArrayList;
        CallbackAdapter callbackAdapter = new CallbackAdapter(this, copyOnWriteArrayList);
        this.n = callbackAdapter;
        this.l.setAdapter(callbackAdapter);
    }

    public final void v0() {
        BYDAutoPM2p5Device.getInstance(this).registerListener((AbsBYDAutoPM2p5Listener) new a());
    }

    public final void w0() {
        this.f1035b = (RecyclerView) findViewById(R.id.get_recyclerview);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(this);
        flexboxLayoutManager.U(0);
        flexboxLayoutManager2.V(1);
        flexboxLayoutManager2.U(0);
        flexboxLayoutManager.V(1);
        this.f1035b.setLayoutManager(flexboxLayoutManager);
        this.d = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.set_recyclerview);
        this.f1036c = recyclerView;
        recyclerView.setLayoutManager(flexboxLayoutManager2);
        this.e = new ArrayList();
        t0();
        this.f = new CommonAdapter(this, this.d);
        this.g = new CommonAdapter(this, this.e);
        this.f1035b.setAdapter(this.f);
        this.f1036c.setAdapter(this.g);
        this.f.c(new CommonAdapter.b() { // from class: b.a.a.i.h.w6
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f837a.z0(taVar, i);
            }
        });
        this.g.c(new CommonAdapter.b() { // from class: b.a.a.i.h.v6
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f817a.B0(taVar, i);
            }
        });
    }

    public final boolean x0(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }
}
