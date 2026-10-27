package org.chromium.components.content_creation.notes.bridges;

import J.N;
import defpackage.go1;
import defpackage.wb;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class NoteServiceBridge implements go1 {
    public long a;

    public NoteServiceBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static NoteServiceBridge create(long j) {
        return new NoteServiceBridge(j);
    }

    @Override // defpackage.go1
    public final void a(wb wbVar) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.M9J_OLxe(j, this, wbVar);
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
    }
}
