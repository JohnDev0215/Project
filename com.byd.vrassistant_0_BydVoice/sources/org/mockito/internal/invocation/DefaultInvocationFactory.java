package org.mockito.internal.invocation;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import org.mockito.internal.creation.bytebuddy.MockMethodInterceptor;
import org.mockito.internal.invocation.RealMethod;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationFactory;
import org.mockito.mock.MockCreationSettings;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultInvocationFactory implements InvocationFactory {
    @Override // org.mockito.invocation.InvocationFactory
    public Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, Callable callable, Object... objArr) {
        return MockMethodInterceptor.createInvocation(obj, method, objArr, new RealMethod.FromCallable(callable), mockCreationSettings);
    }
}
