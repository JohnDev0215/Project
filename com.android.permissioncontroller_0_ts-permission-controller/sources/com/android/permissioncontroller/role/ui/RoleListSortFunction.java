package com.android.permissioncontroller.role.ui;

import android.content.Context;
import android.icu.text.Collator;
import androidx.arch.core.util.Function;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class RoleListSortFunction implements Function<List<RoleItem>, List<RoleItem>> {
    private final Comparator<RoleItem> mComparator;

    public RoleListSortFunction(final Context context) {
        Collator collator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        this.mComparator = Comparator.comparing(new java.util.function.Function() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RoleListSortFunction$ucAWdx0Se1C_BdNpn_LPrzNi1V0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return context.getString(((RoleItem) obj).getRole().getShortLabelResource());
            }
        }, collator);
    }

    @Override // androidx.arch.core.util.Function
    public List<RoleItem> apply(List<RoleItem> input) {
        List<RoleItem> sorted = new ArrayList<>(input);
        sorted.sort(this.mComparator);
        return sorted;
    }
}
