package I5;

import androidx.lifecycle.EnumC0980s;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f3015a;

    static {
        int[] iArr = new int[EnumC0980s.values().length];
        try {
            iArr[EnumC0980s.ON_STOP.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[EnumC0980s.ON_RESUME.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f3015a = iArr;
    }
}
