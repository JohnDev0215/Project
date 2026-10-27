package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.selectors.AndSelector;
import org.apache.tools.ant.types.selectors.ContainsRegexpSelector;
import org.apache.tools.ant.types.selectors.ContainsSelector;
import org.apache.tools.ant.types.selectors.DateSelector;
import org.apache.tools.ant.types.selectors.DependSelector;
import org.apache.tools.ant.types.selectors.DepthSelector;
import org.apache.tools.ant.types.selectors.ExtendSelector;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.MajoritySelector;
import org.apache.tools.ant.types.selectors.NoneSelector;
import org.apache.tools.ant.types.selectors.NotSelector;
import org.apache.tools.ant.types.selectors.OrSelector;
import org.apache.tools.ant.types.selectors.PresentSelector;
import org.apache.tools.ant.types.selectors.SelectSelector;
import org.apache.tools.ant.types.selectors.SizeSelector;
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector;

/* JADX INFO: loaded from: classes.dex */
public class Delete extends MatchingTask {
    private static final int DELETE_RETRY_SLEEP_MILLIS = 10;
    protected File file = null;
    protected File dir = null;
    protected Vector filesets = new Vector();
    protected boolean usedMatchingTask = false;
    protected boolean includeEmpty = false;
    private int verbosity = 3;
    private boolean quiet = false;
    private boolean failonerror = true;
    private boolean deleteOnExit = false;

