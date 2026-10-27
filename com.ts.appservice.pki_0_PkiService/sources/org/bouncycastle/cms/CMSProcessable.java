package org.bouncycastle.cms;

import java.io.OutputStream;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface CMSProcessable {
    Object getContent();

    void write(OutputStream outputStream);
}
