package com.loostone.libtuning.component.provider.util;

import OooO00o.OooO00o.OooO0O0.b;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.loostone.libtuning.PuremicTuning;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.component.provider.TuningProvider;
import kotlin.a;
import kotlin.d;

/* JADX INFO: loaded from: classes.dex */
public final class MicInfoProvider {
    private static final int GET_DONGLE_STATUS = 101;
    private static final int GET_MIC_STATUS = 100;
    private static final String TAG = "MicInfoProvider";
    public static final MicInfoProvider INSTANCE = new MicInfoProvider();
    private static final a fullSceneSpeakMgr$delegate = d.b(new kotlin.jvm.b.a<BaseChannelMgr>() { // from class: com.loostone.libtuning.component.provider.util.MicInfoProvider$fullSceneSpeakMgr$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.b.a
        public final BaseChannelMgr invoke() {
            return PuremicTuning.Companion.getInstance().getChannelMgr();
        }
    });

    private MicInfoProvider() {
    }

    private final BaseChannelMgr getFullSceneSpeakMgr() {
        return (BaseChannelMgr) fullSceneSpeakMgr$delegate.getValue();
    }

    public final Cursor getValue(int i) {
        int verifyStatus;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{TuningProvider.KEY, TuningProvider.VALUE});
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        if (i == 100) {
            verifyStatus = getFullSceneSpeakMgr().getVerifyStatus();
            if (verifyStatus == 2) {
                verifyStatus = getFullSceneSpeakMgr().getPowerStatus();
            }
        } else {
            verifyStatus = 0;
        }
        rowBuilderNewRow.add(TuningProvider.KEY, Integer.valueOf(i));
        rowBuilderNewRow.add(TuningProvider.VALUE, String.valueOf(verifyStatus));
        b.f41a.e(TAG, "get  keyIndex: " + i + " value: " + verifyStatus);
        return matrixCursor;
    }

    public final int setValue(int i, int i2) {
        b.f41a.e(TAG, "set  keyIndex: " + i + " value: " + i2);
        return 1;
    }
}
