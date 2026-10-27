package com.amazon.mp3.util;

import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;

/* JADX INFO: compiled from: MathUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/util/MathUtil;", "", "()V", "generateRandomStartIndex", "", "listSize", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MathUtil {
    public static final MathUtil INSTANCE = new MathUtil();

    private MathUtil() {
    }

    public final int generateRandomStartIndex(int listSize) {
        if (listSize <= 0) {
            return 0;
        }
        return ThreadLocalRandom.current().nextInt(0, listSize);
    }
}
