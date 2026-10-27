package c.a.a.g;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import b.l.d.p;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FragmentUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: compiled from: FragmentUtils.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f3419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f3420c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final String f3421d;

        public a(int i2, String str, boolean z, boolean z2) {
            this.f3418a = i2;
            this.f3421d = str;
            this.f3419b = z;
            this.f3420c = z2;
        }
    }

    public static void a(FragmentManager fragmentManager, Fragment fragment, int i2, String str) {
        b(fragmentManager, fragment, i2, str, false, false);
    }

    public static void b(FragmentManager fragmentManager, Fragment fragment, int i2, String str, boolean z, boolean z2) {
        h(fragment, new a(i2, str, z, z2));
        g(fragmentManager, 1, null, fragment);
    }

    public static Fragment c(FragmentManager fragmentManager, String str) {
        return fragmentManager.j0(str);
    }

    public static List<Fragment> d(FragmentManager fragmentManager) {
        List<Fragment> listT0 = fragmentManager.t0();
        return (listT0 == null || listT0.isEmpty()) ? Collections.emptyList() : listT0;
    }

    public static void e(Fragment fragment) {
        i(fragment, true);
        g(fragment.getFragmentManager(), 4, null, fragment);
    }

    public static void f(int i2, FragmentManager fragmentManager, p pVar, Fragment fragment, Fragment... fragmentArr) {
        if (fragment != null && fragment.isRemoving()) {
            Log.e("FragmentUtils", fragment.getClass().getName() + " is isRemoving");
            return;
        }
        int i3 = 0;
        if (i2 == 1) {
            int length = fragmentArr.length;
            while (i3 < length) {
                Fragment fragment2 = fragmentArr[i3];
                Bundle arguments = fragment2.getArguments();
                if (arguments == null) {
                    return;
                }
                String string = arguments.getString("args_tag", fragment2.getClass().getName());
                Fragment fragmentJ0 = fragmentManager.j0(string);
                if (fragmentJ0 != null && fragmentJ0.isAdded()) {
                    pVar.p(fragmentJ0);
                }
                pVar.b(arguments.getInt("args_id"), fragment2, string);
                if (arguments.getBoolean("args_is_hide")) {
                    pVar.o(fragment2);
                }
                if (arguments.getBoolean("args_is_add_stack")) {
                    pVar.f(string);
                }
                i3++;
            }
        } else if (i2 == 2) {
            int length2 = fragmentArr.length;
            while (i3 < length2) {
                pVar.t(fragmentArr[i3]);
                i3++;
            }
        } else if (i2 == 4) {
            int length3 = fragmentArr.length;
            while (i3 < length3) {
                pVar.o(fragmentArr[i3]);
                i3++;
            }
        } else if (i2 == 8) {
            pVar.t(fragment);
            int length4 = fragmentArr.length;
            while (i3 < length4) {
                Fragment fragment3 = fragmentArr[i3];
                if (fragment3 != fragment) {
                    pVar.o(fragment3);
                }
                i3++;
            }
        } else if (i2 == 16) {
            Bundle arguments2 = fragmentArr[0].getArguments();
            if (arguments2 == null) {
                return;
            }
            String string2 = arguments2.getString("args_tag", fragmentArr[0].getClass().getName());
            pVar.r(arguments2.getInt("args_id"), fragmentArr[0], string2);
            if (arguments2.getBoolean("args_is_add_stack")) {
                pVar.f(string2);
            }
        } else if (i2 == 32) {
            int length5 = fragmentArr.length;
            while (i3 < length5) {
                Fragment fragment4 = fragmentArr[i3];
                if (fragment4 != fragment) {
                    pVar.p(fragment4);
                }
                i3++;
            }
        } else if (i2 == 64) {
            int length6 = fragmentArr.length - 1;
            while (true) {
                if (length6 < 0) {
                    break;
                }
                Fragment fragment5 = fragmentArr[length6];
                if (fragment5 != fragmentArr[0]) {
                    pVar.p(fragment5);
                    length6--;
                } else if (fragment != null) {
                    pVar.p(fragment5);
                }
            }
        }
        pVar.i();
    }

    public static void g(FragmentManager fragmentManager, int i2, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager == null) {
            return;
        }
        f(i2, fragmentManager, fragmentManager.m(), fragment, fragmentArr);
    }

    public static void h(Fragment fragment, a aVar) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putInt("args_id", aVar.f3418a);
        arguments.putBoolean("args_is_hide", aVar.f3419b);
        arguments.putBoolean("args_is_add_stack", aVar.f3420c);
        arguments.putString("args_tag", aVar.f3421d);
    }

    public static void i(Fragment fragment, boolean z) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putBoolean("args_is_hide", z);
    }

    public static void j(Fragment fragment, List<Fragment> list) {
        Iterator<Fragment> it = list.iterator();
        while (it.hasNext()) {
            Fragment next = it.next();
            i(next, next != fragment);
        }
        g(fragment.getFragmentManager(), 8, fragment, (Fragment[]) list.toArray(new Fragment[list.size()]));
    }
}
