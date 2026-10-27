package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class Ear extends Jar {
    private static final FileUtils fu = FileUtils.newFileUtils();
    private File deploymentDescriptor;
    private boolean descriptorAdded;

    public Ear() {
        this.archiveType = "ear";
        this.emptyBehavior = "create";
    }

    public void setEarfile(File earFile) {
        setDestFile(earFile);
    }

    public void setAppxml(File descr) {
        this.deploymentDescriptor = descr;
        if (!descr.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Deployment descriptor: ");
            stringBuffer.append(this.deploymentDescriptor);
            stringBuffer.append(" does not exist.");
            throw new BuildException(stringBuffer.toString());
        }
        ZipFileSet fs = new ZipFileSet();
        fs.setFile(this.deploymentDescriptor);
        fs.setFullpath("META-INF/application.xml");
        super.addFileset(fs);
    }

    public void addArchives(ZipFileSet fs) {
        fs.setPrefix("/");
        super.addFileset(fs);
    }

    @Override // org.apache.tools.ant.taskdefs.Jar, org.apache.tools.ant.taskdefs.Zip
    protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
        if (this.deploymentDescriptor == null && !isInUpdateMode()) {
            throw new BuildException("appxml attribute is required", getLocation());
        }
        super.initZipOutputStream(zOut);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void zipFile(File file, ZipOutputStream zOut, String vPath, int mode) throws IOException {
        if (vPath.equalsIgnoreCase("META-INF/application.xml")) {
            File file2 = this.deploymentDescriptor;
            if (file2 == null || !fu.fileNameEquals(file2, file) || this.descriptorAdded) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Warning: selected ");
                stringBuffer.append(this.archiveType);
                stringBuffer.append(" files include a META-INF/application.xml which will");
                stringBuffer.append(" be ignored (please use appxml attribute to ");
                stringBuffer.append(this.archiveType);
                stringBuffer.append(" task)");
                log(stringBuffer.toString(), 1);
                return;
            }
            super.zipFile(file, zOut, vPath, mode);
            this.descriptorAdded = true;
            return;
        }
        super.zipFile(file, zOut, vPath, mode);
    }

    @Override // org.apache.tools.ant.taskdefs.Jar, org.apache.tools.ant.taskdefs.Zip
    protected void cleanUp() {
        this.descriptorAdded = false;
        super.cleanUp();
    }
}
