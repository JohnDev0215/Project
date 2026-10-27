package com.ts.carplay.lib.common;

import com.ts.carplay.lib.common.CpConst;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayStartInfo {
    public CpConst.CarPlayTriState limitedUi;
    public int mainAudioTransfer;
    public CpConst.CarPlayTriState nightMode;
    public CpConst.CarPlayTriState phoneActive;
    public int screenTransfer;
    public CpConst.CarPlaySpeechMode speechMode;
    public CpConst.CarPlayTriState turnByturnActive;
    public CarPlayResourceStateChange mainAudioTransferInfo = new CarPlayResourceStateChange();
    public CarPlayResourceStateChange screenTransferInfo = new CarPlayResourceStateChange();
}
