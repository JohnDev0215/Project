package com.amazon.musicidentityservice.shared.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class MusicRequestIdentityContext implements Comparable<MusicRequestIdentityContext> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext");
    private MusicIdentities musicIdentities;
    private MusicRequestIdentityHints musicRequestIdentityHints;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicRequestIdentityContext musicRequestIdentityContext) {
        if (musicRequestIdentityContext == null) {
            return -1;
        }
        if (musicRequestIdentityContext == this) {
            return 0;
        }
        MusicIdentities musicIdentities = getMusicIdentities();
        MusicIdentities musicIdentities2 = musicRequestIdentityContext.getMusicIdentities();
        if (musicIdentities != musicIdentities2) {
            if (musicIdentities == null) {
                return -1;
            }
            if (musicIdentities2 == null) {
                return 1;
            }
            int iCompareTo = musicIdentities.compareTo(musicIdentities2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        MusicRequestIdentityHints musicRequestIdentityHints = getMusicRequestIdentityHints();
        MusicRequestIdentityHints musicRequestIdentityHints2 = musicRequestIdentityContext.getMusicRequestIdentityHints();
        if (musicRequestIdentityHints != musicRequestIdentityHints2) {
            if (musicRequestIdentityHints == null) {
                return -1;
            }
            if (musicRequestIdentityHints2 == null) {
                return 1;
            }
            int iCompareTo2 = musicRequestIdentityHints.compareTo(musicRequestIdentityHints2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicRequestIdentityContext)) {
            return false;
        }
        MusicRequestIdentityContext musicRequestIdentityContext = (MusicRequestIdentityContext) obj;
        return internalEqualityCheck(getMusicIdentities(), musicRequestIdentityContext.getMusicIdentities()) && internalEqualityCheck(getMusicRequestIdentityHints(), musicRequestIdentityContext.getMusicRequestIdentityHints());
    }

    public MusicIdentities getMusicIdentities() {
        return this.musicIdentities;
    }

    public MusicRequestIdentityHints getMusicRequestIdentityHints() {
        return this.musicRequestIdentityHints;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMusicIdentities(), getMusicRequestIdentityHints());
    }

    public void setMusicIdentities(MusicIdentities musicIdentities) {
        this.musicIdentities = musicIdentities;
    }

    public void setMusicRequestIdentityHints(MusicRequestIdentityHints musicRequestIdentityHints) {
        this.musicRequestIdentityHints = musicRequestIdentityHints;
    }
}
