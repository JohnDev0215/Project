package org.apache.commons.net.nntp;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class ArticleIterator implements Iterator<Article>, Iterable<Article> {
    private final Iterator<String> stringIterator;

    public ArticleIterator(Iterable<String> iterableString) {
        this.stringIterator = iterableString.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.stringIterator.hasNext();
    }

    @Override // java.util.Iterator
    public Article next() {
        String line = this.stringIterator.next();
        return NNTPClient.parseArticleEntry(line);
    }

    @Override // java.util.Iterator
    public void remove() {
        this.stringIterator.remove();
    }

    @Override // java.lang.Iterable
    public Iterator<Article> iterator() {
        return this;
    }
}
