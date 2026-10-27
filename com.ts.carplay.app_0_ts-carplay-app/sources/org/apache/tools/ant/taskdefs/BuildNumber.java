package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class BuildNumber extends Task {
    private static final String DEFAULT_FILENAME = "build.number";
    private static final String DEFAULT_PROPERTY_NAME = "build.number";
    private File myFile;

    public void setFile(File file) {
        this.myFile = file;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            r11 = this;
            java.lang.String r0 = "error closing output stream "
            java.lang.String r1 = "Build Number for ANT. Do not edit!"
            java.io.File r2 = r11.myFile
            r11.validate()
            java.util.Properties r3 = r11.loadProperties()
            int r4 = r11.getBuildNumber(r3)
            int r5 = r4 + 1
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r6 = "build.number"
            r3.put(r6, r5)
            r5 = 0
            r7 = 0
            r8 = 0
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            java.io.File r10 = r11.myFile     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            r5 = r9
            r8 = r1
            r3.store(r5, r1)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            r5.close()     // Catch: java.io.IOException -> L30
            goto L48
        L30:
            r1 = move-exception
            org.apache.tools.ant.Project r8 = r11.getProject()
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r9.<init>()
            r9.append(r0)
            r9.append(r1)
            java.lang.String r0 = r9.toString()
            r8.log(r0, r7)
        L48:
            r11.myFile = r2
            org.apache.tools.ant.Project r0 = r11.getProject()
            java.lang.String r1 = java.lang.String.valueOf(r4)
            r0.setNewProperty(r6, r1)
            return
        L56:
            r1 = move-exception
            r6 = r8
            goto L75
        L59:
            r1 = move-exception
            r6 = r1
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L74
            r1.<init>()     // Catch: java.lang.Throwable -> L74
            java.lang.String r8 = "Error while writing "
            r1.append(r8)     // Catch: java.lang.Throwable -> L74
            java.io.File r8 = r11.myFile     // Catch: java.lang.Throwable -> L74
            r1.append(r8)     // Catch: java.lang.Throwable -> L74
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L74
            org.apache.tools.ant.BuildException r8 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L74
            r8.<init>(r1, r6)     // Catch: java.lang.Throwable -> L74
            throw r8     // Catch: java.lang.Throwable -> L74
        L74:
            r1 = move-exception
        L75:
            if (r5 == 0) goto L92
            r5.close()     // Catch: java.io.IOException -> L7b
            goto L92
        L7b:
            r8 = move-exception
            org.apache.tools.ant.Project r9 = r11.getProject()
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r10.<init>()
            r10.append(r0)
            r10.append(r8)
            java.lang.String r0 = r10.toString()
            r9.log(r0, r7)
        L92:
            r11.myFile = r2
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.BuildNumber.execute():void");
    }

    private int getBuildNumber(Properties properties) throws BuildException {
        String buildNumber = properties.getProperty("build.number", "0").trim();
        try {
            return Integer.parseInt(buildNumber);
        } catch (NumberFormatException nfe) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.myFile);
            stringBuffer.append(" contains a non integer build number: ");
            stringBuffer.append(buildNumber);
            String message = stringBuffer.toString();
            throw new BuildException(message, nfe);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Properties loadProperties() throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.String r0 = "error closing input stream "
            r1 = 0
            r2 = 0
            r3 = 0
            java.util.Properties r4 = new java.util.Properties     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r4.<init>()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r3 = r4
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.io.File r5 = r8.myFile     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r1 = r4
            r3.load(r1)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r1.close()     // Catch: java.io.IOException -> L1c
            goto L34
        L1c:
            r4 = move-exception
            org.apache.tools.ant.Project r5 = r8.getProject()
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            r6.append(r0)
            r6.append(r4)
            java.lang.String r0 = r6.toString()
            r5.log(r0, r2)
        L34:
            return r3
        L35:
            r4 = move-exception
            goto L3f
        L37:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L3e
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L3e
            throw r4     // Catch: java.lang.Throwable -> L3e
        L3e:
            r4 = move-exception
        L3f:
            if (r1 == 0) goto L5c
            r1.close()     // Catch: java.io.IOException -> L45
            goto L5c
        L45:
            r5 = move-exception
            org.apache.tools.ant.Project r6 = r8.getProject()
            java.lang.StringBuffer r7 = new java.lang.StringBuffer
            r7.<init>()
            r7.append(r0)
            r7.append(r5)
            java.lang.String r0 = r7.toString()
            r6.log(r0, r2)
        L5c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.BuildNumber.loadProperties():java.util.Properties");
    }

    private void validate() throws BuildException {
        if (this.myFile == null) {
            this.myFile = getProject().resolveFile("build.number");
        }
        if (!this.myFile.exists()) {
            try {
                FileUtils.newFileUtils().createNewFile(this.myFile);
            } catch (IOException ioe) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.myFile);
                stringBuffer.append(" doesn't exist and new file can't be created.");
                String message = stringBuffer.toString();
                throw new BuildException(message, ioe);
            }
        }
        if (!this.myFile.canRead()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to read from ");
            stringBuffer2.append(this.myFile);
            stringBuffer2.append(".");
            String message2 = stringBuffer2.toString();
            throw new BuildException(message2);
        }
        if (!this.myFile.canWrite()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Unable to write to ");
            stringBuffer3.append(this.myFile);
            stringBuffer3.append(".");
            String message3 = stringBuffer3.toString();
            throw new BuildException(message3);
        }
    }
}
