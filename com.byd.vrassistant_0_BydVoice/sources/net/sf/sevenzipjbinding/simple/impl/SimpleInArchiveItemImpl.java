package net.sf.sevenzipjbinding.simple.impl;

import java.util.Date;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleInArchiveItemImpl implements ISimpleInArchiveItem {
    private final int index;
    private final SimpleInArchiveImpl simpleInArchiveImpl;

    public SimpleInArchiveItemImpl(SimpleInArchiveImpl simpleInArchiveImpl, int i) {
        this.simpleInArchiveImpl = simpleInArchiveImpl;
        this.index = i;
    }

    public SimpleInArchiveItemImpl(IInArchive iInArchive, int i) {
        this.simpleInArchiveImpl = new SimpleInArchiveImpl(iInArchive);
        this.index = i;
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public String getPath() throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getStringProperty(this.index, PropID.PATH);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Integer getAttributes() throws SevenZipException {
        return (Integer) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.ATTRIBUTES);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Integer getCRC() throws SevenZipException {
        return (Integer) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.CRC);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public String getComment() throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getStringProperty(this.index, PropID.COMMENT);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Date getCreationTime() throws SevenZipException {
        return (Date) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.CREATION_TIME);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public String getGroup() throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getStringProperty(this.index, PropID.GROUP);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public String getHostOS() throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getStringProperty(this.index, PropID.HOST_OS);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Date getLastAccessTime() throws SevenZipException {
        return (Date) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.LAST_ACCESS_TIME);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Date getLastWriteTime() throws SevenZipException {
        return (Date) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.LAST_MODIFICATION_TIME);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public String getMethod() throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getStringProperty(this.index, PropID.METHOD);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Long getPackedSize() throws SevenZipException {
        return (Long) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.PACKED_SIZE);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Integer getPosition() throws SevenZipException {
        return (Integer) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.POSITION);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Long getSize() throws SevenZipException {
        return (Long) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.SIZE);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public String getUser() throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getStringProperty(this.index, PropID.USER);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public Boolean isCommented() throws SevenZipException {
        return (Boolean) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.COMMENTED);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public boolean isEncrypted() throws SevenZipException {
        return ((Boolean) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.ENCRYPTED)).booleanValue();
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public boolean isFolder() throws SevenZipException {
        return ((Boolean) this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().getProperty(this.index, PropID.IS_FOLDER)).booleanValue();
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public ExtractOperationResult extractSlow(ISequentialOutStream iSequentialOutStream) throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().extractSlow(this.index, iSequentialOutStream);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public ExtractOperationResult extractSlow(ISequentialOutStream iSequentialOutStream, String str) throws SevenZipException {
        return this.simpleInArchiveImpl.testAndGetSafeSevenZipInArchive().extractSlow(this.index, iSequentialOutStream, str);
    }

    @Override // net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem
    public int getItemIndex() {
        return this.index;
    }
}
