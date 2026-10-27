package n4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import com.byd.uereport.R;
import com.byd.uereport.dialog.RegisterDialog;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.avm.sdk.AvmManager;
import g5.m;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: RegisterHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f6520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f6521b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RegisterDialog f6522c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RegisterDialog f6523d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6525f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public BYDAutoGearboxDevice f6526g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public k f6527h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6528i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f6529j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6530k = true;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f6531l = 3000;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6532m = 604800000;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public BroadcastReceiver f6533n = new b();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbsBYDAutoGearboxListener f6534o = new i();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public AvmManager.c f6535p = new j();

    /* JADX INFO: renamed from: n4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: RegisterHandler.java */
    public class RunnableC0093a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ RegisterDialog f6536d;

        /* JADX INFO: renamed from: n4.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: RegisterHandler.java */
        public class DialogInterfaceOnDismissListenerC0094a implements DialogInterface.OnDismissListener {
            public DialogInterfaceOnDismissListenerC0094a() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                a.this.f6522c = null;
                BydLogHelper.logD("mRegisterDialog = null !!!");
            }
        }

        /* JADX INFO: renamed from: n4.a$a$b */
        /* JADX INFO: compiled from: RegisterHandler.java */
        public class b implements DialogInterface.OnDismissListener {
            public b() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                a.this.f6523d = null;
                BydLogHelper.logD("mSettingsRegisterDialog = null !!!");
            }
        }

        /* JADX INFO: renamed from: n4.a$a$c */
        /* JADX INFO: compiled from: RegisterHandler.java */
        public class c implements RegisterDialog.a {
            public c() {
            }

            @Override // com.byd.uereport.dialog.RegisterDialog.a
            public void a() {
                a.this.N();
                RunnableC0093a.this.f6536d.g();
            }
        }

        public RunnableC0093a(RegisterDialog registerDialog) {
            this.f6536d = registerDialog;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f6536d.d() <= 1) {
                a.this.f6522c = this.f6536d;
                this.f6536d.setOnDismissListener(new DialogInterfaceOnDismissListenerC0094a());
            } else {
                a.this.f6523d = this.f6536d;
                this.f6536d.setOnDismissListener(new b());
            }
            if (this.f6536d.k()) {
                this.f6536d.o(new c());
            }
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BydLogHelper.logD("action:" + action);
            action.hashCode();
            switch (action) {
                case "android.intent.action.CAR_SETTINGS_REGISTER_REQUEST":
                    if (!a.this.G()) {
                        a.this.C();
                        break;
                    } else {
                        BydLogHelper.logD("in OFF gear, skip it  .");
                        break;
                    }
                    break;
                case "android.intent.action.Server_Register_Bind_Res":
                    if (!a.this.G()) {
                        a.this.f6528i = intent.getIntExtra("bind_status", -1);
                        BydLogHelper.logD("bindStatus:" + a.this.f6528i);
                        Settings.Global.putInt(a.this.f6521b.getContentResolver(), "persist.yun.bind.status", a.this.f6528i);
                        a.this.f6529j = intent.getStringExtra("bind_content");
                        BydLogHelper.logD("bindContent:" + a.this.f6529j);
                        a.this.D();
                        break;
                    } else {
                        BydLogHelper.logD("in OFF gear, skip it  .");
                        break;
                    }
                    break;
                case "android.intent.action.Server_Register_Bind_Info":
                    int intExtra = intent.getIntExtra("bind_status", -1);
                    BydLogHelper.logD("bindResult:" + intExtra);
                    a.this.E(intExtra);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class c implements DialogInterface.OnDismissListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            a.this.f6523d = null;
            BydLogHelper.logD("mSettingsRegisterDialog = null !");
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class d implements DialogInterface.OnDismissListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            a.this.f6523d = null;
            BydLogHelper.logD("mSettingsRegisterDialog = null !!");
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f6523d != null) {
                a.this.f6523d.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class f implements RegisterDialog.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RegisterDialog f6545a;

        public f(RegisterDialog registerDialog) {
            this.f6545a = registerDialog;
        }

        @Override // com.byd.uereport.dialog.RegisterDialog.a
        public void a() {
            a.this.N();
            this.f6545a.g();
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class g implements DialogInterface.OnDismissListener {
        public g() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            a.this.f6522c = null;
            BydLogHelper.logD("mRegisterDialog = null !!");
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class h implements DialogInterface.OnDismissListener {
        public h() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            a.this.f6522c = null;
            BydLogHelper.logD("mRegisterDialog = null !");
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class i extends AbsBYDAutoGearboxListener {
        public i() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i8) {
            super.onGearChanged(i8);
            if (i8 != 1) {
                BydLogHelper.logD("Not in P gear , dismiss register dialog .");
                a aVar = a.this;
                aVar.y(aVar.f6522c);
            }
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class j implements AvmManager.c {
        public j() {
        }

        @Override // com.ts.avm.sdk.AvmManager.c
        public void a(int i8, String str) {
            if (i8 == 2) {
                BydLogHelper.logD("avm is foreground, dismiss register dialog .");
                a aVar = a.this;
                aVar.y(aVar.f6522c);
            }
        }
    }

    /* JADX INFO: compiled from: RegisterHandler.java */
    public class k extends Handler {
        public k(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i8 = message.what;
            if (i8 == 500) {
                if (!BYDAutoYunDevice.getInstance(a.this.f6521b).isConnectTbox()) {
                    a.this.M(3000);
                    return;
                } else {
                    if (!a.this.f6530k) {
                        a.this.N();
                        return;
                    }
                    a.this.f6530k = false;
                    BydLogHelper.logD("first power on , 5s later send register request .");
                    a.this.M(TsNormalNum.THOUSAND_FIVE);
                    return;
                }
            }
            if (i8 != 501) {
                return;
            }
            if (a.this.f6524e || a.this.f6525f) {
                BydLogHelper.logD("isSettingsRequest:" + a.this.f6524e + ",hasBootRegisterDialogShow:" + a.this.f6525f);
                return;
            }
            if (a.this.I()) {
                a aVar = a.this;
                aVar.Q(aVar.f6528i, a.this.f6529j);
                a.this.f6525f = true;
            } else {
                BydLogHelper.logD("the guide is not complete. 10s check it .");
                a.this.f6527h.removeMessages(501);
                a.this.f6527h.sendEmptyMessageDelayed(501, 3000L);
            }
        }
    }

    public a(Context context) {
        this.f6521b = context.getApplicationContext();
        F();
    }

    public static a z(Context context) {
        if (f6520a == null) {
            synchronized (a.class) {
                if (f6520a == null) {
                    f6520a = new a(context);
                }
            }
        }
        return f6520a;
    }

    public void A() {
        y(this.f6522c);
        y(this.f6523d);
        this.f6527h.removeCallbacksAndMessages(null);
    }

    public void B() {
        this.f6524e = false;
        this.f6525f = false;
        M(0);
    }

    public final void C() {
        int i8 = Settings.Global.getInt(this.f6521b.getContentResolver(), "persist.yun.bind.status", 0);
        BydLogHelper.logD("status:" + i8);
        if (i8 == 3) {
            if (this.f6523d != null) {
                return;
            }
            RegisterDialog registerDialog = new RegisterDialog(this.f6521b, 2);
            this.f6523d = registerDialog;
            registerDialog.p(2);
            this.f6523d.setOnDismissListener(new c());
            this.f6523d.show();
        }
        this.f6524e = true;
        N();
        if (this.f6523d == null) {
            RegisterDialog registerDialog2 = new RegisterDialog(this.f6521b, 2);
            this.f6523d = registerDialog2;
            registerDialog2.setOnDismissListener(new d());
            this.f6523d.show();
        }
    }

    public final void D() {
        boolean z7 = false;
        if (this.f6524e) {
            BydLogHelper.logD("it is response for settings request.");
            StringBuilder sb = new StringBuilder();
            sb.append("mSettingsRegisterDialog = null:");
            sb.append(this.f6523d == null);
            BydLogHelper.logD(sb.toString());
            if (this.f6523d == null) {
                BydLogHelper.logD("mSettingsRegisterDialog == null");
                return;
            } else {
                BydLogHelper.logD("update setting register dialog.");
                S(this.f6528i, this.f6529j);
                return;
            }
        }
        if (this.f6522c != null) {
            try {
                String string = new JSONObject(this.f6529j).getString("bind");
                BydLogHelper.logD("update QR, bindUrl:" + string);
                this.f6522c.w(string);
                return;
            } catch (JSONException e8) {
                e8.printStackTrace();
                return;
            }
        }
        if (this.f6525f) {
            return;
        }
        if (g5.k.b(this.f6521b, "register_not_remind", false)) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jE = g5.k.e(this.f6521b, "register_start_time", 0L);
            BydLogHelper.logD("not remind start time :" + jE + ", current time :" + jCurrentTimeMillis);
            if (jCurrentTimeMillis - jE < 604800000) {
                BydLogHelper.logD("less than 7 days ,not remind");
            } else {
                g5.k.g(this.f6521b, "register_not_remind", false);
                g5.k.j(this.f6521b, "register_start_time", 0L);
                z7 = true;
            }
        } else {
            z7 = true;
        }
        if (z7) {
            if (I()) {
                Q(this.f6528i, this.f6529j);
                this.f6525f = true;
            } else {
                BydLogHelper.logD("the guide is not complete. 10s check it .");
                L();
            }
        }
    }

    public final void E(int i8) {
        RegisterDialog registerDialog;
        if (i8 == 2) {
            Settings.Global.putInt(this.f6521b.getContentResolver(), "persist.yun.bind.status", 3);
            RegisterDialog registerDialog2 = this.f6522c;
            if (registerDialog2 != null) {
                registerDialog2.dismiss();
                Context context = this.f6521b;
                m.e(context, context.getString(R.string.register_bind_success_toast), 0);
            } else {
                RegisterDialog registerDialog3 = this.f6523d;
                if (registerDialog3 != null) {
                    registerDialog3.p(2);
                    this.f6523d.x(2);
                    j3.a.b(new e(), 1000L);
                }
            }
        } else if (i8 == 4) {
            Settings.Global.putInt(this.f6521b.getContentResolver(), "persist.yun.bind.status", 0);
        } else if (i8 == 1) {
            RegisterDialog registerDialog4 = this.f6522c;
            if (registerDialog4 != null) {
                P(registerDialog4, false);
            } else {
                RegisterDialog registerDialog5 = this.f6523d;
                if (registerDialog5 != null) {
                    P(registerDialog5, false);
                }
            }
        } else if (i8 == 3 && (registerDialog = this.f6523d) != null) {
            registerDialog.p(1);
            this.f6523d.x(2);
        }
        K();
    }

    public final void F() {
        BydLogHelper.logD("-----------------------init----------------------------");
        HandlerThread handlerThread = new HandlerThread("Register_Handler");
        handlerThread.start();
        this.f6527h = new k(handlerThread.getLooper());
        this.f6526g = BYDAutoGearboxDevice.getInstance(this.f6521b);
    }

    public final boolean G() {
        return BYDAutoBodyworkDevice.getInstance(this.f6521b).getPowerLevel() == 0;
    }

    public final boolean H() {
        return SystemProperties.getInt("persist.sys.vehicle_privacy_status", 0) == 1;
    }

    public final boolean I() {
        return Settings.System.getInt(this.f6521b.getContentResolver(), "sys.byd.safety.warn", 0) == 1;
    }

    public void J() {
        this.f6526g.registerListener(this.f6534o);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.Server_Register_Bind_Res");
        intentFilter.addAction("android.intent.action.Server_Register_Bind_Info");
        intentFilter.addAction("android.intent.action.CAR_SETTINGS_REGISTER_REQUEST");
        this.f6521b.registerReceiver(this.f6533n, intentFilter);
        M(0);
    }

    public final void K() {
        BydLogHelper.logD("-----------sendAckBroadcast------------");
        Intent intent = new Intent("android.intent.action.Server_Register_Bind_Info_Ack");
        intent.putExtra("bind_ack", 1);
        this.f6521b.sendBroadcast(intent);
    }

    public final void L() {
        Message messageObtainMessage = this.f6527h.obtainMessage();
        messageObtainMessage.what = 501;
        Bundle data = messageObtainMessage.getData();
        data.putInt("bind_status", this.f6528i);
        data.putString("bind_content", this.f6529j);
        this.f6527h.sendMessageDelayed(messageObtainMessage, 3000L);
    }

    public void M(int i8) {
        this.f6527h.removeMessages(500);
        this.f6527h.sendEmptyMessageDelayed(500, i8);
    }

    public final void N() {
        BydLogHelper.logD("-----------sendRegisterRequest------------");
        BydLogHelper.logD("register status :" + Settings.Global.getInt(this.f6521b.getContentResolver(), "persist.yun.bind.status", 0));
        if (!H()) {
            BydLogHelper.logD("user do not agree privacy protocol.");
            return;
        }
        BydLogHelper.logD("send register broadcast.");
        this.f6521b.sendBroadcast(new Intent("android.intent.action.Server_Register_Bind_Req"));
    }

    public void O(RegisterDialog registerDialog) {
        BydLogHelper.logD("setRegisterDialog type:" + registerDialog.d());
        j3.a.b(new RunnableC0093a(registerDialog), 500L);
    }

    public final void P(RegisterDialog registerDialog, boolean z7) {
        BydLogHelper.logD("show Dialog Expire View.");
        registerDialog.s(z7);
        registerDialog.o(new f(registerDialog));
    }

    public final void Q(int i8, String str) {
        BydLogHelper.logD("---------------showRegisterDialog------------");
        if (i8 != 1) {
            if (i8 == 2) {
                try {
                    String string = new JSONObject(str).getString("download");
                    RegisterDialog registerDialog = new RegisterDialog(this.f6521b, 1);
                    this.f6522c = registerDialog;
                    registerDialog.n(string);
                    this.f6522c.setOnDismissListener(new h());
                    this.f6522c.show();
                    return;
                } catch (JSONException e8) {
                    e8.printStackTrace();
                    return;
                }
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string2 = jSONObject.getString("bind");
            String string3 = jSONObject.getString("download");
            RegisterDialog registerDialog2 = new RegisterDialog(this.f6521b, 0);
            this.f6522c = registerDialog2;
            registerDialog2.l(string2);
            this.f6522c.n(string3);
            this.f6522c.setOnDismissListener(new g());
            this.f6522c.show();
            P(this.f6522c, true);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    public void R() {
        this.f6527h.removeCallbacksAndMessages(null);
        this.f6521b.unregisterReceiver(this.f6533n);
        this.f6526g.unregisterListener(this.f6534o);
    }

    public final void S(int i8, String str) {
        BydLogHelper.logD("updateSettingRegisterDialog.");
        if (i8 == 1) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("download");
                String string2 = jSONObject.getString("bind");
                this.f6523d.n(string);
                this.f6523d.l(string2);
                this.f6523d.x(3);
                return;
            } catch (JSONException e8) {
                e8.printStackTrace();
                return;
            }
        }
        if (i8 != 2) {
            if (i8 == 3) {
                this.f6523d.p(2);
                this.f6523d.x(2);
                return;
            }
            return;
        }
        try {
            this.f6523d.n(new JSONObject(str).getString("download"));
            this.f6523d.x(4);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    public void x() {
        y(this.f6522c);
    }

    public final void y(RegisterDialog registerDialog) {
        if (registerDialog != null) {
            registerDialog.dismiss();
        }
    }
}
