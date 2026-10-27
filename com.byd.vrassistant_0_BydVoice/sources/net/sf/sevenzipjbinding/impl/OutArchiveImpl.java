package net.sf.sevenzipjbinding.impl;

import java.io.IOException;
import java.io.PrintStream;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.IOutArchive;
import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItemBase;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class OutArchiveImpl<T extends IOutItemBase> implements IOutArchive<T> {
    private ArchiveFormat archiveFormat;
    private boolean closed;
    private Boolean headerEncryption;
    private IInArchive inArchive;
    private long jbindingSession;
    private long sevenZipArchiveInstance;
    private boolean trace;
    private PrintStream tracePrintStream;
    private int compressionLevel = -1;
    private int threadCount = -1;

    private native void nativeClose() throws SevenZipException;

    private native void nativeUpdateItems(ISequentialOutStream iSequentialOutStream, int i, Object obj) throws SevenZipException;

    protected native void nativeSetHeaderEncryption(boolean z) throws SevenZipException;

    protected native void nativeSetLevel(int i) throws SevenZipException;

    protected native void nativeSetMultithreading(int i) throws SevenZipException;

    protected native void nativeSetSolidSpec(String str) throws SevenZipException;

    protected void setInArchive(IInArchive iInArchive) {
        this.inArchive = iInArchive;
    }

    protected void featureSetHeaderEncryption(boolean z) {
        this.headerEncryption = Boolean.valueOf(z);
    }

    protected void featureSetLevel(int i) {
        this.compressionLevel = i;
    }

    protected void featureSetThreadCount(int i) {
        this.threadCount = i;
    }

    protected void applyFeatures() throws SevenZipException {
        ensureOpened();
        int i = this.compressionLevel;
        if (i != -1) {
            nativeSetLevel(i);
        }
        Boolean bool = this.headerEncryption;
        if (bool != null) {
            nativeSetHeaderEncryption(bool.booleanValue());
        }
        int i2 = this.threadCount;
        if (i2 >= 0) {
            nativeSetMultithreading(i2);
        }
    }

    @Override // net.sf.sevenzipjbinding.IOutCreateArchive, net.sf.sevenzipjbinding.IOutUpdateArchive
    public ArchiveFormat getArchiveFormat() {
        return this.archiveFormat;
    }

    protected void setArchiveFormat(ArchiveFormat archiveFormat) {
        this.archiveFormat = archiveFormat;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (this.inArchive != null) {
            return;
        }
        nativeClose();
    }

    @Override // net.sf.sevenzipjbinding.IOutUpdateArchive
    public void updateItems(ISequentialOutStream iSequentialOutStream, int i, IOutCreateCallback<T> iOutCreateCallback) throws SevenZipException {
        ensureOpened();
        doUpdateItems(iSequentialOutStream, i, iOutCreateCallback);
    }

    private void doUpdateItems(ISequentialOutStream iSequentialOutStream, int i, IOutCreateCallback<?> iOutCreateCallback) throws SevenZipException {
        applyFeatures();
        nativeUpdateItems(iSequentialOutStream, i, iOutCreateCallback);
    }

    @Override // net.sf.sevenzipjbinding.IOutCreateArchive
    public void createArchive(ISequentialOutStream iSequentialOutStream, int i, IOutCreateCallback<? extends T> iOutCreateCallback) throws SevenZipException {
        ensureOpened();
        doUpdateItems(iSequentialOutStream, i, iOutCreateCallback);
    }

    @Override // net.sf.sevenzipjbinding.IOutUpdateArchive
    public IInArchive getConnectedInArchive() {
        return this.inArchive;
    }

    private final void traceMessage(String str) {
        if (this.trace) {
            PrintStream printStream = this.tracePrintStream;
            if (printStream == null) {
                System.out.println(str);
            } else {
                printStream.println(str);
            }
        }
    }

    @Override // net.sf.sevenzipjbinding.IOutArchiveBase
    public void setTracePrintStream(PrintStream printStream) {
        this.tracePrintStream = printStream;
    }

    @Override // net.sf.sevenzipjbinding.IOutArchiveBase
    public PrintStream getTracePrintStream() {
        return this.tracePrintStream;
    }

    @Override // net.sf.sevenzipjbinding.IOutArchiveBase
    public void setTrace(boolean z) {
        this.trace = z;
    }

    @Override // net.sf.sevenzipjbinding.IOutArchiveBase
    public boolean isTrace() {
        return this.trace;
    }

    private void ensureOpened() throws SevenZipException {
        if (this.closed) {
            throw new SevenZipException("OutArchive closed");
        }
        IInArchive iInArchive = this.inArchive;
        if (iInArchive != null) {
            ((InArchiveImpl) iInArchive).ensureOpened();
        }
    }
}
