package defpackage;

import defpackage.uo0;
import java.util.ArrayList;

/* JADX INFO: compiled from: FontRequestWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class to0 implements wx<uo0.a> {
    public final /* synthetic */ String a;

    public to0(String str) {
        this.a = str;
    }

    @Override // defpackage.wx
    public final void accept(uo0.a aVar) {
        uo0.a aVar2 = aVar;
        synchronized (uo0.c) {
            wm2<String, ArrayList<wx<uo0.a>>> wm2Var = uo0.d;
            ArrayList<wx<uo0.a>> arrayList = wm2Var.get(this.a);
            if (arrayList == null) {
                return;
            }
            wm2Var.remove(this.a);
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList.get(i).accept(aVar2);
            }
        }
    }
}
