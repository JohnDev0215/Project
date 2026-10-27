package org.apache.tools.ant.taskdefs.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public class CompilerAdapterFactory {
    private static final String MODERN_COMPILER = "com.sun.tools.javac.Main";
    static /* synthetic */ Class class$org$apache$tools$ant$taskdefs$compilers$CompilerAdapterFactory;

    private CompilerAdapterFactory() {
    }

    public static CompilerAdapter getCompiler(String compilerType, Task task) throws BuildException {
        boolean isClassicCompilerSupported = true;
        if (!JavaEnvUtils.isJavaVersion("1.1") && !JavaEnvUtils.isJavaVersion("1.2") && !JavaEnvUtils.isJavaVersion("1.3")) {
            isClassicCompilerSupported = false;
        }
        if (compilerType.equalsIgnoreCase("jikes")) {
            return new Jikes();
        }
        if (compilerType.equalsIgnoreCase("extJavac")) {
            return new JavacExternal();
        }
        if (compilerType.equalsIgnoreCase("classic") || compilerType.equalsIgnoreCase("javac1.1") || compilerType.equalsIgnoreCase("javac1.2")) {
            if (isClassicCompilerSupported) {
                return new Javac12();
            }
            task.log("This version of java does not support the classic compiler; upgrading to modern", 1);
            compilerType = "modern";
        }
        if (compilerType.equalsIgnoreCase("modern") || compilerType.equalsIgnoreCase("javac1.3") || compilerType.equalsIgnoreCase("javac1.4") || compilerType.equalsIgnoreCase("javac1.5")) {
            if (doesModernCompilerExist()) {
                return new Javac13();
            }
            if (isClassicCompilerSupported) {
                task.log("Modern compiler not found - looking for classic compiler", 1);
                return new Javac12();
            }
            throw new BuildException("Unable to find a javac compiler;\ncom.sun.tools.javac.Main is not on the classpath.\nPerhaps JAVA_HOME does not point to the JDK");
        }
        if (compilerType.equalsIgnoreCase("jvc") || compilerType.equalsIgnoreCase("microsoft")) {
            return new Jvc();
        }
        if (compilerType.equalsIgnoreCase("kjc")) {
            return new Kjc();
        }
        if (compilerType.equalsIgnoreCase("gcj")) {
            return new Gcj();
        }
        if (compilerType.equalsIgnoreCase("sj") || compilerType.equalsIgnoreCase("symantec")) {
            return new Sj();
        }
        return resolveClassName(compilerType);
    }

    private static boolean doesModernCompilerExist() {
        Class clsClass$;
        try {
            Class.forName(MODERN_COMPILER);
            return true;
        } catch (ClassNotFoundException e) {
            try {
                if (class$org$apache$tools$ant$taskdefs$compilers$CompilerAdapterFactory == null) {
                    clsClass$ = class$("org.apache.tools.ant.taskdefs.compilers.CompilerAdapterFactory");
                    class$org$apache$tools$ant$taskdefs$compilers$CompilerAdapterFactory = clsClass$;
                } else {
                    clsClass$ = class$org$apache$tools$ant$taskdefs$compilers$CompilerAdapterFactory;
                }
                ClassLoader cl = clsClass$.getClassLoader();
                if (cl != null) {
                    cl.loadClass(MODERN_COMPILER);
                    return true;
                }
                return false;
            } catch (ClassNotFoundException e2) {
                return false;
            }
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private static CompilerAdapter resolveClassName(String className) throws BuildException {
        ClassCastException cce;
        try {
            try {
                try {
                    Object o = Class.forName(className).newInstance();
                    return (CompilerAdapter) o;
                } catch (ClassCastException e) {
                    cce = e;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(className);
                    stringBuffer.append(" isn't the classname of ");
                    stringBuffer.append("a compiler adapter.");
                    throw new BuildException(stringBuffer.toString(), cce);
                } catch (Throwable th) {
                    t = th;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Compiler Adapter ");
                    stringBuffer2.append(className);
                    stringBuffer2.append(" caused an interesting exception.");
                    throw new BuildException(stringBuffer2.toString(), t);
                }
            } catch (ClassCastException e2) {
                cce = e2;
            } catch (Throwable th2) {
                t = th2;
            }
        } catch (ClassNotFoundException cnfe) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Compiler Adapter '");
            stringBuffer3.append(className);
            stringBuffer3.append("' can't be found.");
            throw new BuildException(stringBuffer3.toString(), cnfe);
        }
    }
}
