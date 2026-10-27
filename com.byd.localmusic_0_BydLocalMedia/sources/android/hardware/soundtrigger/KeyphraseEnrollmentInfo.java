package android.hardware.soundtrigger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Slog;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class KeyphraseEnrollmentInfo {
    public static final String ACTION_MANAGE_VOICE_KEYPHRASES = "com.android.intent.action.MANAGE_VOICE_KEYPHRASES";
    public static final String EXTRA_VOICE_KEYPHRASE_ACTION = "com.android.intent.extra.VOICE_KEYPHRASE_ACTION";
    public static final String EXTRA_VOICE_KEYPHRASE_HINT_TEXT = "com.android.intent.extra.VOICE_KEYPHRASE_HINT_TEXT";
    public static final String EXTRA_VOICE_KEYPHRASE_LOCALE = "com.android.intent.extra.VOICE_KEYPHRASE_LOCALE";
    public static final int MANAGE_ACTION_ENROLL = 0;
    public static final int MANAGE_ACTION_RE_ENROLL = 1;
    public static final int MANAGE_ACTION_UN_ENROLL = 2;
    private static final String TAG = "KeyphraseEnrollmentInfo";
    private static final String VOICE_KEYPHRASE_META_DATA = "android.voice_enrollment";
    private final Map<KeyphraseMetadata, String> mKeyphrasePackageMap;
    private final KeyphraseMetadata[] mKeyphrases;
    private String mParseError;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ManageActions {
    }

    public KeyphraseEnrollmentInfo(PackageManager packageManager) throws Throwable {
        Objects.requireNonNull(packageManager);
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent(ACTION_MANAGE_VOICE_KEYPHRASES), 65536);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            this.mParseError = "No enrollment applications found";
            this.mKeyphrasePackageMap = Collections.emptyMap();
            this.mKeyphrases = null;
            return;
        }
        LinkedList linkedList = new LinkedList();
        this.mKeyphrasePackageMap = new HashMap();
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 128);
                if ((applicationInfo.privateFlags & 8) == 0) {
                    Slog.w(TAG, applicationInfo.packageName + " is not a privileged system app");
                } else if (Manifest.permission.MANAGE_VOICE_KEYPHRASES.equals(applicationInfo.permission)) {
                    KeyphraseMetadata keyphraseMetadataFromApplicationInfo = getKeyphraseMetadataFromApplicationInfo(packageManager, applicationInfo, linkedList);
                    if (keyphraseMetadataFromApplicationInfo != null) {
                        this.mKeyphrasePackageMap.put(keyphraseMetadataFromApplicationInfo, applicationInfo.packageName);
                    }
                } else {
                    Slog.w(TAG, applicationInfo.packageName + " does not require MANAGE_VOICE_KEYPHRASES");
                }
            } catch (PackageManager.NameNotFoundException e) {
                String str = "error parsing voice enrollment meta-data for " + resolveInfo.serviceInfo.packageName;
                linkedList.add(str + ": " + e);
                Slog.w(TAG, str, e);
            }
        }
        if (this.mKeyphrasePackageMap.isEmpty()) {
            linkedList.add("No suitable enrollment application found");
            Slog.w(TAG, "No suitable enrollment application found");
            this.mKeyphrases = null;
        } else {
            this.mKeyphrases = (KeyphraseMetadata[]) this.mKeyphrasePackageMap.keySet().toArray(new KeyphraseMetadata[0]);
        }
        if (linkedList.isEmpty()) {
            return;
        }
        this.mParseError = TextUtils.join(BydAdbHelper.COMMAND_LINE_END, linkedList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ba  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.content.res.XmlResourceParser] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [android.hardware.soundtrigger.KeyphraseMetadata] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.hardware.soundtrigger.KeyphraseMetadata getKeyphraseMetadataFromApplicationInfo(android.content.pm.PackageManager r7, android.content.pm.ApplicationInfo r8, java.util.List<java.lang.String> r9) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = "KeyphraseEnrollmentInfo"
            java.lang.String r1 = r8.packageName
            r2 = 0
            java.lang.String r3 = "android.voice_enrollment"
            android.content.res.XmlResourceParser r3 = r8.loadXmlMetaData(r7, r3)     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L87 android.content.pm.PackageManager.NameNotFoundException -> L89 org.xmlpull.v1.XmlPullParserException -> L8b
            if (r3 != 0) goto L2a
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r7.<init>()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            java.lang.String r8 = "No android.voice_enrollment meta-data for "
            r7.append(r8)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r7.append(r1)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r9.add(r7)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            android.util.Slog.w(r0, r7)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            if (r3 == 0) goto L29
            r3.close()
        L29:
            return r2
        L2a:
            android.content.res.Resources r7 = r7.getResourcesForApplication(r8)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            android.util.AttributeSet r8 = android.util.Xml.asAttributeSet(r3)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
        L32:
            int r4 = r3.next()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r5 = 1
            if (r4 == r5) goto L3d
            r5 = 2
            if (r4 == r5) goto L3d
            goto L32
        L3d:
            java.lang.String r4 = r3.getName()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            java.lang.String r5 = "voice-enrollment-application"
            boolean r4 = r5.equals(r4)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            if (r4 != 0) goto L67
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r7.<init>()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            java.lang.String r8 = "Meta-data does not start with voice-enrollment-application tag for "
            r7.append(r8)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r7.append(r1)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r9.add(r7)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            android.util.Slog.w(r0, r7)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            if (r3 == 0) goto L66
            r3.close()
        L66:
            return r2
        L67:
            int[] r4 = com.android.internal.R.styleable.VoiceEnrollmentApplication     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            android.content.res.TypedArray r7 = r7.obtainAttributes(r8, r4)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            android.hardware.soundtrigger.KeyphraseMetadata r2 = r6.getKeyphraseFromTypedArray(r7, r1, r9)     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            r7.recycle()     // Catch: java.lang.Throwable -> L7a java.io.IOException -> L7d android.content.pm.PackageManager.NameNotFoundException -> L7f org.xmlpull.v1.XmlPullParserException -> L81
            if (r3 == 0) goto Lbe
            r3.close()
            goto Lbe
        L7a:
            r7 = move-exception
            r2 = r3
            goto Lbf
        L7d:
            r7 = move-exception
            goto L82
        L7f:
            r7 = move-exception
            goto L82
        L81:
            r7 = move-exception
        L82:
            r8 = r2
            r2 = r3
            goto L8d
        L85:
            r7 = move-exception
            goto Lbf
        L87:
            r7 = move-exception
            goto L8c
        L89:
            r7 = move-exception
            goto L8c
        L8b:
            r7 = move-exception
        L8c:
            r8 = r2
        L8d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L85
            r3.<init>()     // Catch: java.lang.Throwable -> L85
            java.lang.String r4 = "Error parsing keyphrase enrollment meta-data for "
            r3.append(r4)     // Catch: java.lang.Throwable -> L85
            r3.append(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L85
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L85
            r3.<init>()     // Catch: java.lang.Throwable -> L85
            r3.append(r1)     // Catch: java.lang.Throwable -> L85
            java.lang.String r4 = ": "
            r3.append(r4)     // Catch: java.lang.Throwable -> L85
            r3.append(r7)     // Catch: java.lang.Throwable -> L85
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L85
            r9.add(r3)     // Catch: java.lang.Throwable -> L85
            android.util.Slog.w(r0, r1, r7)     // Catch: java.lang.Throwable -> L85
            if (r2 == 0) goto Lbd
            r2.close()
        Lbd:
            r2 = r8
        Lbe:
            return r2
        Lbf:
            if (r2 == 0) goto Lc4
            r2.close()
        Lc4:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.soundtrigger.KeyphraseEnrollmentInfo.getKeyphraseMetadataFromApplicationInfo(android.content.pm.PackageManager, android.content.pm.ApplicationInfo, java.util.List):android.hardware.soundtrigger.KeyphraseMetadata");
    }

    private KeyphraseMetadata getKeyphraseFromTypedArray(TypedArray typedArray, String str, List<String> list) {
        int i = typedArray.getInt(0, -1);
        if (i <= 0) {
            String str2 = "No valid searchKeyphraseId specified in meta-data for " + str;
            list.add(str2);
            Slog.w(TAG, str2);
            return null;
        }
        String string = typedArray.getString(1);
        if (string == null) {
            String str3 = "No valid searchKeyphrase specified in meta-data for " + str;
            list.add(str3);
            Slog.w(TAG, str3);
            return null;
        }
        String string2 = typedArray.getString(2);
        if (string2 == null) {
            String str4 = "No valid searchKeyphraseSupportedLocales specified in meta-data for " + str;
            list.add(str4);
            Slog.w(TAG, str4);
            return null;
        }
        ArraySet arraySet = new ArraySet();
        if (!TextUtils.isEmpty(string2)) {
            try {
                for (String str5 : string2.split(",")) {
                    arraySet.add(Locale.forLanguageTag(str5));
                }
            } catch (Exception unused) {
                String str6 = "Error reading searchKeyphraseSupportedLocales from meta-data for " + str;
                list.add(str6);
                Slog.w(TAG, str6);
                return null;
            }
        }
        int i2 = typedArray.getInt(3, -1);
        if (i2 < 0) {
            String str7 = "No valid searchKeyphraseRecognitionFlags specified in meta-data for " + str;
            list.add(str7);
            Slog.w(TAG, str7);
            return null;
        }
        return new KeyphraseMetadata(i, string, arraySet, i2);
    }

    public String getParseError() {
        return this.mParseError;
    }

    public Collection<KeyphraseMetadata> listKeyphraseMetadata() {
        return Arrays.asList(this.mKeyphrases);
    }

    public Intent getManageKeyphraseIntent(int i, String str, Locale locale) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(locale);
        Map<KeyphraseMetadata, String> map = this.mKeyphrasePackageMap;
        if (map == null || map.isEmpty()) {
            Slog.w(TAG, "No enrollment application exists");
            return null;
        }
        KeyphraseMetadata keyphraseMetadata = getKeyphraseMetadata(str, locale);
        if (keyphraseMetadata != null) {
            return new Intent(ACTION_MANAGE_VOICE_KEYPHRASES).setPackage(this.mKeyphrasePackageMap.get(keyphraseMetadata)).putExtra(EXTRA_VOICE_KEYPHRASE_HINT_TEXT, str).putExtra(EXTRA_VOICE_KEYPHRASE_LOCALE, locale.toLanguageTag()).putExtra(EXTRA_VOICE_KEYPHRASE_ACTION, i);
        }
        return null;
    }

    public KeyphraseMetadata getKeyphraseMetadata(String str, Locale locale) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(locale);
        KeyphraseMetadata[] keyphraseMetadataArr = this.mKeyphrases;
        if (keyphraseMetadataArr != null && keyphraseMetadataArr.length > 0) {
            for (KeyphraseMetadata keyphraseMetadata : keyphraseMetadataArr) {
                if (keyphraseMetadata.supportsPhrase(str) && keyphraseMetadata.supportsLocale(locale)) {
                    return keyphraseMetadata;
                }
            }
        }
        Slog.w(TAG, "No enrollment application supports the given keyphrase/locale: '" + str + "'/" + locale);
        return null;
    }

    public String toString() {
        return "KeyphraseEnrollmentInfo [KeyphrasePackageMap=" + this.mKeyphrasePackageMap.toString() + ", ParseError=" + this.mParseError + "]";
    }
}
