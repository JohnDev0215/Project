package org.apache.tools.ant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class TaskAdapter extends Task implements TypeAdapter {
    static /* synthetic */ Class class$org$apache$tools$ant$Project;
    private Object proxy;

    public static void checkTaskClass(Class taskClass, Project project) {
        try {
            Method executeM = taskClass.getMethod("execute", null);
            if (!Void.TYPE.equals(executeM.getReturnType())) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("return type of execute() should be void but was \"");
                stringBuffer.append(executeM.getReturnType());
                stringBuffer.append("\" in ");
                stringBuffer.append(taskClass);
                project.log(stringBuffer.toString(), 1);
            }
        } catch (LinkageError e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not load ");
            stringBuffer2.append(taskClass);
            stringBuffer2.append(": ");
            stringBuffer2.append(e);
            String message = stringBuffer2.toString();
            project.log(message, 0);
            throw new BuildException(message, e);
        } catch (NoSuchMethodException e2) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("No public execute() in ");
            stringBuffer3.append(taskClass);
            String message2 = stringBuffer3.toString();
            project.log(message2, 0);
            throw new BuildException(message2);
        }
    }

    @Override // org.apache.tools.ant.TypeAdapter
    public void checkProxyClass(Class proxyClass) {
        checkTaskClass(proxyClass, getProject());
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Class<?> clsClass$;
        try {
            try {
                Class<?> cls = this.proxy.getClass();
                try {
                    Class<?>[] clsArr = new Class[1];
                    if (class$org$apache$tools$ant$Project == null) {
                        clsClass$ = class$("org.apache.tools.ant.Project");
                        class$org$apache$tools$ant$Project = clsClass$;
                    } else {
                        clsClass$ = class$org$apache$tools$ant$Project;
                    }
                    clsArr[0] = clsClass$;
                    Method setProjectM = cls.getMethod("setProject", clsArr);
                    if (setProjectM != null) {
                        setProjectM.invoke(this.proxy, getProject());
                    }
                } catch (Exception e) {
                    ex = e;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Error setting project in ");
                    stringBuffer.append(this.proxy.getClass());
                    log(stringBuffer.toString(), 0);
                    throw new BuildException(ex);
                }
            } catch (Exception e2) {
                ex = e2;
            }
        } catch (NoSuchMethodException e3) {
        }
        try {
            try {
                try {
                    Method executeM = this.proxy.getClass().getMethod("execute", new Class[0]);
                    if (executeM != null) {
                        executeM.invoke(this.proxy, null);
                        return;
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("No public execute() in ");
                    stringBuffer2.append(this.proxy.getClass());
                    log(stringBuffer2.toString(), 0);
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("No public execute() in ");
                    stringBuffer3.append(this.proxy.getClass());
                    throw new BuildException(stringBuffer3.toString());
                } catch (Exception e4) {
                    ex = e4;
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Error in ");
                    stringBuffer4.append(this.proxy.getClass());
                    log(stringBuffer4.toString(), 3);
                    throw new BuildException(ex);
                }
            } catch (InvocationTargetException ie) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("Error in ");
                stringBuffer5.append(this.proxy.getClass());
                log(stringBuffer5.toString(), 3);
                Throwable t = ie.getTargetException();
                if (t instanceof BuildException) {
                    throw ((BuildException) t);
                }
                throw new BuildException(t);
            }
        } catch (Exception e5) {
            ex = e5;
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    @Override // org.apache.tools.ant.TypeAdapter
    public void setProxy(Object o) {
        this.proxy = o;
    }

    @Override // org.apache.tools.ant.TypeAdapter
    public Object getProxy() {
        return this.proxy;
    }
}
