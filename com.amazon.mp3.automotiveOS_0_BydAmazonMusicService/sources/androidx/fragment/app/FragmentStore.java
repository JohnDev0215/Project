package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class FragmentStore {
    private static final String TAG = "FragmentManager";
    private final ArrayList<Fragment> mAdded = new ArrayList<>();
    private final HashMap<String, FragmentStateManager> mActive = new HashMap<>();

    FragmentStore() {
    }

    void resetActiveFragments() {
        this.mActive.clear();
    }

    void restoreAddedFragments(@Nullable List<String> list) {
        this.mAdded.clear();
        if (list != null) {
            for (String str : list) {
                Fragment fragmentFindActiveFragment = findActiveFragment(str);
                if (fragmentFindActiveFragment == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(TAG, "restoreSaveState: added (" + str + "): " + fragmentFindActiveFragment);
                }
                addFragment(fragmentFindActiveFragment);
            }
        }
    }

    void makeActive(@NonNull FragmentStateManager fragmentStateManager) {
        this.mActive.put(fragmentStateManager.getFragment().mWho, fragmentStateManager);
    }

    void addFragment(@NonNull Fragment fragment) {
        if (this.mAdded.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.mAdded) {
            this.mAdded.add(fragment);
        }
        fragment.mAdded = true;
    }

    void dispatchStateChange(int i) {
        Iterator<Fragment> it = this.mAdded.iterator();
        while (it.hasNext()) {
            FragmentStateManager fragmentStateManager = this.mActive.get(it.next().mWho);
            if (fragmentStateManager != null) {
                fragmentStateManager.setFragmentManagerState(i);
            }
        }
        for (FragmentStateManager fragmentStateManager2 : this.mActive.values()) {
            if (fragmentStateManager2 != null) {
                fragmentStateManager2.setFragmentManagerState(i);
            }
        }
    }

    void removeFragment(@NonNull Fragment fragment) {
        synchronized (this.mAdded) {
            this.mAdded.remove(fragment);
        }
        fragment.mAdded = false;
    }

    void makeInactive(@NonNull FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.getFragment();
        for (FragmentStateManager fragmentStateManager2 : this.mActive.values()) {
            if (fragmentStateManager2 != null) {
                Fragment fragment2 = fragmentStateManager2.getFragment();
                if (fragment.mWho.equals(fragment2.mTargetWho)) {
                    fragment2.mTarget = fragment;
                    fragment2.mTargetWho = null;
                }
            }
        }
        this.mActive.put(fragment.mWho, null);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = findActiveFragment(str);
        }
    }

    void burpActive() {
        this.mActive.values().removeAll(Collections.singleton(null));
    }

    @NonNull
    ArrayList<FragmentState> saveActiveFragments() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.mActive.size());
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment = fragmentStateManager.getFragment();
                FragmentState fragmentStateSaveState = fragmentStateManager.saveState();
                arrayList.add(fragmentStateSaveState);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(TAG, "Saved state of " + fragment + ": " + fragmentStateSaveState.mSavedFragmentState);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    ArrayList<String> saveAddedFragments() {
        synchronized (this.mAdded) {
            if (this.mAdded.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.mAdded.size());
            for (Fragment fragment : this.mAdded) {
                arrayList.add(fragment.mWho);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(TAG, "saveAllState: adding fragment (" + fragment.mWho + "): " + fragment);
                }
            }
            return arrayList;
        }
    }

    @NonNull
    List<Fragment> getFragments() {
        ArrayList arrayList;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            arrayList = new ArrayList(this.mAdded);
        }
        return arrayList;
    }

    @NonNull
    List<Fragment> getActiveFragments() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager.getFragment());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    int getActiveFragmentCount() {
        return this.mActive.size();
    }

    @Nullable
    Fragment findFragmentById(@IdRes int i) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment2 = fragmentStateManager.getFragment();
                if (fragment2.mFragmentId == i) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    @Nullable
    Fragment findFragmentByTag(@Nullable String str) {
        if (str != null) {
            for (int size = this.mAdded.size() - 1; size >= 0; size--) {
                Fragment fragment = this.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment2 = fragmentStateManager.getFragment();
                if (str.equals(fragment2.mTag)) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    boolean containsActiveFragment(@NonNull String str) {
        return this.mActive.containsKey(str);
    }

    @Nullable
    FragmentStateManager getFragmentStateManager(@NonNull String str) {
        return this.mActive.get(str);
    }

    @Nullable
    Fragment findFragmentByWho(@NonNull String str) {
        Fragment fragmentFindFragmentByWho;
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null && (fragmentFindFragmentByWho = fragmentStateManager.getFragment().findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    @Nullable
    Fragment findActiveFragment(@NonNull String str) {
        FragmentStateManager fragmentStateManager = this.mActive.get(str);
        if (fragmentStateManager != null) {
            return fragmentStateManager.getFragment();
        }
        return null;
    }

    Fragment findFragmentUnder(@NonNull Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.mAdded.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.mAdded.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        String str2 = str + "    ";
        if (!this.mActive.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
                printWriter.print(str);
                if (fragmentStateManager != null) {
                    Fragment fragment = fragmentStateManager.getFragment();
                    printWriter.println(fragment);
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.mAdded.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size; i++) {
                Fragment fragment2 = this.mAdded.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
    }
}
