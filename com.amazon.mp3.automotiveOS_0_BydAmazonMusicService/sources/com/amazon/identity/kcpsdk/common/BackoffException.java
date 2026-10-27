package com.amazon.identity.kcpsdk.common;

import com.amazon.identity.auth.device.bd;
import java.io.IOException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class BackoffException extends IOException {
    private final bd mBackoffInfo;
    private final int mErrorCode;
    private final String mErrorMsg;

    public BackoffException(int i, String str, bd bdVar) {
        super(str);
        this.mErrorCode = i;
        this.mErrorMsg = str;
        this.mBackoffInfo = bdVar;
    }
}
