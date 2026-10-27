package com.bugsnag.android.internal;

import com.bugsnag.android.repackaged.dslplatform.json.DslJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: FallbackWriter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0000\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J.\u0010\u000e\u001a\u00020\u00042\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\rH\u0016R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/bugsnag/android/internal/FallbackWriter;", "Lcom/bugsnag/android/repackaged/dslplatform/json/DslJson$Fallback;", "", "", "", "instance", "Ljava/io/OutputStream;", "stream", "", "serialize", "context", "Ljava/lang/reflect/Type;", "manifest", "Ljava/io/InputStream;", "deserialize", "", "placeholder", "[B", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class FallbackWriter implements DslJson.Fallback<Map<String, Object>> {
    private final byte[] placeholder;

    public FallbackWriter() {
        byte[] bytes = "\"[OBJECT]\"".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        this.placeholder = bytes;
    }

    @Override // com.bugsnag.android.repackaged.dslplatform.json.DslJson.Fallback
    public void serialize(Object instance, OutputStream stream) throws IOException {
        stream.write(this.placeholder);
    }

    @Override // com.bugsnag.android.repackaged.dslplatform.json.DslJson.Fallback
    public Object deserialize(Map<String, Object> context, Type manifest, InputStream stream) {
        throw new UnsupportedOperationException();
    }
}
