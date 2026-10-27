package org.mockito.invocation;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import org.mockito.Incubating;
import org.mockito.mock.MockCreationSettings;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface InvocationFactory {
    @Incubating
    Invocation createInvocation(Object obj, MockCreationSettings mockCreationSettings, Method method, Callable callable, Object... objArr);
}
