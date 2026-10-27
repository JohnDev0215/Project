package kotlin.reflect;

import kotlin.reflect.h;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface i<R> extends h<R>, kotlin.jvm.b.a<R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface a<R> extends h.a<R>, kotlin.jvm.b.a<R> {
    }

    Object getDelegate();

    a<R> getGetter();
}
