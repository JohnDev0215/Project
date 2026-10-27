package com.amazon.eventvendingservice;

import com.amazon.layout.music.model.Root;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetProgramsResponse extends Root {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.eventvendingservice.GetProgramsResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Root, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Root root) {
        if (root == null) {
            return -1;
        }
        if (root == this) {
            return 0;
        }
        if (root instanceof GetProgramsResponse) {
            return super.compareTo(root);
        }
        return 1;
    }

    @Override // com.amazon.layout.music.model.Root
    public boolean equals(Object obj) {
        if (obj instanceof GetProgramsResponse) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.layout.music.model.Root
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
