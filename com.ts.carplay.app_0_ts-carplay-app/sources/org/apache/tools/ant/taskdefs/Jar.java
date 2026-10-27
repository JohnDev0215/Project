package org.apache.tools.ant.taskdefs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.zip.JarMarker;
import org.apache.tools.zip.ZipExtraField;
import org.apache.tools.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Jar extends Zip {
    private static final String INDEX_NAME = "META-INF/INDEX.LIST";
    private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
    private Manifest configuredManifest;
    private Manifest filesetManifest;
    private FilesetManifestConfig filesetManifestConfig;
    private Path indexJars;
    private Manifest manifest;
    private String manifestEncoding;
    private File manifestFile;
    private Manifest originalManifest;
    private Vector rootEntries;
    private Manifest savedConfiguredManifest;
    private boolean mergeManifestsMain = true;
    private boolean index = false;
    private boolean createEmpty = false;
    private ZipExtraField[] JAR_MARKER = {JarMarker.getInstance()};

    public Jar() {
        this.archiveType = "jar";
        this.emptyBehavior = "create";
        setEncoding("UTF8");
        this.rootEntries = new Vector();
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    public void setWhenempty(Zip.WhenEmpty we) {
        log("JARs are never empty, they contain at least a manifest file", 1);
    }

    public void setJarfile(File jarFile) {
        setDestFile(jarFile);
    }

    public void setIndex(boolean flag) {
        this.index = flag;
    }

    public void setManifestEncoding(String manifestEncoding) {
        this.manifestEncoding = manifestEncoding;
    }

    public void addConfiguredManifest(Manifest newManifest) throws ManifestException {
        Manifest manifest = this.configuredManifest;
        if (manifest == null) {
            this.configuredManifest = newManifest;
        } else {
            manifest.merge(newManifest);
        }
        this.savedConfiguredManifest = this.configuredManifest;
    }

    public void setManifest(File manifestFile) {
        if (!manifestFile.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Manifest file: ");
            stringBuffer.append(manifestFile);
            stringBuffer.append(" does not exist.");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        this.manifestFile = manifestFile;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.tools.ant.taskdefs.Manifest getManifest(java.io.File r10) throws java.io.UnsupportedEncodingException {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r4.<init>(r10)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r1 = r4
            java.lang.String r4 = r9.manifestEncoding     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            if (r4 != 0) goto L15
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r2 = r4
            goto L1d
        L15:
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            java.lang.String r5 = r9.manifestEncoding     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r4.<init>(r1, r5)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r2 = r4
        L1d:
            org.apache.tools.ant.taskdefs.Manifest r4 = r9.getManifest(r2)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30 java.io.UnsupportedEncodingException -> L5c
            r0 = r4
            r4 = r3
            r2.close()     // Catch: java.io.IOException -> L28
            goto L2a
        L28:
            r5 = move-exception
        L2a:
            return r0
        L2d:
            r4 = move-exception
            r5 = r3
            goto L7d
        L30:
            r4 = move-exception
            r5 = r4
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L5a
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L5a
            r6.<init>()     // Catch: java.lang.Throwable -> L5a
            java.lang.String r7 = "Unable to read manifest file: "
            r6.append(r7)     // Catch: java.lang.Throwable -> L5a
            r6.append(r10)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r7 = " ("
            r6.append(r7)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r7 = r5.getMessage()     // Catch: java.lang.Throwable -> L5a
            r6.append(r7)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r7 = ")"
            r6.append(r7)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L5a
            r4.<init>(r6, r5)     // Catch: java.lang.Throwable -> L5a
            throw r4     // Catch: java.lang.Throwable -> L5a
        L5a:
            r4 = move-exception
            goto L7d
        L5c:
            r4 = move-exception
            org.apache.tools.ant.BuildException r5 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L78
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L78
            r6.<init>()     // Catch: java.lang.Throwable -> L78
            java.lang.String r7 = "Unsupported encoding while reading manifest: "
            r6.append(r7)     // Catch: java.lang.Throwable -> L78
            java.lang.String r7 = r4.getMessage()     // Catch: java.lang.Throwable -> L78
            r6.append(r7)     // Catch: java.lang.Throwable -> L78
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L78
            r5.<init>(r6, r4)     // Catch: java.lang.Throwable -> L78
            throw r5     // Catch: java.lang.Throwable -> L78
        L78:
            r5 = move-exception
            r8 = r5
            r5 = r3
            r3 = r4
            r4 = r8
        L7d:
            if (r2 == 0) goto L84
            r2.close()     // Catch: java.io.IOException -> L83
            goto L84
        L83:
            r6 = move-exception
        L84:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Jar.getManifest(java.io.File):org.apache.tools.ant.taskdefs.Manifest");
    }

    private Manifest getManifestFromJar(File jarFile) throws IOException {
        ZipFile zf = null;
        try {
            zf = new ZipFile(jarFile);
            Enumeration<? extends ZipEntry> enumerationEntries = zf.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry ze = enumerationEntries.nextElement();
                if (ze.getName().equalsIgnoreCase(MANIFEST_NAME)) {
                    InputStreamReader isr = new InputStreamReader(zf.getInputStream(ze), "UTF-8");
                    Manifest manifest = getManifest(isr);
                    try {
                        zf.close();
                    } catch (IOException e) {
                    }
                    return manifest;
                }
            }
            try {
                zf.close();
            } catch (IOException e2) {
            }
            return null;
        } catch (Throwable th) {
            if (zf != null) {
                try {
                    zf.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }

    private Manifest getManifest(Reader r) {
        try {
            Manifest newManifest = new Manifest(r);
            return newManifest;
        } catch (IOException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to read manifest file (");
            stringBuffer.append(e.getMessage());
            stringBuffer.append(")");
            throw new BuildException(stringBuffer.toString(), e);
        } catch (ManifestException e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Manifest is invalid: ");
            stringBuffer2.append(e2.getMessage());
            log(stringBuffer2.toString(), 0);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Invalid Manifest: ");
            stringBuffer3.append(this.manifestFile);
            throw new BuildException(stringBuffer3.toString(), e2, getLocation());
        }
    }

    public void setFilesetmanifest(FilesetManifestConfig config) {
        this.filesetManifestConfig = config;
        this.mergeManifestsMain = "merge".equals(config.getValue());
        FilesetManifestConfig filesetManifestConfig = this.filesetManifestConfig;
        if (filesetManifestConfig != null && !filesetManifestConfig.getValue().equals("skip")) {
            this.doubleFilePass = true;
        }
    }

    public void addMetainf(ZipFileSet fs) {
        fs.setPrefix("META-INF/");
        super.addFileset(fs);
    }

    public void addConfiguredIndexJars(Path p) {
        if (this.indexJars == null) {
            this.indexJars = new Path(getProject());
        }
        this.indexJars.append(p);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
        if (!this.skipWriting) {
            Manifest jarManifest = createManifest();
            writeManifest(zOut, jarManifest);
        }
    }

    private Manifest createManifest() throws BuildException {
        try {
            Manifest finalManifest = Manifest.getDefaultManifest();
            if (this.manifest == null && this.manifestFile != null) {
                this.manifest = getManifest(this.manifestFile);
            }
            if (isInUpdateMode()) {
                finalManifest.merge(this.originalManifest);
            }
            finalManifest.merge(this.filesetManifest);
            finalManifest.merge(this.configuredManifest);
            finalManifest.merge(this.manifest, !this.mergeManifestsMain);
            return finalManifest;
        } catch (ManifestException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Manifest is invalid: ");
            stringBuffer.append(e.getMessage());
            log(stringBuffer.toString(), 0);
            throw new BuildException("Invalid Manifest", e, getLocation());
        }
    }

    private void writeManifest(ZipOutputStream zOut, Manifest manifest) throws IOException {
        Enumeration e = manifest.getWarnings();
        while (e.hasMoreElements()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Manifest warning: ");
            stringBuffer.append((String) e.nextElement());
            log(stringBuffer.toString(), 1);
        }
        zipDir(null, zOut, "META-INF/", 16877, this.JAR_MARKER);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
        PrintWriter writer = new PrintWriter(osw);
        manifest.write(writer);
        writer.flush();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        super.zipFile(bais, zOut, MANIFEST_NAME, System.currentTimeMillis(), null, 33188);
        super.initZipOutputStream(zOut);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void finalizeZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
        if (this.index) {
            createIndexList(zOut);
        }
    }

    private void createIndexList(ZipOutputStream zOut) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos, "UTF8"));
        writer.println("JarIndex-Version: 1.0");
        writer.println();
        writer.println(this.zipFile.getName());
        writeIndexLikeList(new ArrayList(this.addedDirs.keySet()), this.rootEntries, writer);
        writer.println();
        if (this.indexJars != null) {
            Manifest mf = createManifest();
            Manifest.Attribute classpath = mf.getMainSection().getAttribute(Manifest.ATTRIBUTE_CLASSPATH);
            String[] cpEntries = null;
            if (classpath != null) {
                StringTokenizer tok = new StringTokenizer(classpath.getValue(), " ");
                String[] cpEntries2 = new String[tok.countTokens()];
                int c = 0;
                while (tok.hasMoreTokens()) {
                    cpEntries2[c] = tok.nextToken();
                    c++;
                }
                cpEntries = cpEntries2;
            }
            String[] indexJarEntries = this.indexJars.list();
            for (int i = 0; i < indexJarEntries.length; i++) {
                String name = findJarName(indexJarEntries[i], cpEntries);
                if (name != null) {
                    ArrayList dirs = new ArrayList();
                    ArrayList files = new ArrayList();
                    grabFilesAndDirs(indexJarEntries[i], dirs, files);
                    if (dirs.size() + files.size() > 0) {
                        writer.println(name);
                        writeIndexLikeList(dirs, files, writer);
                        writer.println();
                    }
                }
            }
        }
        writer.flush();
        InputStream bais = new ByteArrayInputStream(baos.toByteArray());
        super.zipFile(bais, zOut, INDEX_NAME, System.currentTimeMillis(), null, 33188);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void zipFile(InputStream is, ZipOutputStream zOut, String vPath, long lastModified, File fromArchive, int mode) throws IOException {
        if (MANIFEST_NAME.equalsIgnoreCase(vPath)) {
            if (!this.doubleFilePass || (this.doubleFilePass && this.skipWriting)) {
                filesetManifest(fromArchive, is);
                return;
            }
            return;
        }
        if (INDEX_NAME.equalsIgnoreCase(vPath) && this.index) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Warning: selected ");
            stringBuffer.append(this.archiveType);
            stringBuffer.append(" files include a META-INF/INDEX.LIST which will");
            stringBuffer.append(" be replaced by a newly generated one.");
            log(stringBuffer.toString(), 1);
            return;
        }
        if (this.index && vPath.indexOf("/") == -1) {
            this.rootEntries.addElement(vPath);
        }
        super.zipFile(is, zOut, vPath, lastModified, fromArchive, mode);
    }

    private void filesetManifest(File file, InputStream is) throws IOException {
        Manifest newManifest;
        InputStreamReader isr;
        InputStreamReader isr2;
        File file2 = this.manifestFile;
        if (file2 != null && file2.equals(file)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found manifest ");
            stringBuffer.append(file);
            log(stringBuffer.toString(), 3);
            try {
                if (is != null) {
                    if (this.manifestEncoding == null) {
                        isr2 = new InputStreamReader(is);
                    } else {
                        isr2 = new InputStreamReader(is, this.manifestEncoding);
                    }
                    this.manifest = getManifest(isr2);
                    return;
                }
                this.manifest = getManifest(file);
                return;
            } catch (UnsupportedEncodingException e) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Unsupported encoding while reading manifest: ");
                stringBuffer2.append(e.getMessage());
                throw new BuildException(stringBuffer2.toString(), e);
            }
        }
        FilesetManifestConfig filesetManifestConfig = this.filesetManifestConfig;
        if (filesetManifestConfig != null && !filesetManifestConfig.getValue().equals("skip")) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Found manifest to merge in file ");
            stringBuffer3.append(file);
            log(stringBuffer3.toString(), 3);
            try {
                if (is != null) {
                    if (this.manifestEncoding == null) {
                        isr = new InputStreamReader(is);
                    } else {
                        isr = new InputStreamReader(is, this.manifestEncoding);
                    }
                    newManifest = getManifest(isr);
                } else {
                    newManifest = getManifest(file);
                }
                if (this.filesetManifest == null) {
                    this.filesetManifest = newManifest;
                } else {
                    this.filesetManifest.merge(newManifest);
                }
            } catch (UnsupportedEncodingException e2) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Unsupported encoding while reading manifest: ");
                stringBuffer4.append(e2.getMessage());
                throw new BuildException(stringBuffer4.toString(), e2);
            } catch (ManifestException e3) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("Manifest in file ");
                stringBuffer5.append(file);
                stringBuffer5.append(" is invalid: ");
                stringBuffer5.append(e3.getMessage());
                log(stringBuffer5.toString(), 0);
                throw new BuildException("Invalid Manifest", e3, getLocation());
            }
        }
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected Zip.ArchiveState getResourcesToAdd(FileSet[] filesets, File zipFile, boolean needsUpdate) throws BuildException {
        if (zipFile.exists()) {
            try {
                Manifest manifestFromJar = getManifestFromJar(zipFile);
                this.originalManifest = manifestFromJar;
                if (manifestFromJar == null) {
                    log("Updating jar since the current jar has no manifest", 3);
                    needsUpdate = true;
                } else {
                    Manifest mf = createManifest();
                    if (!mf.equals(this.originalManifest)) {
                        log("Updating jar since jar manifest has changed", 3);
                        needsUpdate = true;
                    }
                }
            } catch (Throwable t) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("error while reading original manifest: ");
                stringBuffer.append(t.getMessage());
                log(stringBuffer.toString(), 1);
                needsUpdate = true;
            }
        } else {
            needsUpdate = true;
        }
        this.createEmpty = needsUpdate;
        return super.getResourcesToAdd(filesets, zipFile, needsUpdate);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.taskdefs.Zip
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean createEmptyZip(java.io.File r8) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r7.createEmpty
            r1 = 1
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = 0
            r2 = 0
            r3 = 0
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r4.<init>()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            java.lang.String r5 = "Building MANIFEST-only jar: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            java.io.File r5 = r7.getDestFile()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            java.lang.String r5 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r4.append(r5)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r7.log(r4)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            org.apache.tools.zip.ZipOutputStream r4 = new org.apache.tools.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            java.io.File r6 = r7.getDestFile()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r0 = r4
            java.lang.String r4 = r7.getEncoding()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r0.setEncoding(r4)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            boolean r4 = r7.isCompress()     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            if (r4 == 0) goto L47
            r4 = 8
            r0.setMethod(r4)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            goto L4a
        L47:
            r0.setMethod(r2)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
        L4a:
            r7.initZipOutputStream(r0)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r7.finalizeZipOutputStream(r0)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c
            r0.close()     // Catch: java.io.IOException -> L55
            goto L57
        L55:
            r3 = move-exception
        L57:
            r7.createEmpty = r2
            return r1
        L5a:
            r1 = move-exception
            goto L83
        L5c:
            r1 = move-exception
            r3 = r1
            org.apache.tools.ant.BuildException r1 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L82
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L82
            r4.<init>()     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = "Could not create almost empty JAR archive ("
            r4.append(r5)     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = r3.getMessage()     // Catch: java.lang.Throwable -> L82
            r4.append(r5)     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = ")"
            r4.append(r5)     // Catch: java.lang.Throwable -> L82
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L82
            org.apache.tools.ant.Location r5 = r7.getLocation()     // Catch: java.lang.Throwable -> L82
            r1.<init>(r4, r3, r5)     // Catch: java.lang.Throwable -> L82
            throw r1     // Catch: java.lang.Throwable -> L82
        L82:
            r1 = move-exception
        L83:
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.io.IOException -> L89
            goto L8b
        L89:
            r4 = move-exception
            goto L8c
        L8b:
        L8c:
            r7.createEmpty = r2
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Jar.createEmptyZip(java.io.File):boolean");
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void cleanUp() {
        super.cleanUp();
        if (!this.doubleFilePass || (this.doubleFilePass && !this.skipWriting)) {
            this.manifest = null;
            this.configuredManifest = this.savedConfiguredManifest;
            this.filesetManifest = null;
            this.originalManifest = null;
        }
        this.rootEntries.removeAllElements();
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    public void reset() {
        super.reset();
        this.configuredManifest = null;
        this.filesetManifestConfig = null;
        this.mergeManifestsMain = false;
        this.manifestFile = null;
        this.index = false;
    }

    public static class FilesetManifestConfig extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"skip", "merge", "mergewithoutmain"};
        }
    }

    protected final void writeIndexLikeList(List dirs, List files, PrintWriter writer) throws IOException {
        Collections.sort(dirs);
        Collections.sort(files);
        Iterator iter = dirs.iterator();
        while (iter.hasNext()) {
            String dir = ((String) iter.next()).replace('\\', '/');
            if (dir.startsWith("./")) {
                dir = dir.substring(2);
            }
            while (dir.startsWith("/")) {
                dir = dir.substring(1);
            }
            int pos = dir.lastIndexOf(47);
            if (pos != -1) {
                dir = dir.substring(0, pos);
            }
            if (!dir.startsWith("META-INF")) {
                writer.println(dir);
            }
        }
        Iterator iter2 = files.iterator();
        while (iter2.hasNext()) {
            writer.println(iter2.next());
        }
    }

    protected static final String findJarName(String fileName, String[] classpath) {
        if (classpath == null) {
            return new File(fileName).getName();
        }
        String fileName2 = fileName.replace(File.separatorChar, '/');
        TreeMap matches = new TreeMap(new Comparator() { // from class: org.apache.tools.ant.taskdefs.Jar.1
            @Override // java.util.Comparator
            public int compare(Object o1, Object o2) {
                if ((o1 instanceof String) && (o2 instanceof String)) {
                    return ((String) o2).length() - ((String) o1).length();
                }
                return 0;
            }
        });
        for (int i = 0; i < classpath.length; i++) {
            if (fileName2.endsWith(classpath[i])) {
                matches.put(classpath[i], classpath[i]);
            } else {
                int slash = classpath[i].indexOf("/");
                String candidate = classpath[i];
                while (true) {
                    if (slash <= -1) {
                        break;
                    }
                    candidate = candidate.substring(slash + 1);
                    if (fileName2.endsWith(candidate)) {
                        matches.put(candidate, classpath[i]);
                        break;
                    }
                    slash = candidate.indexOf("/");
                }
            }
        }
        if (matches.size() == 0) {
            return null;
        }
        return (String) matches.get(matches.firstKey());
    }

    protected static final void grabFilesAndDirs(String file, List dirs, List files) throws IOException {
        org.apache.tools.zip.ZipFile zf = null;
        try {
            zf = new org.apache.tools.zip.ZipFile(file, "utf-8");
            Enumeration entries = zf.getEntries();
            HashSet dirSet = new HashSet();
            while (entries.hasMoreElements()) {
                org.apache.tools.zip.ZipEntry ze = (org.apache.tools.zip.ZipEntry) entries.nextElement();
                String name = ze.getName();
                if (!name.startsWith("META-INF/")) {
                    if (ze.isDirectory()) {
                        dirSet.add(name);
                    } else if (name.indexOf("/") == -1) {
                        files.add(name);
                    } else {
                        dirSet.add(name.substring(0, name.lastIndexOf("/") + 1));
                    }
                }
            }
            dirs.addAll(dirSet);
            zf.close();
        } catch (Throwable th) {
            if (zf != null) {
                zf.close();
            }
            throw th;
        }
    }
}
