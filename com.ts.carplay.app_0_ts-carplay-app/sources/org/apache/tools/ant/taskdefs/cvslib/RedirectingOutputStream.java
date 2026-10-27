package org.apache.tools.ant.taskdefs.cvslib;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.LogOutputStream;

/* JADX INFO: loaded from: classes.dex */
class RedirectingOutputStream extends LogOutputStream {
    private final ChangeLogParser parser;

    public RedirectingOutputStream(ChangeLogParser parser) {
        super((Task) null, 0);
        this.parser = parser;
    }

    @Override // org.apache.tools.ant.taskdefs.LogOutputStream
    protected void processLine(String line) {
        this.parser.stdout(line);
    }
}
