package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.views.library.models.enums.TargetType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: BrushTargetTypeUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushTargetTypeUtils;", "", "", "target", "Lcom/amazon/music/views/library/models/enums/TargetType;", "getTargetType", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushTargetTypeUtils {
    public static final BrushTargetTypeUtils INSTANCE = new BrushTargetTypeUtils();

    private BrushTargetTypeUtils() {
    }

    public final TargetType getTargetType(String target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return StringsKt__StringsJVMKt.startsWith$default(target, FindSearchBarView.URI_PREFIX, false, 2, null) ? TargetType.URI : TargetType.DEEPLINK;
    }
}
