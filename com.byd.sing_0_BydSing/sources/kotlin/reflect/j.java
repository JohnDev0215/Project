package kotlin.reflect;

import kotlin.jvm.b.l;
import kotlin.reflect.i;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface j<T, V> extends i<V>, l<T, V> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface a<T, V> extends i.a<V>, l<T, V> {
    }

    Object getDelegate(T t);

    a<T, V> getGetter();
}
