package com.android.bluetooth.mapclient;

import java.io.IOException;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
class RequestSetPath extends Request {
    SetPathDir mDir;
    String mName;

    enum SetPathDir {
        ROOT,
        UP,
        DOWN
    }

    RequestSetPath(String name) {
        this.mDir = SetPathDir.DOWN;
        this.mName = name;
        this.mHeaderSet.setHeader(1, name);
    }

    RequestSetPath(boolean goRoot) {
        this.mHeaderSet.setEmptyNameHeader();
        if (goRoot) {
            this.mDir = SetPathDir.ROOT;
        } else {
            this.mDir = SetPathDir.UP;
        }
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) {
        HeaderSet hs = null;
        try {
            int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$mapclient$RequestSetPath$SetPathDir[this.mDir.ordinal()];
            if (i == 1 || i == 2) {
                hs = session.setPath(this.mHeaderSet, false, false);
            } else if (i == 3) {
                hs = session.setPath(this.mHeaderSet, true, false);
            }
            this.mResponseCode = hs.getResponseCode();
        } catch (IOException e) {
            this.mResponseCode = 208;
        }
    }

    /* JADX INFO: renamed from: com.android.bluetooth.mapclient.RequestSetPath$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$mapclient$RequestSetPath$SetPathDir;

        static {
            int[] iArr = new int[SetPathDir.values().length];
            $SwitchMap$com$android$bluetooth$mapclient$RequestSetPath$SetPathDir = iArr;
            try {
                iArr[SetPathDir.ROOT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$RequestSetPath$SetPathDir[SetPathDir.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$bluetooth$mapclient$RequestSetPath$SetPathDir[SetPathDir.UP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }
}
