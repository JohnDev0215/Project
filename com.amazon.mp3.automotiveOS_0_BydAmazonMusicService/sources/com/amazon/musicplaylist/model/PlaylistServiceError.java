package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistServiceError implements Comparable<PlaylistServiceError> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.PlaylistServiceError");
    private String message;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaylistServiceError playlistServiceError) {
        String message;
        String message2;
        if (playlistServiceError == null) {
            return -1;
        }
        if (playlistServiceError != this && (message = getMessage()) != (message2 = playlistServiceError.getMessage())) {
            if (message == null) {
                return -1;
            }
            if (message2 == null) {
                return 1;
            }
            int iCompareTo = message.compareTo(message2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PlaylistServiceError) {
            return internalEqualityCheck(getMessage(), ((PlaylistServiceError) obj).getMessage());
        }
        return false;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMessage());
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
