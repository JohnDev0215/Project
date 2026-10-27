package androidx.room;

import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Function {
    public static final /* synthetic */ b b = new b(0);
    public static final /* synthetic */ b c = new b(1);
    public static final /* synthetic */ b d = new b(2);
    public static final /* synthetic */ b e = new b(3);
    public static final /* synthetic */ b f = new b(4);
    public static final /* synthetic */ b g = new b(5);
    public static final /* synthetic */ b h = new b(6);
    public final /* synthetic */ int a;

    public /* synthetic */ b(int i) {
        this.a = i;
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$isWriteAheadLoggingEnabled$13((SupportSQLiteDatabase) obj);
            case 1:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.lambda$execute$1((SupportSQLiteStatement) obj);
            case 2:
                return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDbLockedByCurrentThread());
            case 3:
                return ((SupportSQLiteDatabase) obj).getAttachedDbs();
            case 4:
                return Long.valueOf(((SupportSQLiteDatabase) obj).getMaximumSize());
            case 5:
                return ((SupportSQLiteDatabase) obj).getPath();
            default:
                return Boolean.valueOf(((SupportSQLiteDatabase) obj).yieldIfContendedSafely());
        }
    }
}
