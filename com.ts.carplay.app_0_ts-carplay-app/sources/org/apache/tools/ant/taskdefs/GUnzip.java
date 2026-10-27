package org.apache.tools.ant.taskdefs;

/* JADX INFO: loaded from: classes.dex */
public class GUnzip extends Unpack {
    private static final String DEFAULT_EXTENSION = ".gz";

    @Override // org.apache.tools.ant.taskdefs.Unpack
    protected String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[SYNTHETIC] */
    @Override // org.apache.tools.ant.taskdefs.Unpack
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void extract() throws java.lang.Throwable {
        /*
            r7 = this;
            java.io.File r0 = r7.source
            long r0 = r0.lastModified()
            java.io.File r2 = r7.dest
            long r2 = r2.lastModified()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto Lb3
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Expanding "
            r0.append(r1)
            java.io.File r1 = r7.source
            java.lang.String r1 = r1.getAbsolutePath()
            r0.append(r1)
            java.lang.String r1 = " to "
            r0.append(r1)
            java.io.File r1 = r7.dest
            java.lang.String r1 = r1.getAbsolutePath()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.log(r0)
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            java.io.File r5 = r7.dest     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r0 = r4
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            java.io.File r5 = r7.source     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r2 = r4
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r1 = r4
            r4 = 8192(0x2000, float:1.148E-41)
            byte[] r3 = new byte[r4]     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r4 = 0
        L57:
            r5 = 0
            r0.write(r3, r5, r4)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            int r6 = r3.length     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            int r5 = r1.read(r3, r5, r6)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7c
            r4 = r5
            r5 = -1
            if (r4 != r5) goto L57
        L65:
            r2.close()     // Catch: java.io.IOException -> L69
            goto L6b
        L69:
            r3 = move-exception
        L6b:
            r0.close()     // Catch: java.io.IOException -> L70
            goto L72
        L70:
            r3 = move-exception
        L72:
            r1.close()     // Catch: java.io.IOException -> L77
            goto L79
        L77:
            r3 = move-exception
        L79:
            goto Lb3
        L7a:
            r4 = move-exception
            goto L9d
        L7c:
            r3 = move-exception
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L9c
            r4.<init>()     // Catch: java.lang.Throwable -> L9c
            java.lang.String r5 = "Problem expanding gzip "
            r4.append(r5)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r5 = r3.getMessage()     // Catch: java.lang.Throwable -> L9c
            r4.append(r5)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L9c
            org.apache.tools.ant.BuildException r5 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L9c
            org.apache.tools.ant.Location r6 = r7.getLocation()     // Catch: java.lang.Throwable -> L9c
            r5.<init>(r4, r3, r6)     // Catch: java.lang.Throwable -> L9c
            throw r5     // Catch: java.lang.Throwable -> L9c
        L9c:
            r4 = move-exception
        L9d:
            if (r2 == 0) goto La4
            r2.close()     // Catch: java.io.IOException -> La3
            goto La4
        La3:
            r5 = move-exception
        La4:
            if (r0 == 0) goto Lab
            r0.close()     // Catch: java.io.IOException -> Laa
            goto Lab
        Laa:
            r5 = move-exception
        Lab:
            if (r1 == 0) goto Lb2
            r1.close()     // Catch: java.io.IOException -> Lb1
            goto Lb2
        Lb1:
            r5 = move-exception
        Lb2:
            throw r4
        Lb3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.GUnzip.extract():void");
    }
}
