package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsedInstrumentationUtils {
    public static ParseResult<ParsedInstrumentation> parseInstrumentation(ParsingPackage parsingPackage, Resources resources, XmlResourceParser xmlResourceParser, boolean z, ParseInput parseInput) throws XmlPullParserException, IOException {
        TypedArray typedArray;
        ParsedInstrumentation parsedInstrumentation = new ParsedInstrumentation();
        String str = "<" + xmlResourceParser.getName() + ">";
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestInstrumentation);
        try {
            ParseResult<ParsedInstrumentation> component = ParsedComponentUtils.parseComponent(parsedInstrumentation, str, parsingPackage, typedArrayObtainAttributes, z, parseInput, 7, null, 1, 0, 6, 2, 8);
            if (!component.isError()) {
                typedArray = typedArrayObtainAttributes;
                try {
                    parsedInstrumentation.setTargetPackage(typedArray.getNonResourceString(3));
                    parsedInstrumentation.setTargetProcesses(typedArray.getNonResourceString(9));
                    parsedInstrumentation.handleProfiling = typedArray.getBoolean(4, false);
                    parsedInstrumentation.functionalTest = typedArray.getBoolean(5, false);
                    typedArray.recycle();
                    return ComponentParseUtils.parseAllMetaData(parsingPackage, resources, xmlResourceParser, str, parsedInstrumentation, parseInput);
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
    }
}
