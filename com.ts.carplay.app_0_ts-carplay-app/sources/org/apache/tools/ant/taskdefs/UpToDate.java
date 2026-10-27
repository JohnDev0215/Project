package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.MergingMapper;
import org.apache.tools.ant.util.SourceFileScanner;

/* JADX INFO: loaded from: classes.dex */
public class UpToDate extends Task implements Condition {
    private String property;
    private File sourceFile;
    private File targetFile;
    private String value;
    private Vector sourceFileSets = new Vector();
    protected Mapper mapperElement = null;

    public void setProperty(String property) {
        this.property = property;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String getValue() {
        String str = this.value;
        return str != null ? str : "true";
    }

    public void setTargetFile(File file) {
        this.targetFile = file;
    }

    public void setSrcfile(File file) {
        this.sourceFile = file;
    }

    public void addSrcfiles(FileSet fs) {
        this.sourceFileSets.addElement(fs);
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapperElement != null) {
            throw new BuildException("Cannot define more than one mapper", getLocation());
        }
        Mapper mapper = new Mapper(getProject());
        this.mapperElement = mapper;
        return mapper;
    }

    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() {
        if (this.sourceFileSets.size() == 0 && this.sourceFile == null) {
            throw new BuildException("At least one srcfile or a nested <srcfiles> element must be set.");
        }
        if (this.sourceFileSets.size() > 0 && this.sourceFile != null) {
            throw new BuildException("Cannot specify both the srcfile attribute and a nested <srcfiles> element.");
        }
        if (this.targetFile == null && this.mapperElement == null) {
            throw new BuildException("The targetfile attribute or a nested mapper element must be set.");
        }
        File file = this.targetFile;
        boolean z = false;
        if (file != null && !file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The targetfile \"");
            stringBuffer.append(this.targetFile.getAbsolutePath());
            stringBuffer.append("\" does not exist.");
            log(stringBuffer.toString(), 3);
            return false;
        }
        File file2 = this.sourceFile;
        if (file2 != null && !file2.exists()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.sourceFile.getAbsolutePath());
            stringBuffer2.append(" not found.");
            throw new BuildException(stringBuffer2.toString());
        }
        Enumeration e = this.sourceFileSets.elements();
        boolean upToDate = true;
        while (true) {
            boolean z2 = true;
            if (!upToDate || !e.hasMoreElements()) {
                break;
            }
            FileSet fs = (FileSet) e.nextElement();
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            if (!upToDate || !scanDir(fs.getDir(getProject()), ds.getIncludedFiles())) {
                z2 = false;
            }
            upToDate = z2;
        }
        if (this.sourceFile != null) {
            if (this.mapperElement == null) {
                if (upToDate && this.targetFile.lastModified() >= this.sourceFile.lastModified()) {
                    z = true;
                }
                boolean upToDate2 = z;
                return upToDate2;
            }
            SourceFileScanner sfs = new SourceFileScanner(this);
            if (upToDate && sfs.restrict(new String[]{this.sourceFile.getAbsolutePath()}, null, null, this.mapperElement.getImplementation()).length == 0) {
                z = true;
            }
            boolean upToDate3 = z;
            return upToDate3;
        }
        return upToDate;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.property == null) {
            throw new BuildException("property attribute is required.", getLocation());
        }
        boolean upToDate = eval();
        if (upToDate) {
            getProject().setNewProperty(this.property, getValue());
            if (this.mapperElement == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("File \"");
                stringBuffer.append(this.targetFile.getAbsolutePath());
                stringBuffer.append("\" is up-to-date.");
                log(stringBuffer.toString(), 3);
                return;
            }
            log("All target files are up-to-date.", 3);
        }
    }

    protected boolean scanDir(File srcDir, String[] files) {
        FileNameMapper mapper;
        SourceFileScanner sfs = new SourceFileScanner(this);
        File dir = srcDir;
        Mapper mapper2 = this.mapperElement;
        if (mapper2 == null) {
            MergingMapper mm = new MergingMapper();
            mm.setTo(this.targetFile.getAbsolutePath());
            mapper = mm;
            dir = null;
        } else {
            mapper = mapper2.getImplementation();
        }
        return sfs.restrict(files, srcDir, dir, mapper).length == 0;
    }
}
