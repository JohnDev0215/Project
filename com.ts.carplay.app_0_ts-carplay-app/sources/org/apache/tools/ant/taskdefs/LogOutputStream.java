package org.apache.tools.ant.taskdefs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class LogOutputStream extends OutputStream {
    private static final int CR = 13;
    private static final int INTIAL_SIZE = 132;
    private static final int LF = 10;
    private ByteArrayOutputStream buffer;
    private int level;
    private ProjectComponent pc;
    private boolean skip;

    public LogOutputStream(Task task, int level) {
        this((ProjectComponent) task, level);
    }

    public LogOutputStream(ProjectComponent pc, int level) {
        this.buffer = new ByteArrayOutputStream(132);
        this.skip = false;
        this.level = 2;
        this.pc = pc;
        this.level = level;
    }

    @Override // java.io.OutputStream
    public void write(int cc) throws IOException {
        byte c = (byte) cc;
        if (c == 10 || c == 13) {
            if (!this.skip) {
                processBuffer();
            }
        } else {
            this.buffer.write(cc);
        }
        this.skip = c == 13;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (this.buffer.size() > 0) {
            processBuffer();
        }
    }

    protected void processBuffer() {
        processLine(this.buffer.toString());
        this.buffer.reset();
    }

    protected void processLine(String line) {
        processLine(line, this.level);
    }

    protected void processLine(String line, int level) {
        this.pc.log(line, level);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.buffer.size() > 0) {
            processBuffer();
        }
        super.close();
    }

    public int getMessageLevel() {
        return this.level;
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        int offset = off;
        int blockStartOffset = offset;
        int remaining = len;
        while (remaining > 0) {
            while (remaining > 0 && b[offset] != 10 && b[offset] != 13) {
                offset++;
                remaining--;
            }
            int blockLength = offset - blockStartOffset;
            if (blockLength > 0) {
                this.buffer.write(b, blockStartOffset, blockLength);
            }
            while (remaining > 0 && (b[offset] == 10 || b[offset] == 13)) {
                write(b[offset]);
                offset++;
                remaining--;
            }
            blockStartOffset = offset;
        }
    }
}
