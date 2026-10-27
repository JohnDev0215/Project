package com.android.car.broadcastradio.support.platform;

import android.app.job.JobInfo;
import android.hardware.radio.ProgramSelector;
import android.net.Uri;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: classes4.dex */
public class ProgramSelectorExt {
    public static final int NAME_MODULATION_ONLY = 2;
    public static final int NAME_NO_MODULATION = 1;
    public static final int NAME_NO_PROGRAM_TYPE_FALLBACK = 4;
    private static final String TAG = "BcRadioApp.pselext";
    private static final String URI_AUTHORITY_PROGRAM = "program";
    private static final String URI_HEX_PREFIX = "0x";
    private static final String URI_SCHEME_BROADCASTRADIO = "broadcastradio";
    private static final String URI_VENDOR_PREFIX = "VENDOR_";
    private static final DecimalFormat FORMAT_FM = new DecimalFormat("###.#");
    private static final Map<Integer, String> ID_TO_URI = new HashMap();
    private static final Map<String, Integer> URI_TO_ID = new HashMap();

    @Retention(RetentionPolicy.SOURCE)
    public @interface NameFlag {
    }

    public static boolean isAmFrequency(long j) {
        return j > 150 && j <= JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS;
    }

    public static boolean isFmFrequency(long j) {
        return j > 60000 && j < 110000;
    }

