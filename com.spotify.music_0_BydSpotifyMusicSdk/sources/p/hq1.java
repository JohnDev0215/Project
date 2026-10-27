package p;

import com.spotify.ondemandselector.proto.OndemandResponse;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface hq1 {
    @sz0("ondemand-selector/v1/select-ondemand-set")
    Single<OndemandResponse> a();
}
