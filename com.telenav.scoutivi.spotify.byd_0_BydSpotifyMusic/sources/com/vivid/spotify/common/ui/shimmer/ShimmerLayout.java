package com.vivid.spotify.common.ui.shimmer;

import android.content.Context;
import android.util.AttributeSet;
import n2.b;
import n2.d;

/* JADX INFO: loaded from: classes.dex */
public class ShimmerLayout extends d {
    public ShimmerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    private void e() {
        b.a aVar = new b.a();
        aVar.f(0.8f).j(2000L).o(0.55f);
        b(aVar.a());
    }
}
