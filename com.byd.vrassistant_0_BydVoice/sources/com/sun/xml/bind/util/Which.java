package com.sun.xml.bind.util;

import com.android.multidex.ClassPathElement;
import java.net.URL;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class Which {
    public static String which(Class cls) {
        return which(cls.getName(), SecureLoader.getClassClassLoader(cls));
    }

    public static String which(String str, ClassLoader classLoader) {
        String str2 = str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION;
        if (classLoader == null) {
            classLoader = SecureLoader.getSystemClassLoader();
        }
        URL resource = classLoader.getResource(str2);
        if (resource != null) {
            return resource.toString();
        }
        return null;
    }
}
