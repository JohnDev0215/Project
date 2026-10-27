package androidx.room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.RoomDatabase;

/* JADX INFO: loaded from: classes.dex */
public class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    public static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    @Deprecated
    public Room() {
    }

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(@NonNull Context context, @NonNull Class<T> cls, @NonNull String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
        }
        return new RoomDatabase.Builder<>(context, cls, str);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static <T, C> T getGeneratedImplementation(@NonNull Class<C> cls, @NonNull String str) {
        String str2;
        String name = cls.getPackage().getName();
        String canonicalName = cls.getCanonicalName();
        if (!name.isEmpty()) {
            canonicalName = canonicalName.substring(name.length() + 1);
        }
        String str3 = canonicalName.replace('.', '_') + str;
        try {
            if (name.isEmpty()) {
                str2 = str3;
            } else {
                str2 = name + "." + str3;
            }
            return (T) Class.forName(str2, true, cls.getClassLoader()).newInstance();
        } catch (ClassNotFoundException unused) {
            StringBuilder sbK = spi.e.k("cannot find implementation for ");
            sbK.append(cls.getCanonicalName());
            sbK.append(". ");
            sbK.append(str3);
            sbK.append(" does not exist");
            throw new RuntimeException(sbK.toString());
        } catch (IllegalAccessException unused2) {
            StringBuilder sbK2 = spi.e.k("Cannot access the constructor");
            sbK2.append(cls.getCanonicalName());
            throw new RuntimeException(sbK2.toString());
        } catch (InstantiationException unused3) {
            StringBuilder sbK3 = spi.e.k("Failed to create an instance of ");
            sbK3.append(cls.getCanonicalName());
            throw new RuntimeException(sbK3.toString());
        }
    }

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(@NonNull Context context, @NonNull Class<T> cls) {
        return new RoomDatabase.Builder<>(context, cls, null);
    }
}
