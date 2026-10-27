package b.a.a.n;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.TypeCollector;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ASMUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f428b;

    static {
        String property = System.getProperty("java.vm.name");
        f427a = property;
        f428b = e(property);
    }

    public static boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 1 || cCharAt > 127 || cCharAt == '.') {
                return false;
            }
        }
        return true;
    }

    public static String b(Class<?> cls) {
        if (cls.isPrimitive()) {
            return d(cls);
        }
        if (cls.isArray()) {
            return "[" + b(cls.getComponentType());
        }
        return "L" + g(cls) + ";";
    }

    public static String c(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuilder sb = new StringBuilder((parameterTypes.length + 1) << 4);
        sb.append('(');
        for (Class<?> cls : parameterTypes) {
            sb.append(b(cls));
        }
        sb.append(')');
        sb.append(b(method.getReturnType()));
        return sb.toString();
    }

    public static String d(Class<?> cls) {
        if (Integer.TYPE == cls) {
            return "I";
        }
        if (Void.TYPE == cls) {
            return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
        }
        if (Boolean.TYPE == cls) {
            return "Z";
        }
        if (Character.TYPE == cls) {
            return "C";
        }
        if (Byte.TYPE == cls) {
            return "B";
        }
        if (Short.TYPE == cls) {
            return ExifInterface.LATITUDE_SOUTH;
        }
        if (Float.TYPE == cls) {
            return "F";
        }
        if (Long.TYPE == cls) {
            return "J";
        }
        if (Double.TYPE == cls) {
            return "D";
        }
        throw new IllegalStateException("Type: " + cls.getCanonicalName() + " is not a primitive type");
    }

    public static boolean e(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("dalvik") || lowerCase.contains("lemur");
    }

    public static String[] f(AccessibleObject accessibleObject) {
        Class<?>[] parameterTypes;
        Class<?> declaringClass;
        Annotation[][] annotationArrC0;
        String name;
        String strName;
        if (f428b) {
            return new String[0];
        }
        if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            parameterTypes = method.getParameterTypes();
            name = method.getName();
            declaringClass = method.getDeclaringClass();
            annotationArrC0 = l.d0(method);
        } else {
            Constructor constructor = (Constructor) accessibleObject;
            parameterTypes = constructor.getParameterTypes();
            declaringClass = constructor.getDeclaringClass();
            annotationArrC0 = l.c0(constructor);
            name = "<init>";
        }
        if (parameterTypes.length == 0) {
            return new String[0];
        }
        ClassLoader classLoader = declaringClass.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        InputStream resourceAsStream = classLoader.getResourceAsStream(declaringClass.getName().replace('.', '/') + ".class");
        try {
            if (resourceAsStream == null) {
                return new String[0];
            }
            b.a.a.i.b bVar = new b.a.a.i.b(resourceAsStream, false);
            TypeCollector typeCollector = new TypeCollector(name, parameterTypes);
            bVar.a(typeCollector);
            String[] strArrB = typeCollector.b();
            for (int i = 0; i < strArrB.length; i++) {
                Annotation[] annotationArr = annotationArrC0[i];
                if (annotationArr != null) {
                    for (int i2 = 0; i2 < annotationArr.length; i2++) {
                        if ((annotationArr[i2] instanceof b.a.a.h.b) && (strName = ((b.a.a.h.b) annotationArr[i2]).name()) != null && strName.length() > 0) {
                            strArrB[i] = strName;
                        }
                    }
                }
            }
            return strArrB;
        } catch (IOException unused) {
            return new String[0];
        } finally {
            e.a(resourceAsStream);
        }
    }

    public static String g(Class<?> cls) {
        if (!cls.isArray()) {
            return !cls.isPrimitive() ? cls.getName().replace('.', '/') : d(cls);
        }
        return "[" + b(cls.getComponentType());
    }
}
