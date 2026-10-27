package org.apache.tools.ant.taskdefs.cvslib;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.TimeZone;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.util.DOMElementWriter;
import org.apache.tools.ant.util.DOMUtils;
import org.apache.tools.ant.util.DateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes.dex */
class ChangeLogWriter {
    private static final SimpleDateFormat c_outputDate = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
    private static final SimpleDateFormat c_outputTime = new SimpleDateFormat("HH:mm");
    private static final DOMElementWriter DOM_WRITER = new DOMElementWriter();

    ChangeLogWriter() {
    }

    static {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        c_outputDate.setTimeZone(utc);
        c_outputTime.setTimeZone(utc);
    }

    public void printChangeLog(PrintWriter output, CVSEntry[] entries) {
        try {
            output.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            Document doc = DOMUtils.newDocument();
            Element root = doc.createElement("changelog");
            DOM_WRITER.openElement(root, output, 0, "\t");
            output.println();
            for (CVSEntry entry : entries) {
                printEntry(doc, output, entry);
            }
            DOM_WRITER.closeElement(root, output, 0, "\t", true);
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    private void printEntry(Document doc, PrintWriter output, CVSEntry entry) throws IOException {
        Element ent = doc.createElement("entry");
        DOMUtils.appendTextElement(ent, "date", c_outputDate.format(entry.getDate()));
        DOMUtils.appendTextElement(ent, "time", c_outputTime.format(entry.getDate()));
        DOMUtils.appendCDATAElement(ent, "author", entry.getAuthor());
        Enumeration enumeration = entry.getFiles().elements();
        while (enumeration.hasMoreElements()) {
            RCSFile file = (RCSFile) enumeration.nextElement();
            Element f = DOMUtils.createChildElement(ent, TypeSelector.FileType.FILE);
            DOMUtils.appendCDATAElement(f, FilenameSelector.NAME_KEY, file.getName());
            DOMUtils.appendTextElement(f, "revision", file.getRevision());
            String previousRevision = file.getPreviousRevision();
            if (previousRevision != null) {
                DOMUtils.appendTextElement(f, "prevrevision", previousRevision);
            }
        }
        DOMUtils.appendCDATAElement(ent, "msg", entry.getComment());
        DOM_WRITER.write(ent, output, 1, "\t");
    }
}
