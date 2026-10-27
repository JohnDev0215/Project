package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.UserProfileQuery;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: UserProfileQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u0017\u0018\u0019B\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UserProfileQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$Data;", "", "other", "", "equals", "", "hashCode", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "<init>", "()V", "Companion", "Data", "User", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class UserProfileQuery implements Query<Data> {
    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query UserProfile { user { __typename ...user } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment user on User { id name visibility playbackVisibility tier images { __typename ...image } url }";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "6b640ed7796b2f4330573226f0791b56c0a71befc399f41b9abf24c1501dba66";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "UserProfile";
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
    }

    public boolean equals(Object other) {
        return other != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(other.getClass()), Reflection.getOrCreateKotlinClass(UserProfileQuery.class));
    }

    public int hashCode() {
        return Reflection.getOrCreateKotlinClass(UserProfileQuery.class).hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.UserProfileQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("user");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public UserProfileQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                UserProfileQuery.User user = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    user = (UserProfileQuery.User) Adapters.m2609nullable(Adapters.m2610obj(UserProfileQuery_ResponseAdapter$User.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new UserProfileQuery.Data(user);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, UserProfileQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("user");
                Adapters.m2609nullable(Adapters.m2610obj(UserProfileQuery_ResponseAdapter$User.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getUser());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: UserProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$User;", "user", "Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$User;", "getUser", "()Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$User;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$User;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final User user;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.user, ((Data) other).user);
        }

        public int hashCode() {
            User user = this.user;
            if (user == null) {
                return 0;
            }
            return user.hashCode();
        }

        public String toString() {
            return "Data(user=" + this.user + ')';
        }

        public Data(User user) {
            this.user = user;
        }

        public final User getUser() {
            return this.user;
        }
    }

    /* JADX INFO: compiled from: UserProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$User;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/User;", "user", "Lcom/amazon/music/platform/graphql/generated/fragment/User;", "getUser", "()Lcom/amazon/music/platform/graphql/generated/fragment/User;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/User;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class User {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.User user;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof User)) {
                return false;
            }
            User user = (User) other;
            return Intrinsics.areEqual(this.__typename, user.__typename) && Intrinsics.areEqual(this.user, user.user);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.user.hashCode();
        }

        public String toString() {
            return "User(__typename=" + this.__typename + ", user=" + this.user + ')';
        }

        public User(String __typename, com.amazon.music.platform.graphql.generated.fragment.User user) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(user, "user");
            this.__typename = __typename;
            this.user = user;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.User getUser() {
            return this.user;
        }
    }
}
