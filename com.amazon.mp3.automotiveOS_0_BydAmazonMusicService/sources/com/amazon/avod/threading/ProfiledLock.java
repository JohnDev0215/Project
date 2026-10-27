package com.amazon.avod.threading;

import android.os.StrictMode;
import com.amazon.avod.core.Framework;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.amazon.avod.util.ReflectionUtils$MethodWrapper;
import com.amazon.avod.util.ReflectionUtils$MethodWrapperFactory;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Sets;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
public class ProfiledLock {
    private final ReentrantLock mLock;
    private final String mLockName;

    @GuardedBy("mLock")
    private final Set<Key> mPendingKeys = Sets.newHashSet();
    private final ThreadUsage mThreadUsage;
    private static final ReflectionUtils$MethodWrapper<Integer> GET_THREAD_POLICY_MASK = new ReflectionUtils$MethodWrapperFactory(false, StrictMode.class, "getThreadPolicyMask", new Class[0]).createForInstance(StrictMode.class);
    private static final Supplier<StrictMode.ThreadPolicy> DETECT_ALL_VIOLATIONS_UNDER_LOCK = Suppliers.memoize(new Supplier<StrictMode.ThreadPolicy>() { // from class: com.amazon.avod.threading.ProfiledLock.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public StrictMode.ThreadPolicy get() {
            return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDeath().build();
        }
    });

    public enum ThreadUsage {
        ANY { // from class: com.amazon.avod.threading.ProfiledLock.ThreadUsage.1
            @Override // com.amazon.avod.threading.ProfiledLock.ThreadUsage
            void checkCurrentThread() {
            }
        },
        BACKGROUND_THREAD_ONLY { // from class: com.amazon.avod.threading.ProfiledLock.ThreadUsage.2
            @Override // com.amazon.avod.threading.ProfiledLock.ThreadUsage
            void checkCurrentThread() {
                Preconditions2.checkNotMainThread();
            }
        };

        abstract void checkCurrentThread();
    }

    public ProfiledLock(@Nonnull Object obj, ThreadUsage threadUsage) {
        Preconditions.checkNotNull(obj, MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
        this.mLockName = String.format(Locale.US, "%s:%s", obj.getClass().getSimpleName(), "Lock");
        this.mLock = new ReentrantLock(false);
        this.mThreadUsage = (ThreadUsage) Preconditions.checkNotNull(threadUsage, "threadUsage");
    }

    @Nonnull
    public Key lock(@Nonnull String str) {
        Optional optionalAbsent;
        Preconditions.checkNotNull(str, "reason");
        this.mThreadUsage.checkCurrentThread();
        int threadPolicyMask = 0;
        if (this.mThreadUsage == ThreadUsage.ANY && Framework.isDebugConfigurationEnabled() && !this.mLock.isHeldByCurrentThread()) {
            optionalAbsent = Optional.of(StrictMode.getThreadPolicy());
            StrictMode.setThreadPolicy(DETECT_ALL_VIOLATIONS_UNDER_LOCK.get());
            threadPolicyMask = getThreadPolicyMask();
            DLog.devf("Thread policy mask: %s", Integer.valueOf(threadPolicyMask));
        } else {
            optionalAbsent = Optional.absent();
        }
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.DEBUG;
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(traceLevel, "%s:%s", this.mLockName, str);
        TraceKey traceKeyBeginTrace2 = Profiler.beginTrace(traceLevel, "%s:%s:WaitOnLock", this.mLockName, str);
        this.mLock.lock();
        Profiler.endTrace(traceKeyBeginTrace2);
        Key key = new Key(traceKeyBeginTrace, optionalAbsent, threadPolicyMask);
        this.mPendingKeys.add(key);
        return key;
    }

    public void unlock(@Nonnull Key key) {
        Preconditions.checkNotNull(key, "key");
        Preconditions2.checkCurrentThread(key.mThread);
        Preconditions.checkState(this.mPendingKeys.remove(key), "Key does not fit in this lock, can only use with the original lock instance it was acquired from: %s", key);
        if (key.mThreadPolicyOptional.isPresent()) {
            int threadPolicyMask = getThreadPolicyMask();
            Preconditions2.checkStateWeakly(key.mThreadPolicyMaskUnderLock == threadPolicyMask, "StrictMode thread policy mask does not match between lock & unlock: %s vs. %s", Integer.valueOf(key.mThreadPolicyMaskUnderLock), Integer.valueOf(threadPolicyMask));
            StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) key.mThreadPolicyOptional.get());
        }
        this.mLock.unlock();
        Profiler.endTrace(key.mTraceKey);
    }

    public void checkLocked(@Nonnull String str) {
        Preconditions.checkNotNull(str, "reason");
        Preconditions.checkState(this.mLock.isHeldByCurrentThread(), "%s lock is not help by current thread, request for: %s", this.mLockName, str);
    }

    public void checkNotLocked(@Nonnull String str) {
        Preconditions.checkNotNull(str, "reason");
        Preconditions.checkState(!this.mLock.isHeldByCurrentThread(), "%s lock must not be held while performing %s operation", this.mLockName, str);
    }

    private int getThreadPolicyMask() {
        return GET_THREAD_POLICY_MASK.call(new Object[0]).intValue();
    }

    @Immutable
    public static class Key {
        private final Thread mThread;
        private final int mThreadPolicyMaskUnderLock;
        private final Optional<StrictMode.ThreadPolicy> mThreadPolicyOptional;
        private final TraceKey mTraceKey;

        private Key(@Nonnull TraceKey traceKey, @Nonnull Optional<StrictMode.ThreadPolicy> optional, int i) {
            this.mTraceKey = traceKey;
            this.mThread = Thread.currentThread();
            this.mThreadPolicyOptional = optional;
            this.mThreadPolicyMaskUnderLock = i;
        }
    }
}
