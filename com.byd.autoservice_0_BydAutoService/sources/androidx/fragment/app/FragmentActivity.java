package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import h.c;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import r0.d;
import r0.f;
import s.h;
import t0.g;
import t0.p;
import t0.q;

/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements ActivityCompat.b, ActivityCompat.d {
    public static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    public static final String FRAGMENTS_TAG = "android:support:fragments";
    public static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    public static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    public static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    public boolean mCreated;
    public final g mFragmentLifecycleRegistry;
    public final d mFragments;
    public int mNextCandidateRequestIndex;
    public h<String> mPendingFragmentActivityResults;
    public boolean mRequestedPermissionsFromFragment;
    public boolean mResumed;
    public boolean mStartedActivityFromFragment;
    public boolean mStartedIntentSenderFromFragment;
    public boolean mStopped;

    public class a extends f<FragmentActivity> implements q, c {
        public a() {
            super(FragmentActivity.this);
        }

        @Override // r0.f, r0.c
        public View b(int i3) {
            return FragmentActivity.this.findViewById(i3);
        }

        @Override // r0.f, r0.c
        public boolean c() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // r0.f
        public void g(Fragment fragment) {
            FragmentActivity.this.onAttachFragment(fragment);
        }

        @Override // t0.f
        public Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        @Override // h.c
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.getOnBackPressedDispatcher();
        }

        @Override // t0.q
        public p getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        @Override // r0.f
        public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // r0.f
        public LayoutInflater j() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override // r0.f
        public int k() {
            Window window = FragmentActivity.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // r0.f
        public boolean l() {
            return FragmentActivity.this.getWindow() != null;
        }

        @Override // r0.f
        public void m(Fragment fragment, String[] strArr, int i3) {
            FragmentActivity.this.requestPermissionsFromFragment(fragment, strArr, i3);
        }

        @Override // r0.f
        public boolean n(Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override // r0.f
        public boolean o(String str) {
            return ActivityCompat.p(FragmentActivity.this, str);
        }

        @Override // r0.f
        public void p(Fragment fragment, Intent intent, int i3, Bundle bundle) {
            FragmentActivity.this.startActivityFromFragment(fragment, intent, i3, bundle);
        }

        @Override // r0.f
        public void q(Fragment fragment, IntentSender intentSender, int i3, Intent intent, int i4, int i5, int i6, Bundle bundle) throws IntentSender.SendIntentException {
            FragmentActivity.this.startIntentSenderFromFragment(fragment, intentSender, i3, intent, i4, i5, i6, bundle);
        }

        @Override // r0.f
        public void r() {
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }

        @Override // r0.f
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public FragmentActivity i() {
            return FragmentActivity.this;
        }
    }

    public FragmentActivity() {
        this.mFragments = d.b(new a());
        this.mFragmentLifecycleRegistry = new g(this);
        this.mStopped = true;
    }

    private int allocateRequestIndex(Fragment fragment) {
        if (this.mPendingFragmentActivityResults.l() >= 65534) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (this.mPendingFragmentActivityResults.g(this.mNextCandidateRequestIndex) >= 0) {
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        }
        int i3 = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.j(i3, fragment.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        return i3;
    }

    public static void checkForValidRequestCode(int i3) {
        if ((i3 & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    private void markFragmentsCreated() {
        while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED)) {
        }
    }

    private static boolean markState(r0.g gVar, Lifecycle.State state) {
        boolean zMarkState = false;
        for (Fragment fragment : gVar.f()) {
            if (fragment != null) {
                if (fragment.getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
                    fragment.mLifecycleRegistry.p(state);
                    zMarkState = true;
                }
                if (fragment.getHost() != null) {
                    zMarkState |= markState(fragment.getChildFragmentManager(), state);
                }
            }
        }
        return zMarkState;
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.w(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            u0.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.u().b(str, fileDescriptor, printWriter, strArr);
    }

    public r0.g getSupportFragmentManager() {
        return this.mFragments.u();
    }

    @Deprecated
    public u0.a getSupportLoaderManager() {
        return u0.a.b(this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i3, int i4, Intent intent) {
        this.mFragments.v();
        int i5 = i3 >> 16;
        if (i5 == 0) {
            ActivityCompat.c cVarJ = ActivityCompat.j();
            if (cVarJ == null || !cVarJ.b(this, i3, i4, intent)) {
                super.onActivityResult(i3, i4, intent);
                return;
            }
            return;
        }
        int i6 = i5 - 1;
        String strE = this.mPendingFragmentActivityResults.e(i6);
        this.mPendingFragmentActivityResults.k(i6);
        if (strE == null) {
            Log.w(TAG, "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentT = this.mFragments.t(strE);
        if (fragmentT != null) {
            fragmentT.onActivityResult(i3 & 65535, i4, intent);
            return;
        }
        Log.w(TAG, "Activity result no fragment exists for who: " + strE);
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.v();
        this.mFragments.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mFragments.a(null);
        if (bundle != null) {
            this.mFragments.x(bundle.getParcelable(FRAGMENTS_TAG));
            if (bundle.containsKey(NEXT_CANDIDATE_REQUEST_INDEX_TAG)) {
                this.mNextCandidateRequestIndex = bundle.getInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG);
                int[] intArray = bundle.getIntArray(ALLOCATED_REQUEST_INDICIES_TAG);
                String[] stringArray = bundle.getStringArray(REQUEST_FRAGMENT_WHO_TAG);
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w(TAG, "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.mPendingFragmentActivityResults = new h<>(intArray.length);
                    for (int i3 = 0; i3 < intArray.length; i3++) {
                        this.mPendingFragmentActivityResults.j(intArray[i3], stringArray[i3]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new h<>();
            this.mNextCandidateRequestIndex = 0;
        }
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_CREATE);
        this.mFragments.f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i3, Menu menu) {
        return i3 == 0 ? super.onCreatePanelMenu(i3, menu) | this.mFragments.g(menu, getMenuInflater()) : super.onCreatePanelMenu(i3, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.h();
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i3, MenuItem menuItem) {
        if (super.onMenuItemSelected(i3, menuItem)) {
            return true;
        }
        if (i3 == 0) {
            return this.mFragments.k(menuItem);
        }
        if (i3 != 6) {
            return false;
        }
        return this.mFragments.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z3) {
        this.mFragments.j(z3);
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.v();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i3, Menu menu) {
        if (i3 == 0) {
            this.mFragments.l(menu);
        }
        super.onPanelClosed(i3, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.m();
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z3) {
        this.mFragments.n(z3);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Deprecated
    public boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i3, View view, Menu menu) {
        return i3 == 0 ? onPrepareOptionsPanel(view, menu) | this.mFragments.o(menu) : super.onPreparePanel(i3, view, menu);
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.b
    public void onRequestPermissionsResult(int i3, String[] strArr, int[] iArr) {
        this.mFragments.v();
        int i4 = (i3 >> 16) & 65535;
        if (i4 != 0) {
            int i5 = i4 - 1;
            String strE = this.mPendingFragmentActivityResults.e(i5);
            this.mPendingFragmentActivityResults.k(i5);
            if (strE == null) {
                Log.w(TAG, "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentT = this.mFragments.t(strE);
            if (fragmentT != null) {
                fragmentT.onRequestPermissionsResult(i3 & 65535, strArr, iArr);
                return;
            }
            Log.w(TAG, "Activity result no fragment exists for who: " + strE);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mResumed = true;
        this.mFragments.v();
        this.mFragments.s();
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_RESUME);
        this.mFragments.p();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        markFragmentsCreated();
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_STOP);
        Parcelable parcelableY = this.mFragments.y();
        if (parcelableY != null) {
            bundle.putParcelable(FRAGMENTS_TAG, parcelableY);
        }
        if (this.mPendingFragmentActivityResults.l() > 0) {
            bundle.putInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG, this.mNextCandidateRequestIndex);
            int[] iArr = new int[this.mPendingFragmentActivityResults.l()];
            String[] strArr = new String[this.mPendingFragmentActivityResults.l()];
            for (int i3 = 0; i3 < this.mPendingFragmentActivityResults.l(); i3++) {
                iArr[i3] = this.mPendingFragmentActivityResults.i(i3);
                strArr[i3] = this.mPendingFragmentActivityResults.m(i3);
            }
            bundle.putIntArray(ALLOCATED_REQUEST_INDICIES_TAG, iArr);
            bundle.putStringArray(REQUEST_FRAGMENT_WHO_TAG, strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.c();
        }
        this.mFragments.v();
        this.mFragments.s();
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_START);
        this.mFragments.q();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.v();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.r();
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_STOP);
    }

    public void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i3) {
        if (i3 == -1) {
            ActivityCompat.m(this, strArr, i3);
            return;
        }
        checkForValidRequestCode(i3);
        try {
            this.mRequestedPermissionsFromFragment = true;
            ActivityCompat.m(this, strArr, ((allocateRequestIndex(fragment) + 1) << 16) + (i3 & 65535));
        } finally {
            this.mRequestedPermissionsFromFragment = false;
        }
    }

    public void setEnterSharedElementCallback(a0.h hVar) {
        ActivityCompat.n(this, hVar);
    }

    public void setExitSharedElementCallback(a0.h hVar) {
        ActivityCompat.o(this, hVar);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i3) {
        if (!this.mStartedActivityFromFragment && i3 != -1) {
            checkForValidRequestCode(i3);
        }
        super.startActivityForResult(intent, i3);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i3) {
        startActivityFromFragment(fragment, intent, i3, (Bundle) null);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i3, Intent intent, int i4, int i5, int i6) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i3 != -1) {
            checkForValidRequestCode(i3);
        }
        super.startIntentSenderForResult(intentSender, i3, intent, i4, i5, i6);
    }

    public void startIntentSenderFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i3, Intent intent, int i4, int i5, int i6, Bundle bundle) throws IntentSender.SendIntentException {
        this.mStartedIntentSenderFromFragment = true;
        try {
            if (i3 == -1) {
                ActivityCompat.r(this, intentSender, i3, intent, i4, i5, i6, bundle);
            } else {
                checkForValidRequestCode(i3);
                ActivityCompat.r(this, intentSender, ((allocateRequestIndex(fragment) + 1) << 16) + (i3 & 65535), intent, i4, i5, i6, bundle);
            }
        } finally {
            this.mStartedIntentSenderFromFragment = false;
        }
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.i(this);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.k(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.s(this);
    }

    @Override // androidx.core.app.ActivityCompat.d
    public final void validateRequestPermissionsRequestCode(int i3) {
        if (this.mRequestedPermissionsFromFragment || i3 == -1) {
            return;
        }
        checkForValidRequestCode(i3);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i3, Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        try {
            if (i3 == -1) {
                ActivityCompat.q(this, intent, -1, bundle);
            } else {
                checkForValidRequestCode(i3);
                ActivityCompat.q(this, intent, ((allocateRequestIndex(fragment) + 1) << 16) + (i3 & 65535), bundle);
            }
        } finally {
            this.mStartedActivityFromFragment = false;
        }
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i3, Bundle bundle) {
        if (!this.mStartedActivityFromFragment && i3 != -1) {
            checkForValidRequestCode(i3);
        }
        super.startActivityForResult(intent, i3, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i3, Intent intent, int i4, int i5, int i6, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i3 != -1) {
            checkForValidRequestCode(i3);
        }
        super.startIntentSenderForResult(intentSender, i3, intent, i4, i5, i6, bundle);
    }

    public FragmentActivity(int i3) {
        super(i3);
        this.mFragments = d.b(new a());
        this.mFragmentLifecycleRegistry = new g(this);
        this.mStopped = true;
    }
}
