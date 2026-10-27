package defpackage;

import J.N;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import defpackage.cp3;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.webapps.WebApkUpdateManager;

/* JADX INFO: compiled from: WebApkUpdateManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cq3 extends i9<Pair<String, String>> {
    public final /* synthetic */ ls3 h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ String k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ boolean m;
    public final /* synthetic */ List n;
    public final /* synthetic */ Callback o;
    public final /* synthetic */ WebApkUpdateManager p;

    public cq3(WebApkUpdateManager webApkUpdateManager, ls3 ls3Var, String str, String str2, String str3, boolean z, boolean z2, List list, l51 l51Var) {
        this.p = webApkUpdateManager;
        this.h = ls3Var;
        this.i = str;
        this.j = str2;
        this.k = str3;
        this.l = z;
        this.m = z2;
        this.n = list;
        this.o = l51Var;
    }

    @Override // defpackage.i9
    public final Pair<String, String> b() {
        ls3 ls3Var = this.h;
        return Pair.create(ls3Var.g().b(), ls3Var.e().b.b());
    }

    @Override // defpackage.i9
    public final void l(Pair<String, String> pair) {
        String[] strArr;
        byte[][] bArr;
        String[] strArr2;
        String[][] strArr3;
        String str;
        String str2;
        String str3;
        boolean z;
        boolean z2;
        Pair<String, String> pair2 = pair;
        String str4 = this.i;
        String str5 = this.j;
        String str6 = (String) pair2.first;
        String str7 = this.k;
        String str8 = (String) pair2.second;
        boolean z3 = this.l;
        boolean z4 = this.m;
        Callback callback = this.o;
        this.p.getClass();
        ls3 ls3Var = this.h;
        int i = ls3Var.e().n;
        int size = ls3Var.h().size();
        String[] strArr4 = new String[size];
        String[] strArr5 = new String[size];
        Iterator<Map.Entry<String, String>> it = ls3Var.h().entrySet().iterator();
        int i2 = 0;
        while (true) {
            String str9 = "";
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, String> next = it.next();
            strArr4[i2] = next.getKey();
            String value = next.getValue();
            if (value != null) {
                str9 = value;
            }
            strArr5[i2] = str9;
            i2++;
        }
        String strN = TextUtils.isEmpty(ls3Var.m()) ? ls3Var.n() : ls3Var.m();
        String[][] strArr6 = new String[ls3Var.s().size()][];
        byte[][] bArr2 = new byte[ls3Var.s().size()][];
        int i3 = 0;
        while (true) {
            strArr = strArr5;
            if (i3 >= ls3Var.s().size()) {
                break;
            }
            cp3.a aVar = ls3Var.s().get(i3);
            String[] strArr7 = strArr4;
            int i4 = i;
            Callback callback2 = callback;
            strArr6[i3] = new String[]{aVar.a, aVar.b, aVar.c, aVar.d, aVar.e};
            ks3 ks3Var = aVar.f;
            byte[] bArr3 = ks3Var.a;
            bArr2[i3] = bArr3 != null ? bArr3 : Base64.decode(ks3Var.b(), 0);
            i3++;
            strArr5 = strArr;
            strArr4 = strArr7;
            i = i4;
            callback = callback2;
        }
        String[] strArr8 = strArr4;
        int i5 = i;
        Callback callback3 = callback;
        String[] strArr9 = new String[0];
        String[][] strArr10 = new String[0][];
        up3 up3Var = ls3Var.e().k;
        if (up3Var != null) {
            String[] strArr11 = up3Var.a;
            String str10 = strArr11[0];
            String str11 = strArr11[1];
            String str12 = strArr11[2];
            boolean z5 = up3Var.b;
            bArr = bArr2;
            boolean z6 = up3Var.c;
            strArr2 = up3Var.d;
            str3 = str12;
            str = str10;
            z = z5;
            strArr3 = up3Var.e;
            str2 = str11;
            z2 = z6;
        } else {
            bArr = bArr2;
            strArr2 = strArr9;
            strArr3 = strArr10;
            str = "";
            str2 = str;
            str3 = str2;
            z = false;
            z2 = false;
        }
        List list = this.n;
        int[] iArr = new int[list.size()];
        for (int i6 = 0; i6 < list.size(); i6++) {
            iArr[i6] = ((Integer) list.get(i6)).intValue();
        }
        N.MBaSXT5X(str4, ls3Var.n(), ls3Var.q(), ls3Var.p(), ls3Var.r(), strN, ls3Var.a(), str5, str6, ls3Var.k(), str7, str8, ls3Var.e().c, strArr8, strArr, ls3Var.d(), ls3Var.f().h, ls3Var.t(), ls3Var.b(), str, str2, str3, z, z2, strArr2, strArr3, strArr6, bArr, ls3Var.o(), ls3Var.u(), i5, z3, z4, iArr, callback3);
    }
}
