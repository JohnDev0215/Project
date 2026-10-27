package defpackage;

import android.util.Log;

/* JADX INFO: renamed from: zh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1887zh extends AbstractC1667vh {
    public C1887zh(C0007Ah c0007Ah, String str, Boolean bool) {
        super(c0007Ah, str, bool, null);
    }

    @Override // defpackage.AbstractC1667vh
    public final /* synthetic */ Object f(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (AbstractC0839gh.c.matcher(str).matches()) {
                return Boolean.TRUE;
            }
            if (AbstractC0839gh.d.matcher(str).matches()) {
                return Boolean.FALSE;
            }
        }
        String strB = super.b();
        String strValueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(strValueOf.length() + String.valueOf(strB).length() + 28);
        sb.append("Invalid boolean value for ");
        sb.append(strB);
        sb.append(": ");
        sb.append(strValueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
