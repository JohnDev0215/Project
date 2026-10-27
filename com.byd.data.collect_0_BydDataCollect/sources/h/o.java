package h;

import f.h0;
import h.h;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* JADX INFO: compiled from: OptionalConverterFactory.java */
/* JADX INFO: loaded from: classes.dex */
@IgnoreJRERequirement
final class o extends h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final h.a f5606a = new o();

    /* JADX INFO: compiled from: OptionalConverterFactory.java */
    @IgnoreJRERequirement
    static final class a<T> implements h<h0, Optional<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final h<h0, T> f5607a;

        a(h<h0, T> hVar) {
            this.f5607a = hVar;
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Optional<T> a(h0 h0Var) {
            return Optional.ofNullable(this.f5607a.a(h0Var));
        }
    }

    o() {
    }

    @Override // h.h.a
    @Nullable
    public h<h0, ?> d(Type type, Annotation[] annotationArr, u uVar) {
        if (h.a.b(type) != Optional.class) {
            return null;
        }
        return new a(uVar.h(h.a.a(0, (ParameterizedType) type), annotationArr));
    }
}
