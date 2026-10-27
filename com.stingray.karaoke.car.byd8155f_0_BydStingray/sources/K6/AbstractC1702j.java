package k6;

import com.stingray.drivemodedetector.DriveMode;

/* JADX INFO: renamed from: k6.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1702j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f18270a;

    static {
        int[] iArr = new int[DriveMode.values().length];
        try {
            iArr[DriveMode.PARKED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[DriveMode.DRIVING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f18270a = iArr;
    }
}
