package spi;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ap<T> {
    public final List<T> a;

    private ap(int i) {
        this.a = new ArrayList(i);
    }

    public static ap a() {
        return new ap(11);
    }
}
