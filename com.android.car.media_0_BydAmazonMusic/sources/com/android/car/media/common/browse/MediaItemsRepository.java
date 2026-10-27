package com.android.car.media.common.browse;

import android.app.Application;
import android.car.utils.Logs;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.media.common.source.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import spi.b0;
import spi.b7;
import spi.d0;
import spi.d4;
import spi.e6;
import spi.n7;

/* JADX INFO: loaded from: classes.dex */
public final class MediaItemsRepository {
    public static MediaItemsRepository[] k = new MediaItemsRepository[2];
    public a.c a;
    public final MediaItemsLiveData d;
    public final MediaItemsLiveData e;
    public String f;
    public MediaBrowserCompat.SearchCallback g;
    public final Map<b7, e> b = new HashMap();
    public final MutableLiveData<a.c> c = e6.a(null);
    public String h = "";
    public final a i = new a();
    public final b j = new b();

    public static class MediaItemsLiveData extends LiveData<d4<List<MediaItemMetadata>>> {
        public static final /* synthetic */ int a = 0;

        public /* synthetic */ MediaItemsLiveData(a aVar) {
            this();
        }

        public static void a(MediaItemsLiveData mediaItemsLiveData, List list, List list2) {
            Objects.requireNonNull(mediaItemsLiveData);
            mediaItemsLiveData.setValue(d4.a(list, list2));
        }

        public /* synthetic */ MediaItemsLiveData(boolean z, a aVar) {
            this(z);
        }

        private MediaItemsLiveData() {
            this(true);
        }

        private MediaItemsLiveData(boolean z) {
            if (z) {
                setValue(new d4(true, null));
            } else {
                setValue(null);
            }
        }
    }

