package org.apache.tools.ant.taskdefs.rmic;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class RmicAdapterFactory {
    private RmicAdapterFactory() {
    }

    public static RmicAdapter getRmic(String rmicType, Task task) throws BuildException {
        if (rmicType.equalsIgnoreCase("sun")) {
            return new SunRmic();
        }
        if (rmicType.equalsIgnoreCase(KaffeRmic.COMPILER_NAME)) {
            return new KaffeRmic();
        }
        if (rmicType.equalsIgnoreCase("weblogic")) {
            return new WLRmic();
        }
        return resolveClassName(rmicType);
    }

    private static RmicAdapter resolveClassName(String className) throws BuildException {
        ClassCastException cce;
        try {
            try {
                try {
                    Object o = Class.forName(className).newInstance();
                    return (RmicAdapter) o;
                } catch (ClassCastException e) {
                    cce = e;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(className);
                    stringBuffer.append(" isn't the classname of ");
                    stringBuffer.append("a rmic adapter.");
                    throw new BuildException(stringBuffer.toString(), cce);
                } catch (Throwable th) {
                    t = th;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(className);
                    stringBuffer2.append(" caused an interesting ");
                    stringBuffer2.append("exception.");
                    throw new BuildException(stringBuffer2.toString(), t);
                }
            } catch (ClassCastException e2) {
                cce = e2;
            } catch (Throwable th2) {
                t = th2;
            }
        } catch (ClassNotFoundException cnfe) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(className);
            stringBuffer3.append(" can't be found.");
            throw new BuildException(stringBuffer3.toString(), cnfe);
        }
    }
}
