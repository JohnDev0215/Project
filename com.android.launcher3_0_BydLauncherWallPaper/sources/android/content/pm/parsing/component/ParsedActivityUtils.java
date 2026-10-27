package android.content.pm.parsing.component;

import android.app.ActivityTaskManager;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageParser;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.IncidentManager;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsedActivityUtils {
    private static final String TAG = "PackageParsing";

    public static ParseResult<ParsedActivity> parseActivityOrReceiver(String[] strArr, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, int i, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        ParseInput parseInput2;
        String str;
        String packageName = parsingPackage.getPackageName();
        ParsedActivity parsedActivity = new ParsedActivity();
        boolean zEquals = IncidentManager.URI_PARAM_RECEIVER_CLASS.equals(xmlResourceParser.getName());
        String str2 = "<" + xmlResourceParser.getName() + ">";
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestActivity);
        try {
            try {
                ParseResult<ParsedActivity> mainComponent = ParsedMainComponentUtils.parseMainComponent(parsedActivity, str2, strArr, parsingPackage, typedArrayObtainAttributes, i, z, parseInput, 30, 17, 42, 5, 2, 1, 23, 3, 7, 44, 48);
                if (mainComponent.isError()) {
                    typedArrayObtainAttributes.recycle();
                    return mainComponent;
                }
                if (zEquals && parsingPackage.isCantSaveState()) {
                    str = packageName;
                    if (Objects.equals(parsedActivity.getProcessName(), str)) {
                        ParseResult<ParsedActivity> parseResultError = parseInput.error("Heavy-weight applications can not have receivers in main process");
                        typedArrayObtainAttributes.recycle();
                        return parseResultError;
                    }
                    parseInput2 = parseInput;
                } else {
                    parseInput2 = parseInput;
                    str = packageName;
                }
                typedArray = typedArrayObtainAttributes;
                try {
                    parsedActivity.theme = typedArray.getResourceId(0, 0);
                    parsedActivity.uiOptions = typedArray.getInt(26, parsingPackage.getUiOptions());
                    parsedActivity.flags |= ComponentParseUtils.flag(64, 19, parsingPackage.isAllowTaskReparenting(), typedArray) | ComponentParseUtils.flag(8, 18, typedArray) | ComponentParseUtils.flag(4, 11, typedArray) | ComponentParseUtils.flag(32, 13, typedArray) | ComponentParseUtils.flag(256, 22, typedArray) | ComponentParseUtils.flag(2, 10, typedArray) | ComponentParseUtils.flag(2048, 24, typedArray) | ComponentParseUtils.flag(1, 9, typedArray) | ComponentParseUtils.flag(128, 21, typedArray) | ComponentParseUtils.flag(1024, 39, typedArray) | ComponentParseUtils.flag(1024, 29, typedArray) | ComponentParseUtils.flag(16, 12, typedArray) | ComponentParseUtils.flag(536870912, 58, typedArray);
                    if (zEquals) {
                        parsedActivity.launchMode = 0;
                        parsedActivity.configChanges = 0;
                        parsedActivity.flags |= ComponentParseUtils.flag(1073741824, 28, typedArray);
                    } else {
                        parsedActivity.flags |= ComponentParseUtils.flag(512, 25, parsingPackage.isBaseHardwareAccelerated(), typedArray) | ComponentParseUtils.flag(Integer.MIN_VALUE, 31, typedArray) | ComponentParseUtils.flag(262144, 57, typedArray) | ComponentParseUtils.flag(8192, 35, typedArray) | ComponentParseUtils.flag(4096, 36, typedArray) | ComponentParseUtils.flag(16384, 37, typedArray) | ComponentParseUtils.flag(8388608, 51, typedArray) | ComponentParseUtils.flag(4194304, 41, typedArray) | ComponentParseUtils.flag(16777216, 52, typedArray) | ComponentParseUtils.flag(33554432, 56, typedArray);
                        parsedActivity.privateFlags |= ComponentParseUtils.flag(1, 54, typedArray);
                        parsedActivity.colorMode = typedArray.getInt(49, 0);
                        parsedActivity.documentLaunchMode = typedArray.getInt(33, 0);
                        parsedActivity.launchMode = typedArray.getInt(14, 0);
                        parsedActivity.lockTaskLaunchMode = typedArray.getInt(38, 0);
                        parsedActivity.maxRecents = typedArray.getInt(34, ActivityTaskManager.getDefaultAppRecentsLimitStatic());
                        parsedActivity.persistableMode = typedArray.getInteger(32, 0);
                        parsedActivity.requestedVrComponent = typedArray.getString(43);
                        parsedActivity.rotationAnimation = typedArray.getInt(46, -1);
                        parsedActivity.softInputMode = typedArray.getInt(20, 0);
                        parsedActivity.configChanges = PackageParser.getActivityConfigChanges(typedArray.getInt(16, 0), typedArray.getInt(47, 0));
                        int i2 = typedArray.getInt(15, -1);
                        int activityResizeMode = getActivityResizeMode(parsingPackage, typedArray, i2);
                        parsedActivity.screenOrientation = i2;
                        parsedActivity.resizeMode = activityResizeMode;
                        if (typedArray.hasValue(50) && typedArray.getType(50) == 4) {
                            parsedActivity.setMaxAspectRatio(activityResizeMode, typedArray.getFloat(50, 0.0f));
                        }
                        if (typedArray.hasValue(53) && typedArray.getType(53) == 4) {
                            parsedActivity.setMinAspectRatio(activityResizeMode, typedArray.getFloat(53, 0.0f));
                        }
                    }
                    ParseResult<String> parseResultBuildTaskAffinityName = ComponentParseUtils.buildTaskAffinityName(str, parsingPackage.getTaskAffinity(), typedArray.getNonConfigurationString(8, 1024), parseInput2);
                    if (parseResultBuildTaskAffinityName.isError()) {
                        ParseResult<ParsedActivity> parseResultError2 = parseInput2.error(parseResultBuildTaskAffinityName);
                        typedArray.recycle();
                        return parseResultError2;
                    }
                    parsedActivity.taskAffinity = parseResultBuildTaskAffinityName.getResult();
                    boolean z2 = typedArray.getBoolean(45, false);
                    if (z2) {
                        parsedActivity.flags |= 1048576;
                        parsingPackage.setVisibleToInstantApps(true);
                    }
                    ParseResult<ParsedActivity> activityOrAlias = parseActivityOrAlias(parsedActivity, parsingPackage, str2, xmlResourceParser, resources, typedArray, zEquals, false, z2, parseInput, 27, 4, 6);
                    typedArray.recycle();
                    return activityOrAlias;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                typedArray = typedArrayObtainAttributes;
            }
        } catch (Throwable th3) {
            th = th3;
            typedArray = typedArrayObtainAttributes;
        }
        typedArray.recycle();
        throw th;
    }

    public static ParseResult<ParsedActivity> parseActivityAlias(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestActivityAlias);
        try {
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(7, 1024);
            if (nonConfigurationString == null) {
                ParseResult<ParsedActivity> parseResultError = parseInput.error("<activity-alias> does not specify android:targetActivity");
                typedArrayObtainAttributes.recycle();
                return parseResultError;
            }
            String packageName = parsingPackage.getPackageName();
            String strBuildClassName = ParsingUtils.buildClassName(packageName, nonConfigurationString);
            if (strBuildClassName == null) {
                ParseResult<ParsedActivity> parseResultError2 = parseInput.error("Empty class name in package " + packageName);
                typedArrayObtainAttributes.recycle();
                return parseResultError2;
            }
            ParsedActivity parsedActivity = null;
            List<ParsedActivity> activities = parsingPackage.getActivities();
            int size = ArrayUtils.size(activities);
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                ParsedActivity parsedActivity2 = activities.get(i);
                if (strBuildClassName.equals(parsedActivity2.getName())) {
                    parsedActivity = parsedActivity2;
                    break;
                }
                i++;
            }
            if (parsedActivity == null) {
                ParseResult<ParsedActivity> parseResultError3 = parseInput.error("<activity-alias> target activity " + strBuildClassName + " not found in manifest with activities = " + parsingPackage.getActivities() + ", parsedActivities = " + activities);
                typedArrayObtainAttributes.recycle();
                return parseResultError3;
            }
            ParsedActivity parsedActivityMakeAlias = ParsedActivity.makeAlias(strBuildClassName, parsedActivity);
            String str = "<" + xmlResourceParser.getName() + ">";
            typedArray = typedArrayObtainAttributes;
            try {
                ParseResult<ParsedActivity> mainComponent = ParsedMainComponentUtils.parseMainComponent(parsedActivityMakeAlias, str, null, parsingPackage, typedArrayObtainAttributes, 0, z, parseInput, 10, 6, null, 4, 1, 0, 8, 2, null, 11, null);
                if (mainComponent.isError()) {
                    typedArray.recycle();
                    return mainComponent;
                }
                ParseResult<ParsedActivity> activityOrAlias = parseActivityOrAlias(parsedActivityMakeAlias, parsingPackage, str, xmlResourceParser, resources, typedArray, false, true, (parsedActivityMakeAlias.getFlags() & 1048576) != 0, parseInput, 9, 3, 5);
                typedArray.recycle();
                return activityOrAlias;
            } catch (Throwable th) {
                th = th;
                typedArray.recycle();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            typedArray = typedArrayObtainAttributes;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0142, code lost:
    
        r0 = resolveWindowLayout(r15, r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x014a, code lost:
    
        if (r0.isError() == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0150, code lost:
    
        return r24.error(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0151, code lost:
    
        r15.windowLayout = r0.getResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0159, code lost:
    
        if (r13 != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0163, code lost:
    
        if (r15.getIntents().size() <= 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0165, code lost:
    
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0166, code lost:
    
        r15.exported = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x016c, code lost:
    
        return r24.success(r15);
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x013d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0072 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.component.ParsedActivity> parseActivityOrAlias(android.content.pm.parsing.component.ParsedActivity r15, android.content.pm.parsing.ParsingPackage r16, java.lang.String r17, android.content.res.XmlResourceParser r18, android.content.res.Resources r19, android.content.res.TypedArray r20, boolean r21, boolean r22, boolean r23, android.content.pm.parsing.result.ParseInput r24, int r25, int r26, int r27) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedActivityUtils.parseActivityOrAlias(android.content.pm.parsing.component.ParsedActivity, android.content.pm.parsing.ParsingPackage, java.lang.String, android.content.res.XmlResourceParser, android.content.res.Resources, android.content.res.TypedArray, boolean, boolean, boolean, android.content.pm.parsing.result.ParseInput, int, int, int):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsedIntentInfo> parseIntentFilter(ParsingPackage parsingPackage, ParsedActivity parsedActivity, boolean z, boolean z2, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) throws XmlPullParserException, IOException {
        ParseResult<ParsedIntentInfo> intentFilter = ParsedMainComponentUtils.parseIntentFilter(parsedActivity, parsingPackage, resources, xmlResourceParser, z2, true, true, z, true, parseInput);
        if (intentFilter.isError()) {
            return parseInput.error(intentFilter);
        }
        ParsedIntentInfo result = intentFilter.getResult();
        if (result != null) {
            if (result.isVisibleToInstantApp()) {
                parsedActivity.flags |= 1048576;
            }
            if (result.isImplicitlyVisibleToInstantApp()) {
                parsedActivity.flags |= 2097152;
            }
        }
        return parseInput.success(result);
    }

    private static int getActivityResizeMode(ParsingPackage parsingPackage, TypedArray typedArray, int i) {
        Boolean resizeableActivity = parsingPackage.getResizeableActivity();
        if (typedArray.hasValue(40) || resizeableActivity != null) {
            return typedArray.getBoolean(40, resizeableActivity != null && resizeableActivity.booleanValue()) ? 2 : 0;
        }
        if (parsingPackage.isResizeableActivityViaSdkVersion()) {
            return 1;
        }
        if (ActivityInfo.isFixedOrientationPortrait(i)) {
            return 6;
        }
        if (ActivityInfo.isFixedOrientationLandscape(i)) {
            return 5;
        }
        return i == 14 ? 7 : 4;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[Catch: all -> 0x005e, TryCatch #0 {all -> 0x005e, blocks: (B:3:0x0007, B:5:0x0013, B:10:0x0023, B:12:0x002a, B:16:0x003a, B:14:0x0031, B:7:0x001b), top: B:22:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.ActivityInfo.WindowLayout> parseLayout(android.content.res.Resources r12, android.util.AttributeSet r13, android.content.pm.parsing.result.ParseInput r14) {
        /*
            int[] r0 = com.android.internal.R.styleable.AndroidManifestLayout
            android.content.res.TypedArray r12 = r12.obtainAttributes(r13, r0)
            r13 = 3
            int r0 = r12.getType(r13)     // Catch: java.lang.Throwable -> L5e
            r1 = 5
            r2 = 6
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r4 = 1
            r5 = -1
            if (r0 != r2) goto L19
            float r13 = r12.getFraction(r13, r4, r4, r3)     // Catch: java.lang.Throwable -> L5e
            r6 = r13
            goto L22
        L19:
            if (r0 != r1) goto L21
            int r13 = r12.getDimensionPixelSize(r13, r5)     // Catch: java.lang.Throwable -> L5e
            r6 = r3
            goto L23
        L21:
            r6 = r3
        L22:
            r13 = r5
        L23:
            r0 = 4
            int r7 = r12.getType(r0)     // Catch: java.lang.Throwable -> L5e
            if (r7 != r2) goto L2f
            float r3 = r12.getFraction(r0, r4, r4, r3)     // Catch: java.lang.Throwable -> L5e
            goto L38
        L2f:
            if (r7 != r1) goto L38
            int r0 = r12.getDimensionPixelSize(r0, r5)     // Catch: java.lang.Throwable -> L5e
            r7 = r3
            r3 = r0
            goto L3a
        L38:
            r7 = r3
            r3 = r5
        L3a:
            r0 = 0
            r1 = 17
            int r8 = r12.getInt(r0, r1)     // Catch: java.lang.Throwable -> L5e
            int r9 = r12.getDimensionPixelSize(r4, r5)     // Catch: java.lang.Throwable -> L5e
            r0 = 2
            int r10 = r12.getDimensionPixelSize(r0, r5)     // Catch: java.lang.Throwable -> L5e
            android.content.pm.ActivityInfo$WindowLayout r11 = new android.content.pm.ActivityInfo$WindowLayout     // Catch: java.lang.Throwable -> L5e
            r0 = r11
            r1 = r13
            r2 = r6
            r4 = r7
            r5 = r8
            r6 = r9
            r7 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L5e
            android.content.pm.parsing.result.ParseResult r13 = r14.success(r11)     // Catch: java.lang.Throwable -> L5e
            r12.recycle()
            return r13
        L5e:
            r13 = move-exception
            r12.recycle()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedActivityUtils.parseLayout(android.content.res.Resources, android.util.AttributeSet, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ActivityInfo.WindowLayout> resolveWindowLayout(ParsedActivity parsedActivity, ParseInput parseInput) {
        if (parsedActivity.metaData == null || !parsedActivity.metaData.containsKey(PackageParser.METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY)) {
            return parseInput.success(parsedActivity.windowLayout);
        }
        if (parsedActivity.windowLayout != null && parsedActivity.windowLayout.windowLayoutAffinity != null) {
            return parseInput.success(parsedActivity.windowLayout);
        }
        String string = parsedActivity.metaData.getString(PackageParser.METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY);
        ActivityInfo.WindowLayout windowLayout = parsedActivity.windowLayout;
        if (windowLayout == null) {
            windowLayout = new ActivityInfo.WindowLayout(-1, -1.0f, -1, -1.0f, 0, -1, -1);
        }
        windowLayout.windowLayoutAffinity = string;
        return parseInput.success(windowLayout);
    }
}
