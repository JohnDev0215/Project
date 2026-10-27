package h.a.a.a;

/* JADX INFO: compiled from: ExportStat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final String a(int i) {
        if (i == 0) {
            return "EXPO_NO_RESPONCE";
        }
        if (i == 1) {
            return "EXPO_SUCCESS";
        }
        if (i == 2) {
            return "EXPO_EXPORTING";
        }
        if (i == 3) {
            return "EXPO_EMPTY";
        }
        if (i == 4) {
            return "EXPO_NO_DISK_ERR";
        }
        if (i == 5) {
            return "EXPO_WRITE_ERR";
        }
        return "0x" + Integer.toHexString(i);
    }
}
