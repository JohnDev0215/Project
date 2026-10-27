package com.byd.data.common.i;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: compiled from: LambdaUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: LambdaUtils.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3425a;

        a() {
        }
    }

    public static <T> Consumer<T> a(final BiConsumer<T, Integer> biConsumer) {
        final a aVar = new a();
        return new Consumer() { // from class: com.byd.data.common.i.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                e.b(aVar, biConsumer, obj);
            }
        };
    }

    static /* synthetic */ void b(a aVar, BiConsumer biConsumer, Object obj) {
        int i2 = aVar.f3425a;
        aVar.f3425a = i2 + 1;
        biConsumer.accept(obj, Integer.valueOf(i2));
    }
}
