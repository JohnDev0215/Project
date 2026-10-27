package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Expand extends Task {
    private static final String NATIVE_ENCODING = "native-encoding";
    private File dest;
    private File source;
    private boolean overwrite = true;
    private Vector patternsets = new Vector();
    private Vector filesets = new Vector();
    private String encoding = "UTF8";

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        if ("expand".equals(getTaskType())) {
            log("!! expand is deprecated. Use unzip instead. !!");
        }
        if (this.source == null && this.filesets.size() == 0) {
            throw new BuildException("src attribute and/or filesets must be specified");
        }
        File file = this.dest;
        if (file == null) {
            throw new BuildException("Dest attribute must be specified");
        }
        if (file.exists() && !this.dest.isDirectory()) {
            throw new BuildException("Dest must be a directory.", getLocation());
        }
        FileUtils fileUtils = FileUtils.newFileUtils();
        File file2 = this.source;
        if (file2 != null) {
            if (file2.isDirectory()) {
                throw new BuildException("Src must not be a directory. Use nested filesets instead.", getLocation());
            }
            expandFile(fileUtils, this.source, this.dest);
        }
        if (this.filesets.size() > 0) {
            for (int j = 0; j < this.filesets.size(); j++) {
                FileSet fs = (FileSet) this.filesets.elementAt(j);
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                File fromDir = fs.getDir(getProject());
                String[] files = ds.getIncludedFiles();
                for (String str : files) {
                    File file3 = new File(fromDir, str);
                    expandFile(fileUtils, file3, this.dest);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void expandFile(org.apache.tools.ant.util.FileUtils r12, java.io.File r13, java.io.File r14) throws java.lang.Throwable {
        /*
            r11 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Expanding: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r1 = " into "
            r0.append(r1)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            r1 = 2
            r11.log(r0, r1)
            r0 = 0
            r1 = 0
            org.apache.tools.zip.ZipFile r2 = new org.apache.tools.zip.ZipFile     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.String r3 = r11.encoding     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r2.<init>(r13, r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r0 = r2
            java.util.Enumeration r1 = r0.getEntries()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
        L2c:
            boolean r2 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            if (r2 != 0) goto L43
            java.lang.String r2 = "expand complete"
            r3 = 3
            r11.log(r2, r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r0.close()     // Catch: java.io.IOException -> L3e
            goto L40
        L3e:
            r2 = move-exception
        L40:
            return
        L43:
            java.lang.Object r2 = r1.nextElement()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            org.apache.tools.zip.ZipEntry r2 = (org.apache.tools.zip.ZipEntry) r2     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.io.InputStream r7 = r0.getInputStream(r2)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.String r8 = r2.getName()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.util.Date r9 = new java.util.Date     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            long r3 = r2.getTime()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r9.<init>(r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            boolean r10 = r2.isDirectory()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r3.extractFile(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            goto L2c
        L66:
            r2 = move-exception
            goto L85
        L68:
            r1 = move-exception
            org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L84
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L84
            r3.<init>()     // Catch: java.lang.Throwable -> L84
            java.lang.String r4 = "Error while expanding "
            r3.append(r4)     // Catch: java.lang.Throwable -> L84
            java.lang.String r4 = r13.getPath()     // Catch: java.lang.Throwable -> L84
            r3.append(r4)     // Catch: java.lang.Throwable -> L84
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L84
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L84
            throw r2     // Catch: java.lang.Throwable -> L84
        L84:
            r2 = move-exception
        L85:
            if (r0 == 0) goto L8c
            r0.close()     // Catch: java.io.IOException -> L8b
            goto L8c
        L8b:
            r3 = move-exception
        L8c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Expand.expandFile(org.apache.tools.ant.util.FileUtils, java.io.File, java.io.File):void");
    }

    protected void extractFile(FileUtils fileUtils, File srcF, File dir, InputStream compressedInputStream, String entryName, Date entryDate, boolean isDirectory) throws Throwable {
        Vector vector = this.patternsets;
        if (vector != null && vector.size() > 0) {
            String name = entryName.replace('/', File.separatorChar).replace('\\', File.separatorChar);
            boolean included = false;
            for (int v = 0; v < this.patternsets.size(); v++) {
                PatternSet p = (PatternSet) this.patternsets.elementAt(v);
                String[] incls = p.getIncludePatterns(getProject());
                if (incls == null || incls.length == 0) {
                    incls = new String[]{"**"};
                }
                for (String str : incls) {
                    String pattern = str.replace('/', File.separatorChar).replace('\\', File.separatorChar);
                    if (pattern.endsWith(File.separator)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(pattern);
                        stringBuffer.append("**");
                        pattern = stringBuffer.toString();
                    }
                    included = SelectorUtils.matchPath(pattern, name);
                    if (included) {
                        break;
                    }
                }
                if (!included) {
                    break;
                }
                String[] excls = p.getExcludePatterns(getProject());
                if (excls != null) {
                    for (String str2 : excls) {
                        String pattern2 = str2.replace('/', File.separatorChar).replace('\\', File.separatorChar);
                        if (pattern2.endsWith(File.separator)) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append(pattern2);
                            stringBuffer2.append("**");
                            pattern2 = stringBuffer2.toString();
                        }
                        included = !SelectorUtils.matchPath(pattern2, name);
                        if (!included) {
                            break;
                        }
                    }
                }
            }
            if (!included) {
                return;
            }
        }
        File f = fileUtils.resolveFile(dir, entryName);
        try {
            if (!this.overwrite && f.exists() && f.lastModified() >= entryDate.getTime()) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Skipping ");
                stringBuffer3.append(f);
                stringBuffer3.append(" as it is up-to-date");
                log(stringBuffer3.toString(), 4);
                return;
            }
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("expanding ");
            stringBuffer4.append(entryName);
            stringBuffer4.append(" to ");
            stringBuffer4.append(f);
            log(stringBuffer4.toString(), 3);
            File dirF = fileUtils.getParentFile(f);
            if (dirF != null) {
                dirF.mkdirs();
            }
            try {
                if (isDirectory) {
                    f.mkdirs();
                } else {
                    byte[] buffer = new byte[1024];
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        while (true) {
                            try {
                                int length = compressedInputStream.read(buffer);
                                if (length < 0) {
                                    break;
                                } else {
                                    fos.write(buffer, 0, length);
                                }
                            } catch (Throwable th) {
                                th = th;
                                Throwable th2 = th;
                                if (fos == null) {
                                    throw th2;
                                }
                                try {
                                    fos.close();
                                    throw th2;
                                } catch (IOException e) {
                                    throw th2;
                                }
                            }
                        }
                        fos.close();
                        FileOutputStream fos2 = null;
                        if (0 != 0) {
                            try {
                                fos2.close();
                            } catch (IOException e2) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                fileUtils.setFileLastModified(f, entryDate.getTime());
            } catch (FileNotFoundException e3) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("Unable to expand to file ");
                stringBuffer5.append(f.getPath());
                log(stringBuffer5.toString(), 1);
            }
        } catch (FileNotFoundException e4) {
        }
    }

    public void setDest(File d) {
        this.dest = d;
    }

    public void setSrc(File s) {
        this.source = s;
    }

    public void setOverwrite(boolean b) {
        this.overwrite = b;
    }

    public void addPatternset(PatternSet set) {
        this.patternsets.addElement(set);
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    public void setEncoding(String encoding) {
        if (NATIVE_ENCODING.equals(encoding)) {
            encoding = null;
        }
        this.encoding = encoding;
    }
}
