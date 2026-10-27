package com.android.providers.media;

import com.android.providers.media.DatabaseHelper;
import com.android.providers.media.util.Metrics;

/* JADX INFO: renamed from: com.android.providers.media.-$$Lambda$v2UzoST2IXxt-fDHBJ7sMtJ9dvk, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk implements DatabaseHelper.OnSchemaChangeListener {
    public static final /* synthetic */ $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk INSTANCE = new $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk();

    private /* synthetic */ $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk() {
    }

    @Override // com.android.providers.media.DatabaseHelper.OnSchemaChangeListener
    public final void onSchemaChange(String str, int i, int i2, long j, long j2) {
        Metrics.logSchemaChange(str, i, i2, j, j2);
    }
}
