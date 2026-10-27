package org.apache.tools.ant.taskdefs;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.MergingMapper;
import org.apache.tools.ant.util.SourceFileScanner;
import org.apache.tools.bzip2.CBZip2OutputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;
import org.apache.tools.zip.UnixStat;

/* JADX INFO: loaded from: classes.dex */
public class Tar extends MatchingTask {
    public static final String FAIL = "fail";
    public static final String GNU = "gnu";
    public static final String OMIT = "omit";
    public static final String TRUNCATE = "truncate";
    public static final String WARN = "warn";
    File baseDir;
    File tarFile;
    private TarLongFileMode longFileMode = new TarLongFileMode();
    Vector filesets = new Vector();
    Vector fileSetFiles = new Vector();
    private boolean longWarningGiven = false;
    private TarCompressionMethod compression = new TarCompressionMethod();

    public TarFileSet createTarFileSet() {
        TarFileSet fileset = new TarFileSet();
        this.filesets.addElement(fileset);
        return fileset;
    }

    public void setTarfile(File tarFile) {
        this.tarFile = tarFile;
    }

    public void setDestFile(File destFile) {
        this.tarFile = destFile;
    }

    public void setBasedir(File baseDir) {
        this.baseDir = baseDir;
    }

    public void setLongfile(String mode) {
        log("DEPRECATED - The setLongfile(String) method has been deprecated. Use setLongfile(Tar.TarLongFileMode) instead.");
        TarLongFileMode tarLongFileMode = new TarLongFileMode();
        this.longFileMode = tarLongFileMode;
        tarLongFileMode.setValue(mode);
    }

    public void setLongfile(TarLongFileMode mode) {
        this.longFileMode = mode;
    }

    public void setCompression(TarCompressionMethod mode) {
        this.compression = mode;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0190 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Tar.execute():void");
    }

