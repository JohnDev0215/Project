package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmos.Response;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;

/* JADX INFO: loaded from: classes.dex */
public interface ResponseTransformer extends ObservableTransformer<Response, Object> {
    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    /* synthetic */ ObservableSource apply(Observable observable);
}
