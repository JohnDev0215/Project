package S1;

import A7.m;
import A7.o;
import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class a extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f7863m = new a(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        String strValueOf;
        Map.Entry entry = (Map.Entry) obj;
        m.f(entry, "entry");
        Object value = entry.getValue();
        if (value instanceof byte[]) {
            byte[] bArr = (byte[]) value;
            m.f(bArr, "<this>");
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) "[");
            int i7 = 0;
            for (byte b10 : bArr) {
                i7++;
                if (i7 > 1) {
                    sb.append((CharSequence) ", ");
                }
                sb.append((CharSequence) String.valueOf((int) b10));
            }
            sb.append((CharSequence) "]");
            strValueOf = sb.toString();
        } else {
            strValueOf = String.valueOf(entry.getValue());
        }
        return "  " + ((d) entry.getKey()).f7868a + " = " + strValueOf;
    }
}
