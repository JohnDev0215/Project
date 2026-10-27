package com.amazon.identity.auth.device.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.r6;
import com.amazon.identity.auth.device.y5;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPSmsReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int g = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y5 f325a;
    public WebView b;
    public r6 c;
    public Boolean d = null;
    public volatile boolean e = false;
    public volatile a f = null;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MAPSmsReceiver f326a;
        public volatile SmsRetrieverClient b;

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.framework.MAPSmsReceiver$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class C0056a implements OnFailureListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Context f327a;

            public C0056a(Context context) {
                this.f327a = context;
            }

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                MAPSmsReceiver mAPSmsReceiver = a.this.f326a;
                Context context = this.f327a;
                int i = MAPSmsReceiver.g;
                synchronized (mAPSmsReceiver) {
                    Log.w(ga.a("MAPSmsReceiver"), "Not able to start sms retriever", exc);
                    mAPSmsReceiver.f325a.b("MOA:RegisterReadSmsReceiverFailed");
                    mAPSmsReceiver.b(context);
                }
            }
        }

        /* JADX INFO: compiled from: DCP */
        public class b implements OnSuccessListener<Void> {
            public b() {
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Void r3) {
                MAPSmsReceiver mAPSmsReceiver = a.this.f326a;
                int i = MAPSmsReceiver.g;
                synchronized (mAPSmsReceiver) {
                    Log.i(ga.a("MAPSmsReceiver"), "sms retriever registered");
                    mAPSmsReceiver.f325a.b("MOA:RegisterReadSmsReceiver");
                }
            }
        }

        public a(MAPSmsReceiver mAPSmsReceiver, Context context) {
            this.b = null;
            this.f326a = mAPSmsReceiver;
            context.registerReceiver(mAPSmsReceiver, a());
            this.b = SmsRetriever.getClient(context);
            ga.a("MAPSmsReceiver", "mSmsRetrieverClient created");
            Task taskStartSmsRetriever = this.b.startSmsRetriever();
            ga.c("MAPSmsReceiver", "mSmsRetrieverClient started");
            taskStartSmsRetriever.addOnSuccessListener(new b()).addOnFailureListener(new C0056a(context));
        }

        public final IntentFilter a() {
            return new IntentFilter("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
        }
    }

    public MAPSmsReceiver(y5 y5Var, WebView webView) {
        this.f325a = y5Var;
        this.b = webView;
        ga.c("MAPSmsReceiver", "instance created");
    }

    public synchronized void b(Context context) {
        Log.i(ga.a("MAPSmsReceiver"), "unregistering sms retriever: " + this.f);
        if (context != null && this.f != null) {
            if (!this.e) {
                this.f325a.b("MOA:AutoPVCancel");
            }
            a aVar = this.f;
            context.unregisterReceiver(aVar.f326a);
            aVar.b = null;
            this.f = null;
            this.c = null;
        }
        Log.i(ga.a("MAPSmsReceiver"), "Unregistered MAP sms receiver");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:26|(1:28)(12:29|(1:31)|34|35|(1:37)|54|38|39|(1:41)|45|46|59)|33|34|35|(0)|54|38|39|(0)|45|46|59) */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c5, code lost:
    
        android.util.Log.e(com.amazon.identity.auth.device.ga.a("MAPSmsReceiver"), "get an non-numeric code");
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ac A[Catch: all -> 0x00d0, TRY_LEAVE, TryCatch #0 {, blocks: (B:20:0x0066, B:23:0x006e, B:24:0x0080, B:26:0x0082, B:35:0x00a1, B:37:0x00ac, B:38:0x00b3, B:39:0x00b6, B:41:0x00ba, B:45:0x00ce, B:44:0x00c5, B:29:0x008b, B:31:0x0097), top: B:53:0x0066, outer: #2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ba A[Catch: all -> 0x00d0, TRY_LEAVE, TryCatch #0 {, blocks: (B:20:0x0066, B:23:0x006e, B:24:0x0080, B:26:0x0082, B:35:0x00a1, B:37:0x00ac, B:38:0x00b3, B:39:0x00b6, B:41:0x00ba, B:45:0x00ce, B:44:0x00c5, B:29:0x008b, B:31:0x0097), top: B:53:0x0066, outer: #2, inners: #1 }] */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r3, android.content.Intent r4) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.framework.MAPSmsReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public boolean a(Context context) {
        if (this.d == null) {
            this.d = Boolean.valueOf(MAPRuntimePermissionHandler.b(context));
        }
        Log.i(ga.a("MAPSmsReceiver"), "sms retriever is supported: " + this.d);
        return this.d.booleanValue();
    }

    public boolean a(URL url, Context context) {
        String query;
        if (context == null) {
            return false;
        }
        if (("/ap/pv".equals(url.getPath()) || "/ap/cvf/request".equals(url.getPath())) && (query = url.getQuery()) != null && query.contains("spin=true") && query.contains("smsretriever=true")) {
            return a(context);
        }
        return false;
    }

    public boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a("MAPSmsReceiver"), "url is null or empty");
            return false;
        }
        try {
            return a(new URL(str), context);
        } catch (MalformedURLException unused) {
            ga.a("MAPSmsReceiver");
            return false;
        }
    }

    public synchronized void a(Context context, r6 r6Var) {
        Log.i(ga.a("MAPSmsReceiver"), "registering sms retriever: " + this.f);
        if (context != null && this.f == null) {
            this.f = new a(this, context);
            this.c = r6Var;
        }
        Log.i(ga.a("MAPSmsReceiver"), "registered sms retriever: " + this.f);
    }
}
