package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import com.android.internal.R;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsedServiceUtils {
    private static final String TAG = "PackageParsing";

    public static ParseResult<ParsedService> parseService(String[] strArr, ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, int i, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        ParsedService parsedService;
        int i2;
        ParseInput parseInput2;
        int i3;
        String str;
        ParseResult<?> parseResultAddMetaData;
        String packageName = parsingPackage.getPackageName();
        ParsedService parsedService2 = new ParsedService();
        String name = xmlResourceParser.getName();
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestService);
        try {
            String str2 = name;
            try {
                ParseResult<ParsedService> mainComponent = ParsedMainComponentUtils.parseMainComponent(parsedService2, name, strArr, parsingPackage, typedArrayObtainAttributes, i, z, parseInput, 12, 7, 13, 4, 1, 0, 8, 2, 6, 15, 17);
                if (!mainComponent.isError()) {
                    typedArray = typedArrayObtainAttributes;
                    try {
                        boolean zHasValue = typedArray.hasValue(5);
                        if (zHasValue) {
                            parsedService = parsedService2;
                            parsedService.exported = typedArray.getBoolean(5, false);
                        } else {
                            parsedService = parsedService2;
                        }
                        int i4 = 3;
                        String nonConfigurationString = typedArray.getNonConfigurationString(3, 0);
                        if (nonConfigurationString == null) {
                            nonConfigurationString = parsingPackage.getPermission();
                        }
                        parsedService.setPermission(nonConfigurationString);
                        parsedService.foregroundServiceType = typedArray.getInt(19, 0);
                        int i5 = 2;
                        parsedService.flags |= ComponentParseUtils.flag(1, 9, typedArray) | ComponentParseUtils.flag(2, 10, typedArray) | ComponentParseUtils.flag(4, 14, typedArray) | ComponentParseUtils.flag(8, 18, typedArray) | ComponentParseUtils.flag(1073741824, 11, typedArray);
                        boolean z2 = typedArray.getBoolean(16, false);
                        if (z2) {
                            parsedService.flags |= 1048576;
                            parsingPackage.setVisibleToInstantApps(true);
                        }
                        typedArray.recycle();
                        if (parsingPackage.isCantSaveState() && Objects.equals(parsedService.getProcessName(), packageName)) {
                            return parseInput.error("Heavy-weight applications can not have services in main process");
                        }
                        ParseInput parseInput3 = parseInput;
                        int depth = xmlResourceParser.getDepth();
                        while (true) {
                            int next = xmlResourceParser.next();
                            if (next == 1 || (next == i4 && xmlResourceParser.getDepth() <= depth)) {
                                break;
                            }
                            if (next == i5) {
                                String name2 = xmlResourceParser.getName();
                                name2.hashCode();
                                if (name2.equals("meta-data")) {
                                    i2 = depth;
                                    parseInput2 = parseInput3;
                                    i3 = i5;
                                    str = str2;
                                    parseResultAddMetaData = ParsedComponentUtils.addMetaData(parsedService, parsingPackage, resources, xmlResourceParser, parseInput2);
                                } else if (name2.equals("intent-filter")) {
                                    str = str2;
                                    i2 = depth;
                                    parseInput2 = parseInput3;
                                    i3 = i5;
                                    ParseResult<ParsedIntentInfo> intentFilter = ParsedMainComponentUtils.parseIntentFilter(parsedService, parsingPackage, resources, xmlResourceParser, z2, true, false, false, false, parseInput);
                                    if (intentFilter.isSuccess()) {
                                        ParsedIntentInfo result = intentFilter.getResult();
                                        parsedService.order = Math.max(result.getOrder(), parsedService.order);
                                        parsedService.addIntent(result);
                                    }
                                    parseResultAddMetaData = intentFilter;
                                } else {
                                    String str3 = str2;
                                    parseResultAddMetaData = ParsingUtils.unknownTag(str3, parsingPackage, xmlResourceParser, parseInput3);
                                    str = str3;
                                    i2 = depth;
                                    parseInput2 = parseInput3;
                                    i3 = i5;
                                }
                                if (parseResultAddMetaData.isError()) {
                                    return parseInput2.error(parseResultAddMetaData);
                                }
                                parseInput3 = parseInput2;
                                depth = i2;
                                i5 = i3;
                                str2 = str;
                                i4 = 3;
                            }
                        }
                        ParseInput parseInput4 = parseInput3;
                        if (!zHasValue) {
                            parsedService.exported = parsedService.getIntents().size() > 0;
                        }
                        return parseInput4.success(parsedService);
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
}
