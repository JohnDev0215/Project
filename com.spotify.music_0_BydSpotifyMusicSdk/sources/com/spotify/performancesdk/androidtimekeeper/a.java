package com.spotify.performancesdk.androidtimekeeper;

import java.util.ArrayList;
import p.lq2;
import p.pl2;
import p.ry0;
import p.v03;
import p.vk2;

/* JADX INFO: loaded from: classes.dex */
public final class a implements pl2 {
    @Override // p.pl2
    public final void a(vk2 vk2Var) {
        v03.j(vk2Var, "measurement");
        String string = vk2Var.toString();
        v03.j(string, "<this>");
        LogTimeReporter$send$1 logTimeReporter$send$1 = new ry0() { // from class: com.spotify.performancesdk.androidtimekeeper.LogTimeReporter$send$1
            @Override // p.ry0
            public final Object invoke(Object obj) {
                CharSequence charSequence = (CharSequence) obj;
                v03.j(charSequence, "it");
                charSequence.toString();
                return lq2.a;
            }
        };
        v03.j(logTimeReporter$send$1, "transform");
        int length = string.length();
        int i = 0;
        ArrayList arrayList = new ArrayList((length / 4000) + (length % 4000 == 0 ? 0 : 1));
        while (i >= 0 && i < length) {
            int i2 = i + 4000;
            arrayList.add(logTimeReporter$send$1.invoke(string.subSequence(i, (i2 < 0 || i2 > length) ? length : i2)));
            i = i2;
        }
    }
}
