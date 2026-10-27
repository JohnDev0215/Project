package p;

import android.widget.ImageView;
import coil.decode.DataSource;
import coil.size.Scale;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[DataSource.values().length];
        try {
            iArr[0] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            DataSource dataSource = DataSource.c;
            iArr[1] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            DataSource dataSource2 = DataSource.c;
            iArr[2] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            DataSource dataSource3 = DataSource.c;
            iArr[3] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[ImageView.ScaleType.values().length];
        try {
            iArr2[ImageView.ScaleType.FIT_START.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[ImageView.ScaleType.FIT_END.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr2[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 4;
        } catch (NoSuchFieldError unused8) {
        }
        a = iArr2;
        int[] iArr3 = new int[Scale.values().length];
        try {
            iArr3[0] = 1;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            Scale scale = Scale.c;
            iArr3[1] = 2;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
