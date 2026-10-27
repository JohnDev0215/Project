package org.apache.tools.ant.input;

import java.io.InputStream;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class DefaultInputHandler implements InputHandler {
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0035, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
    
        throw new org.apache.tools.ant.BuildException("Failed to close input.", r3);
     */
    @Override // org.apache.tools.ant.input.InputHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleInput(org.apache.tools.ant.input.InputRequest r7) throws java.io.IOException, org.apache.tools.ant.BuildException {
        /*
            r6 = this;
            java.lang.String r0 = "Failed to close input."
            java.lang.String r1 = r6.getPrompt(r7)
            r2 = 0
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L45
            org.apache.tools.ant.util.KeepAliveInputStream r4 = new org.apache.tools.ant.util.KeepAliveInputStream     // Catch: java.lang.Throwable -> L45
            java.io.InputStream r5 = r6.getInputStream()     // Catch: java.lang.Throwable -> L45
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L45
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L45
            r2 = r3
        L16:
            java.io.PrintStream r3 = java.lang.System.err     // Catch: java.lang.Throwable -> L45
            r3.println(r1)     // Catch: java.lang.Throwable -> L45
            java.io.PrintStream r3 = java.lang.System.err     // Catch: java.lang.Throwable -> L45
            r3.flush()     // Catch: java.lang.Throwable -> L45
            java.lang.String r3 = r2.readLine()     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L45
            r7.setInput(r3)     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L45
            boolean r3 = r7.isInputValid()     // Catch: java.lang.Throwable -> L45
            if (r3 == 0) goto L16
        L2f:
            r2.close()     // Catch: java.io.IOException -> L35
            return
        L35:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException
            r4.<init>(r0, r3)
            throw r4
        L3c:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L45
            java.lang.String r5 = "Failed to read input from Console."
            r4.<init>(r5, r3)     // Catch: java.lang.Throwable -> L45
            throw r4     // Catch: java.lang.Throwable -> L45
        L45:
            r3 = move-exception
            if (r2 == 0) goto L53
            r2.close()     // Catch: java.io.IOException -> L4c
            goto L53
        L4c:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException
            r4.<init>(r0, r3)
            throw r4
        L53:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.input.DefaultInputHandler.handleInput(org.apache.tools.ant.input.InputRequest):void");
    }

    protected String getPrompt(InputRequest request) {
        String prompt = request.getPrompt();
        if (request instanceof MultipleChoiceInputRequest) {
            StringBuffer sb = new StringBuffer(prompt);
            sb.append("(");
            Enumeration e = ((MultipleChoiceInputRequest) request).getChoices().elements();
            boolean first = true;
            while (e.hasMoreElements()) {
                if (!first) {
                    sb.append(",");
                }
                sb.append(e.nextElement());
                first = false;
            }
            sb.append(")");
            return sb.toString();
        }
        return prompt;
    }

    protected InputStream getInputStream() {
        return System.in;
    }
}
