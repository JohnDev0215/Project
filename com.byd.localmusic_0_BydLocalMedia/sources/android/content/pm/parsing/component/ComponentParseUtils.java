package android.content.pm.parsing.component;

import android.content.Intent;
import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.TypedArray;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ComponentParseUtils {
    private static final String TAG = "PackageParsing";

    public static boolean isImplicitlyExposedIntent(ParsedIntentInfo parsedIntentInfo) {
        return parsedIntentInfo.hasCategory(Intent.CATEGORY_BROWSABLE) || parsedIntentInfo.hasAction(Intent.ACTION_SEND) || parsedIntentInfo.hasAction(Intent.ACTION_SENDTO) || parsedIntentInfo.hasAction(Intent.ACTION_SEND_MULTIPLE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003d, code lost:
    
        return r8.success(r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <Component extends android.content.pm.parsing.component.ParsedComponent> android.content.pm.parsing.result.ParseResult<Component> parseAllMetaData(android.content.pm.parsing.ParsingPackage r3, android.content.res.Resources r4, android.content.res.XmlResourceParser r5, java.lang.String r6, Component r7, android.content.pm.parsing.result.ParseInput r8) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r5.getDepth()
        L4:
            int r1 = r5.next()
            r2 = 1
            if (r1 == r2) goto L39
            r2 = 3
            if (r1 != r2) goto L14
            int r2 = r5.getDepth()
            if (r2 <= r0) goto L39
        L14:
            r2 = 2
            if (r1 == r2) goto L18
            goto L4
        L18:
            java.lang.String r1 = r5.getName()
            java.lang.String r2 = "meta-data"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L2a
            android.content.pm.parsing.result.ParseResult r1 = android.content.pm.parsing.component.ParsedComponentUtils.addMetaData(r7, r3, r4, r5, r8)
            goto L2e
        L2a:
            android.content.pm.parsing.result.ParseResult r1 = android.content.pm.parsing.ParsingUtils.unknownTag(r6, r3, r5, r8)
        L2e:
            boolean r2 = r1.isError()
            if (r2 == 0) goto L4
            android.content.pm.parsing.result.ParseResult r3 = r8.error(r1)
            return r3
        L39:
            android.content.pm.parsing.result.ParseResult r3 = r8.success(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ComponentParseUtils.parseAllMetaData(android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String, android.content.pm.parsing.component.ParsedComponent, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }

    public static ParseResult<String> buildProcessName(String str, String str2, CharSequence charSequence, int i, String[] strArr, ParseInput parseInput) {
        if ((i & 2) != 0 && !"system".contentEquals(charSequence)) {
            if (str2 != null) {
                str = str2;
            }
            return parseInput.success(str);
        }
        if (strArr != null) {
            for (int length = strArr.length - 1; length >= 0; length--) {
                String str3 = strArr[length];
                if (str3.equals(str) || str3.equals(str2) || str3.contentEquals(charSequence)) {
                    return parseInput.success(str);
                }
            }
        }
        if (charSequence == null || charSequence.length() <= 0) {
            return parseInput.success(str2);
        }
        return parseInput.success(TextUtils.safeIntern(buildCompoundName(str, charSequence, "process", parseInput).getResult()));
    }

    public static ParseResult<String> buildTaskAffinityName(String str, String str2, CharSequence charSequence, ParseInput parseInput) {
        if (charSequence == null) {
            return parseInput.success(str2);
        }
        if (charSequence.length() <= 0) {
            return parseInput.success(null);
        }
        return buildCompoundName(str, charSequence, "taskAffinity", parseInput);
    }

    public static ParseResult<String> buildCompoundName(String str, CharSequence charSequence, String str2, ParseInput parseInput) {
        String string = charSequence.toString();
        char cCharAt = string.charAt(0);
        if (str != null && cCharAt == ':') {
            if (string.length() < 2) {
                return parseInput.error("Bad " + str2 + " name " + string + " in package " + str + ": must be at least two characters");
            }
            String strValidateName = PackageParser.validateName(string.substring(1), false, false);
            if (strValidateName != null) {
                return parseInput.error("Invalid " + str2 + " name " + string + " in package " + str + ": " + strValidateName);
            }
            return parseInput.success(str + string);
        }
        String strValidateName2 = PackageParser.validateName(string, true, false);
        if (strValidateName2 != null && !"system".equals(string)) {
            return parseInput.error("Invalid " + str2 + " name " + string + " in package " + str + ": " + strValidateName2);
        }
        return parseInput.success(string);
    }

    public static int flag(int i, int i2, TypedArray typedArray) {
        if (typedArray.getBoolean(i2, false)) {
            return i;
        }
        return 0;
    }

    public static int flag(int i, int i2, boolean z, TypedArray typedArray) {
        if (typedArray.getBoolean(i2, z)) {
            return i;
        }
        return 0;
    }

    public static CharSequence getNonLocalizedLabel(ParsedComponent parsedComponent) {
        return parsedComponent.nonLocalizedLabel;
    }

    public static int getIcon(ParsedComponent parsedComponent) {
        return parsedComponent.icon;
    }

    public static boolean isMatch(PackageUserState packageUserState, boolean z, boolean z2, ParsedMainComponent parsedMainComponent, int i) {
        return packageUserState.isMatch(z, z2, parsedMainComponent.isEnabled(), parsedMainComponent.isDirectBootAware(), parsedMainComponent.getName(), i);
    }

    public static boolean isEnabled(PackageUserState packageUserState, boolean z, ParsedMainComponent parsedMainComponent, int i) {
        return packageUserState.isEnabled(z, parsedMainComponent.isEnabled(), parsedMainComponent.getName(), i);
    }
}
