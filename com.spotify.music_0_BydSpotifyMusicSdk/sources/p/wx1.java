package p;

import com.google.common.base.Optional;
import com.spotify.connectivity.flags.Flags;
import com.spotify.connectivity.productstate.ProductStateUtil;
import com.spotify.connectivity.sessionstate.SessionState;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wx1 implements io.reactivex.rxjava3.functions.g, io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;

    public /* synthetic */ wx1(int i) {
        this.c = i;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        switch (this.c) {
            case 0:
                ((gu) null).a();
                break;
            default:
                ((gu) null).b();
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return Boolean.valueOf(((SessionState) obj).loggedIn());
            case 1:
                return Boolean.valueOf(ProductStateUtil.onDemandEnabled((Map<String, String>) obj));
            case 2:
                return Boolean.valueOf(ProductStateUtil.onDemandEnabled((Flags) obj));
            default:
                return (String) ((Optional) obj).c();
        }
    }
}
