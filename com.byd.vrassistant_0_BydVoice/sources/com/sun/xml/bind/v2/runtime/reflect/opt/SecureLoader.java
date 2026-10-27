package com.sun.xml.bind.v2.runtime.reflect.opt;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes3.dex */
class SecureLoader {
    SecureLoader() {
    }

    static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.xml.bind.v2.runtime.reflect.opt.SecureLoader.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
    }

    static ClassLoader getClassClassLoader(final Class cls) {
        if (System.getSecurityManager() == null) {
            return cls.getClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.xml.bind.v2.runtime.reflect.opt.SecureLoader.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return cls.getClassLoader();
            }
        });
    }

    static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.xml.bind.v2.runtime.reflect.opt.SecureLoader.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }
}
