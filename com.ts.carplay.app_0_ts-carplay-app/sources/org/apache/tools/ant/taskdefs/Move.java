package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterSet;
import org.apache.tools.ant.types.FilterSetCollection;

/* JADX INFO: loaded from: classes.dex */
public class Move extends Copy {
    public Move() {
        setOverwrite(true);
    }

    @Override // org.apache.tools.ant.taskdefs.Copy
    protected void validateAttributes() throws BuildException {
        if (this.file != null && this.file.isDirectory()) {
            if ((this.destFile != null && this.destDir != null) || (this.destFile == null && this.destDir == null)) {
                throw new BuildException("One and only one of tofile and todir must be set.");
            }
            this.destFile = this.destFile == null ? new File(this.destDir, this.file.getName()) : this.destFile;
            this.destDir = this.destDir == null ? this.destFile.getParentFile() : this.destDir;
            this.completeDirMap.put(this.file, this.destFile);
            this.file = null;
            return;
        }
        super.validateAttributes();
    }

    @Override // org.apache.tools.ant.taskdefs.Copy
    protected void doFileOperations() throws Throwable {
        if (this.completeDirMap.size() > 0) {
            Enumeration e = this.completeDirMap.keys();
            while (e.hasMoreElements()) {
                File fromDir = (File) e.nextElement();
                File toDir = (File) this.completeDirMap.get(fromDir);
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Attempting to rename dir: ");
                    stringBuffer.append(fromDir);
                    stringBuffer.append(" to ");
                    stringBuffer.append(toDir);
                    log(stringBuffer.toString(), this.verbosity);
                    boolean renamed = renameFile(fromDir, toDir, this.filtering, this.forceOverwrite);
                    if (!renamed) {
                        FileSet fs = new FileSet();
                        fs.setProject(getProject());
                        fs.setDir(fromDir);
                        addFileset(fs);
                        DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                        String[] files = ds.getIncludedFiles();
                        String[] dirs = ds.getIncludedDirectories();
                        scan(fromDir, toDir, files, dirs);
                    }
                } catch (IOException ioe) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Failed to rename dir ");
                    stringBuffer2.append(fromDir);
                    stringBuffer2.append(" to ");
                    stringBuffer2.append(toDir);
                    stringBuffer2.append(" due to ");
                    stringBuffer2.append(ioe.getMessage());
                    String msg = stringBuffer2.toString();
                    throw new BuildException(msg, ioe, getLocation());
                }
            }
        }
        int moveCount = this.fileCopyMap.size();
        if (moveCount > 0) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Moving ");
            stringBuffer3.append(moveCount);
            stringBuffer3.append(" file");
            stringBuffer3.append(moveCount == 1 ? "" : "s");
            stringBuffer3.append(" to ");
            stringBuffer3.append(this.destDir.getAbsolutePath());
            log(stringBuffer3.toString());
            Enumeration e2 = this.fileCopyMap.keys();
            while (e2.hasMoreElements()) {
                String fromFile = (String) e2.nextElement();
                File f = new File(fromFile);
                boolean selfMove = false;
                if (f.exists()) {
                    String[] toFiles = (String[]) this.fileCopyMap.get(fromFile);
                    for (int i = 0; i < toFiles.length; i++) {
                        String toFile = toFiles[i];
                        if (fromFile.equals(toFile)) {
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append("Skipping self-move of ");
                            stringBuffer4.append(fromFile);
                            log(stringBuffer4.toString(), this.verbosity);
                            selfMove = true;
                        } else {
                            File d = new File(toFile);
                            if (i + 1 == toFiles.length && !selfMove) {
                                moveFile(f, d, this.filtering, this.forceOverwrite);
                            } else {
                                copyFile(f, d, this.filtering, this.forceOverwrite);
                            }
                        }
                    }
                }
            }
        }
        if (this.includeEmpty) {
            Enumeration e3 = this.dirCopyMap.keys();
            int createCount = 0;
            while (e3.hasMoreElements()) {
                String fromDirName = (String) e3.nextElement();
                String[] toDirNames = (String[]) this.dirCopyMap.get(fromDirName);
                boolean selfMove2 = false;
                for (int i2 = 0; i2 < toDirNames.length; i2++) {
                    if (fromDirName.equals(toDirNames[i2])) {
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("Skipping self-move of ");
                        stringBuffer5.append(fromDirName);
                        log(stringBuffer5.toString(), this.verbosity);
                        selfMove2 = true;
                    } else {
                        File d2 = new File(toDirNames[i2]);
                        if (!d2.exists()) {
                            if (!d2.mkdirs()) {
                                StringBuffer stringBuffer6 = new StringBuffer();
                                stringBuffer6.append("Unable to create directory ");
                                stringBuffer6.append(d2.getAbsolutePath());
                                log(stringBuffer6.toString(), 0);
                            } else {
                                createCount++;
                            }
                        }
                    }
                }
                File fromDir2 = new File(fromDirName);
                if (!selfMove2 && okToDelete(fromDir2)) {
                    deleteDir(fromDir2);
                }
            }
            if (createCount > 0) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("Moved ");
                stringBuffer7.append(this.dirCopyMap.size());
                stringBuffer7.append(" empty director");
                stringBuffer7.append(this.dirCopyMap.size() == 1 ? "y" : "ies");
                stringBuffer7.append(" to ");
                stringBuffer7.append(createCount);
                stringBuffer7.append(" empty director");
                stringBuffer7.append(createCount != 1 ? "ies" : "y");
                stringBuffer7.append(" under ");
                stringBuffer7.append(this.destDir.getAbsolutePath());
                log(stringBuffer7.toString());
            }
        }
    }

    private void moveFile(File fromFile, File toFile, boolean filtering, boolean overwrite) throws Throwable {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Attempting to rename: ");
            stringBuffer.append(fromFile);
            stringBuffer.append(" to ");
            stringBuffer.append(toFile);
            log(stringBuffer.toString(), this.verbosity);
            boolean moved = renameFile(fromFile, toFile, filtering, this.forceOverwrite);
            if (!moved) {
                copyFile(fromFile, toFile, filtering, overwrite);
                if (!fromFile.delete()) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Unable to delete file ");
                    stringBuffer2.append(fromFile.getAbsolutePath());
                    throw new BuildException(stringBuffer2.toString());
                }
            }
        } catch (IOException ioe) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Failed to rename ");
            stringBuffer3.append(fromFile);
            stringBuffer3.append(" to ");
            stringBuffer3.append(toFile);
            stringBuffer3.append(" due to ");
            stringBuffer3.append(ioe.getMessage());
            String msg = stringBuffer3.toString();
            throw new BuildException(msg, ioe, getLocation());
        }
    }

    private void copyFile(File fromFile, File toFile, boolean filtering, boolean overwrite) throws Throwable {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Copying ");
            stringBuffer.append(fromFile);
            stringBuffer.append(" to ");
            stringBuffer.append(toFile);
            log(stringBuffer.toString(), this.verbosity);
            FilterSetCollection executionFilters = new FilterSetCollection();
            if (filtering) {
                executionFilters.addFilterSet(getProject().getGlobalFilterSet());
            }
            Enumeration filterEnum = getFilterSets().elements();
            while (filterEnum.hasMoreElements()) {
                executionFilters.addFilterSet((FilterSet) filterEnum.nextElement());
            }
            getFileUtils().copyFile(fromFile, toFile, executionFilters, getFilterChains(), this.forceOverwrite, getPreserveLastModified(), getEncoding(), getOutputEncoding(), getProject());
        } catch (IOException ioe) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Failed to copy ");
            stringBuffer2.append(fromFile);
            stringBuffer2.append(" to ");
            stringBuffer2.append(toFile);
            stringBuffer2.append(" due to ");
            stringBuffer2.append(ioe.getMessage());
            String msg = stringBuffer2.toString();
            throw new BuildException(msg, ioe, getLocation());
        }
    }

    protected boolean okToDelete(File d) {
        String[] list = d.list();
        if (list == null) {
            return false;
        }
        for (String s : list) {
            File f = new File(d, s);
            if (!f.isDirectory() || !okToDelete(f)) {
                return false;
            }
        }
        return true;
    }

    protected void deleteDir(File d) {
        deleteDir(d, false);
    }

    protected void deleteDir(File d, boolean deleteFiles) {
        String[] list = d.list();
        if (list == null) {
            return;
        }
        for (String s : list) {
            File f = new File(d, s);
            if (f.isDirectory()) {
                deleteDir(f);
            } else {
                if (deleteFiles && !f.delete()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unable to delete file ");
                    stringBuffer.append(f.getAbsolutePath());
                    throw new BuildException(stringBuffer.toString());
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("UNEXPECTED ERROR - The file ");
                stringBuffer2.append(f.getAbsolutePath());
                stringBuffer2.append(" should not exist!");
                throw new BuildException(stringBuffer2.toString());
            }
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("Deleting directory ");
        stringBuffer3.append(d.getAbsolutePath());
        log(stringBuffer3.toString(), this.verbosity);
        if (!d.delete()) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Unable to delete directory ");
            stringBuffer4.append(d.getAbsolutePath());
            throw new BuildException(stringBuffer4.toString());
        }
    }

    protected boolean renameFile(File sourceFile, File destFile, boolean filtering, boolean overwrite) throws IOException, BuildException {
        if (getFilterSets().size() + getFilterChains().size() != 0 || filtering || destFile.isDirectory()) {
            return false;
        }
        File parent = destFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (destFile.isFile() && !destFile.delete()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to remove existing file ");
            stringBuffer.append(destFile);
            throw new BuildException(stringBuffer.toString());
        }
        boolean renamed = sourceFile.renameTo(destFile);
        return renamed;
    }
}
