package p;

/* JADX INFO: loaded from: classes.dex */
public final class h10 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ i10 e;

    public /* synthetic */ h10(i10 i10Var, int i) {
        this.c = i;
        this.e = i10Var;
    }

    public final wu a(wu wuVar) {
        int i = this.c;
        i10 i10Var = this.e;
        switch (i) {
            case 0:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, "already_paused", "not_playing_context");
                break;
            case 1:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 2:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, "not_paused");
                break;
            case 3:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 4:
                v03.j(wuVar, "p0");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 5:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 6:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 7:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 8:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            case 9:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
            default:
                v03.j(wuVar, "result");
                i10.a(i10Var, wuVar, new String[0]);
                break;
        }
        return wuVar;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final /* bridge */ /* synthetic */ Object apply(Object obj) {
        switch (this.c) {
        }
        return a((wu) obj);
    }
}
