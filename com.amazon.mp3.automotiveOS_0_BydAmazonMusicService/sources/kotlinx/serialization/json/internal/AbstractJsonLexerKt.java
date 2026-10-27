package kotlinx.serialization.json.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: AbstractJsonLexer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0004H\u0000¨\u0006\u0006"}, d2 = {"", "c", "", "charToTokenClass", "", "escapeToChar", "kotlinx-serialization-json"}, k = 2, mv = {1, 7, 1})
public final class AbstractJsonLexerKt {
    public static final byte charToTokenClass(char c) {
        if (c < '~') {
            return CharMappings.CHAR_TO_TOKEN[c];
        }
        return (byte) 0;
    }

    public static final char escapeToChar(int i) {
        if (i < 117) {
            return CharMappings.ESCAPE_2_CHAR[i];
        }
        return (char) 0;
    }
}
