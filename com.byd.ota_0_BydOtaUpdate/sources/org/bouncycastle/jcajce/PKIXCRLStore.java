package org.bouncycastle.jcajce;

import g.a.f.h;
import java.security.cert.CRL;
import java.util.Collection;
import org.bouncycastle.util.StoreException;

/* JADX INFO: loaded from: classes.dex */
public interface PKIXCRLStore<T extends CRL> {
    Collection<T> getMatches(h<T> hVar) throws StoreException;
}
