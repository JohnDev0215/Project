package com.amazon.podcast.redhoodPlus;

import UpsellInterface.v1_0.ContentTrait;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ContentTraits {
    public static final List<String> traits(final List<String> traits) {
        if (CollectionUtils.isEmpty(traits)) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : traits) {
            if (Arrays.asList(ContentTrait.values()).contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
