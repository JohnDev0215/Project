package org.apache.tools.ant.taskdefs.compilers;

import java.lang.reflect.Method;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class Javac13 extends DefaultCompilerAdapter {
    private static final int MODERN_COMPILER_SUCCESS = 0;

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using modern compiler", 3);
        Commandline cmd = setupModernJavacCommand();
        try {
            Class<?> cls = Class.forName("com.sun.tools.javac.Main");
            Object compiler = cls.newInstance();
            Method compile = cls.getMethod("compile", new String[0].getClass());
            int result = ((Integer) compile.invoke(compiler, cmd.getArguments())).intValue();
            return result == 0;
        } catch (Exception ex) {
            if (ex instanceof BuildException) {
                throw ((BuildException) ex);
            }
            throw new BuildException("Error starting modern compiler", ex, this.location);
        }
    }
}
