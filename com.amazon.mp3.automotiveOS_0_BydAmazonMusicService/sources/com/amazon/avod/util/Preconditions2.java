package com.amazon.avod.util;

import android.os.Looper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Preconditions2 {
    private static final ReflectionUtils$MethodWrapper<Object> FIND_LOADED_CLASS = new ReflectionUtils$MethodWrapperFactory(false, ClassLoader.class, "findLoadedClass", String.class).createForInstance(ClassLoader.getSystemClassLoader());

    public static void fail(@Nullable String str, @Nullable Object... objArr) {
        Preconditions.checkState(false, str, objArr);
    }

    public static void failWeakly(@Nullable String str, @Nullable Object... objArr) {
        try {
            fail(str, objArr);
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
        }
    }

    public static boolean checkStateWeakly(boolean z, @Nullable Object obj) {
        try {
            Preconditions.checkState(z, obj);
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
        }
        return z;
    }

    public static boolean checkStateWeakly(boolean z, @Nullable String str, @Nullable Object... objArr) {
        try {
            Preconditions.checkState(z, str, objArr);
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
        }
        return z;
    }

    public static boolean checkArgumentWeakly(boolean z, @Nullable Object obj) {
        try {
            Preconditions.checkArgument(z, obj);
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
        }
        return z;
    }

    public static boolean checkArgumentWeakly(boolean z, @Nullable String str, @Nullable Object... objArr) {
        try {
            Preconditions.checkArgument(z, str, objArr);
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
        }
        return z;
    }

    @Nonnull
    public static <T> Optional<T> checkNotNullWeakly(@Nullable T t, @Nullable Object obj) {
        try {
            return Optional.of(Preconditions.checkNotNull(t, obj));
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
            return Optional.absent();
        }
    }

    public static int checkPositive(int i, @Nonnull String str) {
        return checkAtLeast(i, 1, str);
    }

    public static int checkNonNegative(int i, @Nonnull String str) {
        return checkAtLeast(i, 0, str);
    }

    public static long checkNonNegative(long j, @Nonnull String str) {
        return checkAtLeast(j, 0L, str);
    }

    public static long checkNonNegativeWeakly(@Nonnegative long j, long j2, @Nonnull String str) {
        try {
            return checkNonNegative(j, str);
        } catch (IllegalArgumentException e) {
            Throwables2.propagateIfWeakMode(e);
            return j2;
        }
    }

    public static void checkCurrentThread(@Nonnull Thread thread) {
        Thread threadCurrentThread = Thread.currentThread();
        Preconditions.checkState(threadCurrentThread == thread, "Method must be invoked from %s thread, instead invoked from %s", thread, threadCurrentThread);
    }

    public static void checkNotCurrentThread(@Nonnull Thread thread) {
        Thread threadCurrentThread = Thread.currentThread();
        Preconditions.checkState(threadCurrentThread != thread, "Method must not be invoked from %s thread!", threadCurrentThread);
    }

    public static void checkNotMainThread() {
        checkNotCurrentThread(Looper.getMainLooper().getThread());
    }

    public static void checkNotMainThreadWeakly() {
        try {
            checkNotMainThread();
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
        }
    }

    private static int checkAtLeast(int i, int i2, @Nonnull String str) {
        if (i >= i2) {
            return i;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Number is invalid. Minimum value: %d. Actual: %d. %s", Integer.valueOf(i2), Integer.valueOf(i), str));
    }

    private static long checkAtLeast(long j, long j2, @Nonnull String str) {
        if (j >= j2) {
            return j;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Number is invalid. Minimum value: %d. Actual: %d. %s", Long.valueOf(j2), Long.valueOf(j), str));
    }

    @Nonnull
    public static <M extends Map<K, V>, K extends Enum<K>, V> M checkFullKeyMapping(@Nonnull Class<K> cls, @Nonnull M m) {
        Preconditions.checkNotNull(m, "map");
        return (M) checkFullKeyMappingWithBlacklist(cls, m, ImmutableSet.of());
    }

    @Nonnull
    public static <M extends Map<K, V>, K extends Enum<K>, V> M checkFullKeyMappingWithBlacklist(@Nonnull Class<K> cls, @Nonnull M m, @Nonnull ImmutableSet<K> immutableSet) {
        Preconditions.checkNotNull(m, "map");
        Preconditions.checkNotNull(immutableSet, "blackListEnums");
        checkFullSetWithBlacklist(cls, m.keySet(), immutableSet);
        return m;
    }

    @Nonnull
    public static <M extends Map<K, V>, K, V extends Enum<V>> M checkFullValueMapping(@Nonnull Class<V> cls, @Nonnull M m) {
        return (M) checkFullValueMappingWithBlacklist(cls, m, ImmutableSet.of());
    }

    @Nonnull
    public static <M extends Map<K, V>, K, V extends Enum<V>> M checkFullValueMappingWithBlacklist(@Nonnull Class<V> cls, @Nonnull M m, @Nonnull ImmutableSet<V> immutableSet) {
        Preconditions.checkNotNull(cls, "enumClass");
        Preconditions.checkNotNull(m, "map");
        Preconditions.checkNotNull(immutableSet, "blackListEnums");
        int length = cls.getEnumConstants().length;
        Preconditions.checkState(m.size() <= length, "Map must have duplicate values. Expected at most %s values in the map. Actual: %s.", length, m.size());
        Sets.SetView setViewDifference = Sets.difference(Sets.complementOf(m.values(), cls), immutableSet);
        Preconditions.checkState(setViewDifference.isEmpty(), "Missing (%s) in values of enum mapping", setViewDifference.toString());
        return m;
    }

    @Nonnull
    public static <K extends Enum<K>, S extends Set<K>> S checkFullSetWithBlacklist(@Nonnull Class<K> cls, @Nonnull S s, @Nonnull ImmutableSet<K> immutableSet) {
        Preconditions.checkNotNull(s, "set");
        Preconditions.checkNotNull(immutableSet, "blackListEnums");
        checkSetAgainstBlacklist(s, Sets.complementOf(s, cls), immutableSet);
        return s;
    }

    @Nonnull
    private static void checkSetAgainstBlacklist(@Nonnull Set set, @Nonnull Set set2, @Nonnull Set set3) {
        Sets.SetView setViewDifference = Sets.difference(set2, set3);
        Preconditions.checkState(setViewDifference.isEmpty(), "Enum missing in set and blacklist: %s", setViewDifference);
        Sets.SetView setViewIntersection = Sets.intersection(set, set3);
        Preconditions.checkState(setViewIntersection.isEmpty(), "set contains enums for blacklisted enums: %s", setViewIntersection);
    }
}
