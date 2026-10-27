package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.android.multidex.ClassPathElement;
import com.sun.xml.bind.Util;
import com.sun.xml.bind.v2.bytecode.ClassTailor;
import com.sun.xml.bind.v2.runtime.RuntimeUtil;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public abstract class OptimizedAccessorFactory {
    private static final String fieldTemplateName;
    private static final Logger logger = Util.getClassLogger();
    private static final String methodTemplateName;

    private OptimizedAccessorFactory() {
    }

    static {
        fieldTemplateName = FieldAccessor_Byte.class.getName().substring(0, r0.length() - 4).replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
        methodTemplateName = MethodAccessor_Byte.class.getName().substring(0, r0.length() - 4).replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
    }

    public static final <B, V> Accessor<B, V> get(Method method, Method method2) {
        Class<?> clsPrepare;
        if (method.getParameterTypes().length != 0) {
            return null;
        }
        Class<?>[] parameterTypes = method2.getParameterTypes();
        if (parameterTypes.length != 1 || parameterTypes[0] != method.getReturnType() || method2.getReturnType() != Void.TYPE || method.getDeclaringClass() != method2.getDeclaringClass() || Modifier.isPrivate(method.getModifiers()) || Modifier.isPrivate(method2.getModifiers())) {
            return null;
        }
        Class<?> cls = parameterTypes[0];
        String strReplace = cls.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, '_');
        if (cls.isArray()) {
            String strReplace2 = cls.getComponentType().getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, '_');
            String str = "AOf_";
            while (strReplace2.startsWith("[L")) {
                strReplace2 = strReplace2.substring(2);
                str = str + "AOf_";
            }
            strReplace = str + strReplace2;
        }
        String str2 = ClassTailor.toVMClassName(method.getDeclaringClass()) + "$JaxbAccessorM_" + method.getName() + '_' + method2.getName() + '_' + strReplace;
        if (cls.isPrimitive()) {
            clsPrepare = AccessorInjector.prepare(method.getDeclaringClass(), methodTemplateName + RuntimeUtil.primitiveToBox.get(cls).getSimpleName(), str2, ClassTailor.toVMClassName(Bean.class), ClassTailor.toVMClassName(method.getDeclaringClass()), "get_" + cls.getName(), method.getName(), "set_" + cls.getName(), method2.getName());
        } else {
            clsPrepare = AccessorInjector.prepare(method.getDeclaringClass(), methodTemplateName + "Ref", str2, ClassTailor.toVMClassName(Bean.class), ClassTailor.toVMClassName(method.getDeclaringClass()), ClassTailor.toVMClassName(Ref.class), ClassTailor.toVMClassName(cls), "()" + ClassTailor.toVMTypeName(Ref.class), "()" + ClassTailor.toVMTypeName(cls), "(" + ClassTailor.toVMTypeName(Ref.class) + ")V", "(" + ClassTailor.toVMTypeName(cls) + ")V", "get_ref", method.getName(), "set_ref", method2.getName());
        }
        if (clsPrepare == null) {
            return null;
        }
        Accessor<B, V> accessorInstanciate = instanciate(clsPrepare);
        if (accessorInstanciate != null) {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.log(Level.FINE, "Using optimized Accessor for {0} and {1}", new Object[]{method, method2});
            }
        }
        return accessorInstanciate;
    }

    public static final <B, V> Accessor<B, V> get(Field field) {
        Class<?> clsPrepare;
        int modifiers = field.getModifiers();
        if (Modifier.isPrivate(modifiers) || Modifier.isFinal(modifiers)) {
            return null;
        }
        String str = ClassTailor.toVMClassName(field.getDeclaringClass()) + "$JaxbAccessorF_" + field.getName();
        if (field.getType().isPrimitive()) {
            clsPrepare = AccessorInjector.prepare(field.getDeclaringClass(), fieldTemplateName + RuntimeUtil.primitiveToBox.get(field.getType()).getSimpleName(), str, ClassTailor.toVMClassName(Bean.class), ClassTailor.toVMClassName(field.getDeclaringClass()), "f_" + field.getType().getName(), field.getName());
        } else {
            clsPrepare = AccessorInjector.prepare(field.getDeclaringClass(), fieldTemplateName + "Ref", str, ClassTailor.toVMClassName(Bean.class), ClassTailor.toVMClassName(field.getDeclaringClass()), ClassTailor.toVMClassName(Ref.class), ClassTailor.toVMClassName(field.getType()), ClassTailor.toVMTypeName(Ref.class), ClassTailor.toVMTypeName(field.getType()), "f_ref", field.getName());
        }
        if (clsPrepare == null) {
            return null;
        }
        Accessor<B, V> accessorInstanciate = instanciate(clsPrepare);
        if (accessorInstanciate != null) {
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.log(Level.FINE, "Using optimized Accessor for {0}", field);
            }
        }
        return accessorInstanciate;
    }

    private static <B, V> Accessor<B, V> instanciate(Class cls) {
        try {
            return (Accessor) cls.newInstance();
        } catch (IllegalAccessException e) {
            logger.log(Level.INFO, "failed to load an optimized Accessor", (Throwable) e);
            return null;
        } catch (InstantiationException e2) {
            logger.log(Level.INFO, "failed to load an optimized Accessor", (Throwable) e2);
            return null;
        } catch (SecurityException e3) {
            logger.log(Level.INFO, "failed to load an optimized Accessor", (Throwable) e3);
            return null;
        }
    }
}
