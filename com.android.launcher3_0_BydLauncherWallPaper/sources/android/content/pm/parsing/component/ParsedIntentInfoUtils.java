package android.content.pm.parsing.component;

import android.content.IntentFilter;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import com.android.internal.R;

/* JADX INFO: loaded from: classes.dex */
public class ParsedIntentInfoUtils {
    private static final String TAG = "PackageParsing";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x005d A[SYNTHETIC] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.component.ParsedIntentInfo> parseIntentInfo(java.lang.String r16, android.content.pm.parsing.ParsingPackage r17, android.content.res.Resources r18, android.content.res.XmlResourceParser r19, boolean r20, boolean r21, android.content.pm.parsing.result.ParseInput r22) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedIntentInfoUtils.parseIntentInfo(java.lang.String, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, boolean, boolean, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsedIntentInfo> parseData(ParsedIntentInfo parsedIntentInfo, Resources resources, XmlResourceParser xmlResourceParser, boolean z, ParseInput parseInput) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestData);
        try {
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
            if (nonConfigurationString != null) {
                parsedIntentInfo.addDataType(nonConfigurationString);
            }
            String nonConfigurationString2 = typedArrayObtainAttributes.getNonConfigurationString(10, 0);
            if (nonConfigurationString2 != null) {
                parsedIntentInfo.addMimeGroup(nonConfigurationString2);
            }
            String nonConfigurationString3 = typedArrayObtainAttributes.getNonConfigurationString(1, 0);
            if (nonConfigurationString3 != null) {
                parsedIntentInfo.addDataScheme(nonConfigurationString3);
            }
            String nonConfigurationString4 = typedArrayObtainAttributes.getNonConfigurationString(7, 0);
            if (nonConfigurationString4 != null) {
                parsedIntentInfo.addDataSchemeSpecificPart(nonConfigurationString4, 0);
            }
            String nonConfigurationString5 = typedArrayObtainAttributes.getNonConfigurationString(8, 0);
            if (nonConfigurationString5 != null) {
                parsedIntentInfo.addDataSchemeSpecificPart(nonConfigurationString5, 1);
            }
            String nonConfigurationString6 = typedArrayObtainAttributes.getNonConfigurationString(9, 0);
            if (nonConfigurationString6 != null) {
                if (!z) {
                    return parseInput.error("sspPattern not allowed here; ssp must be literal");
                }
                parsedIntentInfo.addDataSchemeSpecificPart(nonConfigurationString6, 2);
            }
            String nonConfigurationString7 = typedArrayObtainAttributes.getNonConfigurationString(2, 0);
            String nonConfigurationString8 = typedArrayObtainAttributes.getNonConfigurationString(3, 0);
            if (nonConfigurationString7 != null) {
                parsedIntentInfo.addDataAuthority(nonConfigurationString7, nonConfigurationString8);
            }
            String nonConfigurationString9 = typedArrayObtainAttributes.getNonConfigurationString(4, 0);
            if (nonConfigurationString9 != null) {
                parsedIntentInfo.addDataPath(nonConfigurationString9, 0);
            }
            String nonConfigurationString10 = typedArrayObtainAttributes.getNonConfigurationString(5, 0);
            if (nonConfigurationString10 != null) {
                parsedIntentInfo.addDataPath(nonConfigurationString10, 1);
            }
            String nonConfigurationString11 = typedArrayObtainAttributes.getNonConfigurationString(6, 0);
            if (nonConfigurationString11 != null) {
                if (!z) {
                    return parseInput.error("pathPattern not allowed here; path must be literal");
                }
                parsedIntentInfo.addDataPath(nonConfigurationString11, 2);
            }
            String nonConfigurationString12 = typedArrayObtainAttributes.getNonConfigurationString(11, 0);
            if (nonConfigurationString12 != null) {
                if (!z) {
                    return parseInput.error("pathAdvancedPattern not allowed here; path must be literal");
                }
                parsedIntentInfo.addDataPath(nonConfigurationString12, 3);
            }
            return parseInput.success(null);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            return parseInput.error(e.toString());
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }
}
