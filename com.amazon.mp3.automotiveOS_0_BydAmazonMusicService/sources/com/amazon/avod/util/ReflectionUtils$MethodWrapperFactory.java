package com.amazon.avod.util;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectionUtils$MethodWrapperFactory<T> {
    private final Exception mFailureCause;
    private final Optional<Method> mMethod;
    private final ReflectionUtils$FallbackFunction<Object[], T> mNoOpFallback = new ReflectionUtils$FallbackFunction<Object[], T>() { // from class: com.amazon.avod.util.ReflectionUtils$MethodWrapperFactory.2
        @Override // com.amazon.avod.util.ReflectionUtils$FallbackFunction
        public T run(Object obj, Object[] objArr) {
            return null;
        }
    };
    private final ReflectionUtils$MethodWrapper<T> mNoOpMethodWrapper = new ReflectionUtils$MethodWrapper<T>() { // from class: com.amazon.avod.util.ReflectionUtils$MethodWrapperFactory.3
        @Override // com.amazon.avod.util.ReflectionUtils$MethodWrapper
        public T call(Object... objArr) {
            return null;
        }
    };

    public ReflectionUtils$MethodWrapperFactory(boolean z, Class<?> cls, String str, Class<?>... clsArr) {
        Exception exc;
        Optional<Method> optionalAbsent;
        try {
            optionalAbsent = Optional.of(cls.getDeclaredMethod(str, clsArr));
            optionalAbsent.get().setAccessible(true);
            exc = null;
        } catch (NoSuchMethodException e) {
            exc = e;
            optionalAbsent = Optional.absent();
        } catch (SecurityException e2) {
            exc = e2;
            optionalAbsent = Optional.absent();
        }
        if (!z && exc != null) {
            DLog.exceptionf(exc, "EXCEPTION! Failed to find the method and set it to accessible", new Object[0]);
        }
        this.mMethod = optionalAbsent;
        this.mFailureCause = exc;
    }

    @Nonnull
    public ReflectionUtils$MethodWrapper<T> createForInstance(final Object obj) {
        if (!this.mMethod.isPresent()) {
            return this.mNoOpMethodWrapper;
        }
        final Method method = this.mMethod.get();
        final ReflectionUtils$FallbackFunction<Object[], T> reflectionUtils$FallbackFunction = this.mNoOpFallback;
        return new ReflectionUtils$MethodWrapper<T>(obj, method, reflectionUtils$FallbackFunction) { // from class: com.amazon.avod.util.ReflectionUtils$MethodWrapperBase

            @Nonnull
            private final ReflectionUtils$FallbackFunction<Object[], T> mFallback;

            @Nonnull
            private final Object mInstance;

            @Nonnull
            private final Method mMethod;

            {
                this.mInstance = Preconditions.checkNotNull(obj, "Instance");
                this.mMethod = (Method) Preconditions.checkNotNull(method, "Method");
                this.mFallback = (ReflectionUtils$FallbackFunction) Preconditions.checkNotNull(reflectionUtils$FallbackFunction, "Fallback");
            }

            @Override // com.amazon.avod.util.ReflectionUtils$MethodWrapper
            public T call(Object... objArr) {
                try {
                    return (T) this.mMethod.invoke(this.mInstance, objArr);
                } catch (IllegalAccessException e) {
                    DLog.exceptionf(e, "Exception was thrown when calling when invoking %s", this.mMethod.getName());
                    return this.mFallback.run(this.mInstance, objArr);
                } catch (InvocationTargetException e2) {
                    DLog.exceptionf(e2.getCause(), "Exception was thrown when calling when invoking %s", this.mMethod.getName());
                    return this.mFallback.run(this.mInstance, objArr);
                }
            }
        };
    }
}
