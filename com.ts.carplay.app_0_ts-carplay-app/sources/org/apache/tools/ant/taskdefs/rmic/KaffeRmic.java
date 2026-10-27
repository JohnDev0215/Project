package org.apache.tools.ant.taskdefs.rmic;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ExecuteJava;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class KaffeRmic extends DefaultRmicAdapter {
    public static final String COMPILER_NAME = "kaffe";
    private static final String[] RMIC_CLASSNAMES = {"gnu.classpath.tools.rmi.rmic.RMIC", "gnu.java.rmi.rmic.RMIC", "kaffe.rmi.rmic.RMIC"};

    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    public boolean execute() throws BuildException {
        getRmic().log("Using Kaffe rmic", 3);
        Commandline cmd = setupRmicCommand();
        Class c = getRmicClass();
        if (c == null) {
            StringBuffer buf = new StringBuffer("Cannot use Kaffe rmic, as it is not available.  None of ");
            for (int i = 0; i < RMIC_CLASSNAMES.length; i++) {
                if (i != 0) {
                    buf.append(", ");
                }
                buf.append(RMIC_CLASSNAMES[i]);
            }
            buf.append(" have been found. A common solution is to set the environment variable JAVA_HOME or CLASSPATH.");
            throw new BuildException(buf.toString(), getRmic().getLocation());
        }
        cmd.setExecutable(c.getName());
        ExecuteJava ej = new ExecuteJava();
        ej.setJavaCommand(cmd);
        return ej.fork(getRmic()) == 0;
    }

    public static boolean isAvailable() {
        return getRmicClass() != null;
    }

    private static Class getRmicClass() {
        int i = 0;
        while (true) {
            String[] strArr = RMIC_CLASSNAMES;
            if (i < strArr.length) {
                try {
                    return Class.forName(strArr[i]);
                } catch (ClassNotFoundException e) {
                    i++;
                }
            } else {
                return null;
            }
        }
    }
}
