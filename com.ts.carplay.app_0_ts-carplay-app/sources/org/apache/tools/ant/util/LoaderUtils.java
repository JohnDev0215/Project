package org.apache.tools.ant.util;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.launch.Locator;

/* JADX INFO: loaded from: classes.dex */
public class LoaderUtils {
    static /* synthetic */ Class class$org$apache$tools$ant$util$LoaderUtils;

    public static void setContextClassLoader(ClassLoader loader) {
        Thread currentThread = Thread.currentThread();
        currentThread.setContextClassLoader(loader);
    }

    public static ClassLoader getContextClassLoader() {
        Thread currentThread = Thread.currentThread();
        return currentThread.getContextClassLoader();
    }

    public static boolean isContextLoaderAvailable() {
        return true;
    }

    private static File normalizeSource(File source) {
        if (source != null) {
            FileUtils fileUtils = FileUtils.newFileUtils();
            try {
                return fileUtils.normalize(source.getAbsolutePath());
            } catch (BuildException e) {
                return source;
            }
        }
        return source;
    }

    public static File getClassSource(Class c) {
        return normalizeSource(Locator.getClassSource(c));
    }

    public static File getResourceSource(ClassLoader c, String resource) {
        if (c == null) {
            Class clsClass$ = class$org$apache$tools$ant$util$LoaderUtils;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.tools.ant.util.LoaderUtils");
                class$org$apache$tools$ant$util$LoaderUtils = clsClass$;
            }
            c = clsClass$.getClassLoader();
        }
        return normalizeSource(Locator.getResourceSource(c, resource));
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}
