package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;

/* JADX INFO: loaded from: classes.dex */
public class Sync extends Task {
    private MyCopy _copy;

    @Override // org.apache.tools.ant.Task
    public void init() throws BuildException {
        MyCopy myCopy = new MyCopy();
        this._copy = myCopy;
        configureTask(myCopy);
        this._copy.setFiltering(false);
        this._copy.setIncludeEmptyDirs(false);
        this._copy.setPreserveLastModified(true);
    }

    private void configureTask(Task helper) {
        helper.setProject(getProject());
        helper.setTaskName(getTaskName());
        helper.setOwningTarget(getOwningTarget());
        helper.init();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File toDir = this._copy.getToDir();
        Hashtable allFiles = this._copy._dest2src;
        boolean noRemovalNecessary = !toDir.exists() || toDir.list().length < 1;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PASS#1: Copying files to ");
        stringBuffer.append(toDir);
        log(stringBuffer.toString(), 4);
        this._copy.execute();
        if (noRemovalNecessary) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("NO removing necessary in ");
            stringBuffer2.append(toDir);
            log(stringBuffer2.toString(), 4);
            return;
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("PASS#2: Removing orphan files from ");
        stringBuffer3.append(toDir);
        log(stringBuffer3.toString(), 4);
        int[] removedFileCount = removeOrphanFiles(allFiles, toDir);
        logRemovedCount(removedFileCount[0], "dangling director", "y", "ies");
        logRemovedCount(removedFileCount[1], "dangling file", "", "s");
        if (!this._copy.getIncludeEmptyDirs()) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("PASS#3: Removing empty directories from ");
            stringBuffer4.append(toDir);
            log(stringBuffer4.toString(), 4);
            int removedDirCount = removeEmptyDirectories(toDir, false);
            logRemovedCount(removedDirCount, "empty director", "y", "ies");
        }
    }

    private void logRemovedCount(int count, String prefix, String singularSuffix, String pluralSuffix) {
        File toDir = this._copy.getToDir();
        String what = prefix == null ? "" : prefix;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(what);
        stringBuffer.append(count < 2 ? singularSuffix : pluralSuffix);
        String what2 = stringBuffer.toString();
        if (count > 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Removed ");
            stringBuffer2.append(count);
            stringBuffer2.append(" ");
            stringBuffer2.append(what2);
            stringBuffer2.append(" from ");
            stringBuffer2.append(toDir);
            log(stringBuffer2.toString(), 2);
            return;
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("NO ");
        stringBuffer3.append(what2);
        stringBuffer3.append(" to remove from ");
        stringBuffer3.append(toDir);
        log(stringBuffer3.toString(), 3);
    }

    private int[] removeOrphanFiles(Hashtable nonOrphans, File file) {
        int[] removedCount = {0, 0, 0};
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File file2 : children) {
                int[] temp = removeOrphanFiles(nonOrphans, file2);
                removedCount[0] = removedCount[0] + temp[0];
                removedCount[1] = removedCount[1] + temp[1];
                removedCount[2] = removedCount[2] + temp[2];
            }
            if (nonOrphans.get(file) == null && removedCount[2] == 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Removing orphan directory: ");
                stringBuffer.append(file);
                log(stringBuffer.toString(), 4);
                file.delete();
                removedCount[0] = removedCount[0] + 1;
            } else {
                removedCount[2] = 1;
            }
        } else if (nonOrphans.get(file) == null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Removing orphan file: ");
            stringBuffer2.append(file);
            log(stringBuffer2.toString(), 4);
            file.delete();
            removedCount[1] = removedCount[1] + 1;
        } else {
            removedCount[2] = 1;
        }
        return removedCount;
    }

    private int removeEmptyDirectories(File dir, boolean removeIfEmpty) {
        int removedCount = 0;
        if (!dir.isDirectory()) {
            return 0;
        }
        File[] children = dir.listFiles();
        for (File file : children) {
            if (file.isDirectory()) {
                removedCount += removeEmptyDirectories(file, true);
            }
        }
        if (children.length > 0) {
            children = dir.listFiles();
        }
        if (children.length < 1 && removeIfEmpty) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Removing empty directory: ");
            stringBuffer.append(dir);
            log(stringBuffer.toString(), 4);
            dir.delete();
            return removedCount + 1;
        }
        return removedCount;
    }

    public void setTodir(File destDir) {
        this._copy.setTodir(destDir);
    }

    public void setVerbose(boolean verbose) {
        this._copy.setVerbose(verbose);
    }

    public void setOverwrite(boolean overwrite) {
        this._copy.setOverwrite(overwrite);
    }

    public void setIncludeEmptyDirs(boolean includeEmpty) {
        this._copy.setIncludeEmptyDirs(includeEmpty);
    }

    public void setFailOnError(boolean failonerror) {
        this._copy.setFailOnError(failonerror);
    }

    public void addFileset(FileSet set) {
        this._copy.addFileset(set);
    }

    public void setGranularity(long granularity) {
        this._copy.setGranularity(granularity);
    }

    public static class MyCopy extends Copy {
        private Hashtable _dest2src = new Hashtable();

        @Override // org.apache.tools.ant.taskdefs.Copy
        protected void buildMap(File fromDir, File toDir, String[] names, FileNameMapper mapper, Hashtable map) {
            Sync.assertTrue("No mapper", mapper instanceof IdentityMapper);
            super.buildMap(fromDir, toDir, names, mapper, map);
            for (String name : names) {
                File dest = new File(toDir, name);
                this._dest2src.put(dest, fromDir);
            }
        }

        public File getToDir() {
            return this.destDir;
        }

        public boolean getIncludeEmptyDirs() {
            return this.includeEmpty;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Assertion Error: ");
            stringBuffer.append(message);
            throw new BuildException(stringBuffer.toString());
        }
    }
}
