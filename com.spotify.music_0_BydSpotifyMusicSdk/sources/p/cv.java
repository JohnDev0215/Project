package p;

import java.util.concurrent.CompletableFuture;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes.dex */
public final class cv implements pp {
    public final /* synthetic */ int c;
    public final Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ cv(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    @Override // p.pp
    public final void a(jp jpVar, Throwable th) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((CompletableFuture) obj).completeExceptionally(th);
                break;
            case 1:
                ((CompletableFuture) obj).completeExceptionally(th);
                break;
            default:
                ((k60) this.k).c.execute(new kc0(this, (pp) obj, th, 8));
                break;
        }
    }

    @Override // p.pp
    public final void b(jp jpVar, u42 u42Var) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                if (!u42Var.a.i()) {
                    ((CompletableFuture) obj).completeExceptionally(new HttpException(u42Var));
                } else {
                    ((CompletableFuture) obj).complete(u42Var.b);
                }
                break;
            case 1:
                ((CompletableFuture) obj).complete(u42Var);
                break;
            default:
                ((k60) this.k).c.execute(new kc0(this, (pp) obj, u42Var, 7));
                break;
        }
    }
}
