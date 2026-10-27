package com.byd.secretcode.activity;

import android.content.Context;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import defpackage.lg;
import defpackage.pg;
import defpackage.um;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CanActivity extends AppCompatActivity {
    public ListView c;
    public TextView d;
    public TextView e;
    public TextView f;
    public TextView g;
    public um j;
    public List<lg> b = new ArrayList();
    public BYDAutoVersionDevice h = null;
    public Context i = null;

    public class a extends BaseAdapter {
        public List<lg> b;

        /* JADX INFO: renamed from: com.byd.secretcode.activity.CanActivity$a$a, reason: collision with other inner class name */
        public class C0005a {
            public ImageView a;
            public TextView b;
            public TextView c;
            public TextView d;

            public C0005a() {
            }
        }

        public a(List<lg> list) {
            ArrayList arrayList = new ArrayList();
            this.b = arrayList;
            arrayList.clear();
            this.b.addAll(list);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.b.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C0005a c0005a;
            if (view == null) {
                view = LayoutInflater.from(CanActivity.this.getApplicationContext()).inflate(R.layout.item_appinfo, (ViewGroup) null);
                c0005a = new C0005a();
                c0005a.a = (ImageView) view.findViewById(R.id.img_icon);
                c0005a.b = (TextView) view.findViewById(R.id.tv_appname);
                c0005a.c = (TextView) view.findViewById(R.id.tv_app_version_code);
                c0005a.d = (TextView) view.findViewById(R.id.tv_app_version_name);
                view.setTag(c0005a);
            } else {
                c0005a = (C0005a) view.getTag();
            }
            c0005a.a.setImageDrawable(this.b.get(i).b());
            c0005a.b.setText(this.b.get(i).a());
            c0005a.c.setText("VersionCode: " + this.b.get(i).c());
            c0005a.d.setText("VersionName: " + this.b.get(i).d());
            return view;
        }
    }

    public final void initData() {
        String strH;
        this.b.clear();
        this.b.addAll(pg.c().a(getApplicationContext()));
        this.c.setAdapter((ListAdapter) new a(this.b));
        if (this.j == null) {
            try {
                this.j = um.p();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        try {
            strH = this.j.h();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            strH = "";
        }
        this.d.setText(getString(R.string.can_text_1) + SystemProperties.get("persist.vendor.bosch.cfg.diag.sn"));
        this.e.setText(getString(R.string.can_text_2) + strH);
        this.f.setText(getString(R.string.can_text_3) + SystemProperties.get("ro.vendor.bosch.boot.ver"));
        this.g.setText(getString(R.string.can_text_4));
    }

    public final void initView() {
        this.c = (ListView) findViewById(R.id.list_appinfo);
        this.d = (TextView) findViewById(R.id.tv_sn_info);
        this.e = (TextView) findViewById(R.id.tv_imei_info);
        this.f = (TextView) findViewById(R.id.tv_boot_info);
        this.g = (TextView) findViewById(R.id.tv_dsp_info);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_can);
        Context applicationContext = getApplicationContext();
        this.i = applicationContext;
        this.h = BYDAutoVersionDevice.getInstance(applicationContext);
        initView();
        initData();
    }
}
