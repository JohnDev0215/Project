package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ListenerSet<LISTENER> extends CopyOnWriteArraySet<LISTENER> {
    private static boolean unitTestMode;
    private final LISTENER proxy;
    private static final Logger logger = LoggerFactory.getLogger(ListenerSet.class.getSimpleName());
    public static final ManualExecutor UNIT_TEST_EXECUTOR = new ManualExecutor();

    public enum DispatchStrategy {
        MAIN_THREAD { // from class: com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy.1
            @Override // com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy
            Dispatcher createDispatcher() {
                if (ListenerSet.unitTestMode) {
                    return DispatchStrategy.UNIT_TEST.createDispatcher();
                }
                return new ExecutorDispatcher(ThreadUtils.getMainThreadExecutor(), false);
            }
        },
        BACKGROUND_THREAD { // from class: com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy.2
            @Override // com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy
            Dispatcher createDispatcher() {
                if (ListenerSet.unitTestMode) {
                    return DispatchStrategy.UNIT_TEST.createDispatcher();
                }
                return new ExecutorDispatcher(ThreadUtils.getBackgroundExecutor(), true);
            }
        },
        SYNCHRONOUS { // from class: com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy.3
            @Override // com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy
            Dispatcher createDispatcher() {
                return new SynchronousDispatcher();
            }
        },
        UNIT_TEST { // from class: com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy.4
            @Override // com.amazon.music.media.playback.util.ListenerSet.DispatchStrategy
            Dispatcher createDispatcher() {
                return new ExecutorDispatcher(ListenerSet.UNIT_TEST_EXECUTOR, true);
            }
        };

        abstract Dispatcher createDispatcher();
    }

    private interface Dispatcher {
        <LISTENER> Object invoke(Method method, Object[] objArr, NewCallResolver newCallResolver, Set<LISTENER> set);
    }

    public enum DuplicateStrategy {
        ALLOW_DUPLICATES { // from class: com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy.1
            @Override // com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy
            NewCallResolver createResolver() {
                return new NewCallResolver() { // from class: com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy.1.1
                    @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolver
                    public NewCallResolution resolve(@NonNull List<Call> list, Call call) {
                        return NewCallResolution.KEEP_BOTH;
                    }
                };
            }
        },
        REMOVE_DUPLICATES { // from class: com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy.2
            @Override // com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy
            NewCallResolver createResolver() {
                return new NewCallResolver() { // from class: com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy.2.1
                    @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolver
                    public NewCallResolution resolve(@NonNull List<Call> list, Call call) {
                        if (call.sameAs(list.isEmpty() ? null : list.get(list.size() - 1))) {
                            return NewCallResolution.KEEP_PREVIOUS_ONLY;
                        }
                        return NewCallResolution.KEEP_BOTH;
                    }
                };
            }
        },
        REMOVE_ALL_OTHERS { // from class: com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy.3
            @Override // com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy
            NewCallResolver createResolver() {
                return new NewCallResolver() { // from class: com.amazon.music.media.playback.util.ListenerSet.DuplicateStrategy.3.1
                    @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolver
                    public NewCallResolution resolve(@NonNull List<Call> list, Call call) {
                        return NewCallResolution.KEEP_NEW_ONLY;
                    }
                };
            }
        };

        abstract NewCallResolver createResolver();
    }

    private enum NewCallResolution {
        KEEP_BOTH { // from class: com.amazon.music.media.playback.util.ListenerSet.NewCallResolution.1
            @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolution
            void apply(List<Call> list, Call call) {
                list.add(call);
            }
        },
        KEEP_PREVIOUS_ONLY { // from class: com.amazon.music.media.playback.util.ListenerSet.NewCallResolution.2
            @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolution
            void apply(List<Call> list, Call call) {
            }
        },
        KEEP_NEW_ONLY { // from class: com.amazon.music.media.playback.util.ListenerSet.NewCallResolution.3
            @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolution
            void apply(List<Call> list, Call call) {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1);
                }
                list.add(call);
            }
        },
        KEEP_NONE { // from class: com.amazon.music.media.playback.util.ListenerSet.NewCallResolution.4
            @Override // com.amazon.music.media.playback.util.ListenerSet.NewCallResolution
            void apply(List<Call> list, Call call) {
                if (list.isEmpty()) {
                    return;
                }
                list.remove(list.size() - 1);
            }
        };

        abstract void apply(List<Call> list, Call call);
    }

    private interface NewCallResolver {
        NewCallResolution resolve(@NonNull List<Call> list, Call call);
    }

    private static class Call {
        final Object[] args;
        final Method method;

        Call(Method method, Object[] objArr) {
            this.method = method;
            this.args = objArr;
        }

        void callOn(Object obj) {
            try {
                this.method.invoke(obj, this.args);
            } catch (Exception e) {
                StrictMode.crashIfStrict(obj.getClass().getName() + "." + this.method.getName() + "() threw " + e, e);
            }
        }

        boolean sameAs(Call call) {
            if (call == null || this.method != call.method) {
                return false;
            }
            Object[] objArr = this.args;
            boolean z = objArr == null;
            Object[] objArr2 = call.args;
            if (z != (objArr2 == null)) {
                return false;
            }
            if (objArr != null) {
                if (objArr.length != objArr2.length) {
                    return false;
                }
                int length = objArr.length;
                do {
                    length--;
                    if (length >= 0) {
                    }
                } while (ObjectUtils.equal(this.args[length], call.args[length]));
                return false;
            }
            return true;
        }
    }

    private static class ExecutorDispatcher implements Dispatcher {
        private final Executor executor;
        private final Map<Method, Boolean> inProgressMap;
        private final Object lock = new Object();
        private final Map<Method, List<Call>> callQueues = new HashMap();

        ExecutorDispatcher(Executor executor, boolean z) {
            this.executor = (Executor) Validate.notNull(executor);
            this.inProgressMap = z ? new HashMap() : null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
        @Override // com.amazon.music.media.playback.util.ListenerSet.Dispatcher
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public <LISTENER> java.lang.Object invoke(final java.lang.reflect.Method r7, java.lang.Object[] r8, com.amazon.music.media.playback.util.ListenerSet.NewCallResolver r9, final java.util.Set<LISTENER> r10) {
            /*
                r6 = this;
                java.lang.Class r0 = r7.getReturnType()
                java.lang.Object r0 = com.amazon.music.media.playback.util.ObjectUtils.getDefaultValue(r0)
                boolean r1 = r10.isEmpty()
                if (r1 == 0) goto Lf
                return r0
            Lf:
                java.lang.Object r1 = r6.lock
                monitor-enter(r1)
                com.amazon.music.media.playback.util.ListenerSet$Call r2 = new com.amazon.music.media.playback.util.ListenerSet$Call     // Catch: java.lang.Throwable -> L6d
                r2.<init>(r7, r8)     // Catch: java.lang.Throwable -> L6d
                java.util.Map<java.lang.reflect.Method, java.util.List<com.amazon.music.media.playback.util.ListenerSet$Call>> r8 = r6.callQueues     // Catch: java.lang.Throwable -> L6d
                java.lang.Object r8 = r8.get(r7)     // Catch: java.lang.Throwable -> L6d
                java.util.List r8 = (java.util.List) r8     // Catch: java.lang.Throwable -> L6d
                if (r8 != 0) goto L2b
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L6d
                r8.<init>()     // Catch: java.lang.Throwable -> L6d
                java.util.Map<java.lang.reflect.Method, java.util.List<com.amazon.music.media.playback.util.ListenerSet$Call>> r3 = r6.callQueues     // Catch: java.lang.Throwable -> L6d
                r3.put(r7, r8)     // Catch: java.lang.Throwable -> L6d
            L2b:
                int r3 = r8.size()     // Catch: java.lang.Throwable -> L6d
                com.amazon.music.media.playback.util.ListenerSet$NewCallResolution r9 = r9.resolve(r8, r2)     // Catch: java.lang.Throwable -> L6d
                r9.apply(r8, r2)     // Catch: java.lang.Throwable -> L6d
                int r9 = r8.size()     // Catch: java.lang.Throwable -> L6d
                if (r9 != 0) goto L3e
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L6d
                return r0
            L3e:
                java.util.Map<java.lang.reflect.Method, java.lang.Boolean> r2 = r6.inProgressMap     // Catch: java.lang.Throwable -> L6d
                r4 = 1
                r5 = 0
                if (r2 == 0) goto L5d
                java.lang.Object r9 = r2.get(r7)     // Catch: java.lang.Throwable -> L6d
                java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L6d
                if (r9 == 0) goto L55
                boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L6d
                if (r9 != 0) goto L53
                goto L55
            L53:
                r4 = r5
                goto L5f
            L55:
                java.util.Map<java.lang.reflect.Method, java.lang.Boolean> r9 = r6.inProgressMap     // Catch: java.lang.Throwable -> L6d
                java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L6d
                r9.put(r7, r2)     // Catch: java.lang.Throwable -> L6d
                goto L5f
            L5d:
                if (r9 <= r3) goto L53
            L5f:
                if (r4 == 0) goto L6b
                java.util.concurrent.Executor r9 = r6.executor     // Catch: java.lang.Throwable -> L6d
                com.amazon.music.media.playback.util.ListenerSet$ExecutorDispatcher$2 r2 = new com.amazon.music.media.playback.util.ListenerSet$ExecutorDispatcher$2     // Catch: java.lang.Throwable -> L6d
                r2.<init>()     // Catch: java.lang.Throwable -> L6d
                r9.execute(r2)     // Catch: java.lang.Throwable -> L6d
            L6b:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L6d
                return r0
            L6d:
                r7 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L6d
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.util.ListenerSet.ExecutorDispatcher.invoke(java.lang.reflect.Method, java.lang.Object[], com.amazon.music.media.playback.util.ListenerSet$NewCallResolver, java.util.Set):java.lang.Object");
        }
    }

    private static class SynchronousDispatcher implements Dispatcher {
        private SynchronousDispatcher() {
        }

        @Override // com.amazon.music.media.playback.util.ListenerSet.Dispatcher
        public <LISTENER> Object invoke(Method method, Object[] objArr, NewCallResolver newCallResolver, Set<LISTENER> set) {
            if (!set.isEmpty()) {
                Call call = new Call(method, objArr);
                Iterator<LISTENER> it = set.iterator();
                while (it.hasNext()) {
                    call.callOn(it.next());
                }
            }
            return ObjectUtils.getDefaultValue(method.getReturnType());
        }
    }

    public ListenerSet(Class<LISTENER> cls, DispatchStrategy dispatchStrategy, DuplicateStrategy duplicateStrategy) {
        this(cls, dispatchStrategy.createDispatcher(), duplicateStrategy.createResolver());
    }

    private ListenerSet(Class<LISTENER> cls, final Dispatcher dispatcher, final NewCallResolver newCallResolver) {
        Validate.notNull(newCallResolver);
        this.proxy = cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.amazon.music.media.playback.util.ListenerSet.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                return dispatcher.invoke(method, objArr, newCallResolver, ListenerSet.this);
            }
        }));
    }

    public LISTENER proxy() {
        return this.proxy;
    }
}
