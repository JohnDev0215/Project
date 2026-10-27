package com.amazon.mp3.featuregate;

import android.content.Context;
import com.amazon.mp3.util.ConnectivityUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class Feature$music_never_stops_prime$getRule$1 extends FunctionReferenceImpl implements Function1<Context, Boolean> {
    public static final Feature$music_never_stops_prime$getRule$1 INSTANCE = new Feature$music_never_stops_prime$getRule$1();

    Feature$music_never_stops_prime$getRule$1() {
        super(1, ConnectivityUtil.class, "hasAnyInternetConnection", "hasAnyInternetConnection(Landroid/content/Context;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(Context context) {
        return Boolean.valueOf(ConnectivityUtil.hasAnyInternetConnection(context));
    }
}
