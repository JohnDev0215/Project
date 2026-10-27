package d1;

/* JADX INFO: compiled from: RoomMasterTable.java */
/* JADX INFO: loaded from: classes.dex */
public class j {
    public static String a(String str) {
        return "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + str + "')";
    }
}
