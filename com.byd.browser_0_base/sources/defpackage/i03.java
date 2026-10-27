package defpackage;

/* JADX INFO: compiled from: TabGroupTitleUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i03 {
    public static String a(int i) {
        return xz.a.getSharedPreferences("tab_group_titles", 0).getString(String.valueOf(i), null);
    }
}
