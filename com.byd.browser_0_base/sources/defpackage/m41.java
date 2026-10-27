package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: KeyboardAccessoryData.java */
/* JADX INFO: loaded from: classes.dex */
public final class m41 {
    public final String a;
    public final Callback<m41> b;
    public final int c;

    public m41() {
        throw null;
    }

    public m41(String str, ko0 ko0Var) {
        this.a = str;
        this.b = ko0Var;
        this.c = 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AccessoryAction(");
        int i = this.c;
        sb.append(i);
        sb.append(")");
        String string = sb.toString();
        if (i == 0) {
            string = "GENERATE_PASSWORD_AUTOMATIC";
        } else if (i == 1) {
            string = "MANAGE_PASSWORDS";
        } else if (i == 2) {
            string = "AUTOFILL_SUGGESTION";
        }
        return "'" + this.a + "' of type " + string;
    }
}
