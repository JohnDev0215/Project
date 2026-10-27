package com.amazon.mp3.library.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class RetainedPrimeFragment extends Fragment {
    private Map<String, Object> mMap = new HashMap();

    public static final RetainedPrimeFragment getRetainedFragment(final FragmentManager manager, final String tag) {
        RetainedPrimeFragment retainedPrimeFragment = (RetainedPrimeFragment) manager.findFragmentByTag(tag);
        if (retainedPrimeFragment != null) {
            return retainedPrimeFragment;
        }
        RetainedPrimeFragment retainedPrimeFragment2 = new RetainedPrimeFragment();
        manager.beginTransaction().add(retainedPrimeFragment2, tag).commitAllowingStateLoss();
        return retainedPrimeFragment2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Deprecated
    public Map<String, Object> getMap() {
        return this.mMap;
    }

    public <T> T read(String str) {
        return (T) this.mMap.get(str);
    }

    public <T> T read(String str, T t) {
        T t2 = (T) read(str);
        return t2 == null ? t : t2;
    }

    public Object save(final String key, final Object value) {
        return this.mMap.put(key, value);
    }
}
