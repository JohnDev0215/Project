package com.bugsnag.android;

import com.bugsnag.android.JsonStream;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Notifier.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B'\b\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\b\b\u0002\u0010\u0017\u001a\u00020\r¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\"\u0010\u0017\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcom/bugsnag/android/Notifier;", "Lcom/bugsnag/android/JsonStream$Streamable;", "Lcom/bugsnag/android/JsonStream;", "writer", "", "toStream", "", "dependencies", "Ljava/util/List;", "getDependencies", "()Ljava/util/List;", "setDependencies", "(Ljava/util/List;)V", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "version", "getVersion", "setVersion", ImagesContract.URL, "getUrl", "setUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class Notifier implements JsonStream.Streamable {
    private List<Notifier> dependencies;
    private String name;
    private String url;
    private String version;

    @JvmOverloads
    public Notifier() {
        this(null, null, null, 7, null);
    }

    @JvmOverloads
    public Notifier(String str, String str2, String str3) {
        this.name = str;
        this.version = str2;
        this.url = str3;
        this.dependencies = CollectionsKt__CollectionsKt.emptyList();
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public /* synthetic */ Notifier(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "Android Bugsnag Notifier" : str, (i & 2) != 0 ? "5.30.0" : str2, (i & 4) != 0 ? "https://bugsnag.com" : str3);
    }

    public final String getUrl() {
        return this.url;
    }

    public final List<Notifier> getDependencies() {
        return this.dependencies;
    }

    public final void setDependencies(List<Notifier> list) {
        this.dependencies = list;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("name").value(this.name);
        writer.name("version").value(this.version);
        writer.name(ImagesContract.URL).value(this.url);
        if (!this.dependencies.isEmpty()) {
            writer.name("dependencies");
            writer.beginArray();
            Iterator<T> it = this.dependencies.iterator();
            while (it.hasNext()) {
                writer.value((Notifier) it.next());
            }
            writer.endArray();
        }
        writer.endObject();
    }
}
