package kotlin.reflect.jvm.internal.impl.types.checker;

import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class Ref<T> {

    @Nullable
    private T value;

    public Ref(@Nullable T t) {
        this.value = t;
    }

    @Nullable
    public final T getValue() {
        return this.value;
    }
}
