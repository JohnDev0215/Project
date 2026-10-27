package B5;

import android.util.JsonToken;

/* JADX INFO: renamed from: B5.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0088l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f724a;

    static {
        int[] iArr = new int[JsonToken.values().length];
        f724a = iArr;
        try {
            iArr[JsonToken.BEGIN_OBJECT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f724a[JsonToken.BEGIN_ARRAY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f724a[JsonToken.BOOLEAN.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f724a[JsonToken.NULL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f724a[JsonToken.NUMBER.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f724a[JsonToken.STRING.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
