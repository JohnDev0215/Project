package com.android.dx.mockito;

import com.android.dx.stock.ProxyBuilder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Set;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.exceptions.stacktrace.StackTraceCleaner;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.StackTraceCleanerProvider;

/* JADX INFO: loaded from: classes.dex */
public final class DexmakerMockMaker implements MockMaker, StackTraceCleanerProvider {
    private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();

    @Override // org.mockito.plugins.MockMaker
    public <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler) {
        Class<T> typeToMock = mockCreationSettings.getTypeToMock();
        Set<Class<?>> extraInterfaces = mockCreationSettings.getExtraInterfaces();
        Class<?>[] clsArr = (Class[]) extraInterfaces.toArray(new Class[extraInterfaces.size()]);
        InvocationHandlerAdapter invocationHandlerAdapter = new InvocationHandlerAdapter(mockHandler);
        if (typeToMock.isInterface()) {
            Class[] clsArr2 = new Class[clsArr.length + 1];
            clsArr2[0] = typeToMock;
            System.arraycopy(clsArr, 0, clsArr2, 1, clsArr.length);
            return (T) Proxy.newProxyInstance(typeToMock.getClassLoader(), clsArr2, invocationHandlerAdapter);
        }
        try {
            T t = (T) this.unsafeAllocator.newInstance(ProxyBuilder.forClass(typeToMock).implementing(clsArr).buildProxyClass());
            ProxyBuilder.setInvocationHandler(t, invocationHandlerAdapter);
            return t;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new MockitoException("Failed to mock " + typeToMock, e2);
        }
    }

    @Override // org.mockito.plugins.MockMaker
    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        getInvocationHandlerAdapter(obj).setHandler(mockHandler);
    }

    @Override // org.mockito.plugins.MockMaker
    public MockMaker.TypeMockability isTypeMockable(final Class<?> cls) {
        return new MockMaker.TypeMockability() { // from class: com.android.dx.mockito.DexmakerMockMaker.1
            @Override // org.mockito.plugins.MockMaker.TypeMockability
            public boolean mockable() {
                return (cls.isPrimitive() || Modifier.isFinal(cls.getModifiers())) ? false : true;
            }

            @Override // org.mockito.plugins.MockMaker.TypeMockability
            public String nonMockableReason() {
                return cls.isPrimitive() ? "primitive type" : Modifier.isFinal(cls.getModifiers()) ? "final or anonymous class" : "not handled type";
            }
        };
    }

    @Override // org.mockito.plugins.MockMaker
    public MockHandler getHandler(Object obj) {
        InvocationHandlerAdapter invocationHandlerAdapter = getInvocationHandlerAdapter(obj);
        if (invocationHandlerAdapter != null) {
            return invocationHandlerAdapter.getHandler();
        }
        return null;
    }

    @Override // org.mockito.plugins.StackTraceCleanerProvider
    public StackTraceCleaner getStackTraceCleaner(final StackTraceCleaner stackTraceCleaner) {
        return new StackTraceCleaner() { // from class: com.android.dx.mockito.DexmakerMockMaker.2
            @Override // org.mockito.exceptions.stacktrace.StackTraceCleaner
            public boolean isIn(StackTraceElement stackTraceElement) {
                String className = stackTraceElement.getClassName();
                return (!stackTraceCleaner.isIn(stackTraceElement) || className.endsWith("_Proxy") || className.startsWith("$Proxy") || className.startsWith("java.lang.reflect.Proxy") || (className.startsWith("com.android.dx.mockito.") && !className.startsWith("com.android.dx.mockito.tests"))) ? false : true;
            }
        };
    }

    private InvocationHandlerAdapter getInvocationHandlerAdapter(Object obj) {
        if (obj == null) {
            return null;
        }
        if (Proxy.isProxyClass(obj.getClass())) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(obj);
            if (invocationHandler instanceof InvocationHandlerAdapter) {
                return (InvocationHandlerAdapter) invocationHandler;
            }
            return null;
        }
        if (!ProxyBuilder.isProxyClass(obj.getClass())) {
            return null;
        }
        InvocationHandler invocationHandler2 = ProxyBuilder.getInvocationHandler(obj);
        if (invocationHandler2 instanceof InvocationHandlerAdapter) {
            return (InvocationHandlerAdapter) invocationHandler2;
        }
        return null;
    }
}
