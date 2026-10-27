package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
import p.g30;
import p.ga1;
import p.rm;
import p.wx1;

/* JADX INFO: loaded from: classes.dex */
public class RxProductStateImpl implements RxProductState {
    private final Observable<Map<String, String>> mProductState;

    public RxProductStateImpl(Observable<Map<String, String>> observable) {
        this.mProductState = observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Optional lambda$productStateKey$0(String str, Map map) {
        return Optional.b((String) map.get(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$productStateKeyOr$2(String str, String str2, Map map) {
        String str3 = (String) map.get(str);
        return str3 == null ? str2 : str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Optional lambda$productStateKeyV2$1(String str, Map map) {
        return Optional.b((String) map.get(str));
    }

    @Override // com.spotify.connectivity.productstate.RxProductState
    public Observable<Map<String, String>> productState() {
        return this.mProductState;
    }

    @Override // com.spotify.connectivity.productstate.RxProductState
    @Deprecated
    public Observable<Optional<String>> productStateKey(String str) {
        return this.mProductState.map(new ga1(str, 2)).distinctUntilChanged();
    }

    @Override // com.spotify.connectivity.productstate.RxProductState
    public Observable<String> productStateKeyOr(String str, String str2) {
        return this.mProductState.map(new rm(str, 3, str2)).distinctUntilChanged();
    }

    @Override // com.spotify.connectivity.productstate.RxProductState
    public Observable<String> productStateKeyV2(String str) {
        return this.mProductState.map(new ga1(str, 1)).filter(new g30()).map(new wx1(3)).distinctUntilChanged();
    }
}
