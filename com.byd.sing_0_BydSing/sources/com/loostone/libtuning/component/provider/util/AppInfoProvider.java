package com.loostone.libtuning.component.provider.util;

import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.b.a;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.blankj.utilcode.util.j;
import com.loostone.libtuning.BuildConfig;
import com.loostone.libtuning.channel.byd.old.PuremicTuning;
import com.loostone.libtuning.component.provider.TuningProvider;
import com.loostone.libtuning.data.bean.TuningInfo;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class AppInfoProvider {
    private static final int GET_CHANNEL_ID = 106;
    private static final int GET_CONFIG_CONTENT = 110;
    private static final int GET_ENVIRONMENTAL_SOUND = 112;
    private static final int GET_LIB_TUNING_BUILD_TIME = 102;
    private static final int GET_LIB_TUNING_VERSION = 100;
    private static final int GET_MODEL_ID = 108;
    private static final int GET_SO_VERSION = 104;
    private static final int GET_TUNING_INFO = 114;
    public static final AppInfoProvider INSTANCE = new AppInfoProvider();
    private static final String TAG = "AppInfoProvider";

    private AppInfoProvider() {
    }

    public final Cursor getValue(int i) throws Throwable {
        String strF;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{TuningProvider.KEY, TuningProvider.VALUE});
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        if (i == 100) {
            strF = BuildConfig.VERSION_NAME;
        } else if (i == 102) {
            PuremicTuning puremicTuning = PuremicTuning.getInstance();
            strF = "2025-03-25 18:00:02 H:" + puremicTuning.isHardEffect() + "\nDF:" + ((Object) puremicTuning.getVersion(11)) + " M1F:" + ((Object) puremicTuning.getVersion(12)) + " M2F:" + ((Object) puremicTuning.getVersion(13)) + "\nDH:" + ((Object) puremicTuning.getVersion(21)) + " H1F:" + ((Object) puremicTuning.getVersion(22)) + " M2F:" + ((Object) puremicTuning.getVersion(23));
        } else if (i == 104) {
            strF = OooO0O0.f531a.f();
        } else if (i == 106) {
            strF = String.valueOf(OooO0o.f425a.j().a());
        } else if (i == 108) {
            strF = String.valueOf(OooO0o.f425a.z().a());
        } else if (i == 110) {
            OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0 oooO0O0 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a;
            OooO0o oooO0o = OooO0o.f425a;
            Long lA = oooO0o.j().a();
            h.b(lA);
            long jLongValue = lA.longValue();
            Integer numA = oooO0o.z().a();
            h.b(numA);
            strF = oooO0O0.f(jLongValue, numA.intValue());
        } else if (i == 112) {
            a aVar = a.f469a;
            OooO0o oooO0o2 = OooO0o.f425a;
            Long lA2 = oooO0o2.j().a();
            h.b(lA2);
            long jLongValue2 = lA2.longValue();
            Integer numA2 = oooO0o2.z().a();
            h.b(numA2);
            strF = aVar.a(jLongValue2, numA2.intValue());
        } else if (i != 114) {
            strF = "null";
        } else {
            strF = j.i(new TuningInfo());
            h.c(strF, "{\n                GsonUtils.toJson(TuningInfo())\n            }");
        }
        rowBuilderNewRow.add(TuningProvider.KEY, Integer.valueOf(i));
        rowBuilderNewRow.add(TuningProvider.VALUE, strF);
        b.f41a.e(TAG, "get  keyIndex: " + i + " value: " + strF);
        return matrixCursor;
    }

    public final int setValue(int i, int i2) {
        b.f41a.e(TAG, "set  keyIndex: " + i + " value: " + i2);
        return 1;
    }
}
