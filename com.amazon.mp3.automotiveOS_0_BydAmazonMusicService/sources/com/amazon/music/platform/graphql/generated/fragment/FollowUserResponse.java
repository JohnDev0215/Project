package com.amazon.music.platform.graphql.generated.fragment;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowUserResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u001b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse$User;", "user", "Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse$User;", "getUser", "()Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse$User;", "followState", "Ljava/lang/String;", "getFollowState", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse$User;Ljava/lang/String;)V", "User", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class FollowUserResponse {
    private final String followState;
    private final User user;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FollowUserResponse)) {
            return false;
        }
        FollowUserResponse followUserResponse = (FollowUserResponse) other;
        return Intrinsics.areEqual(this.user, followUserResponse.user) && Intrinsics.areEqual(this.followState, followUserResponse.followState);
    }

    public int hashCode() {
        User user = this.user;
        int iHashCode = (user == null ? 0 : user.hashCode()) * 31;
        String str = this.followState;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "FollowUserResponse(user=" + this.user + ", followState=" + this.followState + ')';
    }

    public FollowUserResponse(User user, String str) {
        this.user = user;
        this.followState = str;
    }

    public final User getUser() {
        return this.user;
    }

    public final String getFollowState() {
        return this.followState;
    }

    /* JADX INFO: compiled from: FollowUserResponse.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse$User;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class User {
        private final String id;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof User) && Intrinsics.areEqual(this.id, ((User) other).id);
        }

        public int hashCode() {
            String str = this.id;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "User(id=" + this.id + ')';
        }

        public User(String str) {
            this.id = str;
        }

        public final String getId() {
            return this.id;
        }
    }
}
