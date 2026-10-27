package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.m.f.c.a.e;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.AppListAdapter;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.mvp.mvp.DialogActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AppListActivity extends DialogActivity<b.b.g.n.a> {
    public volatile int A = -1;
    public volatile String B;
    public TextView u;
    public View v;
    public View w;
    public RecyclerView x;
    public AppListAdapter y;
    public List<BydAppInfo> z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppListActivity.this.finish();
        }
    }

    public class b implements e {
        public b() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            b.b.m.c.a.a(AppListActivity.this.f3705c, "---initData---onItemClicked----position = " + i);
            AppListActivity.this.A = i;
            AppListActivity appListActivity = AppListActivity.this;
            appListActivity.B = ((BydAppInfo) appListActivity.z.get(i)).getInfo().applicationInfo.packageName;
            AppListActivity.this.Y();
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return b.b.o.b.b.a.c() == 2 ? R.layout.activity_app_list_r : R.layout.activity_app_list;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
        l(this.r, R.id.ll_status_in_loading);
        this.u = (TextView) findViewById(R.id.tv_title_app_list_app_manage);
        this.v = findViewById(R.id.rl_data_show_app_list_app_manage);
        this.w = findViewById(R.id.rl_no_data_show);
        this.x = (RecyclerView) findViewById(R.id.rcv_app_list_app_manage);
        ((ImageView) findViewById(R.id.iv_back_app_list_app_manage)).setOnClickListener(new a());
        this.x.setLayoutManager(new LinearLayoutManager(this, 1, false));
        m.b(this.x);
    }

    public final BydAppInfo V(String str) {
        for (BydAppInfo bydAppInfo : this.z) {
            if (str.equals(bydAppInfo.getInfo().packageName)) {
                return bydAppInfo;
            }
        }
        return null;
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.a newP() {
        return new b.b.g.n.a();
    }

    public final void X(Message message) {
        List list = (List) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedAppInfoList---list = " + list);
        this.z.clear();
        if (list != null && !list.isEmpty()) {
            this.z.addAll(list);
        }
        this.y.notifyDataSetChanged();
        if (this.z.size() <= 0) {
            this.v.setVisibility(8);
            this.w.setVisibility(0);
            y();
        } else {
            y();
            this.v.setVisibility(0);
            this.w.setVisibility(8);
        }
    }

    public final void Y() {
        BydAppInfo bydAppInfoV = (this.B == null || this.B.isEmpty()) ? null : V(this.B);
        if (bydAppInfoV == null && this.A != -1) {
            bydAppInfoV = this.z.get(this.A);
        }
        if (bydAppInfoV == null) {
            return;
        }
        b.b.m.c.a.a(this.f3705c, "---showAppDetails---info = " + bydAppInfoV);
        String str = bydAppInfoV.getInfo().applicationInfo.packageName;
        getString(R.string.title_app_details_app_manage);
        Intent intent = new Intent(this, (Class<?>) AppDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("app_package", str);
        bundle.putString("jump_from", "app_list");
        intent.putExtras(bundle);
        intent.setFlags(67108864);
        startActivityForResult(intent, 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        this.u.setText(getString(R.string.dilink_application));
        ArrayList arrayList = new ArrayList();
        this.z = arrayList;
        AppListAdapter appListAdapter = new AppListAdapter(arrayList);
        this.y = appListAdapter;
        this.x.setAdapter(appListAdapter);
        this.y.setOnItemClickedListener(new b());
        ((b.b.g.n.a) mo7getP()).f(33, 1);
        A();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 1 && i2 == 2) {
            String stringExtra = intent.getStringExtra("uninstall_pkg");
            for (int i3 = 0; i3 < this.z.size(); i3++) {
                String str = this.z.get(i3).getInfo().packageName;
                if (stringExtra != null && stringExtra.equals(str)) {
                    this.z.remove(i3);
                    this.y.notifyItemRemoved(i3);
                    return;
                }
            }
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        d.c(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XActivity, android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.A = bundle.getInt("key_app_index");
        b.b.m.c.a.a(this.f3705c, "---onRestoreInstanceState---appIndex = " + this.A);
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.b.m.c.a.a(this.f3705c, "---onSaveInstanceState---" + getClass().getName());
        if (d.h()) {
            bundle.putInt("key_app_index", this.A);
        }
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void w(Message message) {
        b.b.m.c.a.a(this.f3705c, "---onHandlerUpdateUI---msg = " + message);
        if (message.arg1 != 1) {
            return;
        }
        X(message);
    }
}
