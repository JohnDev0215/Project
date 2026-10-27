package com.bugsnag.android;

import android.util.JsonReader;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: User.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B+\b\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/bugsnag/android/User;", "Lcom/bugsnag/android/JsonStream$Streamable;", "id", "", "email", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getId", "getName", "equals", "", "other", "", "hashCode", "", "toStream", "", "writer", "Lcom/bugsnag/android/JsonStream;", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class User implements JsonStream.Streamable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String email;
    private final String id;
    private final String name;

    @JvmOverloads
    public User() {
        this(null, null, null, 7, null);
    }

    @JvmOverloads
    public User(String str, String str2, String str3) {
        this.id = str;
        this.email = str2;
        this.name = str3;
    }

    public final String getId() {
        return this.id;
    }

    public final String getEmail() {
        return this.email;
    }

    public /* synthetic */ User(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getName() {
        return this.name;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("id").value(this.id);
        writer.name("email").value(this.email);
        writer.name("name").value(this.name);
        writer.endObject();
    }

    /* JADX INFO: compiled from: User.kt */
    @kotlin.Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/bugsnag/android/User$Companion;", "", "Lcom/bugsnag/android/User;", "Landroid/util/JsonReader;", "reader", "fromReader", "", "KEY_EMAIL", "Ljava/lang/String;", "KEY_ID", "KEY_NAME", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public User fromReader(JsonReader reader) throws IOException {
            reader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            while (reader.hasNext()) {
                String strNextName = reader.nextName();
                String strNextString = reader.nextString();
                if (strNextName != null) {
                    int iHashCode = strNextName.hashCode();
                    if (iHashCode != 3355) {
                        if (iHashCode != 3373707) {
                            if (iHashCode == 96619420 && strNextName.equals("email")) {
                                str2 = strNextString;
                            }
                        } else if (strNextName.equals("name")) {
                            str3 = strNextString;
                        }
                    } else if (strNextName.equals("id")) {
                        str = strNextString;
                    }
                }
            }
            User user = new User(str, str2, str3);
            reader.endObject();
            return user;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!kotlin.jvm.internal.Intrinsics.areEqual(User.class, other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            User user = (User) other;
            return ((kotlin.jvm.internal.Intrinsics.areEqual(this.id, user.id) ^ true) || (kotlin.jvm.internal.Intrinsics.areEqual(this.email, user.email) ^ true) || (kotlin.jvm.internal.Intrinsics.areEqual(this.name, user.name) ^ true)) ? false : true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bugsnag.android.User");
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.email;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.name;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }
}
