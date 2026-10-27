package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class uv0 implements cz {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ uv0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final void a(vv0 vv0Var) {
        switch (this.a) {
            case 0:
                if (vv0Var == null) {
                    vv0Var = new vv0(-3);
                }
                ((x42) this.b).g(vv0Var);
                return;
            default:
                synchronized (wv0.c) {
                    try {
                        kd2 kd2Var = wv0.d;
                        ArrayList arrayList = (ArrayList) kd2Var.get((String) this.b);
                        if (arrayList == null) {
                            return;
                        }
                        kd2Var.remove((String) this.b);
                        for (int i = 0; i < arrayList.size(); i++) {
                            ((cz) arrayList.get(i)).accept(vv0Var);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }

    @Override // p.cz
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.a) {
            case 0:
                a((vv0) obj);
                break;
            default:
                a((vv0) obj);
                break;
        }
    }
}
