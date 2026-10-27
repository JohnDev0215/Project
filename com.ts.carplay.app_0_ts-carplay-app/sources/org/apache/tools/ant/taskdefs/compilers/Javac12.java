package org.apache.tools.ant.taskdefs.compilers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.LogOutputStream;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class Javac12 extends DefaultCompilerAdapter {
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class class$java$io$OutputStream;
    static /* synthetic */ Class class$java$lang$String;

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws IOException, BuildException {
        Class<?> cls;
        Class<?> clsClass$;
        Class<?> clsClass$2;
        Class<?> clsClass$3;
        this.attributes.log("Using classic compiler", 3);
        Commandline cmd = setupJavacCommand(true);
        OutputStream logstr = new LogOutputStream((Task) this.attributes, 1);
        try {
            try {
                cls = Class.forName("sun.tools.javac.Main");
            } catch (Exception e) {
                e = e;
            } catch (Throwable th) {
                e = th;
            }
            try {
                Class<?>[] clsArr = new Class[2];
                if (class$java$io$OutputStream == null) {
                    clsClass$ = class$("java.io.OutputStream");
                    class$java$io$OutputStream = clsClass$;
                } else {
                    clsClass$ = class$java$io$OutputStream;
                }
                clsArr[0] = clsClass$;
                if (class$java$lang$String == null) {
                    clsClass$2 = class$("java.lang.String");
                    class$java$lang$String = clsClass$2;
                } else {
                    clsClass$2 = class$java$lang$String;
                }
                clsArr[1] = clsClass$2;
                Object compiler = cls.getConstructor(clsArr).newInstance(logstr, "javac");
                Class<?>[] clsArr2 = new Class[1];
                if (array$Ljava$lang$String == null) {
                    clsClass$3 = class$("[Ljava.lang.String;");
                    array$Ljava$lang$String = clsClass$3;
                } else {
                    clsClass$3 = array$Ljava$lang$String;
                }
                clsArr2[0] = clsClass$3;
                Method compile = cls.getMethod("compile", clsArr2);
                Boolean ok = (Boolean) compile.invoke(compiler, cmd.getArguments());
                boolean zBooleanValue = ok.booleanValue();
                try {
                    logstr.close();
                    return zBooleanValue;
                } catch (IOException e2) {
                    throw new BuildException(e2);
                }
            } catch (Exception e3) {
                e = e3;
                Exception ex = e;
                try {
                    if (ex instanceof BuildException) {
                        throw ((BuildException) ex);
                    }
                    throw new BuildException("Error starting classic compiler: ", ex, this.location);
                } catch (Throwable th2) {
                    e = th2;
                    try {
                        logstr.close();
                        throw e;
                    } catch (IOException e4) {
                        throw new BuildException(e4);
                    }
                }
            } catch (Throwable th3) {
                e = th3;
                logstr.close();
                throw e;
            }
        } catch (ClassNotFoundException e5) {
            try {
                throw new BuildException("Cannot use classic compiler, as it is not available.  A common solution is to set the environment variable JAVA_HOME to your jdk directory.", this.location);
            } catch (Throwable th4) {
                e = th4;
                logstr.close();
                throw e;
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
}
