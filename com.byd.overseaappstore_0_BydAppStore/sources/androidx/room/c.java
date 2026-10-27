package androidx.room;

import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Function {
    public static final /* synthetic */ c b = new c(0);
    public static final /* synthetic */ c c = new c(1);
    public static final /* synthetic */ c d = new c(2);
    public static final /* synthetic */ c e = new c(3);
    public static final /* synthetic */ c f = new c(4);
    public static final /* synthetic */ c g = new c(5);
    public static final /* synthetic */ c h = new c(6);
    public final /* synthetic */ int a;

    public /* synthetic */ c(int i) {
        this.a = i;
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$pokeOpen$0((SupportSQLiteDatabase) obj);
            case 1:
                return Boolean.valueOf(((SupportSQLiteDatabase) obj).isReadOnly());
            case 2:
                return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDatabaseIntegrityOk());
            case 3:
                return Long.valueOf(((SupportSQLiteDatabase) obj).getPageSize());
            case 4:
                return Boolean.valueOf(((SupportSQLiteDatabase) obj).yieldIfContendedSafely());
            case 5:
                return Integer.valueOf(((SupportSQLiteStatement) obj).executeUpdateDelete());
            default:
                return Long.valueOf(((SupportSQLiteStatement) obj).executeInsert());
        }
    }
}
