package org.apache.tools.ant;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class DemuxInputStream extends InputStream {
    private Project project;

    public DemuxInputStream(Project project) {
        this.project = project;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] buffer = new byte[1];
        if (this.project.demuxInput(buffer, 0, 1) == -1) {
            return -1;
        }
        return buffer[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        return this.project.demuxInput(buffer, offset, length);
    }
}
