package com.amazon.mp3.util.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavaConcurrent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"invokeAll", "", "Ljava/util/concurrent/ExecutorService;", "tasks", "", "Ljava/lang/Runnable;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class JavaConcurrentKt {
    public static final void invokeAll(ExecutorService executorService, Collection<? extends Runnable> tasks) throws InterruptedException {
        Intrinsics.checkNotNullParameter(executorService, "<this>");
        Intrinsics.checkNotNullParameter(tasks, "tasks");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tasks, 10));
        Iterator<T> it = tasks.iterator();
        while (it.hasNext()) {
            arrayList.add(Executors.callable((Runnable) it.next()));
        }
        executorService.invokeAll(arrayList);
    }
}
