package org.apache.tools.ant.util;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class CollectionUtils {
    public static boolean equals(Vector v1, Vector v2) {
        if (v1 == v2) {
            return true;
        }
        if (v1 == null || v2 == null) {
            return false;
        }
        return v1.equals(v2);
    }

    public static boolean equals(Dictionary d1, Dictionary d2) {
        if (d1 == d2) {
            return true;
        }
        if (d1 == null || d2 == null || d1.size() != d2.size()) {
            return false;
        }
        Enumeration e1 = d1.keys();
        while (e1.hasMoreElements()) {
            Object key = e1.nextElement();
            Object value1 = d1.get(key);
            Object value2 = d2.get(key);
            if (value2 == null || !value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

    public static void putAll(Dictionary m1, Dictionary m2) {
        Enumeration it = m2.keys();
        while (it.hasMoreElements()) {
            Object key = it.nextElement();
            m1.put(key, m2.get(key));
        }
    }

    public static final class EmptyEnumeration implements Enumeration {
        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public Object nextElement() throws NoSuchElementException {
            throw new NoSuchElementException();
        }
    }

    public static Enumeration append(Enumeration e1, Enumeration e2) {
        return new CompoundEnumeration(e1, e2);
    }

    private static final class CompoundEnumeration implements Enumeration {
        private final Enumeration e1;
        private final Enumeration e2;

        public CompoundEnumeration(Enumeration e1, Enumeration e2) {
            this.e1 = e1;
            this.e2 = e2;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.e1.hasMoreElements() || this.e2.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() throws NoSuchElementException {
            if (this.e1.hasMoreElements()) {
                return this.e1.nextElement();
            }
            return this.e2.nextElement();
        }
    }
}
