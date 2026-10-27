package p;

import com.google.common.base.Optional;
import okhttp3.Call;

/* JADX INFO: loaded from: classes.dex */
public final class yz0 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ zz0 c;

    public yz0(zz0 zz0Var) {
        this.c = zz0Var;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        Optional optional = (Optional) obj;
        if (!optional.d()) {
            return Optional.a();
        }
        Object objC = optional.c();
        v03.i(objC, "httpTransport.get()");
        return Optional.e(this.c.b((Call.Factory) objC));
    }
}
