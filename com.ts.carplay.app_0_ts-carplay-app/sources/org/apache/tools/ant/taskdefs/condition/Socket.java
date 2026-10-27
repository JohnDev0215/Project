package org.apache.tools.ant.taskdefs.condition;

import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;

/* JADX INFO: loaded from: classes.dex */
public class Socket extends ProjectComponent implements Condition {
    private String server = null;
    private int port = 0;

    public void setServer(String server) {
        this.server = server;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (this.server == null) {
            throw new BuildException("No server specified in socket condition");
        }
        if (this.port == 0) {
            throw new BuildException("No port specified in socket condition");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Checking for listener at ");
        stringBuffer.append(this.server);
        stringBuffer.append(":");
        stringBuffer.append(this.port);
        log(stringBuffer.toString(), 3);
        java.net.Socket s = null;
        try {
            try {
                new java.net.Socket(this.server, this.port).close();
                return true;
            } catch (IOException e) {
                return true;
            }
        } catch (IOException e2) {
            if (0 != 0) {
                try {
                    s.close();
                } catch (IOException e3) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    s.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }
}
