package h;

import h.c;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.a f5752a = new f();

    /* JADX INFO: compiled from: DefaultCallAdapterFactory.java */
    public class a implements c<Object, b<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f5753a;

        public a(Type type) {
            this.f5753a = type;
        }

        @Override // h.c
        public Type a() {
            return this.f5753a;
        }

        @Override // h.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public b<Object> b(b<Object> bVar) {
            return bVar;
        }
    }

    @Override // h.c.a
    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (c.a.c(type) != b.class) {
            return null;
        }
        return new a(o.g(type));
    }
}
