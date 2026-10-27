package com.neusoft.na.ehp;

/* JADX INFO: loaded from: classes2.dex */
public interface IEhpInfoFetchCallBack {
    void MetaDataMsg(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12);

    void PositionMsg(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    void ProfileLongMsg(int i, boolean z, int i2, int i3, boolean z2, int i4, boolean z3, int i5);

    void ProfileShortMsg(int i, boolean z, int i2, int i3, boolean z2, int i4, boolean z3, int i5, int i6, int i7, int i8);

    void SegmentMsg(int i, boolean z, int i2, int i3, boolean z2, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16);

    void StubMsg(int i, boolean z, int i2, int i3, boolean z2, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, boolean z3);

    void onAdasisV2Message(long j);
}
