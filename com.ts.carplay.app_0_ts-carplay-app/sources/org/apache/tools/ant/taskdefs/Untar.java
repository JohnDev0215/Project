package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.bzip2.CBZip2InputStream;

/* JADX INFO: loaded from: classes.dex */
public class Untar extends Expand {
    private UntarCompressionMethod compression = new UntarCompressionMethod();

    public void setCompression(UntarCompressionMethod method) {
        this.compression = method;
    }

    @Override // org.apache.tools.ant.taskdefs.Expand
    public void setEncoding(String encoding) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The ");
        stringBuffer.append(getTaskName());
        stringBuffer.append(" task doesn't support the encoding");
        stringBuffer.append(" attribute");
        throw new BuildException(stringBuffer.toString(), getLocation());
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.taskdefs.Expand
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void expandFile(org.apache.tools.ant.util.FileUtils r12, java.io.File r13, java.io.File r14) throws java.lang.Throwable {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r3.<init>()     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.lang.String r4 = "Expanding: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r3.append(r13)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.lang.String r4 = " into "
            r3.append(r4)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r3.append(r14)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r4 = 2
            r11.log(r3, r4)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r3.<init>(r13)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r0 = r3
            org.apache.tools.tar.TarInputStream r7 = new org.apache.tools.tar.TarInputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            org.apache.tools.ant.taskdefs.Untar$UntarCompressionMethod r3 = r11.compression     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.io.InputStream r3 = org.apache.tools.ant.taskdefs.Untar.UntarCompressionMethod.access$000(r3, r13, r4)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r1 = 0
        L38:
            org.apache.tools.tar.TarEntry r2 = r7.getNextEntry()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            r1 = r2
            if (r2 != 0) goto L4e
            java.lang.String r2 = "expand complete"
            r3 = 3
            r11.log(r2, r3)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            r7.close()     // Catch: java.io.IOException -> L4a
            goto L4c
        L4a:
            r1 = move-exception
        L4c:
            return
        L4e:
            java.lang.String r8 = r1.getName()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            java.util.Date r9 = r1.getModTime()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            boolean r10 = r1.isDirectory()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r3.extractFile(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            goto L38
        L62:
            r2 = move-exception
            r3 = r2
            r2 = r1
            r1 = r7
            goto L91
        L67:
            r1 = move-exception
            goto L6e
        L69:
            r3 = move-exception
            goto L91
        L6b:
            r2 = move-exception
            r7 = r1
            r1 = r2
        L6e:
            org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L8d
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L8d
            r3.<init>()     // Catch: java.lang.Throwable -> L8d
            java.lang.String r4 = "Error while expanding "
            r3.append(r4)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r4 = r13.getPath()     // Catch: java.lang.Throwable -> L8d
            r3.append(r4)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L8d
            org.apache.tools.ant.Location r4 = r11.getLocation()     // Catch: java.lang.Throwable -> L8d
            r2.<init>(r3, r1, r4)     // Catch: java.lang.Throwable -> L8d
            throw r2     // Catch: java.lang.Throwable -> L8d
        L8d:
            r2 = move-exception
            r3 = r2
            r2 = r1
            r1 = r7
        L91:
            if (r1 != 0) goto L9b
            if (r0 == 0) goto La2
            r0.close()     // Catch: java.io.IOException -> L99
            goto La2
        L99:
            r4 = move-exception
            goto La2
        L9b:
            r1.close()     // Catch: java.io.IOException -> L9f
            goto La1
        L9f:
            r4 = move-exception
        La1:
        La2:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Untar.expandFile(org.apache.tools.ant.util.FileUtils, java.io.File, java.io.File):void");
    }

    public static final class UntarCompressionMethod extends EnumeratedAttribute {
        private static final String BZIP2 = "bzip2";
        private static final String GZIP = "gzip";
        private static final String NONE = "none";

        public UntarCompressionMethod() {
            setValue(NONE);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{NONE, GZIP, BZIP2};
        }

        /* JADX INFO: Access modifiers changed from: private */
        public InputStream decompress(File file, InputStream istream) throws IOException, BuildException {
            String value = getValue();
            if (GZIP.equals(value)) {
                return new GZIPInputStream(istream);
            }
            if (BZIP2.equals(value)) {
                char[] magic = {'B', 'Z'};
                for (char c : magic) {
                    if (istream.read() != c) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Invalid bz2 file.");
                        stringBuffer.append(file.toString());
                        throw new BuildException(stringBuffer.toString());
                    }
                }
                return new CBZip2InputStream(istream);
            }
            return istream;
        }
    }
}
