package com.amazon.avod.events.db;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.db.ADatabaseInstance;

/* JADX INFO: loaded from: classes2.dex */
public class DBEvent extends ADatabaseInstance {
    public DBEvent() {
        super(NotificationCompat.CATEGORY_EVENT, 7, ADatabaseInstance.Scope.USER_PRIVATE);
        addTable(EventsTable.getInstance());
    }
}
