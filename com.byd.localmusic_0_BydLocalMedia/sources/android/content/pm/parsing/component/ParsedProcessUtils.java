package android.content.pm.parsing.component;

import android.Manifest;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import com.android.internal.R;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsedProcessUtils {
    private static final String TAG = "PackageParsing";

    private static ParseResult<Set<String>> parseDenyPermission(Set<String> set, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestDenyPermission);
        try {
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
            if (nonConfigurationString != null && nonConfigurationString.equals(Manifest.permission.INTERNET)) {
                set = CollectionUtils.add(set, nonConfigurationString);
            }
            typedArrayObtainAttributes.recycle();
            XmlUtils.skipCurrentTag(xmlResourceParser);
            return parseInput.success(set);
        } catch (Throwable th) {
            typedArrayObtainAttributes.recycle();
            throw th;
        }
    }

    private static ParseResult<Set<String>> parseAllowPermission(Set<String> set, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestAllowPermission);
        try {
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
            if (nonConfigurationString != null && nonConfigurationString.equals(Manifest.permission.INTERNET)) {
                set = CollectionUtils.remove(set, nonConfigurationString);
            }
            typedArrayObtainAttributes.recycle();
            XmlUtils.skipCurrentTag(xmlResourceParser);
            return parseInput.success(set);
        } catch (Throwable th) {
            typedArrayObtainAttributes.recycle();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00df, code lost:
    
        return r15.success(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x005f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.component.ParsedProcess> parseProcess(java.util.Set<java.lang.String> r9, java.lang.String[] r10, android.content.pm.parsing.ParsingPackage r11, android.content.res.Resources r12, android.content.res.XmlResourceParser r13, int r14, android.content.pm.parsing.result.ParseInput r15) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedProcessUtils.parseProcess(java.util.Set, java.lang.String[], android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d1, code lost:
    
        return r15.success(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.pm.parsing.result.ParseResult<android.util.ArrayMap<java.lang.String, android.content.pm.parsing.component.ParsedProcess>> parseProcesses(java.lang.String[] r10, android.content.pm.parsing.ParsingPackage r11, android.content.res.Resources r12, android.content.res.XmlResourceParser r13, int r14, android.content.pm.parsing.result.ParseInput r15) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedProcessUtils.parseProcesses(java.lang.String[], android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }
}
