package io.objectbox.reactive;

/* JADX INFO: loaded from: classes5.dex */
public interface Scheduler {
    <T> void run(RunWithParam<T> runWithParam, T t);
}
