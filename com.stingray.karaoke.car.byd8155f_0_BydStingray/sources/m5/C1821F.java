package m5;

import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1821F extends j5.y {
    @Override // j5.y
    public final Object a(C2297a c2297a) {
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("java-lang-class-unsupported"));
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) {
        throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + ((Class) obj).getName() + ". Forgot to register a type adapter?\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("java-lang-class-unsupported"));
    }
}
