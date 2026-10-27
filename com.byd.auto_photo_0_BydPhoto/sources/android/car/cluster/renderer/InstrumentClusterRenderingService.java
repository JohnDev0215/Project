package android.car.cluster.renderer;

import android.annotation.SystemApi;
import android.app.ActivityOptions;
import android.app.Service;
import android.car.Car;
import android.car.VehicleAreaDoor;
import android.car.cluster.ClusterActivityState;
import android.car.cluster.renderer.IInstrumentCluster;
import android.car.cluster.renderer.IInstrumentClusterHelper;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.car.navigation.CarNavigationInstrumentCluster;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.util.LruCache;
import android.view.KeyEvent;
import com.android.internal.annotations.GuardedBy;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public abstract class InstrumentClusterRenderingService extends Service {
    private static final String BITMAP_QUERY_HEIGHT = "h";
    private static final String BITMAP_QUERY_OFFLANESALPHA = "offLanesAlpha";
    private static final String BITMAP_QUERY_WIDTH = "w";
    public static final String EXTRA_BUNDLE_KEY_FOR_INSTRUMENT_CLUSTER_HELPER = "android.car.cluster.renderer.IInstrumentClusterHelper";
    private static final int IMAGE_CACHE_SIZE_BYTES = 4194304;
    private static final String TAG = "CAR.L.CLUSTER";
    private ActivityOptions mActivityOptions;
    private ClusterActivityState mActivityState;

    @GuardedBy({"mLock"})
    private IInstrumentClusterHelper mInstrumentClusterHelper;

    @GuardedBy({"mLock"})
    private ContextOwner mNavContextOwner;
    private ComponentName mNavigationComponent;
    private RendererBinder mRendererBinder;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private final Object mLock = new Object();
    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(4194304) { // from class: android.car.cluster.renderer.InstrumentClusterRenderingService.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    static class ContextOwner {
        final Set<String> mAuthorities;
        final Set<String> mPackageNames;
        final int mPid;
        final int mUid;

        ContextOwner(int i, int i2, final PackageManager packageManager) {
            this.mUid = i;
            this.mPid = i2;
            String[] packagesForUid = i != 0 ? packageManager.getPackagesForUid(i) : null;
            Set<String> setUnmodifiableSet = packagesForUid != null ? Collections.unmodifiableSet(new HashSet(Arrays.asList(packagesForUid))) : Collections.emptySet();
            this.mPackageNames = setUnmodifiableSet;
            this.mAuthorities = Collections.unmodifiableSet((Set) setUnmodifiableSet.stream().map(new Function() { // from class: android.car.cluster.renderer.c
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f215a.a(packageManager, (String) obj);
                }
            }).flatMap(new Function() { // from class: android.car.cluster.renderer.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((List) obj).stream();
                }
            }).collect(Collectors.toSet()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getAuthoritiesForPackage, reason: merged with bridge method [inline-methods] */
        public List<String> a(PackageManager packageManager, String str) {
            try {
                ProviderInfo[] providerInfoArr = packageManager.getPackageInfo(str, 8).providers;
                return providerInfoArr == null ? Collections.emptyList() : (List) Arrays.stream(providerInfoArr).map(new Function() { // from class: android.car.cluster.renderer.b
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ProviderInfo) obj).authority;
                    }
                }).collect(Collectors.toList());
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("CAR.L.CLUSTER", "Package name not found while retrieving content provider authorities: " + str);
                return Collections.emptyList();
            }
        }

        public String toString() {
            return "{uid: " + this.mUid + ", pid: " + this.mPid + ", packagenames: " + this.mPackageNames + ", authorities: " + this.mAuthorities + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class NavigationBinder extends IInstrumentClusterNavigation.Stub {
        private final NavigationRenderer mNavigationRenderer;

        NavigationBinder(NavigationRenderer navigationRenderer) {
            this.mNavigationRenderer = navigationRenderer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$getInstrumentClusterInfo$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ CarNavigationInstrumentCluster a() {
            return this.mNavigationRenderer.getNavigationProperties();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onNavigationStateChanged$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(Bundle bundle) {
            NavigationRenderer navigationRenderer = this.mNavigationRenderer;
            if (navigationRenderer != null) {
                navigationRenderer.onNavigationStateChanged(bundle);
            }
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public CarNavigationInstrumentCluster getInstrumentClusterInfo() {
            InstrumentClusterRenderingService.this.assertClusterManagerPermission();
            return (CarNavigationInstrumentCluster) InstrumentClusterRenderingService.this.runAndWaitResult(new Supplier() { // from class: android.car.cluster.renderer.d
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.f217a.a();
                }
            });
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onNavigationStateChanged(final Bundle bundle) {
            InstrumentClusterRenderingService.this.assertClusterManagerPermission();
            InstrumentClusterRenderingService.this.mUiHandler.post(new Runnable() { // from class: android.car.cluster.renderer.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f218a.b(bundle);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class RendererBinder extends IInstrumentCluster.Stub {
        private final NavigationRenderer mNavigationRenderer;

        RendererBinder(NavigationRenderer navigationRenderer) {
            this.mNavigationRenderer = navigationRenderer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onKeyEvent$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void a(KeyEvent keyEvent) {
            InstrumentClusterRenderingService.this.onKeyEvent(keyEvent);
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public IInstrumentClusterNavigation getNavigationService() {
            return InstrumentClusterRenderingService.this.new NavigationBinder(this.mNavigationRenderer);
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void onKeyEvent(final KeyEvent keyEvent) {
            InstrumentClusterRenderingService.this.mUiHandler.post(new Runnable() { // from class: android.car.cluster.renderer.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f220a.a(keyEvent);
                }
            });
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void setNavigationContextOwner(int i, int i2) {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "Updating navigation ownership to uid: " + i + ", pid: " + i2);
            }
            synchronized (InstrumentClusterRenderingService.this.mLock) {
                InstrumentClusterRenderingService instrumentClusterRenderingService = InstrumentClusterRenderingService.this;
                instrumentClusterRenderingService.mNavContextOwner = new ContextOwner(i, i2, instrumentClusterRenderingService.getPackageManager());
            }
            Handler handler = InstrumentClusterRenderingService.this.mUiHandler;
            final InstrumentClusterRenderingService instrumentClusterRenderingService2 = InstrumentClusterRenderingService.this;
            handler.post(new Runnable() { // from class: android.car.cluster.renderer.g
                @Override // java.lang.Runnable
                public final void run() {
                    instrumentClusterRenderingService2.updateNavigationActivity();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assertClusterManagerPermission() {
        if (checkCallingOrSelfPermission(Car.PERMISSION_CAR_NAVIGATION_MANAGER) != 0) {
            throw new SecurityException("requires android.car.permission.CAR_NAVIGATION_MANAGER");
        }
    }

    private IInstrumentClusterHelper getClusterHelper() {
        IInstrumentClusterHelper iInstrumentClusterHelper;
        synchronized (this.mLock) {
            if (this.mInstrumentClusterHelper == null) {
                Log.w("mInstrumentClusterHelper still null, should wait until onBind", new RuntimeException());
            }
            iInstrumentClusterHelper = this.mInstrumentClusterHelper;
        }
        return iInstrumentClusterHelper;
    }

    private ComponentName getComponentFromPackage(String str) {
        String str2;
        PackageManager packageManager = getPackageManager();
        if (packageManager.checkPermission(Car.PERMISSION_CAR_DISPLAY_IN_CLUSTER, str) != 0) {
            str2 = String.format("Package '%s' doesn't have permission %s", str, Car.PERMISSION_CAR_DISPLAY_IN_CLUSTER);
        } else {
            Intent intent = new Intent("android.intent.action.MAIN").addCategory("android.car.cluster.NAVIGATION").setPackage(str);
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 64);
            if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty() && listQueryIntentActivities.get(0).getComponentInfo() != null) {
                return listQueryIntentActivities.get(0).getComponentInfo().getComponentName();
            }
            str2 = "Failed to resolve an intent: " + intent;
        }
        Log.i("CAR.L.CLUSTER", str2);
        return null;
    }

    private ComponentName getNavigationComponentByOwner(ContextOwner contextOwner) {
        Iterator<String> it = contextOwner.mPackageNames.iterator();
        while (it.hasNext()) {
            ComponentName componentFromPackage = getComponentFromPackage(it.next());
            if (componentFromPackage != null) {
                if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                    Log.d("CAR.L.CLUSTER", "Found component: " + componentFromPackage);
                }
                return componentFromPackage;
            }
        }
        return null;
    }

    private ContextOwner getNavigationContextOwner() {
        ContextOwner contextOwner;
        synchronized (this.mLock) {
            contextOwner = this.mNavContextOwner;
        }
        return contextOwner;
    }

    static /* synthetic */ void lambda$runAndWaitResult$0(AtomicReference atomicReference, Supplier supplier, CountDownLatch countDownLatch) {
        atomicReference.set(supplier.get());
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <E> E runAndWaitResult(final Supplier<E> supplier) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        this.mUiHandler.post(new Runnable() { // from class: android.car.cluster.renderer.h
            @Override // java.lang.Runnable
            public final void run() {
                InstrumentClusterRenderingService.lambda$runAndWaitResult$0(atomicReference, supplier, countDownLatch);
            }
        });
        try {
            countDownLatch.await();
            return (E) atomicReference.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigationActivity() {
        ClusterActivityState clusterActivityState;
        ContextOwner navigationContextOwner = getNavigationContextOwner();
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", String.format("updateNavigationActivity (mActivityOptions: %s, mActivityState: %s, mNavContextOwnerUid: %s)", this.mActivityOptions, this.mActivityState, navigationContextOwner));
        }
        if (navigationContextOwner == null || navigationContextOwner.mUid == 0 || this.mActivityOptions == null || (clusterActivityState = this.mActivityState) == null || !clusterActivityState.isVisible()) {
            if (this.mNavigationComponent != null) {
                this.mNavigationComponent = null;
                onNavigationComponentReleased();
                return;
            }
            return;
        }
        ComponentName navigationComponentByOwner = getNavigationComponentByOwner(navigationContextOwner);
        if (Objects.equals(this.mNavigationComponent, navigationComponentByOwner)) {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "Already launched component: " + navigationComponentByOwner);
                return;
            }
            return;
        }
        if (navigationComponentByOwner == null) {
            if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                Log.d("CAR.L.CLUSTER", "No component found for owner: " + navigationContextOwner);
                return;
            }
            return;
        }
        if (startNavigationActivity(navigationComponentByOwner)) {
            this.mNavigationComponent = navigationComponentByOwner;
            onNavigationComponentLaunched();
        } else if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", "Unable to launch component: " + navigationComponentByOwner);
        }
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.mLock) {
            printWriter.println("**" + getClass().getSimpleName() + "**");
            StringBuilder sb = new StringBuilder();
            sb.append("renderer binder: ");
            sb.append(this.mRendererBinder);
            printWriter.println(sb.toString());
            if (this.mRendererBinder != null) {
                printWriter.println("navigation renderer: " + this.mRendererBinder.mNavigationRenderer);
            }
            printWriter.println("navigation focus owner: " + getNavigationContextOwner());
            printWriter.println("activity options: " + this.mActivityOptions);
            printWriter.println("activity state: " + this.mActivityState);
            printWriter.println("current nav component: " + this.mNavigationComponent);
            printWriter.println("current nav packages: " + getNavigationContextOwner().mPackageNames);
            printWriter.println("mInstrumentClusterHelper" + this.mInstrumentClusterHelper);
        }
    }

    @Deprecated
    public Bitmap getBitmap(Uri uri) {
        try {
        } catch (IOException e) {
            Log.e("CAR.L.CLUSTER", "Unable to fetch uri: " + uri, e);
        }
        if (uri.getQueryParameter(BITMAP_QUERY_WIDTH).isEmpty() || uri.getQueryParameter(BITMAP_QUERY_HEIGHT).isEmpty()) {
            throw new IllegalArgumentException("Uri must have 'w' and 'h' query parameters");
        }
        ContextOwner navigationContextOwner = getNavigationContextOwner();
        if (navigationContextOwner == null) {
            Log.e("CAR.L.CLUSTER", "No context owner available while fetching: " + uri);
            return null;
        }
        String host = uri.getHost();
        if (!navigationContextOwner.mAuthorities.contains(host)) {
            Log.e("CAR.L.CLUSTER", "Uri points to an authority not handled by the current context owner: " + uri + " (valid authorities: " + navigationContextOwner.mAuthorities + ")");
            return null;
        }
        int userId = UserHandle.getUserId(navigationContextOwner.mUid);
        Uri uriBuild = uri.buildUpon().encodedAuthority(userId + "@" + host).build();
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", "Requesting bitmap: " + uri);
        }
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContentResolver().openFileDescriptor(uriBuild, "r");
        try {
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                parcelFileDescriptorOpenFileDescriptor.close();
                return bitmapDecodeFileDescriptor;
            }
            Log.e("CAR.L.CLUSTER", "Failed to create pipe for uri string: " + uri);
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                parcelFileDescriptorOpenFileDescriptor.close();
            }
            return null;
        } finally {
        }
    }

    public Bitmap getBitmap(Uri uri, int i, int i2) {
        return getBitmap(uri, i, i2, 1.0f);
    }

    public Bitmap getBitmap(Uri uri, int i, int i2, float f) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Width and height must be > 0");
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("offLanesAlpha must be between [0, 1]");
        }
        try {
            ContextOwner navigationContextOwner = getNavigationContextOwner();
            if (navigationContextOwner == null) {
                Log.e("CAR.L.CLUSTER", "No context owner available while fetching: " + uri);
                return null;
            }
            uri = uri.buildUpon().appendQueryParameter(BITMAP_QUERY_WIDTH, String.valueOf(i)).appendQueryParameter(BITMAP_QUERY_HEIGHT, String.valueOf(i2)).appendQueryParameter(BITMAP_QUERY_OFFLANESALPHA, String.valueOf(f)).build();
            String host = uri.getHost();
            if (!navigationContextOwner.mAuthorities.contains(host)) {
                Log.e("CAR.L.CLUSTER", "Uri points to an authority not handled by the current context owner: " + uri + " (valid authorities: " + navigationContextOwner.mAuthorities + ")");
                return null;
            }
            int userId = UserHandle.getUserId(navigationContextOwner.mUid);
            Uri uriBuild = uri.buildUpon().encodedAuthority(userId + "@" + host).build();
            Bitmap bitmapCreateScaledBitmap = this.mCache.get(uri.toString());
            if (bitmapCreateScaledBitmap == null) {
                if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
                    Log.d("CAR.L.CLUSTER", "Requesting bitmap: " + uri);
                }
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContentResolver().openFileDescriptor(uriBuild, "r");
                try {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        Bitmap bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return bitmapDecodeFileDescriptor;
                    }
                    Log.e("CAR.L.CLUSTER", "Failed to create pipe for uri string: " + uri);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    if (bitmapCreateScaledBitmap.getWidth() != i || bitmapCreateScaledBitmap.getHeight() != i2) {
                        bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, i, i2, true);
                    }
                    this.mCache.put(uri.toString(), bitmapCreateScaledBitmap);
                } finally {
                }
            }
            return bitmapCreateScaledBitmap;
        } catch (IOException e) {
            Log.e("CAR.L.CLUSTER", "Unable to fetch uri: " + uri, e);
            return null;
        }
    }

    public abstract NavigationRenderer getNavigationRenderer();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", "onBind, intent: " + intent);
        }
        Bundle bundleExtra = intent.getBundleExtra(EXTRA_BUNDLE_KEY_FOR_INSTRUMENT_CLUSTER_HELPER);
        IBinder binder = bundleExtra != null ? bundleExtra.getBinder(EXTRA_BUNDLE_KEY_FOR_INSTRUMENT_CLUSTER_HELPER) : null;
        if (binder == null) {
            Log.wtf("CAR.L.CLUSTER", "IInstrumentClusterHelper not passed through binder");
        } else {
            synchronized (this.mLock) {
                this.mInstrumentClusterHelper = IInstrumentClusterHelper.Stub.asInterface(binder);
            }
        }
        if (this.mRendererBinder == null) {
            this.mRendererBinder = new RendererBinder(getNavigationRenderer());
        }
        return this.mRendererBinder;
    }

    public void onKeyEvent(KeyEvent keyEvent) {
    }

    public void onNavigationComponentLaunched() {
    }

    public void onNavigationComponentReleased() {
    }

    public void setClusterActivityLaunchOptions(ActivityOptions activityOptions) {
        this.mActivityOptions = activityOptions;
        updateNavigationActivity();
    }

    @Deprecated
    public void setClusterActivityLaunchOptions(String str, ActivityOptions activityOptions) {
        setClusterActivityLaunchOptions(activityOptions);
    }

    public void setClusterActivityState(ClusterActivityState clusterActivityState) {
        this.mActivityState = clusterActivityState;
        updateNavigationActivity();
    }

    @Deprecated
    public void setClusterActivityState(String str, Bundle bundle) {
        setClusterActivityState(ClusterActivityState.fromBundle(bundle));
    }

    public boolean startFixedActivityModeForDisplayAndUser(Intent intent, ActivityOptions activityOptions, int i) {
        IInstrumentClusterHelper clusterHelper = getClusterHelper();
        if (clusterHelper == null) {
            return false;
        }
        if (this.mActivityState != null && intent.getBundleExtra("android.car.cluster.ClusterActivityState") == null) {
            intent = new Intent(intent).putExtra("android.car.cluster.ClusterActivityState", this.mActivityState.toBundle());
        }
        try {
            return clusterHelper.startFixedActivityModeForDisplayAndUser(intent, activityOptions.toBundle(), i);
        } catch (RemoteException e) {
            Log.w("Remote exception from car service", e);
            return false;
        }
    }

    protected boolean startNavigationActivity(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("android.car.cluster.ClusterActivityState", this.mActivityState.toBundle());
        intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
        try {
            startActivityAsUser(intent, this.mActivityOptions.toBundle(), UserHandle.CURRENT);
            ActivityOptions activityOptions = this.mActivityOptions;
            Log.i("CAR.L.CLUSTER", String.format("Activity launched: %s (options: %s, displayId: %d)", activityOptions, intent, Integer.valueOf(activityOptions.getLaunchDisplayId())));
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.w("CAR.L.CLUSTER", "Unable to find activity for intent: " + intent);
            return false;
        } catch (RuntimeException e) {
            Log.e("CAR.L.CLUSTER", "Error trying to launch intent: " + intent + ". Ignored", e);
            return false;
        }
    }

    public void stopFixedActivityMode(int i) {
        IInstrumentClusterHelper clusterHelper = getClusterHelper();
        if (clusterHelper == null) {
            return;
        }
        try {
            clusterHelper.stopFixedActivityMode(i);
        } catch (RemoteException e) {
            Log.w("Remote exception from car service, displayId:" + i, e);
        }
    }
}
