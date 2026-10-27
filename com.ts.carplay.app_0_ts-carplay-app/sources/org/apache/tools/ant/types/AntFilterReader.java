package org.apache.tools.ant.types;

import java.util.Vector;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public final class AntFilterReader extends DataType implements Cloneable {
    private String className;
    private Path classpath;
    private final Vector parameters = new Vector();

    public final void setClassName(String className) {
        this.className = className;
    }

    public final String getClassName() {
        return this.className;
    }

    public final void addParam(Parameter param) {
        this.parameters.addElement(param);
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

    public void setClasspathRef(Reference r) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        createClasspath().setRefid(r);
    }

    public final Parameter[] getParams() {
        Parameter[] params = new Parameter[this.parameters.size()];
        this.parameters.copyInto(params);
        return params;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (!this.parameters.isEmpty() || this.className != null || this.classpath != null) {
            throw tooManyAttributes();
        }
        Object o = r.getReferencedObject(getProject());
        if (o instanceof AntFilterReader) {
            AntFilterReader afr = (AntFilterReader) o;
            setClassName(afr.getClassName());
            setClasspath(afr.getClasspath());
            Parameter[] p = afr.getParams();
            if (p != null) {
                for (Parameter parameter : p) {
                    addParam(parameter);
                }
            }
            super.setRefid(r);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(r.getRefId());
        stringBuffer.append(" doesn't refer to a FilterReader");
        String msg = stringBuffer.toString();
        throw new BuildException(msg);
    }
}
