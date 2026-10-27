package com.bugsnag.android;

import android.util.JsonReader;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DeviceIdFilePersistence.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/bugsnag/android/DeviceId;", "Lcom/bugsnag/android/JsonStream$Streamable;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "toStream", "", "stream", "Lcom/bugsnag/android/JsonStream;", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
final class DeviceId implements JsonStream.Streamable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String id;

    public DeviceId(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream stream) throws IOException {
        stream.beginObject();
        stream.name("id");
        stream.value(this.id);
        stream.endObject();
    }

    /* JADX INFO: compiled from: DeviceIdFilePersistence.kt */
    @kotlin.Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/bugsnag/android/DeviceId$Companion;", "", "Lcom/bugsnag/android/DeviceId;", "Landroid/util/JsonReader;", "reader", "fromReader", "", "KEY_ID", "Ljava/lang/String;", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public DeviceId fromReader(JsonReader reader) throws IOException {
            reader.beginObject();
            return new DeviceId((reader.hasNext() && kotlin.jvm.internal.Intrinsics.areEqual("id", reader.nextName())) ? reader.nextString() : null);
        }
    }
}
