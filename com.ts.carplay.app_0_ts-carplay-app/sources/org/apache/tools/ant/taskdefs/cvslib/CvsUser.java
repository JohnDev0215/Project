package org.apache.tools.ant.taskdefs.cvslib;

import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class CvsUser {
    private String m_displayName;
    private String m_userID;

    public void setDisplayname(String displayName) {
        this.m_displayName = displayName;
    }

    public void setUserid(String userID) {
        this.m_userID = userID;
    }

    String getUserID() {
        return this.m_userID;
    }

    String getDisplayname() {
        return this.m_displayName;
    }

    void validate() throws BuildException {
        if (this.m_userID == null) {
            throw new BuildException("Username attribute must be set.");
        }
        if (this.m_displayName == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Displayname attribute must be set for userID ");
            stringBuffer.append(this.m_userID);
            String message = stringBuffer.toString();
            throw new BuildException(message);
        }
    }
}
