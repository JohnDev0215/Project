package com.byd.secretcode.activity;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.cg;
import defpackage.eg;
import defpackage.tm;
import defpackage.um;
import defpackage.vm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class NetworkListActivity extends AppCompatActivity implements View.OnClickListener, cg.b {
    public ImageView b;
    public String f;
    public int g;
    public AbsBYDAutoTestListener j;
    public RecyclerView k;
    public cg l;
    public LinearLayoutManager m;
    public Dialog n;
    public um o;
    public h p;
    public int r;
    public int t;
    public int u;
    public BYDAutoTestDevice c = null;
    public ArrayList<eg> d = new ArrayList<>();
    public ArrayList<NetworkOperator> e = new ArrayList<>();
    public int h = 0;
    public String i = "00000";
    public String q = "";
    public String s = "";
    public boolean v = false;
    public boolean w = false;
    public Timer x = null;

    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (NetworkListActivity.this.v) {
                NetworkListActivity.this.H();
            } else if (NetworkListActivity.this.c != null) {
                NetworkListActivity.this.c.reqOperatorInfo();
            }
        }
    }

    public class b extends AbsBYDAutoTestListener {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                NetworkListActivity.this.I();
            }
        }

        /* JADX INFO: renamed from: com.byd.secretcode.activity.NetworkListActivity$b$b, reason: collision with other inner class name */
        public class RunnableC0007b implements Runnable {
            public RunnableC0007b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                NetworkListActivity.this.I();
            }
        }

        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onManuallyStationedNetworkChange(String str, int i, int i2, String str2, int i3) {
            super.onManuallyStationedNetworkChange(str, i, i2, str2, i3);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onManuallyStationedNetworkChange =  state : " + i2 + " , data : " + str + " , curNetType: " + i + " , lastData : " + str2 + " , lastNetType : " + i3);
            NetworkListActivity.this.w = false;
            NetworkListActivity.this.q = str;
            NetworkListActivity.this.r = i;
            NetworkListActivity.this.s = str2;
            NetworkListActivity.this.t = i3;
            NetworkListActivity.this.u = i2;
            NetworkListActivity.this.c.reqNetworkOperatorMode();
            NetworkListActivity.this.runOnUiThread(new RunnableC0007b());
            if (i2 == 0) {
                NetworkListActivity networkListActivity = NetworkListActivity.this;
                networkListActivity.L(networkListActivity.q, NetworkListActivity.this.r, NetworkListActivity.this.s, NetworkListActivity.this.t);
            } else {
                NetworkListActivity networkListActivity2 = NetworkListActivity.this;
                networkListActivity2.K(networkListActivity2.q, NetworkListActivity.this.r, NetworkListActivity.this.s, NetworkListActivity.this.t);
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onOperatorInfoChange(ArrayList<NetworkOperator> arrayList) {
            super.onOperatorInfoChange(arrayList);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onOperatorInfoChange =  , list.size : " + arrayList.size());
            NetworkListActivity.this.v = true;
            NetworkListActivity.this.e.clear();
            NetworkListActivity.this.e.addAll(arrayList);
            NetworkListActivity networkListActivity = NetworkListActivity.this;
            networkListActivity.N(networkListActivity.e);
            NetworkListActivity.this.runOnUiThread(new a());
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NetworkListActivity.this.l.h();
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NetworkListActivity.this.l.h();
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NetworkListActivity.this.l.h();
        }
    }

    public class f implements DialogInterface.OnDismissListener {
        public f() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            BydLogHelper.logD("NetworkListActivity", "showLoadingView onDismiss  messageId ： ");
            NetworkListActivity.this.H();
        }
    }

    public class g implements DialogInterface.OnKeyListener {
        public g() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            NetworkListActivity.this.O();
            return false;
        }
    }

    public class h extends vm.a {

        public class a implements Runnable {
            public final /* synthetic */ tm b;

            public a(tm tmVar) {
                this.b = tmVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                if ("00000".equals(NetworkListActivity.this.f) || NetworkListActivity.this.w) {
                    NetworkListActivity networkListActivity = NetworkListActivity.this;
                    networkListActivity.K(networkListActivity.q, NetworkListActivity.this.r, NetworkListActivity.this.s, NetworkListActivity.this.t);
                    return;
                }
                NetworkListActivity networkListActivity2 = NetworkListActivity.this;
                networkListActivity2.s = networkListActivity2.q;
                NetworkListActivity networkListActivity3 = NetworkListActivity.this;
                networkListActivity3.t = networkListActivity3.r;
                NetworkListActivity.this.q = this.b.m;
                byte b = this.b.a;
                if (b >= 1) {
                    NetworkListActivity.this.r = b - 1;
                }
                NetworkListActivity networkListActivity4 = NetworkListActivity.this;
                networkListActivity4.L(networkListActivity4.q, NetworkListActivity.this.r, NetworkListActivity.this.s, NetworkListActivity.this.t);
            }
        }

        public h() {
        }

        @Override // defpackage.vm
        public void d(byte b) throws RemoteException {
        }

        @Override // defpackage.vm
        public void f(byte b) throws RemoteException {
        }

        @Override // defpackage.vm
        public void g(byte b) throws RemoteException {
        }

        @Override // defpackage.vm
        public void k(String str) throws RemoteException {
        }

        @Override // defpackage.vm
        public void l(tm tmVar) throws RemoteException {
            NetworkListActivity.this.f = tmVar.m;
            NetworkListActivity.this.h = tmVar.a;
            NetworkListActivity.this.g = tmVar.b;
            if (!NetworkListActivity.this.i.equals(NetworkListActivity.this.f)) {
                BydLogHelper.logD("onDynamicInfoChanged : " + NetworkListActivity.this.w);
                NetworkListActivity.this.runOnUiThread(new a(tmVar));
            }
            NetworkListActivity networkListActivity = NetworkListActivity.this;
            networkListActivity.i = networkListActivity.f;
        }

        @Override // defpackage.vm
        public void n(byte b) throws RemoteException {
            BydLogHelper.logD("onApn2Changed b:" + ((int) b));
        }

        @Override // defpackage.vm
        public void o(byte b) throws RemoteException {
        }

        public /* synthetic */ h(NetworkListActivity networkListActivity, a aVar) {
            this();
        }
    }

    public final void G() {
        this.v = false;
        if (this.x == null) {
            Timer timer = new Timer();
            this.x = timer;
            timer.schedule(new a(), 300000L);
        }
    }

    public final void H() {
        Timer timer = this.x;
        if (timer != null) {
            timer.cancel();
            this.x = null;
        }
    }

    public void I() {
        Dialog dialog = this.n;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.n.dismiss();
        BydLogHelper.logD("NetworkListActivity", "reqOperatorInfo = hideLoadingView ");
    }

    public final void J() {
        b bVar = new b();
        this.j = bVar;
        this.c.registerListener((AbsBYDAutoTestListener) bVar);
    }

    public final void K(String str, int i, String str2, int i2) {
        BydLogHelper.logD("NetworkListActivity", "refreshMaunallyFail =  state : " + this.g + " , data : " + str + " , curNetType: " + i + " , lastData : " + str2 + " , lastNetType : " + i2);
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            eg egVar = this.d.get(i3);
            NetworkOperator networkOperatorC = egVar.c();
            String mccMnc = networkOperatorC.getMccMnc();
            int sigLevel = networkOperatorC.getSigLevel();
            if (str.equals(mccMnc) && sigLevel == i) {
                egVar.d(3);
            } else if (str2.equals(mccMnc) && sigLevel == i2) {
                egVar.d(2);
            } else {
                egVar.d(0);
            }
        }
        runOnUiThread(new c());
    }

    public final void L(String str, int i, String str2, int i2) {
        BydLogHelper.logD("NetworkListActivity", "refreshMaunallySuccess =  state : " + this.g + " , data : " + str + " , curNetType: " + i + " , lastData : " + str2 + " , lastNetType : " + i2);
        this.f = str;
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            eg egVar = this.d.get(i3);
            NetworkOperator networkOperatorC = egVar.c();
            String mccMnc = networkOperatorC.getMccMnc();
            int sigLevel = networkOperatorC.getSigLevel();
            BydLogHelper.logD("NetworkListActivity", "refreshMaunallySuccess =  netName : " + mccMnc + " , nettype : " + sigLevel);
            if (str.equals(mccMnc) && i == sigLevel) {
                egVar.d(1);
                if (i3 != 0) {
                    Collections.swap(this.d, 0, i3);
                }
            } else if (str2.equals(mccMnc) && i2 == sigLevel) {
                egVar.d(2);
            } else {
                egVar.d(0);
            }
        }
        runOnUiThread(new d());
    }

    public final void M() {
        try {
            this.f = this.o.j();
            this.g = this.o.i();
            BydLogHelper.logD("NetworkListActivity", "refreshNetWorkInfo mccMnc = " + this.f + " , state : " + this.g);
        } catch (Exception e2) {
            BydLogHelper.logE("e:" + e2);
            e2.printStackTrace();
        }
    }

    public final void N(List<NetworkOperator> list) {
        ArrayList<eg> arrayList = this.d;
        if (arrayList != null && arrayList.size() > 0) {
            this.d.clear();
        }
        M();
        for (int i = 0; i < list.size(); i++) {
            String str = this.f;
            if (str != null && str.equals(list.get(i).getMccMnc())) {
                eg egVar = new eg();
                if (list.get(i).getSigLevel() == this.g - 1) {
                    BydLogHelper.logD("NetworkListActivity", "add network list header =  , mMccMnc  +  : " + list.get(i).getMccMnc() + " , sigLevel : " + list.get(i).getSigLevel() + " , enable : " + list.get(i).getEnable());
                    egVar.e(list.get(i));
                    egVar.d(1);
                    this.d.add(egVar);
                }
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            BydLogHelper.logD("NetworkListActivity", "onOperatorInfoChange =  , mMccMnc  +  : " + list.get(i2).getMccMnc() + " , sigLevel : " + list.get(i2).getSigLevel() + " , enable : " + list.get(i2).getEnable());
            String str2 = this.f;
            if (str2 == null || !str2.equals(list.get(i2).getMccMnc()) || list.get(i2).getSigLevel() != this.g - 1) {
                eg egVar2 = new eg();
                egVar2.e(list.get(i2));
                egVar2.d(0);
                this.d.add(egVar2);
            }
        }
        runOnUiThread(new e());
    }

    public final void O() {
        BydLogHelper.logD("NetworkListActivity", "returnBackActivity   mMccMnc : " + this.f);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, (Class<?>) AutomaticallyActivity.class));
        startActivity(intent);
        finish();
    }

    public void P(int i, boolean z) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView((RelativeLayout) LayoutInflater.from(this).inflate(R.layout.network_selected_loading_alert_dialog, (ViewGroup) null)).create();
        this.n = alertDialogCreate;
        alertDialogCreate.show();
        this.n.setOnDismissListener(new f());
        if (z) {
            this.n.setOnKeyListener(new g());
        }
        ImageView imageView = (ImageView) this.n.findViewById(R.id.network_auto_select_loading);
        ((TextView) this.n.findViewById(R.id.network_auto_select_tips_tv)).setText(i);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
        objectAnimatorOfFloat.setDuration(1000L);
        objectAnimatorOfFloat.setRepeatCount(-1);
        objectAnimatorOfFloat.start();
    }

    @Override // cg.b
    public void e(String str, int i) {
        this.c.setManuallyStationedNetwork(str, i);
        this.w = true;
        P(R.string.str_network_net_registering, false);
        BydLogHelper.logD("NetworkListActivity", "setManuallyStationedNetwork =  , networkName : " + str + " , networkLevel : " + i);
    }

    public final void initView() {
        this.b = (ImageView) findViewById(R.id.network_choice_back_iv);
        this.k = (RecyclerView) findViewById(R.id.network_choice_list_rv);
        this.b.setOnClickListener(this);
        this.l = new cg(this.d, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.m = linearLayoutManager;
        this.k.setLayoutManager(linearLayoutManager);
        this.k.setAdapter(this.l);
        this.l.x(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BydLogHelper.logD("NetworkListActivity", "onBackPressed int " + getClass().getSimpleName());
        O();
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.network_choice_back_iv) {
            O();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_network_list);
        initView();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.c.unregisterListener(this.j);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        BydLogHelper.logD("NetworkListActivity", "onPause()");
        H();
        this.w = false;
        um umVar = this.o;
        if (umVar != null) {
            try {
                umVar.c(this.p);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BYDAutoTestDevice bYDAutoTestDevice = BYDAutoTestDevice.getInstance(getApplication());
        this.c = bYDAutoTestDevice;
        bYDAutoTestDevice.reqOperatorInfo();
        G();
        P(R.string.str_network_net_request_info, true);
        BydLogHelper.logD("NetworkListActivity", "reqOperatorInfo = on Resume ");
        this.f = getIntent().getStringExtra("mMccMnc");
        this.g = getIntent().getIntExtra("m_mcc_mnc_state", 0);
        try {
            um umVarP = um.p();
            this.o = umVarP;
            this.f = umVarP.j();
            h hVar = new h(this, null);
            this.p = hVar;
            this.o.b(hVar);
            BydLogHelper.logD("NetworkListActivity", "mccMnc =" + this.f);
        } catch (Exception e2) {
            BydLogHelper.logE("e:" + e2);
            e2.printStackTrace();
        }
        J();
    }
}
