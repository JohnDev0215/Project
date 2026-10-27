package androidx.room;

import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements Function {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;

    public /* synthetic */ a(int i, int i2) {
        this.a = i2;
        this.b = i;
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setMaxSqlCacheSize$11(this.b, (SupportSQLiteDatabase) obj);
            case 1:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setVersion$1(this.b, (SupportSQLiteDatabase) obj);
            default:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$needUpgrade$9(this.b, (SupportSQLiteDatabase) obj);
        }
    }
}
