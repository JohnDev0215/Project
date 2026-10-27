package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.zzipv;
import com.google.android.gms.internal.zziqe;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: renamed from: wL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1708wL implements FL {
    public C0171Jj s;

    public C1708wL(C0490aM c0490aM) {
        int[] iArr = c0490aM.b;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr2 = c0490aM.b;
                if (i2 >= iArr2.length) {
                    break;
                }
                if (iArr2[i2] == 0) {
                    i3 |= 4096;
                } else if (iArr2[i2] == 1) {
                    i3 |= 1;
                } else if (iArr2[i2] == 2) {
                    i3 |= 2;
                } else if (iArr2[i2] == 3) {
                    i3 |= 4;
                } else if (iArr2[i2] == 4) {
                    i3 |= 8;
                } else if (iArr2[i2] == 5) {
                    i3 |= 16;
                } else if (iArr2[i2] == 6) {
                    i3 |= 32;
                } else if (iArr2[i2] == 7) {
                    i3 |= 64;
                } else if (iArr2[i2] == 8) {
                    i3 |= 128;
                } else if (iArr2[i2] == 9) {
                    i3 |= 2048;
                } else if (iArr2[i2] == 10) {
                    i3 |= 256;
                } else if (iArr2[i2] == 12) {
                    i3 |= 512;
                } else if (iArr2[i2] == 13) {
                    i3 |= 1024;
                } else {
                    StringBuilder sbF = Z7.f("Unsupported barcode format hint: ");
                    sbF.append(c0490aM.b[i2]);
                    Ts.a("BarcodeDetectionImpl", sbF.toString(), new Object[0]);
                }
                i2++;
            }
            i = i3;
        }
        Context context = Is.a;
        zzipv zzipvVar = new zzipv();
        zzipvVar.s = i;
        this.s = new C0171Jj(new C0419Xg(context, zzipvVar), null);
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
        this.s.a();
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.s.a();
    }

    @Override // defpackage.FL
    public void u(GM gm, EL el) {
        Barcode[] barcodeArr;
        int i;
        if (!this.s.c.a()) {
            Ts.a("BarcodeDetectionImpl", "BarcodeDetector is not operational", new Object[0]);
            ((WL) el).a(new RL[0]);
            return;
        }
        C0153Ij c0153IjB = AbstractC1818yL.b(gm);
        if (c0153IjB == null) {
            Ts.a("BarcodeDetectionImpl", "Error converting Mojom Bitmap to Frame", new Object[0]);
            ((WL) el).a(new RL[0]);
            return;
        }
        C0171Jj c0171Jj = this.s;
        Objects.requireNonNull(c0171Jj);
        zziqe zziqeVarB = zziqe.b(c0153IjB);
        Bitmap bitmap = c0153IjB.c;
        if (bitmap != null) {
            C0419Xg c0419Xg = c0171Jj.c;
            if (c0419Xg.a()) {
                try {
                    BinderC0615cg binderC0615cg = new BinderC0615cg(bitmap);
                    C0455Zg c0455Zg = (C0455Zg) ((InterfaceC0504ah) c0419Xg.e());
                    Parcel parcelF = c0455Zg.f();
                    AbstractC0383Vg.c(parcelF, binderC0615cg);
                    AbstractC0383Vg.d(parcelF, zziqeVarB);
                    Parcel parcelC = c0455Zg.c(2, parcelF);
                    Barcode[] barcodeArr2 = (Barcode[]) parcelC.createTypedArray(Barcode.CREATOR);
                    parcelC.recycle();
                    barcodeArr = barcodeArr2;
                } catch (RemoteException e) {
                    Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
                    barcodeArr = new Barcode[0];
                }
            } else {
                barcodeArr = new Barcode[0];
            }
            if (barcodeArr == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        } else {
            ByteBuffer byteBufferA = c0153IjB.a();
            C0419Xg c0419Xg2 = c0171Jj.c;
            if (c0419Xg2.a()) {
                try {
                    BinderC0615cg binderC0615cg2 = new BinderC0615cg(byteBufferA);
                    C0455Zg c0455Zg2 = (C0455Zg) ((InterfaceC0504ah) c0419Xg2.e());
                    Parcel parcelF2 = c0455Zg2.f();
                    AbstractC0383Vg.c(parcelF2, binderC0615cg2);
                    AbstractC0383Vg.d(parcelF2, zziqeVarB);
                    Parcel parcelC2 = c0455Zg2.c(1, parcelF2);
                    Barcode[] barcodeArr3 = (Barcode[]) parcelC2.createTypedArray(Barcode.CREATOR);
                    parcelC2.recycle();
                    barcodeArr = barcodeArr3;
                } catch (RemoteException e2) {
                    Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e2);
                    barcodeArr = new Barcode[0];
                }
            } else {
                barcodeArr = new Barcode[0];
            }
        }
        SparseArray sparseArray = new SparseArray(barcodeArr.length);
        for (Barcode barcode : barcodeArr) {
            sparseArray.append(barcode.t.hashCode(), barcode);
        }
        RL[] rlArr = new RL[sparseArray.size()];
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            rlArr[i2] = new RL();
            Barcode barcode2 = (Barcode) sparseArray.valueAt(i2);
            rlArr[i2].b = barcode2.t;
            int iMax = Integer.MIN_VALUE;
            int iMax2 = Integer.MIN_VALUE;
            int iMin = Integer.MAX_VALUE;
            int iMin2 = Integer.MAX_VALUE;
            int i3 = 0;
            while (true) {
                Point[] pointArr = barcode2.w;
                if (i3 < pointArr.length) {
                    Point point = pointArr[i3];
                    iMin = Math.min(iMin, point.x);
                    iMax = Math.max(iMax, point.x);
                    iMin2 = Math.min(iMin2, point.y);
                    iMax2 = Math.max(iMax2, point.y);
                    i3++;
                } else {
                    Rect rect = new Rect(iMin, iMin2, iMax, iMax2);
                    rlArr[i2].c = new SG();
                    rlArr[i2].c.b = rect.left;
                    rlArr[i2].c.c = rect.top;
                    rlArr[i2].c.d = rect.width();
                    rlArr[i2].c.e = rect.height();
                    Point[] pointArr2 = barcode2.w;
                    rlArr[i2].e = new RG[pointArr2.length];
                    for (int i4 = 0; i4 < pointArr2.length; i4++) {
                        rlArr[i2].e[i4] = new RG();
                        rlArr[i2].e[i4].b = pointArr2[i4].x;
                        rlArr[i2].e[i4].c = pointArr2[i4].y;
                    }
                    RL rl = rlArr[i2];
                    int i5 = barcode2.s;
                    if (i5 == 1) {
                        i = 1;
                    } else if (i5 != 2) {
                        switch (i5) {
                            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                                i = 3;
                                break;
                            case 8:
                                i = 4;
                                break;
                            case 16:
                                i = 5;
                                break;
                            case 32:
                                i = 6;
                                break;
                            case 64:
                                i = 7;
                                break;
                            case 128:
                                i = 8;
                                break;
                            case 256:
                                i = 10;
                                break;
                            case 512:
                                i = 12;
                                break;
                            case 1024:
                                i = 13;
                                break;
                            case 2048:
                                i = 9;
                                break;
                            case 4096:
                                i = 0;
                                break;
                            default:
                                i = 11;
                                break;
                        }
                    } else {
                        i = 2;
                    }
                    rl.d = i;
                }
            }
        }
        ((WL) el).a(rlArr);
    }
}
