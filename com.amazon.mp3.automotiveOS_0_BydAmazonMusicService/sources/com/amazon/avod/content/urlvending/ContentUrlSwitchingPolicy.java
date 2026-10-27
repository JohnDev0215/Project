package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public interface ContentUrlSwitchingPolicy {
    void onSwitchContentUrl();

    boolean shouldSwitchContentUrlOnFailure(int i, ContentException contentException);
}