    protected void tarFile(File file, TarOutputStream tOut, String vPath, TarFileSet tarFileSet) throws IOException {
        String vPath2;
        FileInputStream fIn = null;
        String fullpath = tarFileSet.getFullpath();
        if (fullpath.length() > 0) {
            vPath2 = fullpath;
        } else {
            if (vPath.length() <= 0) {
                return;
            }
            if (file.isDirectory() && !vPath.endsWith("/")) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(vPath);
                stringBuffer.append("/");
                vPath = stringBuffer.toString();
            }
            String prefix = tarFileSet.getPrefix();
            if (prefix.length() > 0 && !prefix.endsWith("/")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(prefix);
                stringBuffer2.append("/");
                prefix = stringBuffer2.toString();
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(prefix);
            stringBuffer3.append(vPath);
            vPath2 = stringBuffer3.toString();
        }
        if (vPath2.startsWith("/") && !tarFileSet.getPreserveLeadingSlashes()) {
            int l = vPath2.length();
            if (l <= 1) {
                return;
            } else {
                vPath2 = vPath2.substring(1, l);
            }
        }
        try {
            if (vPath2.length() >= 100) {
                if (this.longFileMode.isOmitMode()) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Omitting: ");
                    stringBuffer4.append(vPath2);
                    log(stringBuffer4.toString(), 2);
                    if (fIn != null) {
                        return;
                    } else {
                        return;
                    }
                }
                if (this.longFileMode.isWarnMode()) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append("Entry: ");
                    stringBuffer5.append(vPath2);
                    stringBuffer5.append(" longer than ");
                    stringBuffer5.append(100);
                    stringBuffer5.append(" characters.");
                    log(stringBuffer5.toString(), 1);
                    if (!this.longWarningGiven) {
                        log("Resulting tar file can only be processed successfully by GNU compatible tar commands", 1);
                        this.longWarningGiven = true;
                    }
                } else if (this.longFileMode.isFailMode()) {
                    StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("Entry: ");
                    stringBuffer6.append(vPath2);
                    stringBuffer6.append(" longer than ");
                    stringBuffer6.append(100);
                    stringBuffer6.append("characters.");
                    throw new BuildException(stringBuffer6.toString(), getLocation());
                }
            }
            TarEntry te = new TarEntry(vPath2);
            te.setModTime(file.lastModified());
            if (!file.isDirectory()) {
                te.setSize(file.length());
                te.setMode(tarFileSet.getMode());
            } else {
                te.setMode(tarFileSet.getDirMode());
            }
            te.setUserName(tarFileSet.getUserName());
            te.setGroupName(tarFileSet.getGroup());
            te.setUserId(tarFileSet.getUid());
            te.setGroupId(tarFileSet.getGid());
            tOut.putNextEntry(te);
            if (!file.isDirectory()) {
                fIn = new FileInputStream(file);
                byte[] buffer = new byte[VehicleVendorProperty.Health.OIL_HINT];
                int count = 0;
                do {
                    tOut.write(buffer, 0, count);
                    count = fIn.read(buffer, 0, buffer.length);
                } while (count != -1);
            }
            tOut.closeEntry();
            if (fIn != null) {
                fIn.close();
            }
        } finally {
            if (0 != 0) {
                fIn.close();
            }
        }
    }

    protected boolean archiveIsUpToDate(String[] files) {
        return archiveIsUpToDate(files, this.baseDir);
    }

    protected boolean archiveIsUpToDate(String[] files, File dir) {
        SourceFileScanner sfs = new SourceFileScanner(this);
        MergingMapper mm = new MergingMapper();
        mm.setTo(this.tarFile.getAbsolutePath());
        return sfs.restrict(files, dir, null, mm).length == 0;
    }

    public static class TarFileSet extends FileSet {
        private int dirMode;
        private int fileMode;
        private String[] files;
        private String fullpath;
        private int gid;
        private String groupName;
        private String prefix;
        private boolean preserveLeadingSlashes;
        private int uid;
        private String userName;

        public TarFileSet(FileSet fileset) {
            super(fileset);
            this.files = null;
            this.fileMode = 33188;
            this.dirMode = 16877;
            this.userName = "";
            this.groupName = "";
            this.prefix = "";
            this.fullpath = "";
            this.preserveLeadingSlashes = false;
        }

        public TarFileSet() {
            this.files = null;
            this.fileMode = 33188;
            this.dirMode = 16877;
            this.userName = "";
            this.groupName = "";
            this.prefix = "";
            this.fullpath = "";
            this.preserveLeadingSlashes = false;
        }

        public String[] getFiles(Project p) {
            if (this.files == null) {
                DirectoryScanner ds = getDirectoryScanner(p);
                String[] directories = ds.getIncludedDirectories();
                String[] filesPerSe = ds.getIncludedFiles();
                String[] strArr = new String[directories.length + filesPerSe.length];
                this.files = strArr;
                System.arraycopy(directories, 0, strArr, 0, directories.length);
                System.arraycopy(filesPerSe, 0, this.files, directories.length, filesPerSe.length);
            }
            return this.files;
        }

        public void setMode(String octalString) {
            this.fileMode = Integer.parseInt(octalString, 8) | UnixStat.FILE_FLAG;
        }

        public int getMode() {
            return this.fileMode;
        }

        public void setDirMode(String octalString) {
            this.dirMode = Integer.parseInt(octalString, 8) | UnixStat.DIR_FLAG;
        }

        public int getDirMode() {
            return this.dirMode;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setGroup(String groupName) {
            this.groupName = groupName;
        }

        public String getGroup() {
            return this.groupName;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public int getGid() {
            return this.gid;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public void setFullpath(String fullpath) {
            this.fullpath = fullpath;
        }

        public String getFullpath() {
            return this.fullpath;
        }

        public void setPreserveLeadingSlashes(boolean b) {
            this.preserveLeadingSlashes = b;
        }

        public boolean getPreserveLeadingSlashes() {
            return this.preserveLeadingSlashes;
        }
    }

    public static class TarLongFileMode extends EnumeratedAttribute {
        public static final String FAIL = "fail";
        public static final String GNU = "gnu";
        public static final String OMIT = "omit";
        public static final String TRUNCATE = "truncate";
        public static final String WARN = "warn";
        private final String[] validModes = {"warn", "fail", "truncate", "gnu", "omit"};

        public TarLongFileMode() {
            setValue("warn");
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return this.validModes;
        }

        public boolean isTruncateMode() {
            return "truncate".equalsIgnoreCase(getValue());
        }

        public boolean isWarnMode() {
            return "warn".equalsIgnoreCase(getValue());
        }

        public boolean isGnuMode() {
            return "gnu".equalsIgnoreCase(getValue());
        }

        public boolean isFailMode() {
            return "fail".equalsIgnoreCase(getValue());
        }

        public boolean isOmitMode() {
            return "omit".equalsIgnoreCase(getValue());
        }
    }

    public static final class TarCompressionMethod extends EnumeratedAttribute {
        private static final String BZIP2 = "bzip2";
        private static final String GZIP = "gzip";
        private static final String NONE = "none";

        public TarCompressionMethod() {
            setValue(NONE);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{NONE, GZIP, BZIP2};
        }

        /* JADX INFO: Access modifiers changed from: private */
        public OutputStream compress(OutputStream ostream) throws IOException {
            String value = getValue();
            if (GZIP.equals(value)) {
                return new GZIPOutputStream(ostream);
            }
            if (BZIP2.equals(value)) {
                ostream.write(66);
                ostream.write(90);
                return new CBZip2OutputStream(ostream);
            }
            return ostream;
        }
    }
}
