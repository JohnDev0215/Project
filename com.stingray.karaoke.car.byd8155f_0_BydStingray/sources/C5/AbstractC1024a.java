package c5;

/* JADX INFO: renamed from: c5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1024a implements InterfaceC1028e {
    public abstract boolean a(char c);

    @Override // c5.InterfaceC1028e
    public final boolean apply(Object obj) {
        return a(((Character) obj).charValue());
    }
}
