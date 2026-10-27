package defpackage;

/* JADX INFO: compiled from: VisibilityPropagation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class no3 extends vd1 {
    public static final String[] k = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    public static int J(df3 df3Var, int i) {
        int[] iArr;
        if (df3Var == null || (iArr = (int[]) df3Var.a.get("android:visibilityPropagation:center")) == null) {
            return -1;
        }
        return iArr[i];
    }
}
