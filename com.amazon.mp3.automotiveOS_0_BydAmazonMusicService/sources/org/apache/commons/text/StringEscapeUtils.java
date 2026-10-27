package org.apache.commons.text;

import com.amplifyframework.core.model.ModelIdentifier;
import com.visualon.OSMPUtils.voOSType;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.CsvTranslators;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.JavaUnicodeEscaper;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.commons.text.translate.NumericEntityEscaper;
import org.apache.commons.text.translate.NumericEntityUnescaper;
import org.apache.commons.text.translate.OctalUnescaper;
import org.apache.commons.text.translate.UnicodeUnescaper;
import org.apache.commons.text.translate.UnicodeUnpairedSurrogateRemover;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes7.dex */
public class StringEscapeUtils {
    public static final CharSequenceTranslator ESCAPE_CSV;
    public static final CharSequenceTranslator ESCAPE_ECMASCRIPT;
    public static final CharSequenceTranslator ESCAPE_HTML3;
    public static final CharSequenceTranslator ESCAPE_HTML4;
    public static final CharSequenceTranslator ESCAPE_JAVA;
    public static final CharSequenceTranslator ESCAPE_JSON;
    public static final CharSequenceTranslator ESCAPE_XML10;
    public static final CharSequenceTranslator ESCAPE_XML11;
    public static final CharSequenceTranslator ESCAPE_XSI;
    public static final CharSequenceTranslator UNESCAPE_CSV;
    public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT;
    public static final CharSequenceTranslator UNESCAPE_HTML3;
    public static final CharSequenceTranslator UNESCAPE_HTML4;
    public static final CharSequenceTranslator UNESCAPE_JAVA;
    public static final CharSequenceTranslator UNESCAPE_JSON;
    public static final CharSequenceTranslator UNESCAPE_XML;
    public static final CharSequenceTranslator UNESCAPE_XSI;

