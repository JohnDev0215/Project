package com.byd.hvac.view.demo;

import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.hardware.bydauto.fragrance.BYDAutoFragranceDevice;
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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DemoFragranceActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1032b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView f1033c;
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
    public class a extends AbsBYDAutoFragranceListener {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(int i, int i2) {
            ta taVar = new ta("onFragranceConcentrationChanged ");
            taVar.c("type=" + i + " - - value=" + i2);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(int i, int i2) {
            ta taVar = new ta("onFragranceInstallStateChanged ");
            taVar.c("type=" + i + " - - state=" + i2);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(int i, int i2) {
            ta taVar = new ta("onFragranceLessAllowance ");
            taVar.c("type=" + i + " - - value=" + i2);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(int i, int i2) {
            ta taVar = new ta("onFragranceNameChanged ");
            taVar.c("type=" + i + " - - value=" + i2);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(int i) {
            ta taVar = new ta("onFragranceOnlineStateChange ");
            taVar.c("state=" + i);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l(int i, int i2) {
            ta taVar = new ta("onFragranceResidueChanged ");
            taVar.c("type=" + i + " - - value=" + i2);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void n(int i) {
            ta taVar = new ta("onFragranceSystemStateChange ");
            taVar.c("state=" + i);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void p(int i) {
            ta taVar = new ta("onFragranceTypeChanged ");
            taVar.c("type=" + i);
            DemoFragranceActivity.this.m.add(taVar);
            DemoFragranceActivity.this.n.c(DemoFragranceActivity.this.m);
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceConcentrationChanged(final int i, final int i2) {
            super.onFragranceConcentrationChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.e6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f402a.b(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceInstallStateChanged(final int i, final int i2) {
            super.onFragranceInstallStateChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.c6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f351a.d(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceLessAllowance(final int i, final int i2) {
            super.onFragranceLessAllowance(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.z5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f899a.f(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceNameChanged(final int i, final int i2) {
            super.onFragranceNameChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.b6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f324a.h(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceOnlineStateChange(final int i) {
            super.onFragranceOnlineStateChange(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.d6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f379a.j(i);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceResidueChanged(final int i, final int i2) {
            super.onFragranceResidueChanged(i, i2);
            i.d(new Runnable() { // from class: b.a.a.i.h.a6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f297a.l(i, i2);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceSystemStateChange(final int i) {
            super.onFragranceSystemStateChange(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.g6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f454a.n(i);
                }
            });
        }

        @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
        public void onFragranceTypeChanged(final int i) {
            super.onFragranceTypeChanged(i);
            i.d(new Runnable() { // from class: b.a.a.i.h.f6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f429a.p(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0(ta taVar, int i) {
        Editable text = this.i.getText();
        int i2 = (text == null || TextUtils.isEmpty(text.toString())) ? 0 : Integer.parseInt(text.toString());
        String str = taVar.f780a + i2;
        if (taVar.f780a.equals("设置香氛浓度setFragranceConcentration")) {
            f1.l0().d4(i2);
        } else if (taVar.f780a.equals("设置香氛香型setFragranceType(2)")) {
            f1.l0().e4(i2);
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
            case "获取当前香氛香型getCurFragranceType":
                str = "当前香氛香型 getCurFragranceType = " + f1.l0().T();
                break;
            case "获取香氛余量报警状态getFragranceLessAllowanceState":
                str = "获取香氛余量报警状态getFragranceLessAllowanceState 香料1 = " + f1.l0().c0(1) + " - - 香料2 = " + f1.l0().c0(2) + " - - 香料3 = " + f1.l0().c0(3);
                break;
            case "获取香氛香料安装状态getFragranceInstallState":
                str = "获取香氛香料安装状态getFragranceInstallState = " + f1.l0().b0(1) + " -- " + f1.l0().b0(2) + " -- " + f1.l0().b0(3);
                break;
            case "获取香氛浓度getFragranceConcentration":
                BydLogHelper.logD("获取香氛浓度");
                str = "香氛浓度 getFragranceConcentration1 = " + f1.l0().a0(1) + " -- " + f1.l0().a0(2) + " -- " + f1.l0().a0(3);
                break;
            case "获取香氛系统是否在线getFragranceOnlineState":
                str = "香氛系统是否在 getFragranceOnlineState = " + f1.l0().e0();
                break;
            case "获取香氛余量getFragranceResidue":
                str = "香氛余量 getFragranceResidue = " + f1.l0().f0(1) + " -- " + f1.l0().f0(2) + " -- " + f1.l0().f0(3);
                break;
            case "获取香氛香型中文名getFragranceName":
                str = "获取香氛香型中文名getFragranceName = " + f1.l0().d0(1) + " -- " + f1.l0().d0(2) + " -- " + f1.l0().d0(3);
                break;
            default:
                str = "";
                break;
        }
        this.j.setText(str);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_fragrance;
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
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.j6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f529a.D0(view);
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
        this.d.add(new ta("获取香氛浓度getFragranceConcentration"));
        this.d.add(new ta("获取当前香氛香型getCurFragranceType"));
        this.d.add(new ta("获取香氛余量getFragranceResidue"));
        this.d.add(new ta("获取香氛系统是否在线getFragranceOnlineState"));
        this.d.add(new ta("获取香氛香型中文名getFragranceName"));
        this.d.add(new ta("获取香氛香料安装状态getFragranceInstallState"));
        this.d.add(new ta("获取各位置安全带状态getSafetyBeltStatus"));
        this.d.add(new ta("获取香氛余量报警状态getFragranceLessAllowanceState"));
        this.e.add(new ta("设置香氛浓度setFragranceConcentration"));
        this.e.add(new ta("设置香氛香型setFragranceType(2)"));
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
        BYDAutoFragranceDevice.getInstance(this).registerListener((AbsBYDAutoFragranceListener) new a());
    }

    public final void w0() {
        this.f1032b = (RecyclerView) findViewById(R.id.get_recyclerview);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(this);
        flexboxLayoutManager.U(0);
        flexboxLayoutManager2.V(1);
        flexboxLayoutManager2.U(0);
        flexboxLayoutManager.V(1);
        this.f1032b.setLayoutManager(flexboxLayoutManager);
        this.d = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.set_recyclerview);
        this.f1033c = recyclerView;
        recyclerView.setLayoutManager(flexboxLayoutManager2);
        this.e = new ArrayList();
        t0();
        this.f = new CommonAdapter(this, this.d);
        this.g = new CommonAdapter(this, this.e);
        this.f1032b.setAdapter(this.f);
        this.f1033c.setAdapter(this.g);
        this.f.c(new CommonAdapter.b() { // from class: b.a.a.i.h.h6
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f480a.z0(taVar, i);
            }
        });
        this.g.c(new CommonAdapter.b() { // from class: b.a.a.i.h.i6
            @Override // com.byd.hvac.view.demo.CommonAdapter.b
            public final void a(ta taVar, int i) {
                this.f504a.B0(taVar, i);
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
