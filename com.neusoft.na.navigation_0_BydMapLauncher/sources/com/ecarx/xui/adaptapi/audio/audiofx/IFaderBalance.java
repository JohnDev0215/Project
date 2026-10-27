package com.ecarx.xui.adaptapi.audio.audiofx;

import com.ecarx.xui.adaptapi.FunctionStatus;

/* JADX INFO: loaded from: classes2.dex */
public interface IFaderBalance {
    short getBalanceLevel();

    short[] getBalanceLevelRange();

    short getFaderLevel();

    short[] getFaderLevelRange();

    FunctionStatus isBalanceSupported();

    FunctionStatus isFaderSupported();

    void setBalanceLevel(short s);

    void setFaderLevel(short s);
}
