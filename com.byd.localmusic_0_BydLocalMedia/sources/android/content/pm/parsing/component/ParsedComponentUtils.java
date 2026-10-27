package android.content.pm.parsing.component;

import android.content.pm.PackageManager;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
class ParsedComponentUtils {
    private static final String TAG = "PackageParsing";

    ParsedComponentUtils() {
    }

    static <Component extends ParsedComponent> ParseResult<Component> parseComponent(Component component, String str, ParsingPackage parsingPackage, TypedArray typedArray, boolean z, ParseInput parseInput, int i, Integer num, int i2, int i3, int i4, int i5, int i6) {
        String nonConfigurationString = typedArray.getNonConfigurationString(i5, 0);
        if (TextUtils.isEmpty(nonConfigurationString)) {
            return parseInput.error(str + " does not specify android:name");
        }
        String packageName = parsingPackage.getPackageName();
        String strBuildClassName = ParsingUtils.buildClassName(packageName, nonConfigurationString);
        if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(strBuildClassName)) {
            return parseInput.error(str + " invalid android:name");
        }
        component.setName(strBuildClassName);
        component.setPackageName(packageName);
        int resourceId = z ? typedArray.getResourceId(i6, 0) : 0;
        if (resourceId != 0) {
            component.icon = resourceId;
            component.nonLocalizedLabel = null;
        } else {
            int resourceId2 = typedArray.getResourceId(i2, 0);
            if (resourceId2 != 0) {
                component.icon = resourceId2;
                component.nonLocalizedLabel = null;
            }
        }
        int resourceId3 = typedArray.getResourceId(i4, 0);
        if (resourceId3 != 0) {
            component.logo = resourceId3;
        }
        int resourceId4 = typedArray.getResourceId(i, 0);
        if (resourceId4 != 0) {
            component.banner = resourceId4;
        }
        if (num != null) {
            component.descriptionRes = typedArray.getResourceId(num.intValue(), 0);
        }
        TypedValue typedValuePeekValue = typedArray.peekValue(i3);
        if (typedValuePeekValue != null) {
            component.labelRes = typedValuePeekValue.resourceId;
            if (typedValuePeekValue.resourceId == 0) {
                component.nonLocalizedLabel = typedValuePeekValue.coerceToString();
            }
        }
        return parseInput.success(component);
    }

    static ParseResult<Bundle> addMetaData(ParsedComponent parsedComponent, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, ParseInput parseInput) {
        ParseResult<Bundle> metaData = ParsingPackageUtils.parseMetaData(parsingPackage, resources, xmlResourceParser, parsedComponent.metaData, parseInput);
        if (metaData.isError()) {
            return parseInput.error(metaData);
        }
        Bundle result = metaData.getResult();
        parsedComponent.metaData = result;
        return parseInput.success(result);
    }
}
