package com.spotify.cosmos.parsers;

import com.spotify.base.java.logging.Logger;
import com.spotify.cosmos.cosmos.Response;
import com.squareup.moshi.o;
import java.io.IOException;
import p.cs;
import p.lc;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MoshiParser<T> implements ResponseParser<T> {
    private final Class<T> modelClass;
    private final o moshi;

    public MoshiParser(Class<T> cls, o oVar) {
        v03.j(cls, "modelClass");
        v03.j(oVar, "moshi");
        this.modelClass = cls;
        this.moshi = oVar;
    }

    @Override // com.spotify.cosmos.parsers.ResponseParser
    public T parseResponse(Response response) {
        v03.j(response, "response");
        System.nanoTime();
        try {
            try {
                byte[] body = response.getBody();
                T tFromJson = this.moshi.c(this.modelClass).fromJson(body != null ? new String(body, cs.a) : new String());
                System.nanoTime();
                this.modelClass.getSimpleName();
                return tFromJson;
            } catch (IOException e) {
                Logger.b("Error parsing JSON String, response: %s", response.toString());
                throw new ParserException(e);
            } catch (ArrayStoreException e2) {
                Logger.b("Error parsing JSON String, response: %s", response.toString());
                lc.e("Caught an exception while parsing JSON string", e2);
                System.nanoTime();
                this.modelClass.getSimpleName();
                return null;
            }
        } catch (Throwable th) {
            System.nanoTime();
            this.modelClass.getSimpleName();
            throw th;
        }
    }
}
