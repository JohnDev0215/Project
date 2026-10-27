package android.content.res;

/* JADX INFO: loaded from: classes.dex */
public final class ResourceId {
    public static boolean isValid(int i) {
        return (i == -1 || ((-16777216) & i) == 0 || (i & 16711680) == 0) ? false : true;
    }
}
