package com.amazon.podcast.redhoodPlus;

import UpsellInterface.v1_0.UpsellType;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Upsells {
    public static final List<String> types(final List<String> types) {
        if (CollectionUtils.isEmpty(types)) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : types) {
            if (Arrays.asList(UpsellType.values()).contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
