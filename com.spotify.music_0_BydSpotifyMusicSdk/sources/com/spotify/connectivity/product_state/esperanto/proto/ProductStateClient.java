package com.spotify.connectivity.product_state.esperanto.proto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface ProductStateClient {
    Single<DelOverridesValuesResponse> DelOverridesValues(DelOverridesValuesRequest delOverridesValuesRequest);

    Single<GetValuesResponse> GetValues(GetValuesRequest getValuesRequest);

    Single<PutOverridesValuesResponse> PutOverridesValues(PutOverridesValuesRequest putOverridesValuesRequest);

    Single<PutValuesResponse> PutValues(PutValuesRequest putValuesRequest);

    Observable<GetValuesResponse> SubValues(SubValuesRequest subValuesRequest);
}
