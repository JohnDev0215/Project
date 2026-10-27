package defpackage;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: CastMediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class sm implements ne1 {
    public static final List<String> e = Arrays.asList("custom_controller_scoped", "tab_and_origin_scoped", "origin_scoped", "page_scoped");
    public static final List<String> f = Arrays.asList("video_out", "audio_out", "video_in", "audio_in", "multizone_group");

    public sm(String str) {
    }

    public static String b(String[] strArr, String str) {
        String strConcat = str.concat("=");
        for (String str2 : strArr) {
            if (str2.startsWith(strConcat)) {
                return str2.substring(strConcat.length());
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.sm c(java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sm.c(java.lang.String):sm");
    }

    @Override // defpackage.ne1
    public final void a() {
    }
}
