package org.bouncycastle.util;

import java.util.Collection;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface Store<T> {
    Collection<T> getMatches(Selector<T> selector);
}
