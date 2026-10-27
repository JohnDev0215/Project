package com.android.multidex;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
class Path {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String definition;
    List<ClassPathElement> elements = new ArrayList();
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream(40960);
    private final byte[] readBuffer = new byte[20480];

    static ClassPathElement getClassPathElement(File file) throws IOException {
        if (file.isDirectory()) {
            return new FolderPathElement(file);
        }
        if (file.isFile()) {
            return new ArchivePathElement(new ZipFile(file));
        }
        if (file.exists()) {
            throw new IOException("\"" + file.getPath() + "\" is not a directory neither a zip file");
        }
        throw new FileNotFoundException("File \"" + file.getPath() + "\" not found");
    }

    Path(String str) throws IOException {
        this.definition = str;
        for (String str2 : str.split(Pattern.quote(File.pathSeparator))) {
            try {
                addElement(getClassPathElement(new File(str2)));
            } catch (IOException e) {
                throw new IOException("Wrong classpath: " + e.getMessage(), e);
            }
        }
    }

    private static byte[] readStream(InputStream inputStream, ByteArrayOutputStream byteArrayOutputStream, byte[] bArr) throws IOException {
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i >= 0) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    inputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            } catch (Throwable th) {
                inputStream.close();
                throw th;
            }
        }
    }

    public String toString() {
        return this.definition;
    }

    Iterable<ClassPathElement> getElements() {
        return this.elements;
    }

    private void addElement(ClassPathElement classPathElement) {
        this.elements.add(classPathElement);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        if (r1 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0062, code lost:
    
        throw new java.io.FileNotFoundException("File \"" + r7 + "\" not found");
     */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x003e: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:17:0x003e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized com.android.dx.cf.direct.DirectClassFile getClass(java.lang.String r7) throws java.io.FileNotFoundException {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.List<com.android.multidex.ClassPathElement> r0 = r6.elements     // Catch: java.lang.Throwable -> L63
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L63
            r1 = 0
        L8:
            boolean r2 = r0.hasNext()     // Catch: java.lang.Throwable -> L63
            if (r2 == 0) goto L40
            java.lang.Object r2 = r0.next()     // Catch: java.lang.Throwable -> L63
            com.android.multidex.ClassPathElement r2 = (com.android.multidex.ClassPathElement) r2     // Catch: java.lang.Throwable -> L63
            java.io.InputStream r2 = r2.open(r7)     // Catch: java.io.IOException -> L8 java.lang.Throwable -> L63
            java.io.ByteArrayOutputStream r3 = r6.baos     // Catch: java.lang.Throwable -> L37
            byte[] r4 = r6.readBuffer     // Catch: java.lang.Throwable -> L37
            byte[] r3 = readStream(r2, r3, r4)     // Catch: java.lang.Throwable -> L37
            java.io.ByteArrayOutputStream r4 = r6.baos     // Catch: java.lang.Throwable -> L37
            r4.reset()     // Catch: java.lang.Throwable -> L37
            com.android.dx.cf.direct.DirectClassFile r4 = new com.android.dx.cf.direct.DirectClassFile     // Catch: java.lang.Throwable -> L37
            r5 = 0
            r4.<init>(r3, r7, r5)     // Catch: java.lang.Throwable -> L37
            com.android.dx.cf.direct.StdAttributeFactory r1 = com.android.dx.cf.direct.StdAttributeFactory.THE_ONE     // Catch: java.lang.Throwable -> L35
            r4.setAttributeFactory(r1)     // Catch: java.lang.Throwable -> L35
            r2.close()     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L63
            r1 = r4
            goto L40
        L35:
            r1 = move-exception
            goto L3a
        L37:
            r3 = move-exception
            r4 = r1
            r1 = r3
        L3a:
            r2.close()     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L63
            throw r1     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L63
        L3e:
            r1 = r4
            goto L8
        L40:
            if (r1 == 0) goto L44
            monitor-exit(r6)
            return r1
        L44:
            java.io.FileNotFoundException r0 = new java.io.FileNotFoundException     // Catch: java.lang.Throwable -> L63
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r1.<init>()     // Catch: java.lang.Throwable -> L63
            java.lang.String r2 = "File \""
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L63
            java.lang.StringBuilder r7 = r1.append(r7)     // Catch: java.lang.Throwable -> L63
            java.lang.String r1 = "\" not found"
            java.lang.StringBuilder r7 = r7.append(r1)     // Catch: java.lang.Throwable -> L63
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L63
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L63
            throw r0     // Catch: java.lang.Throwable -> L63
        L63:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.multidex.Path.getClass(java.lang.String):com.android.dx.cf.direct.DirectClassFile");
    }
}
