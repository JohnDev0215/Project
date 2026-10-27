package kotlin.reflect.jvm.internal.impl.types.model;

import com.byd.car.locale.ccTLD;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public enum TypeVariance {
    IN(ccTLD.INDIA),
    OUT("out"),
    INV("");


    @NotNull
    private final String presentation;

    TypeVariance(String str) {
        this.presentation = str;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        return this.presentation;
    }
}
