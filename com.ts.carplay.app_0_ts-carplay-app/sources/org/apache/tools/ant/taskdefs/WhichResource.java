package org.apache.tools.ant.taskdefs;

import java.net.URL;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

/* JADX INFO: loaded from: classes.dex */
public class WhichResource extends Task {
    private String classname;
    private Path classpath;
    private String property;
    private String resource;

    public void setClasspath(Path cp) {
        Path path = this.classpath;
        if (path == null) {
            this.classpath = cp;
        } else {
            path.append(cp);
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    private void validate() {
        int setcount = 0;
        if (this.classname != null) {
            setcount = 0 + 1;
        }
        if (this.resource != null) {
            setcount++;
        }
        if (setcount == 0) {
            throw new BuildException("One of classname or resource must be specified");
        }
        if (setcount > 1) {
            throw new BuildException("Only one of classname or resource can be specified");
        }
        if (this.property == null) {
            throw new BuildException("No property defined");
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        validate();
        if (this.classpath != null) {
            Project project = getProject();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("using user supplied classpath: ");
            stringBuffer.append(this.classpath);
            project.log(stringBuffer.toString(), 4);
            this.classpath = this.classpath.concatSystemClasspath("ignore");
        } else {
            Path path = new Path(getProject());
            this.classpath = path;
            this.classpath = path.concatSystemClasspath("only");
            Project project2 = getProject();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("using system classpath: ");
            stringBuffer2.append(this.classpath);
            project2.log(stringBuffer2.toString(), 4);
        }
        AntClassLoader loader = new AntClassLoader(getProject().getCoreLoader(), getProject(), this.classpath, false);
        if (this.classname != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(this.classname.replace('.', '/'));
            stringBuffer3.append(".class");
            this.resource = stringBuffer3.toString();
        }
        String str = this.resource;
        if (str == null) {
            throw new BuildException("One of class or resource is required");
        }
        if (str.startsWith("/")) {
            this.resource = this.resource.substring(1);
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("Searching for ");
        stringBuffer4.append(this.resource);
        log(stringBuffer4.toString(), 3);
        URL url = loader.getResource(this.resource);
        if (url != null) {
            String location = url.toExternalForm();
            getProject().setNewProperty(this.property, location);
        }
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setClass(String classname) {
        this.classname = classname;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
