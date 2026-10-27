package org.apache.tools.ant;

import java.io.IOException;
import java.io.PrintStream;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class NoBannerLogger extends DefaultLogger {
    protected String targetName;

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent event) {
        this.targetName = event.getTarget().getName();
    }

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent event) {
        this.targetName = null;
    }

    @Override // org.apache.tools.ant.DefaultLogger, org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent event) throws IOException {
        if (event.getPriority() > this.msgOutputLevel || event.getMessage() == null || "".equals(event.getMessage().trim())) {
            return;
        }
        if (this.targetName != null) {
            PrintStream printStream = this.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(StringUtils.LINE_SEP);
            stringBuffer.append(this.targetName);
            stringBuffer.append(":");
            printStream.println(stringBuffer.toString());
            this.targetName = null;
        }
        super.messageLogged(event);
    }
}
