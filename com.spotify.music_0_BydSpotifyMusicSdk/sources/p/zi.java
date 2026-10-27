package p;

/* JADX INFO: loaded from: classes.dex */
public final class zi {
    public final cp2 a;
    public final ip2 b;

    public zi(String str, cp2 cp2Var) {
        this.a = cp2Var;
        ip2 ip2Var = ip2.i;
        hp2 hp2VarM = qo.m();
        hp2VarM.h = "automotive";
        hp2VarM.a = "automotive-content-error";
        hp2VarM.f = "1.1.0";
        hp2VarM.g = "15.3.0";
        hp2VarM.d = str;
        this.b = hp2VarM.a();
    }

    public zi() {
        cp2 cp2Var = cp2.b;
        v03.j(cp2Var, "parentAbsoluteLocation");
        this.a = cp2Var;
        ip2 ip2Var = ip2.i;
        hp2 hp2VarM = qo.m();
        hp2VarM.h = "automotive";
        hp2VarM.a = "automotive-browse-tab";
        hp2VarM.f = "1.0.0";
        hp2VarM.g = "15.3.0";
        this.b = hp2VarM.a();
    }

    public zi(String str) {
        cp2 cp2Var = cp2.b;
        v03.j(cp2Var, "parentAbsoluteLocation");
        this.a = cp2Var;
        ip2 ip2Var = ip2.i;
        hp2 hp2VarM = qo.m();
        hp2VarM.h = "automotive";
        hp2VarM.a = "automotive-login-password";
        hp2VarM.f = "1.0.0";
        hp2VarM.g = "15.3.0";
        hp2VarM.b = str;
        this.b = hp2VarM.a();
    }
}
