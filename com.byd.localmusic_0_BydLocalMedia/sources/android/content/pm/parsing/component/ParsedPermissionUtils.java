package android.content.pm.parsing.component;

import android.content.pm.PermissionInfo;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsedPermissionUtils {
    private static final String TAG = "PackageParsing";

    public static ParseResult<ParsedPermission> parsePermission(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        String packageName = parsingPackage.getPackageName();
        ParsedPermission parsedPermission = new ParsedPermission();
        String str = "<" + xmlResourceParser.getName() + ">";
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPermission);
        try {
            try {
                ParseResult<ParsedPermission> component = ParsedComponentUtils.parseComponent(parsedPermission, str, parsingPackage, typedArrayObtainAttributes, z, parseInput, 8, 5, 1, 0, 6, 2, 9);
                if (!component.isError()) {
                    typedArray = typedArrayObtainAttributes;
                    try {
                        if (typedArray.hasValue(10)) {
                            if ("android".equals(packageName)) {
                                parsedPermission.backgroundPermission = typedArray.getNonResourceString(10);
                            } else {
                                Slog.w("PackageParsing", packageName + " defines a background permission. Only the 'android' package can do that.");
                            }
                        }
                        parsedPermission.setGroup(typedArray.getNonResourceString(4));
                        parsedPermission.requestRes = typedArray.getResourceId(11, 0);
                        parsedPermission.protectionLevel = typedArray.getInt(3, 0);
                        parsedPermission.flags = typedArray.getInt(7, 0);
                        if (!parsedPermission.isRuntime() || !"android".equals(parsedPermission.getPackageName())) {
                            parsedPermission.flags &= -5;
                            parsedPermission.flags &= -9;
                        } else if ((4 & parsedPermission.flags) != 0 && (parsedPermission.flags & 8) != 0) {
                            throw new IllegalStateException("Permission cannot be both soft and hard restricted: " + parsedPermission.getName());
                        }
                        typedArray.recycle();
                        if (parsedPermission.protectionLevel == -1) {
                            return parseInput.error("<permission> does not specify protectionLevel");
                        }
                        parsedPermission.protectionLevel = PermissionInfo.fixProtectionLevel(parsedPermission.protectionLevel);
                        if (parsedPermission.getProtectionFlags() != 0 && (parsedPermission.protectionLevel & 4096) == 0 && (parsedPermission.protectionLevel & 8192) == 0 && (parsedPermission.protectionLevel & 15) != 2) {
                            return parseInput.error("<permission>  protectionLevel specifies a non-instant flag but is not based on signature type");
                        }
                        return ComponentParseUtils.parseAllMetaData(parsingPackage, resources, xmlResourceParser, str, parsedPermission, parseInput);
                    } catch (Throwable th) {
                        th = th;
                        typedArray.recycle();
                        throw th;
                    }
                }
                typedArrayObtainAttributes.recycle();
                return component;
            } catch (Throwable th2) {
                th = th2;
                typedArray = typedArrayObtainAttributes;
            }
        } catch (Throwable th3) {
            th = th3;
            typedArray = typedArrayObtainAttributes;
        }
    }

    public static ParseResult<ParsedPermission> parsePermissionTree(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        ParsedPermission parsedPermission = new ParsedPermission();
        String str = "<" + xmlResourceParser.getName() + ">";
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPermissionTree);
        try {
            ParseResult<ParsedPermission> component = ParsedComponentUtils.parseComponent(parsedPermission, str, parsingPackage, typedArrayObtainAttributes, z, parseInput, 4, null, 1, 0, 3, 2, 5);
            if (component.isError()) {
                return component;
            }
            typedArrayObtainAttributes.recycle();
            int iIndexOf = parsedPermission.getName().indexOf(46);
            if (iIndexOf > 0) {
                iIndexOf = parsedPermission.getName().indexOf(46, iIndexOf + 1);
            }
            if (iIndexOf < 0) {
                return parseInput.error("<permission-tree> name has less than three segments: " + parsedPermission.getName());
            }
            parsedPermission.protectionLevel = 0;
            parsedPermission.tree = true;
            return ComponentParseUtils.parseAllMetaData(parsingPackage, resources, xmlResourceParser, str, parsedPermission, parseInput);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    public static ParseResult<ParsedPermissionGroup> parsePermissionGroup(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        ParsedPermissionGroup parsedPermissionGroup = new ParsedPermissionGroup();
        String str = "<" + xmlResourceParser.getName() + ">";
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestPermissionGroup);
        try {
            try {
                ParseResult<ParsedPermissionGroup> component = ParsedComponentUtils.parseComponent(parsedPermissionGroup, str, parsingPackage, typedArrayObtainAttributes, z, parseInput, 7, 4, 1, 0, 5, 2, 8);
                if (!component.isError()) {
                    typedArray = typedArrayObtainAttributes;
                    try {
                        parsedPermissionGroup.requestDetailResourceId = typedArray.getResourceId(12, 0);
                        parsedPermissionGroup.backgroundRequestResourceId = typedArray.getResourceId(9, 0);
                        parsedPermissionGroup.backgroundRequestDetailResourceId = typedArray.getResourceId(10, 0);
                        parsedPermissionGroup.requestRes = typedArray.getResourceId(11, 0);
                        parsedPermissionGroup.flags = typedArray.getInt(6, 0);
                        parsedPermissionGroup.priority = typedArray.getInt(3, 0);
                        typedArray.recycle();
                        return ComponentParseUtils.parseAllMetaData(parsingPackage, resources, xmlResourceParser, str, parsedPermissionGroup, parseInput);
                    } catch (Throwable th) {
                        th = th;
                        typedArray.recycle();
                        throw th;
                    }
                }
                typedArrayObtainAttributes.recycle();
                return component;
            } catch (Throwable th2) {
                th = th2;
                typedArray = typedArrayObtainAttributes;
            }
        } catch (Throwable th3) {
            th = th3;
            typedArray = typedArrayObtainAttributes;
        }
    }
}
