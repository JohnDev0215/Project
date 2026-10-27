package org.chromium.chrome.browser.translate;

import J.N;
import defpackage.f51;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class TranslateBridge {
    public static ArrayList a() {
        return new ArrayList(Arrays.asList(N.MMAgYJ7_()));
    }

    @CalledByNative
    public static void addNewLanguageItemToList(List<f51> list, String str, String str2, String str3, boolean z) {
        list.add(new f51(str, str2, str3, z));
    }
}
