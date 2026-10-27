package org.apache.tools.ant.taskdefs.rmic;

import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.LogOutputStream;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class SunRmic extends DefaultRmicAdapter {
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class class$java$io$OutputStream;
    static /* synthetic */ Class class$java$lang$String;

    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    public boolean execute() throws ClassNotFoundException, BuildException {
        Exception ex;
        Class<?> cls;
        Class<?> clsClass$;
        Class<?> clsClass$2;
        Class<?> clsClass$3;
        getRmic().log("Using SUN rmic compiler", 3);
        Commandline cmd = setupRmicCommand();
        LogOutputStream logstr = new LogOutputStream((Task) getRmic(), 1);
        try {
            try {
                cls = Class.forName("sun.rmi.rmic.Main");
            } catch (Exception e) {
                ex = e;
            } catch (Throwable th) {
                ex = th;
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
                Object rmic = cls.getConstructor(clsArr).newInstance(logstr, "rmic");
                Class<?>[] clsArr2 = new Class[1];
                if (array$Ljava$lang$String == null) {
                    clsClass$3 = class$("[Ljava.lang.String;");
                    array$Ljava$lang$String = clsClass$3;
                } else {
                    clsClass$3 = array$Ljava$lang$String;
                }
                clsArr2[0] = clsClass$3;
                Method doRmic = cls.getMethod("compile", clsArr2);
                Boolean ok = (Boolean) doRmic.invoke(rmic, cmd.getArguments());
                boolean zBooleanValue = ok.booleanValue();
                try {
                    logstr.close();
                    return zBooleanValue;
                } catch (IOException e2) {
                    throw new BuildException(e2);
                }
            } catch (Exception e3) {
                ex = e3;
                try {
                    if (ex instanceof BuildException) {
                        throw ((BuildException) ex);
                    }
                    throw new BuildException("Error starting SUN rmic: ", ex, getRmic().getLocation());
                } catch (Throwable th2) {
                    ex = th2;
                    try {
                        logstr.close();
                        throw ex;
                    } catch (IOException e4) {
                        throw new BuildException(e4);
                    }
                }
            } catch (Throwable th3) {
                ex = th3;
                logstr.close();
                throw ex;
            }
        } catch (ClassNotFoundException e5) {
            try {
                throw new BuildException("Cannot use SUN rmic, as it is not available.  A common solution is to set the environment variable JAVA_HOME or CLASSPATH.", getRmic().getLocation());
            } catch (Throwable th4) {
                ex = th4;
                logstr.close();
                throw ex;
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
