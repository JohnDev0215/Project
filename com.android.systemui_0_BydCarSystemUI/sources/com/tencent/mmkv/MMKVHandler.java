package com.tencent.mmkv;

/* JADX INFO: loaded from: classes2.dex */
public interface MMKVHandler {
    void mmkvLog(MMKVLogLevel level, String file, int line, String function, String message);

    MMKVRecoverStrategic onMMKVCRCCheckFail(String mmapID);

    MMKVRecoverStrategic onMMKVFileLengthError(String mmapID);

    boolean wantLogRedirecting();
}
