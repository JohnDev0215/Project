package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import p.e5;
import p.fr2;
import p.gx1;
import p.ho2;
import p.jr2;
import p.v4;
import p.wi;

/* JADX INFO: loaded from: classes.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String queryParameter = intent.getData().getQueryParameter("backendName");
        String queryParameter2 = intent.getData().getQueryParameter("extras");
        int iIntValue = Integer.valueOf(intent.getData().getQueryParameter("priority")).intValue();
        int i = intent.getExtras().getInt("attemptNumber");
        ho2.b(context);
        v4 v4VarA = wi.a();
        v4VarA.M(queryParameter);
        v4VarA.N(gx1.b(iIntValue));
        if (queryParameter2 != null) {
            v4VarA.k = Base64.decode(queryParameter2, 0);
        }
        jr2 jr2Var = ho2.a().d;
        wi wiVarE = v4VarA.e();
        e5 e5Var = new e5();
        jr2Var.getClass();
        jr2Var.e.execute(new fr2(jr2Var, wiVarE, i, e5Var));
    }
}
