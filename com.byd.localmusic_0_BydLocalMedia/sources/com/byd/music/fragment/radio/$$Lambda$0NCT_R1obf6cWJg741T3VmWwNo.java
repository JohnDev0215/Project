package com.byd.music.fragment.radio;

import com.byd.music.beans.RadioDBBean;
import java.util.function.Function;

/* JADX INFO: renamed from: com.byd.music.fragment.radio.-$$Lambda$0NCT_R1obf6cWJg741T3VmW-wNo, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class $$Lambda$0NCT_R1obf6cWJg741T3VmWwNo implements Function {
    public static final /* synthetic */ $$Lambda$0NCT_R1obf6cWJg741T3VmWwNo INSTANCE = new $$Lambda$0NCT_R1obf6cWJg741T3VmWwNo();

    private /* synthetic */ $$Lambda$0NCT_R1obf6cWJg741T3VmWwNo() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Long.valueOf(((RadioDBBean) obj).getDAB_SID());
    }
}
