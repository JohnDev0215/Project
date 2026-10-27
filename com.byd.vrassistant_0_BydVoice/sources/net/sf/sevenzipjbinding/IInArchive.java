package net.sf.sevenzipjbinding;

import java.io.Closeable;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;

/* JADX INFO: loaded from: classes3.dex */
public interface IInArchive extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws SevenZipException;

    void extract(int[] iArr, boolean z, IArchiveExtractCallback iArchiveExtractCallback) throws SevenZipException;

    ExtractOperationResult extractSlow(int i, ISequentialOutStream iSequentialOutStream) throws SevenZipException;

    ExtractOperationResult extractSlow(int i, ISequentialOutStream iSequentialOutStream, String str) throws SevenZipException;

    ArchiveFormat getArchiveFormat();

    Object getArchiveProperty(PropID propID) throws SevenZipException;

    PropertyInfo getArchivePropertyInfo(int i) throws SevenZipException;

    IOutUpdateArchive<IOutItemAllFormats> getConnectedOutArchive() throws SevenZipException;

    IOutUpdateArchive7z getConnectedOutArchive7z() throws SevenZipException;

    IOutUpdateArchiveBZip2 getConnectedOutArchiveBZip2() throws SevenZipException;

    IOutUpdateArchiveGZip getConnectedOutArchiveGZip() throws SevenZipException;

    IOutUpdateArchiveTar getConnectedOutArchiveTar() throws SevenZipException;

    IOutUpdateArchiveZip getConnectedOutArchiveZip() throws SevenZipException;

    int getNumberOfArchiveProperties() throws SevenZipException;

    int getNumberOfItems() throws SevenZipException;

    int getNumberOfProperties() throws SevenZipException;

    Object getProperty(int i, PropID propID) throws SevenZipException;

    PropertyInfo getPropertyInfo(int i) throws SevenZipException;

    ISimpleInArchive getSimpleInterface();

    String getStringArchiveProperty(PropID propID) throws SevenZipException;

    String getStringProperty(int i, PropID propID) throws SevenZipException;
}
