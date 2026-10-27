package com.amazon.mp3.sharing;

import android.content.Context;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.music.share.ShareProviderClientAction;

/* JADX INFO: loaded from: classes4.dex */
public class ShareProviderActionHandler implements ShareProviderClientAction {
    public static ShareProviderActionHandler newInstance() {
        return new ShareProviderActionHandler();
    }

    @Override // com.amazon.music.share.ShareProviderClientAction
    public void startUserPlaylistsSync(final Context context) {
        SyncService.startSync(context, 1);
    }
}
