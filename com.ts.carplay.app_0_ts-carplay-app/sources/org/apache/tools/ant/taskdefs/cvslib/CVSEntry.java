package org.apache.tools.ant.taskdefs.cvslib;

import java.util.Date;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
class CVSEntry {
    private String m_author;
    private final String m_comment;
    private Date m_date;
    private final Vector m_files = new Vector();

    public CVSEntry(Date date, String author, String comment) {
        this.m_date = date;
        this.m_author = author;
        this.m_comment = comment;
    }

    public void addFile(String file, String revision) {
        this.m_files.addElement(new RCSFile(file, revision));
    }

    public void addFile(String file, String revision, String previousRevision) {
        this.m_files.addElement(new RCSFile(file, revision, previousRevision));
    }

    Date getDate() {
        return this.m_date;
    }

    void setAuthor(String author) {
        this.m_author = author;
    }

    String getAuthor() {
        return this.m_author;
    }

    String getComment() {
        return this.m_comment;
    }

    Vector getFiles() {
        return this.m_files;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getAuthor());
        stringBuffer.append("\n");
        stringBuffer.append(getDate());
        stringBuffer.append("\n");
        stringBuffer.append(getFiles());
        stringBuffer.append("\n");
        stringBuffer.append(getComment());
        return stringBuffer.toString();
    }
}
