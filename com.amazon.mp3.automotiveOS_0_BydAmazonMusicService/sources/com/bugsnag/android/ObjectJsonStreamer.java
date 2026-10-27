package com.bugsnag.android;

import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.DateUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ObjectJsonStreamer.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0001H\u0002J\u001c\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J(\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u0012H\u0002J\"\u0010\u0017\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\u0012R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/bugsnag/android/ObjectJsonStreamer;", "", "()V", "redactedKeys", "", "", "getRedactedKeys", "()Ljava/util/Set;", "setRedactedKeys", "(Ljava/util/Set;)V", "arrayToStream", "", "writer", "Lcom/bugsnag/android/JsonStream;", "obj", "collectionToStream", "", "isRedactedKey", "", "key", "mapToStream", "", "shouldRedactKeys", "objectToStream", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class ObjectJsonStreamer {
    private Set<String> redactedKeys = SetsKt__SetsJVMKt.setOf("password");

    public final Set<String> getRedactedKeys() {
        return this.redactedKeys;
    }

    public final void setRedactedKeys(Set<String> set) {
        this.redactedKeys = set;
    }

    public static /* synthetic */ void objectToStream$default(ObjectJsonStreamer objectJsonStreamer, Object obj, JsonStream jsonStream, boolean z, int i, Object obj2) throws IOException {
        if ((i & 4) != 0) {
            z = false;
        }
        objectJsonStreamer.objectToStream(obj, jsonStream, z);
    }

    public final void objectToStream(Object obj, JsonStream writer, boolean shouldRedactKeys) throws IOException {
        if (obj == null) {
            writer.nullValue();
            return;
        }
        if (obj instanceof String) {
            writer.value((String) obj);
            return;
        }
        if (obj instanceof Number) {
            writer.value((Number) obj);
            return;
        }
        if (obj instanceof Boolean) {
            writer.value(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof JsonStream.Streamable) {
            ((JsonStream.Streamable) obj).toStream(writer);
            return;
        }
        if (obj instanceof Date) {
            writer.value(DateUtils.toIso8601((Date) obj));
            return;
        }
        if (obj instanceof Map) {
            mapToStream(writer, (Map) obj, shouldRedactKeys);
            return;
        }
        if (obj instanceof Collection) {
            collectionToStream(writer, (Collection) obj);
        } else if (obj.getClass().isArray()) {
            arrayToStream(writer, obj);
        } else {
            writer.value("[OBJECT]");
        }
    }

    private final void mapToStream(JsonStream writer, Map<?, ?> obj, boolean shouldRedactKeys) throws IOException {
        writer.beginObject();
        Iterator<T> it = obj.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            if (key instanceof String) {
                String str = (String) key;
                writer.name(str);
                if (shouldRedactKeys && isRedactedKey(str)) {
                    writer.value("[REDACTED]");
                } else {
                    objectToStream(entry.getValue(), writer, shouldRedactKeys);
                }
            }
        }
        writer.endObject();
    }

    private final void collectionToStream(JsonStream writer, Collection<?> obj) throws IOException {
        writer.beginArray();
        Iterator<T> it = obj.iterator();
        while (it.hasNext()) {
            objectToStream$default(this, it.next(), writer, false, 4, null);
        }
        writer.endArray();
    }

    private final void arrayToStream(JsonStream writer, Object obj) throws IOException {
        writer.beginArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            objectToStream$default(this, Array.get(obj, i), writer, false, 4, null);
        }
        writer.endArray();
    }

    private final boolean isRedactedKey(String key) {
        Set<String> set = this.redactedKeys;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (StringsKt__StringsKt.contains$default((CharSequence) key, (CharSequence) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
