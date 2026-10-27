package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import p.fo;
import p.io;
import p.ko;
import p.po1;
import p.sn1;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public abstract class f<T> {

    public class a extends f<T> {
        final /* synthetic */ f a;

        public a(f fVar) {
            this.a = fVar;
        }

        @Override // com.squareup.moshi.f
        public T fromJson(JsonReader jsonReader) {
            return (T) this.a.fromJson(jsonReader);
        }

        @Override // com.squareup.moshi.f
        public boolean isLenient() {
            return this.a.isLenient();
        }

        @Override // com.squareup.moshi.f
        public void toJson(m mVar, T t) {
            boolean zG0 = mVar.G0();
            mVar.S0(true);
            try {
                this.a.toJson(mVar, t);
            } finally {
                mVar.S0(zG0);
            }
        }

        public String toString() {
            return this.a + ".serializeNulls()";
        }
    }

    public class b extends f<T> {
        final /* synthetic */ f a;

        public b(f fVar) {
            this.a = fVar;
        }

        @Override // com.squareup.moshi.f
        public T fromJson(JsonReader jsonReader) {
            boolean zX = jsonReader.X();
            jsonReader.P0(true);
            try {
                return (T) this.a.fromJson(jsonReader);
            } finally {
                jsonReader.P0(zX);
            }
        }

        @Override // com.squareup.moshi.f
        public boolean isLenient() {
            return true;
        }

        @Override // com.squareup.moshi.f
        public void toJson(m mVar, T t) {
            boolean zH0 = mVar.H0();
            mVar.R0(true);
            try {
                this.a.toJson(mVar, t);
            } finally {
                mVar.R0(zH0);
            }
        }

        public String toString() {
            return this.a + ".lenient()";
        }
    }

    public class c extends f<T> {
        final /* synthetic */ f a;

        public c(f fVar) {
            this.a = fVar;
        }

        @Override // com.squareup.moshi.f
        public T fromJson(JsonReader jsonReader) {
            boolean zE = jsonReader.E();
            jsonReader.O0(true);
            try {
                return (T) this.a.fromJson(jsonReader);
            } finally {
                jsonReader.O0(zE);
            }
        }

        @Override // com.squareup.moshi.f
        public boolean isLenient() {
            return this.a.isLenient();
        }

        @Override // com.squareup.moshi.f
        public void toJson(m mVar, T t) {
            this.a.toJson(mVar, t);
        }

        public String toString() {
            return this.a + ".failOnUnknown()";
        }
    }

    public class d extends f<T> {
        final /* synthetic */ f a;
        final /* synthetic */ String b;

        public d(f fVar, String str) {
            this.a = fVar;
            this.b = str;
        }

        @Override // com.squareup.moshi.f
        public T fromJson(JsonReader jsonReader) {
            return (T) this.a.fromJson(jsonReader);
        }

        @Override // com.squareup.moshi.f
        public boolean isLenient() {
            return this.a.isLenient();
        }

        @Override // com.squareup.moshi.f
        public void toJson(m mVar, T t) {
            String strO0 = mVar.o0();
            mVar.Q0(this.b);
            try {
                this.a.toJson(mVar, t);
            } finally {
                mVar.Q0(strO0);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append(".indent(\"");
            return u4.m(sb, this.b, "\")");
        }
    }

    public interface e {
        f<?> a(Type type, Set<? extends Annotation> set, o oVar);
    }

    public final f<T> failOnUnknown() {
        return new c(this);
    }

    public abstract T fromJson(JsonReader jsonReader);

    public final T fromJson(ko koVar) {
        return fromJson(JsonReader.G0(koVar));
    }

    public final T fromJsonValue(Object obj) {
        try {
            return fromJson(new j(obj));
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public f<T> indent(String str) {
        if (str != null) {
            return new d(this, str);
        }
        throw new NullPointerException("indent == null");
    }

    public boolean isLenient() {
        return false;
    }

    public final f<T> lenient() {
        return new b(this);
    }

    public final f<T> nonNull() {
        return this instanceof sn1 ? this : new sn1(this);
    }

    public final f<T> nullSafe() {
        return this instanceof po1 ? this : new po1(this);
    }

    public final f<T> serializeNulls() {
        return new a(this);
    }

    public abstract void toJson(m mVar, T t);

    public final void toJson(io ioVar, T t) {
        toJson(m.L0(ioVar), t);
    }

    public final Object toJsonValue(T t) {
        l lVar = new l();
        try {
            toJson(lVar, t);
            return lVar.Z0();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public final T fromJson(String str) {
        fo foVar = new fo();
        foVar.d1(str);
        JsonReader jsonReaderG0 = JsonReader.G0(foVar);
        T tFromJson = fromJson(jsonReaderG0);
        if (isLenient() || jsonReaderG0.H0() == JsonReader.Token.END_DOCUMENT) {
            return tFromJson;
        }
        throw new JsonDataException("JSON document was not fully consumed.");
    }

    public final String toJson(T t) {
        fo foVar = new fo();
        try {
            toJson(foVar, t);
            return foVar.Q0();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
