package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.zziqe;
import com.google.android.gms.vision.face.internal.client.FaceParcel;
import com.google.android.gms.vision.face.internal.client.LandmarkParcel;
import com.google.android.gms.vision.face.internal.client.zzb;
import com.google.android.gms.vision.face.internal.client.zzd;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: dk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0675dk extends AbstractC0672dh {
    public final zzd j;

    public C0675dk(Context context, zzd zzdVar) {
        super(context, "FaceNativeHandle", "face");
        AbstractC1667vh.c(context);
        this.j = zzdVar;
        e();
    }

    @Override // defpackage.AbstractC0672dh
    public final Object b(C1061kg c1061kg, Context context) {
        InterfaceC1009jk interfaceC1009jkV = C1061kg.a(context, "com.google.android.gms.vision.dynamite.face") > C1061kg.h(context, "com.google.android.gms.vision.dynamite", false) ? AbstractBinderC0953ik.v(c1061kg.b("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator")) : AbstractBinderC0953ik.v(c1061kg.b("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        Object c0898hk = null;
        if (interfaceC1009jkV == null) {
            return null;
        }
        BinderC0615cg binderC0615cg = new BinderC0615cg(context);
        zzd zzdVar = this.j;
        C1065kk c1065kk = (C1065kk) interfaceC1009jkV;
        Parcel parcelF = c1065kk.f();
        AbstractC0383Vg.c(parcelF, binderC0615cg);
        AbstractC0383Vg.d(parcelF, zzdVar);
        Parcel parcelC = c1065kk.c(1, parcelF);
        IBinder strongBinder = parcelC.readStrongBinder();
        if (strongBinder != null) {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            c0898hk = iInterfaceQueryLocalInterface instanceof InterfaceC0786fk ? (InterfaceC0786fk) iInterfaceQueryLocalInterface : new C0898hk(strongBinder);
        }
        parcelC.recycle();
        return c0898hk;
    }

    @Override // defpackage.AbstractC0672dh
    public final void c() {
        C0898hk c0898hk = (C0898hk) ((InterfaceC0786fk) e());
        c0898hk.d(3, c0898hk.f());
    }

    public final C0458Zj[] f(ByteBuffer byteBuffer, zziqe zziqeVar) {
        C0563bk[] c0563bkArr;
        C0458Zj[] c0458ZjArr;
        FaceParcel[] faceParcelArr;
        C0440Yj[] c0440YjArr;
        int i = 0;
        if (!a()) {
            return new C0458Zj[0];
        }
        try {
            BinderC0615cg binderC0615cg = new BinderC0615cg(byteBuffer);
            C0898hk c0898hk = (C0898hk) ((InterfaceC0786fk) e());
            Parcel parcelF = c0898hk.f();
            AbstractC0383Vg.c(parcelF, binderC0615cg);
            AbstractC0383Vg.d(parcelF, zziqeVar);
            Parcel parcelC = c0898hk.c(1, parcelF);
            FaceParcel[] faceParcelArr2 = (FaceParcel[]) parcelC.createTypedArray(FaceParcel.CREATOR);
            parcelC.recycle();
            C0458Zj[] c0458ZjArr2 = new C0458Zj[faceParcelArr2.length];
            int i2 = 0;
            while (i2 < faceParcelArr2.length) {
                FaceParcel faceParcel = faceParcelArr2[i2];
                int i3 = faceParcel.t;
                PointF pointF = new PointF(faceParcel.u, faceParcel.v);
                float f = faceParcel.w;
                float f2 = faceParcel.x;
                float f3 = faceParcel.y;
                float f4 = faceParcel.z;
                float f5 = faceParcel.A;
                LandmarkParcel[] landmarkParcelArr = faceParcel.B;
                if (landmarkParcelArr == null) {
                    c0458ZjArr = c0458ZjArr2;
                    faceParcelArr = faceParcelArr2;
                    c0563bkArr = new C0563bk[i];
                } else {
                    c0563bkArr = new C0563bk[landmarkParcelArr.length];
                    int i4 = 0;
                    while (i4 < landmarkParcelArr.length) {
                        LandmarkParcel landmarkParcel = landmarkParcelArr[i4];
                        c0563bkArr[i4] = new C0563bk(new PointF(landmarkParcel.t, landmarkParcel.u), landmarkParcel.v);
                        i4++;
                        faceParcelArr2 = faceParcelArr2;
                        c0458ZjArr2 = c0458ZjArr2;
                        landmarkParcelArr = landmarkParcelArr;
                    }
                    c0458ZjArr = c0458ZjArr2;
                    faceParcelArr = faceParcelArr2;
                }
                zzb[] zzbVarArr = faceParcel.F;
                if (zzbVarArr == null) {
                    c0440YjArr = new C0440Yj[0];
                } else {
                    C0440Yj[] c0440YjArr2 = new C0440Yj[zzbVarArr.length];
                    for (int i5 = 0; i5 < zzbVarArr.length; i5++) {
                        zzb zzbVar = zzbVarArr[i5];
                        c0440YjArr2[i5] = new C0440Yj(zzbVar.s, zzbVar.t);
                    }
                    c0440YjArr = c0440YjArr2;
                }
                c0458ZjArr[i2] = new C0458Zj(i3, pointF, f, f2, f3, f4, f5, c0563bkArr, c0440YjArr, faceParcel.C, faceParcel.D, faceParcel.E, faceParcel.G);
                i2++;
                faceParcelArr2 = faceParcelArr;
                c0458ZjArr2 = c0458ZjArr;
                i = 0;
            }
            return c0458ZjArr2;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new C0458Zj[0];
        }
    }
}
