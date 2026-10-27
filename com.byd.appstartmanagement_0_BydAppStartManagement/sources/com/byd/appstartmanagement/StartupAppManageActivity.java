package com.byd.appstartmanagement;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.SystemProperties;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StartupAppManageActivity extends DialogActivity<b.a.a.c> {
    public View s;
    public View t;
    public RecyclerView u;
    public DisableSelfStartAdapter v;
    public List<b.a.a.a> w;
    public View x;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            StartupAppManageActivity.this.finish();
        }
    }

    public class b implements BydAdapter.a {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.mvp.ui.adapter.BydAdapter.a
        public void a(int i, boolean z, BtnType btnType) {
            if (btnType == BtnType.BTN_0) {
                b.a.a.c cVar = (b.a.a.c) StartupAppManageActivity.this.e();
                StartupAppManageActivity startupAppManageActivity = StartupAppManageActivity.this;
                cVar.d(startupAppManageActivity, (b.a.a.a) startupAppManageActivity.w.get(i), z);
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            StartupAppManageActivity startupAppManageActivity = StartupAppManageActivity.this;
            startupAppManageActivity.I(((b.a.a.c) startupAppManageActivity.e()).e());
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StartupAppManageActivity.this.v.notifyDataSetChanged();
            if (StartupAppManageActivity.this.w.isEmpty()) {
                StartupAppManageActivity.this.s.setVisibility(8);
                StartupAppManageActivity.this.t.setVisibility(0);
            } else {
                StartupAppManageActivity.this.s.setVisibility(0);
                StartupAppManageActivity.this.t.setVisibility(8);
            }
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void A(Context context) {
        this.s = findViewById(R.id.rl_data_show);
        this.t = findViewById(R.id.rl_no_data_show);
        this.u = (RecyclerView) findViewById(R.id.rcv_disable_self_start);
        findViewById(R.id.iv_back_disable_self_start).setOnClickListener(new a());
        this.x = findViewById(R.id.rl_content);
        if ("1".equals(SystemProperties.get(BYDAutoSysPropertiesDevice.UI_STYLE))) {
            this.x.setBackgroundResource(R.drawable.bg_frame_dialog_gradient_denz);
        }
    }

    @Override // b.a.d.d.f
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public b.a.a.c f() {
        return new b.a.a.c();
    }

    public final void I(List<b.a.a.a> list) {
        this.w.clear();
        if (list != null && !list.isEmpty()) {
            this.w.addAll(list);
        }
        runOnUiThread(new d());
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void g() {
        super.g();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void i(Context context) {
        ((b.a.a.c) e()).f();
        ArrayList arrayList = new ArrayList();
        this.w = arrayList;
        this.v = new DisableSelfStartAdapter(arrayList);
        this.u.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.u.setAdapter(this.v);
        this.v.h(new b());
        b.a.d.f.b.a(new c());
    }

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int w(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int x() {
        return b.a.b.d.a.a(this.f650c) ? R.layout.activity_disable_self_start_r : R.layout.activity_disable_self_start;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int z(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }
}
