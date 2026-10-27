package net.sf.sevenzipjbinding;

import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public interface IOutItemTar extends IOutItemBase {
    String getPropertyGroup();

    String getPropertyHardLink();

    Boolean getPropertyIsDir();

    Date getPropertyLastModificationTime();

    String getPropertyPath();

    Integer getPropertyPosixAttributes();

    String getPropertySymLink();

    String getPropertyUser();

    void setPropertyGroup(String str);

    void setPropertyHardLink(String str);

    void setPropertyIsDir(Boolean bool);

    void setPropertyLastModificationTime(Date date);

    void setPropertyPath(String str);

    void setPropertyPosixAttributes(Integer num);

    void setPropertySymLink(String str);

    void setPropertyUser(String str);
}
