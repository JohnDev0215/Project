package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.DataType;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSelector extends DataType implements FileSelector {
    private String errmsg = null;

    public abstract boolean isSelected(File file, String str, File file2);

    public void setError(String msg) {
        if (this.errmsg == null) {
            this.errmsg = msg;
        }
    }

    public String getError() {
        return this.errmsg;
    }

    public void verifySettings() {
    }

    public void validate() {
        if (getError() == null) {
            verifySettings();
        }
        if (getError() != null) {
            throw new BuildException(this.errmsg);
        }
    }
}
