package org.apache.tools.ant.types.selectors.modifiedselector;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class EqualComparator implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (obj != null) {
            return 1 ^ (obj.equals(obj2) ? 1 : 0);
        }
        if (obj2 == null) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return "EqualComparator";
    }
}
