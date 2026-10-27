package com.byd.data.common.present;

import android.os.Environment;
import android.util.Log;
import com.byd.data.common.b.a;
import com.byd.data.common.b.b;
import com.byd.data.common.b.c;
import com.byd.data.common.c.d.d;
import com.byd.data.common.entiry.extension.ExtensionItem;
import com.byd.data.common.i.f;
import f.a0;
import f.f0;
import f.z;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: loaded from: classes.dex */
public class MainTestPresent extends a<d> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final byte[] f3444e;

    /* JADX INFO: renamed from: com.byd.data.common.present.MainTestPresent$1, reason: invalid class name */
    class AnonymousClass1 extends c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ MainTestPresent f3445e;

        @Override // com.byd.data.common.b.c
        public void c(String str) {
        }

        @Override // com.byd.data.common.b.c
        public void j(b bVar) {
            Object result = bVar.getResult();
            Log.i(((a) this.f3445e).f3290a, " result = " + result);
        }
    }

    public MainTestPresent(d dVar) {
        super(dVar);
        int[] iArr = {241, 25, 220, 203, 112, 143, 67, 175, 134, 175, 249, 206, 11, 148, 67, 24, 241, 25, 220, 203, 112, 143, 67, 175, 134, 175, 249, 206, 11, 148, 67, 24};
        this.f3444e = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            this.f3444e[i2] = (byte) (iArr[i2] & 255);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v4, types: [int] */
    public void e() {
        com.byd.data.common.server.e.a aVarF = com.byd.data.common.g.a.i().f();
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + File.separator + "gb_ex00";
        List<ExtensionItem> list = com.byd.data.common.server.b.e().g().evExtData.extList;
        try {
            BufferedSource bufferedSourceC = Okio.c(Okio.j(new FileInputStream(str)));
            try {
                byte[] bArrA = bufferedSourceC.A();
                int i2 = 22;
                boolean z = false;
                String str2 = null;
                int i3 = 22;
                int i4 = 0;
                while (i4 < bArrA.length) {
                    int i5 = (short) (((bArrA[i3] & 255) << 8) | (bArrA[i3 + 1] & 255));
                    byte[] bArr = new byte[i5];
                    System.arraycopy(bArrA, i3 + 2, bArr, z ? 1 : 0, i5);
                    i3 += i5 + 3 + i2;
                    if (str2 == null) {
                        str2 = new String(bArrA, 4, 17);
                    }
                    f.g(this.f3290a, "file first vin =" + str2);
                    byte[] bArrJ = aVarF.j(bArr, str2);
                    String strG = c.a.a.g.d.g(bArrJ, z);
                    byte b2 = bArrJ[6];
                    boolean z2 = true;
                    if (bArrJ[3] != 1) {
                        z2 = z ? 1 : 0;
                    }
                    byte b3 = (byte) (z2 ? 87 : 93);
                    int i6 = 16;
                    int i7 = z ? 1 : 0;
                    boolean z3 = z;
                    while (i7 < b2) {
                        com.byd.data.common.server.e.a aVar = aVarF;
                        for (?? r6 = z3; r6 < list.size(); r6++) {
                            ExtensionItem extensionItem = list.get(r6);
                            List<ExtensionItem> list2 = list;
                            f.g(this.f3290a, "canId =" + Integer.toHexString(extensionItem.canId) + ", " + c.a.a.g.d.d(new String(bArrJ, i6, (int) extensionItem.size)));
                            list = list2;
                            bArrA = bArrA;
                            str2 = str2;
                        }
                        i6 += b3;
                        i7++;
                        aVarF = aVar;
                        z3 = false;
                    }
                    com.byd.data.common.server.e.a aVar2 = aVarF;
                    List<ExtensionItem> list3 = list;
                    byte[] bArr2 = bArrA;
                    String str3 = str2;
                    f.g(this.f3290a, "packNum =" + ((int) b2) + " flen=" + ((int) b3) + " totalLen =" + bArrJ.length);
                    String str4 = this.f3290a;
                    StringBuilder sb = new StringBuilder();
                    sb.append(" len  = ");
                    sb.append(i5);
                    sb.append(", read decrypt= ");
                    sb.append(strG);
                    f.g(str4, sb.toString());
                    i4 += i3;
                    aVarF = aVar2;
                    list = list3;
                    bArrA = bArr2;
                    str2 = str3;
                    i2 = 22;
                    z = false;
                }
                bufferedSourceC.close();
            } finally {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void f(boolean z, File file) {
        if (com.byd.data.common.i.j.b.b()) {
            File file2 = new File(file, z ? "cloud/period/" : "cloud/event/");
            File[] fileArrListFiles = file2.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                d.a.a.f.b.b.a.h(this.f3290a, file2.getPath() + "uploadFileTest fail, no file");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (File file3 : fileArrListFiles) {
                if (!file3.isDirectory()) {
                    arrayList.add(a0.b.d("files", file3.getName(), f0.c(z.d("application/zip"), file3)));
                }
            }
            a(this.f3293d.d(arrayList), new c(this.f3292c) { // from class: com.byd.data.common.present.MainTestPresent.2
                @Override // com.byd.data.common.b.c
                public void c(String str) {
                    Log.i(((a) MainTestPresent.this).f3290a, " onError  msg = " + str);
                }

                @Override // com.byd.data.common.b.c
                public void j(b bVar) {
                    Log.i(((a) MainTestPresent.this).f3290a, " result = " + bVar);
                }
            });
        }
    }
}