    public class b extends MediaBrowserCompat.SearchCallback {
        public b() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
        public final void onError(@NonNull String str, Bundle bundle) {
            String str2;
            super.onError(str, bundle);
            MediaItemsRepository mediaItemsRepository = MediaItemsRepository.this;
            if (mediaItemsRepository.g != null && (str2 = mediaItemsRepository.h) != null) {
                if (Objects.equals(str2, str)) {
                    MediaItemsRepository.this.g.onError(str, bundle);
                }
                MediaItemsRepository.this.g = null;
            }
            if (Objects.equals(MediaItemsRepository.this.f, str)) {
                MediaItemsLiveData.a(MediaItemsRepository.this.e, null, null);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
        public final void onSearchResult(@NonNull String str, Bundle bundle, @NonNull List<MediaBrowserCompat.MediaItem> list) {
            String str2;
            super.onSearchResult(str, bundle, list);
            MediaItemsRepository mediaItemsRepository = MediaItemsRepository.this;
            if (mediaItemsRepository.g != null && (str2 = mediaItemsRepository.h) != null) {
                if (Objects.equals(str2, str)) {
                    MediaItemsRepository.this.g.onSearchResult(str, bundle, list);
                }
                MediaItemsRepository.this.g = null;
            }
            if (Objects.equals(MediaItemsRepository.this.f, str)) {
                StringBuilder sb = new StringBuilder("Manual SearchCallback: query = ");
                sb.append(str);
                sb.append(" size = ");
                sb.append(list.size());
                sb.append(" thread = ");
                sb.append(Thread.currentThread().getName());
                sb.append("\n");
                for (MediaBrowserCompat.MediaItem mediaItem : list) {
                    sb.append("title = ");
                    sb.append(mediaItem.getDescription().getTitle());
                    sb.append(" mediaId = ");
                    sb.append(mediaItem.getMediaId());
                    sb.append("\n");
                }
                Logs.d("amazon_music_search", sb.toString());
                MediaItemsLiveData.a(MediaItemsRepository.this.e, null, (List) list.stream().filter(n7.h).map(d0.h).collect(Collectors.toList()));
            }
        }
    }

    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[a.e.values().length];
            a = iArr;
            try {
                iArr[a.e.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.e.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.e.DISCONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[a.e.REJECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[a.e.SUSPENDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static class d {
        public final MediaItemsLiveData a = new MediaItemsLiveData((a) null);
        public List<MediaItemMetadata> b = Collections.emptyList();

        public d(String str) {
        }
    }

    @VisibleForTesting
    public MediaItemsRepository(LiveData<a.c> liveData) {
        a aVar = null;
        this.d = new MediaItemsLiveData(aVar);
        this.e = new MediaItemsLiveData(false, aVar);
        liveData.observeForever(new b0(this, 4));
    }

    public static MediaItemsRepository a(@NonNull Application application, int i) {
        MediaItemsRepository[] mediaItemsRepositoryArr = k;
        if (mediaItemsRepositoryArr[i] == null) {
            mediaItemsRepositoryArr[i] = new MediaItemsRepository(MediaSourceViewModel.a(application, i).d);
        }
        return mediaItemsRepositoryArr[i];
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<spi.b7, com.android.car.media.common.browse.MediaItemsRepository$e>] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.HashMap, java.util.Map<spi.b7, com.android.car.media.common.browse.MediaItemsRepository$e>] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final e b() {
        ?? r0 = this.b;
        a.c cVar = this.a;
        byte b2 = 0;
        e eVar = (e) r0.get(cVar != null ? cVar.a : null);
        if (eVar == null) {
            eVar = new e(b2 == true ? 1 : 0);
            ?? r1 = this.b;
            a.c cVar2 = this.a;
            r1.put(cVar2 != null ? cVar2.a : null, eVar);
        }
        return eVar;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.HashMap, java.util.Map<java.lang.String, com.android.car.media.common.browse.MediaItemsRepository$d>] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.HashMap, java.util.Map<java.lang.String, com.android.car.media.common.browse.MediaItemsRepository$d>] */
    public final MediaItemsLiveData c(String str) {
        Logs.d("MediaItemsRepository", "getMediaChildren: nodeId: " + str);
        e eVarB = b();
        d dVar = (d) eVarB.b.get(str);
        if (dVar == null) {
            dVar = new d(str);
            eVarB.b.put(str, dVar);
        }
        this.a.b.unsubscribe(str);
        this.a.b.subscribe(str, this.i);
        return dVar.a;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<java.lang.String, com.android.car.media.common.browse.MediaItemsRepository$d>] */
    public final void d(@NonNull String str, @Nullable List<MediaItemMetadata> list) {
        e eVarB = b();
        d dVar = (d) eVarB.b.get(str);
        if (dVar == null) {
            Logs.w("MediaItemsRepository", "Browse parent not in the cache: " + str);
            return;
        }
        List<MediaItemMetadata> list2 = dVar.b;
        dVar.b = list;
        MediaItemsLiveData.a(dVar.a, list2, list);
        if (Objects.equals(str, eVarB.a)) {
            MediaItemsLiveData.a(this.d, list2, list);
        }
    }

    public static class e {
        public String a;
        public Map<String, d> b;

        private e() {
            this.b = new HashMap();
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public class a extends MediaBrowserCompat.SubscriptionCallback {
        public a() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public final void onChildrenLoaded(@NonNull String str, @NonNull List<MediaBrowserCompat.MediaItem> list) {
            Logs.d("MediaItemsRepository", "onChildrenLoaded_0: " + str);
            MediaItemsRepository.this.d(str, (List) list.stream().filter(n7.g).map(d0.g).collect(Collectors.toList()));
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public final void onError(@NonNull String str) {
            Logs.d("MediaItemsRepository", "onError_1: " + str);
            MediaItemsRepository.this.d(str, null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public final void onError(@NonNull String str, @NonNull Bundle bundle) {
            Logs.d("MediaItemsRepository", "onError_2: " + str);
            onError(str);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public final void onChildrenLoaded(@NonNull String str, @NonNull List<MediaBrowserCompat.MediaItem> list, @NonNull Bundle bundle) {
            Logs.d("MediaItemsRepository", "onChildrenLoaded_1: " + str);
            onChildrenLoaded(str, list);
        }
    }
}
