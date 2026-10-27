package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.ClasspathUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class DefBase extends AntlibDefinition {
    private ClasspathUtils.Delegate cpDelegate;
    private ClassLoader createdLoader;

    public void setReverseLoader(boolean reverseLoader) {
        getDelegate().setReverseLoader(reverseLoader);
        log("The reverseloader attribute is DEPRECATED. It will be removed", 1);
    }

    public Path getClasspath() {
        return getDelegate().getClasspath();
    }

    public boolean isReverseLoader() {
        return getDelegate().isReverseLoader();
    }

    public String getLoaderId() {
        return getDelegate().getClassLoadId();
    }

    public String getClasspathId() {
        return getDelegate().getClassLoadId();
    }

    public void setClasspath(Path classpath) {
        getDelegate().setClasspath(classpath);
    }

    public Path createClasspath() {
        return getDelegate().createClasspath();
    }

    public void setClasspathRef(Reference r) {
        getDelegate().setClasspathref(r);
    }

    public void setLoaderRef(Reference r) {
        getDelegate().setLoaderRef(r);
    }

    protected ClassLoader createLoader() {
        if (getAntlibClassLoader() != null && this.cpDelegate == null) {
            return getAntlibClassLoader();
        }
        if (this.cpDelegate == null) {
            this.cpDelegate = ClasspathUtils.getDelegate(this);
        }
        if (this.createdLoader == null) {
            ClassLoader classLoader = this.cpDelegate.getClassLoader();
            this.createdLoader = classLoader;
            ((AntClassLoader) classLoader).addSystemPackageRoot("org.apache.tools.ant");
        }
        return this.createdLoader;
    }

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        super.init();
    }

    private ClasspathUtils.Delegate getDelegate() {
        if (this.cpDelegate == null) {
            this.cpDelegate = ClasspathUtils.getDelegate(this);
        }
        return this.cpDelegate;
    }
}
