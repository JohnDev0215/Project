package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class LocalEpisodeAlreadyPlayingClientState extends ClientState {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientState");
    private Boolean alreadyPlaying;

    public static class Builder extends ClientState.Builder {
        protected Boolean alreadyPlaying;

        public LocalEpisodeAlreadyPlayingClientState build() {
            LocalEpisodeAlreadyPlayingClientState localEpisodeAlreadyPlayingClientState = new LocalEpisodeAlreadyPlayingClientState();
            populate(localEpisodeAlreadyPlayingClientState);
            return localEpisodeAlreadyPlayingClientState;
        }

        protected void populate(LocalEpisodeAlreadyPlayingClientState localEpisodeAlreadyPlayingClientState) {
            super.populate((ClientState) localEpisodeAlreadyPlayingClientState);
            localEpisodeAlreadyPlayingClientState.setAlreadyPlaying(this.alreadyPlaying);
        }

        public Builder withAlreadyPlaying(Boolean bool) {
            this.alreadyPlaying = bool;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof LocalEpisodeAlreadyPlayingClientState)) {
            return 1;
        }
        Boolean boolIsAlreadyPlaying = isAlreadyPlaying();
        Boolean boolIsAlreadyPlaying2 = ((LocalEpisodeAlreadyPlayingClientState) sOAObject).isAlreadyPlaying();
        if (boolIsAlreadyPlaying != boolIsAlreadyPlaying2) {
            if (boolIsAlreadyPlaying == null) {
                return -1;
            }
            if (boolIsAlreadyPlaying2 == null) {
                return 1;
            }
            int iCompareTo = boolIsAlreadyPlaying.compareTo(boolIsAlreadyPlaying2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof LocalEpisodeAlreadyPlayingClientState) {
            return super.equals(obj) && internalEqualityCheck(isAlreadyPlaying(), ((LocalEpisodeAlreadyPlayingClientState) obj).isAlreadyPlaying());
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.ClientState, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isAlreadyPlaying());
    }

    public Boolean isAlreadyPlaying() {
        return this.alreadyPlaying;
    }

    public void setAlreadyPlaying(Boolean bool) {
        this.alreadyPlaying = bool;
    }
}