    public void setFile(File file) {
        this.file = file;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public void setVerbose(boolean verbose) {
        if (verbose) {
            this.verbosity = 2;
        } else {
            this.verbosity = 3;
        }
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
        if (quiet) {
            this.failonerror = false;
        }
    }

    public void setFailOnError(boolean failonerror) {
        this.failonerror = failonerror;
    }

    public void setDeleteOnExit(boolean deleteOnExit) {
        this.deleteOnExit = deleteOnExit;
    }

    public void setIncludeEmptyDirs(boolean includeEmpty) {
        this.includeEmpty = includeEmpty;
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public PatternSet.NameEntry createInclude() {
        this.usedMatchingTask = true;
        return super.createInclude();
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public PatternSet.NameEntry createIncludesFile() {
        this.usedMatchingTask = true;
        return super.createIncludesFile();
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public PatternSet.NameEntry createExclude() {
        this.usedMatchingTask = true;
        return super.createExclude();
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public PatternSet.NameEntry createExcludesFile() {
        this.usedMatchingTask = true;
        return super.createExcludesFile();
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public PatternSet createPatternSet() {
        this.usedMatchingTask = true;
        return super.createPatternSet();
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setIncludes(String includes) {
        this.usedMatchingTask = true;
        super.setIncludes(includes);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setExcludes(String excludes) {
        this.usedMatchingTask = true;
        super.setExcludes(excludes);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setDefaultexcludes(boolean useDefaultExcludes) {
        this.usedMatchingTask = true;
        super.setDefaultexcludes(useDefaultExcludes);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setIncludesfile(File includesfile) {
        this.usedMatchingTask = true;
        super.setIncludesfile(includesfile);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setExcludesfile(File excludesfile) {
        this.usedMatchingTask = true;
        super.setExcludesfile(excludesfile);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setCaseSensitive(boolean isCaseSensitive) {
        this.usedMatchingTask = true;
        super.setCaseSensitive(isCaseSensitive);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask
    public void setFollowSymlinks(boolean followSymlinks) {
        this.usedMatchingTask = true;
        super.setFollowSymlinks(followSymlinks);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addSelector(SelectSelector selector) {
        this.usedMatchingTask = true;
        super.addSelector(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addAnd(AndSelector selector) {
        this.usedMatchingTask = true;
        super.addAnd(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addOr(OrSelector selector) {
        this.usedMatchingTask = true;
        super.addOr(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addNot(NotSelector selector) {
        this.usedMatchingTask = true;
        super.addNot(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addNone(NoneSelector selector) {
        this.usedMatchingTask = true;
        super.addNone(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addMajority(MajoritySelector selector) {
        this.usedMatchingTask = true;
        super.addMajority(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDate(DateSelector selector) {
        this.usedMatchingTask = true;
        super.addDate(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addSize(SizeSelector selector) {
        this.usedMatchingTask = true;
        super.addSize(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addFilename(FilenameSelector selector) {
        this.usedMatchingTask = true;
        super.addFilename(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addCustom(ExtendSelector selector) {
        this.usedMatchingTask = true;
        super.addCustom(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addContains(ContainsSelector selector) {
        this.usedMatchingTask = true;
        super.addContains(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addPresent(PresentSelector selector) {
        this.usedMatchingTask = true;
        super.addPresent(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDepth(DepthSelector selector) {
        this.usedMatchingTask = true;
        super.addDepth(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addDepend(DependSelector selector) {
        this.usedMatchingTask = true;
        super.addDepend(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addContainsRegexp(ContainsRegexpSelector selector) {
        this.usedMatchingTask = true;
        super.addContainsRegexp(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void addModified(ModifiedSelector selector) {
        this.usedMatchingTask = true;
        super.addModified(selector);
    }

    @Override // org.apache.tools.ant.taskdefs.MatchingTask, org.apache.tools.ant.types.selectors.SelectorContainer
    public void add(FileSelector selector) {
        this.usedMatchingTask = true;
        super.add(selector);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file;
        if (this.usedMatchingTask) {
            log("DEPRECATED - Use of the implicit FileSet is deprecated.  Use a nested fileset element instead.");
        }
        if (this.file == null && this.dir == null && this.filesets.size() == 0) {
            throw new BuildException("At least one of the file or dir attributes, or a fileset element, must be set.");
        }
        if (this.quiet && this.failonerror) {
            throw new BuildException("quiet and failonerror cannot both be set to true", getLocation());
        }
        File file2 = this.file;
        if (file2 != null) {
            if (file2.exists()) {
                if (this.file.isDirectory()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Directory ");
                    stringBuffer.append(this.file.getAbsolutePath());
                    stringBuffer.append(" cannot be removed using the file attribute.  ");
                    stringBuffer.append("Use dir instead.");
                    log(stringBuffer.toString());
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Deleting: ");
                    stringBuffer2.append(this.file.getAbsolutePath());
                    log(stringBuffer2.toString());
                    if (!delete(this.file)) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Unable to delete file ");
                        stringBuffer3.append(this.file.getAbsolutePath());
                        String message = stringBuffer3.toString();
                        if (this.failonerror) {
                            throw new BuildException(message);
                        }
                        log(message, this.quiet ? 3 : 1);
                    }
                }
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Could not find file ");
                stringBuffer4.append(this.file.getAbsolutePath());
                stringBuffer4.append(" to delete.");
                log(stringBuffer4.toString(), 3);
            }
        }
        File file3 = this.dir;
        if (file3 != null && file3.exists() && this.dir.isDirectory() && !this.usedMatchingTask) {
            if (this.verbosity == 3) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("Deleting directory ");
                stringBuffer5.append(this.dir.getAbsolutePath());
                log(stringBuffer5.toString());
            }
            removeDir(this.dir);
        }
        for (int i = 0; i < this.filesets.size(); i++) {
            FileSet fs = (FileSet) this.filesets.elementAt(i);
            try {
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                String[] files = ds.getIncludedFiles();
                String[] dirs = ds.getIncludedDirectories();
                removeFiles(fs.getDir(getProject()), files, dirs);
            } catch (BuildException be) {
                if (this.failonerror) {
                    throw be;
                }
                log(be.getMessage(), this.quiet ? 3 : 1);
            }
        }
        if (this.usedMatchingTask && (file = this.dir) != null) {
            try {
                DirectoryScanner ds2 = super.getDirectoryScanner(file);
                String[] files2 = ds2.getIncludedFiles();
                String[] dirs2 = ds2.getIncludedDirectories();
                removeFiles(this.dir, files2, dirs2);
            } catch (BuildException be2) {
                if (this.failonerror) {
                    throw be2;
                }
                log(be2.getMessage(), this.quiet ? 3 : 1);
            }
        }
    }

    private boolean delete(File f) {
        if (!f.delete()) {
            if (Os.isFamily("windows")) {
                System.gc();
            }
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
            }
            if (!f.delete()) {
                if (this.deleteOnExit) {
                    int level = this.quiet ? 3 : 2;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Failed to delete ");
                    stringBuffer.append(f);
                    stringBuffer.append(", calling deleteOnExit.");
                    stringBuffer.append(" This attempts to delete the file when the ant jvm");
                    stringBuffer.append(" has exited and might not succeed.");
                    log(stringBuffer.toString(), level);
                    f.deleteOnExit();
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    protected void removeDir(File d) {
        String[] list = d.list();
        if (list == null) {
            list = new String[0];
        }
        int i = 0;
        while (true) {
            if (i < list.length) {
                String s = list[i];
                File f = new File(d, s);
                if (f.isDirectory()) {
                    removeDir(f);
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Deleting ");
                    stringBuffer.append(f.getAbsolutePath());
                    log(stringBuffer.toString(), this.verbosity);
                    if (delete(f)) {
                        continue;
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Unable to delete file ");
                        stringBuffer2.append(f.getAbsolutePath());
                        String message = stringBuffer2.toString();
                        if (this.failonerror) {
                            throw new BuildException(message);
                        }
                        log(message, this.quiet ? 3 : 1);
                    }
                }
                i++;
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Deleting directory ");
                stringBuffer3.append(d.getAbsolutePath());
                log(stringBuffer3.toString(), this.verbosity);
                if (!delete(d)) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Unable to delete directory ");
                    stringBuffer4.append(this.dir.getAbsolutePath());
                    String message2 = stringBuffer4.toString();
                    if (this.failonerror) {
                        throw new BuildException(message2);
                    }
                    log(message2, this.quiet ? 3 : 1);
                    return;
                }
                return;
            }
        }
    }

    protected void removeFiles(File d, String[] files, String[] dirs) {
        if (files.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Deleting ");
            stringBuffer.append(files.length);
            stringBuffer.append(" files from ");
            stringBuffer.append(d.getAbsolutePath());
            log(stringBuffer.toString());
            for (String str : files) {
                File f = new File(d, str);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Deleting ");
                stringBuffer2.append(f.getAbsolutePath());
                log(stringBuffer2.toString(), this.verbosity);
                if (!delete(f)) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Unable to delete file ");
                    stringBuffer3.append(f.getAbsolutePath());
                    String message = stringBuffer3.toString();
                    if (this.failonerror) {
                        throw new BuildException(message);
                    }
                    log(message, this.quiet ? 3 : 1);
                }
            }
        }
        int j = dirs.length;
        if (j > 0 && this.includeEmpty) {
            int dirCount = 0;
            for (int j2 = dirs.length - 1; j2 >= 0; j2--) {
                File currDir = new File(d, dirs[j2]);
                String[] dirFiles = currDir.list();
                if (dirFiles == null || dirFiles.length == 0) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Deleting ");
                    stringBuffer4.append(currDir.getAbsolutePath());
                    log(stringBuffer4.toString(), this.verbosity);
                    if (!delete(currDir)) {
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("Unable to delete directory ");
                        stringBuffer5.append(currDir.getAbsolutePath());
                        String message2 = stringBuffer5.toString();
                        if (this.failonerror) {
                            throw new BuildException(message2);
                        }
                        log(message2, this.quiet ? 3 : 1);
                    } else {
                        dirCount++;
                    }
                }
            }
            if (dirCount > 0) {
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append("Deleted ");
                stringBuffer6.append(dirCount);
                stringBuffer6.append(" director");
                stringBuffer6.append(dirCount == 1 ? "y" : "ies");
                stringBuffer6.append(" from ");
                stringBuffer6.append(d.getAbsolutePath());
                log(stringBuffer6.toString());
            }
        }
    }
}
