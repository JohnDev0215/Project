package com.spotify.connectivity.productstateesperanto;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface ProductStateMethods {
    Completable overrideValue(String str, String str2);

    Completable removeOverride(String str);

    Completable updateState(String str, String str2);

    Observable<Map<String, String>> values();
}
