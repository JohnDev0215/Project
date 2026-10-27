package com.amazon.avod.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.upgrade.UpgradeManager;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DBOpenHelperSupplier {
    protected final Supplier<ADatabaseInstance> mADatabaseInstance;
    protected final Context mContext;
    protected final Supplier<UpgradeManager<SQLiteDatabase>> mUpgradeManager;
    protected final Object mLock = new Object();
    protected volatile DBOpenHelper mDBOpenHelper = null;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ADatabaseInstance lambda$new$0(ADatabaseInstance aDatabaseInstance) {
        return aDatabaseInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ UpgradeManager lambda$new$1(UpgradeManager upgradeManager) {
        return upgradeManager;
    }

    public DBOpenHelperSupplier(@Nonnull Context context, @Nonnull final UpgradeManager<SQLiteDatabase> upgradeManager, @Nonnull final ADatabaseInstance aDatabaseInstance) {
        Preconditions.checkNotNull(aDatabaseInstance, "aDatabaseInstance");
        Preconditions.checkNotNull(upgradeManager, "upgradeManager");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mADatabaseInstance = new Supplier() { // from class: com.amazon.avod.db.DBOpenHelperSupplier$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return DBOpenHelperSupplier.lambda$new$0(aDatabaseInstance);
            }
        };
        this.mUpgradeManager = new Supplier() { // from class: com.amazon.avod.db.DBOpenHelperSupplier$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return DBOpenHelperSupplier.lambda$new$1(upgradeManager);
            }
        };
    }

    public DBOpenHelperSupplier(@Nonnull Context context, @Nonnull Supplier<UpgradeManager<SQLiteDatabase>> supplier, @Nonnull Supplier<ADatabaseInstance> supplier2) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mADatabaseInstance = (Supplier) Preconditions.checkNotNull(supplier2, "aDatabaseInstance");
        this.mUpgradeManager = (Supplier) Preconditions.checkNotNull(supplier, "upgradeManager");
    }

    public DBOpenHelper get() {
        DBOpenHelper dBOpenHelper = this.mDBOpenHelper;
        if (dBOpenHelper != null) {
            return dBOpenHelper;
        }
        synchronized (this.mLock) {
            if (this.mDBOpenHelper != null) {
                return this.mDBOpenHelper;
            }
            this.mDBOpenHelper = new DBOpenHelper(this.mContext, this.mADatabaseInstance.get(), this.mUpgradeManager.get());
            return this.mDBOpenHelper;
        }
    }

    public boolean deleteDatabase() {
        boolean zDeleteDatabase = get().deleteDatabase();
        reset();
        return zDeleteDatabase;
    }

    private void reset() {
        synchronized (this.mLock) {
            this.mDBOpenHelper = null;
        }
    }
}
