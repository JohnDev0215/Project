package org.apache.tools.ant.taskdefs.cvslib;

/* JADX INFO: loaded from: classes.dex */
class RCSFile {
    private String m_name;
    private String m_previousRevision;
    private String m_revision;

    RCSFile(String name, String rev) {
        this(name, rev, null);
    }

    RCSFile(String name, String revision, String previousRevision) {
        this.m_name = name;
        this.m_revision = revision;
        if (!revision.equals(previousRevision)) {
            this.m_previousRevision = previousRevision;
        }
    }

    String getName() {
        return this.m_name;
    }

    String getRevision() {
        return this.m_revision;
    }

    String getPreviousRevision() {
        return this.m_previousRevision;
    }
}
