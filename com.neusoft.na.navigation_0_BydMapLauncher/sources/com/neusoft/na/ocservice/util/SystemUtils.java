package com.neusoft.na.ocservice.util;

import android.util.Log;
import com.neusoft.na.ocservice.MotsBl;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/ocservice/util/SystemUtils;", "", "()V", "getSystemPropertiesValue", "", "key", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SystemUtils {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "SystemUtils";
    public static final String TYPE_CAR_MODE = "persist.vendor.gwm.cfg.project.code";
    public static final String TYPE_COUNTRY_CODE = "persist.vendor.gwm.cfg.special.country.export";
    public static final String TYPE_RUDDER_MODE = "persist.vendor.gwm.cfg.drive.handle.type";

    /* JADX INFO: compiled from: SystemUtils.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/ocservice/util/SystemUtils$Companion;", "", "()V", "TAG", "", "TYPE_CAR_MODE", "TYPE_COUNTRY_CODE", "TYPE_RUDDER_MODE", "getLanguage", "Ljava/util/Locale;", "locale", "getLanguageBy6125F", "getLanguageBy8155F", "getTTSLanguageResourceKey", MotsBl.JS_PROPERTY_LOCALE_LANGUAGE, "getTTSLanguageResourceKeyBy6125F", "getTTSLanguageResourceKeyBy8155F", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTTSLanguageResourceKey(String language) {
            Intrinsics.checkNotNullParameter(language, "language");
            Log.i(SystemUtils.TAG, "getTTSLanguageResourceKey --> language == " + language);
            String tTSLanguageResourceKeyBy6125F = CheckBoardKt.isByd() ? getTTSLanguageResourceKeyBy6125F(language) : getTTSLanguageResourceKeyBy8155F(language);
            Log.i(SystemUtils.TAG, "getTTSLanguageResourceKey --> value == " + tTSLanguageResourceKeyBy6125F);
            return tTSLanguageResourceKeyBy6125F;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.lang.String getTTSLanguageResourceKeyBy6125F(java.lang.String r5) {
            /*
                r4 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "getTTSLanguageResourceKeyBy6125F --> language == "
                r0.<init>(r1)
                java.lang.StringBuilder r0 = r0.append(r5)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "SystemUtils"
                android.util.Log.i(r1, r0)
                int r0 = r5.hashCode()
                r2 = 3121(0xc31, float:4.373E-42)
                java.lang.String r3 = "Zoe-Ml"
                if (r0 == r2) goto L4c
                r2 = 3241(0xca9, float:4.542E-42)
                if (r0 == r2) goto L43
                r2 = 3276(0xccc, float:4.59E-42)
                if (r0 == r2) goto L37
                r2 = 3588(0xe04, float:5.028E-42)
                if (r0 == r2) goto L2b
                goto L54
            L2b:
                java.lang.String r0 = "pt"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L34
                goto L54
            L34:
                java.lang.String r3 = "Joana"
                goto L5c
            L37:
                java.lang.String r0 = "fr"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L40
                goto L54
            L40:
                java.lang.String r3 = "Audrey-Ml"
                goto L5c
            L43:
                java.lang.String r0 = "en"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L5c
                goto L54
            L4c:
                java.lang.String r0 = "ar"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L5a
            L54:
                java.lang.String r5 = "has no language key, so default language --> en[Zoe-Ml]"
                android.util.Log.e(r1, r5)
                goto L5c
            L5a:
                java.lang.String r3 = "Mariam"
            L5c:
                java.lang.String r5 = "getTTSLanguageResourceKeyBy6125F --> value == "
                java.lang.String r5 = r5.concat(r3)
                android.util.Log.i(r1, r5)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.SystemUtils.Companion.getTTSLanguageResourceKeyBy6125F(java.lang.String):java.lang.String");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00be  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.lang.String getTTSLanguageResourceKeyBy8155F(java.lang.String r4) {
            /*
                Method dump skipped, instruction units count: 260
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.SystemUtils.Companion.getTTSLanguageResourceKeyBy8155F(java.lang.String):java.lang.String");
        }

        public final Locale getLanguage(Locale locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            Log.i(SystemUtils.TAG, "getLanguage --> language == " + locale.getLanguage());
            Locale languageBy6125F = CheckBoardKt.isByd() ? getLanguageBy6125F(locale) : getLanguageBy8155F(locale);
            Log.i(SystemUtils.TAG, "getLanguage --> value == " + languageBy6125F);
            return languageBy6125F;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0076  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.util.Locale getLanguageBy6125F(java.util.Locale r9) {
            /*
                r8 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "getLanguageBy6125F --> language == "
                r0.<init>(r1)
                java.lang.String r1 = r9.getLanguage()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "SystemUtils"
                android.util.Log.i(r1, r0)
                java.util.Locale r0 = new java.util.Locale
                java.lang.String r2 = r9.getLanguage()
                java.lang.String r3 = "en"
                if (r2 == 0) goto L76
                int r4 = r2.hashCode()
                java.lang.String r5 = "pt"
                java.lang.String r6 = "fr"
                java.lang.String r7 = "ar"
                switch(r4) {
                    case 3121: goto L6d;
                    case 3241: goto L66;
                    case 3276: goto L5d;
                    case 3588: goto L54;
                    case 93071345: goto L4b;
                    case 96646644: goto L42;
                    case 97688863: goto L39;
                    case 106983967: goto L30;
                    default: goto L2f;
                }
            L2f:
                goto L76
            L30:
                java.lang.String r4 = "pt_PT"
                boolean r2 = r2.equals(r4)
                if (r2 != 0) goto L5b
                goto L76
            L39:
                java.lang.String r4 = "fr_FR"
                boolean r2 = r2.equals(r4)
                if (r2 != 0) goto L64
                goto L76
            L42:
                java.lang.String r4 = "en_US"
                boolean r2 = r2.equals(r4)
                if (r2 != 0) goto L7b
                goto L76
            L4b:
                java.lang.String r4 = "ar_IL"
                boolean r2 = r2.equals(r4)
                if (r2 != 0) goto L74
                goto L76
            L54:
                boolean r2 = r2.equals(r5)
                if (r2 != 0) goto L5b
                goto L76
            L5b:
                r3 = r5
                goto L7b
            L5d:
                boolean r2 = r2.equals(r6)
                if (r2 != 0) goto L64
                goto L76
            L64:
                r3 = r6
                goto L7b
            L66:
                boolean r2 = r2.equals(r3)
                if (r2 != 0) goto L7b
                goto L76
            L6d:
                boolean r2 = r2.equals(r7)
                if (r2 != 0) goto L74
                goto L76
            L74:
                r3 = r7
                goto L7b
            L76:
                java.lang.String r2 = "has no language key, so default language --> en"
                android.util.Log.e(r1, r2)
            L7b:
                java.lang.String r2 = r9.getCountry()
                java.lang.String r9 = r9.getVariant()
                r0.<init>(r3, r2, r9)
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                java.lang.String r2 = "getLanguageBy6125F --> value == "
                r9.<init>(r2)
                java.lang.StringBuilder r9 = r9.append(r0)
                java.lang.String r9 = r9.toString()
                android.util.Log.i(r1, r9)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.SystemUtils.Companion.getLanguageBy6125F(java.util.Locale):java.util.Locale");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00c1  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00cc  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00d7  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x00ea  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x00f6  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x012a  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0135  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.util.Locale getLanguageBy8155F(java.util.Locale r17) {
            /*
                Method dump skipped, instruction units count: 446
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.SystemUtils.Companion.getLanguageBy8155F(java.util.Locale):java.util.Locale");
        }
    }

    public final String getSystemPropertiesValue(String key) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(key, "key");
        Log.i(TAG, "getSystemPropertiesValue key == " + key);
        Class<?> cls = Class.forName("android.os.SystemProperties");
        String str = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, key);
        Log.i(TAG, "getSystemPropertiesValue value == " + str);
        return str;
    }
}
