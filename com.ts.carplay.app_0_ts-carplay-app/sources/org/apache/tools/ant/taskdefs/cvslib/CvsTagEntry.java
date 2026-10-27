package org.apache.tools.ant.taskdefs.cvslib;

/* JADX INFO: loaded from: classes.dex */
class CvsTagEntry {
    String m_filename;
    String m_prevRevision;
    String m_revision;

    public CvsTagEntry(String filename) {
        this(filename, null, null);
    }

    public CvsTagEntry(String filename, String revision) {
        this(filename, revision, null);
    }

    public CvsTagEntry(String filename, String revision, String prevRevision) {
        this.m_filename = filename;
        this.m_revision = revision;
        this.m_prevRevision = prevRevision;
    }

    public String getFile() {
        return this.m_filename;
    }

    public String getRevision() {
        return this.m_revision;
    }

    public String getPreviousRevision() {
        return this.m_prevRevision;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.m_filename);
        String str = this.m_revision;
        if (str == null) {
            buffer.append(" was removed");
            if (this.m_prevRevision != null) {
                buffer.append("; previous revision was ");
                buffer.append(this.m_prevRevision);
            }
        } else if (str != null && this.m_prevRevision == null) {
            buffer.append(" is new; current revision is ");
            buffer.append(this.m_revision);
        } else if (this.m_revision != null && this.m_prevRevision != null) {
            buffer.append(" has changed from ");
            buffer.append(this.m_prevRevision);
            buffer.append(" to ");
            buffer.append(this.m_revision);
        }
        return buffer.toString();
    }
}
