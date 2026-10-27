package org.apache.tools.ant.taskdefs.cvslib;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.AbstractCvsTask;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.util.DOMElementWriter;
import org.apache.tools.ant.util.DOMUtils;
import org.apache.tools.ant.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes.dex */
public class CvsTagDiff extends AbstractCvsTask {
    private static final DOMElementWriter DOM_WRITER = new DOMElementWriter();
    static final String FILE_HAS_CHANGED = " changed from revision ";
    static final String FILE_IS_NEW = " is new;";
    static final String FILE_STRING = "File ";
    static final String FILE_WAS_REMOVED = " is removed";
    static final String REVISION = "revision ";
    static final String TO_STRING = " to ";
    private File mydestfile;
    private String myendDate;
    private String myendTag;
    private FileUtils myfileUtils = FileUtils.newFileUtils();
    private String mypackage;
    private String mystartDate;
    private String mystartTag;

    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask
    public void setPackage(String p) {
        this.mypackage = p;
    }

    public void setStartTag(String s) {
        this.mystartTag = s;
    }

    public void setStartDate(String s) {
        this.mystartDate = s;
    }

    public void setEndTag(String s) {
        this.myendTag = s;
    }

    public void setEndDate(String s) {
        this.myendDate = s;
    }

    public void setDestFile(File f) {
        this.mydestfile = f;
    }

    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask, org.apache.tools.ant.Task
    public void execute() throws BuildException {
        validate();
        addCommandArgument("rdiff");
        addCommandArgument("-s");
        if (this.mystartTag != null) {
            addCommandArgument("-r");
            addCommandArgument(this.mystartTag);
        } else {
            addCommandArgument("-D");
            addCommandArgument(this.mystartDate);
        }
        if (this.myendTag != null) {
            addCommandArgument("-r");
            addCommandArgument(this.myendTag);
        } else {
            addCommandArgument("-D");
            addCommandArgument(this.myendDate);
        }
        StringTokenizer myTokenizer = new StringTokenizer(this.mypackage);
        while (myTokenizer.hasMoreTokens()) {
            addCommandArgument(myTokenizer.nextToken());
        }
        setCommand("");
        File tmpFile = null;
        try {
            tmpFile = this.myfileUtils.createTempFile("cvstagdiff", ".log", null);
            tmpFile.deleteOnExit();
            setOutput(tmpFile);
            super.execute();
            CvsTagEntry[] entries = parseRDiff(tmpFile);
            writeTagDiff(entries);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.tools.ant.taskdefs.cvslib.CvsTagEntry[] parseRDiff(java.io.File r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.cvslib.CvsTagDiff.parseRDiff(java.io.File):org.apache.tools.ant.taskdefs.cvslib.CvsTagEntry[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void writeTagDiff(org.apache.tools.ant.taskdefs.cvslib.CvsTagEntry[] r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.cvslib.CvsTagDiff.writeTagDiff(org.apache.tools.ant.taskdefs.cvslib.CvsTagEntry[]):void");
    }

    private void writeTagEntry(Document doc, PrintWriter writer, CvsTagEntry entry) throws IOException {
        Element ent = doc.createElement("entry");
        Element f = DOMUtils.createChildElement(ent, TypeSelector.FileType.FILE);
        DOMUtils.appendCDATAElement(f, FilenameSelector.NAME_KEY, entry.getFile());
        if (entry.getRevision() != null) {
            DOMUtils.appendTextElement(f, "revision", entry.getRevision());
        }
        if (entry.getPreviousRevision() != null) {
            DOMUtils.appendTextElement(f, "prevrevision", entry.getPreviousRevision());
        }
        DOM_WRITER.write(ent, writer, 1, "\t");
    }

    private void validate() throws BuildException {
        if (this.mypackage == null) {
            throw new BuildException("Package/module must be set.");
        }
        if (this.mydestfile == null) {
            throw new BuildException("Destfile must be set.");
        }
        if (this.mystartTag == null && this.mystartDate == null) {
            throw new BuildException("Start tag or start date must be set.");
        }
        if (this.mystartTag != null && this.mystartDate != null) {
            throw new BuildException("Only one of start tag and start date must be set.");
        }
        if (this.myendTag == null && this.myendDate == null) {
            throw new BuildException("End tag or end date must be set.");
        }
        if (this.myendTag != null && this.myendDate != null) {
            throw new BuildException("Only one of end tag and end date must be set.");
        }
    }
}
