package org.apache.tools.ant.taskdefs;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public abstract class Pack extends Task {
    protected File source;
    protected File zipFile;

    protected abstract void pack();

    public void setZipfile(File zipFile) {
        this.zipFile = zipFile;
    }

    public void setDestfile(File zipFile) {
        setZipfile(zipFile);
    }

    public void setSrc(File src) {
        this.source = src;
    }

    private void validate() throws BuildException {
        File file = this.zipFile;
        if (file == null) {
            throw new BuildException("zipfile attribute is required", getLocation());
        }
        if (file.isDirectory()) {
            throw new BuildException("zipfile attribute must not represent a directory!", getLocation());
        }
        File file2 = this.source;
        if (file2 == null) {
            throw new BuildException("src attribute is required", getLocation());
        }
        if (file2.isDirectory()) {
            throw new BuildException("Src attribute must not represent a directory!", getLocation());
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        validate();
        if (!this.source.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Nothing to do: ");
            stringBuffer.append(this.source.getAbsolutePath());
            stringBuffer.append(" doesn't exist.");
            log(stringBuffer.toString());
            return;
        }
        if (this.zipFile.lastModified() < this.source.lastModified()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Building: ");
            stringBuffer2.append(this.zipFile.getAbsolutePath());
            log(stringBuffer2.toString());
            pack();
            return;
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("Nothing to do: ");
        stringBuffer3.append(this.zipFile.getAbsolutePath());
        stringBuffer3.append(" is up to date.");
        log(stringBuffer3.toString());
    }

    private void zipFile(InputStream in, OutputStream zOut) throws IOException {
        byte[] buffer = new byte[VehicleVendorProperty.Health.OIL_HINT];
        int count = 0;
        do {
            zOut.write(buffer, 0, count);
            count = in.read(buffer, 0, buffer.length);
        } while (count != -1);
    }

    protected void zipFile(File file, OutputStream zOut) throws IOException {
        FileInputStream fIn = new FileInputStream(file);
        try {
            zipFile(fIn, zOut);
        } finally {
            fIn.close();
        }
    }
}
