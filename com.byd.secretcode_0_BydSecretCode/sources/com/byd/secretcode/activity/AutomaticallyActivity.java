package com.byd.secretcode.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;
import com.byd.secretcode.view.BydSwitch;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import defpackage.ag;
import defpackage.tm;
import defpackage.um;
import defpackage.vm;
import defpackage.wc;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AutomaticallyActivity extends Activity implements View.OnClickListener {
    public static long b;
    public int B;
    public um c;
    public j d;
    public AbsBYDAutoTestListener i;
    public ag j;
    public List<String> k;
    public RecyclerView l;
    public Button t;
    public BydSwitch u;
    public TextView v;
    public TextView w;
    public LinearLayout x;
    public Dialog y;
    public Button z;
    public TextView e = null;
    public Button f = null;
    public String g = "";
    public BYDAutoTestDevice h = null;
    public final int m = 1001;
    public final int n = 1002;
    public final int o = 1003;
    public final int p = 1004;
    public final int q = 1005;
    public final int r = 1006;
    public int s = 0;
    public int A = 0;
    public final Handler C = new e();

    public class a extends RecyclerView.t {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            super.b(recyclerView, i, i2);
        }
    }

    public class b extends AbsBYDAutoTestListener {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AutomaticallyActivity automaticallyActivity = AutomaticallyActivity.this;
                automaticallyActivity.t(automaticallyActivity.B);
            }
        }

        /* JADX INFO: renamed from: com.byd.secretcode.activity.AutomaticallyActivity$b$b, reason: collision with other inner class name */
        public class RunnableC0003b implements Runnable {
            public final /* synthetic */ int b;

            public RunnableC0003b(int i) {
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                AutomaticallyActivity.this.v(this.b == 0);
            }
        }

        public class c implements Runnable {
            public final /* synthetic */ int b;

            public c(int i) {
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                AutomaticallyActivity.this.m(1005);
                AutomaticallyActivity.this.v(this.b == 0);
            }
        }

        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onAutomaticNetworkChange(int i) {
            super.onAutomaticNetworkChange(i);
            BydLogHelper.logD("onAutomaticNetworkChange state : " + i);
            AutomaticallyActivity.this.runOnUiThread(new c(i));
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onNetworkOperatorList(ArrayList<String> arrayList) {
            super.onNetworkOperatorList(arrayList);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onNetworkOperatorList code =" + arrayList.size());
            AutomaticallyActivity.this.k.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                AutomaticallyActivity.this.k.add(arrayList.get(i));
            }
            AutomaticallyActivity.this.C.removeMessages(1001);
            AutomaticallyActivity.this.C.sendEmptyMessageDelayed(1001, 1000L);
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onNetworkOperatorModeChange(int i) {
            super.onNetworkOperatorModeChange(i);
            BydLogHelper.logD("onNetworkOperatorModeChange state : " + i);
            AutomaticallyActivity.this.runOnUiThread(new RunnableC0003b(i));
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onOperatorInfoChange(ArrayList<NetworkOperator> arrayList) {
            super.onOperatorInfoChange(arrayList);
            BydLogHelper.logD("onOperatorInfoChange list size : " + arrayList.size());
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onSimCardStateChange(int i) {
            super.onSimCardStateChange(i);
            BydLogHelper.logD("onSimCardStateChange status : " + i);
            AutomaticallyActivity.this.B = i;
            AutomaticallyActivity.this.runOnUiThread(new a());
        }
    }

    public class c implements CompoundButton.OnCheckedChangeListener {
        public c() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            BydLogHelper.logD("onCheckedChanged isChecked : " + z);
            if (!z) {
                AutomaticallyActivity.this.C.removeMessages(1004);
                AutomaticallyActivity.this.C.sendEmptyMessage(1004);
                AutomaticallyActivity.this.p();
            } else {
                AutomaticallyActivity.this.C.removeMessages(1003);
                AutomaticallyActivity.this.C.sendEmptyMessage(1003);
                AutomaticallyActivity.this.C.sendEmptyMessageDelayed(1005, 120000L);
                AutomaticallyActivity.this.h.setAutomaticNetwork();
            }
        }
    }

    public class d implements DialogInterface.OnClickListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    public class e extends Handler {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AutomaticallyActivity automaticallyActivity = AutomaticallyActivity.this;
                automaticallyActivity.t(automaticallyActivity.B);
            }
        }

        public e() {
        }

        @Override // android.os.Handler
        @SuppressLint({"HandlerLeak"})
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    AutomaticallyActivity.this.j.w(AutomaticallyActivity.this.k);
                    AutomaticallyActivity.this.j.h();
                    break;
                case 1002:
                    if (AutomaticallyActivity.this.h != null) {
                        AutomaticallyActivity.this.h.getNetworkOperatorList();
                    }
                    break;
                case 1003:
                    AutomaticallyActivity.this.v(true);
                    AutomaticallyActivity.this.s(1005, R.string.str_network_select_auto_register);
                    break;
                case 1004:
                    AutomaticallyActivity.this.v(false);
                    break;
                case 1005:
                    AutomaticallyActivity.this.m(1005);
                    if (AutomaticallyActivity.this.h != null) {
                        AutomaticallyActivity.this.h.reqNetworkOperatorMode();
                    }
                    AutomaticallyActivity.this.v(false);
                    Toast.makeText(AutomaticallyActivity.this, "打开自动驻网请求超时", 0).show();
                    break;
                case 1006:
                    BydLogHelper.logD("AutomaticallyActivity", "MESSAGE_SHOW_SIM_DIALOG  simState : " + AutomaticallyActivity.this.B);
                    AutomaticallyActivity.this.runOnUiThread(new a());
                    break;
            }
        }
    }

    public class f implements View.OnClickListener {
        public final /* synthetic */ Dialog b;

        public f(Dialog dialog) {
            this.b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toast.makeText(AutomaticallyActivity.this.getApplicationContext(), "ok", 0).show();
            this.b.dismiss();
            AutomaticallyActivity.this.finish();
        }
    }

    public class g implements View.OnClickListener {
        public final /* synthetic */ Dialog b;

        public g(Dialog dialog) {
            this.b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toast.makeText(AutomaticallyActivity.this.getApplicationContext(), "cancel", 0).show();
            this.b.dismiss();
        }
    }

    public class h implements View.OnClickListener {
        public final /* synthetic */ Dialog b;

        public h(Dialog dialog) {
            this.b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toast.makeText(AutomaticallyActivity.this.getApplicationContext(), "close", 0).show();
            this.b.dismiss();
        }
    }

    public class i implements DialogInterface.OnDismissListener {
        public final /* synthetic */ int b;

        public i(int i) {
            this.b = i;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AutomaticallyActivity.this.C.removeMessages(this.b);
            BydLogHelper.logD("AutomaticallyActivity", "showLoadingView onDismiss  messageId ： " + this.b);
        }
    }

    public class j extends vm.a {

        public class a implements Runnable {
            public final /* synthetic */ tm b;

            public a(tm tmVar) {
                this.b = tmVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (AutomaticallyActivity.this.w != null) {
                    AutomaticallyActivity.this.w.setText(this.b.m);
                }
            }
        }

        public j() {
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
            AutomaticallyActivity.this.runOnUiThread(new a(tmVar));
        }

        @Override // defpackage.vm
        public void n(byte b) throws RemoteException {
            BydLogHelper.logD("onApn2Changed b:" + ((int) b));
        }

        @Override // defpackage.vm
        public void o(byte b) throws RemoteException {
        }

        public /* synthetic */ j(AutomaticallyActivity automaticallyActivity, a aVar) {
            this();
        }
    }

    public final String k(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? getString(R.string.str_network_sim_state_normal) : getString(R.string.str_network_sim_state_netregister) : getString(R.string.str_network_sim_state_countrycode) : getString(R.string.str_network_sim_state_exception) : getString(R.string.str_network_sim_state_normal);
    }

    public void l() {
        m(0);
    }

    public void m(int i2) {
        Handler handler = this.C;
        if (handler != null) {
            handler.removeMessages(i2);
            BydLogHelper.logD("AutomaticallyActivity", "hideLoadingView removeMessages  messageId ： " + i2);
        }
        Dialog dialog = this.y;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.y.dismiss();
        this.h.reqOperatorInfo();
        BydLogHelper.logD("AutomaticallyActivity", "reqOperatorInfo = hideLoadingView ");
    }

    public final void n() {
        TextView textView = (TextView) findViewById(R.id.curr_mccmnc);
        this.e = textView;
        textView.setText(getString(R.string.current_network) + this.g);
        this.l = (RecyclerView) findViewById(R.id.list_automatically);
        this.f = (Button) findViewById(R.id.ok_button);
        this.t = (Button) findViewById(R.id.btn_autoupdate);
        this.f.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.l.setLayoutManager(new LinearLayoutManager(this));
        this.l.h(new wc(this, 1));
        this.l.setAdapter(this.j);
        this.l.l(new a());
        b bVar = new b();
        this.i = bVar;
        this.h.registerListener((AbsBYDAutoTestListener) bVar);
        BydSwitch bydSwitch = (BydSwitch) findViewById(R.id.swAutoSelect);
        this.u = bydSwitch;
        bydSwitch.setOnCheckedChangeListener(new c());
        this.v = (TextView) findViewById(R.id.tvSelectNetwork1);
        this.w = (TextView) findViewById(R.id.tvSelectNetwork);
        this.x = (LinearLayout) findViewById(R.id.llSelectNetwork);
        this.z = (Button) findViewById(R.id.btnSimDiagnose);
        this.x.setOnClickListener(this);
        this.z.setOnClickListener(this);
        BydLogHelper.logD("AutomaticallyActivity", "reqOperatorInfo  init  AutomaticallyActivity : ");
    }

    public boolean o(long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j3 = b;
        long j4 = jCurrentTimeMillis - j3;
        if (j4 < 0) {
            return false;
        }
        if (j3 > 0 && j4 < j2) {
            return true;
        }
        b = jCurrentTimeMillis;
        return false;
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 101 && i3 == 102) {
            BydLogHelper.logD("AutomaticallyActivity", "reqNetworkOperatorMode in onActivityResult ");
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        BydSwitch bydSwitch = this.u;
        if (bydSwitch != null && !bydSwitch.isChecked()) {
            r();
            return;
        }
        Dialog dialog = this.y;
        if (dialog != null && dialog.isShowing()) {
            l();
            this.C.removeCallbacksAndMessages(null);
        }
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSimDiagnose /* 2131296382 */:
                this.h.reqSimCardState();
                break;
            case R.id.btn_autoupdate /* 2131296383 */:
                u();
                break;
            case R.id.llSelectNetwork /* 2131296581 */:
                p();
                break;
            case R.id.ok_button /* 2131296675 */:
                BydLogHelper.logD("AutomaticallyActivity", "ok_button111 size =" + this.k.size() + ",mListMccMnc =" + this.k);
                if (!o(1000L)) {
                    BydLogHelper.logD("AutomaticallyActivity", "ok_button222 size =" + this.k.size() + ",mListMccMnc =" + this.k);
                    if (this.k.size() > 0 && this.k.size() > this.s) {
                        BydLogHelper.logD("AutomaticallyActivity", "ok_button mListMccMnc.get(mSelectItem) =" + this.k.get(this.s));
                        this.h.setNetworkOperator(this.k.get(this.s));
                        this.C.sendEmptyMessageDelayed(1002, 1000L);
                        break;
                    }
                } else {
                    ToastUtils.show(this, getString(R.string.str_automatically));
                    break;
                }
                break;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_automatically);
        this.h = BYDAutoTestDevice.getInstance(this);
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        this.j = new ag(this, arrayList);
        n();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logD("AutomaticallyActivity", "onDestroy()");
        BYDAutoTestDevice bYDAutoTestDevice = this.h;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.unregisterListener(this.i);
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        BydLogHelper.logD("AutomaticallyActivity", "onPause()");
        um umVar = this.c;
        if (umVar != null) {
            try {
                umVar.c(this.d);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        BydLogHelper.logD("AutomaticallyActivity", "onResume()");
        try {
            um umVarP = um.p();
            this.c = umVarP;
            this.g = umVarP.j();
            j jVar = new j(this, null);
            this.d = jVar;
            this.c.b(jVar);
            BydLogHelper.logD("AutomaticallyActivity", "mccMnc =" + this.g);
        } catch (Exception e2) {
            BydLogHelper.logE("e:" + e2);
            e2.printStackTrace();
        }
        TextView textView = this.w;
        if (textView != null) {
            textView.setText(this.g);
        }
        this.h.reqNetworkOperatorMode();
    }

    public final void p() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, (Class<?>) NetworkListActivity.class));
        startActivity(intent);
        BydLogHelper.logD("AutomaticallyActivity", "startActivityForResult =  , state:" + this.A + " , mMccMnc : " + this.g);
    }

    public void q(int i2) {
        this.s = i2;
    }

    public final void r() {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.network_selected_exit_alert_dialog, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView(relativeLayout).create();
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.network_auto_select_close);
        ((Button) relativeLayout.findViewById(R.id.network_selected_exit_confirm_button)).setOnClickListener(new f(alertDialogCreate));
        ((Button) relativeLayout.findViewById(R.id.network_selected_exit_cancel_button)).setOnClickListener(new g(alertDialogCreate));
        imageView.setOnClickListener(new h(alertDialogCreate));
    }

    public void s(int i2, int i3) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView((RelativeLayout) LayoutInflater.from(this).inflate(R.layout.network_selected_loading_alert_dialog, (ViewGroup) null)).create();
        this.y = alertDialogCreate;
        alertDialogCreate.show();
        this.y.setOnDismissListener(new i(i2));
        ImageView imageView = (ImageView) this.y.findViewById(R.id.network_auto_select_loading);
        ((TextView) this.y.findViewById(R.id.network_auto_select_tips_tv)).setText(i3);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
        objectAnimatorOfFloat.setDuration(1000L);
        objectAnimatorOfFloat.setRepeatCount(-1);
        objectAnimatorOfFloat.start();
    }

    public final void t(int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(k(i2));
        builder.setPositiveButton(R.string.confirm, new d());
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.show();
    }

    public final void u() {
        BydLogHelper.logD("AutomaticallyActivity", "startSimInfoUploadService");
        Intent intent = new Intent("com.byd.traffic_monitor.SIM_INFO_UPLOAD");
        intent.setPackage("com.byd.trafficmonitor");
        startService(intent);
    }

    public final void v(boolean z) {
        BydLogHelper.logD("updateAutoSelect state:" + z);
        if (z) {
            this.u.setEverTouch(true);
            this.u.b(true, false);
            this.x.setClickable(false);
            this.w.setTextColor(getResources().getColor(R.color.auto_study_background_color, null));
            this.v.setTextColor(getResources().getColor(R.color.auto_study_background_color, null));
            return;
        }
        this.u.setEverTouch(true);
        this.u.b(false, false);
        this.x.setClickable(true);
        this.w.setTextColor(getResources().getColor(R.color.black, null));
        this.v.setTextColor(getResources().getColor(R.color.black, null));
    }
}
