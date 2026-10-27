package com.android.permissioncontroller.role.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.icu.text.Collator;
import android.os.UserHandle;
import android.util.Pair;
import androidx.arch.core.util.Function;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes8.dex */
public class RoleSortFunction implements Function<List<Pair<ApplicationInfo, Boolean>>, List<Pair<ApplicationInfo, Boolean>>> {
    private final Comparator<Pair<ApplicationInfo, Boolean>> mComparator;

    public RoleSortFunction(final Context context) {
        Collator collator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        Comparator<Pair<ApplicationInfo, Boolean>> labelComparator = Comparator.comparing(new java.util.function.Function() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RoleSortFunction$0Bw1SXqDKpbYUoqlwmbYOIFkG_8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Utils.getAppLabel((ApplicationInfo) ((Pair) obj).first, context);
            }
        }, collator);
        Comparator<Pair<ApplicationInfo, Boolean>> userIdComparator = Comparator.comparingInt(new ToIntFunction() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RoleSortFunction$fhJWksEdpq-_ncG8Vr-JS4lvjkg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return UserHandle.getUserHandleForUid(((ApplicationInfo) ((Pair) obj).first).uid).getIdentifier();
            }
        });
        this.mComparator = labelComparator.thenComparing(userIdComparator);
    }

    @Override // androidx.arch.core.util.Function
    public List<Pair<ApplicationInfo, Boolean>> apply(List<Pair<ApplicationInfo, Boolean>> input) {
        List<Pair<ApplicationInfo, Boolean>> sorted = new ArrayList<>(input);
        sorted.sort(this.mComparator);
        return sorted;
    }
}
