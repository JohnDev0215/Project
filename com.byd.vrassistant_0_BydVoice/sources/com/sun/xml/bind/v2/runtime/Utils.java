package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.model.nav.Navigator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
final class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
    static final Navigator<Type, Class, Field, Method> REFLECTION_NAVIGATOR;

    static {
        try {
            final Class<?> cls = Class.forName("com.sun.xml.bind.v2.model.nav.ReflectionNavigator");
            REFLECTION_NAVIGATOR = (Navigator) ((Method) AccessController.doPrivileged(new PrivilegedAction<Method>() { // from class: com.sun.xml.bind.v2.runtime.Utils.1
                @Override // java.security.PrivilegedAction
                public Method run() {
                    try {
                        Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
                        declaredMethod.setAccessible(true);
                        return declaredMethod;
                    } catch (NoSuchMethodException unused) {
                        throw new IllegalStateException("ReflectionNavigator.getInstance can't be found");
                    }
                }
            })).invoke(null, new Object[0]);
        } catch (ClassNotFoundException unused) {
            throw new IllegalStateException("Can't find ReflectionNavigator class");
        } catch (IllegalAccessException unused2) {
            throw new IllegalStateException("ReflectionNavigator.getInstance method is inaccessible");
        } catch (SecurityException e) {
            LOGGER.log(Level.FINE, "Unable to access ReflectionNavigator.getInstance", (Throwable) e);
            throw e;
        } catch (InvocationTargetException unused3) {
            throw new IllegalStateException("ReflectionNavigator.getInstance throws the exception");
        }
    }

    private Utils() {
    }
}
