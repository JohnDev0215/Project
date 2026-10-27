package com.byd.data.common.entiry.extension;

import com.byd.data.common.i.f;
import d.a.a.f.b.c.a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EvExtData extends ExtBaseData {
    public ExtensionItem a(short s, byte b2) {
        if (a.a(this.extList)) {
            return null;
        }
        List<ExtensionItem> list = this.extList;
        int i2 = 0;
        int size = list.size() - 1;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            ExtensionItem extensionItem = list.get(i3);
            short s2 = extensionItem.canId;
            if (s2 >= s) {
                if (s2 <= s) {
                    byte b3 = extensionItem.subId;
                    if (b3 == 0) {
                        return extensionItem;
                    }
                    if (b3 >= b2) {
                        if (b3 <= b2) {
                            f.b(this.TAG, "findCanIndex has subId index =" + i3);
                            return extensionItem;
                        }
                    }
                }
                size = i3 - 1;
            }
            i2 = i3 + 1;
        }
        return null;
    }
}
