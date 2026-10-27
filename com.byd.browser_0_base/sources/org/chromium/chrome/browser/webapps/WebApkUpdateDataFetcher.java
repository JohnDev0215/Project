package org.chromium.chrome.browser.webapps;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import defpackage.cp3;
import defpackage.hp3;
import defpackage.ks3;
import defpackage.ls3;
import defpackage.nh0;
import defpackage.up3;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.webapk.lib.common.R$color;

/* JADX INFO: loaded from: classes2.dex */
public class WebApkUpdateDataFetcher extends nh0 {
    public long e;
    public Tab f;
    public ls3 g;
    public a h;

    public interface a {
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        N.M_a8xBBL(this.e, this, this.f.c());
    }

    @CalledByNative
    public void onDataAvailable(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Bitmap bitmap, boolean z, String str9, String str10, Bitmap bitmap2, boolean z2, String[] strArr, int i, int i2, long j, long j2, String str11, String str12, String str13, boolean z3, boolean z4, String[] strArr2, String[][] strArr3, String[][] strArr4, byte[][] bArr) {
        Context context = xz.a;
        HashMap map = new HashMap();
        int length = strArr.length;
        int i3 = 0;
        while (true) {
            String str14 = null;
            if (i3 >= length) {
                break;
            }
            String str15 = strArr[i3];
            if (str15.equals(str7)) {
                str14 = str8;
            } else if (str15.equals(str9)) {
                str14 = str10;
            }
            map.put(str15, str14);
            i3++;
        }
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < strArr4.length; i4++) {
            String[] strArr5 = strArr4[i4];
            arrayList.add(new cp3.a(strArr5[0], strArr5[1], strArr5[2], strArr5[3], strArr5[4], new ks3(bArr[i4])));
        }
        ((WebApkUpdateManager) this.h).d(hp3.c(new Intent(), this.g.f().b, str2, new ks3(bitmap), new ks3(bitmap2), str3, str4, i, i2, this.g.f().i, j, j2, context.getResources().getColor(R$color.webapp_default_bg, null), z, z2, this.g.u(), this.g.e().d, str5, str, str6, this.g.a(), 0, map, !TextUtils.isEmpty(str11) ? new up3(str11, str12, str13, z3, z4, strArr2, strArr3) : null, this.g.f().n, this.g.e().l, null, arrayList, this.g.e().n), str7, str9);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        N.M_a8xBBL(this.e, this, this.f.c());
    }
}
