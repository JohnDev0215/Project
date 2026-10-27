package p;

import android.os.Bundle;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class v62 {
    public boolean b;
    public Bundle c;
    public boolean d;
    public r7 e;
    public final q62 a = new q62();
    public boolean f = true;

    public final Bundle a(String str) {
        if (!this.d) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component".toString());
        }
        Bundle bundle = this.c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        Bundle bundle3 = this.c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.c;
        if (bundle4 == null || bundle4.isEmpty()) {
            this.c = null;
        }
        return bundle2;
    }

    public final void b(String str, u62 u62Var) {
        Object obj;
        v03.j(u62Var, "provider");
        q62 q62Var = this.a;
        m62 m62VarA = q62Var.a(str);
        if (m62VarA != null) {
            obj = m62VarA.e;
        } else {
            m62 m62Var = new m62(str, u62Var);
            q62Var.l++;
            m62 m62Var2 = q62Var.e;
            if (m62Var2 == null) {
                q62Var.c = m62Var;
                q62Var.e = m62Var;
            } else {
                m62Var2.k = m62Var;
                m62Var.l = m62Var2;
                q62Var.e = m62Var;
            }
            obj = null;
        }
        if (((u62) obj) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered".toString());
        }
    }

    public final void c() {
        if (!this.f) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState".toString());
        }
        r7 r7Var = this.e;
        if (r7Var == null) {
            r7Var = new r7(this);
        }
        this.e = r7Var;
        try {
            c91.class.getDeclaredConstructor(new Class[0]);
            r7 r7Var2 = this.e;
            if (r7Var2 != null) {
                ((Set) r7Var2.b).add(c91.class.getName());
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + c91.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }
}
