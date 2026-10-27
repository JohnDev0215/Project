package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f713c;
    int d;
    int e;
    int f;
    boolean g;

    @Nullable
    String h;
    int i;
    CharSequence j;
    int k;
    CharSequence l;
    ArrayList<String> m;
    ArrayList<String> n;
    ArrayList<Runnable> p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<a> f711a = new ArrayList<>();
    boolean o = false;

    /* JADX INFO: compiled from: caradapter-bug */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f714a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Fragment f715b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f716c;
        int d;
        int e;
        int f;
        Lifecycle.State g;
        Lifecycle.State h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.f714a = i;
            this.f715b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }
    }

    r(@NonNull h hVar, @Nullable ClassLoader classLoader) {
    }

    @NonNull
    public r b(@IdRes int i, @NonNull Fragment fragment, @Nullable String str) {
        j(i, fragment, str, 1);
        return this;
    }

    r c(@NonNull ViewGroup viewGroup, @NonNull Fragment fragment, @Nullable String str) {
        fragment.I = viewGroup;
        b(viewGroup.getId(), fragment, str);
        return this;
    }

    @NonNull
    public r d(@NonNull Fragment fragment, @Nullable String str) {
        j(0, fragment, str, 1);
        return this;
    }

    void e(a aVar) {
        this.f711a.add(aVar);
        aVar.f716c = this.f712b;
        aVar.d = this.f713c;
        aVar.e = this.d;
        aVar.f = this.e;
    }

    public abstract int f();

    public abstract int g();

    public abstract void h();

    @NonNull
    public r i() {
        if (this.g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        return this;
    }

    void j(int i, Fragment fragment, @Nullable String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.A;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.A + " now " + str);
            }
            fragment.A = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i3 = fragment.y;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.y + " now " + i);
            }
            fragment.y = i;
            fragment.z = i;
        }
        e(new a(i2, fragment));
    }

    @NonNull
    public r k(@NonNull Fragment fragment) {
        e(new a(3, fragment));
        return this;
    }

    @NonNull
    public r l(boolean z) {
        this.o = z;
        return this;
    }
}
