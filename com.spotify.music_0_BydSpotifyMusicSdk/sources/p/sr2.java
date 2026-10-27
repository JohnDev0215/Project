package p;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class sr2 extends BroadcastReceiver {
    public final /* synthetic */ int a;
    public final qj b;

    public /* synthetic */ sr2(qj qjVar, int i) {
        this.a = i;
        this.b = qjVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int i = this.a;
        qj qjVar = this.b;
        switch (i) {
            case 0:
                bi1 bi1Var = qjVar.b;
                bi1Var.a.a.setActive(false);
                Iterator it = bi1Var.c.iterator();
                if (it.hasNext()) {
                    u4.z(it.next());
                    throw null;
                }
                qjVar.c.g();
                return;
            default:
                bi1 bi1Var2 = qjVar.b;
                bi1Var2.a.a.setActive(true);
                Iterator it2 = bi1Var2.c.iterator();
                if (it2.hasNext()) {
                    u4.z(it2.next());
                    throw null;
                }
                return;
        }
    }
}
