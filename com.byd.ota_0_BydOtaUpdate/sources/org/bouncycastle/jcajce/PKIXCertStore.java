package org.bouncycastle.jcajce;

import g.a.f.h;
import java.security.cert.Certificate;
import java.util.Collection;
import org.bouncycastle.util.StoreException;

/* JADX INFO: loaded from: classes.dex */
public interface PKIXCertStore<T extends Certificate> {
    Collection<T> getMatches(h<T> hVar) throws StoreException;
}
