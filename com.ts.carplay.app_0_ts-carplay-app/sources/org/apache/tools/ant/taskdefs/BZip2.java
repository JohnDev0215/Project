package org.apache.tools.ant.taskdefs;

/* JADX INFO: loaded from: classes.dex */
public class BZip2 extends Pack {
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.taskdefs.Pack
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void pack() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            java.io.File r4 = r5.zipFile     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r1 = r2
            r2 = 66
            r1.write(r2)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r2 = 90
            r1.write(r2)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            org.apache.tools.bzip2.CBZip2OutputStream r2 = new org.apache.tools.bzip2.CBZip2OutputStream     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r0 = r2
            java.io.File r2 = r5.source     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r5.zipFile(r2, r0)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L31
            r0.close()     // Catch: java.io.IOException -> L2a
            goto L2c
        L2a:
            r2 = move-exception
        L2c:
            return
        L2f:
            r2 = move-exception
            goto L52
        L31:
            r1 = move-exception
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L51
            r2.<init>()     // Catch: java.lang.Throwable -> L51
            java.lang.String r3 = "Problem creating bzip2 "
            r2.append(r3)     // Catch: java.lang.Throwable -> L51
            java.lang.String r3 = r1.getMessage()     // Catch: java.lang.Throwable -> L51
            r2.append(r3)     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L51
            org.apache.tools.ant.BuildException r3 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L51
            org.apache.tools.ant.Location r4 = r5.getLocation()     // Catch: java.lang.Throwable -> L51
            r3.<init>(r2, r1, r4)     // Catch: java.lang.Throwable -> L51
            throw r3     // Catch: java.lang.Throwable -> L51
        L51:
            r2 = move-exception
        L52:
            if (r0 == 0) goto L59
            r0.close()     // Catch: java.io.IOException -> L58
            goto L59
        L58:
            r3 = move-exception
        L59:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.BZip2.pack():void");
    }
}
