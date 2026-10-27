package org.chromium.components.webxr;

import defpackage.aq1;
import defpackage.b8;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes3.dex */
@UsedByReflection
public class ArDelegateImpl implements b8 {
    @UsedByReflection
    public ArDelegateImpl() {
    }

    @Override // defpackage.b8
    public final boolean a() {
        ArCoreJavaUtils arCoreJavaUtils = ArCoreJavaUtils.c;
        if (arCoreJavaUtils == null) {
            return false;
        }
        arCoreJavaUtils.endSession();
        return true;
    }

    @Override // defpackage.b8
    public final aq1 b() {
        return ArCoreJavaUtils.d;
    }
}