    static {
        HashMap map = new HashMap();
        map.put(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "\\\"");
        map.put("\\", "\\\\");
        Map<CharSequence, CharSequence> map2 = EntityArrays.JAVA_CTRL_CHARS_ESCAPE;
        ESCAPE_JAVA = new AggregateTranslator(new LookupTranslator(Collections.unmodifiableMap(map)), new LookupTranslator(map2), JavaUnicodeEscaper.outsideOf(32, WorkQueueKt.MASK));
        HashMap map3 = new HashMap();
        map3.put("'", "\\'");
        map3.put(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "\\\"");
        map3.put("\\", "\\\\");
        map3.put("/", "\\/");
        ESCAPE_ECMASCRIPT = new AggregateTranslator(new LookupTranslator(Collections.unmodifiableMap(map3)), new LookupTranslator(map2), JavaUnicodeEscaper.outsideOf(32, WorkQueueKt.MASK));
        HashMap map4 = new HashMap();
        map4.put(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "\\\"");
        map4.put("\\", "\\\\");
        map4.put("/", "\\/");
        ESCAPE_JSON = new AggregateTranslator(new LookupTranslator(Collections.unmodifiableMap(map4)), new LookupTranslator(map2), JavaUnicodeEscaper.outsideOf(32, GeneratorAdapter.AND));
        HashMap map5 = new HashMap();
        map5.put("\u0000", "");
        map5.put("\u0001", "");
        map5.put("\u0002", "");
        map5.put("\u0003", "");
        map5.put("\u0004", "");
        map5.put("\u0005", "");
        map5.put("\u0006", "");
        map5.put("\u0007", "");
        map5.put("\b", "");
        map5.put("\u000b", "");
        map5.put("\f", "");
        map5.put("\u000e", "");
        map5.put("\u000f", "");
        map5.put("\u0010", "");
        map5.put("\u0011", "");
        map5.put("\u0012", "");
        map5.put("\u0013", "");
        map5.put("\u0014", "");
        map5.put("\u0015", "");
        map5.put("\u0016", "");
        map5.put("\u0017", "");
        map5.put("\u0018", "");
        map5.put("\u0019", "");
        map5.put("\u001a", "");
        map5.put("\u001b", "");
        map5.put("\u001c", "");
        map5.put("\u001d", "");
        map5.put("\u001e", "");
        map5.put("\u001f", "");
        map5.put("\ufffe", "");
        map5.put("\uffff", "");
        Map<CharSequence, CharSequence> map6 = EntityArrays.BASIC_ESCAPE;
        Map<CharSequence, CharSequence> map7 = EntityArrays.APOS_ESCAPE;
        ESCAPE_XML10 = new AggregateTranslator(new LookupTranslator(map6), new LookupTranslator(map7), new LookupTranslator(Collections.unmodifiableMap(map5)), NumericEntityEscaper.between(WorkQueueKt.MASK, voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION), NumericEntityEscaper.between(134, 159), new UnicodeUnpairedSurrogateRemover());
        HashMap map8 = new HashMap();
        map8.put("\u0000", "");
        map8.put("\u000b", "&#11;");
        map8.put("\f", "&#12;");
        map8.put("\ufffe", "");
        map8.put("\uffff", "");
        ESCAPE_XML11 = new AggregateTranslator(new LookupTranslator(map6), new LookupTranslator(map7), new LookupTranslator(Collections.unmodifiableMap(map8)), NumericEntityEscaper.between(1, 8), NumericEntityEscaper.between(14, 31), NumericEntityEscaper.between(WorkQueueKt.MASK, voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION), NumericEntityEscaper.between(134, 159), new UnicodeUnpairedSurrogateRemover());
        Map<CharSequence, CharSequence> map9 = EntityArrays.ISO8859_1_ESCAPE;
        ESCAPE_HTML3 = new AggregateTranslator(new LookupTranslator(map6), new LookupTranslator(map9));
        ESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(map6), new LookupTranslator(map9), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE));
        ESCAPE_CSV = new CsvTranslators.CsvEscaper();
        HashMap map10 = new HashMap();
        map10.put("|", "\\|");
        map10.put("&", "\\&");
        map10.put(";", "\\;");
        map10.put("<", "\\<");
        map10.put(">", "\\>");
        map10.put("(", "\\(");
        map10.put(")", "\\)");
        map10.put("$", "\\$");
        map10.put("`", "\\`");
        map10.put("\\", "\\\\");
        map10.put(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "\\\"");
        map10.put("'", "\\'");
        map10.put(" ", "\\ ");
        map10.put("\t", "\\\t");
        map10.put("\r\n", "");
        map10.put("\n", "");
        map10.put("*", "\\*");
        map10.put("?", "\\?");
        map10.put("[", "\\[");
        map10.put("#", "\\#");
        map10.put("~", "\\~");
        map10.put("=", "\\=");
        map10.put("%", "\\%");
        ESCAPE_XSI = new LookupTranslator(Collections.unmodifiableMap(map10));
        HashMap map11 = new HashMap();
        map11.put("\\\\", "\\");
        map11.put("\\\"", ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        map11.put("\\'", "'");
        map11.put("\\", "");
        AggregateTranslator aggregateTranslator = new AggregateTranslator(new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE), new LookupTranslator(Collections.unmodifiableMap(map11)));
        UNESCAPE_JAVA = aggregateTranslator;
        UNESCAPE_ECMASCRIPT = aggregateTranslator;
        UNESCAPE_JSON = aggregateTranslator;
        Map<CharSequence, CharSequence> map12 = EntityArrays.BASIC_UNESCAPE;
        Map<CharSequence, CharSequence> map13 = EntityArrays.ISO8859_1_UNESCAPE;
        UNESCAPE_HTML3 = new AggregateTranslator(new LookupTranslator(map12), new LookupTranslator(map13), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(map12), new LookupTranslator(map13), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_XML = new AggregateTranslator(new LookupTranslator(map12), new LookupTranslator(EntityArrays.APOS_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_CSV = new CsvTranslators.CsvUnescaper();
        UNESCAPE_XSI = new XsiUnescaper();
    }

    static class XsiUnescaper extends CharSequenceTranslator {
        XsiUnescaper() {
        }

        @Override // org.apache.commons.text.translate.CharSequenceTranslator
        public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
            if (i != 0) {
                throw new IllegalStateException("XsiUnescaper should never reach the [1] index");
            }
            String string = charSequence.toString();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int iIndexOf = string.indexOf(92, i2);
                if (iIndexOf == -1) {
                    break;
                }
                if (iIndexOf > i3) {
                    writer.write(string.substring(i3, iIndexOf));
                }
                i3 = iIndexOf + 1;
                i2 = iIndexOf + 2;
            }
            if (i3 < string.length()) {
                writer.write(string.substring(i3));
            }
            return Character.codePointCount(charSequence, 0, charSequence.length());
        }
    }

    public static final String unescapeHtml3(String str) {
        return UNESCAPE_HTML3.translate(str);
    }
}
