package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.g.n.f;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.DisableSelfStartAdapter;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class StartupAppManageActivity extends DialogActivity<f> {
    public View u;
    public View v;
    public RecyclerView w;
    public DisableSelfStartAdapter x;
    public List<b.b.g.p.k.a> y;

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
                f fVar = (f) StartupAppManageActivity.this.mo7getP();
                StartupAppManageActivity startupAppManageActivity = StartupAppManageActivity.this;
                fVar.c(startupAppManageActivity, (b.b.g.p.k.a) startupAppManageActivity.y.get(i), z);
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
            startupAppManageActivity.W(((f) startupAppManageActivity.mo7getP()).d());
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StartupAppManageActivity.this.x.notifyDataSetChanged();
            if (StartupAppManageActivity.this.y.isEmpty()) {
                StartupAppManageActivity.this.u.setVisibility(8);
                StartupAppManageActivity.this.v.setVisibility(0);
            } else {
                StartupAppManageActivity.this.u.setVisibility(0);
                StartupAppManageActivity.this.v.setVisibility(8);
            }
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return R.layout.activity_disable_self_start;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
        this.u = findViewById(R.id.rl_data_show);
        this.v = findViewById(R.id.rl_no_data_show);
        this.w = (RecyclerView) findViewById(R.id.rcv_disable_self_start);
        findViewById(R.id.iv_back_disable_self_start).setOnClickListener(new a());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public f newP() {
        return new f();
    }

    public final void W(List<b.b.g.p.k.a> list) {
        this.y.clear();
        if (list != null && !list.isEmpty()) {
            this.y.addAll(list);
        }
        runOnUiThread(new d());
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void e() {
        super.e();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        ((f) mo7getP()).e();
        ArrayList arrayList = new ArrayList();
        this.y = arrayList;
        this.x = new DisableSelfStartAdapter(arrayList);
        this.w.setLayoutManager(new LinearLayoutManager(this, 1, false));
        m.b(this.w);
        this.w.setAdapter(this.x);
        this.x.setOnItemBtnCheckedCallback(new b());
        b.b.m.g.b.a(new c());
    }

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b.b.m.f.d.d.c(getClass().getName());
    }
}
