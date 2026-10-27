package androidx.room;

import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Function {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ e(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setLocale$10((Locale) this.b, (SupportSQLiteDatabase) obj);
            default:
                return ((RoomDatabase) this.b).lambda$beginTransaction$0((SupportSQLiteDatabase) obj);
        }
    }
}
