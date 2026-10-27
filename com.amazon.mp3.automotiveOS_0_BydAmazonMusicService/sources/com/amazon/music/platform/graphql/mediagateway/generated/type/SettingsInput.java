package com.amazon.music.platform.graphql.mediagateway.generated.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsInput.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/SettingsInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MusicSettingsInput;", "music", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MusicSettingsInput;", "getMusic", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MusicSettingsInput;", "<init>", "(Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MusicSettingsInput;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SettingsInput {
    private final MusicSettingsInput music;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SettingsInput) && Intrinsics.areEqual(this.music, ((SettingsInput) other).music);
    }

    public int hashCode() {
        return this.music.hashCode();
    }

    public String toString() {
        return "SettingsInput(music=" + this.music + ')';
    }

    public SettingsInput(MusicSettingsInput music) {
        Intrinsics.checkNotNullParameter(music, "music");
        this.music = music;
    }

    public final MusicSettingsInput getMusic() {
        return this.music;
    }
}
