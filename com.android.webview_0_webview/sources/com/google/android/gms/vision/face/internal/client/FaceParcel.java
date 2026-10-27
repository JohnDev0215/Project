package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0730ek;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FaceParcel extends zza {
    public static final Parcelable.Creator CREATOR = new C0730ek();
    public final float A;
    public final LandmarkParcel[] B;
    public final float C;
    public final float D;
    public final float E;
    public final zzb[] F;
    public final float G;
    public final int s;
    public final int t;
    public final float u;
    public final float v;
    public final float w;
    public final float x;
    public final float y;
    public final float z;

    public FaceParcel(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, LandmarkParcel[] landmarkParcelArr, float f8, float f9, float f10, zzb[] zzbVarArr, float f11) {
        this.s = i;
        this.t = i2;
        this.u = f;
        this.v = f2;
        this.w = f3;
        this.x = f4;
        this.y = f5;
        this.z = f6;
        this.A = f7;
        this.B = landmarkParcelArr;
        this.C = f8;
        this.D = f9;
        this.E = f10;
        this.F = zzbVarArr;
        this.G = f11;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 1, this.s);
        AbstractC1116lf.l(parcel, 2, this.t);
        AbstractC1116lf.a(parcel, 3, this.u);
        AbstractC1116lf.a(parcel, 4, this.v);
        AbstractC1116lf.a(parcel, 5, this.w);
        AbstractC1116lf.a(parcel, 6, this.x);
        AbstractC1116lf.a(parcel, 7, this.y);
        AbstractC1116lf.a(parcel, 8, this.z);
        AbstractC1116lf.g(parcel, 9, this.B, i);
        AbstractC1116lf.a(parcel, 10, this.C);
        AbstractC1116lf.a(parcel, 11, this.D);
        AbstractC1116lf.a(parcel, 12, this.E);
        AbstractC1116lf.g(parcel, 13, this.F, i);
        AbstractC1116lf.a(parcel, 14, this.A);
        AbstractC1116lf.a(parcel, 15, this.G);
        AbstractC1116lf.j(parcel, iN);
    }
}
