package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class LogStreamHandler extends PumpStreamHandler {
    public LogStreamHandler(Task task, int outlevel, int errlevel) {
        super(new LogOutputStream(task, outlevel), new LogOutputStream(task, errlevel));
    }

    @Override // org.apache.tools.ant.taskdefs.PumpStreamHandler, org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void stop() {
        super.stop();
        try {
            getErr().close();
            getOut().close();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }
}
