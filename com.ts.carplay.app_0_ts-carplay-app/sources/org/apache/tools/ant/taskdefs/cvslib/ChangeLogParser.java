package org.apache.tools.ant.taskdefs.cvslib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
class ChangeLogParser {
    private static final int GET_COMMENT = 3;
    private static final int GET_DATE = 2;
    private static final int GET_FILE = 1;
    private static final int GET_PREVIOUS_REV = 5;
    private static final int GET_REVISION = 4;
    private static final SimpleDateFormat c_inputDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String m_author;
    private String m_comment;
    private String m_date;
    private String m_file;
    private String m_previousRevision;
    private String m_revision;
    private int m_status = 1;
    private final Hashtable m_entries = new Hashtable();

    ChangeLogParser() {
    }

    static {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        c_inputDate.setTimeZone(utc);
    }

    CVSEntry[] getEntrySetAsArray() {
        CVSEntry[] array = new CVSEntry[this.m_entries.size()];
        Enumeration e = this.m_entries.elements();
        int i = 0;
        while (e.hasMoreElements()) {
            array[i] = (CVSEntry) e.nextElement();
            i++;
        }
        return array;
    }

    public void stdout(String line) {
        int i = this.m_status;
        if (i == 1) {
            reset();
            processFile(line);
            return;
        }
        if (i == 2) {
            processDate(line);
            return;
        }
        if (i == 3) {
            processComment(line);
        } else if (i == 4) {
            processRevision(line);
        } else if (i == 5) {
            processGetPreviousRevision(line);
        }
    }

    private void processComment(String line) {
        String lineSeparator = System.getProperty("line.separator");
        if (line.startsWith("======")) {
            int end = this.m_comment.length() - lineSeparator.length();
            this.m_comment = this.m_comment.substring(0, end);
            saveEntry();
            this.m_status = 1;
            return;
        }
        if (line.startsWith("----------------------------")) {
            int end2 = this.m_comment.length() - lineSeparator.length();
            this.m_comment = this.m_comment.substring(0, end2);
            this.m_status = 5;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.m_comment);
            stringBuffer.append(line);
            stringBuffer.append(lineSeparator);
            this.m_comment = stringBuffer.toString();
        }
    }

    private void processFile(String line) {
        if (line.startsWith("Working file:")) {
            this.m_file = line.substring(14, line.length());
            this.m_status = 4;
        }
    }

    private void processRevision(String line) {
        if (line.startsWith("revision")) {
            this.m_revision = line.substring(9);
            this.m_status = 2;
        } else if (line.startsWith("======")) {
            this.m_status = 1;
        }
    }

    private void processDate(String line) {
        if (line.startsWith("date:")) {
            this.m_date = line.substring(6, 25);
            String lineData = line.substring(line.indexOf(";") + 1);
            this.m_author = lineData.substring(10, lineData.indexOf(";"));
            this.m_status = 3;
            this.m_comment = "";
        }
    }

    private void processGetPreviousRevision(String line) {
        if (!line.startsWith("revision")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unexpected line from CVS: ");
            stringBuffer.append(line);
            throw new IllegalStateException(stringBuffer.toString());
        }
        this.m_previousRevision = line.substring(9);
        saveEntry();
        this.m_revision = this.m_previousRevision;
        this.m_status = 2;
    }

    private void saveEntry() {
        CVSEntry entry;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.m_date);
        stringBuffer.append(this.m_author);
        stringBuffer.append(this.m_comment);
        String entryKey = stringBuffer.toString();
        if (!this.m_entries.containsKey(entryKey)) {
            entry = new CVSEntry(parseDate(this.m_date), this.m_author, this.m_comment);
            this.m_entries.put(entryKey, entry);
        } else {
            entry = (CVSEntry) this.m_entries.get(entryKey);
        }
        entry.addFile(this.m_file, this.m_revision, this.m_previousRevision);
    }

    private Date parseDate(String date) {
        try {
            return c_inputDate.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    private void reset() {
        this.m_file = null;
        this.m_date = null;
        this.m_author = null;
        this.m_comment = null;
        this.m_revision = null;
        this.m_previousRevision = null;
    }
}
