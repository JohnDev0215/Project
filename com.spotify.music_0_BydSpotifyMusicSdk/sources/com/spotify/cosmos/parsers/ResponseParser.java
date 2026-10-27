package com.spotify.cosmos.parsers;

import com.spotify.cosmos.cosmos.Response;

/* JADX INFO: loaded from: classes.dex */
public interface ResponseParser<T> {
    T parseResponse(Response response);
}
