package org.apache.tools.ant.taskdefs;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import java.util.zip.CRC32;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.types.ZipScanner;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.GlobPatternMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.apache.tools.ant.util.MergingMapper;
import org.apache.tools.ant.util.ResourceUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipExtraField;
import org.apache.tools.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Zip extends MatchingTask {
    private static final long EMPTY_CRC = new CRC32().getValue();
    private static FileUtils fileUtils = FileUtils.newFileUtils();
    private File baseDir;
    private String encoding;
    protected File zipFile;
    private ZipScanner zs;
    protected Hashtable entries = new Hashtable();
    private Vector groupfilesets = new Vector();
    private Vector filesetsFromGroupfilesets = new Vector();
    protected String duplicate = "add";
    private boolean doCompress = true;
    private boolean doUpdate = false;
    private boolean savedDoUpdate = false;
    private boolean doFilesonly = false;
    protected String archiveType = "zip";
    protected String emptyBehavior = "skip";
    private Vector filesets = new Vector();
    protected Hashtable addedDirs = new Hashtable();
    private Vector addedFiles = new Vector();
    protected boolean doubleFilePass = false;
    protected boolean skipWriting = false;
    private boolean addingNewFiles = false;
    private boolean keepCompression = false;
    private boolean roundUp = true;
    private String comment = "";

    public void setZipfile(File zipFile) {
        setDestFile(zipFile);
    }

    public void setFile(File file) {
        setDestFile(file);
    }

    public void setDestFile(File destFile) {
        this.zipFile = destFile;
    }

    public File getDestFile() {
        return this.zipFile;
    }

    public void setBasedir(File baseDir) {
        this.baseDir = baseDir;
    }

    public void setCompress(boolean c) {
        this.doCompress = c;
    }

    public boolean isCompress() {
        return this.doCompress;
    }

    public void setFilesonly(boolean f) {
        this.doFilesonly = f;
    }

    public void setUpdate(boolean c) {
        this.doUpdate = c;
        this.savedDoUpdate = c;
    }

    public boolean isInUpdateMode() {
        return this.doUpdate;
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    public void addZipfileset(ZipFileSet set) {
        this.filesets.addElement(set);
    }

    public void addZipGroupFileset(FileSet set) {
        this.groupfilesets.addElement(set);
    }

    public void setDuplicate(Duplicate df) {
        this.duplicate = df.getValue();
    }

    public static class WhenEmpty extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"fail", "skip", "create"};
        }
    }

    public void setWhenempty(WhenEmpty we) {
        this.emptyBehavior = we.getValue();
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setKeepCompression(boolean keep) {
        this.keepCompression = keep;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public void setRoundUp(boolean r) {
        this.roundUp = r;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.doubleFilePass) {
            this.skipWriting = true;
            executeMain();
            this.skipWriting = false;
            executeMain();
            return;
        }
        executeMain();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void executeMain() throws BuildException {
        File fileCreateTempFile;
        if (this.baseDir == null && this.filesets.size() == 0 && this.groupfilesets.size() == 0 && "zip".equals(this.archiveType)) {
            throw new BuildException("basedir attribute must be set, or at least one fileset must be given!");
        }
        File file = this.zipFile;
        if (file == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("You must specify the ");
            stringBuffer.append(this.archiveType);
            stringBuffer.append(" file to create!");
            throw new BuildException(stringBuffer.toString());
        }
        if (file.exists() && !this.zipFile.isFile()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.zipFile);
            stringBuffer2.append(" is not a file.");
            throw new BuildException(stringBuffer2.toString());
        }
        if (this.zipFile.exists() && !this.zipFile.canWrite()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(this.zipFile);
            stringBuffer3.append(" is read-only.");
            throw new BuildException(stringBuffer3.toString());
        }
        this.addingNewFiles = true;
        if (this.doUpdate && !this.zipFile.exists()) {
            this.doUpdate = false;
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("ignoring update attribute as ");
            stringBuffer4.append(this.archiveType);
            stringBuffer4.append(" doesn't exist.");
            log(stringBuffer4.toString(), 4);
        }
        for (int i = 0; i < this.groupfilesets.size(); i++) {
            log("Processing groupfileset ", 3);
            DirectoryScanner directoryScanner = ((FileSet) this.groupfilesets.elementAt(i)).getDirectoryScanner(getProject());
            String[] includedFiles = directoryScanner.getIncludedFiles();
            File basedir = directoryScanner.getBasedir();
            for (int i2 = 0; i2 < includedFiles.length; i2++) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("Adding file ");
                stringBuffer5.append(includedFiles[i2]);
                stringBuffer5.append(" to fileset");
                log(stringBuffer5.toString(), 3);
                ZipFileSet zipFileSet = new ZipFileSet();
                zipFileSet.setProject(getProject());
                zipFileSet.setSrc(new File(basedir, includedFiles[i2]));
                this.filesets.addElement(zipFileSet);
                this.filesetsFromGroupfilesets.addElement(zipFileSet);
            }
        }
        Vector vector = new Vector();
        if (this.baseDir != null) {
            FileSet fileSet = (FileSet) getImplicitFileSet().clone();
            fileSet.setDir(this.baseDir);
            vector.addElement(fileSet);
        }
        for (int i3 = 0; i3 < this.filesets.size(); i3++) {
            vector.addElement((FileSet) this.filesets.elementAt(i3));
        }
        int size = vector.size();
        FileSet[] fileSetArr = new FileSet[size];
        vector.copyInto(fileSetArr);
        File file2 = null;
        zipOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        try {
            try {
                ArchiveState resourcesToAdd = getResourcesToAdd(fileSetArr, this.zipFile, false);
                if (!resourcesToAdd.isOutOfDate()) {
                    return;
                }
                if (!this.zipFile.exists() && resourcesToAdd.isWithoutAnyResources()) {
                    createEmptyZip(this.zipFile);
                    return;
                }
                Resource[][] resourcesToAdd2 = resourcesToAdd.getResourcesToAdd();
                if (this.doUpdate) {
                    fileCreateTempFile = fileUtils.createTempFile("zip", ".tmp", fileUtils.getParentFile(this.zipFile));
                    try {
                        fileCreateTempFile.deleteOnExit();
                        try {
                            fileUtils.rename(this.zipFile, fileCreateTempFile);
                        } catch (IOException e) {
                            StringBuffer stringBuffer6 = new StringBuffer();
                            stringBuffer6.append("Unable to rename old file (");
                            stringBuffer6.append(this.zipFile.getAbsolutePath());
                            stringBuffer6.append(") to temporary file");
                            throw new BuildException(stringBuffer6.toString());
                        } catch (SecurityException e2) {
                            StringBuffer stringBuffer7 = new StringBuffer();
                            stringBuffer7.append("Not allowed to rename old file (");
                            stringBuffer7.append(this.zipFile.getAbsolutePath());
                            stringBuffer7.append(") to temporary file");
                            throw new BuildException(stringBuffer7.toString());
                        }
                    } catch (IOException e3) {
                        e = e3;
                        file2 = fileCreateTempFile;
                        StringBuffer stringBuffer8 = new StringBuffer();
                        stringBuffer8.append("Problem creating ");
                        stringBuffer8.append(this.archiveType);
                        stringBuffer8.append(": ");
                        stringBuffer8.append(e.getMessage());
                        String string = stringBuffer8.toString();
                        if ((!this.doUpdate || file2 != null) && !this.zipFile.delete()) {
                            StringBuffer stringBuffer9 = new StringBuffer();
                            stringBuffer9.append(string);
                            stringBuffer9.append(" (and the archive is probably corrupt but I could not delete it)");
                            string = stringBuffer9.toString();
                        }
                        if (this.doUpdate && file2 != null) {
                            try {
                                fileUtils.rename(file2, this.zipFile);
                            } catch (IOException e4) {
                                StringBuffer stringBuffer10 = new StringBuffer();
                                stringBuffer10.append(string);
                                stringBuffer10.append(" (and I couldn't rename the temporary file ");
                                stringBuffer10.append(file2.getName());
                                stringBuffer10.append(" back)");
                                string = stringBuffer10.toString();
                            }
                        }
                        throw new BuildException(string, e, getLocation());
                    }
                } else {
                    fileCreateTempFile = null;
                }
                String str = this.doUpdate ? "Updating " : "Building ";
                StringBuffer stringBuffer11 = new StringBuffer();
                stringBuffer11.append(str);
                stringBuffer11.append(this.archiveType);
                stringBuffer11.append(": ");
                stringBuffer11.append(this.zipFile.getAbsolutePath());
                log(stringBuffer11.toString());
                try {
                    if (!this.skipWriting) {
                        ZipOutputStream zipOutputStream2 = new ZipOutputStream(this.zipFile);
                        try {
                            zipOutputStream2.setEncoding(this.encoding);
                            if (this.doCompress) {
                                zipOutputStream2.setMethod(8);
                            } else {
                                zipOutputStream2.setMethod(0);
                            }
                            zipOutputStream = zipOutputStream2;
                        } catch (Throwable th) {
                            th = th;
                            zipOutputStream = zipOutputStream2;
                            if (zipOutputStream != null) {
                                try {
                                    zipOutputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    }
                    initZipOutputStream(zipOutputStream);
                    for (int i4 = 0; i4 < size; i4++) {
                        if (resourcesToAdd2[i4].length != 0) {
                            addResources(fileSetArr[i4], resourcesToAdd2[i4], zipOutputStream);
                        }
                    }
                    if (this.doUpdate) {
                        this.addingNewFiles = false;
                        ZipFileSet zipFileSet2 = new ZipFileSet();
                        zipFileSet2.setProject(getProject());
                        zipFileSet2.setSrc(fileCreateTempFile);
                        zipFileSet2.setDefaultexcludes(false);
                        for (int i5 = 0; i5 < this.addedFiles.size(); i5++) {
                            zipFileSet2.createExclude().setName((String) this.addedFiles.elementAt(i5));
                        }
                        DirectoryScanner directoryScanner2 = zipFileSet2.getDirectoryScanner(getProject());
                        ((ZipScanner) directoryScanner2).setEncoding(this.encoding);
                        String[] includedFiles2 = directoryScanner2.getIncludedFiles();
                        int length = includedFiles2.length;
                        Resource[] resourceArr = new Resource[length];
                        for (int i6 = 0; i6 < includedFiles2.length; i6++) {
                            resourceArr[i6] = directoryScanner2.getResource(includedFiles2[i6]);
                        }
                        if (!this.doFilesonly) {
                            String[] includedDirectories = directoryScanner2.getIncludedDirectories();
                            int length2 = includedDirectories.length;
                            Resource[] resourceArr2 = new Resource[length2];
                            for (int i7 = 0; i7 < includedDirectories.length; i7++) {
                                resourceArr2[i7] = directoryScanner2.getResource(includedDirectories[i7]);
                            }
                            Resource[] resourceArr3 = new Resource[length + length2];
                            System.arraycopy(resourceArr2, 0, resourceArr3, 0, length2);
                            System.arraycopy(resourceArr, 0, resourceArr3, length2, length);
                            resourceArr = resourceArr3;
                        }
                        addResources(zipFileSet2, resourceArr, zipOutputStream);
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.setComment(this.comment);
                    }
                    finalizeZipOutputStream(zipOutputStream);
                    if (this.doUpdate && !fileCreateTempFile.delete()) {
                        StringBuffer stringBuffer12 = new StringBuffer();
                        stringBuffer12.append("Warning: unable to delete temporary file ");
                        stringBuffer12.append(fileCreateTempFile.getName());
                        log(stringBuffer12.toString(), 1);
                    }
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (IOException e6) {
                            throw e6;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e7) {
                e = e7;
            }
        } finally {
            cleanUp();
        }
    }

    protected final boolean isAddingNewFiles() {
        return this.addingNewFiles;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0217 A[Catch: all -> 0x02ca, TryCatch #4 {all -> 0x02ca, blocks: (B:102:0x020f, B:104:0x0217, B:106:0x0224), top: B:154:0x020f }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:180:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01c8 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void addResources(org.apache.tools.ant.types.FileSet r28, org.apache.tools.ant.types.Resource[] r29, org.apache.tools.zip.ZipOutputStream r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 747
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Zip.addResources(org.apache.tools.ant.types.FileSet, org.apache.tools.ant.types.Resource[], org.apache.tools.zip.ZipOutputStream):void");
    }

    protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
    }

    protected void finalizeZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean createEmptyZip(java.io.File r6) throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Note: creating empty "
            r0.append(r1)
            java.lang.String r1 = r5.archiveType
            r0.append(r1)
            java.lang.String r1 = " archive "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r1 = 2
            r5.log(r0, r1)
            r0 = 0
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r0 = r3
            r3 = 22
            byte[] r2 = new byte[r3]     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r3 = 0
            r4 = 80
            r2[r3] = r4     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r3 = 75
            r4 = 1
            r2[r4] = r3     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r3 = 5
            r2[r1] = r3     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r1 = 3
            r3 = 6
            r2[r1] = r3     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r0.write(r2)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r1 = r2
            r0.close()     // Catch: java.io.IOException -> L45
            goto L47
        L45:
            r2 = move-exception
        L47:
            return r4
        L4a:
            r1 = move-exception
            goto L73
        L4c:
            r1 = move-exception
            r2 = r1
            org.apache.tools.ant.BuildException r1 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L72
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L72
            r3.<init>()     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = "Could not create empty ZIP archive ("
            r3.append(r4)     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = r2.getMessage()     // Catch: java.lang.Throwable -> L72
            r3.append(r4)     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = ")"
            r3.append(r4)     // Catch: java.lang.Throwable -> L72
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L72
            org.apache.tools.ant.Location r4 = r5.getLocation()     // Catch: java.lang.Throwable -> L72
            r1.<init>(r3, r2, r4)     // Catch: java.lang.Throwable -> L72
            throw r1     // Catch: java.lang.Throwable -> L72
        L72:
            r1 = move-exception
        L73:
            if (r0 == 0) goto L7a
            r0.close()     // Catch: java.io.IOException -> L79
            goto L7a
        L79:
            r3 = move-exception
        L7a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Zip.createEmptyZip(java.io.File):boolean");
    }

    private synchronized ZipScanner getZipScanner() {
        if (this.zs == null) {
            ZipScanner zipScanner = new ZipScanner();
            this.zs = zipScanner;
            zipScanner.setEncoding(this.encoding);
            this.zs.setSrc(this.zipFile);
        }
        return this.zs;
    }

    protected ArchiveState getResourcesToAdd(FileSet[] filesets, File zipFile, boolean needsUpdate) throws BuildException {
        boolean needsUpdate2;
        Resource[][] initialResources = grabResources(filesets);
        if (isEmpty(initialResources)) {
            if (needsUpdate && this.doUpdate) {
                return new ArchiveState(true, initialResources);
            }
            if (this.emptyBehavior.equals("skip")) {
                if (this.doUpdate) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(this.archiveType);
                    stringBuffer.append(" archive ");
                    stringBuffer.append(zipFile);
                    stringBuffer.append(" not updated because no new files were included.");
                    log(stringBuffer.toString(), 3);
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Warning: skipping ");
                    stringBuffer2.append(this.archiveType);
                    stringBuffer2.append(" archive ");
                    stringBuffer2.append(zipFile);
                    stringBuffer2.append(" because no files were included.");
                    log(stringBuffer2.toString(), 1);
                }
            } else {
                if (this.emptyBehavior.equals("fail")) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Cannot create ");
                    stringBuffer3.append(this.archiveType);
                    stringBuffer3.append(" archive ");
                    stringBuffer3.append(zipFile);
                    stringBuffer3.append(": no files were included.");
                    throw new BuildException(stringBuffer3.toString(), getLocation());
                }
                if (!zipFile.exists()) {
                    needsUpdate2 = true;
                }
                return new ArchiveState(needsUpdate2, initialResources);
            }
            needsUpdate2 = needsUpdate;
            return new ArchiveState(needsUpdate2, initialResources);
        }
        if (!zipFile.exists()) {
            return new ArchiveState(true, initialResources);
        }
        if (needsUpdate && !this.doUpdate) {
            return new ArchiveState(true, initialResources);
        }
        Resource[][] newerResources = new Resource[filesets.length][];
        for (int i = 0; i < filesets.length; i++) {
            if (!(this.fileset instanceof ZipFileSet) || ((ZipFileSet) this.fileset).getSrc(getProject()) == null) {
                File base = filesets[i].getDir(getProject());
                for (int j = 0; j < initialResources[i].length; j++) {
                    File resourceAsFile = fileUtils.resolveFile(base, initialResources[i][j].getName());
                    if (resourceAsFile.equals(zipFile)) {
                        throw new BuildException("A zip file cannot include itself", getLocation());
                    }
                }
            }
        }
        boolean needsUpdate3 = needsUpdate;
        for (int i2 = 0; i2 < filesets.length; i2++) {
            if (initialResources[i2].length == 0) {
                newerResources[i2] = new Resource[0];
            } else {
                FileNameMapper myMapper = new IdentityMapper();
                if (filesets[i2] instanceof ZipFileSet) {
                    ZipFileSet zfs = (ZipFileSet) filesets[i2];
                    if (zfs.getFullpath(getProject()) != null && !zfs.getFullpath(getProject()).equals("")) {
                        MergingMapper fm = new MergingMapper();
                        fm.setTo(zfs.getFullpath(getProject()));
                        myMapper = fm;
                    } else if (zfs.getPrefix(getProject()) != null && !zfs.getPrefix(getProject()).equals("")) {
                        GlobPatternMapper gm = new GlobPatternMapper();
                        gm.setFrom("*");
                        String prefix = zfs.getPrefix(getProject());
                        if (!prefix.endsWith("/") && !prefix.endsWith("\\")) {
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append(prefix);
                            stringBuffer4.append("/");
                            prefix = stringBuffer4.toString();
                        }
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append(prefix);
                        stringBuffer5.append("*");
                        gm.setTo(stringBuffer5.toString());
                        myMapper = gm;
                    }
                }
                Resource[] resources = initialResources[i2];
                if (this.doFilesonly) {
                    resources = selectFileResources(resources);
                }
                newerResources[i2] = ResourceUtils.selectOutOfDateSources(this, resources, myMapper, getZipScanner());
                needsUpdate3 = needsUpdate3 || newerResources[i2].length > 0;
                if (needsUpdate3 && !this.doUpdate) {
                    break;
                }
            }
        }
        if (needsUpdate3 && !this.doUpdate) {
            return new ArchiveState(true, initialResources);
        }
        return new ArchiveState(needsUpdate3, newerResources);
    }

    protected Resource[][] grabResources(FileSet[] filesets) {
        Resource[][] result = new Resource[filesets.length][];
        for (int i = 0; i < filesets.length; i++) {
            boolean skipEmptyNames = true;
            if (filesets[i] instanceof ZipFileSet) {
                ZipFileSet zfs = (ZipFileSet) filesets[i];
                skipEmptyNames = zfs.getPrefix(getProject()).equals("") && zfs.getFullpath(getProject()).equals("");
            }
            DirectoryScanner rs = filesets[i].getDirectoryScanner(getProject());
            if (rs instanceof ZipScanner) {
                ((ZipScanner) rs).setEncoding(this.encoding);
            }
            Vector resources = new Vector();
            String[] directories = rs.getIncludedDirectories();
            for (int j = 0; j < directories.length; j++) {
                if (!"".equals(directories[j]) || !skipEmptyNames) {
                    resources.addElement(rs.getResource(directories[j]));
                }
            }
            String[] files = rs.getIncludedFiles();
            for (int j2 = 0; j2 < files.length; j2++) {
                if (!"".equals(files[j2]) || !skipEmptyNames) {
                    resources.addElement(rs.getResource(files[j2]));
                }
            }
            result[i] = new Resource[resources.size()];
            resources.copyInto(result[i]);
        }
        return result;
    }

    protected void zipDir(File dir, ZipOutputStream zOut, String vPath, int mode) throws IOException {
        zipDir(dir, zOut, vPath, mode, null);
    }

    protected void zipDir(File dir, ZipOutputStream zOut, String vPath, int mode, ZipExtraField[] extra) throws IOException {
        if (this.addedDirs.get(vPath) != null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("adding directory ");
        stringBuffer.append(vPath);
        log(stringBuffer.toString(), 3);
        this.addedDirs.put(vPath, vPath);
        if (!this.skipWriting) {
            ZipEntry ze = new ZipEntry(vPath);
            if (dir == null || !dir.exists()) {
                ze.setTime(System.currentTimeMillis() + ((long) (this.roundUp ? 1999 : 0)));
            } else {
                ze.setTime(dir.lastModified() + ((long) (this.roundUp ? 1999 : 0)));
            }
            ze.setSize(0L);
            ze.setMethod(0);
            ze.setCrc(EMPTY_CRC);
            ze.setUnixMode(mode);
            if (extra != null) {
                ze.setExtraFields(extra);
            }
            zOut.putNextEntry(ze);
        }
    }

    protected void zipFile(InputStream in, ZipOutputStream zOut, String vPath, long lastModified, File fromArchive, int mode) throws IOException {
        InputStream in2;
        InputStream in3 = in;
        if (this.entries.contains(vPath)) {
            if (this.duplicate.equals("preserve")) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(vPath);
                stringBuffer.append(" already added, skipping");
                log(stringBuffer.toString(), 2);
                return;
            }
            if (this.duplicate.equals("fail")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Duplicate file ");
                stringBuffer2.append(vPath);
                stringBuffer2.append(" was found and the duplicate ");
                stringBuffer2.append("attribute is 'fail'.");
                throw new BuildException(stringBuffer2.toString());
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("duplicate file ");
            stringBuffer3.append(vPath);
            stringBuffer3.append(" found, adding.");
            log(stringBuffer3.toString(), 3);
        } else {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("adding entry ");
            stringBuffer4.append(vPath);
            log(stringBuffer4.toString(), 3);
        }
        this.entries.put(vPath, vPath);
        if (!this.skipWriting) {
            ZipEntry ze = new ZipEntry(vPath);
            ze.setTime(lastModified);
            ze.setMethod(this.doCompress ? 8 : 0);
            if (!zOut.isSeekable() && !this.doCompress) {
                long size = 0;
                CRC32 cal = new CRC32();
                if (!in.markSupported()) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[VehicleVendorProperty.Health.OIL_HINT];
                    int count = 0;
                    do {
                        size += (long) count;
                        cal.update(buffer, 0, count);
                        bos.write(buffer, 0, count);
                        count = in3.read(buffer, 0, buffer.length);
                    } while (count != -1);
                    in3 = new ByteArrayInputStream(bos.toByteArray());
                } else {
                    in3.mark(Execute.INVALID);
                    byte[] buffer2 = new byte[VehicleVendorProperty.Health.OIL_HINT];
                    int count2 = 0;
                    do {
                        size += (long) count2;
                        cal.update(buffer2, 0, count2);
                        count2 = in3.read(buffer2, 0, buffer2.length);
                    } while (count2 != -1);
                    in.reset();
                }
                ze.setSize(size);
                ze.setCrc(cal.getValue());
                in2 = in3;
            } else {
                in2 = in3;
            }
            ze.setUnixMode(mode);
            zOut.putNextEntry(ze);
            byte[] buffer3 = new byte[VehicleVendorProperty.Health.OIL_HINT];
            int count3 = 0;
            do {
                if (count3 != 0) {
                    zOut.write(buffer3, 0, count3);
                }
                count3 = in2.read(buffer3, 0, buffer3.length);
            } while (count3 != -1);
        }
        this.addedFiles.addElement(vPath);
    }

    protected void zipFile(File file, ZipOutputStream zOut, String vPath, int mode) throws IOException {
        if (file.equals(this.zipFile)) {
            throw new BuildException("A zip file cannot include itself", getLocation());
        }
        FileInputStream fIn = new FileInputStream(file);
        try {
            zipFile(fIn, zOut, vPath, file.lastModified() + ((long) (this.roundUp ? 1999 : 0)), null, mode);
        } finally {
            fIn.close();
        }
    }

    protected final void addParentDirs(File baseDir, String entry, ZipOutputStream zOut, String prefix, int dirMode) throws IOException {
        File file;
        if (!this.doFilesonly) {
            Stack directories = new Stack();
            int slashPos = entry.length();
            while (true) {
                int iLastIndexOf = entry.lastIndexOf(47, slashPos - 1);
                slashPos = iLastIndexOf;
                if (iLastIndexOf == -1) {
                    break;
                }
                String dir = entry.substring(0, slashPos + 1);
                Hashtable hashtable = this.addedDirs;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(prefix);
                stringBuffer.append(dir);
                if (hashtable.get(stringBuffer.toString()) != null) {
                    break;
                } else {
                    directories.push(dir);
                }
            }
            while (!directories.isEmpty()) {
                String dir2 = (String) directories.pop();
                if (baseDir != null) {
                    file = new File(baseDir, dir2);
                } else {
                    file = new File(dir2);
                }
                File f = file;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(prefix);
                stringBuffer2.append(dir2);
                zipDir(f, zOut, stringBuffer2.toString(), dirMode);
            }
        }
    }

    protected void cleanUp() {
        this.addedDirs.clear();
        this.addedFiles.removeAllElements();
        this.entries.clear();
        this.addingNewFiles = false;
        this.doUpdate = this.savedDoUpdate;
        Enumeration e = this.filesetsFromGroupfilesets.elements();
        while (e.hasMoreElements()) {
            ZipFileSet zf = (ZipFileSet) e.nextElement();
            this.filesets.removeElement(zf);
        }
        this.filesetsFromGroupfilesets.removeAllElements();
    }

    public void reset() {
        this.filesets.removeAllElements();
        this.zipFile = null;
        this.baseDir = null;
        this.groupfilesets.removeAllElements();
        this.duplicate = "add";
        this.archiveType = "zip";
        this.doCompress = true;
        this.emptyBehavior = "skip";
        this.doUpdate = false;
        this.doFilesonly = false;
        this.encoding = null;
    }

    protected static final boolean isEmpty(Resource[][] r) {
        for (Resource[] resourceArr : r) {
            if (resourceArr.length > 0) {
                return false;
            }
        }
        return true;
    }

    protected Resource[] selectFileResources(Resource[] orig) {
        if (orig.length == 0) {
            return orig;
        }
        Vector v = new Vector(orig.length);
        for (int i = 0; i < orig.length; i++) {
            if (!orig[i].isDirectory()) {
                v.addElement(orig[i]);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Ignoring directory ");
                stringBuffer.append(orig[i].getName());
                stringBuffer.append(" as only files will be added.");
                log(stringBuffer.toString(), 3);
            }
        }
        if (v.size() != orig.length) {
            Resource[] r = new Resource[v.size()];
            v.copyInto(r);
            return r;
        }
        return orig;
    }

    public static class Duplicate extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"add", "preserve", "fail"};
        }
    }

    public static class ArchiveState {
        private boolean outOfDate;
        private Resource[][] resourcesToAdd;

        ArchiveState(boolean state, Resource[][] r) {
            this.outOfDate = state;
            this.resourcesToAdd = r;
        }

        public boolean isOutOfDate() {
            return this.outOfDate;
        }

        public Resource[][] getResourcesToAdd() {
            return this.resourcesToAdd;
        }

        public boolean isWithoutAnyResources() {
            if (this.resourcesToAdd == null) {
                return true;
            }
            int counter = 0;
            while (true) {
                Resource[][] resourceArr = this.resourcesToAdd;
                if (counter >= resourceArr.length) {
                    return true;
                }
                if (resourceArr[counter] == null || resourceArr[counter].length <= 0) {
                    counter++;
                } else {
                    return false;
                }
            }
        }
    }
}
