package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public class ExtendSelector extends BaseSelector {
    private String classname = null;
    private FileSelector dynselector = null;
    private Vector paramVec = new Vector();
    private Path classpath = null;

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void selectorCreate() {
        Class<?> cls;
        String str = this.classname;
        if (str != null && str.length() > 0) {
            try {
                if (this.classpath == null) {
                    cls = Class.forName(this.classname);
                } else {
                    AntClassLoader al = getProject().createClassLoader(this.classpath);
                    cls = Class.forName(this.classname, true, al);
                }
                this.dynselector = (FileSelector) cls.newInstance();
                Project project = getProject();
                if (project != null) {
                    project.setProjectReference(this.dynselector);
                    return;
                }
                return;
            } catch (ClassNotFoundException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Selector ");
                stringBuffer.append(this.classname);
                stringBuffer.append(" not initialized, no such class");
                setError(stringBuffer.toString());
                return;
            } catch (IllegalAccessException e2) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Selector ");
                stringBuffer2.append(this.classname);
                stringBuffer2.append(" not initialized, class not accessible");
                setError(stringBuffer2.toString());
                return;
            } catch (InstantiationException e3) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Selector ");
                stringBuffer3.append(this.classname);
                stringBuffer3.append(" not initialized, could not create class");
                setError(stringBuffer3.toString());
                return;
            }
        }
        setError("There is no classname specified");
    }

    public void addParam(Parameter p) {
        this.paramVec.addElement(p);
    }

    public final void setClasspath(Path classpath) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        Path path = this.classpath;
        if (path == null) {
            this.classpath = classpath;
        } else {
            path.append(classpath);
        }
    }

    public final Path createClasspath() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public final Path getClasspath() {
        return this.classpath;
    }

    public void setClasspathref(Reference r) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createClasspath().setRefid(r);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.dynselector == null) {
            selectorCreate();
        }
        String str = this.classname;
        if (str == null || str.length() < 1) {
            setError("The classname attribute is required");
            return;
        }
        FileSelector fileSelector = this.dynselector;
        if (fileSelector == null) {
            setError("Internal Error: The custom selector was not created");
        } else if (!(fileSelector instanceof ExtendFileSelector) && this.paramVec.size() > 0) {
            setError("Cannot set parameters on custom selector that does not implement ExtendFileSelector");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) throws BuildException {
        validate();
        if (this.paramVec.size() > 0 && (this.dynselector instanceof ExtendFileSelector)) {
            Parameter[] paramArray = new Parameter[this.paramVec.size()];
            this.paramVec.copyInto(paramArray);
            ((ExtendFileSelector) this.dynselector).setParameters(paramArray);
        }
        return this.dynselector.isSelected(basedir, filename, file);
    }
}
