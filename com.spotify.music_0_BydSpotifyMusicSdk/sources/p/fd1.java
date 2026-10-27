package p;

import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.automotive.ubi.Referrer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fd1 implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ fd1(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                com.spotify.automotive.auth.b bVar = (com.spotify.automotive.auth.b) obj2;
                j jVar = bVar.f;
                jVar.c.dispose();
                jVar.b.a();
                ((sd1) bVar.d).b.onNext(new LoginResponse());
                bVar.d((LoginState) obj);
                break;
            case 1:
                ((oj) obj2).g.b((aq2) obj);
                break;
            case 2:
                ((oj) obj2).g.b((bq2) obj);
                break;
            default:
                ot1 ot1Var = (ot1) obj2;
                ot1Var.getClass();
                String strA = Referrer.SEARCH.a();
                ((z1) ot1Var.a).s(null, (String) obj, null, strA);
                break;
        }
    }
}
