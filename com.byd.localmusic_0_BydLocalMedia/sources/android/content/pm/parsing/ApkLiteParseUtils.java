package android.content.pm.parsing;

import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.VerifierInfo;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ApkLiteParseUtils {
    private static final int DEFAULT_MIN_SDK_VERSION = 1;
    private static final int DEFAULT_TARGET_SDK_VERSION = 0;
    private static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
    private static final String TAG = "PackageParsing";

    public static ParseResult<PackageParser.PackageLite> parsePackageLite(ParseInput parseInput, File file, int i) {
        if (file.isDirectory()) {
            return parseClusterPackageLite(parseInput, file, i);
        }
        return parseMonolithicPackageLite(parseInput, file, i);
    }

    public static ParseResult<PackageParser.PackageLite> parseMonolithicPackageLite(ParseInput parseInput, File file, int i) {
        Trace.traceBegin(262144L, "parseApkLite");
        try {
            ParseResult<PackageParser.ApkLite> apkLite = parseApkLite(parseInput, file, i);
            if (apkLite.isError()) {
                return parseInput.error(apkLite);
            }
            return parseInput.success(new PackageParser.PackageLite(file.getAbsolutePath(), apkLite.getResult(), null, null, null, null, null, null));
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    public static ParseResult<PackageParser.PackageLite> parseClusterPackageLite(ParseInput parseInput, File file, int i) {
        String[] strArr;
        boolean[] zArr;
        String[] strArr2;
        String[] strArr3;
        String[] strArr4;
        int[] iArr;
        File[] fileArrListFiles = file.listFiles();
        if (ArrayUtils.isEmpty(fileArrListFiles)) {
            return parseInput.error(-100, "No packages found in split");
        }
        if (fileArrListFiles.length == 1 && fileArrListFiles[0].isDirectory()) {
            return parseClusterPackageLite(parseInput, fileArrListFiles[0], i);
        }
        ArrayMap arrayMap = new ArrayMap();
        Trace.traceBegin(262144L, "parseApkLite");
        try {
            int i2 = 0;
            String str = null;
            for (File file2 : fileArrListFiles) {
                if (PackageParser.isApkFile(file2)) {
                    ParseResult<PackageParser.ApkLite> apkLite = parseApkLite(parseInput, file2, i);
                    if (apkLite.isError()) {
                        return parseInput.error(apkLite);
                    }
                    PackageParser.ApkLite result = apkLite.getResult();
                    if (str == null) {
                        str = result.packageName;
                        i2 = result.versionCode;
                    } else {
                        if (!str.equals(result.packageName)) {
                            return parseInput.error(-101, "Inconsistent package " + result.packageName + " in " + file2 + "; expected " + str);
                        }
                        if (i2 != result.versionCode) {
                            return parseInput.error(-101, "Inconsistent version " + result.versionCode + " in " + file2 + "; expected " + i2);
                        }
                    }
                    if (arrayMap.put(result.splitName, result) != null) {
                        return parseInput.error(-101, "Split name " + result.splitName + " defined more than once; most recent was " + file2);
                    }
                }
            }
            Trace.traceEnd(262144L);
            PackageParser.ApkLite apkLite2 = (PackageParser.ApkLite) arrayMap.remove(null);
            if (apkLite2 == null) {
                return parseInput.error(-101, "Missing base APK in " + file);
            }
            int size = arrayMap.size();
            if (size > 0) {
                boolean[] zArr2 = new boolean[size];
                String[] strArr5 = new String[size];
                String[] strArr6 = new String[size];
                String[] strArr7 = new String[size];
                int[] iArr2 = new int[size];
                String[] strArr8 = (String[]) arrayMap.keySet().toArray(new String[size]);
                Arrays.sort(strArr8, PackageParser.sSplitNameComparator);
                for (int i3 = 0; i3 < size; i3++) {
                    PackageParser.ApkLite apkLite3 = (PackageParser.ApkLite) arrayMap.get(strArr8[i3]);
                    strArr5[i3] = apkLite3.usesSplitName;
                    zArr2[i3] = apkLite3.isFeatureSplit;
                    strArr6[i3] = apkLite3.configForSplit;
                    strArr7[i3] = apkLite3.codePath;
                    iArr2[i3] = apkLite3.revisionCode;
                }
                strArr = strArr8;
                strArr2 = strArr5;
                strArr3 = strArr6;
                strArr4 = strArr7;
                zArr = zArr2;
                iArr = iArr2;
            } else {
                strArr = null;
                zArr = null;
                strArr2 = null;
                strArr3 = null;
                strArr4 = null;
                iArr = null;
            }
            return parseInput.success(new PackageParser.PackageLite(file.getAbsolutePath(), apkLite2, strArr, zArr, strArr2, strArr3, strArr4, iArr));
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    public static ParseResult<PackageParser.ApkLite> parseApkLite(ParseInput parseInput, File file, int i) {
        return parseApkLiteInner(parseInput, file, null, null, i);
    }

    public static ParseResult<PackageParser.ApkLite> parseApkLite(ParseInput parseInput, FileDescriptor fileDescriptor, String str, int i) {
        return parseApkLiteInner(parseInput, null, fileDescriptor, str, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x00dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.PackageParser.ApkLite> parseApkLiteInner(android.content.pm.parsing.result.ParseInput r14, java.io.File r15, java.io.FileDescriptor r16, java.lang.String r17, int r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ApkLiteParseUtils.parseApkLiteInner(android.content.pm.parsing.result.ParseInput, java.io.File, java.io.FileDescriptor, java.lang.String, int):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x02cd, code lost:
    
        r1 = r35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02d3, code lost:
    
        if (android.content.pm.PackageParser.checkRequiredSystemProperties(r5, r1) != false) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02d5, code lost:
    
        android.util.Slog.i("PackageParsing", "Skipping target and overlay pair " + r4 + " and " + r37 + ": overlay ignored due to required system property: " + r5 + " with value: " + r1);
        r30 = null;
        r31 = false;
        r32 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x030a, code lost:
    
        r32 = r31;
        r31 = r30;
        r30 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x032f, code lost:
    
        return r36.success(new android.content.pm.PackageParser.ApkLite(r37, r3.first, r3.second, r12, r13, r20, r15, r16, r17, r18, r19, r8, r40, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34));
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0184  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.PackageParser.ApkLite> parseApkLite(android.content.pm.parsing.result.ParseInput r36, java.lang.String r37, org.xmlpull.v1.XmlPullParser r38, android.util.AttributeSet r39, android.content.pm.PackageParser.SigningDetails r40) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 898
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ApkLiteParseUtils.parseApkLite(android.content.pm.parsing.result.ParseInput, java.lang.String, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.pm.PackageParser$SigningDetails):android.content.pm.parsing.result.ParseResult");
    }

    public static ParseResult<Pair<String, String>> parsePackageSplitNames(ParseInput parseInput, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int next;
        String strValidateName;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No start tag found");
        }
        if (!xmlPullParser.getName().equals(PackageParser.TAG_MANIFEST)) {
            return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No <manifest> tag");
        }
        String strIntern = null;
        String attributeValue = attributeSet.getAttributeValue(null, "package");
        if (!"android".equals(attributeValue) && (strValidateName = PackageParser.validateName(attributeValue, true, true)) != null) {
            return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest package: " + strValidateName);
        }
        String attributeValue2 = attributeSet.getAttributeValue(null, "split");
        if (attributeValue2 == null) {
            strIntern = attributeValue2;
        } else if (attributeValue2.length() != 0) {
            String strValidateName2 = PackageParser.validateName(attributeValue2, false, false);
            if (strValidateName2 != null) {
                return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest split: " + strValidateName2);
            }
            strIntern = attributeValue2;
        }
        String strIntern2 = attributeValue.intern();
        if (strIntern != null) {
            strIntern = strIntern.intern();
        }
        return parseInput.success(Pair.create(strIntern2, strIntern));
    }

    public static VerifierInfo parseVerifier(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        String attributeValue = null;
        String attributeValue2 = null;
        for (int i = 0; i < attributeCount; i++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i);
            if (attributeNameResource == 16842755) {
                attributeValue = attributeSet.getAttributeValue(i);
            } else if (attributeNameResource == 16843686) {
                attributeValue2 = attributeSet.getAttributeValue(i);
            }
        }
        if (attributeValue == null || attributeValue.length() == 0) {
            Slog.i("PackageParsing", "verifier package name was null; skipping");
            return null;
        }
        PublicKey publicKey = PackageParser.parsePublicKey(attributeValue2);
        if (publicKey == null) {
            Slog.i("PackageParsing", "Unable to parse verifier public key for " + attributeValue);
            return null;
        }
        return new VerifierInfo(attributeValue, publicKey);
    }
}
