package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public class TypeSelector extends BaseExtendSelector {
    public static final String TYPE_KEY = "type";
    private String type = null;

    public String toString() {
        StringBuffer buf = new StringBuffer("{typeselector type: ");
        buf.append(this.type);
        buf.append("}");
        return buf.toString();
    }

    public void setType(FileType fileTypes) {
        this.type = fileTypes.getValue();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        super.setParameters(parameters);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                String paramname = parameters[i].getName();
                if (TYPE_KEY.equalsIgnoreCase(paramname)) {
                    FileType type = new FileType();
                    type.setValue(parameters[i].getValue());
                    setType(type);
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
        if (this.type == null) {
            setError("The type attribute is required");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        if (file.isDirectory()) {
            return this.type.equals(FileType.DIR);
        }
        return this.type.equals(FileType.FILE);
    }

    public static class FileType extends EnumeratedAttribute {
        public static final String DIR = "dir";
        public static final String FILE = "file";

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{FILE, DIR};
        }
    }
}
