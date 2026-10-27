package org.apache.tools.ant.types.selectors;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public class ContainsSelector extends BaseExtendSelector {
    public static final String CASE_KEY = "casesensitive";
    public static final String CONTAINS_KEY = "text";
    public static final String EXPRESSION_KEY = "expression";
    public static final String WHITESPACE_KEY = "ignorewhitespace";
    private String contains = null;
    private boolean casesensitive = true;
    private boolean ignorewhitespace = false;

    public String toString() {
        StringBuffer buf = new StringBuffer("{containsselector text: ");
        buf.append(this.contains);
        buf.append(" casesensitive: ");
        if (this.casesensitive) {
            buf.append("true");
        } else {
            buf.append("false");
        }
        buf.append(" ignorewhitespace: ");
        if (this.ignorewhitespace) {
            buf.append("true");
        } else {
            buf.append("false");
        }
        buf.append("}");
        return buf.toString();
    }

    public void setText(String contains) {
        this.contains = contains;
    }

    public void setCasesensitive(boolean casesensitive) {
        this.casesensitive = casesensitive;
    }

    public void setIgnorewhitespace(boolean ignorewhitespace) {
        this.ignorewhitespace = ignorewhitespace;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if (CONTAINS_KEY.equalsIgnoreCase(paramname)) {
                    setText(parameters[i].getValue());
                } else if ("casesensitive".equalsIgnoreCase(paramname)) {
                    setCasesensitive(Project.toBoolean(parameters[i].getValue()));
                } else if (WHITESPACE_KEY.equalsIgnoreCase(paramname)) {
                    setIgnorewhitespace(Project.toBoolean(parameters[i].getValue()));
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
        if (this.contains == null) {
            setError("The text attribute is required");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isSelected(java.io.File r9, java.lang.String r10, java.io.File r11) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.types.selectors.ContainsSelector.isSelected(java.io.File, java.lang.String, java.io.File):boolean");
    }
}
