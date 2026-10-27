package com.byd.dilink.app.am;

import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkParceledListSlice<T extends Parcelable> {
    int describeContents();

    List<T> getList();

    void setInlineCountLimit(int i);
}
