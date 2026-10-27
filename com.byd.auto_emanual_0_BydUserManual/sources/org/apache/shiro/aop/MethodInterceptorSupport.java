package org.apache.shiro.aop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/* JADX INFO: loaded from: classes.dex */
public abstract class MethodInterceptorSupport implements MethodInterceptor {
    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
