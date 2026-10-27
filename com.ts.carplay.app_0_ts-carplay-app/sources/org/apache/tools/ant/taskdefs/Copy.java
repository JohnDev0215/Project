package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.types.FilterSet;
import org.apache.tools.ant.types.FilterSetCollection;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.FlatFileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.SourceFileScanner;

/* JADX INFO: loaded from: classes.dex */
public class Copy extends Task {
    protected FileUtils fileUtils;
    private long granularity;
    protected File file = null;
    protected File destFile = null;
    protected File destDir = null;
    protected Vector filesets = new Vector();
    private boolean enableMultipleMappings = false;
    protected boolean filtering = false;
    protected boolean preserveLastModified = false;
    protected boolean forceOverwrite = false;
    protected boolean flatten = false;
    protected int verbosity = 3;
    protected boolean includeEmpty = true;
    protected boolean failonerror = true;
    protected Hashtable fileCopyMap = new Hashtable();
    protected Hashtable dirCopyMap = new Hashtable();
    protected Hashtable completeDirMap = new Hashtable();
    protected Mapper mapperElement = null;
    private Vector filterChains = new Vector();
    private Vector filterSets = new Vector();
    private String inputEncoding = null;
    private String outputEncoding = null;

    public Copy() {
        this.granularity = 0L;
        FileUtils fileUtilsNewFileUtils = FileUtils.newFileUtils();
        this.fileUtils = fileUtilsNewFileUtils;
        this.granularity = fileUtilsNewFileUtils.getFileTimestampGranularity();
    }

