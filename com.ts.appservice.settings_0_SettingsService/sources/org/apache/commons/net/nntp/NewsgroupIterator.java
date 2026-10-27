package org.apache.commons.net.nntp;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class NewsgroupIterator implements Iterator<NewsgroupInfo>, Iterable<NewsgroupInfo> {
    private final Iterator<String> stringIterator;

    public NewsgroupIterator(Iterable<String> iterableString) {
        this.stringIterator = iterableString.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.stringIterator.hasNext();
    }

    @Override // java.util.Iterator
    public NewsgroupInfo next() {
        String line = this.stringIterator.next();
        return NNTPClient.parseNewsgroupListEntry(line);
    }

    @Override // java.util.Iterator
    public void remove() {
        this.stringIterator.remove();
    }

    @Override // java.lang.Iterable
    public Iterator<NewsgroupInfo> iterator() {
        return this;
    }
}
