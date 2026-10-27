package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.ZipFileSet;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class War extends Jar {
    private static final FileUtils fu = FileUtils.newFileUtils();
    private File deploymentDescriptor;
    private boolean descriptorAdded;

    public War() {
        this.archiveType = "war";
        this.emptyBehavior = "create";
    }

    public void setWarfile(File warFile) {
        setDestFile(warFile);
    }

    public void setWebxml(File descr) {
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
        fs.setFullpath("WEB-INF/web.xml");
        super.addFileset(fs);
    }

    public void addLib(ZipFileSet fs) {
        fs.setPrefix("WEB-INF/lib/");
        super.addFileset(fs);
    }

    public void addClasses(ZipFileSet fs) {
        fs.setPrefix("WEB-INF/classes/");
        super.addFileset(fs);
    }

    public void addWebinf(ZipFileSet fs) {
        fs.setPrefix("WEB-INF/");
        super.addFileset(fs);
    }

    @Override // org.apache.tools.ant.taskdefs.Jar, org.apache.tools.ant.taskdefs.Zip
    protected void initZipOutputStream(ZipOutputStream zOut) throws IOException, BuildException {
        if (this.deploymentDescriptor == null && !isInUpdateMode()) {
            throw new BuildException("webxml attribute is required", getLocation());
        }
        super.initZipOutputStream(zOut);
    }

    @Override // org.apache.tools.ant.taskdefs.Zip
    protected void zipFile(File file, ZipOutputStream zOut, String vPath, int mode) throws IOException {
        if (vPath.equalsIgnoreCase("WEB-INF/web.xml")) {
            File file2 = this.deploymentDescriptor;
            if (file2 == null || !fu.fileNameEquals(file2, file) || this.descriptorAdded) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Warning: selected ");
                stringBuffer.append(this.archiveType);
                stringBuffer.append(" files include a WEB-INF/web.xml which will be ignored ");
                stringBuffer.append("(please use webxml attribute to ");
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
