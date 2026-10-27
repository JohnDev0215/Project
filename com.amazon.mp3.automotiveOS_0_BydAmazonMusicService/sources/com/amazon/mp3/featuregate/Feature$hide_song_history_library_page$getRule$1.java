package com.amazon.mp3.featuregate;

import com.amazon.mp3.util.PlatformUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class Feature$hide_song_history_library_page$getRule$1 extends FunctionReferenceImpl implements Function0<Boolean> {
    public static final Feature$hide_song_history_library_page$getRule$1 INSTANCE = new Feature$hide_song_history_library_page$getRule$1();

    Feature$hide_song_history_library_page$getRule$1() {
        super(0, PlatformUtil.class, "isFireOS", "isFireOS()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(PlatformUtil.isFireOS());
    }
}
