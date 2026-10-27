package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.MethodInterceptorSupport;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthorizingMethodInterceptor extends MethodInterceptorSupport {
    protected abstract void assertAuthorized(MethodInvocation methodInvocation) throws AuthorizationException;

    @Override // org.apache.shiro.aop.MethodInterceptor
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        assertAuthorized(methodInvocation);
        return methodInvocation.proceed();
    }
}
