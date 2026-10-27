package com.google.gson.internal.k;

import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;
import com.google.gson.o;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;

/* JADX INFO: compiled from: NumberTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends p<Number> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final q f4072a = f(ToNumberPolicy.LAZILY_PARSED_NUMBER);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final o f4073b;

    /* JADX INFO: compiled from: NumberTypeAdapter.java */
    class a implements q {
        a() {
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            if (aVar.c() == Number.class) {
                return i.this;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: NumberTypeAdapter.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4075a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f4075a = iArr;
            try {
                iArr[JsonToken.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4075a[JsonToken.NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4075a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private i(o oVar) {
        this.f4073b = oVar;
    }

    public static q e(o oVar) {
        return oVar == ToNumberPolicy.LAZILY_PARSED_NUMBER ? f4072a : f(oVar);
    }

    private static q f(o oVar) {
        return new i(oVar).new a();
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public Number b(com.google.gson.stream.a aVar) throws IOException {
        JsonToken jsonTokenX = aVar.x();
        int i = b.f4075a[jsonTokenX.ordinal()];
        if (i == 1) {
            aVar.t();
            return null;
        }
        if (i == 2 || i == 3) {
            return this.f4073b.readNumber(aVar);
        }
        throw new JsonSyntaxException("Expecting number, got: " + jsonTokenX);
    }

    @Override // com.google.gson.p
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
        bVar.y(number);
    }
}
