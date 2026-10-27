package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public class FilenameSelector extends BaseExtendSelector {
    public static final String CASE_KEY = "casesensitive";
    public static final String NAME_KEY = "name";
    public static final String NEGATE_KEY = "negate";
    private String pattern = null;
    private boolean casesensitive = true;
    private boolean negated = false;

    public String toString() {
        StringBuffer buf = new StringBuffer("{filenameselector name: ");
        buf.append(this.pattern);
        buf.append(" negate: ");
        if (this.negated) {
            buf.append("true");
        } else {
            buf.append("false");
        }
        buf.append(" casesensitive: ");
        if (this.casesensitive) {
            buf.append("true");
        } else {
            buf.append("false");
        }
        buf.append("}");
        return buf.toString();
    }

    public void setName(String pattern) {
        String pattern2 = pattern.replace('/', File.separatorChar).replace('\\', File.separatorChar);
        if (pattern2.endsWith(File.separator)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(pattern2);
            stringBuffer.append("**");
            pattern2 = stringBuffer.toString();
        }
        this.pattern = pattern2;
    }

    public void setCasesensitive(boolean casesensitive) {
        this.casesensitive = casesensitive;
    }

    public void setNegate(boolean negated) {
        this.negated = negated;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if (NAME_KEY.equalsIgnoreCase(paramname)) {
                    setName(parameters[i].getValue());
                } else if ("casesensitive".equalsIgnoreCase(paramname)) {
                    setCasesensitive(Project.toBoolean(parameters[i].getValue()));
                } else if (NEGATE_KEY.equalsIgnoreCase(paramname)) {
                    setNegate(Project.toBoolean(parameters[i].getValue()));
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
        if (this.pattern == null) {
            setError("The name attribute is required");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        return SelectorUtils.matchPath(this.pattern, filename, this.casesensitive) == (this.negated ^ true);
    }
}
