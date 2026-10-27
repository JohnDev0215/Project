package java9.util.stream;

import java.util.Iterator;
import java9.util.Spliterator;
import java9.util.stream.BaseStream;

/* JADX INFO: loaded from: classes3.dex */
public interface BaseStream<T, S extends BaseStream<T, S>> {
    void close();

    boolean isParallel();

    Iterator<T> iterator();

    S onClose(Runnable runnable);

    S parallel();

    S sequential();

    /* JADX INFO: renamed from: spliterator */
    Spliterator<T> spliterator2();

    S unordered();
}
