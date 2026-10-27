package org.apache.tools.ant.taskdefs;

/* JADX INFO: loaded from: classes.dex */
public class GZip extends Pack {
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            java.io.File r4 = r5.zipFile     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r0 = r2
            java.io.File r2 = r5.source     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r5.zipFile(r2, r0)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L21
            r0.close()     // Catch: java.io.IOException -> L1a
            goto L1c
        L1a:
            r2 = move-exception
        L1c:
            return
        L1f:
            r2 = move-exception
            goto L42
        L21:
            r1 = move-exception
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L41
            java.lang.String r3 = "Problem creating gzip "
            r2.append(r3)     // Catch: java.lang.Throwable -> L41
            java.lang.String r3 = r1.getMessage()     // Catch: java.lang.Throwable -> L41
            r2.append(r3)     // Catch: java.lang.Throwable -> L41
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L41
            org.apache.tools.ant.BuildException r3 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L41
            org.apache.tools.ant.Location r4 = r5.getLocation()     // Catch: java.lang.Throwable -> L41
            r3.<init>(r2, r1, r4)     // Catch: java.lang.Throwable -> L41
            throw r3     // Catch: java.lang.Throwable -> L41
        L41:
            r2 = move-exception
        L42:
            if (r0 == 0) goto L49
            r0.close()     // Catch: java.io.IOException -> L48
            goto L49
        L48:
            r3 = move-exception
        L49:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.GZip.pack():void");
    }
}
