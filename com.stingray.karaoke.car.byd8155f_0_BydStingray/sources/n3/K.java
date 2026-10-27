package n3;

import android.os.Bundle;
import g1.AbstractC1274a;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class K extends M {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f19837f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ K(int i7, boolean z6) {
        super(z6);
        this.f19837f = i7;
    }

    @Override // n3.M
    public final Object a(String str, Bundle bundle) {
        switch (this.f19837f) {
            case 0:
                return (Boolean) AbstractC1347d.f(bundle, "bundle", str, "key", str);
            case 1:
                Object objF = AbstractC1347d.f(bundle, "bundle", str, "key", str);
                A7.m.d(objF, "null cannot be cast to non-null type kotlin.Int");
                return (Integer) objF;
            case 2:
                Object objF2 = AbstractC1347d.f(bundle, "bundle", str, "key", str);
                A7.m.d(objF2, "null cannot be cast to non-null type kotlin.Long");
                return (Long) objF2;
            default:
                return (String) AbstractC1347d.f(bundle, "bundle", str, "key", str);
        }
    }

    @Override // n3.M
    public final String b() {
        switch (this.f19837f) {
            case 0:
                return "boolean";
            case 1:
                return "integer";
            case 2:
                return "long";
            default:
                return "string";
        }
    }

    @Override // n3.M
    public final Object d(String str) {
        boolean z6;
        int i7;
        String strSubstring;
        long j3;
        switch (this.f19837f) {
            case 0:
                if (str.equals("true")) {
                    z6 = true;
                } else {
                    if (!str.equals("false")) {
                        throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
                    }
                    z6 = false;
                }
                return Boolean.valueOf(z6);
            case 1:
                if (Q8.o.z0(str, "0x", false)) {
                    String strSubstring2 = str.substring(2);
                    A7.m.e(strSubstring2, "substring(...)");
                    AbstractC1274a.y(16);
                    i7 = Integer.parseInt(strSubstring2, 16);
                } else {
                    i7 = Integer.parseInt(str);
                }
                return Integer.valueOf(i7);
            case 2:
                if (Q8.o.s0(str, "L", false)) {
                    strSubstring = str.substring(0, str.length() - 1);
                    A7.m.e(strSubstring, "substring(...)");
                } else {
                    strSubstring = str;
                }
                if (Q8.o.z0(str, "0x", false)) {
                    String strSubstring3 = strSubstring.substring(2);
                    A7.m.e(strSubstring3, "substring(...)");
                    AbstractC1274a.y(16);
                    j3 = Long.parseLong(strSubstring3, 16);
                } else {
                    j3 = Long.parseLong(strSubstring);
                }
                return Long.valueOf(j3);
            default:
                if (str.equals("null")) {
                    return null;
                }
                return str;
        }
    }

    @Override // n3.M
    public final void e(Bundle bundle, String str, Object obj) {
        switch (this.f19837f) {
            case 0:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                A7.m.f(str, "key");
                bundle.putBoolean(str, zBooleanValue);
                break;
            case 1:
                int iIntValue = ((Number) obj).intValue();
                A7.m.f(str, "key");
                bundle.putInt(str, iIntValue);
                break;
            case 2:
                long jLongValue = ((Number) obj).longValue();
                A7.m.f(str, "key");
                bundle.putLong(str, jLongValue);
                break;
            default:
                A7.m.f(str, "key");
                bundle.putString(str, (String) obj);
                break;
        }
    }
}
