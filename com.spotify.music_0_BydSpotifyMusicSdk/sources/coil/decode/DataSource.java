package coil.decode;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class DataSource {
    public static final DataSource c;
    public static final DataSource e;
    public static final DataSource k;
    public static final DataSource l;
    public static final /* synthetic */ DataSource[] m;

    static {
        DataSource dataSource = new DataSource("MEMORY_CACHE", 0);
        c = dataSource;
        DataSource dataSource2 = new DataSource("MEMORY", 1);
        e = dataSource2;
        DataSource dataSource3 = new DataSource("DISK", 2);
        k = dataSource3;
        DataSource dataSource4 = new DataSource("NETWORK", 3);
        l = dataSource4;
        DataSource[] dataSourceArr = {dataSource, dataSource2, dataSource3, dataSource4};
        m = dataSourceArr;
        kotlin.enums.a.a(dataSourceArr);
    }

    public static DataSource valueOf(String str) {
        return (DataSource) Enum.valueOf(DataSource.class, str);
    }

    public static DataSource[] values() {
        return (DataSource[]) m.clone();
    }
}