    protected FileUtils getFileUtils() {
        return this.fileUtils;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setTofile(File destFile) {
        this.destFile = destFile;
    }

    public void setTodir(File destDir) {
        this.destDir = destDir;
    }

    public FilterChain createFilterChain() {
        FilterChain filterChain = new FilterChain();
        this.filterChains.addElement(filterChain);
        return filterChain;
    }

    public FilterSet createFilterSet() {
        FilterSet filterSet = new FilterSet();
        this.filterSets.addElement(filterSet);
        return filterSet;
    }

    public void setPreserveLastModified(String preserve) {
        setPreserveLastModified(Project.toBoolean(preserve));
    }

    public void setPreserveLastModified(boolean preserve) {
        this.preserveLastModified = preserve;
    }

    public boolean getPreserveLastModified() {
        return this.preserveLastModified;
    }

    protected Vector getFilterSets() {
        return this.filterSets;
    }

    protected Vector getFilterChains() {
        return this.filterChains;
    }

    public void setFiltering(boolean filtering) {
        this.filtering = filtering;
    }

    public void setOverwrite(boolean overwrite) {
        this.forceOverwrite = overwrite;
    }

    public void setFlatten(boolean flatten) {
        this.flatten = flatten;
    }

    public void setVerbose(boolean verbose) {
        if (verbose) {
            this.verbosity = 2;
        } else {
            this.verbosity = 3;
        }
    }

    public void setIncludeEmptyDirs(boolean includeEmpty) {
        this.includeEmpty = includeEmpty;
    }

    public void setEnableMultipleMappings(boolean enableMultipleMappings) {
        this.enableMultipleMappings = enableMultipleMappings;
    }

    public boolean isEnableMultipleMapping() {
        return this.enableMultipleMappings;
    }

    public void setFailOnError(boolean failonerror) {
        this.failonerror = failonerror;
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
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

    public void setEncoding(String encoding) {
        this.inputEncoding = encoding;
        if (this.outputEncoding == null) {
            this.outputEncoding = encoding;
        }
    }

    public String getEncoding() {
        return this.inputEncoding;
    }

    public void setOutputEncoding(String encoding) {
        this.outputEncoding = encoding;
    }

    public String getOutputEncoding() {
        return this.outputEncoding;
    }

    public void setGranularity(long granularity) {
        this.granularity = granularity;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File savedFile = this.file;
        File savedDestFile = this.destFile;
        File savedDestDir = this.destDir;
        FileSet savedFileSet = null;
        if (this.file == null && this.destFile != null && this.filesets.size() == 1) {
            savedFileSet = (FileSet) this.filesets.elementAt(0);
        }
        validateAttributes();
        try {
            if (this.file != null) {
                if (this.file.exists()) {
                    if (this.destFile == null) {
                        this.destFile = new File(this.destDir, this.file.getName());
                    }
                    if (this.forceOverwrite || !this.destFile.exists() || this.file.lastModified() - this.granularity > this.destFile.lastModified()) {
                        this.fileCopyMap.put(this.file.getAbsolutePath(), new String[]{this.destFile.getAbsolutePath()});
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(this.file);
                        stringBuffer.append(" omitted as ");
                        stringBuffer.append(this.destFile);
                        stringBuffer.append(" is up to date.");
                        log(stringBuffer.toString(), 3);
                    }
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Warning: Could not find file ");
                    stringBuffer2.append(this.file.getAbsolutePath());
                    stringBuffer2.append(" to copy.");
                    String message = stringBuffer2.toString();
                    if (!this.failonerror) {
                        log(message);
                    } else {
                        throw new BuildException(message);
                    }
                }
            }
            for (int i = 0; i < this.filesets.size(); i++) {
                FileSet fs = (FileSet) this.filesets.elementAt(i);
                try {
                    DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                    File fromDir = fs.getDir(getProject());
                    String[] srcFiles = ds.getIncludedFiles();
                    String[] srcDirs = ds.getIncludedDirectories();
                    boolean isEverythingIncluded = (!ds.isEverythingIncluded() || fs.hasSelectors() || fs.hasPatterns()) ? false : true;
                    if (isEverythingIncluded && !this.flatten && this.mapperElement == null) {
                        this.completeDirMap.put(fromDir, this.destDir);
                    }
                    scan(fromDir, this.destDir, srcFiles, srcDirs);
                } catch (BuildException e) {
                    if (this.failonerror || !e.getMessage().endsWith(" not found.")) {
                        throw e;
                    }
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Warning: ");
                    stringBuffer3.append(e.getMessage());
                    log(stringBuffer3.toString());
                }
            }
            try {
                doFileOperations();
            } catch (BuildException e2) {
                if (this.failonerror) {
                    throw e2;
                }
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Warning: ");
                stringBuffer4.append(e2.getMessage());
                log(stringBuffer4.toString(), 0);
            }
        } finally {
            this.file = savedFile;
            this.destFile = savedDestFile;
            this.destDir = savedDestDir;
            if (savedFileSet != null) {
                this.filesets.insertElementAt(savedFileSet, 0);
            }
            this.fileCopyMap.clear();
            this.dirCopyMap.clear();
            this.completeDirMap.clear();
        }
    }

    protected void validateAttributes() throws BuildException {
        if (this.file == null && this.filesets.size() == 0) {
            throw new BuildException("Specify at least one source - a file or a fileset.");
        }
        if (this.destFile != null && this.destDir != null) {
            throw new BuildException("Only one of tofile and todir may be set.");
        }
        if (this.destFile == null && this.destDir == null) {
            throw new BuildException("One of tofile or todir must be set.");
        }
        File file = this.file;
        if (file != null && file.exists() && this.file.isDirectory()) {
            throw new BuildException("Use a fileset to copy directories.");
        }
        if (this.destFile != null && this.filesets.size() > 0) {
            if (this.filesets.size() > 1) {
                throw new BuildException("Cannot concatenate multiple files into a single file.");
            }
            FileSet fs = (FileSet) this.filesets.elementAt(0);
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            String[] srcFiles = ds.getIncludedFiles();
            if (srcFiles.length == 0) {
                throw new BuildException("Cannot perform operation from directory to file.");
            }
            if (srcFiles.length == 1) {
                if (this.file == null) {
                    this.file = new File(ds.getBasedir(), srcFiles[0]);
                    this.filesets.removeElementAt(0);
                } else {
                    throw new BuildException("Cannot concatenate multiple files into a single file.");
                }
            } else {
                throw new BuildException("Cannot concatenate multiple files into a single file.");
            }
        }
        File file2 = this.destFile;
        if (file2 != null) {
            this.destDir = this.fileUtils.getParentFile(file2);
        }
    }

    protected void scan(File fromDir, File toDir, String[] files, String[] dirs) {
        FileNameMapper mapper;
        Mapper mapper2 = this.mapperElement;
        if (mapper2 != null) {
            mapper = mapper2.getImplementation();
        } else if (this.flatten) {
            mapper = new FlatFileNameMapper();
        } else {
            mapper = new IdentityMapper();
        }
        buildMap(fromDir, toDir, files, mapper, this.fileCopyMap);
        if (this.includeEmpty) {
            buildMap(fromDir, toDir, dirs, mapper, this.dirCopyMap);
        }
    }

    protected void buildMap(File fromDir, File toDir, String[] names, FileNameMapper mapper, Hashtable map) {
        String[] toCopy;
        if (this.forceOverwrite) {
            Vector v = new Vector();
            for (int i = 0; i < names.length; i++) {
                if (mapper.mapFileName(names[i]) != null) {
                    v.addElement(names[i]);
                }
            }
            toCopy = new String[v.size()];
            v.copyInto(toCopy);
        } else {
            SourceFileScanner ds = new SourceFileScanner(this);
            toCopy = ds.restrict(names, fromDir, toDir, mapper, this.granularity);
        }
        for (int i2 = 0; i2 < toCopy.length; i2++) {
            File src = new File(fromDir, toCopy[i2]);
            String[] mappedFiles = mapper.mapFileName(toCopy[i2]);
            if (!this.enableMultipleMappings) {
                map.put(src.getAbsolutePath(), new String[]{new File(toDir, mappedFiles[0]).getAbsolutePath()});
            } else {
                for (int k = 0; k < mappedFiles.length; k++) {
                    mappedFiles[k] = new File(toDir, mappedFiles[k]).getAbsolutePath();
                }
                map.put(src.getAbsolutePath(), mappedFiles);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v8 */
    protected void doFileOperations() throws Throwable {
        String str;
        int i;
        String[] toFiles;
        String str2;
        String msg;
        File targetFile;
        if (this.fileCopyMap.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            String fromFile = "Copying ";
            stringBuffer.append("Copying ");
            stringBuffer.append(this.fileCopyMap.size());
            stringBuffer.append(" file");
            stringBuffer.append(this.fileCopyMap.size() == 1 ? "" : "s");
            stringBuffer.append(" to ");
            stringBuffer.append(this.destDir.getAbsolutePath());
            log(stringBuffer.toString());
            Enumeration e = this.fileCopyMap.keys();
            while (e.hasMoreElements()) {
                String fromFile2 = (String) e.nextElement();
                String[] toFiles2 = (String[]) this.fileCopyMap.get(fromFile2);
                int i2 = 0;
                while (i2 < toFiles2.length) {
                    String toFile = toFiles2[i2];
                    if (fromFile2.equals(toFile)) {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Skipping self-copy of ");
                        stringBuffer2.append(fromFile2);
                        log(stringBuffer2.toString(), this.verbosity);
                        str2 = fromFile;
                        i = i2;
                        toFiles = toFiles2;
                        fromFile = fromFile2;
                    } else {
                        try {
                            StringBuffer stringBuffer3 = new StringBuffer();
                            stringBuffer3.append(fromFile);
                            stringBuffer3.append(fromFile2);
                            stringBuffer3.append(" to ");
                            stringBuffer3.append(toFile);
                            log(stringBuffer3.toString(), this.verbosity);
                            FilterSetCollection executionFilters = new FilterSetCollection();
                            if (this.filtering) {
                                try {
                                    executionFilters.addFilterSet(getProject().getGlobalFilterSet());
                                } catch (IOException e2) {
                                    ioe = e2;
                                    str = toFile;
                                    fromFile = fromFile2;
                                    StringBuffer stringBuffer4 = new StringBuffer();
                                    stringBuffer4.append("Failed to copy ");
                                    stringBuffer4.append(fromFile);
                                    stringBuffer4.append(" to ");
                                    stringBuffer4.append(str);
                                    stringBuffer4.append(" due to ");
                                    stringBuffer4.append(ioe.getMessage());
                                    msg = stringBuffer4.toString();
                                    targetFile = new File(str);
                                    if (targetFile.exists() && !targetFile.delete()) {
                                        StringBuffer stringBuffer5 = new StringBuffer();
                                        stringBuffer5.append(msg);
                                        stringBuffer5.append(" and I couldn't delete the corrupt ");
                                        stringBuffer5.append(str);
                                        msg = stringBuffer5.toString();
                                    }
                                    throw new BuildException(msg, ioe, getLocation());
                                }
                            }
                            Enumeration filterEnum = this.filterSets.elements();
                            while (filterEnum.hasMoreElements()) {
                                String str3 = fromFile;
                                String toFile2 = toFile;
                                int i3 = i2;
                                String[] toFiles3 = toFiles2;
                                String fromFile3 = fromFile2;
                                executionFilters.addFilterSet((FilterSet) filterEnum.nextElement());
                                toFile = toFile2;
                                fromFile2 = fromFile3;
                                fromFile = str3;
                                toFiles2 = toFiles3;
                                i2 = i3;
                            }
                            str = toFile;
                            i = i2;
                            toFiles = toFiles2;
                            str2 = fromFile;
                            fromFile = fromFile2;
                            try {
                                this.fileUtils.copyFile(fromFile2, toFile, executionFilters, this.filterChains, this.forceOverwrite, this.preserveLastModified, this.inputEncoding, this.outputEncoding, getProject());
                            } catch (IOException e3) {
                                ioe = e3;
                                StringBuffer stringBuffer42 = new StringBuffer();
                                stringBuffer42.append("Failed to copy ");
                                stringBuffer42.append(fromFile);
                                stringBuffer42.append(" to ");
                                stringBuffer42.append(str);
                                stringBuffer42.append(" due to ");
                                stringBuffer42.append(ioe.getMessage());
                                msg = stringBuffer42.toString();
                                targetFile = new File(str);
                                if (targetFile.exists()) {
                                    StringBuffer stringBuffer52 = new StringBuffer();
                                    stringBuffer52.append(msg);
                                    stringBuffer52.append(" and I couldn't delete the corrupt ");
                                    stringBuffer52.append(str);
                                    msg = stringBuffer52.toString();
                                }
                                throw new BuildException(msg, ioe, getLocation());
                            }
                        } catch (IOException e4) {
                            ioe = e4;
                            str = toFile;
                            fromFile = fromFile2;
                        }
                    }
                    i2 = i + 1;
                    fromFile2 = fromFile;
                    fromFile = str2;
                    toFiles2 = toFiles;
                    str = 1;
                }
            }
        }
        if (this.includeEmpty) {
            Enumeration e5 = this.dirCopyMap.elements();
            int createCount = 0;
            while (e5.hasMoreElements()) {
                String[] dirs = (String[]) e5.nextElement();
                for (String str4 : dirs) {
                    File d = new File(str4);
                    if (!d.exists()) {
                        if (!d.mkdirs()) {
                            StringBuffer stringBuffer6 = new StringBuffer();
                            stringBuffer6.append("Unable to create directory ");
                            stringBuffer6.append(d.getAbsolutePath());
                            log(stringBuffer6.toString(), 0);
                        } else {
                            createCount++;
                        }
                    }
                }
            }
            if (createCount > 0) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("Copied ");
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
}
