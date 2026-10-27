package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class cd2 {
    public static final tz2 a = new tz2("NO_VALUE", 3);

    public static final void a(Object[] objArr, long j, Object obj) {
        objArr[((int) j) & (objArr.length - 1)] = obj;
    }
}
