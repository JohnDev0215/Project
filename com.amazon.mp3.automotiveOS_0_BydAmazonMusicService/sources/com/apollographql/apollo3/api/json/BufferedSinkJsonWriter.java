package com.apollographql.apollo3.api.json;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.json.internal.JsonScope;
import com.apollographql.apollo3.exception.JsonDataException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

/* JADX INFO: compiled from: BufferedSinkJsonWriter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u000f\u0018\u0000 92\u00020\u0001:\u00019B\u001d\b\u0007\u0012\u0006\u0010\"\u001a\u00020!\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b7\u00108J\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J \u0010\t\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002J\b\u0010\u000f\u001a\u00020\u0002H\u0002J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u0014\u001a\u00020\u0001H\u0016J\b\u0010\u0015\u001a\u00020\u0001H\u0016J\b\u0010\u0016\u001a\u00020\u0001H\u0016J\b\u0010\u0017\u001a\u00020\u0001H\u0016J\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0001H\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001bH\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001cH\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0002H\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001dH\u0016J\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001eH\u0016J\u0010\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001fH\u0016J\u000e\u0010 \u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0004J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u001c\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010*R\u0016\u0010/\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u00101\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u0010%R\u0014\u00104\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00106\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00103¨\u0006:"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSinkJsonWriter;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "empty", "", "openBracket", "open", "nonempty", "closeBracket", "close", "", "writeDeferredName", "newline", "beforeName", "beforeValue", "peekScope", "newTop", "pushScope", "topOfStack", "replaceTop", "beginArray", "endArray", "beginObject", "endObject", "name", "value", "nullValue", "", "", "", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "Lcom/apollographql/apollo3/api/Upload;", "jsonValue", "Lokio/BufferedSink;", "sink", "Lokio/BufferedSink;", "indent", "Ljava/lang/String;", "stackSize", "I", "", OAuth2Constants.SCOPES, "[I", "", "pathNames", "[Ljava/lang/String;", "pathIndices", "isLenient", "Z", "deferredName", "getSeparator", "()Ljava/lang/String;", "separator", "getPath", "path", "<init>", "(Lokio/BufferedSink;Ljava/lang/String;)V", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class BufferedSinkJsonWriter implements JsonWriter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private final String indent;
    private boolean isLenient;
    private final int[] pathIndices;
    private final String[] pathNames;
    private final int[] scopes;
    private final BufferedSink sink;
    private int stackSize;

    @JvmOverloads
    public BufferedSinkJsonWriter(BufferedSink sink, String str) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = sink;
        this.indent = str;
        this.scopes = new int[256];
        this.pathNames = new String[256];
        this.pathIndices = new int[256];
        pushScope(6);
    }

    private final String getSeparator() {
        String str = this.indent;
        return str == null || str.length() == 0 ? ":" : ": ";
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public String getPath() {
        return JsonScope.INSTANCE.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(1, "[");
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter endArray() {
        return close(1, 2, "]");
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(3, "{");
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter endObject() {
        return close(3, 5, "}");
    }

    private final JsonWriter open(int empty, String openBracket) throws IOException {
        beforeValue();
        pushScope(empty);
        this.pathIndices[this.stackSize - 1] = 0;
        this.sink.writeUtf8(openBracket);
        return this;
    }

    private final JsonWriter close(int empty, int nonempty, String closeBracket) throws IOException {
        int iPeekScope = peekScope();
        if (!(iPeekScope == nonempty || iPeekScope == empty)) {
            throw new IllegalStateException("Nesting problem.".toString());
        }
        String str = this.deferredName;
        if (!(str == null)) {
            throw new IllegalStateException(Intrinsics.stringPlus("Dangling name: ", str).toString());
        }
        int i = this.stackSize - 1;
        this.stackSize = i;
        this.pathNames[i] = null;
        int[] iArr = this.pathIndices;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        if (iPeekScope == nonempty) {
            newline();
        }
        this.sink.writeUtf8(closeBracket);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter name(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int i = this.stackSize;
        if (!(i != 0)) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        if (!(this.deferredName == null)) {
            throw new IllegalStateException("Nesting problem.".toString());
        }
        this.deferredName = name;
        this.pathNames[i - 1] = name;
        return this;
    }

    private final void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            Companion companion = INSTANCE;
            BufferedSink bufferedSink = this.sink;
            String str = this.deferredName;
            Intrinsics.checkNotNull(str);
            companion.string(bufferedSink, str);
            this.deferredName = null;
        }
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter value(String value) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        writeDeferredName();
        beforeValue();
        INSTANCE.string(this.sink, value);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter nullValue() {
        return jsonValue("null");
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter value(boolean value) {
        return jsonValue(value ? "true" : "false");
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter value(double value) {
        if (!(this.isLenient || !(Double.isNaN(value) || Double.isInfinite(value)))) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Numeric values must be finite, but was ", Double.valueOf(value)).toString());
        }
        return jsonValue(String.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter value(int value) {
        return jsonValue(String.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter value(long value) {
        return jsonValue(String.valueOf(value));
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public JsonWriter value(JsonNumber value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return jsonValue(value.toString());
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public BufferedSinkJsonWriter value(Upload value) {
        Intrinsics.checkNotNullParameter(value, "value");
        nullValue();
        return this;
    }

    public final JsonWriter jsonValue(String value) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(value);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sink.close();
        int i = this.stackSize;
        if (i > 1 || (i == 1 && this.scopes[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    private final void newline() throws IOException {
        if (this.indent == null) {
            return;
        }
        this.sink.writeByte(10);
        int i = this.stackSize;
        for (int i2 = 1; i2 < i; i2++) {
            this.sink.writeUtf8(this.indent);
        }
    }

    private final void beforeName() throws IOException {
        int iPeekScope = peekScope();
        if (iPeekScope == 5) {
            this.sink.writeByte(44);
        } else {
            if (!(iPeekScope == 3)) {
                throw new IllegalStateException("Nesting problem.".toString());
            }
        }
        newline();
        replaceTop(4);
    }

    private final void beforeValue() throws IOException {
        int iPeekScope = peekScope();
        if (iPeekScope == 1) {
            replaceTop(2);
            newline();
            return;
        }
        if (iPeekScope == 2) {
            this.sink.writeByte(44);
            newline();
            return;
        }
        if (iPeekScope == 4) {
            this.sink.writeUtf8(getSeparator());
            replaceTop(5);
        } else if (iPeekScope == 6) {
            replaceTop(7);
        } else {
            if (iPeekScope == 7) {
                if (!this.isLenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.".toString());
                }
                replaceTop(7);
                return;
            }
            throw new IllegalStateException("Nesting problem.");
        }
    }

    private final int peekScope() {
        int i = this.stackSize;
        if (!(i != 0)) {
            throw new IllegalStateException("JsonWriter is closed.".toString());
        }
        return this.scopes[i - 1];
    }

    private final void pushScope(int newTop) {
        int i = this.stackSize;
        int[] iArr = this.scopes;
        if (i == iArr.length) {
            throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
        }
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    private final void replaceTop(int topOfStack) {
        this.scopes[this.stackSize - 1] = topOfStack;
    }

    /* JADX INFO: compiled from: BufferedSinkJsonWriter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004J\f\u0010\r\u001a\u00020\u0004*\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSinkJsonWriter$Companion;", "", "()V", "HEX_ARRAY", "", "REPLACEMENT_CHARS", "", "[Ljava/lang/String;", "string", "", "sink", "Lokio/BufferedSink;", "value", "hexString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String hexString(byte b) {
            StringBuilder sb = new StringBuilder();
            sb.append("0123456789abcdef".charAt(b >>> 4));
            sb.append("0123456789abcdef".charAt(b & 15));
            return sb.toString();
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x003c A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void string(okio.BufferedSink r9, java.lang.String r10) throws java.io.IOException {
            /*
                r8 = this;
                java.lang.String r0 = "sink"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.String r0 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String[] r0 = com.apollographql.apollo3.api.json.BufferedSinkJsonWriter.access$getREPLACEMENT_CHARS$cp()
                r1 = 34
                r9.writeByte(r1)
                int r2 = r10.length()
                r3 = 0
            L18:
                r4 = r3
            L19:
                if (r3 >= r2) goto L43
                int r5 = r3 + 1
                char r6 = r10.charAt(r3)
                r7 = 128(0x80, float:1.8E-43)
                if (r6 >= r7) goto L2a
                r6 = r0[r6]
                if (r6 != 0) goto L37
                goto L41
            L2a:
                r7 = 8232(0x2028, float:1.1535E-41)
                if (r6 != r7) goto L31
                java.lang.String r6 = "\\u2028"
                goto L37
            L31:
                r7 = 8233(0x2029, float:1.1537E-41)
                if (r6 != r7) goto L41
                java.lang.String r6 = "\\u2029"
            L37:
                if (r4 >= r3) goto L3c
                r9.writeUtf8(r10, r4, r3)
            L3c:
                r9.writeUtf8(r6)
                r3 = r5
                goto L18
            L41:
                r3 = r5
                goto L19
            L43:
                if (r4 >= r2) goto L48
                r9.writeUtf8(r10, r4, r2)
            L48:
                r9.writeByte(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.BufferedSinkJsonWriter.Companion.string(okio.BufferedSink, java.lang.String):void");
        }
    }

    static {
        String[] strArr = new String[128];
        for (int i = 0; i < 32; i++) {
            strArr[i] = Intrinsics.stringPlus("\\u00", INSTANCE.hexString((byte) i));
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        REPLACEMENT_CHARS = strArr;
    }
}
