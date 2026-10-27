package com.byd.logtools.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.IHwBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.byd.logtools.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import h.a.a.a.d;
import h.a.a.a.e;
import h.a.a.a.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LogLevelConfigActivity extends BydBaseActivity implements RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static b f3000b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    TextView f3001c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    RadioGroup f3002d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    RadioButton f3003e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    RadioButton f3004f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    RadioButton f3005g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    RadioButton f3006h;
    RadioButton i;
    TextView j;
    RadioGroup k;
    RadioButton l;
    RadioButton m;
    private TextView n;
    private RadioGroup o;
    private RadioButton p;
    private RadioButton q;
    private RadioButton r;
    private RadioButton s;
    private RadioButton t;
    private d u;
    private int v = 5;
    private boolean w = false;

    class a extends e.a {
        a() {
        }

        @Override // h.a.a.a.e
        public IHwBinder asBinder() {
            return this;
        }

        @Override // h.a.a.a.e
        public void e() throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void h(boolean z) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void j(String str, h.a.a.a.b bVar) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void k(boolean z) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void n(boolean z) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void o(String str) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void s(String str) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void t(ArrayList<g> arrayList) throws RemoteException {
            Log.e("ConfigurationLogActivity", "reportGetLogLevel arrayList = " + arrayList);
            Message message = new Message();
            message.obj = arrayList;
            message.what = 1003;
            LogLevelConfigActivity.f3000b.sendMessage(message);
        }

        @Override // h.a.a.a.e
        public void u(boolean z) throws RemoteException {
        }

        @Override // h.a.a.a.e
        public void w(boolean z) throws RemoteException {
            Log.e("ConfigurationLogActivity", "reportSetLogLevel b = " + z);
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<LogLevelConfigActivity> f3008a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        LogLevelConfigActivity f3009b;

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ArrayList f3011b;

            a(ArrayList arrayList) {
                this.f3011b = arrayList;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Log.e("ConfigurationLogActivity", "mBoschLogService getLogLevel = " + LogLevelConfigActivity.this.u.A(this.f3011b) + ", arrayList.size()" + this.f3011b.size() + ", arrayList " + this.f3011b.get(0));
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }

        /* JADX INFO: renamed from: com.byd.logtools.activity.LogLevelConfigActivity$b$b, reason: collision with other inner class name */
        class RunnableC0056b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ArrayList f3013b;

            RunnableC0056b(ArrayList arrayList) {
                this.f3013b = arrayList;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Log.e("ConfigurationLogActivity", "mBoschLogService SetLogLevel arrayLogLevelList = " + LogLevelConfigActivity.this.u.p(this.f3013b) + ", arrayLogLevelList.size()" + this.f3013b.size() + ", arrayList " + this.f3013b.get(0));
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }

        b(LogLevelConfigActivity logLevelConfigActivity) {
            this.f3008a = new WeakReference<>(logLevelConfigActivity);
            this.f3009b = logLevelConfigActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f3008a.get() == null) {
                BydLogHelper.logE("outer is null");
            }
            switch (message.what) {
                case 1001:
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(0);
                    if (LogLevelConfigActivity.this.u != null) {
                        new Thread(new a(arrayList)).start();
                    }
                    break;
                case 1002:
                    ArrayList arrayList2 = new ArrayList();
                    g gVar = new g();
                    gVar.f4972a = 0;
                    gVar.f4973b = ((Integer) message.obj).intValue();
                    arrayList2.add(gVar);
                    if (LogLevelConfigActivity.this.u != null) {
                        new Thread(new RunnableC0056b(arrayList2)).start();
                    }
                    break;
                case 1003:
                    ArrayList<g> arrayList3 = (ArrayList) message.obj;
                    if (arrayList3 != null && arrayList3.size() != 0) {
                        boolean z = true;
                        for (g gVar2 : arrayList3) {
                            Log.e("ConfigurationLogActivity", "reportGetLogLevel info.module_name  = " + gVar2.f4972a + ", info.level = " + gVar2.f4973b);
                            if (z) {
                                LogLevelConfigActivity.this.m(gVar2.f4973b);
                                z = false;
                            }
                        }
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(int i) {
        Log.e("ConfigurationLogActivity", "initQnxLevel level = " + i);
        this.w = true;
        if (i == 7) {
            this.p.setChecked(true);
            this.n.setText("VERBOSE");
        } else if (i == 6) {
            this.q.setChecked(true);
            this.n.setText("DEBUG");
        } else if (i == 5) {
            this.r.setChecked(true);
            this.n.setText("INFO");
        } else if (i == 3) {
            this.s.setChecked(true);
            this.n.setText("WARN");
        } else if (i == 2) {
            this.t.setChecked(true);
            this.n.setText("ERROR");
        } else {
            this.n.setText("UNKNOWN");
            this.o.clearCheck();
        }
        this.w = false;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    @SuppressLint({"NonConstantResourceId"})
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Message message = new Message();
        message.obj = Integer.valueOf(this.v);
        message.what = 1002;
        Log.w("ConfigurationLogActivity", "initQnxLog = " + this.w);
        switch (i) {
            case R.id.boschVehicleHal_radio_debug /* 2131296344 */:
                Log.e("ConfigurationLogActivity", "persist.log.tag.BoschVehicleHal d ：" + this.l.getText().toString());
                SystemProperties.set("persist.log.tag.BoschVehicleHal", "D");
                this.j.setText(this.l.getText().toString());
                break;
            case R.id.boschVehicleHal_radio_info /* 2131296345 */:
                Log.e("ConfigurationLogActivity", "persist.log.tag.BoschVehicleHal i ：" + this.m.getText().toString());
                SystemProperties.set("persist.log.tag.BoschVehicleHal", "I");
                this.j.setText(this.m.getText().toString());
                break;
            default:
                switch (i) {
                    case R.id.qnx_radio_debug /* 2131296559 */:
                        if (!this.w) {
                            Log.e("ConfigurationLogActivity", "qnx_radio_debug：" + this.q.getText().toString() + ", LogLevel.LOG_DEBUG = 6");
                            this.n.setText(this.q.getText().toString());
                            message.obj = 6;
                            f3000b.sendMessage(message);
                        } else {
                            this.w = false;
                        }
                        break;
                    case R.id.qnx_radio_error /* 2131296560 */:
                        if (!this.w) {
                            Log.e("ConfigurationLogActivity", "qnx_radio_error：" + this.t.getText().toString() + ", LogLevel.LOG_ERROR = 2");
                            this.n.setText(this.t.getText().toString());
                            message.obj = 2;
                            f3000b.sendMessage(message);
                        } else {
                            this.w = false;
                        }
                        break;
                    case R.id.qnx_radio_info /* 2131296561 */:
                        if (!this.w) {
                            Log.e("ConfigurationLogActivity", "qnx_radio_info：" + this.r.getText().toString() + ", LogLevel.LOG_INFO = 5");
                            this.n.setText(this.r.getText().toString());
                            message.obj = 5;
                            f3000b.sendMessage(message);
                        } else {
                            this.w = false;
                        }
                        break;
                    case R.id.qnx_radio_verbose /* 2131296562 */:
                        if (!this.w) {
                            Log.e("ConfigurationLogActivity", "qnx_radio_verbose：" + this.p.getText().toString() + ", LogLevel.LOG_VERBOSE = 7");
                            this.n.setText(this.f3003e.getText().toString());
                            message.obj = 7;
                            f3000b.sendMessage(message);
                        } else {
                            this.w = false;
                        }
                        break;
                    case R.id.qnx_radio_warn /* 2131296563 */:
                        if (!this.w) {
                            Log.e("ConfigurationLogActivity", "qnx_radio_warn：" + this.s.getText().toString() + ", LogLevel.LOG_WARN = 3");
                            this.n.setText(this.s.getText().toString());
                            message.obj = 3;
                            f3000b.sendMessage(message);
                        } else {
                            this.w = false;
                        }
                        break;
                    default:
                        switch (i) {
                            case R.id.radio_debug /* 2131296568 */:
                                Log.e("ConfigurationLogActivity", "persist.log.tag：" + this.f3004f.getText().toString());
                                SystemProperties.set("persist.log.tag", "D");
                                this.f3001c.setText(this.f3004f.getText().toString());
                                break;
                            case R.id.radio_error /* 2131296569 */:
                                Log.e("ConfigurationLogActivity", "persist.log.tag：" + this.i.getText().toString());
                                SystemProperties.set("persist.log.tag", "E");
                                this.f3001c.setText(this.i.getText().toString());
                                break;
                            case R.id.radio_info /* 2131296570 */:
                                Log.e("ConfigurationLogActivity", "persist.log.tag：" + this.f3005g.getText().toString());
                                SystemProperties.set("persist.log.tag", "I");
                                this.f3001c.setText(this.f3005g.getText().toString());
                                break;
                            case R.id.radio_verbose /* 2131296571 */:
                                Log.e("ConfigurationLogActivity", "persist.log.tag：" + this.f3003e.getText().toString());
                                SystemProperties.set("persist.log.tag", "V");
                                this.f3001c.setText(this.f3003e.getText().toString());
                                break;
                            case R.id.radio_warn /* 2131296572 */:
                                Log.e("ConfigurationLogActivity", "persist.log.tag：" + this.f3006h.getText().toString());
                                SystemProperties.set("persist.log.tag", "W");
                                this.f3001c.setText(this.f3006h.getText().toString());
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (i("ConfigurationLogActivity")) {
            finish();
            return;
        }
        requestWindowFeature(1);
        getWindow().clearFlags(1024);
        setContentView(R.layout.activity_log_level_config);
        this.f3001c = (TextView) findViewById(R.id.tv_current_log_level);
        this.f3002d = (RadioGroup) findViewById(R.id.radioGroup);
        this.f3003e = (RadioButton) findViewById(R.id.radio_verbose);
        this.f3004f = (RadioButton) findViewById(R.id.radio_debug);
        this.f3005g = (RadioButton) findViewById(R.id.radio_info);
        this.f3006h = (RadioButton) findViewById(R.id.radio_warn);
        this.i = (RadioButton) findViewById(R.id.radio_error);
        this.f3002d.setOnCheckedChangeListener(this);
        this.n = (TextView) findViewById(R.id.tv_qnx_current_log_level);
        this.o = (RadioGroup) findViewById(R.id.qnx_radioGroup);
        this.p = (RadioButton) findViewById(R.id.qnx_radio_verbose);
        this.q = (RadioButton) findViewById(R.id.qnx_radio_debug);
        this.r = (RadioButton) findViewById(R.id.qnx_radio_info);
        this.s = (RadioButton) findViewById(R.id.qnx_radio_warn);
        this.t = (RadioButton) findViewById(R.id.qnx_radio_error);
        this.o.setOnCheckedChangeListener(this);
        this.j = (TextView) findViewById(R.id.tv_boschVehicleHal_log_level);
        this.k = (RadioGroup) findViewById(R.id.boschVehicleHal_radioGroup);
        this.l = (RadioButton) findViewById(R.id.boschVehicleHal_radio_debug);
        this.m = (RadioButton) findViewById(R.id.boschVehicleHal_radio_info);
        this.k.setOnCheckedChangeListener(this);
        String str = SystemProperties.get("persist.log.tag");
        Log.e("ConfigurationLogActivity", "persist.log.tag：logLevel = " + str);
        if ("V".equalsIgnoreCase(str) || "VERBOSE".equalsIgnoreCase(str)) {
            this.f3003e.setChecked(true);
            this.f3001c.setText("VERBOSE");
        } else if ("D".equalsIgnoreCase(str) || "DEBUG".equalsIgnoreCase(str)) {
            this.f3004f.setChecked(true);
            this.f3001c.setText("DEBUG");
        } else if ("I".equalsIgnoreCase(str) || "INFO".equalsIgnoreCase(str)) {
            this.f3005g.setChecked(true);
            this.f3001c.setText("INFO");
        } else if ("W".equalsIgnoreCase(str) || "WARN".equalsIgnoreCase(str)) {
            this.f3006h.setChecked(true);
            this.f3001c.setText("WARN");
        } else if ("E".equalsIgnoreCase(str) || "ERROR".equalsIgnoreCase(str)) {
            this.i.setChecked(true);
            this.f3001c.setText("ERROR");
        } else {
            this.f3001c.setText("UNKNOWN");
            this.f3002d.clearCheck();
        }
        String str2 = SystemProperties.get("persist.log.tag.BoschVehicleHal");
        Log.e("ConfigurationLogActivity", "persist.log.tag.BoschVehicleHal = " + str2);
        if ("D".equalsIgnoreCase(str2) || "DEBUG".equalsIgnoreCase(str2)) {
            this.l.setChecked(true);
            this.j.setText("DEBUG");
        } else if ("I".equalsIgnoreCase(str2) || "INFO".equalsIgnoreCase(str2)) {
            this.m.setChecked(true);
            this.j.setText("INFO");
        } else {
            this.j.setText("UNKNOWN");
            this.k.clearCheck();
        }
        f3000b = new b(this);
        try {
            d dVarD = d.d();
            this.u = dVarD;
            if (dVarD != null) {
                dVarD.f(new a());
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        Message message = new Message();
        message.what = 1001;
        f3000b.sendMessage(message);
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (i("ConfigurationLogActivity")) {
            finish();
            return;
        }
        b bVar = f3000b;
        if (bVar != null) {
            if (bVar.hasMessages(1001)) {
                f3000b.removeMessages(1001);
            }
            if (f3000b.hasMessages(1002)) {
                f3000b.removeMessages(1002);
            }
            if (f3000b.hasMessages(1003)) {
                f3000b.removeMessages(1003);
            }
            f3000b = null;
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (i("ConfigurationLogActivity")) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (i("ConfigurationLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (i("ConfigurationLogActivity")) {
            finish();
        }
    }
}
