package defpackage;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: ChildProcessConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class mn implements Cdo {
    public final /* synthetic */ Context a;
    public final /* synthetic */ qn b;

    public mn(qn qnVar, Context context) {
        this.b = qnVar;
        this.a = context;
    }

    public final eo a(Intent intent, int i, co coVar, String str) {
        Context context = this.a;
        qn qnVar = this.b;
        return new eo(context, intent, i, qnVar.b, qnVar.c, coVar, str);
    }
}
