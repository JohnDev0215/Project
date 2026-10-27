package org.apache.tools.ant.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class ConcatFileInputStream extends InputStream {
    private static final int EOF = -1;
    private InputStream currentStream;
    private File[] file;
    private ProjectComponent managingPc;
    private int currentIndex = -1;
    private boolean eof = false;

    public ConcatFileInputStream(File[] file) throws IOException {
        this.file = file;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        closeCurrent();
        this.eof = true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int result = readCurrent();
        if (result == -1 && !this.eof) {
            int i = this.currentIndex + 1;
            this.currentIndex = i;
            openFile(i);
            return readCurrent();
        }
        return result;
    }

    public void setManagingTask(Task task) {
        setManagingComponent(task);
    }

    public void setManagingComponent(ProjectComponent pc) {
        this.managingPc = pc;
    }

    public void log(String message, int loglevel) {
        ProjectComponent projectComponent = this.managingPc;
        if (projectComponent != null) {
            projectComponent.log(message, loglevel);
        } else if (loglevel > 1) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
    }

    private int readCurrent() throws IOException {
        InputStream inputStream;
        if (this.eof || (inputStream = this.currentStream) == null) {
            return -1;
        }
        return inputStream.read();
    }

    private void openFile(int index) throws IOException {
        closeCurrent();
        File[] fileArr = this.file;
        if (fileArr != null && index < fileArr.length) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Opening ");
            stringBuffer.append(this.file[index]);
            log(stringBuffer.toString(), 3);
            try {
                this.currentStream = new BufferedInputStream(new FileInputStream(this.file[index]));
                return;
            } catch (IOException eyeOhEx) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Failed to open ");
                stringBuffer2.append(this.file[index]);
                log(stringBuffer2.toString(), 0);
                throw eyeOhEx;
            }
        }
        this.eof = true;
    }

    private void closeCurrent() {
        FileUtils.close(this.currentStream);
        this.currentStream = null;
    }
}
