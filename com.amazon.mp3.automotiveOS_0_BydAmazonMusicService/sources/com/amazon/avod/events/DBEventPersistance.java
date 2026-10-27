package com.amazon.avod.events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.amazon.avod.db.ADatabaseInstance;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.events.db.DBEvent;
import com.amazon.avod.events.db.upgrade.DBEventUpgradeManager;
import com.amazon.avod.upgrade.UpgradeManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* JADX INFO: loaded from: classes2.dex */
public class DBEventPersistance implements EventPersistance {
    private final DBOpenHelperSupplier mDBOpenHelperSupplier;
    private final DBEventTransformer mTransformer;

    public DBEventPersistance(Context context, CompositeEventModelFactory compositeEventModelFactory) {
        this(new DBOpenHelperSupplier(context, (Supplier<UpgradeManager<SQLiteDatabase>>) Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.events.DBEventPersistance$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return new DBEventUpgradeManager();
            }
        }), (Supplier<ADatabaseInstance>) Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.events.DBEventPersistance$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return new DBEvent();
            }
        })), new DBEventTransformer(compositeEventModelFactory));
    }

    DBEventPersistance(DBOpenHelperSupplier dBOpenHelperSupplier, DBEventTransformer dBEventTransformer) {
        this.mDBOpenHelperSupplier = dBOpenHelperSupplier;
        this.mTransformer = dBEventTransformer;
    }

    @Override // com.amazon.avod.events.EventPersistance
    public synchronized boolean persistEvent(EventData eventData) {
        boolean zInnerUpdateEvent;
        if (-1 == eventData.getId()) {
            zInnerUpdateEvent = innerInsertEvent(eventData);
        } else {
            zInnerUpdateEvent = innerUpdateEvent(eventData);
        }
        return zInnerUpdateEvent;
    }

    @Override // com.amazon.avod.events.EventPersistance
    public synchronized void cleanUpEvents() {
        this.mDBOpenHelperSupplier.get().cleanupDB();
    }

    private boolean innerInsertEvent(EventData eventData) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            return false;
        }
        try {
            long jInsertOrThrow = writableDatabase.insertOrThrow("events", "", this.mTransformer.toContentValues(eventData));
            z = jInsertOrThrow >= 0;
            if (z) {
                eventData.setId(jInsertOrThrow);
            }
        } catch (SQLiteException e) {
            DLog.exceptionf(e);
        }
        return z;
    }

    private boolean innerUpdateEvent(EventData eventData) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            return false;
        }
        try {
            return writableDatabase.update("events", this.mTransformer.toContentValues(eventData), "_id=?", new String[]{Long.toString(eventData.getId())}) > 0;
        } catch (SQLiteException e) {
            DLog.exceptionf(e);
            return false;
        }
    }

    private SQLiteDatabase getWritableDatabase() {
        try {
            return this.mDBOpenHelperSupplier.get().getWritableDatabase();
        } catch (SQLiteException e) {
            DLog.exceptionf(e);
            return null;
        }
    }
}
