package com.spotify.connectivity.productstateflags;

import com.spotify.connectivity.flags.Source;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateSource extends Source {
    public static final Companion Companion = new Companion(null);
    public static final String TYPE = "product-state";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProductStateSource(String str) {
        super(TYPE, str);
        v03.j(str, "productStateKey");
    }
}
