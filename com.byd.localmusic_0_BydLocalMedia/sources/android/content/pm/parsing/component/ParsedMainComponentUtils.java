package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
class ParsedMainComponentUtils {
    private static final String TAG = "PackageParsing";

    ParsedMainComponentUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <Component extends android.content.pm.parsing.component.ParsedMainComponent> android.content.pm.parsing.result.ParseResult<Component> parseMainComponent(Component r16, java.lang.String r17, java.lang.String[] r18, android.content.pm.parsing.ParsingPackage r19, android.content.res.TypedArray r20, int r21, boolean r22, android.content.pm.parsing.result.ParseInput r23, int r24, int r25, java.lang.Integer r26, java.lang.Integer r27, int r28, int r29, int r30, int r31, java.lang.Integer r32, int r33, java.lang.Integer r34) {
        /*
            r13 = r16
            r14 = r20
            r15 = r23
            java.lang.Integer r7 = java.lang.Integer.valueOf(r25)
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r20
            r4 = r22
            r5 = r23
            r6 = r24
            r8 = r28
            r9 = r29
            r10 = r30
            r11 = r31
            r12 = r33
            android.content.pm.parsing.result.ParseResult r0 = android.content.pm.parsing.component.ParsedComponentUtils.parseComponent(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            boolean r1 = r0.isError()
            if (r1 == 0) goto L2d
            return r0
        L2d:
            r0 = 1
            r1 = 0
            if (r26 == 0) goto L47
            int r2 = r26.intValue()
            boolean r2 = r14.getBoolean(r2, r1)
            r13.directBootAware = r2
            boolean r2 = r16.isDirectBootAware()
            if (r2 == 0) goto L47
            r2 = r19
            r2.setPartiallyDirectBootAware(r0)
            goto L49
        L47:
            r2 = r19
        L49:
            if (r27 == 0) goto L55
            int r3 = r27.intValue()
            boolean r0 = r14.getBoolean(r3, r0)
            r13.enabled = r0
        L55:
            if (r32 == 0) goto L9e
            int r0 = r19.getTargetSdkVersion()
            r3 = 8
            if (r0 < r3) goto L6a
            int r0 = r32.intValue()
            r3 = 1024(0x400, float:1.435E-42)
            java.lang.String r0 = r14.getNonConfigurationString(r0, r3)
            goto L72
        L6a:
            int r0 = r32.intValue()
            java.lang.String r0 = r14.getNonResourceString(r0)
        L72:
            java.lang.String r3 = r19.getPackageName()
            java.lang.String r2 = r19.getProcessName()
            r24 = r3
            r25 = r2
            r26 = r0
            r27 = r21
            r28 = r18
            r29 = r23
            android.content.pm.parsing.result.ParseResult r0 = android.content.pm.parsing.component.ComponentParseUtils.buildProcessName(r24, r25, r26, r27, r28, r29)
            boolean r2 = r0.isError()
            if (r2 == 0) goto L95
            android.content.pm.parsing.result.ParseResult r0 = r15.error(r0)
            return r0
        L95:
            java.lang.Object r0 = r0.getResult()
            java.lang.String r0 = (java.lang.String) r0
            r13.setProcessName(r0)
        L9e:
            if (r34 == 0) goto Laa
            int r0 = r34.intValue()
            java.lang.String r0 = r14.getNonConfigurationString(r0, r1)
            r13.splitName = r0
        Laa:
            android.content.pm.parsing.result.ParseResult r0 = r15.success(r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedMainComponentUtils.parseMainComponent(android.content.pm.parsing.component.ParsedMainComponent, java.lang.String, java.lang.String[], android.content.pm.parsing.ParsingPackage, android.content.res.TypedArray, int, boolean, android.content.pm.parsing.result.ParseInput, int, int, java.lang.Integer, java.lang.Integer, int, int, int, int, java.lang.Integer, int, java.lang.Integer):android.content.pm.parsing.result.ParseResult");
    }

    static ParseResult<ParsedIntentInfo> parseIntentFilter(ParsedMainComponent parsedMainComponent, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, ParseInput parseInput) throws XmlPullParserException, IOException {
        int i;
        ParseResult<ParsedIntentInfo> intentInfo = ParsedIntentInfoUtils.parseIntentInfo(parsedMainComponent.getName(), parsingPackage, resources, xmlResourceParser, z2, z3, parseInput);
        if (intentInfo.isError()) {
            return parseInput.error(intentInfo);
        }
        ParsedIntentInfo result = intentInfo.getResult();
        if (result.countActions() == 0 && z5) {
            Slog.w("PackageParsing", "No actions in " + xmlResourceParser.getName() + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
            return parseInput.success(null);
        }
        if (z) {
            i = 1;
        } else {
            i = (z4 && ComponentParseUtils.isImplicitlyExposedIntent(result)) ? 2 : 0;
        }
        result.setVisibilityToInstantApp(i);
        return parseInput.success(intentInfo.getResult());
    }
}
