package com.google.android.gms.vision.text.internal.client;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.AbstractC1116lf;
import defpackage.C1670vk;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RecognitionOptions extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator CREATOR = new C1670vk();
    public final Rect s;

    public RecognitionOptions(Rect rect) {
        this.s = rect;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.d(parcel, 2, this.s, i, false);
        AbstractC1116lf.j(parcel, iN);
    }
}
