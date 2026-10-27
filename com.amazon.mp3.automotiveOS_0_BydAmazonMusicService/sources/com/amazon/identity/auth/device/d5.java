package com.amazon.identity.auth.device;

import android.util.Log;
import android.webkit.JavascriptInterface;
import com.amazon.identity.auth.device.AuthPortalUIActivity;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class d5 {
    public static final String b = "com.amazon.identity.auth.device.d5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f276a;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i(ga.a(d5.b), "Javascript interface onCF() is triggered.");
            c cVar = d5.this.f276a;
            if (cVar == null) {
                return;
            }
            AuthPortalUIActivity.c cVar2 = (AuthPortalUIActivity.c) cVar;
            y5 y5Var = AuthPortalUIActivity.this.C;
            if (y5Var != null) {
                y5Var.b("OnCFCalledByAuthPortal");
            }
            de deVar = AuthPortalUIActivity.this.h;
            if (deVar != null) {
                deVar.c();
                AuthPortalUIActivity.this.h = null;
            }
            AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
            String str = AuthPortalUIActivity.Q;
            authPortalUIActivity.e();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f278a;

        public b(String str) {
            this.f278a = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 259
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.d5.b.run():void");
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface c {
    }

    public d5(c cVar) {
        this.f276a = cVar;
    }

    @JavascriptInterface
    public void onCF() {
        va.f637a.execute(new a());
    }

    @JavascriptInterface
    public void reqPerm(String str) {
        va.f637a.execute(new b(str));
    }
}
