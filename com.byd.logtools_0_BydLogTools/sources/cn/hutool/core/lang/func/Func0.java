package cn.hutool.core.lang.func;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@FunctionalInterface
public interface Func0<R> extends Serializable {
    R call() throws Exception;

    default R callWithRuntimeException() {
        try {
            return call();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
