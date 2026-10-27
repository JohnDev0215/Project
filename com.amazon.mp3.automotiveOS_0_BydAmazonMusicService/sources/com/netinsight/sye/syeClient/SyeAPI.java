package com.netinsight.sye.syeClient;

import a.b.a.a.g.e;
import a.b.a.a.h.a;
import a.b.a.a.i.b;
import a.b.a.a.o.a;
import a.b.a.a.o.d.b.k;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.TextPaint;
import androidx.annotation.Keep;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import com.netinsight.sye.syeClient.channels.IFetchChannelsRequest;
import com.netinsight.sye.syeClient.channels.ISyeChannel;
import com.netinsight.sye.syeClient.generated.enums.SyeFetchChannelsError;
import com.netinsight.sye.syeClient.internal.IFetchChannelsRequestWrapper;
import com.netinsight.sye.syeClient.internal.IHTTPSessionFactoryWrapper;
import com.netinsight.sye.syeClient.internal.IHTTPSessionWrapper;
import com.netinsight.sye.syeClient.internal.JNISyeSystem;
import com.netinsight.sye.syeClient.internal.NativeSyeAPI;
import com.netinsight.sye.syeClient.playerListeners.IErrorListener;
import com.netinsight.sye.syeClient.playerListeners.ITeardownListener;
import com.netinsight.sye.syeClient.timeshift.ISyeThumbnailPreferences;
import com.netinsight.sye.syeClient.video.ISyeVideoLimitations;
import com.netinsight.sye.syeClient.video.SyeVideoCodecSupport;
import com.visualon.OSMPUtils.voMimeTypes;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/netinsight/sye/syeClient/SyeAPI;", "", "<init>", "()V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f725a = "SyeAPI";
    public static final b b = b.d.a();
    public static String c = SQLiteCommandFactory.UNDEFINED;
    public static Companion.a d;
    public static boolean e;
    public static AudioManager f;
    public static a g;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\u0003\u0018\u00002\u00020\u0001:\u0002IJB\t\b\u0002¢\u0006\u0004\bH\u0010\u0004J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0015\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0016\u0010\u0014J\u000f\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u001b\u0010\u0014J\u000f\u0010\u001f\u001a\u00020\u001cH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\"\u001a\u00020\u00022\u0006\u0010!\u001a\u00020 H\u0007¢\u0006\u0004\b\"\u0010#J/\u0010-\u001a\u00020,2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&2\u0006\u0010)\u001a\u00020(2\u0006\u0010+\u001a\u00020*H\u0007¢\u0006\u0004\b-\u0010.J\u0017\u00101\u001a\u00020\u00022\u0006\u00100\u001a\u00020/H\u0007¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020/H\u0007¢\u0006\u0004\b3\u00104R\u0016\u00106\u001a\u00020\u00188@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\u001aR\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u00128\u0002@\u0002X\u0082D¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010?\u001a\u00020\u001c8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010;R\u0016\u0010F\u001a\u00020E8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bF\u0010G¨\u0006K"}, d2 = {"Lcom/netinsight/sye/syeClient/SyeAPI$Companion;", "", "", "assertInitialized", "()V", "", "getSyncTimeMicros", "()J", "Landroid/content/Context;", "appContext", "Lcom/netinsight/sye/syeClient/SyeAPIConfig;", "syeAPIConfig", "setup", "(Landroid/content/Context;Lcom/netinsight/sye/syeClient/SyeAPIConfig;)V", "Lcom/netinsight/sye/syeClient/channels/IFetchChannelsRequest;", "request", "fetchChannels", "(Lcom/netinsight/sye/syeClient/channels/IFetchChannelsRequest;)V", "", "getBazingaBuildTag$syeClient_release", "()Ljava/lang/String;", "getBazingaBuildTag", "getBazingaGitVersion$syeClient_release", "getBazingaGitVersion", "", "getProtocolVersion", "()I", "getSDKVersion", "La/b/a/a/h/a;", "getDeviceInfo$syeClient_release", "()La/b/a/a/h/a;", "getDeviceInfo", "Lcom/netinsight/sye/syeClient/video/ISyeVideoLimitations;", "syeVideoLimitations", "setVideoLimitations", "(Lcom/netinsight/sye/syeClient/video/ISyeVideoLimitations;)V", "Lcom/netinsight/sye/syeClient/SyeSystem;", "syeSystem", "Lcom/netinsight/sye/syeClient/SyePlayerConfig;", "config", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/netinsight/sye/syeClient/playerListeners/IErrorListener;", "errorListener", "Lcom/netinsight/sye/syeClient/ISyePlayer;", "createPlayer", "(Lcom/netinsight/sye/syeClient/SyeSystem;Lcom/netinsight/sye/syeClient/SyePlayerConfig;Landroid/app/Activity;Lcom/netinsight/sye/syeClient/playerListeners/IErrorListener;)Lcom/netinsight/sye/syeClient/ISyePlayer;", "Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnailPreferences;", "syeThumbnailPreferences", "setThumbnailPreferences", "(Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnailPreferences;)V", "getThumbnailPreferences", "()Lcom/netinsight/sye/syeClient/timeshift/ISyeThumbnailPreferences;", "getOsVersionInt$syeClient_release", "osVersionInt", "La/b/a/a/i/b;", "Log", "La/b/a/a/i/b;", "TAG", "Ljava/lang/String;", "Landroid/media/AudioManager;", "audioManager", "Landroid/media/AudioManager;", "deviceInfo", "La/b/a/a/h/a;", "", "initialized", "Z", "sdkVersion", "Lcom/netinsight/sye/syeClient/SyeAPI$Companion$a;", "syePlayerAssociation", "Lcom/netinsight/sye/syeClient/SyeAPI$Companion$a;", "<init>", "a", "b", "syeClient_release"}, k = 1, mv = {1, 4, 0})
    public static final class Companion {

        public interface a {
            Collection<ISyePlayer> a(Activity activity);

            void a(Activity activity, ISyePlayer iSyePlayer);

            void b(Activity activity, ISyePlayer iSyePlayer);
        }

        public static final class b implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakHashMap<Activity, Collection<ISyePlayer>> f726a = new WeakHashMap<>();

            @Override // com.netinsight.sye.syeClient.SyeAPI.Companion.a
            public synchronized Collection<ISyePlayer> a(Activity activity) {
                Collection<ISyePlayer> collectionUnmodifiableCollection;
                Collection<ISyePlayer> collectionEmptyList = this.f726a.get(activity);
                if (collectionEmptyList == null) {
                    collectionEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                collectionUnmodifiableCollection = Collections.unmodifiableCollection(collectionEmptyList);
                Intrinsics.checkExpressionValueIsNotNull(collectionUnmodifiableCollection, "Collections.unmodifiable…vity[activity].orEmpty())");
                return collectionUnmodifiableCollection;
            }

            @Override // com.netinsight.sye.syeClient.SyeAPI.Companion.a
            public synchronized void a(Activity activity, ISyePlayer iSyePlayer) {
                Collection<ISyePlayer> collection = this.f726a.get(activity);
                if (collection != null) {
                    collection.remove(iSyePlayer);
                }
            }

            @Override // com.netinsight.sye.syeClient.SyeAPI.Companion.a
            public synchronized void b(Activity activity, ISyePlayer iSyePlayer) {
                Collection<ISyePlayer> hashSet = this.f726a.get(activity);
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                    this.f726a.put(activity, hashSet);
                }
                hashSet.add(iSyePlayer);
            }
        }

        public static final class c implements ITeardownListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Activity f727a;
            public final /* synthetic */ a.b.a.a.a b;

            public c(Activity activity, a.b.a.a.a aVar) {
                this.f727a = activity;
                this.b = aVar;
            }

            @Override // com.netinsight.sye.syeClient.playerListeners.ITeardownListener
            public void onTeardown() {
                a aVar = SyeAPI.d;
                if (aVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("syePlayerAssociation");
                }
                aVar.a(this.f727a, this.b);
            }
        }

        public static final class d implements IFetchChannelsRequestWrapper {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IFetchChannelsRequest f728a;

            public d(IFetchChannelsRequest iFetchChannelsRequest) {
                this.f728a = iFetchChannelsRequest;
            }

            @Override // com.netinsight.sye.syeClient.internal.IFetchChannelsRequestWrapper
            public void onAvailableChannels(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    ArrayList<a.b.a.a.k.b> arrayList = new ArrayList(jSONArray.length());
                    int i = 0;
                    int length = jSONArray.length();
                    while (true) {
                        a.b.a.a.k.b bVar = null;
                        if (i >= length) {
                            break;
                        }
                        String string = jSONArray.getJSONObject(i).toString();
                        Intrinsics.checkExpressionValueIsNotNull(string, "array.getJSONObject(i).toString()");
                        if (!Intrinsics.areEqual(string, "null")) {
                            try {
                                bVar = new a.b.a.a.k.b(string);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (bVar != null) {
                            arrayList.add(bVar);
                        }
                        i++;
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (a.b.a.a.k.b bVar2 : arrayList) {
                        a.b.a.a.f.a aVar = bVar2 != null ? new a.b.a.a.f.a(bVar2) : null;
                        if (aVar != null) {
                            arrayList2.add(aVar);
                        }
                    }
                    List list = CollectionsKt___CollectionsKt.toList(arrayList2);
                    IFetchChannelsRequest iFetchChannelsRequest = this.f728a;
                    List<ISyeChannel> listUnmodifiableList = Collections.unmodifiableList(list);
                    Intrinsics.checkExpressionValueIsNotNull(listUnmodifiableList, "Collections.unmodifiableList(channels)");
                    iFetchChannelsRequest.onAvailableChannels(listUnmodifiableList);
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            }

            @Override // com.netinsight.sye.syeClient.internal.IFetchChannelsRequestWrapper
            public void onError(int i, String str) {
                this.f728a.onError(SyeFetchChannelsError.INSTANCE.a(i), str);
            }
        }

        public static final class e implements IHTTPSessionFactoryWrapper {
            @Override // com.netinsight.sye.syeClient.internal.IHTTPSessionFactoryWrapper
            public IHTTPSessionWrapper createHttpSession(int i) {
                return new a.b.a.a.l.a(i);
            }
        }

        public static final class f implements Application.ActivityLifecycleCallbacks {
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                if (activity != null) {
                    SyeAPI.b.a("LIFECYCLE_TEST", "onActivityDestroyed " + activity.getClass().getSimpleName());
                    a aVar = SyeAPI.d;
                    if (aVar == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("syePlayerAssociation");
                    }
                    Iterator<ISyePlayer> it = aVar.a(activity).iterator();
                    while (it.hasNext()) {
                        it.next().teardown();
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                if (activity != null) {
                    SyeAPI.b.a("LIFECYCLE_TEST", "onActivityPaused " + activity.getClass().getSimpleName());
                    a aVar = SyeAPI.d;
                    if (aVar == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("syePlayerAssociation");
                    }
                    for (ISyePlayer iSyePlayer : aVar.a(activity)) {
                        if (iSyePlayer == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.netinsight.sye.syeClient.SyePlayer");
                        }
                        a.b.a.a.a aVar2 = (a.b.a.a.a) iSyePlayer;
                        if (!aVar2.p) {
                            aVar2.t = true;
                            aVar2.d.c(aVar2.c, "onActivityPaused");
                            aVar2.d.c(aVar2.c, "pausePollThread");
                            aVar2.j = true;
                            aVar2.d();
                            aVar2.b();
                            aVar2.a(new a.b.a.a.b(aVar2, "last poll"));
                        }
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                if (activity != null) {
                    SyeAPI.b.a("LIFECYCLE_TEST", "onActivityResumed " + activity.getClass().getSimpleName());
                    a aVar = SyeAPI.d;
                    if (aVar == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("syePlayerAssociation");
                    }
                    for (ISyePlayer iSyePlayer : aVar.a(activity)) {
                        if (iSyePlayer == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.netinsight.sye.syeClient.SyePlayer");
                        }
                        a.b.a.a.a aVar2 = (a.b.a.a.a) iSyePlayer;
                        if (!aVar2.p) {
                            aVar2.d.c(aVar2.c, "onActivtyResumed");
                            aVar2.c();
                        }
                    }
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                if (activity != null) {
                    SyeAPI.b.a("LIFECYCLE_TEST", "onActivityStopped " + activity.getClass().getSimpleName());
                }
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void assertInitialized() {
            if (!SyeAPI.e) {
                throw new a.b.a.a.j.a("SyeAPI was not initialized!");
            }
        }

        @JvmStatic
        public final synchronized ISyePlayer createPlayer(SyeSystem syeSystem, SyePlayerConfig config, Activity activity, IErrorListener errorListener) {
            a.b.a.a.a aVar;
            assertInitialized();
            aVar = new a.b.a.a.a(syeSystem, config, errorListener, activity);
            c cVar = new c(activity, aVar);
            a.b.a.a.i.a aVar2 = aVar.k;
            aVar2.getClass();
            aVar2.k.add(cVar);
            a aVar3 = SyeAPI.d;
            if (aVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("syePlayerAssociation");
            }
            aVar3.b(activity, aVar);
            return aVar;
        }

        @JvmStatic
        public final synchronized void fetchChannels(IFetchChannelsRequest request) {
            assertInitialized();
            NativeSyeAPI.fetchChannels(new JNISyeSystem(request.getSyeSystem()), new d(request));
        }

        public final String getBazingaBuildTag$syeClient_release() {
            assertInitialized();
            return NativeSyeAPI.loadBuildTag();
        }

        public final String getBazingaGitVersion$syeClient_release() {
            assertInitialized();
            return NativeSyeAPI.loadGitVersion();
        }

        public final a.b.a.a.h.a getDeviceInfo$syeClient_release() {
            assertInitialized();
            a.b.a.a.h.a aVar = SyeAPI.g;
            if (aVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            return aVar;
        }

        public final int getOsVersionInt$syeClient_release() {
            return SyeAPI.INSTANCE.getDeviceInfo$syeClient_release().d;
        }

        @JvmStatic
        public final int getProtocolVersion() {
            assertInitialized();
            return NativeSyeAPI.loadProtocolVersion();
        }

        @JvmStatic
        public final String getSDKVersion() {
            assertInitialized();
            return SyeAPI.c;
        }

        @JvmStatic
        public final long getSyncTimeMicros() {
            return NativeSyeAPI.getLocalTimeMicros();
        }

        @JvmStatic
        public final synchronized ISyeThumbnailPreferences getThumbnailPreferences() {
            assertInitialized();
            return NativeSyeAPI.getThumbnailPreferences();
        }

        @JvmStatic
        public final synchronized void setThumbnailPreferences(ISyeThumbnailPreferences syeThumbnailPreferences) {
            assertInitialized();
            NativeSyeAPI.setThumbnailPreferences(syeThumbnailPreferences);
        }

        @JvmStatic
        public final synchronized void setVideoLimitations(ISyeVideoLimitations syeVideoLimitations) {
            assertInitialized();
            NativeSyeAPI.setVideoLimitations(syeVideoLimitations);
        }

        @JvmStatic
        public final synchronized void setup(Context appContext, SyeAPIConfig syeAPIConfig) {
            if (SyeAPI.e) {
                SyeAPI.b.e(SyeAPI.f725a, "SyeAPI already initialized");
                return;
            }
            System.loadLibrary("syendk");
            SyeAPI.c = "2.1.15";
            SyeAPI.g = new a.b.a.a.h.a(appContext);
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
            a.b.a.a.h.a aVar = SyeAPI.g;
            if (aVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            String str = aVar.b;
            a.b.a.a.h.a aVar2 = SyeAPI.g;
            if (aVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            String str2 = aVar2.f;
            a.b.a.a.h.a aVar3 = SyeAPI.g;
            if (aVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            String str3 = aVar3.e;
            a.b.a.a.h.a aVar4 = SyeAPI.g;
            if (aVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfo");
            }
            String str4 = aVar4.c;
            String str5 = SyeAPI.c;
            e eVar = new e();
            e.b bVar = a.b.a.a.g.e.b;
            String str6 = a.b.a.a.g.e.f56a;
            String string = str6 == null || str6.length() == 0 ? null : str6;
            if (string == null) {
                TextPaint textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setSubpixelText(true);
                textPaint.setTextSize(64.0f);
                StringBuilder sb = new StringBuilder();
                sb.append(bVar.a(textPaint, a.b.a.a.g.g.b.f67a));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.b));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.c));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.d));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.e));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.f));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.g));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.h));
                sb.append(',' + bVar.a(textPaint, a.b.a.a.g.g.b.i));
                string = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "StringBuilder().apply {\n…             }.toString()");
                a.b.a.a.g.e.f56a = string;
            }
            NativeSyeAPI.initialize(str, str2, str3, str4, str5, eVar, string);
            a.C0017a c0017a = a.b.a.a.o.a.f89a;
            ArrayList<SyeVideoCodecSupport> arrayList = new ArrayList();
            a.b.a.a.o.c.c cVar = new a.b.a.a.o.c.c();
            k kVar = k.b;
            arrayList.add(c0017a.a("video/avc", kVar.a("video/avc"), new a.b.a.a.o.c.a(cVar)));
            if (kVar.a()) {
                arrayList.add(c0017a.a("video/hevc", kVar.a("video/hevc"), new a.b.a.a.o.c.d(cVar)));
            }
            for (SyeVideoCodecSupport syeVideoCodecSupport : arrayList) {
                syeVideoCodecSupport.getCodec();
                syeVideoCodecSupport.getProfile();
                syeVideoCodecSupport.getLevel();
            }
            Object[] array = arrayList.toArray(new SyeVideoCodecSupport[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            NativeSyeAPI.setVideoCapabilities((SyeVideoCodecSupport[]) array);
            Object systemService = appContext.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
            }
            SyeAPI.f = (AudioManager) systemService;
            SyeAPI.d = new b();
            ((Application) appContext).registerActivityLifecycleCallbacks(new f());
            SyeAPI.e = true;
        }
    }

    @JvmStatic
    public static final synchronized ISyePlayer createPlayer(SyeSystem syeSystem, SyePlayerConfig syePlayerConfig, Activity activity, IErrorListener iErrorListener) {
        return INSTANCE.createPlayer(syeSystem, syePlayerConfig, activity, iErrorListener);
    }

    @JvmStatic
    public static final synchronized void fetchChannels(IFetchChannelsRequest iFetchChannelsRequest) {
        INSTANCE.fetchChannels(iFetchChannelsRequest);
    }

    @JvmStatic
    public static final int getProtocolVersion() {
        return INSTANCE.getProtocolVersion();
    }

    @JvmStatic
    public static final String getSDKVersion() {
        return INSTANCE.getSDKVersion();
    }

    @JvmStatic
    public static final long getSyncTimeMicros() {
        return INSTANCE.getSyncTimeMicros();
    }

    @JvmStatic
    public static final synchronized ISyeThumbnailPreferences getThumbnailPreferences() {
        return INSTANCE.getThumbnailPreferences();
    }

    @JvmStatic
    public static final synchronized void setThumbnailPreferences(ISyeThumbnailPreferences iSyeThumbnailPreferences) {
        INSTANCE.setThumbnailPreferences(iSyeThumbnailPreferences);
    }

    @JvmStatic
    public static final synchronized void setVideoLimitations(ISyeVideoLimitations iSyeVideoLimitations) {
        INSTANCE.setVideoLimitations(iSyeVideoLimitations);
    }

    @JvmStatic
    public static final synchronized void setup(Context context, SyeAPIConfig syeAPIConfig) {
        INSTANCE.setup(context, syeAPIConfig);
    }
}
