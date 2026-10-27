package android.content.pm.parsing.component;

import android.content.pm.PathPermission;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.PatternMatcher;
import android.util.Slog;
import com.android.internal.R;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsedProviderUtils {
    private static final String TAG = "PackageParsing";

    public static ParseResult<ParsedProvider> parseProvider(String[] strArr, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, int i, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        int targetSdkVersion = parsingPackage.getTargetSdkVersion();
        String packageName = parsingPackage.getPackageName();
        ParsedProvider parsedProvider = new ParsedProvider();
        String name = xmlResourceParser.getName();
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestProvider);
        try {
            try {
                ParseResult<ParsedProvider> mainComponent = ParsedMainComponentUtils.parseMainComponent(parsedProvider, name, strArr, parsingPackage, typedArrayObtainAttributes, i, z, parseInput, 17, 14, 18, 6, 1, 0, 15, 2, 8, 19, 21);
                if (!mainComponent.isError()) {
                    typedArray = typedArrayObtainAttributes;
                    try {
                        String nonConfigurationString = typedArray.getNonConfigurationString(10, 0);
                        parsedProvider.exported = typedArray.getBoolean(7, targetSdkVersion < 17);
                        parsedProvider.syncable = typedArray.getBoolean(11, false);
                        String nonConfigurationString2 = typedArray.getNonConfigurationString(3, 0);
                        String nonConfigurationString3 = typedArray.getNonConfigurationString(4, 0);
                        if (nonConfigurationString3 == null) {
                            nonConfigurationString3 = nonConfigurationString2;
                        }
                        if (nonConfigurationString3 == null) {
                            parsedProvider.setReadPermission(parsingPackage.getPermission());
                        } else {
                            parsedProvider.setReadPermission(nonConfigurationString3);
                        }
                        String nonConfigurationString4 = typedArray.getNonConfigurationString(5, 0);
                        if (nonConfigurationString4 != null) {
                            nonConfigurationString2 = nonConfigurationString4;
                        }
                        if (nonConfigurationString2 == null) {
                            parsedProvider.setWritePermission(parsingPackage.getPermission());
                        } else {
                            parsedProvider.setWritePermission(nonConfigurationString2);
                        }
                        parsedProvider.grantUriPermissions = typedArray.getBoolean(13, false);
                        parsedProvider.forceUriPermissions = typedArray.getBoolean(22, false);
                        parsedProvider.multiProcess = typedArray.getBoolean(9, false);
                        parsedProvider.initOrder = typedArray.getInt(12, 0);
                        parsedProvider.flags |= ComponentParseUtils.flag(1073741824, 16, typedArray);
                        boolean z2 = typedArray.getBoolean(20, false);
                        if (z2) {
                            parsedProvider.flags |= 1048576;
                            parsingPackage.setVisibleToInstantApps(true);
                        }
                        typedArray.recycle();
                        if (parsingPackage.isCantSaveState() && Objects.equals(parsedProvider.getProcessName(), packageName)) {
                            return parseInput.error("Heavy-weight applications can not have providers in main process");
                        }
                        if (nonConfigurationString == null) {
                            return parseInput.error("<provider> does not include authorities attribute");
                        }
                        if (nonConfigurationString.length() <= 0) {
                            return parseInput.error("<provider> has empty authorities attribute");
                        }
                        parsedProvider.setAuthority(nonConfigurationString);
                        return parseProviderTags(parsingPackage, name, resources, xmlResourceParser, z2, parsedProvider, parseInput);
                    } catch (Throwable th) {
                        th = th;
                        typedArray.recycle();
                        throw th;
                    }
                }
                typedArrayObtainAttributes.recycle();
                return mainComponent;
            } catch (Throwable th2) {
                th = th2;
                typedArray = typedArrayObtainAttributes;
            }
        } catch (Throwable th3) {
            th = th3;
            typedArray = typedArrayObtainAttributes;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b9, code lost:
    
        return r22.success(r21);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.component.ParsedProvider> parseProviderTags(android.content.pm.parsing.ParsingPackage r16, java.lang.String r17, android.content.res.Resources r18, android.content.res.XmlResourceParser r19, boolean r20, android.content.pm.parsing.component.ParsedProvider r21, android.content.pm.parsing.result.ParseInput r22) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedProviderUtils.parseProviderTags(android.content.pm.parsing.ParsingPackage, java.lang.String, android.content.res.Resources, android.content.res.XmlResourceParser, boolean, android.content.pm.parsing.component.ParsedProvider, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsedProvider> parseGrantUriPermission(ParsedProvider parsedProvider, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestGrantUriPermission);
        try {
            String name = xmlResourceParser.getName();
            PatternMatcher patternMatcher = null;
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(2, 0);
            if (nonConfigurationString != null) {
                patternMatcher = new PatternMatcher(nonConfigurationString, 2);
            } else {
                String nonConfigurationString2 = typedArrayObtainAttributes.getNonConfigurationString(1, 0);
                if (nonConfigurationString2 != null) {
                    patternMatcher = new PatternMatcher(nonConfigurationString2, 1);
                } else {
                    String nonConfigurationString3 = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
                    if (nonConfigurationString3 != null) {
                        patternMatcher = new PatternMatcher(nonConfigurationString3, 0);
                    }
                }
            }
            if (patternMatcher != null) {
                if (parsedProvider.uriPermissionPatterns == null) {
                    parsedProvider.uriPermissionPatterns = new PatternMatcher[1];
                    parsedProvider.uriPermissionPatterns[0] = patternMatcher;
                } else {
                    int length = parsedProvider.uriPermissionPatterns.length;
                    PatternMatcher[] patternMatcherArr = new PatternMatcher[length + 1];
                    System.arraycopy(parsedProvider.uriPermissionPatterns, 0, patternMatcherArr, 0, length);
                    patternMatcherArr[length] = patternMatcher;
                    parsedProvider.uriPermissionPatterns = patternMatcherArr;
                }
                parsedProvider.grantUriPermissions = true;
            } else {
                Slog.w("PackageParsing", "Unknown element under <path-permission>: " + name + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
            }
            return parseInput.success(parsedProvider);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static ParseResult<ParsedProvider> parsePathPermission(ParsedProvider parsedProvider, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) {
        boolean z;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPathPermission);
        try {
            String name = xmlResourceParser.getName();
            String nonConfigurationString = typedArrayObtainAttributes.getNonConfigurationString(0, 0);
            String nonConfigurationString2 = typedArrayObtainAttributes.getNonConfigurationString(1, 0);
            if (nonConfigurationString2 == null) {
                nonConfigurationString2 = nonConfigurationString;
            }
            String nonConfigurationString3 = typedArrayObtainAttributes.getNonConfigurationString(2, 0);
            if (nonConfigurationString3 != null) {
                nonConfigurationString = nonConfigurationString3;
            }
            if (nonConfigurationString2 != null) {
                nonConfigurationString2 = nonConfigurationString2.intern();
                z = true;
            } else {
                z = false;
            }
            if (nonConfigurationString != null) {
                nonConfigurationString = nonConfigurationString.intern();
                z = true;
            }
            if (!z) {
                Slog.w("PackageParsing", "No readPermission or writePermission for <path-permission>: " + name + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
                return parseInput.success(parsedProvider);
            }
            PathPermission pathPermission = null;
            String nonConfigurationString4 = typedArrayObtainAttributes.getNonConfigurationString(6, 0);
            if (nonConfigurationString4 != null) {
                pathPermission = new PathPermission(nonConfigurationString4, 3, nonConfigurationString2, nonConfigurationString);
            } else {
                String nonConfigurationString5 = typedArrayObtainAttributes.getNonConfigurationString(5, 0);
                if (nonConfigurationString5 != null) {
                    pathPermission = new PathPermission(nonConfigurationString5, 2, nonConfigurationString2, nonConfigurationString);
                } else {
                    String nonConfigurationString6 = typedArrayObtainAttributes.getNonConfigurationString(4, 0);
                    if (nonConfigurationString6 != null) {
                        pathPermission = new PathPermission(nonConfigurationString6, 1, nonConfigurationString2, nonConfigurationString);
                    } else {
                        String nonConfigurationString7 = typedArrayObtainAttributes.getNonConfigurationString(3, 0);
                        if (nonConfigurationString7 != null) {
                            pathPermission = new PathPermission(nonConfigurationString7, 0, nonConfigurationString2, nonConfigurationString);
                        }
                    }
                }
            }
            if (pathPermission != null) {
                if (parsedProvider.pathPermissions == null) {
                    parsedProvider.pathPermissions = new PathPermission[1];
                    parsedProvider.pathPermissions[0] = pathPermission;
                } else {
                    int length = parsedProvider.pathPermissions.length;
                    PathPermission[] pathPermissionArr = new PathPermission[length + 1];
                    System.arraycopy(parsedProvider.pathPermissions, 0, pathPermissionArr, 0, length);
                    pathPermissionArr[length] = pathPermission;
                    parsedProvider.pathPermissions = pathPermissionArr;
                }
            } else {
                Slog.w("PackageParsing", "No path, pathPrefix, or pathPattern for <path-permission>: " + name + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
            }
            return parseInput.success(parsedProvider);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }
}
