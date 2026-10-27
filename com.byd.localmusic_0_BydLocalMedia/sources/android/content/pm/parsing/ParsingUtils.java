package android.content.pm.parsing;

import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ParsingUtils {
    public static final String TAG = "PackageParsing";

    public static String buildClassName(String str, CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 0) {
            return null;
        }
        String string = charSequence.toString();
        if (string.charAt(0) == '.') {
            return str + string;
        }
        if (string.indexOf(46) >= 0) {
            return string;
        }
        return str + '.' + string;
    }

    public static ParseResult unknownTag(String str, ParsingPackage parsingPackage, XmlResourceParser xmlResourceParser, ParseInput parseInput) throws XmlPullParserException, IOException {
        Slog.w("PackageParsing", "Unknown element under " + str + ": " + xmlResourceParser.getName() + " at " + parsingPackage.getBaseCodePath() + " " + xmlResourceParser.getPositionDescription());
        XmlUtils.skipCurrentTag(xmlResourceParser);
        return parseInput.success(null);
    }
}
