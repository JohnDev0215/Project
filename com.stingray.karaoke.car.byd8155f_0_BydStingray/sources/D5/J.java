package d5;

/* JADX INFO: loaded from: classes.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f13385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f13386b;
    public final Object c;

    public J(Object obj, Object obj2, Object obj3) {
        this.f13385a = obj;
        this.f13386b = obj2;
        this.c = obj3;
    }

    public final IllegalArgumentException a() {
        StringBuilder sb = new StringBuilder("Multiple entries with same key: ");
        Object obj = this.f13385a;
        sb.append(obj);
        sb.append("=");
        sb.append(this.f13386b);
        sb.append(" and ");
        sb.append(obj);
        sb.append("=");
        sb.append(this.c);
        return new IllegalArgumentException(sb.toString());
    }
}
