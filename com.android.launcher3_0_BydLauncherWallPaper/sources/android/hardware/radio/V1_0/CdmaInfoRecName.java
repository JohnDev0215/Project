package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CdmaInfoRecName {
    public static final int CALLED_PARTY_NUMBER = 1;
    public static final int CALLING_PARTY_NUMBER = 2;
    public static final int CONNECTED_NUMBER = 3;
    public static final int DISPLAY = 0;
    public static final int EXTENDED_DISPLAY = 7;
    public static final int LINE_CONTROL = 6;
    public static final int REDIRECTING_NUMBER = 5;
    public static final int SIGNAL = 4;
    public static final int T53_AUDIO_CONTROL = 10;
    public static final int T53_CLIR = 8;
    public static final int T53_RELEASE = 9;

    public static final String toString(int i) {
        return i == 0 ? "DISPLAY" : i == 1 ? "CALLED_PARTY_NUMBER" : i == 2 ? "CALLING_PARTY_NUMBER" : i == 3 ? "CONNECTED_NUMBER" : i == 4 ? "SIGNAL" : i == 5 ? "REDIRECTING_NUMBER" : i == 6 ? "LINE_CONTROL" : i == 7 ? "EXTENDED_DISPLAY" : i == 8 ? "T53_CLIR" : i == 9 ? "T53_RELEASE" : i == 10 ? "T53_AUDIO_CONTROL" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("DISPLAY");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CALLED_PARTY_NUMBER");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CALLING_PARTY_NUMBER");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CONNECTED_NUMBER");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SIGNAL");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("REDIRECTING_NUMBER");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("LINE_CONTROL");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("EXTENDED_DISPLAY");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("T53_CLIR");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("T53_RELEASE");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("T53_AUDIO_CONTROL");
            i2 |= 10;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
