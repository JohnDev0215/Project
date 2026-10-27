package b.b.a.h;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FragmentUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: compiled from: FragmentUtils.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f527a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f528b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f529c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final String f530d;

        public a(int i, String str, boolean z, boolean z2) {
            this.f527a = i;
            this.f530d = str;
            this.f528b = z;
            this.f529c = z2;
        }
    }

    public static void a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        b(fragmentManager, fragment, i, str, false, false);
    }

    public static void b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z, boolean z2) {
        h(fragment, new a(i, str, z, z2));
        g(fragmentManager, 1, null, fragment);
    }

    public static Fragment c(@NonNull FragmentManager fragmentManager, @NonNull String str) {
        return fragmentManager.findFragmentByTag(str);
    }

    public static List<Fragment> d(@NonNull FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        return (fragments == null || fragments.isEmpty()) ? Collections.emptyList() : fragments;
    }

    public static void e(@NonNull Fragment fragment) {
        i(fragment, true);
        g(fragment.getFragmentManager(), 4, null, fragment);
    }

    public static void f(int i, @NonNull FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, Fragment fragment, Fragment... fragmentArr) {
        if (fragment != null && fragment.isRemoving()) {
            Log.e("FragmentUtils", fragment.getClass().getName() + " is isRemoving");
            return;
        }
        int i2 = 0;
        if (i == 1) {
            int length = fragmentArr.length;
            while (i2 < length) {
                Fragment fragment2 = fragmentArr[i2];
                Bundle arguments = fragment2.getArguments();
                if (arguments == null) {
                    return;
                }
                String string = arguments.getString("args_tag", fragment2.getClass().getName());
                Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(string);
                if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isAdded()) {
                    fragmentTransaction.remove(fragmentFindFragmentByTag);
                }
                fragmentTransaction.add(arguments.getInt("args_id"), fragment2, string);
                if (arguments.getBoolean("args_is_hide")) {
                    fragmentTransaction.hide(fragment2);
                }
                if (arguments.getBoolean("args_is_add_stack")) {
                    fragmentTransaction.addToBackStack(string);
                }
                i2++;
            }
        } else if (i == 2) {
            int length2 = fragmentArr.length;
            while (i2 < length2) {
                fragmentTransaction.show(fragmentArr[i2]);
                i2++;
            }
        } else if (i == 4) {
            int length3 = fragmentArr.length;
            while (i2 < length3) {
                fragmentTransaction.hide(fragmentArr[i2]);
                i2++;
            }
        } else if (i == 8) {
            fragmentTransaction.show(fragment);
            int length4 = fragmentArr.length;
            while (i2 < length4) {
                Fragment fragment3 = fragmentArr[i2];
                if (fragment3 != fragment) {
                    fragmentTransaction.hide(fragment3);
                }
                i2++;
            }
        } else if (i == 16) {
            Bundle arguments2 = fragmentArr[0].getArguments();
            if (arguments2 == null) {
                return;
            }
            String string2 = arguments2.getString("args_tag", fragmentArr[0].getClass().getName());
            fragmentTransaction.replace(arguments2.getInt("args_id"), fragmentArr[0], string2);
            if (arguments2.getBoolean("args_is_add_stack")) {
                fragmentTransaction.addToBackStack(string2);
            }
        } else if (i == 32) {
            int length5 = fragmentArr.length;
            while (i2 < length5) {
                Fragment fragment4 = fragmentArr[i2];
                if (fragment4 != fragment) {
                    fragmentTransaction.remove(fragment4);
                }
                i2++;
            }
        } else if (i == 64) {
            int length6 = fragmentArr.length - 1;
            while (true) {
                if (length6 < 0) {
                    break;
                }
                Fragment fragment5 = fragmentArr[length6];
                if (fragment5 != fragmentArr[0]) {
                    fragmentTransaction.remove(fragment5);
                    length6--;
                } else if (fragment != null) {
                    fragmentTransaction.remove(fragment5);
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void g(@Nullable FragmentManager fragmentManager, int i, Fragment fragment, Fragment... fragmentArr) {
        if (fragmentManager == null) {
            return;
        }
        f(i, fragmentManager, fragmentManager.beginTransaction(), fragment, fragmentArr);
    }

    public static void h(Fragment fragment, a aVar) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putInt("args_id", aVar.f527a);
        arguments.putBoolean("args_is_hide", aVar.f528b);
        arguments.putBoolean("args_is_add_stack", aVar.f529c);
        arguments.putString("args_tag", aVar.f530d);
    }

    public static void i(Fragment fragment, boolean z) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }
        arguments.putBoolean("args_is_hide", z);
    }

    public static void j(@NonNull Fragment fragment) {
        g(fragment.getFragmentManager(), 32, null, fragment);
    }

    public static void k(@NonNull FragmentManager fragmentManager) {
        List<Fragment> listD = d(fragmentManager);
        g(fragmentManager, 32, null, (Fragment[]) listD.toArray(new Fragment[listD.size()]));
    }

    public static void l(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str) {
        m(fragmentManager, fragment, i, str, false);
    }

    public static void m(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @IdRes int i, String str, boolean z) {
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        h(fragment, new a(i, str, false, z));
        f(16, fragmentManager, fragmentTransactionBeginTransaction, null, fragment);
    }

    public static void n(@NonNull Fragment fragment, @NonNull List<Fragment> list) {
        Iterator<Fragment> it = list.iterator();
        while (it.hasNext()) {
            Fragment next = it.next();
            i(next, next != fragment);
        }
        g(fragment.getFragmentManager(), 8, fragment, (Fragment[]) list.toArray(new Fragment[list.size()]));
    }
}
