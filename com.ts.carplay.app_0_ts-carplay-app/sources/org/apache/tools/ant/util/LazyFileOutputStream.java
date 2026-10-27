package org.apache.tools.ant.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class LazyFileOutputStream extends OutputStream {
    private boolean alwaysCreate;
    private boolean append;
    private boolean closed;
    private File file;
    private FileOutputStream fos;
    private boolean opened;

    public LazyFileOutputStream(String name) {
        this(name, false);
    }

    public LazyFileOutputStream(String name, boolean append) {
        this(new File(name), append);
    }

    public LazyFileOutputStream(File f) {
        this(f, false);
    }

    public LazyFileOutputStream(File file, boolean append) {
        this(file, append, false);
    }

    public LazyFileOutputStream(File file, boolean append, boolean alwaysCreate) {
        this.opened = false;
        this.closed = false;
        this.file = file;
        this.append = append;
        this.alwaysCreate = alwaysCreate;
    }

    public void open() throws IOException {
        ensureOpened();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.alwaysCreate && !this.closed) {
            ensureOpened();
        }
        if (this.opened) {
            this.fos.close();
        }
        this.closed = true;
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b, int offset, int len) throws IOException {
        ensureOpened();
        this.fos.write(b, offset, len);
    }

    @Override // java.io.OutputStream
    public synchronized void write(int b) throws IOException {
        ensureOpened();
        this.fos.write(b);
    }

    private synchronized void ensureOpened() throws IOException {
        if (this.closed) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.file);
            stringBuffer.append(" has already been closed.");
            throw new IOException(stringBuffer.toString());
        }
        if (!this.opened) {
            this.fos = new FileOutputStream(this.file.getAbsolutePath(), this.append);
            this.opened = true;
        }
    }
}
