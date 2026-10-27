package com.bugsnag.android.ndk;

import com.bugsnag.android.JsonStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: OpaqueValue.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/bugsnag/android/ndk/OpaqueValue;", "", "json", "", "(Ljava/lang/String;)V", "getJson", "()Ljava/lang/String;", "Companion", "bugsnag-plugin-android-ndk_release"}, k = 1, mv = {1, 4, 2})
public final class OpaqueValue {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String json;

    /* JADX INFO: compiled from: OpaqueValue.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/bugsnag/android/ndk/OpaqueValue$Companion;", "", "()V", "MAX_NDK_STRING_LENGTH", "", "US_ASCII_MAX_CODEPOINT", "encode", "", "value", "isStringNDKSupported", "", "makeSafe", "bugsnag-plugin-android-ndk_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean isStringNDKSupported(String value) {
            boolean z;
            if (value.length() >= 64) {
                return false;
            }
            int i = 0;
            while (true) {
                if (i >= value.length()) {
                    z = true;
                    break;
                }
                if (!(value.charAt(i) <= 127)) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                return true;
            }
            byte[] bytes = value.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes.length < 64;
        }

        private final String encode(Object value) {
            StringWriter stringWriter = new StringWriter();
            try {
                new JsonStream(stringWriter).value(value, true);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(stringWriter, null);
                String string = stringWriter.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "writer.toString()");
                return string;
            } finally {
            }
        }

        public final Object makeSafe(Object value) {
            if ((value instanceof Boolean) || (value instanceof Number)) {
                return value;
            }
            boolean z = value instanceof String;
            if (z && isStringNDKSupported((String) value)) {
                return value;
            }
            if (z || (value instanceof Map) || (value instanceof Collection)) {
                return new OpaqueValue(encode(value));
            }
            return null;
        }
    }

    public OpaqueValue(String str) {
        this.json = str;
    }

    public final String getJson() {
        return this.json;
    }
}
