package p;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class g02 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ h02 e;

    public /* synthetic */ g02(h02 h02Var, int i) {
        this.c = i;
        this.e = h02Var;
    }

    public final void a(Throwable th) {
        int i = this.c;
        h02 h02Var = this.e;
        switch (i) {
            case 1:
                ((u11) h02Var.b).v("Error while receiving initial state: " + th.getMessage());
                break;
            default:
                ((u11) h02Var.b).v("Error while updating persisted report: " + th.getMessage());
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        h02 h02Var = this.e;
        switch (i) {
            case 0:
                Map map = (Map) obj;
                ((u11) h02Var.b).getClass();
                LinkedHashMap linkedHashMap = h02Var.c;
                v03.i(map, "savedState");
                linkedHashMap.putAll(map);
                break;
            case 1:
                a((Throwable) obj);
                break;
            case 2:
                Boolean bool = (Boolean) obj;
                v03.i(bool, "isSuccessful");
                if (!bool.booleanValue()) {
                    ((u11) h02Var.b).getClass();
                } else {
                    ((u11) h02Var.b).getClass();
                }
                break;
            default:
                a((Throwable) obj);
                break;
        }
    }
}
