package com.byd.logtools.apkinstall.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.logtools.R;
import com.byd.logtools.apkinstall.ui.h;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ApkInstallActivity extends AppCompatActivity implements View.OnClickListener, com.byd.logtools.apkinstall.a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h f3078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RecyclerView f3079c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private FrameLayout f3080d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ImageView f3081e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CheckBox f3082f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private com.byd.logtools.apkinstall.c.e f3083g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextView f3084h;
    private TextView i;
    private TextView j;
    private int k;
    private int l;
    private int m;

    private void i() {
        BydLogHelper.logD("initData");
        int intExtra = getIntent().getIntExtra("verifyType", 2);
        com.byd.logtools.apkinstall.c.e eVarF = com.byd.logtools.apkinstall.c.e.f(this);
        this.f3083g = eVarF;
        eVarF.x(intExtra);
        this.f3078b.v(this.f3083g.d(intExtra));
        this.f3078b.h();
        this.f3083g.v(new com.byd.logtools.apkinstall.a.a() { // from class: com.byd.logtools.apkinstall.ui.b
            @Override // com.byd.logtools.apkinstall.a.a
            public final void a(String str, int i) {
                this.f3092a.t(str, i);
            }
        });
    }

    private void j() {
        this.f3080d = (FrameLayout) findViewById(R.id.layout_loading);
        this.f3081e = (ImageView) findViewById(R.id.iv_loading);
        this.f3084h = (TextView) findViewById(R.id.tv_success);
        this.i = (TextView) findViewById(R.id.tv_fail);
        this.j = (TextView) findViewById(R.id.tv_total);
        this.f3079c = (RecyclerView) findViewById(R.id.recycler_view_apk);
        s();
        findViewById(R.id.btn_install).setOnClickListener(this);
        h hVar = new h();
        this.f3078b = hVar;
        this.f3079c.setAdapter(hVar);
        this.f3079c.setLayoutManager(new LinearLayoutManager(this));
        this.f3079c.setItemAnimator(null);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_select_all);
        this.f3082f = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.logtools.apkinstall.ui.e
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f3097b.n(compoundButton, z);
            }
        });
        this.f3078b.C(new h.a() { // from class: com.byd.logtools.apkinstall.ui.c
            @Override // com.byd.logtools.apkinstall.ui.h.a
            public final void a(boolean z) {
                this.f3093a.p(z);
            }
        });
    }

    private void k() {
        if (this.f3078b.c() == 0) {
            BydLogHelper.logE("installSelectedApk fail, no apk exist!!!");
            return;
        }
        List<com.byd.logtools.apkinstall.b.a> listW = this.f3078b.w();
        for (int i = 0; i < listW.size(); i++) {
            com.byd.logtools.apkinstall.b.a aVar = listW.get(i);
            if (aVar.d() && aVar.c() != 1) {
                aVar.e(1);
                this.f3078b.i(i);
                this.m++;
                s();
                this.f3083g.c(aVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n(CompoundButton compoundButton, boolean z) {
        if (this.f3082f.isPressed()) {
            this.f3078b.B(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p(boolean z) {
        this.f3082f.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r(int i, String str) {
        Toast.makeText(getApplicationContext(), getString(i != 0 ? i != 1 ? i != 2 ? R.string.install_fail : R.string.install_fail_no_permission : R.string.install_fail_no_space : R.string.install_success, new Object[]{str}), 0).show();
        this.f3078b.E(str, i == 0);
        if (i == 0) {
            this.k++;
        } else {
            this.l++;
        }
        s();
    }

    private void s() {
        this.f3084h.setText(getString(R.string.install_success_count, new Object[]{Integer.valueOf(this.k), Integer.valueOf(this.m)}));
        this.i.setText(getString(R.string.install_fail_count, new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.m)}));
        this.j.setText(getString(R.string.install_total_count, new Object[]{Integer.valueOf(this.k + this.l), Integer.valueOf(this.m)}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"StringFormatInvalid"})
    public void t(final String str, final int i) {
        runOnUiThread(new Runnable() { // from class: com.byd.logtools.apkinstall.ui.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f3094b.r(i, str);
            }
        });
    }

    @Override // com.byd.logtools.apkinstall.a.b
    public void c() {
        BydLogHelper.logD("onDetach");
        finish();
    }

    @Override // com.byd.logtools.apkinstall.a.b
    public void g() {
        BydLogHelper.logD("onAttach");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_install) {
            return;
        }
        k();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        BydLogHelper.logD("onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.activity_apk_install);
        j();
        i();
        com.byd.logtools.apkinstall.c.f.e(this).d(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        BydLogHelper.logD("onDestroy");
        super.onDestroy();
        this.f3083g.u();
        this.f3083g.b();
        com.byd.logtools.apkinstall.c.f.e(this).i(this);
    }
}
