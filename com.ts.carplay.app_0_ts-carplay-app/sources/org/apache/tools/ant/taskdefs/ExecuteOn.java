package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.AbstractFileSet;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.SourceFileScanner;

/* JADX INFO: loaded from: classes.dex */
public class ExecuteOn extends ExecTask {
    protected Vector filesets = new Vector();
    private Vector filelists = new Vector();
    private boolean relative = false;
    private boolean parallel = false;
    private boolean forwardSlash = false;
    protected String type = TypeSelector.FileType.FILE;
    protected Commandline.Marker srcFilePos = null;
    private boolean skipEmpty = false;
    protected Commandline.Marker targetFilePos = null;
    protected Mapper mapperElement = null;
    protected FileNameMapper mapper = null;
    protected File destDir = null;
    private int maxParallel = -1;
    private boolean addSourceFile = true;
    private boolean verbose = false;
    private boolean ignoreMissing = true;
    private boolean force = false;
    protected boolean srcIsFirst = true;

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    public void addDirset(DirSet set) {
        this.filesets.addElement(set);
    }

    public void addFilelist(FileList list) {
        this.filelists.addElement(list);
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public void setType(FileDirBoth type) {
        this.type = type.getValue();
    }

    public void setSkipEmptyFilesets(boolean skip) {
        this.skipEmpty = skip;
    }

    public void setDest(File destDir) {
        this.destDir = destDir;
    }

    public void setForwardslash(boolean forwardSlash) {
        this.forwardSlash = forwardSlash;
    }

    public void setMaxParallel(int max) {
        this.maxParallel = max;
    }

    public void setAddsourcefile(boolean b) {
        this.addSourceFile = b;
    }

    public void setVerbose(boolean b) {
        this.verbose = b;
    }

    public void setIgnoremissing(boolean b) {
        this.ignoreMissing = b;
    }

    public void setForce(boolean b) {
        this.force = b;
    }

    public Commandline.Marker createSrcfile() {
        if (this.srcFilePos != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getTaskType());
            stringBuffer.append(" doesn't support multiple ");
            stringBuffer.append("srcfile elements.");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        Commandline.Marker markerCreateMarker = this.cmdl.createMarker();
        this.srcFilePos = markerCreateMarker;
        return markerCreateMarker;
    }

    public Commandline.Marker createTargetfile() {
        if (this.targetFilePos != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getTaskType());
            stringBuffer.append(" doesn't support multiple ");
            stringBuffer.append("targetfile elements.");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        this.targetFilePos = this.cmdl.createMarker();
        this.srcIsFirst = this.srcFilePos != null;
        return this.targetFilePos;
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

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    protected void checkConfiguration() {
        if ("execon".equals(getTaskName())) {
            log("!! execon is deprecated. Use apply instead. !!");
        }
        super.checkConfiguration();
        if (this.filesets.size() == 0 && this.filelists.size() == 0) {
            throw new BuildException("no filesets and no filelists specified", getLocation());
        }
        if (this.targetFilePos != null && this.mapperElement == null) {
            throw new BuildException("targetfile specified without mapper", getLocation());
        }
        if (this.destDir != null && this.mapperElement == null) {
            throw new BuildException("dest specified without mapper", getLocation());
        }
        Mapper mapper = this.mapperElement;
        if (mapper != null) {
            this.mapper = mapper.getImplementation();
        }
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    protected ExecuteStreamHandler createHandler() throws BuildException {
        return this.redirectorElement == null ? super.createHandler() : new PumpStreamHandler();
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    protected void setupRedirector() {
        super.setupRedirector();
        this.redirector.setAppendProperties(true);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecTask
    protected void runExec(Execute exe) throws Throwable {
        Vector fileNames;
        Vector baseDirs;
        int i;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        int i2;
        Vector baseDirs2;
        String str6;
        Vector baseDirs3;
        int totalDirs;
        int totalFiles;
        int totalFiles2 = 0;
        int totalDirs2 = 0;
        boolean haveExecuted = false;
        boolean z = false;
        try {
            fileNames = new Vector();
            baseDirs = new Vector();
            i = 0;
        } catch (IOException e) {
            e = e;
        } catch (Throwable th) {
            th = th;
        }
        while (true) {
            try {
                str = "up to date.";
                str2 = "empty.";
                str3 = ". It is ";
                if (i >= this.filesets.size()) {
                    break;
                }
                int i3 = i;
                Vector baseDirs4 = baseDirs;
                String currentType = this.type;
                AbstractFileSet fs = (AbstractFileSet) this.filesets.elementAt(i3);
                if ((fs instanceof DirSet) && !TypeSelector.FileType.DIR.equals(this.type)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Found a nested dirset but type is ");
                    stringBuffer.append(this.type);
                    stringBuffer.append(". ");
                    stringBuffer.append("Temporarily switching to type=\"dir\" on the");
                    stringBuffer.append(" assumption that you really did mean");
                    stringBuffer.append(" <dirset> not <fileset>.");
                    log(stringBuffer.toString(), 4);
                    currentType = TypeSelector.FileType.DIR;
                }
                File base = fs.getDir(getProject());
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                if (TypeSelector.FileType.DIR.equals(currentType)) {
                    totalDirs = totalDirs2;
                } else {
                    String[] s = getFiles(base, ds);
                    int j = 0;
                    while (true) {
                        totalDirs = totalDirs2;
                        try {
                            int totalDirs3 = s.length;
                            if (j >= totalDirs3) {
                                break;
                            }
                            totalFiles2++;
                            fileNames.addElement(s[j]);
                            baseDirs4.addElement(base);
                            j++;
                            totalDirs2 = totalDirs;
                        } catch (IOException e2) {
                            e = e2;
                            IOException e3 = e;
                            try {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("Execute failed: ");
                                stringBuffer2.append(e3);
                                throw new BuildException(stringBuffer2.toString(), e3, getLocation());
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                }
                try {
                    if (TypeSelector.FileType.FILE.equals(currentType)) {
                        totalDirs2 = totalDirs;
                    } else {
                        for (String str7 : getDirs(base, ds)) {
                            totalDirs++;
                            fileNames.addElement(str7);
                            baseDirs4.addElement(base);
                        }
                        totalDirs2 = totalDirs;
                    }
                    try {
                        if (fileNames.size() == 0 && this.skipEmpty) {
                            int includedCount = (!TypeSelector.FileType.DIR.equals(currentType) ? ds.getIncludedFilesCount() : 0) + (!TypeSelector.FileType.FILE.equals(currentType) ? ds.getIncludedDirsCount() : 0);
                            StringBuffer stringBuffer3 = new StringBuffer();
                            stringBuffer3.append("Skipping fileset for directory ");
                            stringBuffer3.append(base);
                            stringBuffer3.append(". It is ");
                            stringBuffer3.append(includedCount > 0 ? "up to date." : "empty.");
                            log(stringBuffer3.toString(), 2);
                        } else if (!this.parallel) {
                            String[] s2 = new String[fileNames.size()];
                            fileNames.copyInto(s2);
                            int j2 = 0;
                            while (j2 < s2.length) {
                                String[] command = getCommandline(s2[j2], base);
                                String currentType2 = currentType;
                                log(Commandline.describeCommand(command), 3);
                                exe.setCommandline(command);
                                if (this.redirectorElement != null) {
                                    setupRedirector();
                                    totalFiles = totalFiles2;
                                    try {
                                        this.redirectorElement.configure(this.redirector, s2[j2]);
                                    } catch (IOException e4) {
                                        e = e4;
                                        IOException e32 = e;
                                        StringBuffer stringBuffer22 = new StringBuffer();
                                        stringBuffer22.append("Execute failed: ");
                                        stringBuffer22.append(e32);
                                        throw new BuildException(stringBuffer22.toString(), e32, getLocation());
                                    } catch (Throwable th4) {
                                        th = th4;
                                    }
                                } else {
                                    totalFiles = totalFiles2;
                                }
                                if (this.redirectorElement != null || haveExecuted) {
                                    exe.setStreamHandler(this.redirector.createHandler());
                                }
                                runExecute(exe);
                                haveExecuted = true;
                                j2++;
                                currentType = currentType2;
                                totalFiles2 = totalFiles;
                            }
                            fileNames.removeAllElements();
                            baseDirs4.removeAllElements();
                        }
                        totalFiles2 = totalFiles2;
                        z = false;
                        i = i3 + 1;
                        baseDirs = baseDirs4;
                    } catch (IOException e5) {
                        e = e5;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (IOException e6) {
                    e = e6;
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (IOException e7) {
                e = e7;
            } catch (Throwable th7) {
                th = th7;
            }
            logFlush();
            this.redirector.setAppendProperties(false);
            this.redirector.setProperties();
            throw th;
        }
        int i4 = 0;
        while (i4 < this.filelists.size()) {
            try {
                FileList list = (FileList) this.filelists.elementAt(i4);
                File base2 = list.getDir(getProject());
                String[] names = getFilesAndDirs(list);
                boolean haveExecuted2 = haveExecuted;
                int j3 = 0;
                while (true) {
                    str4 = str;
                    String[] names2 = names;
                    str5 = str2;
                    try {
                        if (j3 >= names2.length) {
                            break;
                        }
                        Vector baseDirs5 = baseDirs;
                        int i5 = i;
                        String str8 = str3;
                        FileList list2 = list;
                        File f = new File(base2, names2[j3]);
                        if (!this.ignoreMissing || ((f.isFile() && !TypeSelector.FileType.DIR.equals(this.type)) || (f.isDirectory() && !TypeSelector.FileType.FILE.equals(this.type)))) {
                            if (this.ignoreMissing || f.isFile()) {
                                totalFiles2++;
                            } else {
                                totalDirs2++;
                            }
                            fileNames.addElement(names2[j3]);
                            baseDirs3 = baseDirs5;
                            baseDirs3.addElement(base2);
                        } else {
                            baseDirs3 = baseDirs5;
                        }
                        j3++;
                        baseDirs = baseDirs3;
                        str2 = str5;
                        i = i5;
                        str3 = str8;
                        list = list2;
                        names = names2;
                        str = str4;
                    } catch (IOException e8) {
                        e = e8;
                        IOException e322 = e;
                        StringBuffer stringBuffer222 = new StringBuffer();
                        stringBuffer222.append("Execute failed: ");
                        stringBuffer222.append(e322);
                        throw new BuildException(stringBuffer222.toString(), e322, getLocation());
                    } catch (Throwable th8) {
                        th = th8;
                    }
                }
                if (fileNames.size() == 0 && this.skipEmpty) {
                    DirectoryScanner ds2 = new DirectoryScanner();
                    ds2.setBasedir(base2);
                    i2 = i;
                    ds2.setIncludes(list.getFiles(getProject()));
                    ds2.scan();
                    int includedCount2 = ds2.getIncludedFilesCount() + ds2.getIncludedDirsCount();
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Skipping filelist for directory ");
                    stringBuffer4.append(base2);
                    stringBuffer4.append(str3);
                    stringBuffer4.append(includedCount2 > 0 ? str4 : str5);
                    log(stringBuffer4.toString(), 2);
                    baseDirs2 = baseDirs;
                    str6 = str3;
                } else {
                    i2 = i;
                    if (this.parallel) {
                        baseDirs2 = baseDirs;
                        str6 = str3;
                    } else {
                        String[] s3 = new String[fileNames.size()];
                        fileNames.copyInto(s3);
                        int j4 = 0;
                        while (j4 < s3.length) {
                            String[] command2 = getCommandline(s3[j4], base2);
                            String str9 = str3;
                            Vector baseDirs6 = baseDirs;
                            log(Commandline.describeCommand(command2), 3);
                            exe.setCommandline(command2);
                            if (this.redirectorElement != null) {
                                setupRedirector();
                                this.redirectorElement.configure(this.redirector, s3[j4]);
                            }
                            if (this.redirectorElement != null || haveExecuted2) {
                                exe.setStreamHandler(this.redirector.createHandler());
                            }
                            runExecute(exe);
                            haveExecuted2 = true;
                            j4++;
                            str3 = str9;
                            baseDirs = baseDirs6;
                        }
                        fileNames.removeAllElements();
                        baseDirs.removeAllElements();
                        baseDirs2 = baseDirs;
                        str6 = str3;
                        haveExecuted = haveExecuted2;
                        i4++;
                        str2 = str5;
                        str = str4;
                        i = i2;
                        str3 = str6;
                        baseDirs = baseDirs2;
                        z = false;
                    }
                }
                haveExecuted = haveExecuted2;
                i4++;
                str2 = str5;
                str = str4;
                i = i2;
                str3 = str6;
                baseDirs = baseDirs2;
                z = false;
            } catch (IOException e9) {
                e = e9;
            } catch (Throwable th9) {
                th = th9;
            }
        }
        if (this.parallel && (fileNames.size() > 0 || !this.skipEmpty)) {
            runParallel(exe, fileNames, baseDirs);
            haveExecuted = true;
        }
        if (haveExecuted) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Applied ");
            stringBuffer5.append(this.cmdl.getExecutable());
            stringBuffer5.append(" to ");
            stringBuffer5.append(totalFiles2);
            stringBuffer5.append(" file");
            stringBuffer5.append(totalFiles2 != 1 ? "s" : "");
            stringBuffer5.append(" and ");
            stringBuffer5.append(totalDirs2);
            stringBuffer5.append(" director");
            stringBuffer5.append(totalDirs2 != 1 ? "ies" : "y");
            stringBuffer5.append(".");
            log(stringBuffer5.toString(), this.verbose ? 2 : 3);
        }
        logFlush();
        this.redirector.setAppendProperties(z);
        this.redirector.setProperties();
    }

    protected String[] getCommandline(String[] srcFiles, File[] baseDirs) {
        String name;
        char fileSeparator = File.separatorChar;
        Vector targets = new Vector();
        if (this.targetFilePos != null) {
            Hashtable addedFiles = new Hashtable();
            for (String str : srcFiles) {
                String[] subTargets = this.mapper.mapFileName(str);
                if (subTargets != null) {
                    for (int j = 0; j < subTargets.length; j++) {
                        if (!this.relative) {
                            name = new File(this.destDir, subTargets[j]).getAbsolutePath();
                        } else {
                            name = subTargets[j];
                        }
                        if (this.forwardSlash && fileSeparator != '/') {
                            name = name.replace(fileSeparator, '/');
                        }
                        if (!addedFiles.contains(name)) {
                            targets.addElement(name);
                            addedFiles.put(name, name);
                        }
                    }
                }
            }
        }
        String[] targetFiles = new String[targets.size()];
        targets.copyInto(targetFiles);
        if (!this.addSourceFile) {
            srcFiles = new String[0];
        }
        String[] orig = this.cmdl.getCommandline();
        String[] result = new String[orig.length + srcFiles.length + targetFiles.length];
        int srcIndex = orig.length;
        Commandline.Marker marker = this.srcFilePos;
        if (marker != null) {
            srcIndex = marker.getPosition();
        }
        Commandline.Marker marker2 = this.targetFilePos;
        if (marker2 != null) {
            int targetIndex = marker2.getPosition();
            if (srcIndex < targetIndex || (srcIndex == targetIndex && this.srcIsFirst)) {
                System.arraycopy(orig, 0, result, 0, srcIndex);
                System.arraycopy(orig, srcIndex, result, srcFiles.length + srcIndex, targetIndex - srcIndex);
                System.arraycopy(targetFiles, 0, result, srcFiles.length + targetIndex, targetFiles.length);
                System.arraycopy(orig, targetIndex, result, srcFiles.length + targetIndex + targetFiles.length, orig.length - targetIndex);
            } else {
                System.arraycopy(orig, 0, result, 0, targetIndex);
                System.arraycopy(targetFiles, 0, result, targetIndex, targetFiles.length);
                System.arraycopy(orig, targetIndex, result, targetFiles.length + targetIndex, srcIndex - targetIndex);
                System.arraycopy(orig, srcIndex, result, srcFiles.length + srcIndex + targetFiles.length, orig.length - srcIndex);
                srcIndex += targetFiles.length;
            }
        } else {
            System.arraycopy(orig, 0, result, 0, srcIndex);
            System.arraycopy(orig, srcIndex, result, srcFiles.length + srcIndex, orig.length - srcIndex);
        }
        for (int i = 0; i < srcFiles.length; i++) {
            if (!this.relative) {
                result[srcIndex + i] = new File(baseDirs[i], srcFiles[i]).getAbsolutePath();
            } else {
                result[srcIndex + i] = srcFiles[i];
            }
            if (this.forwardSlash && fileSeparator != '/') {
                result[srcIndex + i] = result[srcIndex + i].replace(fileSeparator, '/');
            }
        }
        return result;
    }

    protected String[] getCommandline(String srcFile, File baseDir) {
        return getCommandline(new String[]{srcFile}, new File[]{baseDir});
    }

    protected String[] getFiles(File baseDir, DirectoryScanner ds) {
        return restrict(ds.getIncludedFiles(), baseDir);
    }

    protected String[] getDirs(File baseDir, DirectoryScanner ds) {
        return restrict(ds.getIncludedDirectories(), baseDir);
    }

    protected String[] getFilesAndDirs(FileList list) {
        return restrict(list.getFiles(getProject()), list.getDir(getProject()));
    }

    private String[] restrict(String[] s, File baseDir) {
        return (this.mapper == null || this.force) ? s : new SourceFileScanner(this).restrict(s, baseDir, this.destDir, this.mapper);
    }

    protected void runParallel(Execute exe, Vector fileNames, Vector baseDirs) throws IOException, BuildException {
        String[] s = new String[fileNames.size()];
        fileNames.copyInto(s);
        File[] b = new File[baseDirs.size()];
        baseDirs.copyInto(b);
        if (this.maxParallel <= 0 || s.length == 0) {
            String[] command = getCommandline(s, b);
            log(Commandline.describeCommand(command), 3);
            exe.setCommandline(command);
            runExecute(exe);
            return;
        }
        int stillToDo = fileNames.size();
        int currentOffset = 0;
        while (stillToDo > 0) {
            int currentAmount = Math.min(stillToDo, this.maxParallel);
            String[] cs = new String[currentAmount];
            System.arraycopy(s, currentOffset, cs, 0, currentAmount);
            File[] cb = new File[currentAmount];
            System.arraycopy(b, currentOffset, cb, 0, currentAmount);
            String[] command2 = getCommandline(cs, cb);
            log(Commandline.describeCommand(command2), 3);
            exe.setCommandline(command2);
            if (this.redirectorElement != null) {
                setupRedirector();
                this.redirectorElement.configure(this.redirector, null);
            }
            if (this.redirectorElement != null || currentOffset > 0) {
                exe.setStreamHandler(this.redirector.createHandler());
            }
            runExecute(exe);
            stillToDo -= currentAmount;
            currentOffset += currentAmount;
        }
    }

    public static class FileDirBoth extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{TypeSelector.FileType.FILE, TypeSelector.FileType.DIR, "both"};
        }
    }
}
