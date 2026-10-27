package com.amazon.music.platform.aom.launcher;

import kotlin.Metadata;

/* JADX INFO: compiled from: LaunchConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/LaunchConfig;", "", "", "toString", "", "hashCode", "other", "", "equals", "isMandatoryToLaunchTarget", "Z", "()Z", "<init>", "(Z)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LaunchConfig {
    private final boolean isMandatoryToLaunchTarget;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LaunchConfig) && this.isMandatoryToLaunchTarget == ((LaunchConfig) other).isMandatoryToLaunchTarget;
    }

    public int hashCode() {
        boolean z = this.isMandatoryToLaunchTarget;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "LaunchConfig(isMandatoryToLaunchTarget=" + this.isMandatoryToLaunchTarget + ')';
    }

    public LaunchConfig(boolean z) {
        this.isMandatoryToLaunchTarget = z;
    }
}
