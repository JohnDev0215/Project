package com.ts.carplay.linkadapter;

import android.os.HandlerThread;
import android.os.Looper;
import com.ts.carplay.linkadapter.iap.IIapAdapter;
import com.ts.carplay.linkadapter.iap.IapAdapter;
import com.ts.carplay.linkadapter.link.ILinkAdapter;
import com.ts.carplay.linkadapter.link.LinkAdapter;

/* JADX INFO: loaded from: classes.dex */
public class ProprietaryAdapterFactory {
    private static final String THREAD_NAME = "ProprietaryAdapterHandler";
    private static HandlerThread sHandlerThread;
    private static volatile IIapAdapter sIapAdapter;
    private static volatile ILinkAdapter sLinkAdapter;
    private static Looper sLooper;

    public static IIapAdapter getIapAdapter() {
        if (sIapAdapter == null && sIapAdapter == null) {
            sIapAdapter = new IapAdapter(getLooper());
        }
        return sIapAdapter;
    }

    public static ILinkAdapter getLinkAdapter() {
        if (sLinkAdapter == null && sLinkAdapter == null) {
            sLinkAdapter = new LinkAdapter(getLooper());
        }
        return sLinkAdapter;
    }

    public static synchronized Looper getLooper() {
        if (sHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
            sHandlerThread = handlerThread;
            handlerThread.start();
            sLooper = sHandlerThread.getLooper();
        }
        return sLooper;
    }
}
