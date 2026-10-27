package com.amazon.music.platform.graphql.mediagateway.generated.type;

import com.apollographql.apollo3.api.Optional;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSettingsInput.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MusicSettingsInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "filterExplicitLanguage", "Lcom/apollographql/apollo3/api/Optional;", "getFilterExplicitLanguage", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MusicSettingsInput {
    private final Optional<Boolean> filterExplicitLanguage;

    public MusicSettingsInput() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MusicSettingsInput) && Intrinsics.areEqual(this.filterExplicitLanguage, ((MusicSettingsInput) other).filterExplicitLanguage);
    }

    public int hashCode() {
        return this.filterExplicitLanguage.hashCode();
    }

    public String toString() {
        return "MusicSettingsInput(filterExplicitLanguage=" + this.filterExplicitLanguage + ')';
    }

    public MusicSettingsInput(Optional<Boolean> filterExplicitLanguage) {
        Intrinsics.checkNotNullParameter(filterExplicitLanguage, "filterExplicitLanguage");
        this.filterExplicitLanguage = filterExplicitLanguage;
    }

    public /* synthetic */ MusicSettingsInput(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<Boolean> getFilterExplicitLanguage() {
        return this.filterExplicitLanguage;
    }
}
