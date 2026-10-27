package com.android.dx.mockito;

import com.android.dx.stock.ProxyBuilder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mockito.internal.creation.DelegatingMethod;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.exceptions.VerificationAwareInvocation;
import org.mockito.internal.invocation.ArgumentsProcessor;
import org.mockito.internal.progress.SequenceNumber;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.MockHandler;
import org.mockito.invocation.StubInfo;

/* JADX INFO: loaded from: classes.dex */
final class InvocationHandlerAdapter implements InvocationHandler {
    private MockHandler handler;

    public InvocationHandlerAdapter(MockHandler mockHandler) {
        this.handler = mockHandler;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (objArr == null) {
            objArr = new Object[0];
        }
        Object[] objArr2 = objArr;
        if (isEqualsMethod(method)) {
            return Boolean.valueOf(obj == objArr2[0]);
        }
        if (isHashCodeMethod(method)) {
            return Integer.valueOf(System.identityHashCode(obj));
        }
        return this.handler.handle(new ProxyInvocation(obj, method, objArr2, new DelegatingMethod(method), SequenceNumber.next(), new LocationImpl()));
    }

    public MockHandler getHandler() {
        return this.handler;
    }

    public void setHandler(MockHandler mockHandler) {
        this.handler = mockHandler;
    }

    private static boolean isEqualsMethod(Method method) {
        return method.getName().equals("equals") && method.getParameterTypes().length == 1 && method.getParameterTypes()[0] == Object.class;
    }

    private static boolean isHashCodeMethod(Method method) {
        return method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
    }

    private class ProxyInvocation implements Invocation, VerificationAwareInvocation {
        private final Object[] args;
        private boolean isIgnoredForVerification;
        private final Location location;
        private final Method method;
        private final Object proxy;
        private final Object[] rawArgs;
        private final int sequenceNumber;
        private StubInfo stubInfo;
        private boolean verified;

        private ProxyInvocation(Object obj, Method method, Object[] objArr, DelegatingMethod delegatingMethod, int i, Location location) {
            this.rawArgs = objArr;
            this.proxy = obj;
            this.method = method;
            this.sequenceNumber = i;
            this.location = location;
            this.args = ArgumentsProcessor.expandArgs(delegatingMethod, objArr);
        }

        @Override // org.mockito.invocation.InvocationOnMock
        public Object getMock() {
            return this.proxy;
        }

        @Override // org.mockito.invocation.InvocationOnMock
        public Method getMethod() {
            return this.method;
        }

        @Override // org.mockito.invocation.InvocationOnMock
        public Object[] getArguments() {
            return this.args;
        }

        @Override // org.mockito.invocation.InvocationOnMock
        public <T> T getArgument(int i) {
            return (T) this.args[i];
        }

        @Override // org.mockito.invocation.InvocationOnMock
        public Object callRealMethod() throws Throwable {
            if (Modifier.isAbstract(this.method.getModifiers())) {
                throw Reporter.cannotCallAbstractRealMethod();
            }
            return ProxyBuilder.callSuper(this.proxy, this.method, this.rawArgs);
        }

        @Override // org.mockito.invocation.Invocation, org.mockito.internal.exceptions.VerificationAwareInvocation
        public boolean isVerified() {
            return this.verified || this.isIgnoredForVerification;
        }

        @Override // org.mockito.invocation.Invocation
        public int getSequenceNumber() {
            return this.sequenceNumber;
        }

        @Override // org.mockito.invocation.Invocation, org.mockito.invocation.DescribedInvocation
        public Location getLocation() {
            return this.location;
        }

        @Override // org.mockito.invocation.Invocation
        public Object[] getRawArguments() {
            return this.rawArgs;
        }

        @Override // org.mockito.invocation.Invocation
        public Class<?> getRawReturnType() {
            return this.method.getReturnType();
        }

        @Override // org.mockito.invocation.Invocation
        public void markVerified() {
            this.verified = true;
        }

        @Override // org.mockito.invocation.Invocation
        public StubInfo stubInfo() {
            return this.stubInfo;
        }

        @Override // org.mockito.invocation.Invocation
        public void markStubbed(StubInfo stubInfo) {
            this.stubInfo = stubInfo;
        }

        @Override // org.mockito.invocation.Invocation
        public boolean isIgnoredForVerification() {
            return this.isIgnoredForVerification;
        }

        @Override // org.mockito.invocation.Invocation
        public void ignoreForVerification() {
            this.isIgnoredForVerification = true;
        }
    }
}
