package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.IdentityMapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class MappingSelector extends BaseSelector {
    protected int granularity;
    protected File targetdir = null;
    protected Mapper mapperElement = null;
    protected FileNameMapper map = null;

    protected abstract boolean selectionTest(File file, File file2);

    public MappingSelector() {
        this.granularity = 0;
        this.granularity = (int) FileUtils.newFileUtils().getFileTimestampGranularity();
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
            stringBuffer.append(this.targetdir.getName());
            stringBuffer.append(" with filename ");
            stringBuffer.append(filename);
            throw new BuildException(stringBuffer.toString());
        }
        String destname = destfiles[0];
        File destfile = new File(this.targetdir, destname);
        boolean selected = selectionTest(file, destfile);
        return selected;
    }

    public void setGranularity(int granularity) {
        this.granularity = granularity;
    }
}
