package com.netinsight.sye.syeClient.video;

import com.google.android.gms.common.Scopes;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0005\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0004J.\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0004J\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0004\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u0018\u0010\u0004\"\u0004\b\u0019\u0010\u0017R\"\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0014\u001a\u0004\b\u001a\u0010\u0004\"\u0004\b\u001b\u0010\u0017¨\u0006\u001e"}, d2 = {"Lcom/netinsight/sye/syeClient/video/SyeVideoCodecSupport;", "", "", "component1", "()I", "component2", "component3", "codec", Scopes.PROFILE, "level", "copy", "(III)Lcom/netinsight/sye/syeClient/video/SyeVideoCodecSupport;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getLevel", "setLevel", "(I)V", "getCodec", "setCodec", "getProfile", "setProfile", "<init>", "(III)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final /* data */ class SyeVideoCodecSupport {
    private int codec;
    private int level;
    private int profile;

    public SyeVideoCodecSupport(int i, int i2, int i3) {
        this.codec = i;
        this.profile = i2;
        this.level = i3;
    }

    public static /* synthetic */ SyeVideoCodecSupport copy$default(SyeVideoCodecSupport syeVideoCodecSupport, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = syeVideoCodecSupport.codec;
        }
        if ((i4 & 2) != 0) {
            i2 = syeVideoCodecSupport.profile;
        }
        if ((i4 & 4) != 0) {
            i3 = syeVideoCodecSupport.level;
        }
        return syeVideoCodecSupport.copy(i, i2, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCodec() {
        return this.codec;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getProfile() {
        return this.profile;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getLevel() {
        return this.level;
    }

    public final SyeVideoCodecSupport copy(int codec, int profile, int level) {
        return new SyeVideoCodecSupport(codec, profile, level);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyeVideoCodecSupport)) {
            return false;
        }
        SyeVideoCodecSupport syeVideoCodecSupport = (SyeVideoCodecSupport) other;
        return this.codec == syeVideoCodecSupport.codec && this.profile == syeVideoCodecSupport.profile && this.level == syeVideoCodecSupport.level;
    }

    public final int getCodec() {
        return this.codec;
    }

    public final int getLevel() {
        return this.level;
    }

    public final int getProfile() {
        return this.profile;
    }

    public int hashCode() {
        return (((this.codec * 31) + this.profile) * 31) + this.level;
    }

    public final void setCodec(int i) {
        this.codec = i;
    }

    public final void setLevel(int i) {
        this.level = i;
    }

    public final void setProfile(int i) {
        this.profile = i;
    }

    public String toString() {
        return "SyeVideoCodecSupport(codec=" + this.codec + ", profile=" + this.profile + ", level=" + this.level + ")";
    }
}
