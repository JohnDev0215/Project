package com.google.gson;

import com.google.gson.stream.JsonToken;
import java.io.IOException;

/* JADX INFO: compiled from: TypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p<T> {

    /* JADX INFO: compiled from: TypeAdapter.java */
    class a extends p<T> {
        a() {
        }

        @Override // com.google.gson.p
        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return (T) p.this.b(aVar);
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        public void d(com.google.gson.stream.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.m();
            } else {
                p.this.d(bVar, t);
            }
        }
    }

    public final p<T> a() {
        return new a();
    }

    public abstract T b(com.google.gson.stream.a aVar) throws IOException;

    public final i c(T t) {
        try {
            com.google.gson.internal.k.g gVar = new com.google.gson.internal.k.g();
            d(gVar, t);
            return gVar.C();
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public abstract void d(com.google.gson.stream.b bVar, T t) throws IOException;
}
