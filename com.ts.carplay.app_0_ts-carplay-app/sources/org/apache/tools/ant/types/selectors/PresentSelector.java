package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;

/* JADX INFO: loaded from: classes.dex */
public class PresentSelector extends BaseSelector {
    private File targetdir = null;
    private Mapper mapperElement = null;
    private FileNameMapper map = null;
    private boolean destmustexist = true;

    public String toString() {
        StringBuffer buf = new StringBuffer("{presentselector targetdir: ");
        File file = this.targetdir;
        if (file == null) {
            buf.append("NOT YET SET");
        } else {
            buf.append(file.getName());
        }
        buf.append(" present: ");
        if (this.destmustexist) {
            buf.append("both");
        } else {
            buf.append("srconly");
        }
        FileNameMapper fileNameMapper = this.map;
        if (fileNameMapper != null) {
            buf.append(fileNameMapper.toString());
        } else {
            Mapper mapper = this.mapperElement;
            if (mapper != null) {
                buf.append(mapper.toString());
            }
        }
        buf.append("}");
        return buf.toString();
    }

    public void setTargetdir(File targetdir) {
        this.targetdir = targetdir;
    }

    public Mapper createMapper() throws BuildException {
        if (this.mapperElement != null) {
            throw new BuildException("Cannot define more than one mapper");
        }
        Mapper mapper = new Mapper(getProject());
        this.mapperElement = mapper;
        return mapper;
    }

    public void setPresent(FilePresence fp) {
        if (fp.getIndex() == 0) {
            this.destmustexist = false;
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.targetdir == null) {
            setError("The targetdir attribute is required.");
        }
        Mapper mapper = this.mapperElement;
        if (mapper == null) {
            this.map = new IdentityMapper();
        } else {
            this.map = mapper.getImplementation();
        }
        if (this.map == null) {
            setError("Could not set <mapper> element.");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        String[] destfiles = this.map.mapFileName(filename);
        if (destfiles == null) {
            return false;
        }
        if (destfiles.length != 1 || destfiles[0] == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid destination file results for ");
            stringBuffer.append(this.targetdir);
            stringBuffer.append(" with filename ");
            stringBuffer.append(filename);
            throw new BuildException(stringBuffer.toString());
        }
        String destname = destfiles[0];
        File destfile = new File(this.targetdir, destname);
        return destfile.exists() == this.destmustexist;
    }

    public static class FilePresence extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"srconly", "both"};
        }
    }
}
