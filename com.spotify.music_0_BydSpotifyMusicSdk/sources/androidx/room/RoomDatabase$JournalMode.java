package androidx.room;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RoomDatabase$JournalMode {
    public static final RoomDatabase$JournalMode c;
    public static final RoomDatabase$JournalMode e;
    public static final RoomDatabase$JournalMode k;
    public static final /* synthetic */ RoomDatabase$JournalMode[] l;

    static {
        RoomDatabase$JournalMode roomDatabase$JournalMode = new RoomDatabase$JournalMode("AUTOMATIC", 0);
        c = roomDatabase$JournalMode;
        RoomDatabase$JournalMode roomDatabase$JournalMode2 = new RoomDatabase$JournalMode("TRUNCATE", 1);
        e = roomDatabase$JournalMode2;
        RoomDatabase$JournalMode roomDatabase$JournalMode3 = new RoomDatabase$JournalMode("WRITE_AHEAD_LOGGING", 2);
        k = roomDatabase$JournalMode3;
        l = new RoomDatabase$JournalMode[]{roomDatabase$JournalMode, roomDatabase$JournalMode2, roomDatabase$JournalMode3};
    }

    public static RoomDatabase$JournalMode valueOf(String str) {
        return (RoomDatabase$JournalMode) Enum.valueOf(RoomDatabase$JournalMode.class, str);
    }

    public static RoomDatabase$JournalMode[] values() {
        return (RoomDatabase$JournalMode[]) l.clone();
    }
}
