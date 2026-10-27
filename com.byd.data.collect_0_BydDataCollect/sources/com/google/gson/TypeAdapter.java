package com.google.gson;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public abstract class TypeAdapter<T> {
    public final T fromJson(Reader reader) {
        return read(new com.google.gson.w.a(reader));
    }

    public final T fromJsonTree(j jVar) {
        try {
            return read(new com.google.gson.internal.bind.a(jVar));
        } catch (IOException e2) {
            throw new k(e2);
        }
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() { // from class: com.google.gson.TypeAdapter.1
            @Override // com.google.gson.TypeAdapter
            public T read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return (T) TypeAdapter.this.read(aVar);
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(com.google.gson.w.c cVar, T t) throws IOException {
                if (t == null) {
                    cVar.Y();
                } else {
                    TypeAdapter.this.write(cVar, t);
                }
            }
        };
    }

    public abstract T read(com.google.gson.w.a aVar);

    public final void toJson(Writer writer, T t) {
        write(new com.google.gson.w.c(writer), t);
    }

    public final j toJsonTree(T t) {
        try {
            com.google.gson.internal.bind.b bVar = new com.google.gson.internal.bind.b();
            write(bVar, t);
            return bVar.o0();
        } catch (IOException e2) {
            throw new k(e2);
        }
    }

    public abstract void write(com.google.gson.w.c cVar, T t);

    public final T fromJson(String str) {
        return fromJson(new StringReader(str));
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
