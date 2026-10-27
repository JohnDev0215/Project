package com.neusoft.de.nts.OneCoreTest.utils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java9.util.concurrent.CompletableFuture;
import java9.util.function.Function;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes2.dex */
public final class CommonUtil {
    private static final Logger logger = Logger.getLogger((Class<?>) CommonUtil.class);
    private static final AtomicInteger screenshotCounter = new AtomicInteger(0);

    private CommonUtil() {
    }

    @Deprecated
    public static void sleep(long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            TimeUnit.MILLISECONDS.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static CompletableFuture<Void> asyncSleep(final long j, final TimeUnit timeUnit) {
        return CompletableFuture.runAsync(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommonUtil.1
            @Override // java.lang.Runnable
            public void run() {
                CommonUtil.sleep(j, timeUnit);
            }
        });
    }

    @Deprecated
    public static CompletableFuture<Void> asyncSleep(long j, TimeUnit timeUnit, Executor executor) {
        return asyncSleep(j, timeUnit).thenRunAsync(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommonUtil.2
            @Override // java.lang.Runnable
            public void run() {
            }
        }, executor);
    }

    public static CompletableFuture<Void> asyncSleep(final Duration duration) {
        return CompletableFuture.runAsync(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommonUtil.3
            @Override // java.lang.Runnable
            public void run() {
                CommonUtil.sleep(duration);
            }
        });
    }

    public static CompletableFuture<Void> asyncSleep(Duration duration, Executor executor) {
        return asyncSleep(duration).thenRunAsync(new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommonUtil.4
            @Override // java.lang.Runnable
            public void run() {
            }
        }, executor);
    }

    @Deprecated
    public static <T> T delay(T t, long j, TimeUnit timeUnit) {
        sleep(j, timeUnit);
        return t;
    }

    public static <T> T delay(T t, Duration duration) {
        sleep(duration);
        return t;
    }

    public static <T> CompletableFuture<T> delayAsync(final T t, Duration duration) {
        return (CompletableFuture<T>) asyncSleep(duration).thenApplyAsync((Function<? super Void, ? extends U>) new Function<Void, T>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommonUtil.5
            @Override // java9.util.function.Function
            public T apply(Void r1) {
                return (T) t;
            }
        });
    }

    public static <T> CompletableFuture<T> delayAsync(final T t, Duration duration, Executor executor) {
        return (CompletableFuture<T>) asyncSleep(duration).thenApplyAsync((Function<? super Void, ? extends U>) new Function<Void, T>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.CommonUtil.6
            @Override // java9.util.function.Function
            public T apply(Void r1) {
                return (T) t;
            }
        }, executor);
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static <T> void safeInitialize(T t, Class<T> cls, Runnable runnable) {
        if (t == null) {
            synchronized (cls) {
                if (t == null) {
                    runnable.run();
                }
            }
        }
    }

    public static <T> T[] toArray(Collection<T> collection) {
        return (T[]) toArray(collection, collection.iterator().next().getClass());
    }

    public static <T> T[] toArray(Collection<T> collection, Class<?> cls) {
        return (T[]) collection.toArray((Object[]) Array.newInstance(cls, collection.size()));
    }

    public static <T extends Comparable<T>> T clamp(T t, T t2, T t3) {
        return t.compareTo(t2) < 0 ? t2 : t.compareTo(t3) > 0 ? t3 : t;
    }

    public static boolean isTimeout(Throwable th) {
        return ExceptionUtils.indexOfThrowable(th, TimeoutException.class) != -1;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static boolean isThisMyIpAddress(String str) {
        try {
            InetAddress byName = InetAddress.getByName(str);
            if (byName.isAnyLocalAddress() || byName.isLoopbackAddress()) {
                return NetworkInterface.getByInetAddress(byName) != null;
            }
            return false;
        } catch (SocketException | UnknownHostException e) {
            logger.debug(e, "Exception when checking IP-Address: " + e.getMessage(), new Object[0]);
            return false;
        }
    }

    public static String humanReadableDuration(Duration duration) {
        return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
    }
}
