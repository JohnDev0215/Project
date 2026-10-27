package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class AntlibDefinition extends Task {
    private ClassLoader antlibClassLoader;
    private String uri = "";

    public void setURI(String uri) throws BuildException {
        if (uri.equals(ProjectHelper.ANT_CORE_URI)) {
            uri = "";
        }
        if (uri.startsWith("ant:")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Attempt to use a reserved URI ");
            stringBuffer.append(uri);
            throw new BuildException(stringBuffer.toString());
        }
        this.uri = uri;
    }

    public String getURI() {
        return this.uri;
    }

    public void setAntlibClassLoader(ClassLoader classLoader) {
        this.antlibClassLoader = classLoader;
    }

    public ClassLoader getAntlibClassLoader() {
        return this.antlibClassLoader;
    }
}
