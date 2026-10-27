package net.sf.sevenzipjbinding;

/* JADX INFO: loaded from: classes3.dex */
public interface IOutItemBase {
    ArchiveFormat getArchiveFormat();

    Long getDataSize();

    int getIndex();

    IOutArchive<?> getOutArchive();

    Boolean getUpdateIsNewData();

    Boolean getUpdateIsNewProperties();

    Integer getUpdateOldArchiveItemIndex();

    void setDataSize(Long l);

    void setUpdateIsNewData(Boolean bool);

    void setUpdateIsNewProperties(Boolean bool);

    void setUpdateOldArchiveItemIndex(Integer num);
}
