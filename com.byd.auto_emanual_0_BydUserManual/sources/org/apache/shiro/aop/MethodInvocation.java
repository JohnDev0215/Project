package org.apache.shiro.aop;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public interface MethodInvocation {
    Object[] getArguments();

    Method getMethod();

    Object getThis();

    Object proceed() throws Throwable;
}
