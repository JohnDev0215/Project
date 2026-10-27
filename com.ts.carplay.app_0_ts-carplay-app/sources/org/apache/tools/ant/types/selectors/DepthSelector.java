package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public class DepthSelector extends BaseExtendSelector {
    public static final String MAX_KEY = "max";
    public static final String MIN_KEY = "min";
    public int min = -1;
    public int max = -1;

    public String toString() {
        StringBuffer buf = new StringBuffer("{depthselector min: ");
        buf.append(this.min);
        buf.append(" max: ");
        buf.append(this.max);
        buf.append("}");
        return buf.toString();
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if (MIN_KEY.equalsIgnoreCase(paramname)) {
                    try {
                        setMin(Integer.parseInt(parameters[i].getValue()));
                    } catch (NumberFormatException e) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Invalid minimum value ");
                        stringBuffer.append(parameters[i].getValue());
                        setError(stringBuffer.toString());
                    }
                } else if (MAX_KEY.equalsIgnoreCase(paramname)) {
                    try {
                        setMax(Integer.parseInt(parameters[i].getValue()));
                    } catch (NumberFormatException e2) {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Invalid maximum value ");
                        stringBuffer2.append(parameters[i].getValue());
                        setError(stringBuffer2.toString());
                    }
                } else {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Invalid parameter ");
                    stringBuffer3.append(paramname);
                    setError(stringBuffer3.toString());
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.min < 0 && this.max < 0) {
            setError("You must set at least one of the min or the max levels.");
        }
        int i = this.max;
        if (i < this.min && i > -1) {
            setError("The maximum depth is lower than the minimum.");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        int depth = -1;
        String absBase = basedir.getAbsolutePath();
        String absFile = file.getAbsolutePath();
        StringTokenizer tokBase = new StringTokenizer(absBase, File.separator);
        StringTokenizer tokFile = new StringTokenizer(absFile, File.separator);
        while (tokFile.hasMoreTokens()) {
            String filetoken = tokFile.nextToken();
            if (tokBase.hasMoreTokens()) {
                String basetoken = tokBase.nextToken();
                if (!basetoken.equals(filetoken)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("File ");
                    stringBuffer.append(filename);
                    stringBuffer.append(" does not appear within ");
                    stringBuffer.append(absBase);
                    stringBuffer.append("directory");
                    throw new BuildException(stringBuffer.toString());
                }
            } else {
                depth++;
                int i = this.max;
                if (i > -1 && depth > i) {
                    return false;
                }
            }
        }
        if (tokBase.hasMoreTokens()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("File ");
            stringBuffer2.append(filename);
            stringBuffer2.append(" is outside of ");
            stringBuffer2.append(absBase);
            stringBuffer2.append("directory tree");
            throw new BuildException(stringBuffer2.toString());
        }
        int i2 = this.min;
        return i2 <= -1 || depth >= i2;
    }
}
