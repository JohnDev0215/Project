package com.byd.secretcode.activity;

import android.os.Bundle;
import android.os.RemoteException;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import defpackage.tm;
import defpackage.um;
import defpackage.vm;
import defpackage.zf;

/* JADX INFO: loaded from: classes.dex */
public class WirelessToolsActivity extends AppCompatActivity {
    public TextView b;
    public um c;
    public a h;
    public String d = "";
    public String e = "";
    public String f = "";
    public int g = 0;
    public int i = 0;
    public int j = 1;
    public int k = 2;
    public int l = 3;
    public int m = 4;
    public int n = 5;

    public class a extends vm.a {

        /* JADX INFO: renamed from: com.byd.secretcode.activity.WirelessToolsActivity$a$a, reason: collision with other inner class name */
        public class RunnableC0010a implements Runnable {
            public final /* synthetic */ tm b;

            public RunnableC0010a(tm tmVar) {
                this.b = tmVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                WirelessToolsActivity.this.b.setText(WirelessToolsActivity.this.getString(R.string.wireless_text_11) + WirelessToolsActivity.this.f + WirelessToolsActivity.this.getString(R.string.wireless_text_12) + WirelessToolsActivity.this.e + WirelessToolsActivity.this.getString(R.string.wireless_text_13) + ((int) this.b.c));
            }
        }

        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void y() {
            WirelessToolsActivity.this.b.setText(WirelessToolsActivity.this.getString(R.string.wireless_text_16));
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
            if (tmVar == null || tmVar.equals("")) {
                WirelessToolsActivity.this.runOnUiThread(new Runnable() { // from class: yf
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.b.y();
                    }
                });
                return;
            }
            byte b = tmVar.a;
            if (b == 0) {
                WirelessToolsActivity wirelessToolsActivity = WirelessToolsActivity.this;
                wirelessToolsActivity.e = wirelessToolsActivity.getString(R.string.wireless_text_1);
            } else if (b == 2) {
                WirelessToolsActivity wirelessToolsActivity2 = WirelessToolsActivity.this;
                wirelessToolsActivity2.e = wirelessToolsActivity2.getString(R.string.wireless_text_2);
            } else if (b == 3) {
                WirelessToolsActivity wirelessToolsActivity3 = WirelessToolsActivity.this;
                wirelessToolsActivity3.e = wirelessToolsActivity3.getString(R.string.wireless_text_3);
            } else if (b == 4) {
                WirelessToolsActivity wirelessToolsActivity4 = WirelessToolsActivity.this;
                wirelessToolsActivity4.e = wirelessToolsActivity4.getString(R.string.wireless_text_4);
            } else if (b == 1) {
                WirelessToolsActivity wirelessToolsActivity5 = WirelessToolsActivity.this;
                wirelessToolsActivity5.e = wirelessToolsActivity5.getString(R.string.wireless_text_5);
            }
            int i = tmVar.b;
            if (i == 0) {
                WirelessToolsActivity wirelessToolsActivity6 = WirelessToolsActivity.this;
                wirelessToolsActivity6.f = wirelessToolsActivity6.getString(R.string.wireless_text_6);
            } else if (i == 1) {
                WirelessToolsActivity wirelessToolsActivity7 = WirelessToolsActivity.this;
                wirelessToolsActivity7.f = wirelessToolsActivity7.getString(R.string.wireless_text_7);
            } else if (i == 2) {
                WirelessToolsActivity wirelessToolsActivity8 = WirelessToolsActivity.this;
                wirelessToolsActivity8.f = wirelessToolsActivity8.getString(R.string.wireless_text_8);
            } else if (i == 3) {
                WirelessToolsActivity wirelessToolsActivity9 = WirelessToolsActivity.this;
                wirelessToolsActivity9.f = wirelessToolsActivity9.getString(R.string.wireless_text_9);
            } else if (i == 4) {
                WirelessToolsActivity wirelessToolsActivity10 = WirelessToolsActivity.this;
                wirelessToolsActivity10.f = wirelessToolsActivity10.getString(R.string.wireless_text_10);
            }
            WirelessToolsActivity.this.runOnUiThread(new RunnableC0010a(tmVar));
        }

        @Override // defpackage.vm
        public void n(byte b) throws RemoteException {
        }

        @Override // defpackage.vm
        public void o(byte b) throws RemoteException {
        }

        public /* synthetic */ a(WirelessToolsActivity wirelessToolsActivity, zf zfVar) {
            this();
        }
    }

    public final void initData() {
        this.h = new a(this, null);
        if (this.c == null) {
            try {
                um umVarP = um.p();
                this.c = umVarP;
                umVarP.b(this.h);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public final void initView() {
        this.b = (TextView) findViewById(R.id.tv_wireless);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wireless_tools);
        initView();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            um umVar = this.c;
            if (umVar != null) {
                umVar.c(this.h);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initData();
    }
}
