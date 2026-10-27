package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmos.Response;
import io.reactivex.rxjava3.core.Observable;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public interface ReturnTypeConverter {
    Object convert(ResponseTransformer responseTransformer, Observable<Response> observable);

    boolean isSupported(Type type, String str);
}
