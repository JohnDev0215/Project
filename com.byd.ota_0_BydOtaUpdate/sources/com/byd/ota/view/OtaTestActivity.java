package com.byd.ota.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import c.a.b.d.e;
import c.a.b.d.l;
import c.a.h.b.a;
import c.a.h.b.b;
import com.byd.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class OtaTestActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public static final String R3 = OtaTestActivity.class.getSimpleName();
    public c.a.h.b.b S3;
    public String[] T3;
    public String[] U3;
    public String[] V3;
    public TextView W3;
    public TextView X3;
    public TextView Y3;
    public TextView Z3;
    public EditText a4;
    public int b4;
    public ServiceConnection c4 = new a();
    public c.a.h.b.a d4 = new b();

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OtaTestActivity.this.S3 = b.a.B(iBinder);
            try {
                OtaTestActivity.this.S3.n(OtaTestActivity.this.d4);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                OtaTestActivity.this.S3.u(OtaTestActivity.this.d4);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            OtaTestActivity.this.S3 = null;
        }
    }

    public class b extends a.AbstractBinderC0057a {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void E(String str) {
            OtaTestActivity.this.Z3.setText(str);
        }

        @Override // c.a.h.b.a
        public void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException {
        }

        @Override // c.a.h.b.a
        public void b(Bundle bundle) throws RemoteException {
            if (bundle != null) {
                final String string = bundle.getString("onCallback", "");
                e.c(OtaTestActivity.R3, "onValueChanged->onCallback = " + string);
                l.d().b(new Runnable() { // from class: c.a.d.i.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2506c.E(string);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void N(android.view.View r3) {
        /*
            r2 = this;
            java.lang.String[] r3 = r2.T3
            int r0 = r2.b4
            r3 = r3[r0]
            r0 = 0
            android.widget.Toast r3 = android.widget.Toast.makeText(r2, r3, r0)
            r3.show()
            int r3 = r2.b4
            r0 = 37
            if (r3 == r0) goto L47
            r0 = 42
            if (r3 == r0) goto L47
            switch(r3) {
                case 0: goto L47;
                case 1: goto L47;
                case 2: goto L47;
                case 3: goto L47;
                case 4: goto L47;
                case 5: goto L47;
                case 6: goto L47;
                case 7: goto L47;
                case 8: goto L47;
                case 9: goto L1c;
                case 10: goto L47;
                case 11: goto L47;
                case 12: goto L47;
                case 13: goto L47;
                case 14: goto L47;
                case 15: goto L1c;
                case 16: goto L1c;
                case 17: goto L47;
                case 18: goto L1c;
                case 19: goto L47;
                case 20: goto L47;
                case 21: goto L47;
                case 22: goto L47;
                case 23: goto L47;
                case 24: goto L47;
                case 25: goto L47;
                case 26: goto L47;
                default: goto L1b;
            }
        L1b:
            goto L52
        L1c:
            android.widget.EditText r3 = r2.a4     // Catch: java.lang.Exception -> L3c
            android.text.Editable r3 = r3.getText()     // Catch: java.lang.Exception -> L3c
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L3c
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Exception -> L3c
            c.a.h.b.b r0 = r2.S3     // Catch: android.os.RemoteException -> L37
            int r1 = r2.b4     // Catch: android.os.RemoteException -> L37
            int r3 = r3.intValue()     // Catch: android.os.RemoteException -> L37
            java.lang.String r3 = r0.x(r1, r3)     // Catch: android.os.RemoteException -> L37
            goto L54
        L37:
            r3 = move-exception
            r3.printStackTrace()
            goto L52
        L3c:
            r3 = move-exception
            android.widget.TextView r0 = r2.Z3
            java.lang.String r3 = r3.getMessage()
            r0.setText(r3)
            return
        L47:
            c.a.h.b.b r0 = r2.S3     // Catch: android.os.RemoteException -> L4e
            java.lang.String r3 = r0.o(r3)     // Catch: android.os.RemoteException -> L4e
            goto L54
        L4e:
            r3 = move-exception
            r3.printStackTrace()
        L52:
            java.lang.String r3 = "null"
        L54:
            android.widget.TextView r0 = r2.Z3
            r0.setText(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.ota.view.OtaTestActivity.N(android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void P(View view) {
        String strT;
        Toast.makeText(this, this.T3[this.b4], 0).show();
        switch (this.b4) {
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 38:
            case 39:
            case 40:
            case 41:
            case 43:
                try {
                    try {
                        strT = this.S3.t(this.b4, Integer.valueOf(this.a4.getText().toString()).intValue());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        strT = "null";
                    }
                    this.Z3.setText(strT);
                    break;
                } catch (Exception e3) {
                    this.Z3.setText(e3.getMessage());
                }
            case 37:
            case 42:
            default:
                strT = "null";
                this.Z3.setText(strT);
                break;
        }
    }

    public final void K() {
        Intent intent = new Intent(this, (Class<?>) OtaUiService.class);
        intent.setAction("com.intent.action.OTA_UPGRADE_TEST");
        bindService(intent, this.c4, 1);
    }

    public final void L(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("method_name", str);
        try {
            this.S3.c(bundle);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i2) {
        switch (i2) {
            case R.id.rb_0 /* 2131231028 */:
                this.b4 = 0;
                break;
            case R.id.rb_1 /* 2131231029 */:
                this.b4 = 1;
                break;
            case R.id.rb_10 /* 2131231030 */:
                this.b4 = 10;
                break;
            case R.id.rb_11 /* 2131231031 */:
                this.b4 = 11;
                break;
            case R.id.rb_12 /* 2131231032 */:
                this.b4 = 12;
                break;
            case R.id.rb_13 /* 2131231033 */:
                this.b4 = 13;
                break;
            case R.id.rb_14 /* 2131231034 */:
                this.b4 = 14;
                break;
            case R.id.rb_15 /* 2131231035 */:
                this.b4 = 15;
                break;
            case R.id.rb_16 /* 2131231036 */:
                this.b4 = 16;
                break;
            case R.id.rb_17 /* 2131231037 */:
                this.b4 = 17;
                break;
            case R.id.rb_18 /* 2131231038 */:
                this.b4 = 18;
                break;
            case R.id.rb_19 /* 2131231039 */:
                this.b4 = 19;
                break;
            case R.id.rb_2 /* 2131231040 */:
                this.b4 = 2;
                break;
            case R.id.rb_20 /* 2131231041 */:
                this.b4 = 20;
                break;
            case R.id.rb_21 /* 2131231042 */:
                this.b4 = 21;
                break;
            case R.id.rb_22 /* 2131231043 */:
                this.b4 = 22;
                break;
            case R.id.rb_23 /* 2131231044 */:
                this.b4 = 23;
                break;
            case R.id.rb_24 /* 2131231045 */:
                this.b4 = 24;
                break;
            case R.id.rb_25 /* 2131231046 */:
                this.b4 = 25;
                break;
            case R.id.rb_26 /* 2131231047 */:
                this.b4 = 26;
                break;
            case R.id.rb_27 /* 2131231048 */:
                this.b4 = 27;
                break;
            case R.id.rb_28 /* 2131231049 */:
                this.b4 = 28;
                break;
            case R.id.rb_29 /* 2131231050 */:
                this.b4 = 29;
                break;
            case R.id.rb_3 /* 2131231051 */:
                this.b4 = 3;
                break;
            case R.id.rb_30 /* 2131231052 */:
                this.b4 = 30;
                break;
            case R.id.rb_31 /* 2131231053 */:
                this.b4 = 31;
                break;
            case R.id.rb_32 /* 2131231054 */:
                this.b4 = 32;
                break;
            case R.id.rb_33 /* 2131231055 */:
                this.b4 = 33;
                break;
            case R.id.rb_34 /* 2131231056 */:
                this.b4 = 34;
                break;
            case R.id.rb_35 /* 2131231057 */:
                this.b4 = 35;
                break;
            case R.id.rb_36 /* 2131231058 */:
                this.b4 = 36;
                break;
            case R.id.rb_37 /* 2131231059 */:
                this.b4 = 37;
                break;
            case R.id.rb_38 /* 2131231060 */:
                this.b4 = 38;
                break;
            case R.id.rb_39 /* 2131231061 */:
                this.b4 = 39;
                break;
            case R.id.rb_4 /* 2131231062 */:
                this.b4 = 4;
                break;
            case R.id.rb_40 /* 2131231063 */:
                this.b4 = 40;
                break;
            case R.id.rb_41 /* 2131231064 */:
                this.b4 = 41;
                break;
            case R.id.rb_42 /* 2131231065 */:
                this.b4 = 42;
                break;
            case R.id.rb_43 /* 2131231066 */:
                this.b4 = 43;
                break;
            case R.id.rb_5 /* 2131231067 */:
                this.b4 = 5;
                break;
            case R.id.rb_6 /* 2131231068 */:
                this.b4 = 6;
                break;
            case R.id.rb_7 /* 2131231069 */:
                this.b4 = 7;
                break;
            case R.id.rb_8 /* 2131231070 */:
                this.b4 = 8;
                break;
            case R.id.rb_9 /* 2131231071 */:
                this.b4 = 9;
                break;
        }
        this.W3.setText("[" + this.b4 + "]" + this.T3[this.b4]);
        this.X3.setText(this.U3[this.b4]);
        this.Y3.setText(this.V3[this.b4]);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_dtc_btn /* 2131230844 */:
                L("clearDtc");
                break;
            case R.id.control_presenter_btn /* 2131230859 */:
                L("controlPre");
                break;
            case R.id.delete_ota_dir_btn /* 2131230873 */:
                L("deleteOtaDir");
                break;
            case R.id.get_dtc_btn /* 2131230921 */:
                L("getDtc");
                break;
            case R.id.get_ecu_info_btn /* 2131230922 */:
                L("getEcuInfo");
                break;
            case R.id.loginIn_btn /* 2131230960 */:
                L("loginIn");
                break;
            case R.id.reboot_btn /* 2131231073 */:
                L("reboot");
                break;
            case R.id.remote_presenter_btn /* 2131231075 */:
                L("remotePre");
                break;
            case R.id.show_dialog_btn /* 2131231103 */:
                L("showDialog");
                break;
        }
    }

    @Override // com.byd.ota.view.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ota_test);
        findViewById(R.id.loginIn_btn).setOnClickListener(this);
        findViewById(R.id.show_dialog_btn).setOnClickListener(this);
        findViewById(R.id.remote_presenter_btn).setOnClickListener(this);
        findViewById(R.id.control_presenter_btn).setOnClickListener(this);
        findViewById(R.id.get_ecu_info_btn).setOnClickListener(this);
        findViewById(R.id.get_dtc_btn).setOnClickListener(this);
        findViewById(R.id.clear_dtc_btn).setOnClickListener(this);
        findViewById(R.id.delete_ota_dir_btn).setOnClickListener(this);
        findViewById(R.id.reboot_btn).setOnClickListener(this);
        K();
        this.T3 = getResources().getStringArray(R.array.method_name);
        this.U3 = getResources().getStringArray(R.array.param_description);
        this.V3 = getResources().getStringArray(R.array.result_description);
        ((RadioGroup) findViewById(R.id.radio_group)).setOnCheckedChangeListener(this);
        this.W3 = (TextView) findViewById(R.id.method_name_tv);
        this.X3 = (TextView) findViewById(R.id.param_description_tv);
        this.Y3 = (TextView) findViewById(R.id.result_description_tv);
        this.a4 = (EditText) findViewById(R.id.param_et);
        this.Z3 = (TextView) findViewById(R.id.result_tv);
        findViewById(R.id.test_get_can_btn).setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2510c.N(view);
            }
        });
        findViewById(R.id.test_set_can_btn).setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2514c.P(view);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unbindService(this.c4);
    }
}
