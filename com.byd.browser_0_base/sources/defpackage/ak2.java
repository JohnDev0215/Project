package defpackage;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.components.browser_ui.share.a;

/* JADX INFO: compiled from: ShareContentTypeHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ak2 {
    static {
        new HashSet(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
    }

    public static HashSet a(a aVar, jr jrVar) {
        HashSet hashSet = new HashSet();
        boolean z = !TextUtils.isEmpty(aVar.e);
        if (z && !jrVar.h) {
            if (jrVar.c) {
                hashSet.add(0);
            } else {
                hashSet.add(1);
            }
        }
        if (!TextUtils.isEmpty(aVar.a())) {
            if (jrVar.j == 3) {
                hashSet.add(3);
            } else {
                hashSet.add(2);
            }
        }
        if (z && !TextUtils.isEmpty(aVar.a())) {
            hashSet.add(4);
        }
        if (aVar.g != null) {
            String str = aVar.f;
            if (TextUtils.isEmpty(str) || !str.startsWith("image/")) {
                hashSet.add(6);
            } else if (z) {
                hashSet.add(7);
            } else {
                hashSet.add(5);
            }
        }
        return hashSet;
    }
}
