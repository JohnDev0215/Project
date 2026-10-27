package org.apache.tools.ant.types;

import java.io.File;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.zip.UnixStat;

/* JADX INFO: loaded from: classes.dex */
public class ZipFileSet extends FileSet {
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    private int dirMode;
    private boolean dirModeHasBeenSet;
    private int fileMode;
    private boolean fileModeHasBeenSet;
    private String fullpath;
    private boolean hasDir;
    private String prefix;
    private File srcFile;

    public ZipFileSet() {
        this.srcFile = null;
        this.prefix = "";
        this.fullpath = "";
        this.hasDir = false;
        this.fileMode = 33188;
        this.dirMode = 16877;
        this.fileModeHasBeenSet = false;
        this.dirModeHasBeenSet = false;
    }

    protected ZipFileSet(FileSet fileset) {
        super(fileset);
        this.srcFile = null;
        this.prefix = "";
        this.fullpath = "";
        this.hasDir = false;
        this.fileMode = 33188;
        this.dirMode = 16877;
        this.fileModeHasBeenSet = false;
        this.dirModeHasBeenSet = false;
    }

    protected ZipFileSet(ZipFileSet fileset) {
        super(fileset);
        this.srcFile = null;
        this.prefix = "";
        this.fullpath = "";
        this.hasDir = false;
        this.fileMode = 33188;
        this.dirMode = 16877;
        this.fileModeHasBeenSet = false;
        this.dirModeHasBeenSet = false;
        this.srcFile = fileset.srcFile;
        this.prefix = fileset.prefix;
        this.fullpath = fileset.fullpath;
        this.hasDir = fileset.hasDir;
        this.fileMode = fileset.fileMode;
        this.dirMode = fileset.dirMode;
        this.fileModeHasBeenSet = fileset.fileModeHasBeenSet;
        this.dirModeHasBeenSet = fileset.dirModeHasBeenSet;
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public void setDir(File dir) throws BuildException {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (this.srcFile != null) {
            throw new BuildException("Cannot set both dir and src attributes");
        }
        super.setDir(dir);
        this.hasDir = true;
    }

    public void setSrc(File srcFile) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (this.hasDir) {
            throw new BuildException("Cannot set both dir and src attributes");
        }
        this.srcFile = srcFile;
    }

    public File getSrc(Project p) {
        if (isReference()) {
            return ((ZipFileSet) getRef(p)).getSrc(p);
        }
        return this.srcFile;
    }

    public void setPrefix(String prefix) {
        if (!prefix.equals("") && !this.fullpath.equals("")) {
            throw new BuildException("Cannot set both fullpath and prefix attributes");
        }
        this.prefix = prefix;
    }

    public String getPrefix(Project p) {
        if (isReference()) {
            return ((ZipFileSet) getRef(p)).getPrefix(p);
        }
        return this.prefix;
    }

    public void setFullpath(String fullpath) {
        if (!this.prefix.equals("") && !fullpath.equals("")) {
            throw new BuildException("Cannot set both fullpath and prefix attributes");
        }
        this.fullpath = fullpath;
    }

    public String getFullpath(Project p) {
        if (isReference()) {
            return ((ZipFileSet) getRef(p)).getFullpath(p);
        }
        return this.fullpath;
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public DirectoryScanner getDirectoryScanner(Project p) {
        if (isReference()) {
            return getRef(p).getDirectoryScanner(p);
        }
        if (this.srcFile != null) {
            ZipScanner zs = new ZipScanner();
            zs.setSrc(this.srcFile);
            super.setDir(p.getBaseDir());
            setupDirectoryScanner(zs, p);
            zs.init();
            return zs;
        }
        return super.getDirectoryScanner(p);
    }

    public void setFileMode(String octalString) {
        this.fileModeHasBeenSet = true;
        this.fileMode = Integer.parseInt(octalString, 8) | UnixStat.FILE_FLAG;
    }

    public int getFileMode(Project p) {
        if (isReference()) {
            return ((ZipFileSet) getRef(p)).getFileMode(p);
        }
        return this.fileMode;
    }

    public boolean hasFileModeBeenSet() {
        if (isReference()) {
            return ((ZipFileSet) getRef(getProject())).hasFileModeBeenSet();
        }
        return this.fileModeHasBeenSet;
    }

    public void setDirMode(String octalString) {
        this.dirModeHasBeenSet = true;
        this.dirMode = Integer.parseInt(octalString, 8) | UnixStat.DIR_FLAG;
    }

    public int getDirMode(Project p) {
        if (isReference()) {
            return ((ZipFileSet) getRef(p)).getDirMode(p);
        }
        return this.dirMode;
    }

    public boolean hasDirModeBeenSet() {
        if (isReference()) {
            return ((ZipFileSet) getRef(getProject())).hasDirModeBeenSet();
        }
        return this.dirModeHasBeenSet;
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    protected AbstractFileSet getRef(Project p) {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, p);
        }
        Object o = getRefid().getReferencedObject(p);
        if (o instanceof ZipFileSet) {
            return (AbstractFileSet) o;
        }
        if (o instanceof FileSet) {
            ZipFileSet zfs = new ZipFileSet((FileSet) o);
            zfs.setPrefix(this.prefix);
            zfs.setFullpath(this.fullpath);
            zfs.fileModeHasBeenSet = this.fileModeHasBeenSet;
            zfs.fileMode = this.fileMode;
            zfs.dirModeHasBeenSet = this.dirModeHasBeenSet;
            zfs.dirMode = this.dirMode;
            return zfs;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getRefid().getRefId());
        stringBuffer.append(" doesn't denote a zipfileset or a fileset");
        String msg = stringBuffer.toString();
        throw new BuildException(msg);
    }

    @Override // org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.AbstractFileSet
    public Object clone() {
        if (isReference()) {
            return ((ZipFileSet) getRef(getProject())).clone();
        }
        return super.clone();
    }
}
