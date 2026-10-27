package net.sf.sevenzipjbinding;

import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public interface IOutItem7z extends IOutItemBase {
    Integer getPropertyAttributes();

    Boolean getPropertyIsAnti();

    Boolean getPropertyIsDir();

    Date getPropertyLastModificationTime();

    String getPropertyPath();

    void setPropertyAttributes(Integer num);

    void setPropertyIsAnti(Boolean bool);

    void setPropertyIsDir(Boolean bool);

    void setPropertyLastModificationTime(Date date);

    void setPropertyPath(String str);
}
