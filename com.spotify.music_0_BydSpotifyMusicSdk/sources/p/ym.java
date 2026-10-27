package p;

import com.spotify.bootstrap.v1.proto.Bootstrap$BootstrapRequest;
import com.spotify.bootstrap.v1.proto.Bootstrap$BootstrapResponse;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface ym {
    @t11({"Accept: application/protobuf"})
    @gr1("/bootstrap/v1/bootstrap")
    Single<u42<Bootstrap$BootstrapResponse>> a(@hm Bootstrap$BootstrapRequest bootstrap$BootstrapRequest);
}
