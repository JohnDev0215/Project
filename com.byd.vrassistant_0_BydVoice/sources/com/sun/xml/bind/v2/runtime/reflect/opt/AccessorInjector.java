package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.Util;
import com.sun.xml.bind.v2.bytecode.ClassTailor;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.dynamic.ClassFileLocator;

/* JADX INFO: loaded from: classes3.dex */
class AccessorInjector {
    private static final ClassLoader CLASS_LOADER;
    private static final Logger logger;
    protected static final boolean noOptimize;

    AccessorInjector() {
    }

    static {
        Logger classLogger = Util.getClassLogger();
        logger = classLogger;
        boolean z = Util.getSystemProperty(new StringBuilder().append(ClassTailor.class.getName()).append(".noOptimize").toString()) != null;
        noOptimize = z;
        if (z) {
            classLogger.info("The optimized code generation is disabled");
        }
        CLASS_LOADER = SecureLoader.getClassClassLoader(AccessorInjector.class);
    }

    public static Class<?> prepare(Class cls, String str, String str2, String... strArr) {
        if (noOptimize) {
            return null;
        }
        try {
            ClassLoader classClassLoader = SecureLoader.getClassClassLoader(cls);
            if (classClassLoader == null) {
                return null;
            }
            Class<?> clsFind = Injector.find(classClassLoader, str2);
            if (clsFind == null) {
                byte[] bArrTailor = tailor(str, str2, strArr);
                if (bArrTailor == null) {
                    return null;
                }
                clsFind = Injector.inject(classClassLoader, str2, bArrTailor);
                if (clsFind == null) {
                    Injector.find(classClassLoader, str2);
                }
            }
            return clsFind;
        } catch (SecurityException e) {
            logger.log(Level.INFO, "Unable to create an optimized TransducedAccessor ", (Throwable) e);
            return null;
        }
    }

    private static byte[] tailor(String str, String str2, String... strArr) {
        InputStream systemResourceAsStream;
        ClassLoader classLoader = CLASS_LOADER;
        if (classLoader != null) {
            systemResourceAsStream = classLoader.getResourceAsStream(str + ClassFileLocator.CLASS_FILE_EXTENSION);
        } else {
            systemResourceAsStream = ClassLoader.getSystemResourceAsStream(str + ClassFileLocator.CLASS_FILE_EXTENSION);
        }
        if (systemResourceAsStream == null) {
            return null;
        }
        return ClassTailor.tailor(systemResourceAsStream, str, str2, strArr);
    }
}
