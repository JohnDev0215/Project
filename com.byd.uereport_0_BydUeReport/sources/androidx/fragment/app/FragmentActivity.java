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
import d0.i;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import k.c;
import v.h;
import v0.d;
import v0.f;
import x0.g;
import x0.p;
import x0.q;

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

        @Override // v0.f, v0.c
        public View b(int i8) {
            return FragmentActivity.this.findViewById(i8);
        }

        @Override // v0.f, v0.c
        public boolean c() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // v0.f
        public void g(Fragment fragment) {
            FragmentActivity.this.onAttachFragment(fragment);
        }

        @Override // x0.f
        public Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        @Override // k.c
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.getOnBackPressedDispatcher();
        }

        @Override // x0.q
        public p getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        @Override // v0.f
        public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // v0.f
        public LayoutInflater j() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override // v0.f
        public int k() {
            Window window = FragmentActivity.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // v0.f
        public boolean l() {
            return FragmentActivity.this.getWindow() != null;
        }

        @Override // v0.f
        public void m(Fragment fragment, String[] strArr, int i8) {
            FragmentActivity.this.requestPermissionsFromFragment(fragment, strArr, i8);
        }

        @Override // v0.f
        public boolean n(Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override // v0.f
        public boolean o(String str) {
            return ActivityCompat.p(FragmentActivity.this, str);
        }

        @Override // v0.f
        public void p(Fragment fragment, Intent intent, int i8, Bundle bundle) {
            FragmentActivity.this.startActivityFromFragment(fragment, intent, i8, bundle);
        }

        @Override // v0.f
        public void q(Fragment fragment, IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) throws IntentSender.SendIntentException {
            FragmentActivity.this.startIntentSenderFromFragment(fragment, intentSender, i8, intent, i9, i10, i11, bundle);
        }

        @Override // v0.f
        public void r() {
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }

        @Override // v0.f
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
        int i8 = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.j(i8, fragment.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        return i8;
    }

    public static void checkForValidRequestCode(int i8) {
        if ((i8 & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    private void markFragmentsCreated() {
        while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED)) {
        }
    }

    private static boolean markState(v0.g gVar, Lifecycle.State state) {
        boolean zMarkState = false;
        for (Fragment fragment : gVar.g()) {
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
            y0.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.u().b(str, fileDescriptor, printWriter, strArr);
    }

    public v0.g getSupportFragmentManager() {
        return this.mFragments.u();
    }

    @Deprecated
    public y0.a getSupportLoaderManager() {
        return y0.a.b(this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i8, int i9, Intent intent) {
        this.mFragments.v();
        int i10 = i8 >> 16;
        if (i10 == 0) {
            ActivityCompat.c cVarJ = ActivityCompat.j();
            if (cVarJ == null || !cVarJ.a(this, i8, i9, intent)) {
                super.onActivityResult(i8, i9, intent);
                return;
            }
            return;
        }
        int i11 = i10 - 1;
        String strE = this.mPendingFragmentActivityResults.e(i11);
        this.mPendingFragmentActivityResults.k(i11);
        if (strE == null) {
            Log.w(TAG, "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentT = this.mFragments.t(strE);
        if (fragmentT != null) {
            fragmentT.onActivityResult(i8 & 65535, i9, intent);
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
                    for (int i8 = 0; i8 < intArray.length; i8++) {
                        this.mPendingFragmentActivityResults.j(intArray[i8], stringArray[i8]);
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
    public boolean onCreatePanelMenu(int i8, Menu menu) {
        return i8 == 0 ? super.onCreatePanelMenu(i8, menu) | this.mFragments.g(menu, getMenuInflater()) : super.onCreatePanelMenu(i8, menu);
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
    public boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        if (super.onMenuItemSelected(i8, menuItem)) {
            return true;
        }
        if (i8 == 0) {
            return this.mFragments.k(menuItem);
        }
        if (i8 != 6) {
            return false;
        }
        return this.mFragments.e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z7) {
        this.mFragments.j(z7);
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.v();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i8, Menu menu) {
        if (i8 == 0) {
            this.mFragments.l(menu);
        }
        super.onPanelClosed(i8, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.m();
        this.mFragmentLifecycleRegistry.i(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z7) {
        this.mFragments.n(z7);
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
    public boolean onPreparePanel(int i8, View view, Menu menu) {
        return i8 == 0 ? onPrepareOptionsPanel(view, menu) | this.mFragments.o(menu) : super.onPreparePanel(i8, view, menu);
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.b
    public void onRequestPermissionsResult(int i8, String[] strArr, int[] iArr) {
        this.mFragments.v();
        int i9 = (i8 >> 16) & 65535;
        if (i9 != 0) {
            int i10 = i9 - 1;
            String strE = this.mPendingFragmentActivityResults.e(i10);
            this.mPendingFragmentActivityResults.k(i10);
            if (strE == null) {
                Log.w(TAG, "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentT = this.mFragments.t(strE);
            if (fragmentT != null) {
                fragmentT.onRequestPermissionsResult(i8 & 65535, strArr, iArr);
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
            for (int i8 = 0; i8 < this.mPendingFragmentActivityResults.l(); i8++) {
                iArr[i8] = this.mPendingFragmentActivityResults.i(i8);
                strArr[i8] = this.mPendingFragmentActivityResults.m(i8);
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

    public void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i8) {
        if (i8 == -1) {
            ActivityCompat.m(this, strArr, i8);
            return;
        }
        checkForValidRequestCode(i8);
        try {
            this.mRequestedPermissionsFromFragment = true;
            ActivityCompat.m(this, strArr, ((allocateRequestIndex(fragment) + 1) << 16) + (i8 & 65535));
        } finally {
            this.mRequestedPermissionsFromFragment = false;
        }
    }

    public void setEnterSharedElementCallback(i iVar) {
        ActivityCompat.n(this, iVar);
    }

    public void setExitSharedElementCallback(i iVar) {
        ActivityCompat.o(this, iVar);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i8) {
        if (!this.mStartedActivityFromFragment && i8 != -1) {
            checkForValidRequestCode(i8);
        }
        super.startActivityForResult(intent, i8);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i8) {
        startActivityFromFragment(fragment, intent, i8, (Bundle) null);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i8 != -1) {
            checkForValidRequestCode(i8);
        }
        super.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11);
    }

    public void startIntentSenderFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) throws IntentSender.SendIntentException {
        this.mStartedIntentSenderFromFragment = true;
        try {
            if (i8 == -1) {
                ActivityCompat.r(this, intentSender, i8, intent, i9, i10, i11, bundle);
            } else {
                checkForValidRequestCode(i8);
                ActivityCompat.r(this, intentSender, ((allocateRequestIndex(fragment) + 1) << 16) + (i8 & 65535), intent, i9, i10, i11, bundle);
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
    public final void validateRequestPermissionsRequestCode(int i8) {
        if (this.mRequestedPermissionsFromFragment || i8 == -1) {
            return;
        }
        checkForValidRequestCode(i8);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i8, Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        try {
            if (i8 == -1) {
                ActivityCompat.q(this, intent, -1, bundle);
            } else {
                checkForValidRequestCode(i8);
                ActivityCompat.q(this, intent, ((allocateRequestIndex(fragment) + 1) << 16) + (i8 & 65535), bundle);
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
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i8, Bundle bundle) {
        if (!this.mStartedActivityFromFragment && i8 != -1) {
            checkForValidRequestCode(i8);
        }
        super.startActivityForResult(intent, i8, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i8 != -1) {
            checkForValidRequestCode(i8);
        }
        super.startIntentSenderForResult(intentSender, i8, intent, i9, i10, i11, bundle);
    }

    public FragmentActivity(int i8) {
        super(i8);
        this.mFragments = d.b(new a());
        this.mFragmentLifecycleRegistry = new g(this);
        this.mStopped = true;
    }
}
