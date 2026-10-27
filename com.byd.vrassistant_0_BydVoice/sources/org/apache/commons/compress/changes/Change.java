package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* JADX INFO: loaded from: classes4.dex */
class Change {
    static final int TYPE_ADD = 2;
    static final int TYPE_DELETE = 1;
    static final int TYPE_DELETE_DIR = 4;
    static final int TYPE_MOVE = 3;
    private final ArchiveEntry entry;
    private final InputStream input;
    private final boolean replaceMode;
    private final String targetFile;
    private final int type;

    Change(String str, int i) {
        Objects.requireNonNull(str, "fileName");
        this.targetFile = str;
        this.type = i;
        this.input = null;
        this.entry = null;
        this.replaceMode = true;
    }

    Change(ArchiveEntry archiveEntry, InputStream inputStream, boolean z) {
        Objects.requireNonNull(archiveEntry, "archiveEntry");
        Objects.requireNonNull(inputStream, "inputStream");
        this.entry = archiveEntry;
        this.input = inputStream;
        this.type = 2;
        this.targetFile = null;
        this.replaceMode = z;
    }

    ArchiveEntry getEntry() {
        return this.entry;
    }

    InputStream getInput() {
        return this.input;
    }

    String targetFile() {
        return this.targetFile;
    }

    int type() {
        return this.type;
    }

    boolean isReplaceMode() {
        return this.replaceMode;
    }
}
