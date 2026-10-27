package net.sf.sevenzipjbinding.simple;

import java.util.Date;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public interface ISimpleInArchiveItem {
    ExtractOperationResult extractSlow(ISequentialOutStream iSequentialOutStream) throws SevenZipException;

    ExtractOperationResult extractSlow(ISequentialOutStream iSequentialOutStream, String str) throws SevenZipException;

    Integer getAttributes() throws SevenZipException;

    Integer getCRC() throws SevenZipException;

    String getComment() throws SevenZipException;

    Date getCreationTime() throws SevenZipException;

    String getGroup() throws SevenZipException;

    String getHostOS() throws SevenZipException;

    int getItemIndex();

    Date getLastAccessTime() throws SevenZipException;

    Date getLastWriteTime() throws SevenZipException;

    String getMethod() throws SevenZipException;

    Long getPackedSize() throws SevenZipException;

    String getPath() throws SevenZipException;

    Integer getPosition() throws SevenZipException;

    Long getSize() throws SevenZipException;

    String getUser() throws SevenZipException;

    Boolean isCommented() throws SevenZipException;

    boolean isEncrypted() throws SevenZipException;

    boolean isFolder() throws SevenZipException;
}
