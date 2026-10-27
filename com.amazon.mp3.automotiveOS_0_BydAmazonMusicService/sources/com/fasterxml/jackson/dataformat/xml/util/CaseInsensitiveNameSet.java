package com.fasterxml.jackson.dataformat.xml.util;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public final class CaseInsensitiveNameSet extends AbstractSet<String> {
    private final Set<String> _namesToMatch;

    private CaseInsensitiveNameSet(Set<String> set) {
        this._namesToMatch = set;
    }

    public static CaseInsensitiveNameSet construct(Set<String> set) {
        HashSet hashSet = new HashSet(set);
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().toLowerCase());
        }
        return new CaseInsensitiveNameSet(hashSet);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        String str = (String) obj;
        if (this._namesToMatch.contains(str)) {
            return true;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase != str && this._namesToMatch.contains(lowerCase);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<String> iterator() {
        return this._namesToMatch.iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this._namesToMatch.size();
    }
}