    static {
        $$Lambda$ProgramSelectorExt$f85Qu6R989MDQGvZ22noGUx07E __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e = new BiConsumer() { // from class: com.android.car.broadcastradio.support.platform.-$$Lambda$ProgramSelectorExt$f85Qu6R989MDQGv-Z22noGUx07E
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ProgramSelectorExt.lambda$static$0((Integer) obj, (String) obj2);
            }
        };
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(1, "AMFM_FREQUENCY");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(2, "RDS_PI");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(3, "HD_STATION_ID_EXT");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(10004, "HD_STATION_NAME");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(5, "DAB_SID_EXT");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(6, "DAB_ENSEMBLE");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(7, "DAB_SCID");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(8, "DAB_FREQUENCY");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(9, "DRMO_SERVICE_ID");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(10, "DRMO_FREQUENCY");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(12, "SXM_SERVICE_ID");
        __lambda_programselectorext_f85qu6r989mdqgvz22nogux07e.accept(13, "SXM_CHANNEL");
    }

    public static ProgramSelector newProgramSelector(ProgramSelector.Identifier identifier, ProgramSelector.Identifier[] identifierArr) {
        return new ProgramSelector(identifierToProgramType(identifier), identifier, identifierArr, null);
    }

    private static int identifierToProgramType(ProgramSelector.Identifier identifier) {
        int type = identifier.getType();
        switch (type) {
            case 1:
                return isAmFrequency(identifier.getValue()) ? 1 : 2;
            case 2:
                return 2;
            case 3:
                return isAmFrequency((long) IdentifierExt.asHdPrimary(identifier).getFrequency()) ? 3 : 4;
            case 4:
            case 11:
            default:
                if (type < 1000 || type > 1999) {
                    return 0;
                }
                return type;
            case 5:
            case 6:
            case 7:
            case 8:
                return 5;
            case 9:
            case 10:
                return 6;
            case 12:
            case 13:
                return 7;
        }
    }

    public static String formatAmFmFrequency(long j, int i) {
        String string;
        String str;
        if (isAmFrequency(j)) {
            string = Long.toString(j);
            str = "AM";
        } else if (isFmFrequency(j)) {
            string = FORMAT_FM.format(j / 1000.0f);
            str = "FM";
        } else {
            Log.w(TAG, "AM/FM frequency out of range: " + j);
            return null;
        }
        if ((i & 2) != 0) {
            return str;
        }
        if ((i & 1) != 0) {
            return string;
        }
        return string + ' ' + str;
    }

    public static ProgramSelector createAmFmSelector(long j) {
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException("illegal frequency value: " + j);
        }
        return ProgramSelector.createAmFmSelector(-1, (int) j);
    }

    public static boolean hasId(ProgramSelector programSelector, int i) {
        try {
            programSelector.getFirstId(i);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean isAmFmProgram(ProgramSelector programSelector) {
        int type = programSelector.getPrimaryId().getType();
        return type == 1 || type == 2 || type == 3;
    }

    public static String getDisplayName(ProgramSelector programSelector, int i) {
        boolean z = (i & 4) != 0;
        if (isAmFmProgram(programSelector)) {
            if (hasId(programSelector, 1)) {
                return formatAmFmFrequency(programSelector.getFirstId(1), i);
            }
            if (z) {
                return null;
            }
            return "FM";
        }
        if ((i & 2) != 0) {
            return null;
        }
        if (programSelector.getPrimaryId().getType() == 12 && hasId(programSelector, 13)) {
            return Long.toString(programSelector.getFirstId(13));
        }
        if (z) {
            return null;
        }
        int type = programSelector.getPrimaryId().getType();
        if (type == 5) {
            return "DAB";
        }
        if (type == 9) {
            return "DRMO";
        }
        if (type != 12) {
            return null;
        }
        return "SXM";
    }

    static /* synthetic */ void lambda$static$0(Integer num, String str) {
        ID_TO_URI.put(num, str);
        URI_TO_ID.put(str, num);
    }

    private static String typeToUri(int i) {
        if (i >= 1000 && i <= 1999) {
            return URI_VENDOR_PREFIX + (i - 1000);
        }
        return ID_TO_URI.get(Integer.valueOf(i));
    }

    private static int uriToType(String str) {
        if (str == null) {
            return 0;
        }
        if (str.startsWith(URI_VENDOR_PREFIX)) {
            try {
                int i = Integer.parseInt(str.substring(7));
                if (i > 999) {
                    return 0;
                }
                return i + 1000;
            } catch (NumberFormatException unused) {
                return 0;
            }
        }
        return URI_TO_ID.get(str).intValue();
    }

    private static String valueToUri(ProgramSelector.Identifier identifier) {
        long value = identifier.getValue();
        int type = identifier.getType();
        if (type == 1 || type == 8 || type == 10 || type == 13) {
            return Long.toString(value);
        }
        return URI_HEX_PREFIX + Long.toHexString(value);
    }

    private static Long uriToValue(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.startsWith(URI_HEX_PREFIX)) {
                return Long.valueOf(Long.parseLong(str.substring(2), 16));
            }
            return Long.valueOf(Long.parseLong(str, 10));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Uri toUri(ProgramSelector programSelector) {
        ProgramSelector.Identifier primaryId = programSelector.getPrimaryId();
        String strTypeToUri = typeToUri(primaryId.getType());
        if (strTypeToUri == null) {
            return null;
        }
        Uri.Builder builderAppendPath = new Uri.Builder().scheme("broadcastradio").authority(URI_AUTHORITY_PROGRAM).appendPath(strTypeToUri).appendPath(valueToUri(primaryId));
        for (ProgramSelector.Identifier identifier : programSelector.getSecondaryIds()) {
            String strTypeToUri2 = typeToUri(identifier.getType());
            if (strTypeToUri2 != null) {
                builderAppendPath.appendQueryParameter(strTypeToUri2, valueToUri(identifier));
            }
        }
        return builderAppendPath.build();
    }

    public static ProgramSelector fromUri(Uri uri) {
        ProgramSelector.Identifier identifier;
        ProgramSelector.Identifier identifier2;
        if (uri == null || !"broadcastradio".equals(uri.getScheme())) {
            return null;
        }
        if (!URI_AUTHORITY_PROGRAM.equals(uri.getAuthority())) {
            Log.w(TAG, "Unknown URI authority part (might be a future, unsupported version): " + uri.getAuthority());
            return null;
        }
        $$Lambda$ProgramSelectorExt$A2C2u8mkREmU5_NbyCRd5RxxW8 __lambda_programselectorext_a2c2u8mkremu5_nbycrd5rxxw8 = new BiFunction() { // from class: com.android.car.broadcastradio.support.platform.-$$Lambda$ProgramSelectorExt$A2C2u-8mkREmU5_NbyCRd5RxxW8
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ProgramSelectorExt.lambda$fromUri$1((String) obj, (String) obj2);
            }
        };
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() != 2 || (identifier = (ProgramSelector.Identifier) __lambda_programselectorext_a2c2u8mkremu5_nbycrd5rxxw8.apply(pathSegments.get(0), pathSegments.get(1))) == null) {
            return null;
        }
        String query = uri.getQuery();
        ArrayList arrayList = new ArrayList();
        if (query != null) {
            for (String str : query.split("&")) {
                String[] strArrSplit = str.split("=");
                if (strArrSplit.length == 2 && (identifier2 = (ProgramSelector.Identifier) __lambda_programselectorext_a2c2u8mkremu5_nbycrd5rxxw8.apply(strArrSplit[0], strArrSplit[1])) != null) {
                    arrayList.add(identifier2);
                }
            }
        }
        return newProgramSelector(identifier, (ProgramSelector.Identifier[]) arrayList.toArray(new ProgramSelector.Identifier[arrayList.size()]));
    }

    static /* synthetic */ ProgramSelector.Identifier lambda$fromUri$1(String str, String str2) {
        int iUriToType = uriToType(str);
        Long lUriToValue = uriToValue(str2);
        if (iUriToType == 0 || lUriToValue == null) {
            return null;
        }
        return new ProgramSelector.Identifier(iUriToType, lUriToValue.longValue());
    }

    public static class IdentifierExt {
        public static HdPrimary asHdPrimary(ProgramSelector.Identifier identifier) {
            if (identifier.getType() == 3) {
                return new HdPrimary(identifier.getValue());
            }
            return null;
        }

        public static class HdPrimary {
            private final long mValue;

            private HdPrimary(long j) {
                this.mValue = j;
            }

            public long getStationId() {
                return this.mValue & (-1);
            }

            public int getSubchannel() {
                return (int) ((this.mValue >>> 32) & 15);
            }

            public int getFrequency() {
                return (int) ((this.mValue >>> 36) & 262143);
            }
        }
    }
}
