package defpackage;

import J.N;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.browser.privacy_guide.CookiesFragment;
import org.chromium.chrome.browser.privacy_guide.DoneFragment;
import org.chromium.chrome.browser.privacy_guide.HistorySyncFragment;
import org.chromium.chrome.browser.privacy_guide.MSBBFragment;
import org.chromium.chrome.browser.privacy_guide.SafeBrowsingFragment;
import org.chromium.chrome.browser.privacy_guide.WelcomeFragment;
import org.chromium.chrome.browser.privacy_guide.a;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: PrivacyGuidePagerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s42 extends FragmentStateAdapter {
    public final ArrayList q;

    public s42(Fragment fragment, n3 n3Var) {
        super(fragment.J(), fragment.S);
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(0, 1, 5));
        if (N.MdyQjr8h() != 0) {
            hashSet.add(3);
        }
        if (N.MJSt3Ocq(Profile.d(), 0) && a.a() != 0) {
            hashSet.add(4);
        }
        Set setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 5; i++) {
            if (setUnmodifiableSet.contains(Integer.valueOf(i))) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        this.q = arrayList;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public final Fragment F(int i) {
        int iK = K(i);
        if (iK == 0) {
            return new WelcomeFragment();
        }
        if (iK == 1) {
            return new MSBBFragment();
        }
        if (iK == 2) {
            return new HistorySyncFragment();
        }
        if (iK == 3) {
            return new SafeBrowsingFragment();
        }
        if (iK == 4) {
            return new CookiesFragment();
        }
        if (iK != 5) {
            return null;
        }
        return new DoneFragment();
    }

    public final int K(int i) {
        return ((Integer) this.q.get(i)).intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.q.size();
    }
}
