package r;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.lifecycle.C0978p;
import java.util.Iterator;
import java.util.Map;
import t.C2418b;
import t.C2419c;
import t.C2422f;
import t3.C2434a;

/* JADX INFO: renamed from: r.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2257p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f21542a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f21543b;
    public boolean c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Parcelable f21544e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f21545f;

    public C2257p() {
        this.d = new C2422f();
        this.c = true;
    }

    public void a() {
        AbstractC2256o abstractC2256o = (AbstractC2256o) this.d;
        Drawable buttonDrawable = abstractC2256o.getButtonDrawable();
        if (buttonDrawable != null) {
            if (this.f21542a || this.f21543b) {
                Drawable drawableMutate = buttonDrawable.mutate();
                if (this.f21542a) {
                    drawableMutate.setTintList((ColorStateList) this.f21544e);
                }
                if (this.f21543b) {
                    drawableMutate.setTintMode((PorterDuff.Mode) this.f21545f);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(abstractC2256o.getDrawableState());
                }
                abstractC2256o.setButtonDrawable(drawableMutate);
            }
        }
    }

    public Bundle b(String str) {
        A7.m.f(str, "key");
        if (!this.f21543b) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = (Bundle) this.f21544e;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        Bundle bundle3 = (Bundle) this.f21544e;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = (Bundle) this.f21544e;
        if (bundle4 == null || bundle4.isEmpty()) {
            this.f21544e = null;
        }
        return bundle2;
    }

    public t3.d c() {
        String str;
        t3.d dVar;
        Iterator it = ((C2422f) this.d).iterator();
        do {
            C2418b c2418b = (C2418b) it;
            if (!c2418b.hasNext()) {
                return null;
            }
            Map.Entry entry = (Map.Entry) c2418b.next();
            A7.m.e(entry, "components");
            str = (String) entry.getKey();
            dVar = (t3.d) entry.getValue();
        } while (!A7.m.a(str, "androidx.lifecycle.internal.SavedStateHandlesProvider"));
        return dVar;
    }

    public void d(String str, t3.d dVar) {
        Object obj;
        A7.m.f(dVar, "provider");
        C2422f c2422f = (C2422f) this.d;
        C2419c c2419cA = c2422f.a(str);
        if (c2419cA != null) {
            obj = c2419cA.f22419m;
        } else {
            C2419c c2419c = new C2419c(str, dVar);
            c2422f.o++;
            C2419c c2419c2 = c2422f.f22423m;
            if (c2419c2 == null) {
                c2422f.l = c2419c;
                c2422f.f22423m = c2419c;
            } else {
                c2419c2.f22420n = c2419c;
                c2419c.o = c2419c2;
                c2422f.f22423m = c2419c;
            }
            obj = null;
        }
        if (((t3.d) obj) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void e() {
        if (!this.c) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        C2434a c2434a = (C2434a) this.f21545f;
        if (c2434a == null) {
            c2434a = new C2434a(this);
        }
        this.f21545f = c2434a;
        try {
            C0978p.class.getDeclaredConstructor(null);
            C2434a c2434a2 = (C2434a) this.f21545f;
            if (c2434a2 != null) {
                c2434a2.f22455a.add(C0978p.class.getName());
            }
        } catch (NoSuchMethodException e10) {
            throw new IllegalArgumentException("Class " + C0978p.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e10);
        }
    }

    public C2257p(AbstractC2256o abstractC2256o) {
        this.f21544e = null;
        this.f21545f = null;
        this.f21542a = false;
        this.f21543b = false;
        this.d = abstractC2256o;
    }
}
