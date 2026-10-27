package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class TimerHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.TimerHint");
    private String timer;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof TimerHint)) {
            return 1;
        }
        String timer = getTimer();
        String timer2 = ((TimerHint) hint).getTimer();
        if (timer != timer2) {
            if (timer == null) {
                return -1;
            }
            if (timer2 == null) {
                return 1;
            }
            int iCompareTo = timer.compareTo(timer2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (obj instanceof TimerHint) {
            return super.equals(obj) && internalEqualityCheck(getTimer(), ((TimerHint) obj).getTimer());
        }
        return false;
    }

    public String getTimer() {
        return this.timer;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTimer());
    }

    public void setTimer(String str) {
        this.timer = str;
    }
}
