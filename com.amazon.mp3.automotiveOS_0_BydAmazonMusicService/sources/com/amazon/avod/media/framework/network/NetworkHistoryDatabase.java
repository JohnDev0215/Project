package com.amazon.avod.media.framework.network;

import com.amazon.avod.db.ADatabaseInstance;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkHistoryDatabase extends ADatabaseInstance {
    public NetworkHistoryDatabase() {
        super("wifiaccesspoints", 3, ADatabaseInstance.Scope.GLOBAL);
        addTable(new NetworkHistoryTable());
    }
}
