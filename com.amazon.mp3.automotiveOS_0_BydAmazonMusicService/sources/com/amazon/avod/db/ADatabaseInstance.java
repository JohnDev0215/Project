package com.amazon.avod.db;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ADatabaseInstance {
    protected ArrayList<DBTable> dbTables = new ArrayList<>();
    private final String mDbName;
    private final int mDbVersion;
    private final Scope mScope;

    public enum Scope {
        USER_PRIVATE(false),
        GLOBAL(true);

        private final boolean mRequiresSharedFilePermissions;

        Scope(boolean z) {
            this.mRequiresSharedFilePermissions = z;
        }

        public boolean requiresSharedFilePermissions() {
            return this.mRequiresSharedFilePermissions;
        }
    }

    public String getDBName() {
        return this.mDbName;
    }

    public int getDBVersion() {
        return this.mDbVersion;
    }

    public List<DBTable> getDBTables() {
        return this.dbTables;
    }

    protected ADatabaseInstance(@Nullable String str, @Nonnegative int i, @Nonnull Scope scope) {
        Preconditions.checkArgument(i >= 0, "dbVersion < 0 (%s)", i);
        this.mDbName = str;
        this.mDbVersion = i;
        this.mScope = (Scope) Preconditions.checkNotNull(scope, "scope");
    }

    protected void addTable(DBTable dBTable) {
        this.dbTables.add(dBTable);
    }

    public String getFilePath(StorageHelper storageHelper) {
        if (this.mDbName.charAt(0) == File.separatorChar || "".equals(this.mDbName)) {
            return this.mDbName;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$db$ADatabaseInstance$Scope[this.mScope.ordinal()];
        if (i == 1) {
            return getFullDatabasePath(storageHelper.getGeneralFileDir());
        }
        if (i == 2) {
            return getFullDatabasePath(storageHelper.getGlobalSharedDir());
        }
        throw new IllegalStateException("New database scope added without file path definition: " + this.mScope.name());
    }

    /* JADX INFO: renamed from: com.amazon.avod.db.ADatabaseInstance$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$db$ADatabaseInstance$Scope;

        static {
            int[] iArr = new int[Scope.values().length];
            $SwitchMap$com$amazon$avod$db$ADatabaseInstance$Scope = iArr;
            try {
                iArr[Scope.USER_PRIVATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$db$ADatabaseInstance$Scope[Scope.GLOBAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public String getFullDatabasePath(File file) {
        String absolutePath = new File(new File(file, "databases"), this.mDbName).getAbsolutePath();
        DLog.devf("Database %s with scope %s is located at %s", this.mDbName, this.mScope, absolutePath);
        return absolutePath;
    }
}
