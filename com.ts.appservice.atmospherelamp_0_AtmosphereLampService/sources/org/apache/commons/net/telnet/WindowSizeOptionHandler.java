package org.apache.commons.net.telnet;

/* JADX INFO: loaded from: classes3.dex */
public class WindowSizeOptionHandler extends TelnetOptionHandler {
    protected static final int WINDOW_SIZE = 31;
    private int height;
    private int width;

    public WindowSizeOptionHandler(int nWidth, int nHeight, boolean initlocal, boolean initremote, boolean acceptlocal, boolean acceptremote) {
        super(31, initlocal, initremote, acceptlocal, acceptremote);
        this.width = 80;
        this.height = 24;
        this.width = nWidth;
        this.height = nHeight;
    }

    public WindowSizeOptionHandler(int nWidth, int nHeight) {
        super(31, false, false, false, false);
        this.width = 80;
        this.height = 24;
        this.width = nWidth;
        this.height = nHeight;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationLocal() {
        int i = this.width;
        int nCompoundWindowSize = (65536 * i) + this.height;
        int nResponseSize = 5;
        if (i % 256 == 255) {
            nResponseSize = 5 + 1;
        }
        if (this.width / 256 == 255) {
            nResponseSize++;
        }
        if (this.height % 256 == 255) {
            nResponseSize++;
        }
        if (this.height / 256 == 255) {
            nResponseSize++;
        }
        int[] response = new int[nResponseSize];
        response[0] = 31;
        int nIndex = 1;
        int nShift = 24;
        while (nIndex < nResponseSize) {
            int nTurnedOnBits = 255 << nShift;
            response[nIndex] = (nCompoundWindowSize & nTurnedOnBits) >>> nShift;
            if (response[nIndex] == 255) {
                nIndex++;
                response[nIndex] = 255;
            }
            nIndex++;
            nShift -= 8;
        }
        return response;
    }
}
