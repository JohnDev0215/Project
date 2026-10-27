package net.lingala.zip4j.io.inputstream;

import java.io.InputStream;
import net.lingala.zip4j.model.FileHeader;

/* JADX INFO: loaded from: classes.dex */
public abstract class SplitFileInputStream extends InputStream {
    public abstract void prepareExtractionForFileHeader(FileHeader fileHeader);
}
