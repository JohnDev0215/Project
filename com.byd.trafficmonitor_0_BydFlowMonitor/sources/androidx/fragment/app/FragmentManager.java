package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.IntentSenderRequest;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import b.l.d.c;
import b.l.d.p;
import b.l.d.q;
import b.l.d.u;
import b.l.d.v;
import b.n.s;
import b.n.t;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f921a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f922b = true;
    public b.a.e.b<Intent> B;
    public b.a.e.b<IntentSenderRequest> C;
    public b.a.e.b<String[]> D;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public ArrayList<b.l.d.a> K;
    public ArrayList<Boolean> L;
    public ArrayList<Fragment> M;
    public ArrayList<p> N;
    public b.l.d.k O;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f924d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<b.l.d.a> f926f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<Fragment> f927g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public OnBackPressedDispatcher f929i;
    public ArrayList<m> n;
    public b.l.d.g<?> t;
    public b.l.d.d u;
    public Fragment v;
    public Fragment w;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<n> f923c = new ArrayList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b.l.d.o f925e = new b.l.d.o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b.l.d.h f928h = new b.l.d.h(this);
    public final b.a.b j = new c(false);
    public final AtomicInteger k = new AtomicInteger();
    public final Map<String, Bundle> l = Collections.synchronizedMap(new HashMap());
    public final Map<String, ?> m = Collections.synchronizedMap(new HashMap());
    public Map<Fragment, HashSet<b.h.i.a>> o = Collections.synchronizedMap(new HashMap());
    public final q.g p = new d();
    public final b.l.d.i q = new b.l.d.i(this);
    public final CopyOnWriteArrayList<b.l.d.l> r = new CopyOnWriteArrayList<>();
    public int s = -1;
    public b.l.d.f x = null;
    public b.l.d.f y = new e();
    public v z = null;
    public v A = new f();
    public ArrayDeque<LaunchedFragmentInfo> E = new ArrayDeque<>();
    public Runnable P = new g();

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentManager$6, reason: invalid class name */
    public class AnonymousClass6 implements b.n.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f930a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.l.d.m f931b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Lifecycle f932c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ FragmentManager f933d;

        @Override // b.n.e
        public void d(b.n.g gVar, Lifecycle.Event event) {
            Bundle bundle;
            if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) this.f933d.l.get(this.f930a)) != null) {
                this.f931b.a(this.f930a, bundle);
                this.f933d.r(this.f930a);
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                this.f932c.c(this);
                this.f933d.m.remove(this.f930a);
            }
        }
    }

    public class a implements b.a.e.a<ActivityResult> {
        public a() {
        }

        @Override // b.a.e.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ActivityResult activityResult) {
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.E.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No IntentSenders were started for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f934b;
            int i2 = launchedFragmentInfoPollFirst.f935c;
            Fragment fragmentI = FragmentManager.this.f925e.i(str);
            if (fragmentI != null) {
                fragmentI.onActivityResult(i2, activityResult.k(), activityResult.j());
                return;
            }
            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
        }
    }

    public class b implements b.a.e.a<Map<String, Boolean>> {
        public b() {
        }

        @Override // b.a.e.a
        @SuppressLint({"SyntheticAccessor"})
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Map<String, Boolean> map) {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList(map.values());
            int[] iArr = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
            }
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.E.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No permissions were requested for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f934b;
            int i3 = launchedFragmentInfoPollFirst.f935c;
            Fragment fragmentI = FragmentManager.this.f925e.i(str);
            if (fragmentI != null) {
                fragmentI.onRequestPermissionsResult(i3, strArr, iArr);
                return;
            }
            Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
        }
    }

    public class c extends b.a.b {
        public c(boolean z) {
            super(z);
        }

        @Override // b.a.b
        public void b() {
            FragmentManager.this.C0();
        }
    }

    public class d implements q.g {
        public d() {
        }

        @Override // b.l.d.q.g
        public void a(Fragment fragment, b.h.i.a aVar) {
            if (aVar.b()) {
                return;
            }
            FragmentManager.this.c1(fragment, aVar);
        }

        @Override // b.l.d.q.g
        public void b(Fragment fragment, b.h.i.a aVar) {
            FragmentManager.this.f(fragment, aVar);
        }
    }

    public class e extends b.l.d.f {
        public e() {
        }

        @Override // b.l.d.f
        public Fragment a(ClassLoader classLoader, String str) {
            return FragmentManager.this.u0().b(FragmentManager.this.u0().f(), str, null);
        }
    }

    public class f implements v {
        public f() {
        }

        @Override // b.l.d.v
        public SpecialEffectsController a(ViewGroup viewGroup) {
            return new b.l.d.b(viewGroup);
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.b0(true);
        }
    }

    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f943a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f944b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f945c;

        public h(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f943a = viewGroup;
            this.f944b = view;
            this.f945c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f943a.endViewTransition(this.f944b);
            animator.removeListener(this);
            Fragment fragment = this.f945c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    public class i implements b.l.d.l {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f947b;

        public i(Fragment fragment) {
            this.f947b = fragment;
        }

        @Override // b.l.d.l
        public void a(FragmentManager fragmentManager, Fragment fragment) {
            this.f947b.onAttachFragment(fragment);
        }
    }

    public class j implements b.a.e.a<ActivityResult> {
        public j() {
        }

        @Override // b.a.e.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ActivityResult activityResult) {
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.E.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No Activities were started for result for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f934b;
            int i2 = launchedFragmentInfoPollFirst.f935c;
            Fragment fragmentI = FragmentManager.this.f925e.i(str);
            if (fragmentI != null) {
                fragmentI.onActivityResult(i2, activityResult.k(), activityResult.j());
                return;
            }
            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
        }
    }

    public static class k extends b.a.e.d.a<IntentSenderRequest, ActivityResult> {
        @Override // b.a.e.d.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intentJ = intentSenderRequest.j();
            if (intentJ != null && (bundleExtra = intentJ.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                intentJ.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (intentJ.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest = new IntentSenderRequest.b(intentSenderRequest.m()).b(null).c(intentSenderRequest.l(), intentSenderRequest.k()).a();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        @Override // b.a.e.d.a
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResult c(int i2, Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    public static abstract class l {
    }

    public interface m {
        void a();
    }

    public interface n {
        boolean a(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    public class o implements n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f950a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f951b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f952c;

        public o(String str, int i2, int i3) {
            this.f950a = str;
            this.f951b = i2;
            this.f952c = i3;
        }

        @Override // androidx.fragment.app.FragmentManager.n
        public boolean a(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.w;
            if (fragment == null || this.f951b >= 0 || this.f950a != null || !fragment.getChildFragmentManager().Y0()) {
                return FragmentManager.this.a1(arrayList, arrayList2, this.f950a, this.f951b, this.f952c);
            }
            return false;
        }
    }

    public static class p implements Fragment.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f954a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.l.d.a f955b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f956c;

        public p(b.l.d.a aVar, boolean z) {
            this.f954a = z;
            this.f955b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.k
        public void a() {
            int i2 = this.f956c - 1;
            this.f956c = i2;
            if (i2 != 0) {
                return;
            }
            this.f955b.t.k1();
        }

        @Override // androidx.fragment.app.Fragment.k
        public void b() {
            this.f956c++;
        }

        public void c() {
            b.l.d.a aVar = this.f955b;
            aVar.t.u(aVar, this.f954a, false, false);
        }

        public void d() {
            boolean z = this.f956c > 0;
            for (Fragment fragment : this.f955b.t.t0()) {
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            b.l.d.a aVar = this.f955b;
            aVar.t.u(aVar, this.f954a, !z, true);
        }

        public boolean e() {
            return this.f956c == 0;
        }
    }

    public static Fragment A0(View view) {
        Object tag = view.getTag(b.l.b.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    public static boolean G0(int i2) {
        return f921a || Log.isLoggable("FragmentManager", i2);
    }

    public static void d0(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            b.l.d.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.u(-1);
                aVar.z(i2 == i3 + (-1));
            } else {
                aVar.u(1);
                aVar.y();
            }
            i2++;
        }
    }

    public static int i1(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    public void A() {
        this.G = false;
        this.H = false;
        this.O.o(false);
        T(0);
    }

    public void B(Configuration configuration) {
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public s B0(Fragment fragment) {
        return this.O.l(fragment);
    }

    public boolean C(MenuItem menuItem) {
        if (this.s < 1) {
            return false;
        }
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void C0() {
        b0(true);
        if (this.j.c()) {
            Y0();
        } else {
            this.f929i.c();
        }
    }

    public void D() {
        this.G = false;
        this.H = false;
        this.O.o(false);
        T(1);
    }

    public void D0(Fragment fragment) {
        if (G0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        o1(fragment);
    }

    public boolean E(Menu menu, MenuInflater menuInflater) {
        if (this.s < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null && I0(fragment) && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.f927g != null) {
            for (int i2 = 0; i2 < this.f927g.size(); i2++) {
                Fragment fragment2 = this.f927g.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.f927g = arrayList;
        return z;
    }

    public void E0(Fragment fragment) {
        if (fragment.mAdded && H0(fragment)) {
            this.F = true;
        }
    }

    public void F() {
        this.I = true;
        b0(true);
        Y();
        T(-1);
        this.t = null;
        this.u = null;
        this.v = null;
        if (this.f929i != null) {
            this.j.d();
            this.f929i = null;
        }
        b.a.e.b<Intent> bVar = this.B;
        if (bVar != null) {
            bVar.c();
            this.C.c();
            this.D.c();
        }
    }

    public boolean F0() {
        return this.I;
    }

    public void G() {
        T(1);
    }

    public void H() {
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public final boolean H0(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.o();
    }

    public void I(boolean z) {
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public boolean I0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    public void J(Fragment fragment) {
        Iterator<b.l.d.l> it = this.r.iterator();
        while (it.hasNext()) {
            it.next().a(this, fragment);
        }
    }

    public boolean J0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        return fragment.equals(fragmentManager.y0()) && J0(fragmentManager.v);
    }

    public boolean K(MenuItem menuItem) {
        if (this.s < 1) {
            return false;
        }
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean K0(int i2) {
        return this.s >= i2;
    }

    public void L(Menu menu) {
        if (this.s < 1) {
            return;
        }
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public boolean L0() {
        return this.G || this.H;
    }

    public final void M(Fragment fragment) {
        if (fragment == null || !fragment.equals(h0(fragment.mWho))) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    public void M0(Fragment fragment, String[] strArr, int i2) {
        if (this.D == null) {
            this.t.j(fragment, strArr, i2);
            return;
        }
        this.E.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        this.D.a(strArr);
    }

    public void N() {
        T(5);
    }

    public void N0(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, Bundle bundle) {
        if (this.B == null) {
            this.t.m(fragment, intent, i2, bundle);
            return;
        }
        this.E.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        if (intent != null && bundle != null) {
            intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        this.B.a(intent);
    }

    public void O(boolean z) {
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public void O0(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        Intent intent2;
        if (this.C == null) {
            this.t.n(fragment, intentSender, i2, intent, i3, i4, i5, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            } else {
                intent2 = intent;
            }
            if (G0(2)) {
                Log.v("FragmentManager", "ActivityOptions " + bundle + " were added to fillInIntent " + intent2 + " for fragment " + fragment);
            }
            intent2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        } else {
            intent2 = intent;
        }
        IntentSenderRequest intentSenderRequestA = new IntentSenderRequest.b(intentSender).b(intent2).c(i4, i3).a();
        this.E.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        if (G0(2)) {
            Log.v("FragmentManager", "Fragment " + fragment + "is launching an IntentSender for result ");
        }
        this.C.a(intentSenderRequestA);
    }

    public boolean P(Menu menu) {
        boolean z = false;
        if (this.s < 1) {
            return false;
        }
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null && I0(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public final void P0(b.e.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentI = bVar.i(i2);
            if (!fragmentI.mAdded) {
                View viewRequireView = fragmentI.requireView();
                fragmentI.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    public void Q() {
        r1();
        M(this.w);
    }

    public void Q0(Fragment fragment) {
        if (!this.f925e.c(fragment.mWho)) {
            if (G0(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.s + "since it is not added to " + this);
                return;
            }
            return;
        }
        S0(fragment);
        View view = fragment.mView;
        if (view != null && fragment.mIsNewlyAdded && fragment.mContainer != null) {
            float f2 = fragment.mPostponedAlpha;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.mPostponedAlpha = 0.0f;
            fragment.mIsNewlyAdded = false;
            c.d dVarC = b.l.d.c.c(this.t.f(), fragment, true, fragment.getPopDirection());
            if (dVarC != null) {
                Animation animation = dVarC.f2803a;
                if (animation != null) {
                    fragment.mView.startAnimation(animation);
                } else {
                    dVarC.f2804b.setTarget(fragment.mView);
                    dVarC.f2804b.start();
                }
            }
        }
        if (fragment.mHiddenChanged) {
            v(fragment);
        }
    }

    public void R() {
        this.G = false;
        this.H = false;
        this.O.o(false);
        T(7);
    }

    public void R0(int i2, boolean z) {
        b.l.d.g<?> gVar;
        if (this.t == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.s) {
            this.s = i2;
            if (f922b) {
                this.f925e.r();
            } else {
                Iterator<Fragment> it = this.f925e.n().iterator();
                while (it.hasNext()) {
                    Q0(it.next());
                }
                for (b.l.d.n nVar : this.f925e.k()) {
                    Fragment fragmentK = nVar.k();
                    if (!fragmentK.mIsNewlyAdded) {
                        Q0(fragmentK);
                    }
                    if (fragmentK.mRemoving && !fragmentK.isInBackStack()) {
                        this.f925e.q(nVar);
                    }
                }
            }
            q1();
            if (this.F && (gVar = this.t) != null && this.s == 7) {
                gVar.o();
                this.F = false;
            }
        }
    }

    public void S() {
        this.G = false;
        this.H = false;
        this.O.o(false);
        T(5);
    }

    public void S0(Fragment fragment) {
        T0(fragment, this.s);
    }

    public final void T(int i2) {
        try {
            this.f924d = true;
            this.f925e.d(i2);
            R0(i2, false);
            if (f922b) {
                Iterator<SpecialEffectsController> it = s().iterator();
                while (it.hasNext()) {
                    it.next().j();
                }
            }
            this.f924d = false;
            b0(true);
        } catch (Throwable th) {
            this.f924d = false;
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void T0(androidx.fragment.app.Fragment r11, int r12) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.T0(androidx.fragment.app.Fragment, int):void");
    }

    public void U() {
        this.H = true;
        this.O.o(true);
        T(4);
    }

    public void U0() {
        if (this.t == null) {
            return;
        }
        this.G = false;
        this.H = false;
        this.O.o(false);
        for (Fragment fragment : this.f925e.n()) {
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void V() {
        T(2);
    }

    public void V0(FragmentContainerView fragmentContainerView) {
        View view;
        for (b.l.d.n nVar : this.f925e.k()) {
            Fragment fragmentK = nVar.k();
            if (fragmentK.mContainerId == fragmentContainerView.getId() && (view = fragmentK.mView) != null && view.getParent() == null) {
                fragmentK.mContainer = fragmentContainerView;
                nVar.b();
            }
        }
    }

    public final void W() {
        if (this.J) {
            this.J = false;
            q1();
        }
    }

    public void W0(b.l.d.n nVar) {
        Fragment fragmentK = nVar.k();
        if (fragmentK.mDeferStart) {
            if (this.f924d) {
                this.J = true;
                return;
            }
            fragmentK.mDeferStart = false;
            if (f922b) {
                nVar.m();
            } else {
                S0(fragmentK);
            }
        }
    }

    public void X(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.f925e.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.f927g;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = this.f927g.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<b.l.d.a> arrayList2 = this.f926f;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                b.l.d.a aVar = this.f926f.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.w(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.k.get());
        synchronized (this.f923c) {
            int size3 = this.f923c.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    n nVar = this.f923c.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(nVar);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.t);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.u);
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.v);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.s);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.G);
        printWriter.print(" mStopped=");
        printWriter.print(this.H);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.I);
        if (this.F) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.F);
        }
    }

    public void X0(int i2, int i3) {
        if (i2 >= 0) {
            Z(new o(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public final void Y() {
        if (f922b) {
            Iterator<SpecialEffectsController> it = s().iterator();
            while (it.hasNext()) {
                it.next().j();
            }
        } else {
            if (this.o.isEmpty()) {
                return;
            }
            for (Fragment fragment : this.o.keySet()) {
                n(fragment);
                S0(fragment);
            }
        }
    }

    public boolean Y0() {
        return Z0(null, -1, 0);
    }

    public void Z(n nVar, boolean z) {
        if (!z) {
            if (this.t == null) {
                if (!this.I) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            p();
        }
        synchronized (this.f923c) {
            if (this.t == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.f923c.add(nVar);
                k1();
            }
        }
    }

    public final boolean Z0(String str, int i2, int i3) {
        b0(false);
        a0(true);
        Fragment fragment = this.w;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().Y0()) {
            return true;
        }
        boolean zA1 = a1(this.K, this.L, str, i2, i3);
        if (zA1) {
            this.f924d = true;
            try {
                e1(this.K, this.L);
            } finally {
                q();
            }
        }
        r1();
        W();
        this.f925e.b();
        return zA1;
    }

    public final void a0(boolean z) {
        if (this.f924d) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.t == null) {
            if (!this.I) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.t.g().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            p();
        }
        if (this.K == null) {
            this.K = new ArrayList<>();
            this.L = new ArrayList<>();
        }
        this.f924d = true;
        try {
            g0(null, null);
        } finally {
            this.f924d = false;
        }
    }

    public boolean a1(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<b.l.d.a> arrayList3 = this.f926f;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f926f.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    b.l.d.a aVar = this.f926f.get(size2);
                    if ((str != null && str.equals(aVar.B())) || (i2 >= 0 && i2 == aVar.v)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        b.l.d.a aVar2 = this.f926f.get(size2);
                        if (str == null || !str.equals(aVar2.B())) {
                            if (i2 < 0 || i2 != aVar2.v) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.f926f.size() - 1) {
                return false;
            }
            for (int size3 = this.f926f.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.f926f.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public boolean b0(boolean z) {
        a0(z);
        boolean z2 = false;
        while (m0(this.K, this.L)) {
            this.f924d = true;
            try {
                e1(this.K, this.L);
                q();
                z2 = true;
            } catch (Throwable th) {
                q();
                throw th;
            }
        }
        r1();
        W();
        this.f925e.b();
        return z2;
    }

    public final int b1(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, b.e.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            b.l.d.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.F() && !aVar.D(arrayList, i5 + 1, i3)) {
                if (this.N == null) {
                    this.N = new ArrayList<>();
                }
                p pVar = new p(aVar, zBooleanValue);
                this.N.add(pVar);
                aVar.H(pVar);
                if (zBooleanValue) {
                    aVar.y();
                } else {
                    aVar.z(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                d(bVar);
            }
        }
        return i4;
    }

    public void c0(n nVar, boolean z) {
        if (z && (this.t == null || this.I)) {
            return;
        }
        a0(z);
        if (nVar.a(this.K, this.L)) {
            this.f924d = true;
            try {
                e1(this.K, this.L);
            } finally {
                q();
            }
        }
        r1();
        W();
        this.f925e.b();
    }

    public void c1(Fragment fragment, b.h.i.a aVar) {
        HashSet<b.h.i.a> hashSet = this.o.get(fragment);
        if (hashSet != null && hashSet.remove(aVar) && hashSet.isEmpty()) {
            this.o.remove(fragment);
            if (fragment.mState < 5) {
                x(fragment);
                S0(fragment);
            }
        }
    }

    public final void d(b.e.b<Fragment> bVar) {
        int i2 = this.s;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 5);
        for (Fragment fragment : this.f925e.n()) {
            if (fragment.mState < iMin) {
                T0(fragment, iMin);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    public void d1(Fragment fragment) {
        if (G0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            this.f925e.s(fragment);
            if (H0(fragment)) {
                this.F = true;
            }
            fragment.mRemoving = true;
            o1(fragment);
        }
    }

    public void e(b.l.d.a aVar) {
        if (this.f926f == null) {
            this.f926f = new ArrayList<>();
        }
        this.f926f.add(aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r17v0, types: [androidx.fragment.app.FragmentManager] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [boolean] */
    public final void e0(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ?? r1;
        ?? r14;
        boolean z;
        int i4;
        int i5;
        ArrayList<Boolean> arrayList3;
        int iB1;
        ArrayList<Boolean> arrayList4;
        int i6;
        ?? r12;
        boolean z2 = arrayList.get(i2).r;
        ArrayList<Fragment> arrayList5 = this.M;
        if (arrayList5 == null) {
            this.M = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.M.addAll(this.f925e.n());
        Fragment fragmentY0 = y0();
        boolean z3 = false;
        for (int i7 = i2; i7 < i3; i7++) {
            b.l.d.a aVar = arrayList.get(i7);
            fragmentY0 = !arrayList2.get(i7).booleanValue() ? aVar.A(this.M, fragmentY0) : aVar.I(this.M, fragmentY0);
            z3 = z3 || aVar.f2850i;
        }
        this.M.clear();
        if (z2 || this.s < 1) {
            r1 = 1;
        } else if (f922b) {
            for (int i8 = i2; i8 < i3; i8++) {
                Iterator<p.a> it = arrayList.get(i8).f2844c.iterator();
                while (it.hasNext()) {
                    Fragment fragment = it.next().f2852b;
                    if (fragment != null && fragment.mFragmentManager != null) {
                        this.f925e.p(w(fragment));
                    }
                }
            }
            r1 = 1;
        } else {
            r1 = 1;
            q.B(this.t.f(), this.u, arrayList, arrayList2, i2, i3, false, this.p);
        }
        d0(arrayList, arrayList2, i2, i3);
        if (f922b) {
            boolean zBooleanValue = arrayList2.get(i3 - 1).booleanValue();
            for (int i9 = i2; i9 < i3; i9++) {
                b.l.d.a aVar2 = arrayList.get(i9);
                if (zBooleanValue) {
                    for (int size = aVar2.f2844c.size() - r1; size >= 0; size--) {
                        Fragment fragment2 = aVar2.f2844c.get(size).f2852b;
                        if (fragment2 != null) {
                            w(fragment2).m();
                        }
                    }
                } else {
                    Iterator<p.a> it2 = aVar2.f2844c.iterator();
                    while (it2.hasNext()) {
                        Fragment fragment3 = it2.next().f2852b;
                        if (fragment3 != null) {
                            w(fragment3).m();
                        }
                    }
                }
            }
            R0(this.s, r1);
            for (SpecialEffectsController specialEffectsController : t(arrayList, i2, i3)) {
                specialEffectsController.r(zBooleanValue);
                specialEffectsController.p();
                specialEffectsController.g();
            }
            i6 = i3;
            arrayList4 = arrayList2;
        } else {
            if (z2) {
                b.e.b bVar = new b.e.b();
                d(bVar);
                r14 = r1;
                z = z2;
                i4 = i3;
                i5 = i2;
                arrayList3 = arrayList2;
                iB1 = b1(arrayList, arrayList2, i2, i3, bVar);
                P0(bVar);
            } else {
                r14 = r1;
                z = z2;
                i4 = i3;
                i5 = i2;
                arrayList3 = arrayList2;
                iB1 = i4;
            }
            if (iB1 == i5 || !z) {
                arrayList4 = arrayList3;
                i6 = i4;
            } else {
                if (this.s >= r14) {
                    arrayList4 = arrayList3;
                    int i10 = iB1;
                    i6 = i4;
                    r12 = r14;
                    q.B(this.t.f(), this.u, arrayList, arrayList2, i2, i10, true, this.p);
                } else {
                    arrayList4 = arrayList3;
                    i6 = i4;
                    r12 = r14;
                }
                R0(this.s, r12);
            }
        }
        for (int i11 = i2; i11 < i6; i11++) {
            b.l.d.a aVar3 = arrayList.get(i11);
            if (arrayList4.get(i11).booleanValue() && aVar3.v >= 0) {
                aVar3.v = -1;
            }
            aVar3.G();
        }
        if (z3) {
            g1();
        }
    }

    public final void e1(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        g0(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).r) {
                if (i3 != i2) {
                    e0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).r) {
                        i3++;
                    }
                }
                e0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            e0(arrayList, arrayList2, i3, size);
        }
    }

    public void f(Fragment fragment, b.h.i.a aVar) {
        if (this.o.get(fragment) == null) {
            this.o.put(fragment, new HashSet<>());
        }
        this.o.get(fragment).add(aVar);
    }

    public boolean f0() {
        boolean zB0 = b0(true);
        l0();
        return zB0;
    }

    public void f1(Fragment fragment) {
        this.O.n(fragment);
    }

    public b.l.d.n g(Fragment fragment) {
        if (G0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        b.l.d.n nVarW = w(fragment);
        fragment.mFragmentManager = this;
        this.f925e.p(nVarW);
        if (!fragment.mDetached) {
            this.f925e.a(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (H0(fragment)) {
                this.F = true;
            }
        }
        return nVarW;
    }

    public final void g0(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<p> arrayList3 = this.N;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            p pVar = this.N.get(i2);
            if (arrayList != null && !pVar.f954a && (iIndexOf2 = arrayList.indexOf(pVar.f955b)) != -1 && arrayList2 != null && arrayList2.get(iIndexOf2).booleanValue()) {
                this.N.remove(i2);
                i2--;
                size--;
                pVar.c();
            } else if (pVar.e() || (arrayList != null && pVar.f955b.D(arrayList, 0, arrayList.size()))) {
                this.N.remove(i2);
                i2--;
                size--;
                if (arrayList == null || pVar.f954a || (iIndexOf = arrayList.indexOf(pVar.f955b)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf).booleanValue()) {
                    pVar.d();
                } else {
                    pVar.c();
                }
            }
            i2++;
        }
    }

    public final void g1() {
        if (this.n != null) {
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                this.n.get(i2).a();
            }
        }
    }

    public void h(b.l.d.l lVar) {
        this.r.add(lVar);
    }

    public Fragment h0(String str) {
        return this.f925e.f(str);
    }

    public void h1(Parcelable parcelable) {
        b.l.d.n nVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f957b == null) {
            return;
        }
        this.f925e.t();
        for (FragmentState fragmentState : fragmentManagerState.f957b) {
            if (fragmentState != null) {
                Fragment fragmentH = this.O.h(fragmentState.f966c);
                if (fragmentH != null) {
                    if (G0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragmentH);
                    }
                    nVar = new b.l.d.n(this.q, this.f925e, fragmentH, fragmentState);
                } else {
                    nVar = new b.l.d.n(this.q, this.f925e, this.t.f().getClassLoader(), r0(), fragmentState);
                }
                Fragment fragmentK = nVar.k();
                fragmentK.mFragmentManager = this;
                if (G0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentK.mWho + "): " + fragmentK);
                }
                nVar.o(this.t.f().getClassLoader());
                this.f925e.p(nVar);
                nVar.t(this.s);
            }
        }
        for (Fragment fragment : this.O.k()) {
            if (!this.f925e.c(fragment.mWho)) {
                if (G0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f957b);
                }
                this.O.n(fragment);
                fragment.mFragmentManager = this;
                b.l.d.n nVar2 = new b.l.d.n(this.q, this.f925e, fragment);
                nVar2.t(1);
                nVar2.m();
                fragment.mRemoving = true;
                nVar2.m();
            }
        }
        this.f925e.u(fragmentManagerState.f958c);
        if (fragmentManagerState.f959d != null) {
            this.f926f = new ArrayList<>(fragmentManagerState.f959d.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f959d;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                b.l.d.a aVarJ = backStackStateArr[i2].j(this);
                if (G0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarJ.v + "): " + aVarJ);
                    PrintWriter printWriter = new PrintWriter(new u("FragmentManager"));
                    aVarJ.x("  ", printWriter, false);
                    printWriter.close();
                }
                this.f926f.add(aVarJ);
                i2++;
            }
        } else {
            this.f926f = null;
        }
        this.k.set(fragmentManagerState.f960e);
        String str = fragmentManagerState.f961f;
        if (str != null) {
            Fragment fragmentH0 = h0(str);
            this.w = fragmentH0;
            M(fragmentH0);
        }
        ArrayList<String> arrayList = fragmentManagerState.f962g;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Bundle bundle = fragmentManagerState.f963h.get(i3);
                bundle.setClassLoader(this.t.f().getClassLoader());
                this.l.put(arrayList.get(i3), bundle);
            }
        }
        this.E = new ArrayDeque<>(fragmentManagerState.f964i);
    }

    public void i(Fragment fragment) {
        this.O.f(fragment);
    }

    public Fragment i0(int i2) {
        return this.f925e.g(i2);
    }

    public int j() {
        return this.k.getAndIncrement();
    }

    public Fragment j0(String str) {
        return this.f925e.h(str);
    }

    public Parcelable j1() {
        int size;
        l0();
        Y();
        b0(true);
        this.G = true;
        this.O.o(true);
        ArrayList<FragmentState> arrayListV = this.f925e.v();
        BackStackState[] backStackStateArr = null;
        if (arrayListV.isEmpty()) {
            if (G0(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> arrayListW = this.f925e.w();
        ArrayList<b.l.d.a> arrayList = this.f926f;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState(this.f926f.get(i2));
                if (G0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f926f.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f957b = arrayListV;
        fragmentManagerState.f958c = arrayListW;
        fragmentManagerState.f959d = backStackStateArr;
        fragmentManagerState.f960e = this.k.get();
        Fragment fragment = this.w;
        if (fragment != null) {
            fragmentManagerState.f961f = fragment.mWho;
        }
        fragmentManagerState.f962g.addAll(this.l.keySet());
        fragmentManagerState.f963h.addAll(this.l.values());
        fragmentManagerState.f964i = new ArrayList<>(this.E);
        return fragmentManagerState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"SyntheticAccessor"})
    public void k(b.l.d.g<?> gVar, b.l.d.d dVar, Fragment fragment) {
        String str;
        if (this.t != null) {
            throw new IllegalStateException("Already attached");
        }
        this.t = gVar;
        this.u = dVar;
        this.v = fragment;
        if (fragment != null) {
            h(new i(fragment));
        } else if (gVar instanceof b.l.d.l) {
            h((b.l.d.l) gVar);
        }
        if (this.v != null) {
            r1();
        }
        if (gVar instanceof b.a.c) {
            b.a.c cVar = (b.a.c) gVar;
            OnBackPressedDispatcher onBackPressedDispatcher = cVar.getOnBackPressedDispatcher();
            this.f929i = onBackPressedDispatcher;
            b.n.g gVar2 = cVar;
            if (fragment != null) {
                gVar2 = fragment;
            }
            onBackPressedDispatcher.a(gVar2, this.j);
        }
        if (fragment != null) {
            this.O = fragment.mFragmentManager.o0(fragment);
        } else if (gVar instanceof t) {
            this.O = b.l.d.k.j(((t) gVar).getViewModelStore());
        } else {
            this.O = new b.l.d.k(false);
        }
        this.O.o(L0());
        this.f925e.x(this.O);
        Object obj = this.t;
        if (obj instanceof b.a.e.c) {
            ActivityResultRegistry activityResultRegistry = ((b.a.e.c) obj).getActivityResultRegistry();
            if (fragment != null) {
                str = fragment.mWho + ":";
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.B = activityResultRegistry.i(str2 + "StartActivityForResult", new b.a.e.d.c(), new j());
            this.C = activityResultRegistry.i(str2 + "StartIntentSenderForResult", new k(), new a());
            this.D = activityResultRegistry.i(str2 + "RequestPermissions", new b.a.e.d.b(), new b());
        }
    }

    public Fragment k0(String str) {
        return this.f925e.i(str);
    }

    public void k1() {
        synchronized (this.f923c) {
            ArrayList<p> arrayList = this.N;
            boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z2 = this.f923c.size() == 1;
            if (z || z2) {
                this.t.g().removeCallbacks(this.P);
                this.t.g().post(this.P);
                r1();
            }
        }
    }

    public void l(Fragment fragment) {
        if (G0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            this.f925e.a(fragment);
            if (G0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (H0(fragment)) {
                this.F = true;
            }
        }
    }

    public final void l0() {
        if (f922b) {
            Iterator<SpecialEffectsController> it = s().iterator();
            while (it.hasNext()) {
                it.next().k();
            }
        } else if (this.N != null) {
            while (!this.N.isEmpty()) {
                this.N.remove(0).d();
            }
        }
    }

    public void l1(Fragment fragment, boolean z) {
        ViewGroup viewGroupQ0 = q0(fragment);
        if (viewGroupQ0 == null || !(viewGroupQ0 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) viewGroupQ0).setDrawDisappearingViewsLast(!z);
    }

    public b.l.d.p m() {
        return new b.l.d.a(this);
    }

    public final boolean m0(ArrayList<b.l.d.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this.f923c) {
            if (this.f923c.isEmpty()) {
                return false;
            }
            int size = this.f923c.size();
            boolean zA = false;
            for (int i2 = 0; i2 < size; i2++) {
                zA |= this.f923c.get(i2).a(arrayList, arrayList2);
            }
            this.f923c.clear();
            this.t.g().removeCallbacks(this.P);
            return zA;
        }
    }

    public void m1(Fragment fragment, Lifecycle.State state) {
        if (fragment.equals(h0(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void n(Fragment fragment) {
        HashSet<b.h.i.a> hashSet = this.o.get(fragment);
        if (hashSet != null) {
            Iterator<b.h.i.a> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            hashSet.clear();
            x(fragment);
            this.o.remove(fragment);
        }
    }

    public int n0() {
        ArrayList<b.l.d.a> arrayList = this.f926f;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void n1(Fragment fragment) {
        if (fragment == null || (fragment.equals(h0(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.w;
            this.w = fragment;
            M(fragment2);
            M(this.w);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public boolean o() {
        boolean zH0 = false;
        for (Fragment fragment : this.f925e.l()) {
            if (fragment != null) {
                zH0 = H0(fragment);
            }
            if (zH0) {
                return true;
            }
        }
        return false;
    }

    public final b.l.d.k o0(Fragment fragment) {
        return this.O.i(fragment);
    }

    public final void o1(Fragment fragment) {
        ViewGroup viewGroupQ0 = q0(fragment);
        if (viewGroupQ0 == null || fragment.getEnterAnim() + fragment.getExitAnim() + fragment.getPopEnterAnim() + fragment.getPopExitAnim() <= 0) {
            return;
        }
        int i2 = b.l.b.visible_removing_fragment_view_tag;
        if (viewGroupQ0.getTag(i2) == null) {
            viewGroupQ0.setTag(i2, fragment);
        }
        ((Fragment) viewGroupQ0.getTag(i2)).setPopDirection(fragment.getPopDirection());
    }

    public final void p() {
        if (L0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public b.l.d.d p0() {
        return this.u;
    }

    public void p1(Fragment fragment) {
        if (G0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public final void q() {
        this.f924d = false;
        this.L.clear();
        this.K.clear();
    }

    public final ViewGroup q0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.u.d()) {
            View viewC = this.u.c(fragment.mContainerId);
            if (viewC instanceof ViewGroup) {
                return (ViewGroup) viewC;
            }
        }
        return null;
    }

    public final void q1() {
        Iterator<b.l.d.n> it = this.f925e.k().iterator();
        while (it.hasNext()) {
            W0(it.next());
        }
    }

    public final void r(String str) {
        this.l.remove(str);
    }

    public b.l.d.f r0() {
        b.l.d.f fVar = this.x;
        if (fVar != null) {
            return fVar;
        }
        Fragment fragment = this.v;
        return fragment != null ? fragment.mFragmentManager.r0() : this.y;
    }

    public final void r1() {
        synchronized (this.f923c) {
            if (this.f923c.isEmpty()) {
                this.j.f(n0() > 0 && J0(this.v));
            } else {
                this.j.f(true);
            }
        }
    }

    public final Set<SpecialEffectsController> s() {
        HashSet hashSet = new HashSet();
        Iterator<b.l.d.n> it = this.f925e.k().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = it.next().k().mContainer;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.o(viewGroup, z0()));
            }
        }
        return hashSet;
    }

    public b.l.d.o s0() {
        return this.f925e;
    }

    public final Set<SpecialEffectsController> t(ArrayList<b.l.d.a> arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator<p.a> it = arrayList.get(i2).f2844c.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().f2852b;
                if (fragment != null && (viewGroup = fragment.mContainer) != null) {
                    hashSet.add(SpecialEffectsController.n(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    public List<Fragment> t0() {
        return this.f925e.n();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.v;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.v)));
            sb.append("}");
        } else {
            b.l.d.g<?> gVar = this.t;
            if (gVar != null) {
                sb.append(gVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.t)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public void u(b.l.d.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.z(z3);
        } else {
            aVar.y();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.s >= 1) {
            q.B(this.t.f(), this.u, arrayList, arrayList2, 0, 1, true, this.p);
        }
        if (z3) {
            R0(this.s, true);
        }
        for (Fragment fragment : this.f925e.l()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.C(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public b.l.d.g<?> u0() {
        return this.t;
    }

    public final void v(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            c.d dVarC = b.l.d.c.c(this.t.f(), fragment, !fragment.mHidden, fragment.getPopDirection());
            if (dVarC == null || (animator = dVarC.f2804b) == null) {
                if (dVarC != null) {
                    fragment.mView.startAnimation(dVarC.f2803a);
                    dVarC.f2803a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    ViewGroup viewGroup = fragment.mContainer;
                    View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    dVarC.f2804b.addListener(new h(viewGroup, view, fragment));
                }
                dVarC.f2804b.start();
            }
        }
        E0(fragment);
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public LayoutInflater.Factory2 v0() {
        return this.f928h;
    }

    public b.l.d.n w(Fragment fragment) {
        b.l.d.n nVarM = this.f925e.m(fragment.mWho);
        if (nVarM != null) {
            return nVarM;
        }
        b.l.d.n nVar = new b.l.d.n(this.q, this.f925e, fragment);
        nVar.o(this.t.f().getClassLoader());
        nVar.t(this.s);
        return nVar;
    }

    public b.l.d.i w0() {
        return this.q;
    }

    public final void x(Fragment fragment) {
        fragment.performDestroyView();
        this.q.n(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.j(null);
        fragment.mInLayout = false;
    }

    public Fragment x0() {
        return this.v;
    }

    public void y(Fragment fragment) {
        if (G0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (G0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f925e.s(fragment);
            if (H0(fragment)) {
                this.F = true;
            }
            o1(fragment);
        }
    }

    public Fragment y0() {
        return this.w;
    }

    public void z() {
        this.G = false;
        this.H = false;
        this.O.o(false);
        T(4);
    }

    public v z0() {
        v vVar = this.z;
        if (vVar != null) {
            return vVar;
        }
        Fragment fragment = this.v;
        return fragment != null ? fragment.mFragmentManager.z0() : this.A;
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f934b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f935c;

        public class a implements Parcelable.Creator<LaunchedFragmentInfo> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public LaunchedFragmentInfo[] newArray(int i2) {
                return new LaunchedFragmentInfo[i2];
            }
        }

        public LaunchedFragmentInfo(String str, int i2) {
            this.f934b = str;
            this.f935c = i2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f934b);
            parcel.writeInt(this.f935c);
        }

        public LaunchedFragmentInfo(Parcel parcel) {
            this.f934b = parcel.readString();
            this.f935c = parcel.readInt();
        }
    }
}
