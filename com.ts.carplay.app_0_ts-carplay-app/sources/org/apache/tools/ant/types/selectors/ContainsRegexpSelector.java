package org.apache.tools.ant.types.selectors;

import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.util.regexp.Regexp;

/* JADX INFO: loaded from: classes.dex */
public class ContainsRegexpSelector extends BaseExtendSelector {
    public static final String EXPRESSION_KEY = "expression";
    private String userProvidedExpression = null;
    private RegularExpression myRegExp = null;
    private Regexp myExpression = null;

    public String toString() {
        StringBuffer buf = new StringBuffer("{containsregexpselector expression: ");
        buf.append(this.userProvidedExpression);
        buf.append("}");
        return buf.toString();
    }

    public void setExpression(String theexpression) {
        this.userProvidedExpression = theexpression;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if ("expression".equalsIgnoreCase(paramname)) {
                    setExpression(parameters[i].getValue());
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Invalid parameter ");
                    stringBuffer.append(paramname);
                    setError(stringBuffer.toString());
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.userProvidedExpression == null) {
            setError("The expression attribute is required");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isSelected(java.io.File r9, java.lang.String r10, java.io.File r11) throws java.lang.Exception {
        /*
            r8 = this;
            java.lang.String r0 = "Could not close file "
            r1 = 0
            r2 = 0
            r8.validate()
            boolean r3 = r11.isDirectory()
            r4 = 1
            if (r3 == 0) goto Lf
            return r4
        Lf:
            org.apache.tools.ant.types.RegularExpression r3 = r8.myRegExp
            if (r3 != 0) goto L2b
            org.apache.tools.ant.types.RegularExpression r3 = new org.apache.tools.ant.types.RegularExpression
            r3.<init>()
            r8.myRegExp = r3
            java.lang.String r5 = r8.userProvidedExpression
            r3.setPattern(r5)
            org.apache.tools.ant.types.RegularExpression r3 = r8.myRegExp
            org.apache.tools.ant.Project r5 = r8.getProject()
            org.apache.tools.ant.util.regexp.Regexp r3 = r3.getRegexp(r5)
            r8.myExpression = r3
        L2b:
            r3 = 0
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            r7.<init>(r11)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            r2 = r5
            java.lang.String r5 = r2.readLine()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            r1 = r5
        L42:
            if (r1 != 0) goto L61
            r3 = 0
            r2.close()     // Catch: java.lang.Exception -> L4b
            return r3
        L4b:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            r5.append(r0)
            r5.append(r10)
            java.lang.String r0 = r5.toString()
            r4.<init>(r0)
            throw r4
        L61:
            org.apache.tools.ant.util.regexp.Regexp r5 = r8.myExpression     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            boolean r5 = r5.matches(r1)     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            if (r5 == 0) goto L86
        L6b:
            r2.close()     // Catch: java.lang.Exception -> L70
            return r4
        L70:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            r5.append(r0)
            r5.append(r10)
            java.lang.String r0 = r5.toString()
            r4.<init>(r0)
            throw r4
        L86:
            java.lang.String r5 = r2.readLine()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L8e
            r1 = r5
            goto L42
        L8c:
            r4 = move-exception
            goto La7
        L8e:
            r3 = move-exception
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> La6
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> La6
            r5.<init>()     // Catch: java.lang.Throwable -> La6
            java.lang.String r6 = "Could not read file "
            r5.append(r6)     // Catch: java.lang.Throwable -> La6
            r5.append(r10)     // Catch: java.lang.Throwable -> La6
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> La6
            r4.<init>(r5)     // Catch: java.lang.Throwable -> La6
            throw r4     // Catch: java.lang.Throwable -> La6
        La6:
            r4 = move-exception
        La7:
            if (r2 == 0) goto Lc3
            r2.close()     // Catch: java.lang.Exception -> Lad
            goto Lc3
        Lad:
            r4 = move-exception
            org.apache.tools.ant.BuildException r5 = new org.apache.tools.ant.BuildException
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            r6.append(r0)
            r6.append(r10)
            java.lang.String r0 = r6.toString()
            r5.<init>(r0)
            throw r5
        Lc3:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.types.selectors.ContainsRegexpSelector.isSelected(java.io.File, java.lang.String, java.io.File):boolean");
    }
}
