package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* JADX INFO: loaded from: classes.dex */
public class LoadProperties extends Task {
    static /* synthetic */ Class class$org$apache$tools$ant$taskdefs$LoadProperties;
    private File srcFile = null;
    private String resource = null;
    private Path classpath = null;
    private final Vector filterChains = new Vector();
    private String encoding = null;

    public final void setSrcFile(File srcFile) {
        this.srcFile = srcFile;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public final void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setClasspath(Path classpath) {
        Path path = this.classpath;
        if (path == null) {
            this.classpath = classpath;
        } else {
            path.append(classpath);
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    public Path getClasspath() {
        return this.classpath;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0155 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:? A[SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instruction units count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.LoadProperties.execute():void");
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public final void addFilterChain(FilterChain filter) {
        this.filterChains.addElement(filter);
    }
}
