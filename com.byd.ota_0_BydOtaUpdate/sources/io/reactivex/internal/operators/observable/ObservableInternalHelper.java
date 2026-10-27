package io.reactivex.internal.operators.observable;

import d.a.f;
import d.a.h;
import d.a.o.e;
import d.a.o.g;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableInternalHelper {

    public enum ErrorMapperFilter implements e<f<Object>, Throwable>, g<f<Object>> {
        INSTANCE;

        @Override // d.a.o.e
        public Throwable apply(f<Object> fVar) throws Exception {
            return fVar.d();
        }

        @Override // d.a.o.g
        public boolean test(f<Object> fVar) throws Exception {
            return fVar.e();
        }
    }

    public static final class a implements e<d.a.g<f<Object>>, h<?>> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final e<? super d.a.g<Throwable>, ? extends h<?>> f5903c;

        public a(e<? super d.a.g<Throwable>, ? extends h<?>> eVar) {
            this.f5903c = eVar;
        }

        @Override // d.a.o.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public h<?> apply(d.a.g<f<Object>> gVar) throws Exception {
            ErrorMapperFilter errorMapperFilter = ErrorMapperFilter.INSTANCE;
            return this.f5903c.apply(gVar.v(errorMapperFilter).n(errorMapperFilter));
        }
    }

    public static <T> e<d.a.g<f<Object>>, h<?>> a(e<? super d.a.g<Throwable>, ? extends h<?>> eVar) {
        return new a(eVar);
    }
}
