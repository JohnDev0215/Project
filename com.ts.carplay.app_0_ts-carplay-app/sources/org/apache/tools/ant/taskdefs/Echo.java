package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;

/* JADX INFO: loaded from: classes.dex */
public class Echo extends Task {
    protected String message = "";
    protected File file = null;
    protected boolean append = false;
    protected int logLevel = 1;

    /* JADX WARN: Removed duplicated region for block: B:26:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            r5 = this;
            java.io.File r0 = r5.file
            if (r0 != 0) goto Lc
            java.lang.String r0 = r5.message
            int r1 = r5.logLevel
            r5.log(r0, r1)
            goto L32
        Lc:
            r0 = 0
            r1 = 0
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            java.io.File r3 = r5.file     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            boolean r4 = r5.append     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            r0 = r2
            java.lang.String r2 = r5.message     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            r3 = 0
            java.lang.String r4 = r5.message     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            r0.write(r2, r3, r4)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            r0.close()     // Catch: java.io.IOException -> L2e
            goto L30
        L2e:
            r2 = move-exception
        L30:
        L32:
            return
        L33:
            r2 = move-exception
            goto L41
        L35:
            r1 = move-exception
            org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L40
            org.apache.tools.ant.Location r3 = r5.getLocation()     // Catch: java.lang.Throwable -> L40
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L40
            throw r2     // Catch: java.lang.Throwable -> L40
        L40:
            r2 = move-exception
        L41:
            if (r0 == 0) goto L48
            r0.close()     // Catch: java.io.IOException -> L47
            goto L48
        L47:
            r3 = move-exception
        L48:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Echo.execute():void");
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public void addText(String msg) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.message);
        stringBuffer.append(getProject().replaceProperties(msg));
        this.message = stringBuffer.toString();
    }

    public void setLevel(EchoLevel echoLevel) {
        String option = echoLevel.getValue();
        if (option.equals("error")) {
            this.logLevel = 0;
            return;
        }
        if (option.equals("warning")) {
            this.logLevel = 1;
            return;
        }
        if (option.equals("info")) {
            this.logLevel = 2;
        } else if (option.equals("verbose")) {
            this.logLevel = 3;
        } else {
            this.logLevel = 4;
        }
    }

    public static class EchoLevel extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"error", "warning", "info", "verbose", "debug"};
        }
    }
}
