package com.github.amlcurran.showcaseview.targets;

import android.graphics.Point;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes6.dex */
public interface Target {
    public static final Target NONE = new Target() { // from class: com.github.amlcurran.showcaseview.targets.Target.1
        @Override // com.github.amlcurran.showcaseview.targets.Target
        public Point getPoint() {
            return new Point(DurationKt.NANOS_IN_MILLIS, DurationKt.NANOS_IN_MILLIS);
        }
    };

    Point getPoint();
}
