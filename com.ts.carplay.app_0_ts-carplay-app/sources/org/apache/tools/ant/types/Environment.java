package org.apache.tools.ant.types;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class Environment {
    protected Vector variables = new Vector();

    public static class Variable {
        private String key;
        private String value;

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public void setPath(Path path) {
            this.value = path.toString();
        }

        public void setFile(File file) {
            this.value = file.getAbsolutePath();
        }

        public String getContent() throws BuildException {
            validate();
            StringBuffer sb = new StringBuffer(this.key.trim());
            sb.append("=");
            sb.append(this.value.trim());
            return sb.toString();
        }

        public void validate() {
            if (this.key == null || this.value == null) {
                throw new BuildException("key and value must be specified for environment variables.");
            }
        }
    }

    public void addVariable(Variable var) {
        this.variables.addElement(var);
    }

    public String[] getVariables() throws BuildException {
        if (this.variables.size() == 0) {
            return null;
        }
        String[] result = new String[this.variables.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = ((Variable) this.variables.elementAt(i)).getContent();
        }
        return result;
    }
}
