package com.byd.dilink.os;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkBootBusinessManager {
    void addListener(IDiLinkBootBusinessListener iDiLinkBootBusinessListener);

    void entryBusiness(String str);

    void entrySubBusiness(String str);

    void exitBusiness(String str);

    void exitSubBusiness(String str);

    void removeListener(IDiLinkBootBusinessListener iDiLinkBootBusinessListener);
}
