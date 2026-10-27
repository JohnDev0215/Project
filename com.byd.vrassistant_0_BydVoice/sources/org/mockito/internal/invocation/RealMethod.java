package org.mockito.internal.invocation;

import java.io.Serializable;
import java.util.concurrent.Callable;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;

/* JADX INFO: loaded from: classes4.dex */
public interface RealMethod extends Serializable {
    Object invoke() throws Throwable;

    boolean isInvokable();

    public enum IsIllegal implements RealMethod {
        INSTANCE;

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return false;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() {
            throw new IllegalStateException();
        }
    }

    public static class FromCallable implements RealMethod {
        private static final long serialVersionUID = 47957363950483625L;
        private final Callable<?> callable;

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return true;
        }

        public FromCallable(Callable<?> callable) {
            this.callable = callable;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() throws Throwable {
            try {
                return this.callable.call();
            } catch (Throwable th) {
                new ConditionalStackTraceFilter().filter(th);
                throw th;
            }
        }
    }
}
