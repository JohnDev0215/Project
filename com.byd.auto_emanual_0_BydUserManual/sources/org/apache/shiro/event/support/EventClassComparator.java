package org.apache.shiro.event.support;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class EventClassComparator implements Comparator<Class> {
    @Override // java.util.Comparator
    public int compare(Class cls, Class cls2) {
        if (cls == null) {
            return cls2 == null ? 0 : -1;
        }
        if (cls2 == null) {
            return 1;
        }
        if (cls != cls2 && !cls.equals(cls2)) {
            if (cls.isAssignableFrom(cls2)) {
                return 1;
            }
            if (cls2.isAssignableFrom(cls)) {
                return -1;
            }
        }
        return 0;
    }
}
