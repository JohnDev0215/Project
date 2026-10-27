package org.apache.tools.ant.taskdefs.cvslib;

import com.ts.lib.settings.common.constant.Constants;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;
import org.apache.tools.ant.taskdefs.AbstractCvsTask;

/* JADX INFO: loaded from: classes.dex */
public class CvsVersion extends AbstractCvsTask {
    static final long MULTIPLY = 100;
    static final long VERSION_1_11_2 = 11102;
    private String clientVersion;
    private String clientVersionProperty;
    private String serverVersion;
    private String serverVersionProperty;

    public String getClientVersion() {
        return this.clientVersion;
    }

    public String getServerVersion() {
        return this.serverVersion;
    }

    public void setClientVersionProperty(String clientVersionProperty) {
        this.clientVersionProperty = clientVersionProperty;
    }

    public void setServerVersionProperty(String serverVersionProperty) {
        this.serverVersionProperty = serverVersionProperty;
    }

    public boolean supportsCvsLogWithSOption() {
        if (this.serverVersion == null) {
            return false;
        }
        StringTokenizer mySt = new StringTokenizer(this.serverVersion, ".");
        long counter = 10000;
        long version = 0;
        while (mySt.hasMoreTokens()) {
            String s = mySt.nextToken();
            int i = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                i++;
            }
            String s2 = s.substring(0, i);
            version += Long.parseLong(s2) * counter;
            if (counter == 1) {
                break;
            }
            counter /= MULTIPLY;
        }
        return version >= VERSION_1_11_2;
    }

    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask, org.apache.tools.ant.Task
    public void execute() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        setOutputStream(bos);
        OutputStream berr = new ByteArrayOutputStream();
        setErrorStream(berr);
        setCommand(Constants.VERSION_SERVICE);
        super.execute();
        String output = bos.toString();
        StringTokenizer st = new StringTokenizer(output);
        boolean client = false;
        boolean server = false;
        boolean cvs = false;
        while (st.hasMoreTokens()) {
            String currentToken = st.nextToken();
            if (currentToken.equals("Client:")) {
                client = true;
            } else if (currentToken.equals("Server:")) {
                server = true;
            } else if (currentToken.equals("(CVS)")) {
                cvs = true;
            }
            if (client && cvs) {
                if (st.hasMoreTokens()) {
                    this.clientVersion = st.nextToken();
                }
                client = false;
                cvs = false;
            } else if (server && cvs) {
                if (st.hasMoreTokens()) {
                    this.serverVersion = st.nextToken();
                }
                server = false;
                cvs = false;
            }
        }
        if (this.clientVersionProperty != null) {
            getProject().setNewProperty(this.clientVersionProperty, this.clientVersion);
        }
        if (this.serverVersionProperty != null) {
            getProject().setNewProperty(this.serverVersionProperty, this.serverVersion);
        }
    }
}
