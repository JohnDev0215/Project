package com.bugsnag.android;

import com.bugsnag.android.JsonStream;
import com.bugsnag.android.Thread;
import com.bugsnag.android.internal.ImmutableConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ThreadState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B!\b\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bBW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\u0002\u0010\u0016JT\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006#"}, d2 = {"Lcom/bugsnag/android/ThreadState;", "Lcom/bugsnag/android/JsonStream$Streamable;", "exc", "", "isUnhandled", "", "config", "Lcom/bugsnag/android/internal/ImmutableConfig;", "(Ljava/lang/Throwable;ZLcom/bugsnag/android/internal/ImmutableConfig;)V", "maxThreads", "", "sendThreads", "Lcom/bugsnag/android/ThreadSendPolicy;", "projectPackages", "", "", "logger", "Lcom/bugsnag/android/Logger;", "currentThread", "Ljava/lang/Thread;", "allThreads", "", "(Ljava/lang/Throwable;ZILcom/bugsnag/android/ThreadSendPolicy;Ljava/util/Collection;Lcom/bugsnag/android/Logger;Ljava/lang/Thread;Ljava/util/List;)V", "threads", "", "Lcom/bugsnag/android/Thread;", "getThreads", "()Ljava/util/List;", "captureThreadTrace", "maxThreadCount", "toStream", "", "writer", "Lcom/bugsnag/android/JsonStream;", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ThreadState implements JsonStream.Streamable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<Thread> threads;

    public ThreadState(Throwable th, boolean z, int i, ThreadSendPolicy threadSendPolicy, Collection<String> collection, Logger logger, java.lang.Thread thread, List<? extends java.lang.Thread> list) {
        List<Thread> arrayList;
        if (threadSendPolicy == ThreadSendPolicy.ALWAYS || (threadSendPolicy == ThreadSendPolicy.UNHANDLED_ONLY && z)) {
            arrayList = captureThreadTrace(list, thread, th, z, i, collection, logger);
        } else {
            arrayList = new ArrayList<>();
        }
        this.threads = arrayList;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ThreadState(Throwable th, boolean z, int i, ThreadSendPolicy threadSendPolicy, Collection collection, Logger logger, java.lang.Thread thread, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        java.lang.Thread thread2;
        if ((i2 & 64) != 0) {
            java.lang.Thread threadCurrentThread = java.lang.Thread.currentThread();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "JavaThread.currentThread()");
            thread2 = threadCurrentThread;
        } else {
            thread2 = thread;
        }
        this(th, z, i, threadSendPolicy, collection, logger, thread2, (i2 & 128) != 0 ? INSTANCE.allThreads$bugsnag_android_core_release() : list);
    }

    public ThreadState(Throwable th, boolean z, ImmutableConfig immutableConfig) {
        this(th, z, immutableConfig.getMaxReportedThreads(), immutableConfig.getSendThreads(), immutableConfig.getProjectPackages(), immutableConfig.getLogger(), null, null, 192, null);
    }

    public final List<Thread> getThreads() {
        return this.threads;
    }

    /* JADX INFO: compiled from: ThreadState.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0002\b\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, d2 = {"Lcom/bugsnag/android/ThreadState$Companion;", "", "()V", "allThreads", "", "Ljava/lang/Thread;", "allThreads$bugsnag_android_core_release", "rootThreadGroup", "Ljava/lang/ThreadGroup;", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final ThreadGroup rootThreadGroup() {
            java.lang.Thread threadCurrentThread = java.lang.Thread.currentThread();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "JavaThread.currentThread()");
            ThreadGroup threadGroup = threadCurrentThread.getThreadGroup();
            if (threadGroup == null) {
                kotlin.jvm.internal.Intrinsics.throwNpe();
            }
            while (threadGroup.getParent() != null) {
                threadGroup = threadGroup.getParent();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(threadGroup, "group.parent");
            }
            return threadGroup;
        }

        public final List<java.lang.Thread> allThreads$bugsnag_android_core_release() {
            ThreadGroup threadGroupRootThreadGroup = rootThreadGroup();
            java.lang.Thread[] threadArr = new java.lang.Thread[threadGroupRootThreadGroup.activeCount()];
            threadGroupRootThreadGroup.enumerate(threadArr);
            return ArraysKt___ArraysKt.filterNotNull(threadArr);
        }
    }

    private final List<Thread> captureThreadTrace(List<? extends java.lang.Thread> allThreads, final java.lang.Thread currentThread, final Throwable exc, final boolean isUnhandled, int maxThreadCount, final Collection<String> projectPackages, final Logger logger) {
        Function1<java.lang.Thread, Thread> function1 = new Function1<java.lang.Thread, Thread>() { // from class: com.bugsnag.android.ThreadState.captureThreadTrace.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Thread invoke(java.lang.Thread thread) {
                StackTraceElement[] stackTrace;
                boolean z = thread.getId() == currentThread.getId();
                if (z) {
                    Throwable th = exc;
                    if (th != null && isUnhandled) {
                        stackTrace = th.getStackTrace();
                    } else {
                        stackTrace = currentThread.getStackTrace();
                    }
                } else {
                    stackTrace = thread.getStackTrace();
                }
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(stackTrace, "if (isErrorThread) {\n   …ckTrace\n                }");
                return new Thread(thread.getId(), thread.getName(), ThreadType.ANDROID, z, Thread.State.forThread(thread), new Stacktrace(stackTrace, projectPackages, logger), logger);
            }
        };
        List listTake = CollectionsKt___CollectionsKt.take(CollectionsKt___CollectionsKt.sortedWith(allThreads, new Comparator<T>() { // from class: com.bugsnag.android.ThreadState$captureThreadTrace$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((java.lang.Thread) t).getId()), Long.valueOf(((java.lang.Thread) t2).getId()));
            }
        }), maxThreadCount);
        if (!listTake.contains(currentThread)) {
            listTake = CollectionsKt___CollectionsKt.sortedWith(CollectionsKt___CollectionsKt.plus((Collection<? extends java.lang.Thread>) CollectionsKt___CollectionsKt.take(listTake, Math.max(maxThreadCount - 1, 0)), currentThread), new Comparator<T>() { // from class: com.bugsnag.android.ThreadState$captureThreadTrace$$inlined$sortedBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((java.lang.Thread) t).getId()), Long.valueOf(((java.lang.Thread) t2).getId()));
                }
            });
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listTake, 10));
        Iterator it = listTake.iterator();
        while (it.hasNext()) {
            arrayList.add(function1.invoke((java.lang.Thread) it.next()));
        }
        List<Thread> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
        if (allThreads.size() > maxThreadCount) {
            mutableList.add(new Thread(-1L, '[' + (allThreads.size() - maxThreadCount) + " threads omitted as the maxReportedThreads limit (" + maxThreadCount + ") was exceeded]", ThreadType.EMPTY, false, Thread.State.UNKNOWN, new Stacktrace(new StackTraceElement[]{new StackTraceElement("", "", "-", 0)}, projectPackages, logger), logger));
        }
        return mutableList;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        writer.beginArray();
        Iterator<Thread> it = this.threads.iterator();
        while (it.hasNext()) {
            writer.value(it.next());
        }
        writer.endArray();
    }
}
