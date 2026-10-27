package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class StratusBenefitsInput {
    private final Boolean hawkfireBrowseAndAdd;
    private final Boolean hawkfireKatanaAccess;
    private final Boolean hawkfirePlaybackAccess;
    private final Boolean nightwing;
    private final Boolean primeMusicBrowse;
    private final Boolean primeMusicContentAccess;
    private final Boolean sonicRush;

    public interface BuildStep {
    }

    public static class Builder implements PrimeMusicBrowseStep, PrimeMusicContentAccessStep, HawkfirePlaybackAccessStep, HawkfireBrowseAndAddStep, HawkfireKatanaAccessStep, NightwingStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface HawkfireBrowseAndAddStep {
    }

    public interface HawkfireKatanaAccessStep {
    }

    public interface HawkfirePlaybackAccessStep {
    }

    public interface NightwingStep {
    }

    public interface PrimeMusicBrowseStep {
    }

    public interface PrimeMusicContentAccessStep {
    }

    public Boolean getPrimeMusicBrowse() {
        return this.primeMusicBrowse;
    }

    public Boolean getPrimeMusicContentAccess() {
        return this.primeMusicContentAccess;
    }

    public Boolean getHawkfirePlaybackAccess() {
        return this.hawkfirePlaybackAccess;
    }

    public Boolean getHawkfireBrowseAndAdd() {
        return this.hawkfireBrowseAndAdd;
    }

    public Boolean getHawkfireKatanaAccess() {
        return this.hawkfireKatanaAccess;
    }

    public Boolean getNightwing() {
        return this.nightwing;
    }

    public Boolean getSonicRush() {
        return this.sonicRush;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StratusBenefitsInput.class != obj.getClass()) {
            return false;
        }
        StratusBenefitsInput stratusBenefitsInput = (StratusBenefitsInput) obj;
        return ObjectsCompat.equals(getPrimeMusicBrowse(), stratusBenefitsInput.getPrimeMusicBrowse()) && ObjectsCompat.equals(getPrimeMusicContentAccess(), stratusBenefitsInput.getPrimeMusicContentAccess()) && ObjectsCompat.equals(getHawkfirePlaybackAccess(), stratusBenefitsInput.getHawkfirePlaybackAccess()) && ObjectsCompat.equals(getHawkfireBrowseAndAdd(), stratusBenefitsInput.getHawkfireBrowseAndAdd()) && ObjectsCompat.equals(getHawkfireKatanaAccess(), stratusBenefitsInput.getHawkfireKatanaAccess()) && ObjectsCompat.equals(getNightwing(), stratusBenefitsInput.getNightwing()) && ObjectsCompat.equals(getSonicRush(), stratusBenefitsInput.getSonicRush());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPrimeMusicBrowse());
        sb.append(getPrimeMusicContentAccess());
        sb.append(getHawkfirePlaybackAccess());
        sb.append(getHawkfireBrowseAndAdd());
        sb.append(getHawkfireKatanaAccess());
        sb.append(getNightwing());
        sb.append(getSonicRush());
        return sb.toString().hashCode();
    }
}
