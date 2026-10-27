package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.m.e.g;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.CopyrightNoticeAdapter;
import com.byd.carsettings.model.dilink.CopyrightNoticeApp;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.systemsettings.system.AppLicenseDialog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CopyrightNoticeActivity extends DialogActivity {
    public TextView u;
    public RecyclerView v;
    public CopyrightNoticeAdapter w;
    public List<CopyrightNoticeApp> x;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CopyrightNoticeActivity.this.finish();
        }
    }

    public class b implements e {
        public b() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            b.b.m.c.a.a(CopyrightNoticeActivity.this.f3705c, "---initData---onItemClicked----position = " + i);
            AppLicenseDialog.newInstance((CopyrightNoticeApp) CopyrightNoticeActivity.this.x.get(i)).show(CopyrightNoticeActivity.this.getSupportFragmentManager(), "AppLicenseDialog");
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return R.layout.activity_app_list;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
        findViewById(R.id.ll_status_in_loading).setVisibility(8);
        this.u = (TextView) findViewById(R.id.tv_title_app_list_app_manage);
        this.v = (RecyclerView) findViewById(R.id.rcv_app_list_app_manage);
        ((ImageView) findViewById(R.id.iv_back_app_list_app_manage)).setOnClickListener(new a());
        this.v.setLayoutManager(new LinearLayoutManager(this, 1, false));
        m.b(this.v);
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        this.u.setText(getString(R.string.open_source_licenses));
        ArrayList arrayList = new ArrayList();
        this.x = arrayList;
        arrayList.add(new CopyrightNoticeApp(getString(R.string.os_licenses), "os.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.app_licenses), "app.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.isa_map_licenses), "isa_map.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.dms_licenses), "dms.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.stingray_licenses), "stingray.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.spotify_licenses), "spotify.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.assistant_licenses), "assistant.html"));
        this.x.add(new CopyrightNoticeApp(getString(R.string.navigation_licenses), "navigation.html"));
        CopyrightNoticeAdapter copyrightNoticeAdapter = new CopyrightNoticeAdapter(this.x);
        this.w = copyrightNoticeAdapter;
        this.v.setAdapter(copyrightNoticeAdapter);
        this.w.setOnItemClickedListener(new b());
    }

    @Override // b.b.m.e.h
    public g newP() {
        return null;
    }
}
