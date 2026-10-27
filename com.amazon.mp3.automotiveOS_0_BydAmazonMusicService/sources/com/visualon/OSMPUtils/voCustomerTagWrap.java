package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public interface voCustomerTagWrap {

    public interface onID3Listener {
        long onGetPos();

        int onSendID3Event(int i, int i2, int i3, Object obj);
    }

    void addID3(voOSTimedTag voostimedtag);

    void check(long j);

    boolean isEmpty();

    void reset();
}
