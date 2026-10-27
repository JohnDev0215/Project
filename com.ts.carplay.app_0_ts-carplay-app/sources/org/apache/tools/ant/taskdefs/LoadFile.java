package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FilterChain;

/* JADX INFO: loaded from: classes.dex */
public class LoadFile extends Task {
    private File srcFile = null;
    private boolean failOnError = true;
    private String encoding = null;
    private String property = null;
    private final Vector filterChains = new Vector();

    public final void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public final void setProperty(String property) {
        this.property = property;
    }

    public final void setSrcFile(File srcFile) {
        this.srcFile = srcFile;
    }

    public final void setFailonerror(boolean fail) {
        this.failOnError = fail;
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x013a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void execute() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.LoadFile.execute():void");
    }

    public final void addFilterChain(FilterChain filter) {
        this.filterChains.addElement(filter);
    }
}
