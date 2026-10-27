package android.content.pm.parsing;

import android.app.ActivityThread;
import android.bluetooth.BluetoothHidDevice;
import android.content.IntentFilter;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.Signature;
import android.content.pm.parsing.component.ComponentParseUtils;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedAttributionUtils;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedInstrumentationUtils;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedPermissionUtils;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProcessUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.pm.parsing.result.ParseTypeImpl;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.content.pm.split.DefaultSplitAssetLoader;
import android.content.pm.split.SplitAssetDependencyLoader;
import android.content.pm.split.SplitAssetLoader;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.RemoteException;
import android.os.Trace;
import android.os.ext.SdkExtensions;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.apk.ApkSignatureVerifier;
import com.android.internal.R;
import com.android.internal.midi.MidiConstants;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsingPackageUtils {
    public static final String TAG = "PackageParsing";
    private Callback mCallback;
    private DisplayMetrics mDisplayMetrics;
    private boolean mOnlyCoreApps;
    private String[] mSeparateProcesses;

    public interface Callback {
        boolean hasFeature(String str);

        ParsingPackage startParsingPackage(String str, String str2, String str3, TypedArray typedArray, boolean z);
    }

    public static ParseResult<ParsingPackage> parseDefaultOneTime(File file, int i, boolean z) {
        return parseDefault(ParseTypeImpl.forDefaultParsing().reset(), file, i, z);
    }

    public static ParseResult<ParsingPackage> parseDefault(ParseInput parseInput, File file, int i, boolean z) {
        try {
            ParseResult<ParsingPackage> parseResult = new ParsingPackageUtils(false, null, null, new Callback() { // from class: android.content.pm.parsing.ParsingPackageUtils.1
                @Override // android.content.pm.parsing.ParsingPackageUtils.Callback
                public boolean hasFeature(String str) {
                    return false;
                }

                @Override // android.content.pm.parsing.ParsingPackageUtils.Callback
                public ParsingPackage startParsingPackage(String str, String str2, String str3, TypedArray typedArray, boolean z2) {
                    return new ParsingPackageImpl(str, str2, str3, typedArray);
                }
            }).parsePackage(parseInput, file, i);
            if (parseResult.isError()) {
                return parseResult;
            }
            try {
                ParsingPackage result = parseResult.getResult();
                if (z) {
                    result.setSigningDetails(getSigningDetails(result, false));
                }
                return parseInput.success(result);
            } catch (PackageParser.PackageParserException e) {
                return parseInput.error(-102, "Error collecting package certificates", e);
            }
        } catch (PackageParser.PackageParserException e2) {
            return parseInput.error(-102, "Error parsing package", e2);
        }
    }

    public ParsingPackageUtils(boolean z, String[] strArr, DisplayMetrics displayMetrics, Callback callback) {
        this.mOnlyCoreApps = z;
        this.mSeparateProcesses = strArr;
        this.mDisplayMetrics = displayMetrics;
        this.mCallback = callback;
    }

    public ParseResult<ParsingPackage> parsePackage(ParseInput parseInput, File file, int i) throws PackageParser.PackageParserException {
        if (file.isDirectory()) {
            return parseClusterPackage(parseInput, file, i);
        }
        return parseMonolithicPackage(parseInput, file, i);
    }

    private ParseResult<ParsingPackage> parseClusterPackage(ParseInput parseInput, File file, int i) {
        SplitAssetLoader defaultSplitAssetLoader;
        ParseResult<PackageParser.PackageLite> clusterPackageLite = ApkLiteParseUtils.parseClusterPackageLite(parseInput, file, 0);
        if (clusterPackageLite.isError()) {
            return parseInput.error(clusterPackageLite);
        }
        PackageParser.PackageLite result = clusterPackageLite.getResult();
        if (this.mOnlyCoreApps && !result.coreApp) {
            return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED, "Not a coreApp: " + file);
        }
        SparseArray<int[]> sparseArrayCreateDependenciesFromPackage = null;
        if (result.isolatedSplits && !ArrayUtils.isEmpty(result.splitNames)) {
            try {
                sparseArrayCreateDependenciesFromPackage = SplitAssetDependencyLoader.createDependenciesFromPackage(result);
                defaultSplitAssetLoader = new SplitAssetDependencyLoader(result, sparseArrayCreateDependenciesFromPackage, i);
            } catch (SplitDependencyLoader.IllegalDependencyException e) {
                return parseInput.error(-101, e.getMessage());
            }
        } else {
            defaultSplitAssetLoader = new DefaultSplitAssetLoader(result, i);
        }
        try {
            ParseResult<ParsingPackage> baseApk = parseBaseApk(parseInput, new File(result.baseCodePath), result.codePath, defaultSplitAssetLoader.getBaseAssetManager(), i);
            if (baseApk.isError()) {
                return parseInput.error(baseApk);
            }
            ParsingPackage result2 = baseApk.getResult();
            if (!ArrayUtils.isEmpty(result.splitNames)) {
                result2.asSplit(result.splitNames, result.splitCodePaths, result.splitRevisionCodes, sparseArrayCreateDependenciesFromPackage);
                int length = result.splitNames.length;
                for (int i2 = 0; i2 < length; i2++) {
                    parseSplitApk(parseInput, result2, i2, defaultSplitAssetLoader.getSplitAssetManager(i2), i);
                }
            }
            result2.setUse32BitAbi(result.use32bitAbi);
            return parseInput.success(result2);
        } catch (PackageParser.PackageParserException e2) {
            return parseInput.error(-102, "Failed to load assets: " + result.baseCodePath, e2);
        } finally {
            IoUtils.closeQuietly(defaultSplitAssetLoader);
        }
    }

    private ParseResult<ParsingPackage> parseMonolithicPackage(ParseInput parseInput, File file, int i) throws PackageParser.PackageParserException {
        ParseResult<PackageParser.PackageLite> monolithicPackageLite = ApkLiteParseUtils.parseMonolithicPackageLite(parseInput, file, i);
        if (monolithicPackageLite.isError()) {
            return parseInput.error(monolithicPackageLite);
        }
        PackageParser.PackageLite result = monolithicPackageLite.getResult();
        if (this.mOnlyCoreApps && !result.coreApp) {
            return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED, "Not a coreApp: " + file);
        }
        DefaultSplitAssetLoader defaultSplitAssetLoader = new DefaultSplitAssetLoader(result, i);
        try {
            ParseResult<ParsingPackage> baseApk = parseBaseApk(parseInput, file, file.getCanonicalPath(), defaultSplitAssetLoader.getBaseAssetManager(), i);
            if (baseApk.isError()) {
                return parseInput.error(baseApk);
            }
            return parseInput.success(baseApk.getResult().setUse32BitAbi(result.use32bitAbi));
        } catch (IOException e) {
            return parseInput.error(-102, "Failed to get path: " + file, e);
        } finally {
            IoUtils.closeQuietly(defaultSplitAssetLoader);
        }
    }

    private ParseResult<ParsingPackage> parseBaseApk(ParseInput parseInput, File file, String str, AssetManager assetManager, int i) {
        String absolutePath = file.getAbsolutePath();
        String strSubstring = absolutePath.startsWith(PackageParser.MNT_EXPAND) ? absolutePath.substring(12, absolutePath.indexOf(47, 12)) : null;
        int iFindCookieForPath = assetManager.findCookieForPath(absolutePath);
        if (iFindCookieForPath == 0) {
            return parseInput.error(-101, "Failed adding asset path: " + absolutePath);
        }
        try {
            XmlResourceParser xmlResourceParserOpenXmlResourceParser = assetManager.openXmlResourceParser(iFindCookieForPath, PackageParser.ANDROID_MANIFEST_FILENAME);
            try {
                ParseResult<ParsingPackage> baseApk = parseBaseApk(parseInput, absolutePath, str, new Resources(assetManager, this.mDisplayMetrics, null), xmlResourceParserOpenXmlResourceParser, i);
                if (baseApk.isError()) {
                    ParseResult<ParsingPackage> parseResultError = parseInput.error(baseApk.getErrorCode(), absolutePath + " (at " + xmlResourceParserOpenXmlResourceParser.getPositionDescription() + "): " + baseApk.getErrorMessage());
                    if (xmlResourceParserOpenXmlResourceParser != null) {
                        xmlResourceParserOpenXmlResourceParser.close();
                    }
                    return parseResultError;
                }
                ParsingPackage result = baseApk.getResult();
                if (assetManager.containsAllocatedTable()) {
                    ParseResult<?> parseResultDeferError = parseInput.deferError("Targeting R+ (version 30 and above) requires the resources.arsc of installed APKs to be stored uncompressed and aligned on a 4-byte boundary", ParseInput.DeferredError.RESOURCES_ARSC_COMPRESSED);
                    if (parseResultDeferError.isError()) {
                        ParseResult<ParsingPackage> parseResultError2 = parseInput.error(PackageManager.INSTALL_PARSE_FAILED_RESOURCES_ARSC_COMPRESSED, parseResultDeferError.getErrorMessage());
                        if (xmlResourceParserOpenXmlResourceParser != null) {
                            xmlResourceParserOpenXmlResourceParser.close();
                        }
                        return parseResultError2;
                    }
                }
                if (assetManager.getApkAssets()[0].definesOverlayable()) {
                    SparseArray<String> assignedPackageIdentifiers = assetManager.getAssignedPackageIdentifiers();
                    int size = assignedPackageIdentifiers.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Map<String, String> overlayableMap = assetManager.getOverlayableMap(assignedPackageIdentifiers.get(i2));
                        if (overlayableMap != null && !overlayableMap.isEmpty()) {
                            for (String str2 : overlayableMap.keySet()) {
                                result.addOverlayable(str2, overlayableMap.get(str2));
                            }
                        }
                    }
                }
                result.setVolumeUuid(strSubstring);
                if ((i & 32) != 0) {
                    result.setSigningDetails(getSigningDetails(result, false));
                } else {
                    result.setSigningDetails(PackageParser.SigningDetails.UNKNOWN);
                }
                ParseResult<ParsingPackage> parseResultSuccess = parseInput.success(result);
                if (xmlResourceParserOpenXmlResourceParser != null) {
                    xmlResourceParserOpenXmlResourceParser.close();
                }
                return parseResultSuccess;
            } finally {
            }
        } catch (Exception e) {
            return parseInput.error(-102, "Failed to read manifest from " + absolutePath, e);
        }
    }

    private ParseResult<ParsingPackage> parseSplitApk(ParseInput parseInput, ParsingPackage parsingPackage, int i, AssetManager assetManager, int i2) {
        String str = parsingPackage.getSplitCodePaths()[i];
        int iFindCookieForPath = assetManager.findCookieForPath(str);
        if (iFindCookieForPath == 0) {
            return parseInput.error(-101, "Failed adding asset path: " + str);
        }
        try {
            XmlResourceParser xmlResourceParserOpenXmlResourceParser = assetManager.openXmlResourceParser(iFindCookieForPath, PackageParser.ANDROID_MANIFEST_FILENAME);
            try {
                ParseResult<ParsingPackage> splitApk = parseSplitApk(parseInput, parsingPackage, new Resources(assetManager, this.mDisplayMetrics, null), xmlResourceParserOpenXmlResourceParser, i2, i);
                if (!splitApk.isError()) {
                    if (xmlResourceParserOpenXmlResourceParser != null) {
                        xmlResourceParserOpenXmlResourceParser.close();
                    }
                    return splitApk;
                }
                ParseResult<ParsingPackage> parseResultError = parseInput.error(splitApk.getErrorCode(), str + " (at " + xmlResourceParserOpenXmlResourceParser.getPositionDescription() + "): " + splitApk.getErrorMessage());
                if (xmlResourceParserOpenXmlResourceParser != null) {
                    xmlResourceParserOpenXmlResourceParser.close();
                }
                return parseResultError;
            } finally {
            }
        } catch (Exception e) {
            return parseInput.error(-102, "Failed to read manifest from " + str, e);
        }
        return parseInput.error(-102, "Failed to read manifest from " + str, e);
    }

    private ParseResult<ParsingPackage> parseBaseApk(ParseInput parseInput, String str, String str2, Resources resources, XmlResourceParser xmlResourceParser, int i) throws XmlPullParserException, PackageParser.PackageParserException, IOException {
        ParseResult<Pair<String, String>> packageSplitNames = ApkLiteParseUtils.parsePackageSplitNames(parseInput, xmlResourceParser, xmlResourceParser);
        if (packageSplitNames.isError()) {
            return parseInput.error(packageSplitNames);
        }
        Pair<String, String> result = packageSplitNames.getResult();
        String str3 = result.first;
        String str4 = result.second;
        if (!TextUtils.isEmpty(str4)) {
            return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Expected base APK, but found split " + str4);
        }
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifest);
        try {
            ParsingPackage parsingPackageStartParsingPackage = this.mCallback.startParsingPackage(str3, str, str2, typedArrayObtainAttributes, xmlResourceParser.getAttributeBooleanValue(null, "coreApp", false));
            ParseResult<ParsingPackage> baseApkTags = parseBaseApkTags(parseInput, parsingPackageStartParsingPackage, typedArrayObtainAttributes, resources, xmlResourceParser, i);
            return baseApkTags.isError() ? baseApkTags : parseInput.success(parsingPackageStartParsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private ParseResult<ParsingPackage> parseSplitApk(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, int i, int i2) throws XmlPullParserException, PackageParser.PackageParserException, IOException {
        ParseResult<ParsingPackage> parseResultUnknownTag;
        PackageParser.parsePackageSplitNames(xmlResourceParser, xmlResourceParser);
        int depth = xmlResourceParser.getDepth();
        boolean z = false;
        while (true) {
            int next = xmlResourceParser.next();
            if (next != 1) {
                if (depth + 1 >= xmlResourceParser.getDepth() && next == 2) {
                    if (!PackageParser.TAG_APPLICATION.equals(xmlResourceParser.getName())) {
                        parseResultUnknownTag = ParsingUtils.unknownTag("<manifest>", parsingPackage, xmlResourceParser, parseInput);
                    } else if (z) {
                        Slog.w("PackageParsing", "<manifest> has more than one <application>");
                        parseResultUnknownTag = parseInput.success(null);
                    } else {
                        parseResultUnknownTag = parseSplitApplication(parseInput, parsingPackage, resources, xmlResourceParser, i, i2);
                        z = true;
                    }
                    if (parseResultUnknownTag.isError()) {
                        return parseInput.error(parseResultUnknownTag);
                    }
                }
            } else {
                if (!z) {
                    ParseResult<?> parseResultDeferError = parseInput.deferError("<manifest> does not contain an <application>", ParseInput.DeferredError.MISSING_APP_TAG);
                    if (parseResultDeferError.isError()) {
                        return parseInput.error(parseResultDeferError);
                    }
                }
                return parseInput.success(parsingPackage);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x016d, code lost:
    
        return r18.success(r19);
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseSplitApplication(android.content.pm.parsing.result.ParseInput r18, android.content.pm.parsing.ParsingPackage r19, android.content.res.Resources r20, android.content.res.XmlResourceParser r21, int r22, int r23) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseSplitApplication(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int, int):android.content.pm.parsing.result.ParseResult");
    }

    private ParseResult parseSplitBaseAppChildTags(ParseInput parseInput, String str, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        str.hashCode();
        switch (str) {
            case "uses-library":
                return parseUsesLibrary(parseInput, parsingPackage, resources, xmlResourceParser);
            case "meta-data":
                ParseResult<Bundle> metaData = parseMetaData(parsingPackage, resources, xmlResourceParser, parsingPackage.getMetaData(), parseInput);
                if (metaData.isSuccess()) {
                    parsingPackage.setMetaData(metaData.getResult());
                }
                return metaData;
            case "uses-static-library":
                return parseUsesStaticLibrary(parseInput, parsingPackage, resources, xmlResourceParser);
            case "uses-package":
                return parseInput.success(null);
            default:
                return ParsingUtils.unknownTag("<application>", parsingPackage, xmlResourceParser, parseInput);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0088, code lost:
    
        if (r12 != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0092, code lost:
    
        if (com.android.internal.util.ArrayUtils.size(r15.getInstrumentations()) != 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
    
        r0 = r14.deferError("<manifest> does not contain an <application> or <instrumentation>", android.content.pm.parsing.result.ParseInput.DeferredError.MISSING_APP_TAG);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a1, code lost:
    
        if (r0.isError() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a7, code lost:
    
        return r14.error(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
    
        if (android.content.pm.parsing.component.ParsedAttribution.isCombinationValid(r15.getAttributions()) != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ba, code lost:
    
        return r14.error(-101, "Combination <feature> tags are not valid");
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bb, code lost:
    
        convertNewPermissions(r15);
        convertSplitPermissions(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c5, code lost:
    
        if (r15.getTargetSdkVersion() < 4) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00cb, code lost:
    
        if (r15.isSupportsSmallScreens() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d1, code lost:
    
        if (r15.isSupportsNormalScreens() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d7, code lost:
    
        if (r15.isSupportsLargeScreens() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00dd, code lost:
    
        if (r15.isSupportsExtraLargeScreens() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00e3, code lost:
    
        if (r15.isResizeable() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e9, code lost:
    
        if (r15.isAnyDensity() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00eb, code lost:
    
        adjustPackageToBeUnresizeableAndUnpipable(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f2, code lost:
    
        return r14.success(r15);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseBaseApkTags(android.content.pm.parsing.result.ParseInput r14, android.content.pm.parsing.ParsingPackage r15, android.content.res.TypedArray r16, android.content.res.Resources r17, android.content.res.XmlResourceParser r18, int r19) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseBaseApkTags(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.TypedArray, android.content.res.Resources, android.content.res.XmlResourceParser, int):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private ParseResult parseBaseApkTag(String str, ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, int i) throws XmlPullParserException, IOException {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1773650763:
                if (str.equals(PackageParser.TAG_USES_CONFIGURATION)) {
                    b = 0;
                }
                break;
            case -1667688228:
                if (str.equals(PackageParser.TAG_PERMISSION_TREE)) {
                    b = 1;
                }
                break;
            case -1108197302:
                if (str.equals(PackageParser.TAG_ORIGINAL_PACKAGE)) {
                    b = 2;
                }
                break;
            case -1091287984:
                if (str.equals("overlay")) {
                    b = 3;
                }
                break;
            case -998269702:
                if (str.equals(PackageParser.TAG_RESTRICT_UPDATE)) {
                    b = 4;
                }
                break;
            case -979207434:
                if (str.equals("feature")) {
                    b = 5;
                }
                break;
            case -517618225:
                if (str.equals("permission")) {
                    b = 6;
                }
                break;
            case -309882753:
                if (str.equals(PackageParser.TAG_ATTRIBUTION)) {
                    b = 7;
                }
                break;
            case -266709319:
                if (str.equals(PackageParser.TAG_USES_SDK)) {
                    b = 8;
                }
                break;
            case -170723071:
                if (str.equals(PackageParser.TAG_PERMISSION_GROUP)) {
                    b = 9;
                }
                break;
            case -129269526:
                if (str.equals(PackageParser.TAG_EAT_COMMENT)) {
                    b = 10;
                }
                break;
            case 119109844:
                if (str.equals(PackageParser.TAG_USES_GL_TEXTURE)) {
                    b = 11;
                }
                break;
            case 349565761:
                if (str.equals(PackageParser.TAG_SUPPORTS_INPUT)) {
                    b = 12;
                }
                break;
            case 454915839:
                if (str.equals(PackageParser.TAG_KEY_SETS)) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                }
                break;
            case 544550766:
                if (str.equals(PackageParser.TAG_INSTRUMENTATION)) {
                    b = BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                }
                break;
            case 599862896:
                if (str.equals(PackageParser.TAG_USES_PERMISSION)) {
                    b = MidiConstants.STATUS_CHANNEL_MASK;
                }
                break;
            case 632228327:
                if (str.equals(PackageParser.TAG_ADOPT_PERMISSIONS)) {
                    b = 16;
                }
                break;
            case 655087462:
                if (str.equals(PackageParser.TAG_QUERIES)) {
                    b = 17;
                }
                break;
            case 896788286:
                if (str.equals(PackageParser.TAG_SUPPORT_SCREENS)) {
                    b = 18;
                }
                break;
            case 1343942321:
                if (str.equals(PackageParser.TAG_USES_PERMISSION_SDK_23)) {
                    b = 19;
                }
                break;
            case 1439495522:
                if (str.equals(PackageParser.TAG_PROTECTED_BROADCAST)) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.AUTOMOBILE_CLIENT;
                }
                break;
            case 1682371816:
                if (str.equals(PackageParser.TAG_FEATURE_GROUP)) {
                    b = 21;
                }
                break;
            case 1705921021:
                if (str.equals(PackageParser.TAG_USES_PERMISSION_SDK_M)) {
                    b = 22;
                }
                break;
            case 1792785909:
                if (str.equals(PackageParser.TAG_USES_FEATURE)) {
                    b = 23;
                }
                break;
            case 1818228622:
                if (str.equals(PackageParser.TAG_COMPATIBLE_SCREENS)) {
                    b = 24;
                }
                break;
        }
        switch (b) {
            case 0:
                return parseUsesConfiguration(parseInput, parsingPackage, resources, xmlResourceParser);
            case 1:
                return parsePermissionTree(parseInput, parsingPackage, resources, xmlResourceParser);
            case 2:
                return parseOriginalPackage(parseInput, parsingPackage, resources, xmlResourceParser);
            case 3:
                return parseOverlay(parseInput, parsingPackage, resources, xmlResourceParser);
            case 4:
                return parseRestrictUpdateHash(i, parseInput, parsingPackage, resources, xmlResourceParser);
            case 5:
            case 7:
                return parseAttribution(parseInput, parsingPackage, resources, xmlResourceParser);
            case 6:
                return parsePermission(parseInput, parsingPackage, resources, xmlResourceParser);
            case 8:
                return parseUsesSdk(parseInput, parsingPackage, resources, xmlResourceParser);
            case 9:
                return parsePermissionGroup(parseInput, parsingPackage, resources, xmlResourceParser);
            case 10:
            case 11:
            case 12:
            case 24:
                XmlUtils.skipCurrentTag(xmlResourceParser);
                return parseInput.success(parsingPackage);
            case 13:
                return parseKeySets(parseInput, parsingPackage, resources, xmlResourceParser);
            case 14:
                return parseInstrumentation(parseInput, parsingPackage, resources, xmlResourceParser);
            case 15:
            case 19:
            case 22:
                return parseUsesPermission(parseInput, parsingPackage, resources, xmlResourceParser);
            case 16:
                return parseAdoptPermissions(parseInput, parsingPackage, resources, xmlResourceParser);
            case 17:
                return parseQueries(parseInput, parsingPackage, resources, xmlResourceParser);
            case 18:
                return parseSupportScreens(parseInput, parsingPackage, resources, xmlResourceParser);
            case 20:
                return parseProtectedBroadcast(parseInput, parsingPackage, resources, xmlResourceParser);
            case 21:
                return parseFeatureGroup(parseInput, parsingPackage, resources, xmlResourceParser);
            case 23:
                return parseUsesFeature(parseInput, parsingPackage, resources, xmlResourceParser);
            default:
                return ParsingUtils.unknownTag("<manifest>", parsingPackage, xmlResourceParser, parseInput);
        }
    }

    private static ParseResult<ParsingPackage> parseSharedUser(ParseInput parseInput, ParsingPackage parsingPackage, TypedArray typedArray) {
        String strNonConfigString = nonConfigString(0, 0, typedArray);
        if (TextUtils.isEmpty(strNonConfigString)) {
            return parseInput.success(parsingPackage);
        }
        if (!"android".equals(parsingPackage.getPackageName())) {
            ParseResult parseResultValidateName = validateName(parseInput, strNonConfigString, true, true);
            if (parseResultValidateName.isError()) {
                return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID, "<manifest> specifies bad sharedUserId name \"" + strNonConfigString + "\": " + parseResultValidateName.getErrorMessage());
            }
        }
        return parseInput.success(parsingPackage.setSharedUserId(strNonConfigString.intern()).setSharedUserLabel(resId(3, typedArray)));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x029f, code lost:
    
        return r16.success(r17);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02bb, code lost:
    
        return r16.error("Package" + r2 + " AndroidManifest.xml does not define all 'upgrade-key-set's .");
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01c6, code lost:
    
        r2 = r17.getPackageName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01d8, code lost:
    
        if (r5.keySet().removeAll(r7.keySet()) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01f5, code lost:
    
        return r16.error("Package" + r2 + " AndroidManifest.xml 'key-set' and 'public-key' names must be distinct.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01f6, code lost:
    
        r3 = r7.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0202, code lost:
    
        if (r3.hasNext() == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0204, code lost:
    
        r7 = (java.util.Map.Entry) r3.next();
        r9 = (java.lang.String) r7.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x021c, code lost:
    
        if (((android.util.ArraySet) r7.getValue()).size() != 0) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x021e, code lost:
    
        android.util.Slog.w("PackageParsing", "Package" + r2 + " AndroidManifest.xml 'key-set' " + r9 + " has no valid associated 'public-key'. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0245, code lost:
    
        if (r8.contains(r9) == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0247, code lost:
    
        android.util.Slog.w("PackageParsing", "Package" + r2 + " AndroidManifest.xml 'key-set' " + r9 + " contained improper 'public-key' tags. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x026a, code lost:
    
        r7 = ((android.util.ArraySet) r7.getValue()).iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0278, code lost:
    
        if (r7.hasNext() == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x027a, code lost:
    
        r17.addKeySet(r9, (java.security.PublicKey) r5.get((java.lang.String) r7.next()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0296, code lost:
    
        if (r17.getKeySetMapping().keySet().containsAll(r6) == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0298, code lost:
    
        r17.setUpgradeKeySets(r6);
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseKeySets(android.content.pm.parsing.result.ParseInput r16, android.content.pm.parsing.ParsingPackage r17, android.content.res.Resources r18, android.content.res.XmlResourceParser r19) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 724
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseKeySets(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsingPackage> parseAttribution(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        ParseResult<ParsedAttribution> attribution = ParsedAttributionUtils.parseAttribution(resources, xmlResourceParser, parseInput);
        if (attribution.isError()) {
            return parseInput.error(attribution);
        }
        return parseInput.success(parsingPackage.addAttribution(attribution.getResult()));
    }

    private static ParseResult<ParsingPackage> parsePermissionGroup(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        ParseResult<ParsedPermissionGroup> permissionGroup = ParsedPermissionUtils.parsePermissionGroup(parsingPackage, resources, xmlResourceParser, PackageParser.sUseRoundIcon, parseInput);
        if (permissionGroup.isError()) {
            return parseInput.error(permissionGroup);
        }
        return parseInput.success(parsingPackage.addPermissionGroup(permissionGroup.getResult()));
    }

    private static ParseResult<ParsingPackage> parsePermission(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        ParseResult<ParsedPermission> permission = ParsedPermissionUtils.parsePermission(parsingPackage, resources, xmlResourceParser, PackageParser.sUseRoundIcon, parseInput);
        if (permission.isError()) {
            return parseInput.error(permission);
        }
        return parseInput.success(parsingPackage.addPermission(permission.getResult()));
    }

    private static ParseResult<ParsingPackage> parsePermissionTree(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        ParseResult<ParsedPermission> permissionTree = ParsedPermissionUtils.parsePermissionTree(parsingPackage, resources, xmlResourceParser, PackageParser.sUseRoundIcon, parseInput);
        if (permissionTree.isError()) {
            return parseInput.error(permissionTree);
        }
        return parseInput.success(parsingPackage.addPermission(permissionTree.getResult()));
    }

    private ParseResult<ParsingPackage> parseUsesPermission(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        Callback callback;
        Callback callback2;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesPermission);
        try {
            String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
            TypedValue typedValuePeekValue = typedArrayObtainAttributes.peekValue(1);
            int i = (typedValuePeekValue == null || typedValuePeekValue.type < 16 || typedValuePeekValue.type > 31) ? 0 : typedValuePeekValue.data;
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(2, 0);
            String nonConfigurationString2 = typedArrayObtainAttributes.getNonConfigurationString(3, 0);
            XmlUtils.skipCurrentTag(xmlResourceParser);
            ParseResult<ParsingPackage> parseResultSuccess = parseInput.success(parsingPackage);
            if (nonResourceString == null) {
                return parseResultSuccess;
            }
            if (i != 0 && i < Build.VERSION.RESOURCES_SDK_INT) {
                return parseResultSuccess;
            }
            if (nonConfigurationString != null && (callback2 = this.mCallback) != null && !callback2.hasFeature(nonConfigurationString)) {
                return parseResultSuccess;
            }
            if (nonConfigurationString2 != null && (callback = this.mCallback) != null && callback.hasFeature(nonConfigurationString2)) {
                return parseResultSuccess;
            }
            if (parsingPackage.getRequestedPermissions().contains(nonResourceString)) {
                Slog.w("PackageParsing", "Ignoring duplicate uses-permissions/uses-permissions-sdk-m: " + nonResourceString + " in package: " + parsingPackage.getPackageName() + " at: " + xmlResourceParser.getPositionDescription());
            } else {
                parsingPackage.addRequestedPermission(nonResourceString.intern());
            }
            return parseResultSuccess;
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesConfiguration(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        ConfigurationInfo configurationInfo = new ConfigurationInfo();
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesConfiguration);
        try {
            configurationInfo.reqTouchScreen = typedArrayObtainAttributes.getInt(0, 0);
            configurationInfo.reqKeyboardType = typedArrayObtainAttributes.getInt(1, 0);
            if (typedArrayObtainAttributes.getBoolean(2, false)) {
                configurationInfo.reqInputFeatures = 1 | configurationInfo.reqInputFeatures;
            }
            configurationInfo.reqNavigation = typedArrayObtainAttributes.getInt(3, 0);
            if (typedArrayObtainAttributes.getBoolean(4, false)) {
                configurationInfo.reqInputFeatures |= 2;
            }
            parsingPackage.addConfigPreference(configurationInfo);
            return parseInput.success(parsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesFeature(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        FeatureInfo featureInfo = parseFeatureInfo(resources, xmlResourceParser);
        parsingPackage.addReqFeature(featureInfo);
        if (featureInfo.name == null) {
            ConfigurationInfo configurationInfo = new ConfigurationInfo();
            configurationInfo.reqGlEsVersion = featureInfo.reqGlEsVersion;
            parsingPackage.addConfigPreference(configurationInfo);
        }
        return parseInput.success(parsingPackage);
    }

    private static FeatureInfo parseFeatureInfo(Resources resources, AttributeSet attributeSet) {
        FeatureInfo featureInfo = new FeatureInfo();
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, R.styleable.AndroidManifestUsesFeature);
        try {
            featureInfo.name = typedArrayObtainAttributes.getNonResourceString(0);
            featureInfo.version = typedArrayObtainAttributes.getInt(3, 0);
            if (featureInfo.name == null) {
                featureInfo.reqGlEsVersion = typedArrayObtainAttributes.getInt(1, 0);
            }
            if (typedArrayObtainAttributes.getBoolean(2, true)) {
                featureInfo.flags |= 1;
            }
            return featureInfo;
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseFeatureGroup(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        FeatureGroupInfo featureGroupInfo = new FeatureGroupInfo();
        int depth = xmlResourceParser.getDepth();
        ArrayList arrayListAdd = null;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || (next == 3 && xmlResourceParser.getDepth() <= depth)) {
                break;
            }
            if (next == 2) {
                String name = xmlResourceParser.getName();
                if (name.equals(PackageParser.TAG_USES_FEATURE)) {
                    FeatureInfo featureInfo = parseFeatureInfo(resources, xmlResourceParser);
                    featureInfo.flags = 1 | featureInfo.flags;
                    arrayListAdd = ArrayUtils.add((ArrayList<FeatureInfo>) arrayListAdd, featureInfo);
                } else {
                    Slog.w("PackageParsing", "Unknown element under <feature-group>: " + name + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
                }
            }
        }
        if (arrayListAdd != null) {
            featureGroupInfo.features = new FeatureInfo[arrayListAdd.size()];
            featureGroupInfo.features = (FeatureInfo[]) arrayListAdd.toArray(featureGroupInfo.features);
        }
        parsingPackage.addFeatureGroup(featureGroupInfo);
        return parseInput.success(parsingPackage);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00ef, code lost:
    
        r11.setMinExtensionVersions(exactSizedCopyOfSparseArray(r3));
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0030 A[Catch: all -> 0x00fa, TryCatch #0 {all -> 0x00fa, blocks: (B:5:0x000b, B:7:0x0014, B:9:0x0018, B:11:0x001c, B:15:0x002a, B:17:0x0030, B:19:0x0034, B:21:0x0038, B:27:0x004d, B:29:0x0059, B:32:0x0061, B:34:0x0079, B:37:0x0081, B:39:0x008f, B:42:0x0097, B:43:0x00ac, B:46:0x00b4, B:52:0x00c0, B:55:0x00ce, B:56:0x00d3, B:58:0x00e1, B:60:0x00e7, B:57:0x00db, B:63:0x00ef, B:25:0x0046, B:12:0x0024), top: B:70:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0059 A[Catch: all -> 0x00fa, TRY_LEAVE, TryCatch #0 {all -> 0x00fa, blocks: (B:5:0x000b, B:7:0x0014, B:9:0x0018, B:11:0x001c, B:15:0x002a, B:17:0x0030, B:19:0x0034, B:21:0x0038, B:27:0x004d, B:29:0x0059, B:32:0x0061, B:34:0x0079, B:37:0x0081, B:39:0x008f, B:42:0x0097, B:43:0x00ac, B:46:0x00b4, B:52:0x00c0, B:55:0x00ce, B:56:0x00d3, B:58:0x00e1, B:60:0x00e7, B:57:0x00db, B:63:0x00ef, B:25:0x0046, B:12:0x0024), top: B:70:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061 A[Catch: all -> 0x00fa, TRY_ENTER, TryCatch #0 {all -> 0x00fa, blocks: (B:5:0x000b, B:7:0x0014, B:9:0x0018, B:11:0x001c, B:15:0x002a, B:17:0x0030, B:19:0x0034, B:21:0x0038, B:27:0x004d, B:29:0x0059, B:32:0x0061, B:34:0x0079, B:37:0x0081, B:39:0x008f, B:42:0x0097, B:43:0x00ac, B:46:0x00b4, B:52:0x00c0, B:55:0x00ce, B:56:0x00d3, B:58:0x00e1, B:60:0x00e7, B:57:0x00db, B:63:0x00ef, B:25:0x0046, B:12:0x0024), top: B:70:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseUsesSdk(android.content.pm.parsing.result.ParseInput r10, android.content.pm.parsing.ParsingPackage r11, android.content.res.Resources r12, android.content.res.XmlResourceParser r13) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseUsesSdk(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    private static SparseIntArray exactSizedCopyOfSparseArray(SparseIntArray sparseIntArray) {
        if (sparseIntArray == null) {
            return null;
        }
        SparseIntArray sparseIntArray2 = new SparseIntArray(sparseIntArray.size());
        for (int i = 0; i < sparseIntArray.size(); i++) {
            sparseIntArray2.put(sparseIntArray.keyAt(i), sparseIntArray.valueAt(i));
        }
        return sparseIntArray2;
    }

    private static ParseResult<SparseIntArray> parseExtensionSdk(ParseInput parseInput, Resources resources, XmlResourceParser xmlResourceParser, SparseIntArray sparseIntArray) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestExtensionSdk);
        try {
            int i = typedArrayObtainAttributes.getInt(0, -1);
            int i2 = typedArrayObtainAttributes.getInt(1, -1);
            typedArrayObtainAttributes.recycle();
            if (i < 0) {
                return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "<extension-sdk> must specify an sdkVersion >= 0");
            }
            if (i2 < 0) {
                return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "<extension-sdk> must specify minExtensionVersion >= 0");
            }
            try {
                int extensionVersion = SdkExtensions.getExtensionVersion(i);
                if (extensionVersion < i2) {
                    return parseInput.error(-12, "Package requires " + i + " extension version " + i2 + " which exceeds device version " + extensionVersion);
                }
                sparseIntArray.put(i, i2);
                return parseInput.success(sparseIntArray);
            } catch (RuntimeException unused) {
                return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "Specified sdkVersion " + i + " is not valid");
            }
        } catch (Throwable th) {
            typedArrayObtainAttributes.recycle();
            throw th;
        }
    }

    public static ParseResult<Integer> computeMinSdkVersion(int i, String str, int i2, String[] strArr, ParseInput parseInput) {
        if (str == null) {
            if (i <= i2) {
                return parseInput.success(Integer.valueOf(i));
            }
            return parseInput.error(-12, "Requires newer sdk version #" + i + " (current version is #" + i2 + ")");
        }
        if (matchTargetCode(strArr, str)) {
            return parseInput.success(10000);
        }
        if (strArr.length > 0) {
            return parseInput.error(-12, "Requires development platform " + str + " (current platform is any of " + Arrays.toString(strArr) + ")");
        }
        return parseInput.error(-12, "Requires development platform " + str + " but this is a release platform.");
    }

    public static ParseResult<Integer> computeTargetSdkVersion(int i, String str, String[] strArr, ParseInput parseInput) {
        if (str == null) {
            return parseInput.success(Integer.valueOf(i));
        }
        if (matchTargetCode(strArr, str)) {
            return parseInput.success(10000);
        }
        if (strArr.length > 0) {
            return parseInput.error(-12, "Requires development platform " + str + " (current platform is any of " + Arrays.toString(strArr) + ")");
        }
        return parseInput.error(-12, "Requires development platform " + str + " but this is a release platform.");
    }

    private static boolean matchTargetCode(String[] strArr, String str) {
        int iIndexOf = str.indexOf(46);
        if (iIndexOf != -1) {
            str = str.substring(0, iIndexOf);
        }
        return ArrayUtils.contains(strArr, str);
    }

    private static ParseResult<ParsingPackage> parseRestrictUpdateHash(int i, ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        if ((i & 16) != 0) {
            TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestRestrictUpdate);
            try {
                String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
                if (nonConfigurationString != null) {
                    int length = nonConfigurationString.length();
                    byte[] bArr = new byte[length / 2];
                    for (int i2 = 0; i2 < length; i2 += 2) {
                        bArr[i2 / 2] = (byte) ((Character.digit(nonConfigurationString.charAt(i2), 16) << 4) + Character.digit(nonConfigurationString.charAt(i2 + 1), 16));
                    }
                    parsingPackage.setRestrictUpdateHash(bArr);
                } else {
                    parsingPackage.setRestrictUpdateHash(null);
                }
            } finally {
                typedArrayObtainAttributes.recycle();
            }
        }
        return parseInput.success(parsingPackage);
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x0188, code lost:
    
        return r12.success(r13);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseQueries(android.content.pm.parsing.result.ParseInput r12, android.content.pm.parsing.ParsingPackage r13, android.content.res.Resources r14, android.content.res.XmlResourceParser r15) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseQueries(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x0349  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseBaseApplication(android.content.pm.parsing.result.ParseInput r22, android.content.pm.parsing.ParsingPackage r23, android.content.res.Resources r24, android.content.res.XmlResourceParser r25, int r26) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1002
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseBaseApplication(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int):android.content.pm.parsing.result.ParseResult");
    }

    private void parseBaseAppBasicFlags(ParsingPackage parsingPackage, TypedArray typedArray) {
        int targetSdkVersion = parsingPackage.getTargetSdkVersion();
        parsingPackage.setAllowBackup(bool(true, 17, typedArray)).setAllowClearUserData(bool(true, 5, typedArray)).setAllowClearUserDataOnFailedRestore(bool(true, 54, typedArray)).setAllowNativeHeapPointerTagging(bool(true, 59, typedArray)).setEnabled(bool(true, 9, typedArray)).setExtractNativeLibs(bool(true, 34, typedArray)).setHasCode(bool(true, 7, typedArray)).setAllowTaskReparenting(bool(false, 14, typedArray)).setCantSaveState(bool(false, 47, typedArray)).setCrossProfile(bool(false, 58, typedArray)).setDebuggable(bool(false, 10, typedArray)).setDefaultToDeviceProtectedStorage(bool(false, 38, typedArray)).setDirectBootAware(bool(false, 39, typedArray)).setForceQueryable(bool(false, 57, typedArray)).setGame(bool(false, 31, typedArray)).setHasFragileUserData(bool(false, 50, typedArray)).setLargeHeap(bool(false, 24, typedArray)).setMultiArch(bool(false, 33, typedArray)).setPreserveLegacyExternalStorage(bool(false, 61, typedArray)).setRequiredForAllUsers(bool(false, 27, typedArray)).setSupportsRtl(bool(false, 26, typedArray)).setTestOnly(bool(false, 15, typedArray)).setUseEmbeddedDex(bool(false, 53, typedArray)).setUsesNonSdkApi(bool(false, 49, typedArray)).setVmSafeMode(bool(false, 20, typedArray)).setAutoRevokePermissions(anInt(60, typedArray)).setAllowAudioPlaybackCapture(bool(targetSdkVersion >= 29, 55, typedArray)).setBaseHardwareAccelerated(bool(targetSdkVersion >= 14, 23, typedArray)).setRequestLegacyExternalStorage(bool(targetSdkVersion < 29, 56, typedArray)).setUsesCleartextTraffic(bool(targetSdkVersion < 28, 36, typedArray)).setUiOptions(anInt(25, typedArray)).setCategory(anInt(-1, 43, typedArray)).setMaxAspectRatio(aFloat(44, typedArray)).setMinAspectRatio(aFloat(51, typedArray)).setBanner(resId(30, typedArray)).setDescriptionRes(resId(13, typedArray)).setIconRes(resId(2, typedArray)).setLogo(resId(22, typedArray)).setNetworkSecurityConfigRes(resId(41, typedArray)).setRoundIconRes(resId(42, typedArray)).setTheme(resId(0, typedArray)).setClassLoaderName(string(46, typedArray)).setRequiredAccountType(string(29, typedArray)).setRestrictedAccountType(string(28, typedArray)).setZygotePreloadName(string(52, typedArray)).setPermission(nonConfigString(0, 6, typedArray));
    }

    private ParseResult parseBaseAppChildTag(ParseInput parseInput, String str, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, int i) throws XmlPullParserException, IOException {
        str.hashCode();
        switch (str) {
            case "uses-library":
                return parseUsesLibrary(parseInput, parsingPackage, resources, xmlResourceParser);
            case "meta-data":
                ParseResult<Bundle> metaData = parseMetaData(parsingPackage, resources, xmlResourceParser, parsingPackage.getMetaData(), parseInput);
                if (metaData.isSuccess()) {
                    parsingPackage.setMetaData(metaData.getResult());
                }
                return metaData;
            case "processes":
                return parseProcesses(parseInput, parsingPackage, resources, xmlResourceParser, this.mSeparateProcesses, i);
            case "static-library":
                return parseStaticLibrary(parsingPackage, resources, xmlResourceParser, parseInput);
            case "uses-static-library":
                return parseUsesStaticLibrary(parseInput, parsingPackage, resources, xmlResourceParser);
            case "library":
                return parseLibrary(parsingPackage, resources, xmlResourceParser, parseInput);
            case "profileable":
                return parseProfileable(parseInput, parsingPackage, resources, xmlResourceParser);
            case "uses-package":
                return parseInput.success(null);
            default:
                return ParsingUtils.unknownTag("<application>", parsingPackage, xmlResourceParser, parseInput);
        }
    }

    private static ParseResult<ParsingPackage> parseStaticLibrary(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestStaticLibrary);
        try {
            String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
            int i = typedArrayObtainAttributes.getInt(1, -1);
            int i2 = typedArrayObtainAttributes.getInt(2, 0);
            if (nonResourceString != null && i >= 0) {
                return parsingPackage.getSharedUserId() != null ? parseInput.error(PackageManager.INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID, "sharedUserId not allowed in static shared library") : parsingPackage.getStaticSharedLibName() != null ? parseInput.error("Multiple static-shared libs for package " + parsingPackage.getPackageName()) : parseInput.success(parsingPackage.setStaticSharedLibName(nonResourceString.intern()).setStaticSharedLibVersion(PackageInfo.composeLongVersionCode(i2, i)).setStaticSharedLibrary(true));
            }
            return parseInput.error("Bad static-library declaration name: " + nonResourceString + " version: " + i);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseLibrary(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestLibrary);
        try {
            String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
            if (nonResourceString != null) {
                String strIntern = nonResourceString.intern();
                if (!ArrayUtils.contains(parsingPackage.getLibraryNames(), strIntern)) {
                    parsingPackage.addLibraryName(strIntern);
                }
            }
            return parseInput.success(parsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesStaticLibrary(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesStaticLibrary);
        try {
            String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
            int i = typedArrayObtainAttributes.getInt(1, -1);
            String nonResourceString2 = typedArrayObtainAttributes.getNonResourceString(2);
            if (nonResourceString != null && i >= 0 && nonResourceString2 != null) {
                if (parsingPackage.getUsesStaticLibraries().contains(nonResourceString)) {
                    return parseInput.error("Depending on multiple versions of static library " + nonResourceString);
                }
                String strIntern = nonResourceString.intern();
                String lowerCase = nonResourceString2.replace(SettingsStringUtil.DELIMITER, "").toLowerCase();
                String[] result = EmptyArray.STRING;
                if (parsingPackage.getTargetSdkVersion() >= 27) {
                    ParseResult<String[]> additionalCertificates = parseAdditionalCertificates(parseInput, resources, xmlResourceParser);
                    if (additionalCertificates.isError()) {
                        return parseInput.error((ParseResult<?>) additionalCertificates);
                    }
                    result = additionalCertificates.getResult();
                }
                String[] strArr = new String[result.length + 1];
                strArr[0] = lowerCase;
                System.arraycopy(result, 0, strArr, 1, result.length);
                return parseInput.success(parsingPackage.addUsesStaticLibrary(strIntern).addUsesStaticLibraryVersion(i).addUsesStaticLibraryCertDigests(strArr));
            }
            return parseInput.error("Bad uses-static-library declaration name: " + nonResourceString + " version: " + i + " certDigest" + nonResourceString2);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesLibrary(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesLibrary);
        try {
            String nonResourceString = typedArrayObtainAttributes.getNonResourceString(0);
            boolean z = typedArrayObtainAttributes.getBoolean(1, true);
            if (nonResourceString != null) {
                String strIntern = nonResourceString.intern();
                if (z) {
                    parsingPackage.addUsesLibrary(strIntern).removeUsesOptionalLibrary(strIntern);
                } else if (!ArrayUtils.contains(parsingPackage.getUsesLibraries(), strIntern)) {
                    parsingPackage.addUsesOptionalLibrary(strIntern);
                }
            }
            return parseInput.success(parsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseProcesses(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, String[] strArr, int i) throws XmlPullParserException, IOException {
        ParseResult<ArrayMap<String, ParsedProcess>> processes = ParsedProcessUtils.parseProcesses(strArr, parsingPackage, resources, xmlResourceParser, i, parseInput);
        if (processes.isError()) {
            return parseInput.error(processes);
        }
        return parseInput.success(parsingPackage.setProcesses(processes.getResult()));
    }

    private static ParseResult<ParsingPackage> parseProfileable(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestProfileable);
        try {
            return parseInput.success(parsingPackage.setProfileableByShell(parsingPackage.isProfileableByShell() || bool(false, 0, typedArrayObtainAttributes)));
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0073, code lost:
    
        return r6.success(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<java.lang.String[]> parseAdditionalCertificates(android.content.pm.parsing.result.ParseInput r6, android.content.res.Resources r7, android.content.res.XmlResourceParser r8) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            java.lang.String[] r0 = libcore.util.EmptyArray.STRING
            int r1 = r8.getDepth()
        L6:
            int r2 = r8.next()
            r3 = 1
            if (r2 == r3) goto L6f
            r3 = 3
            if (r2 != r3) goto L16
            int r3 = r8.getDepth()
            if (r3 <= r1) goto L6f
        L16:
            r3 = 2
            if (r2 == r3) goto L1a
            goto L6
        L1a:
            java.lang.String r2 = r8.getName()
            java.lang.String r3 = "additional-certificate"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L6
            int[] r2 = com.android.internal.R.styleable.AndroidManifestAdditionalCertificate
            android.content.res.TypedArray r2 = r7.obtainAttributes(r8, r2)
            r3 = 0
            java.lang.String r3 = r2.getNonResourceString(r3)     // Catch: java.lang.Throwable -> L6a
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L6a
            if (r4 == 0) goto L52
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
            r7.<init>()     // Catch: java.lang.Throwable -> L6a
            java.lang.String r8 = "Bad additional-certificate declaration with empty certDigest:"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> L6a
            java.lang.StringBuilder r7 = r7.append(r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L6a
            android.content.pm.parsing.result.ParseResult r6 = r6.error(r7)     // Catch: java.lang.Throwable -> L6a
            r2.recycle()
            return r6
        L52:
            java.lang.String r4 = ":"
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = r3.toLowerCase()     // Catch: java.lang.Throwable -> L6a
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            java.lang.Object[] r0 = com.android.internal.util.ArrayUtils.appendElement(r4, r0, r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Throwable -> L6a
            r2.recycle()
            goto L6
        L6a:
            r6 = move-exception
            r2.recycle()
            throw r6
        L6f:
            android.content.pm.parsing.result.ParseResult r6 = r6.success(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseAdditionalCertificates(android.content.pm.parsing.result.ParseInput, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsedActivity> generateAppDetailsHiddenActivity(ParseInput parseInput, ParsingPackage parsingPackage) {
        String packageName = parsingPackage.getPackageName();
        ParseResult<String> parseResultBuildTaskAffinityName = ComponentParseUtils.buildTaskAffinityName(packageName, packageName, ":app_details", parseInput);
        if (parseResultBuildTaskAffinityName.isError()) {
            return parseInput.error(parseResultBuildTaskAffinityName);
        }
        return parseInput.success(ParsedActivity.makeAppDetailsActivity(packageName, parsingPackage.getProcessName(), parsingPackage.getUiOptions(), parseResultBuildTaskAffinityName.getResult(), parsingPackage.isBaseHardwareAccelerated()));
    }

    private static boolean hasDomainURLs(ParsingPackage parsingPackage) {
        List<ParsedActivity> activities = parsingPackage.getActivities();
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            List<ParsedIntentInfo> intents = activities.get(i).getIntents();
            int size2 = intents.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ParsedIntentInfo parsedIntentInfo = intents.get(i2);
                if (parsedIntentInfo.hasAction("android.intent.action.VIEW") && parsedIntentInfo.hasAction("android.intent.action.VIEW") && (parsedIntentInfo.hasDataScheme(IntentFilter.SCHEME_HTTP) || parsedIntentInfo.hasDataScheme(IntentFilter.SCHEME_HTTPS))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void setMaxAspectRatio(ParsingPackage parsingPackage) {
        float f = parsingPackage.getTargetSdkVersion() < 26 ? 1.86f : 0.0f;
        float maxAspectRatio = parsingPackage.getMaxAspectRatio();
        if (maxAspectRatio != 0.0f) {
            f = maxAspectRatio;
        } else {
            Bundle metaData = parsingPackage.getMetaData();
            if (metaData != null && metaData.containsKey(PackageParser.METADATA_MAX_ASPECT_RATIO)) {
                f = metaData.getFloat(PackageParser.METADATA_MAX_ASPECT_RATIO, f);
            }
        }
        List<ParsedActivity> activities = parsingPackage.getActivities();
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            ParsedActivity parsedActivity = activities.get(i);
            if (parsedActivity.getMaxAspectRatio() == null) {
                parsedActivity.setMaxAspectRatio(parsedActivity.getResizeMode(), parsedActivity.getMetaData() != null ? parsedActivity.getMetaData().getFloat(PackageParser.METADATA_MAX_ASPECT_RATIO, f) : f);
            }
        }
    }

    private void setMinAspectRatio(ParsingPackage parsingPackage) {
        float minAspectRatio = parsingPackage.getMinAspectRatio();
        if (minAspectRatio == 0.0f) {
            if (parsingPackage.getTargetSdkVersion() >= 29) {
                minAspectRatio = 0.0f;
            } else {
                Callback callback = this.mCallback;
                minAspectRatio = (callback == null || !callback.hasFeature(PackageManager.FEATURE_WATCH)) ? 1.333f : 1.0f;
            }
        }
        List<ParsedActivity> activities = parsingPackage.getActivities();
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            ParsedActivity parsedActivity = activities.get(i);
            if (parsedActivity.getMinAspectRatio() == null) {
                parsedActivity.setMinAspectRatio(parsedActivity.getResizeMode(), minAspectRatio);
            }
        }
    }

    private void setSupportsSizeChanges(ParsingPackage parsingPackage) {
        Bundle metaData = parsingPackage.getMetaData();
        boolean z = metaData != null && metaData.getBoolean(PackageParser.METADATA_SUPPORTS_SIZE_CHANGES, false);
        List<ParsedActivity> activities = parsingPackage.getActivities();
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            ParsedActivity parsedActivity = activities.get(i);
            if (z || (parsedActivity.getMetaData() != null && parsedActivity.getMetaData().getBoolean(PackageParser.METADATA_SUPPORTS_SIZE_CHANGES, false))) {
                parsedActivity.setSupportsSizeChanges(true);
            }
        }
    }

    private static ParseResult<ParsingPackage> parseOverlay(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestResourceOverlay);
        try {
            String string = typedArrayObtainAttributes.getString(1);
            int iAnInt = anInt(0, 0, typedArrayObtainAttributes);
            if (string == null) {
                return parseInput.error("<overlay> does not specify a target package");
            }
            if (iAnInt < 0 || iAnInt > 9999) {
                return parseInput.error("<overlay> priority must be between 0 and 9999");
            }
            String string2 = typedArrayObtainAttributes.getString(5);
            String string3 = typedArrayObtainAttributes.getString(6);
            if (PackageParser.checkRequiredSystemProperties(string2, string3)) {
                return parseInput.success(parsingPackage.setOverlay(true).setOverlayTarget(string).setOverlayPriority(iAnInt).setOverlayTargetName(typedArrayObtainAttributes.getString(3)).setOverlayCategory(typedArrayObtainAttributes.getString(2)).setOverlayIsStatic(bool(false, 4, typedArrayObtainAttributes)));
            }
            String str = "Skipping target and overlay pair " + string + " and " + parsingPackage.getBaseCodePath() + ": overlay ignored due to required system property: " + string2 + " with value: " + string3;
            Slog.i("PackageParsing", str);
            return parseInput.skip(str);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseProtectedBroadcast(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestProtectedBroadcast);
        try {
            String strNonResString = nonResString(0, typedArrayObtainAttributes);
            if (strNonResString != null) {
                parsingPackage.addProtectedBroadcast(strNonResString);
            }
            return parseInput.success(parsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseSupportScreens(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestSupportsScreens);
        try {
            int iAnInt = anInt(0, 6, typedArrayObtainAttributes);
            int iAnInt2 = anInt(0, 7, typedArrayObtainAttributes);
            return parseInput.success(parsingPackage.setSupportsSmallScreens(anInt(1, 1, typedArrayObtainAttributes)).setSupportsNormalScreens(anInt(1, 2, typedArrayObtainAttributes)).setSupportsLargeScreens(anInt(1, 3, typedArrayObtainAttributes)).setSupportsExtraLargeScreens(anInt(1, 5, typedArrayObtainAttributes)).setResizeable(anInt(1, 4, typedArrayObtainAttributes)).setAnyDensity(anInt(1, 0, typedArrayObtainAttributes)).setRequiresSmallestWidthDp(iAnInt).setCompatibleWidthLimitDp(iAnInt2).setLargestWidthLimitDp(anInt(0, 8, typedArrayObtainAttributes)));
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseInstrumentation(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        ParseResult<ParsedInstrumentation> instrumentation = ParsedInstrumentationUtils.parseInstrumentation(parsingPackage, resources, xmlResourceParser, PackageParser.sUseRoundIcon, parseInput);
        if (instrumentation.isError()) {
            return parseInput.error(instrumentation);
        }
        return parseInput.success(parsingPackage.addInstrumentation(instrumentation.getResult()));
    }

    private static ParseResult<ParsingPackage> parseOriginalPackage(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestOriginalPackage);
        try {
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
            if (!parsingPackage.getPackageName().equals(nonConfigurationString)) {
                if (parsingPackage.getOriginalPackages().isEmpty()) {
                    parsingPackage.setRealPackage(parsingPackage.getPackageName());
                }
                parsingPackage.addOriginalPackage(nonConfigurationString);
            }
            return parseInput.success(parsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseAdoptPermissions(ParseInput parseInput, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestOriginalPackage);
        try {
            String strNonConfigString = nonConfigString(0, 0, typedArrayObtainAttributes);
            if (strNonConfigString != null) {
                parsingPackage.addAdoptPermission(strNonConfigString);
            }
            return parseInput.success(parsingPackage);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static void convertNewPermissions(ParsingPackage parsingPackage) {
        int length = PackageParser.NEW_PERMISSIONS.length;
        StringBuilder sb = null;
        for (int i = 0; i < length; i++) {
            PackageParser.NewPermissionInfo newPermissionInfo = PackageParser.NEW_PERMISSIONS[i];
            if (parsingPackage.getTargetSdkVersion() >= newPermissionInfo.sdkVersion) {
                break;
            }
            if (!parsingPackage.getRequestedPermissions().contains(newPermissionInfo.name)) {
                if (sb == null) {
                    sb = new StringBuilder(128);
                    sb.append(parsingPackage.getPackageName());
                    sb.append(": compat added ");
                } else {
                    sb.append(' ');
                }
                sb.append(newPermissionInfo.name);
                parsingPackage.addRequestedPermission(newPermissionInfo.name).addImplicitPermission(newPermissionInfo.name);
            }
        }
        if (sb != null) {
            Slog.i("PackageParsing", sb.toString());
        }
    }

    private static void convertSplitPermissions(ParsingPackage parsingPackage) {
        try {
            List<SplitPermissionInfoParcelable> splitPermissions = ActivityThread.getPermissionManager().getSplitPermissions();
            int size = splitPermissions.size();
            for (int i = 0; i < size; i++) {
                SplitPermissionInfoParcelable splitPermissionInfoParcelable = splitPermissions.get(i);
                List<String> requestedPermissions = parsingPackage.getRequestedPermissions();
                if (parsingPackage.getTargetSdkVersion() < splitPermissionInfoParcelable.getTargetSdk() && requestedPermissions.contains(splitPermissionInfoParcelable.getSplitPermission())) {
                    List<String> newPermissions = splitPermissionInfoParcelable.getNewPermissions();
                    for (int i2 = 0; i2 < newPermissions.size(); i2++) {
                        String str = newPermissions.get(i2);
                        if (!requestedPermissions.contains(str)) {
                            parsingPackage.addRequestedPermission(str).addImplicitPermission(str);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static void adjustPackageToBeUnresizeableAndUnpipable(ParsingPackage parsingPackage) {
        List<ParsedActivity> activities = parsingPackage.getActivities();
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            ParsedActivity parsedActivity = activities.get(i);
            parsedActivity.setResizeMode(0).setFlags(parsedActivity.getFlags() & (-4194305));
        }
    }

    private static ParseResult validateName(ParseInput parseInput, String str, boolean z, boolean z2) {
        int length = str.length();
        boolean z3 = false;
        boolean z4 = true;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                z4 = false;
            } else if (z4 || ((cCharAt < '0' || cCharAt > '9') && cCharAt != '_')) {
                if (cCharAt != '.') {
                    return parseInput.error("bad character '" + cCharAt + "'");
                }
                z3 = true;
                z4 = true;
            }
        }
        if (z2 && !FileUtils.isValidExtFilename(str)) {
            return parseInput.error("Invalid filename");
        }
        if (z3 || !z) {
            return parseInput.success(null);
        }
        return parseInput.error("must have at least one '.' separator");
    }

    public static ParseResult<Bundle> parseMetaData(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, Bundle bundle, ParseInput parseInput) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestMetaData);
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } finally {
                typedArrayObtainAttributes.recycle();
            }
        }
        String strSafeIntern = TextUtils.safeIntern(nonConfigString(0, 0, typedArrayObtainAttributes));
        if (strSafeIntern == null) {
            return parseInput.error("<meta-data> requires an android:name attribute");
        }
        TypedValue typedValuePeekValue = typedArrayObtainAttributes.peekValue(2);
        if (typedValuePeekValue != null && typedValuePeekValue.resourceId != 0) {
            bundle.putInt(strSafeIntern, typedValuePeekValue.resourceId);
        } else {
            TypedValue typedValuePeekValue2 = typedArrayObtainAttributes.peekValue(1);
            if (typedValuePeekValue2 != null) {
                if (typedValuePeekValue2.type == 3) {
                    CharSequence charSequenceCoerceToString = typedValuePeekValue2.coerceToString();
                    bundle.putString(strSafeIntern, charSequenceCoerceToString != null ? charSequenceCoerceToString.toString() : null);
                } else if (typedValuePeekValue2.type == 18) {
                    bundle.putBoolean(strSafeIntern, typedValuePeekValue2.data != 0);
                } else if (typedValuePeekValue2.type >= 16 && typedValuePeekValue2.type <= 31) {
                    bundle.putInt(strSafeIntern, typedValuePeekValue2.data);
                } else if (typedValuePeekValue2.type == 4) {
                    bundle.putFloat(strSafeIntern, typedValuePeekValue2.getFloat());
                } else {
                    Slog.w("PackageParsing", "<meta-data> only supports string, integer, float, color, boolean, and resource reference types: " + xmlResourceParser.getName() + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
                }
            } else {
                return parseInput.error("<meta-data> requires an android:value or android:resource attribute");
            }
        }
        return parseInput.success(bundle);
    }

    public static PackageParser.SigningDetails getSigningDetails(ParsingPackageRead parsingPackageRead, boolean z) throws PackageParser.PackageParserException {
        PackageParser.SigningDetails signingDetails = PackageParser.SigningDetails.UNKNOWN;
        ParseInput parseInputReset = ParseTypeImpl.forDefaultParsing().reset();
        Trace.traceBegin(262144L, "collectCertificates");
        try {
            ParseResult<PackageParser.SigningDetails> signingDetails2 = getSigningDetails(parseInputReset, parsingPackageRead.getBaseCodePath(), z, parsingPackageRead.isStaticSharedLibrary(), signingDetails, parsingPackageRead.getTargetSdkVersion());
            if (signingDetails2.isError()) {
                throw new PackageParser.PackageParserException(signingDetails2.getErrorCode(), signingDetails2.getErrorMessage(), signingDetails2.getException());
            }
            PackageParser.SigningDetails result = signingDetails2.getResult();
            String[] splitCodePaths = parsingPackageRead.getSplitCodePaths();
            if (!ArrayUtils.isEmpty(splitCodePaths)) {
                PackageParser.SigningDetails result2 = result;
                for (String str : splitCodePaths) {
                    ParseResult<PackageParser.SigningDetails> signingDetails3 = getSigningDetails(parseInputReset, str, z, parsingPackageRead.isStaticSharedLibrary(), result2, parsingPackageRead.getTargetSdkVersion());
                    if (signingDetails3.isError()) {
                        throw new PackageParser.PackageParserException(signingDetails3.getErrorCode(), signingDetails3.getErrorMessage(), signingDetails3.getException());
                    }
                    result2 = signingDetails3.getResult();
                }
                result = result2;
            }
            return result;
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    public static ParseResult<PackageParser.SigningDetails> getSigningDetails(ParseInput parseInput, String str, boolean z, boolean z2, PackageParser.SigningDetails signingDetails, int i) {
        PackageParser.SigningDetails signingDetailsVerify;
        int minimumSignatureSchemeVersionForTargetSdk = ApkSignatureVerifier.getMinimumSignatureSchemeVersionForTargetSdk(i);
        if (z2) {
            minimumSignatureSchemeVersionForTargetSdk = 2;
        }
        try {
            if (z) {
                signingDetailsVerify = ApkSignatureVerifier.unsafeGetCertsWithoutVerification(str, 1);
            } else {
                signingDetailsVerify = ApkSignatureVerifier.verify(str, minimumSignatureSchemeVersionForTargetSdk);
            }
            if (signingDetails == PackageParser.SigningDetails.UNKNOWN) {
                return parseInput.success(signingDetailsVerify);
            }
            if (!Signature.areExactMatch(signingDetails.signatures, signingDetailsVerify.signatures)) {
                return parseInput.error(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, str + " has mismatched certificates");
            }
            return parseInput.success(signingDetails);
        } catch (PackageParser.PackageParserException e) {
            return parseInput.error(-103, "Failed collecting certificates for " + str, e);
        }
    }

    private static boolean bool(boolean z, int i, TypedArray typedArray) {
        return typedArray.getBoolean(i, z);
    }

    private static float aFloat(float f, int i, TypedArray typedArray) {
        return typedArray.getFloat(i, f);
    }

    private static float aFloat(int i, TypedArray typedArray) {
        return typedArray.getFloat(i, 0.0f);
    }

    private static int anInt(int i, int i2, TypedArray typedArray) {
        return typedArray.getInt(i2, i);
    }

    private static int anInteger(int i, int i2, TypedArray typedArray) {
        return typedArray.getInteger(i2, i);
    }

    private static int anInt(int i, TypedArray typedArray) {
        return typedArray.getInt(i, 0);
    }

    private static int resId(int i, TypedArray typedArray) {
        return typedArray.getResourceId(i, 0);
    }

    private static String string(int i, TypedArray typedArray) {
        return typedArray.getString(i);
    }

    private static String nonConfigString(int i, int i2, TypedArray typedArray) {
        return typedArray.getNonConfigurationString(i2, i);
    }

    private static String nonResString(int i, TypedArray typedArray) {
        return typedArray.getNonResourceString(i);
    }
}
