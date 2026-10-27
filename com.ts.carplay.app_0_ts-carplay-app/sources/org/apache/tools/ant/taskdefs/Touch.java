package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Touch extends Task {
    private static final DateFormatFactory DEFAULT_DF_FACTORY = new DateFormatFactory() { // from class: org.apache.tools.ant.taskdefs.Touch.1
        @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
        public DateFormat getPrimaryFormat() {
            return DateFormat.getDateTimeInstance(3, 3, Locale.US);
        }

        @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
        public DateFormat getFallbackFormat() {
            return DateFormat.getDateTimeInstance(3, 2, Locale.US);
        }
    };
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private String dateTime;
    private boolean dateTimeConfigured;
    private File file;
    private boolean mkdirs;
    private long millis = -1;
    private Vector filesets = new Vector();
    private Vector filelists = new Vector();
    private boolean verbose = true;
    private FileNameMapper fileNameMapper = null;
    private DateFormatFactory dfFactory = DEFAULT_DF_FACTORY;

    private interface DateFormatFactory {
        DateFormat getFallbackFormat();

        DateFormat getPrimaryFormat();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public void setDatetime(String dateTime) {
        if (this.dateTime != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Resetting datetime attribute to ");
            stringBuffer.append(dateTime);
            log(stringBuffer.toString(), 3);
        }
        this.dateTime = dateTime;
        this.dateTimeConfigured = false;
    }

    public void setMkdirs(boolean mkdirs) {
        this.mkdirs = mkdirs;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setPattern(final String pattern) {
        this.dfFactory = new DateFormatFactory() { // from class: org.apache.tools.ant.taskdefs.Touch.2
            @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
            public DateFormat getPrimaryFormat() {
                return new SimpleDateFormat(pattern);
            }

            @Override // org.apache.tools.ant.taskdefs.Touch.DateFormatFactory
            public DateFormat getFallbackFormat() {
                return null;
            }
        };
    }

    public void addConfiguredMapper(Mapper mapper) {
        add(mapper.getImplementation());
    }

    public void add(FileNameMapper fileNameMapper) throws BuildException {
        if (this.fileNameMapper != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Only one mapper may be added to the ");
            stringBuffer.append(getTaskName());
            stringBuffer.append(" task.");
            throw new BuildException(stringBuffer.toString());
        }
        this.fileNameMapper = fileNameMapper;
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    public void addFilelist(FileList list) {
        this.filelists.addElement(list);
    }

    protected synchronized void checkConfiguration() throws BuildException {
        if (this.file == null && this.filesets.size() + this.filelists.size() == 0) {
            throw new BuildException("Specify at least one source--a file, filelist or a fileset.");
        }
        if (this.file != null && this.file.exists() && this.file.isDirectory()) {
            throw new BuildException("Use a fileset to touch directories.");
        }
        if (this.dateTime != null && !this.dateTimeConfigured) {
            long workmillis = this.millis;
            ParseException pe = null;
            try {
                workmillis = this.dfFactory.getPrimaryFormat().parse(this.dateTime).getTime();
            } catch (ParseException peOne) {
                DateFormat df = this.dfFactory.getFallbackFormat();
                if (df == null) {
                    pe = peOne;
                } else {
                    try {
                        workmillis = df.parse(this.dateTime).getTime();
                    } catch (ParseException peTwo) {
                        pe = peTwo;
                    }
                }
            }
            if (pe != null) {
                throw new BuildException(pe.getMessage(), pe, getLocation());
            }
            if (workmillis < 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Date of ");
                stringBuffer.append(this.dateTime);
                stringBuffer.append(" results in negative ");
                stringBuffer.append("milliseconds value ");
                stringBuffer.append("relative to epoch ");
                stringBuffer.append("(January 1, 1970, ");
                stringBuffer.append("00:00:00 GMT).");
                throw new BuildException(stringBuffer.toString());
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Setting millis to ");
            stringBuffer2.append(workmillis);
            stringBuffer2.append(" from datetime attribute");
            log(stringBuffer2.toString(), this.millis < 0 ? 4 : 3);
            setMillis(workmillis);
            this.dateTimeConfigured = true;
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        checkConfiguration();
        touch();
    }

    protected void touch() throws BuildException {
        long defaultTimestamp = getTimestamp();
        File file = this.file;
        if (file != null) {
            touch(file.getParentFile(), this.file.getName(), defaultTimestamp);
        }
        for (int i = 0; i < this.filesets.size(); i++) {
            FileSet fs = (FileSet) this.filesets.elementAt(i);
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            File fromDir = fs.getDir(getProject());
            String[] srcFiles = ds.getIncludedFiles();
            String[] srcDirs = ds.getIncludedDirectories();
            for (String str : srcFiles) {
                touch(fromDir, str, defaultTimestamp);
            }
            for (String str2 : srcDirs) {
                touch(fromDir, str2, defaultTimestamp);
            }
        }
        for (int i2 = 0; i2 < this.filelists.size(); i2++) {
            FileList fl = (FileList) this.filelists.elementAt(i2);
            File fromDir2 = fl.getDir(getProject());
            String[] srcFiles2 = fl.getFiles(getProject());
            for (String str3 : srcFiles2) {
                touch(fromDir2, str3, defaultTimestamp);
            }
        }
    }

    protected void touch(File file) {
        touch(file, getTimestamp());
    }

    private long getTimestamp() {
        long j = this.millis;
        return j < 0 ? System.currentTimeMillis() : j;
    }

    private void touch(File fromDir, String filename, long defaultTimestamp) {
        File f = FILE_UTILS.resolveFile(fromDir, filename);
        FileNameMapper fileNameMapper = this.fileNameMapper;
        if (fileNameMapper == null) {
            touch(f, defaultTimestamp);
            return;
        }
        String[] mapped = fileNameMapper.mapFileName(filename);
        if (mapped != null && mapped.length > 0) {
            long modTime = f.exists() ? f.lastModified() : defaultTimestamp;
            for (String str : mapped) {
                touch(getProject().resolveFile(str), modTime);
            }
        }
    }

    private void touch(File file, long modTime) {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Creating ");
            stringBuffer.append(file);
            log(stringBuffer.toString(), this.verbose ? 2 : 3);
            try {
                FILE_UTILS.createNewFile(file, this.mkdirs);
            } catch (IOException ioe) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not create ");
                stringBuffer2.append(file);
                throw new BuildException(stringBuffer2.toString(), ioe, getLocation());
            }
        }
        if (!file.canWrite()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Can not change modification date of read-only file ");
            stringBuffer3.append(file);
            throw new BuildException(stringBuffer3.toString());
        }
        FILE_UTILS.setFileLastModified(file, modTime);
    }
}
