package org.apache.shiro.event.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
public class SingleArgumentMethodEventListener implements TypedEventListener {
    private final Method method;
    private final Object target;

    public SingleArgumentMethodEventListener(Object obj, Method method) {
        this.target = obj;
        this.method = method;
        getMethodArgumentType(method);
        assertPublicMethod(method);
    }

    public Object getTarget() {
        return this.target;
    }

    public Method getMethod() {
        return this.method;
    }

    private void assertPublicMethod(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            throw new IllegalArgumentException("Event handler method [" + method + "] must be public.");
        }
    }

    @Override // org.apache.shiro.event.support.EventListener
    public boolean accepts(Object obj) {
        return obj != null && getEventType().isInstance(obj);
    }

    @Override // org.apache.shiro.event.support.TypedEventListener
    public Class getEventType() {
        return getMethodArgumentType(getMethod());
    }

    @Override // org.apache.shiro.event.support.EventListener
    public void onEvent(Object obj) {
        Method method = getMethod();
        try {
            method.invoke(getTarget(), obj);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to invoke event handler method [" + method + "].", e);
        }
    }

    protected Class getMethodArgumentType(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Event handler methods must accept a single argument.");
        }
        return parameterTypes[0];
    }
}
