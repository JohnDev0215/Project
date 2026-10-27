package com.android.bluetooth.avrcpcontroller;

import android.util.SparseArray;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class BipEncoding {
    public static final int BMP = 2;
    public static final int GIF = 3;
    public static final int JPEG = 0;
    public static final int JPEG2000 = 4;
    public static final int PNG = 1;
    public static final int UNKNOWN = 7;
    public static final int USR_XXX = 6;
    public static final int WBMP = 5;
    private static final HashMap sEncodingNamesToIds;
    private static final SparseArray sIdsToEncodingNames;
    private final String mProprietaryEncodingId;
    private final int mType;

    static {
        HashMap map = new HashMap();
        sEncodingNamesToIds = map;
        map.put(AvrcpCoverArtManager.MIMETYPE_DEFAULT, 0);
        sEncodingNamesToIds.put("GIF", 3);
        sEncodingNamesToIds.put("WBMP", 5);
        sEncodingNamesToIds.put("PNG", 1);
        sEncodingNamesToIds.put("JPEG2000", 4);
        sEncodingNamesToIds.put("BMP", 2);
        SparseArray sparseArray = new SparseArray();
        sIdsToEncodingNames = sparseArray;
        sparseArray.put(0, AvrcpCoverArtManager.MIMETYPE_DEFAULT);
        sIdsToEncodingNames.put(3, "GIF");
        sIdsToEncodingNames.put(5, "WBMP");
        sIdsToEncodingNames.put(1, "PNG");
        sIdsToEncodingNames.put(4, "JPEG2000");
        sIdsToEncodingNames.put(2, "BMP");
        sIdsToEncodingNames.put(7, "UNKNOWN");
    }

    public BipEncoding(String encoding) {
        if (encoding == null) {
            throw new ParseException("Encoding input invalid");
        }
        String encoding2 = encoding.trim();
        int iDetermineEncoding = determineEncoding(encoding2.toUpperCase());
        this.mType = iDetermineEncoding;
        String proprietaryEncodingId = iDetermineEncoding == 6 ? encoding2.substring(4).toUpperCase() : null;
        this.mProprietaryEncodingId = proprietaryEncodingId;
        if (this.mType == 7) {
            throw new ParseException("Failed to determine type of '" + encoding2 + "'");
        }
    }

    public BipEncoding(int encoding, String proprietaryId) {
        if (encoding < 0 || encoding > 6) {
            throw new IllegalArgumentException("Received invalid encoding type '" + encoding + "'");
        }
        this.mType = encoding;
        String proprietaryEncodingId = null;
        if (encoding == 6) {
            if (proprietaryId == null) {
                throw new IllegalArgumentException("Received invalid user defined encoding id '" + proprietaryId + "'");
            }
            proprietaryEncodingId = proprietaryId.toUpperCase();
        }
        this.mProprietaryEncodingId = proprietaryEncodingId;
    }

    public BipEncoding(int encoding) {
        this(encoding, null);
    }

    public int getType() {
        return this.mType;
    }

    public String getProprietaryEncodingId() {
        return this.mProprietaryEncodingId;
    }

    public boolean isAndroidSupported() {
        int i = this.mType;
        return i == 0 || i == 1 || i == 2 || i == 3;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int getEncodingTypeFromString(String StringType) {
        byte b = -1;
        if (StringType == null) {
            return -1;
        }
        String upperCase = StringType.toUpperCase();
        switch (upperCase.hashCode()) {
            case 65893:
                if (upperCase.equals("BMP")) {
                    b = 2;
                }
                break;
            case 70564:
                if (upperCase.equals("GIF")) {
                    b = 3;
                }
                break;
            case 79369:
                if (upperCase.equals("PNG")) {
                    b = 1;
                }
                break;
            case 2283624:
                if (upperCase.equals(AvrcpCoverArtManager.MIMETYPE_DEFAULT)) {
                    b = 0;
                }
                break;
            case 2657710:
                if (upperCase.equals("WBMP")) {
                    b = 5;
                }
                break;
            case 147314982:
                if (upperCase.equals("JPEG2000")) {
                    b = 4;
                }
                break;
        }
        if (b == 0) {
            return 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            return 2;
        }
        if (b == 3) {
            return 3;
        }
        if (b != 4) {
            return b != 5 ? 7 : 5;
        }
        return 4;
    }

    private static int determineEncoding(String encoding) {
        Integer type = (Integer) sEncodingNamesToIds.get(encoding);
        if (type != null) {
            return type.intValue();
        }
        if (encoding != null && encoding.length() >= 4 && encoding.substring(0, 4).equals("USR-")) {
            return 6;
        }
        return 7;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BipEncoding)) {
            return false;
        }
        BipEncoding e = (BipEncoding) o;
        return e.getType() == getType() && e.getProprietaryEncodingId() == getProprietaryEncodingId();
    }

    public String toString() {
        int i = this.mType;
        if (i != 6) {
            String encoding = (String) sIdsToEncodingNames.get(i);
            return encoding;
        }
        return "USR-" + this.mProprietaryEncodingId;
    }
}
