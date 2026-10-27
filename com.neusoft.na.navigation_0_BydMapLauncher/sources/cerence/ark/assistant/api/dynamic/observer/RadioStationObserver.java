package cerence.ark.assistant.api.dynamic.observer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.MediaDescription;
import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.api.dynamic.RadioStation;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RadioStationObserver extends BaseObserver {
    private static final String PROGRAMS_ID = "programs_id";
    private static final String TAG = "RadioStationObserver";
    protected Handler mHandler;
    protected MediaBrowser mMediaBrowser;
    protected String mRootId;
    protected volatile boolean mDeactivated = false;
    protected final Map<String, List<RadioStation>> mSubscriptMap = new HashMap();
    private final MediaBrowser.SubscriptionCallback mRootCallback = new MediaBrowser.SubscriptionCallback() { // from class: cerence.ark.assistant.api.dynamic.observer.RadioStationObserver.1
        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
            super.onChildrenLoaded(str, list);
            Logger.info(RadioStationObserver.TAG, Utils.format("root: onChildrenLoaded: parentId = %s", str));
            RadioStationObserver.this.processRootMediaItem(list);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
            super.onChildrenLoaded(str, list, bundle);
            Logger.info(RadioStationObserver.TAG, Utils.format("root: onChildrenLoaded: parentId = %s, options = %s", str, bundle));
            RadioStationObserver.this.processRootMediaItem(list);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str) {
            super.onError(str);
            Logger.error(RadioStationObserver.TAG, Utils.format("root: onError: parentId = %s", str));
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            super.onError(str, bundle);
            Logger.error(RadioStationObserver.TAG, Utils.format("root: onError: parentId = %s, options = %s", str, bundle));
        }
    };
    private final MediaBrowser.SubscriptionCallback mChildCallback = new MediaBrowser.SubscriptionCallback() { // from class: cerence.ark.assistant.api.dynamic.observer.RadioStationObserver.2
        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
            super.onChildrenLoaded(str, list);
            Logger.info(RadioStationObserver.TAG, Utils.format("onChildrenLoaded: parentId = %s", str));
            RadioStationObserver.this.processChildMediaItem(str, list);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
            super.onChildrenLoaded(str, list, bundle);
            Logger.info(RadioStationObserver.TAG, Utils.format("onChildrenLoaded: parentId = %s, options = %s", str, bundle));
            RadioStationObserver.this.processChildMediaItem(str, list);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str) {
            super.onError(str);
            Logger.error(RadioStationObserver.TAG, Utils.format("onError: parentId = %s", str));
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            super.onError(str, bundle);
            Logger.error(RadioStationObserver.TAG, Utils.format("onError: parentId = %s, options = %s", str, bundle));
        }
    };

    protected void onConnected() {
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        HandlerThread handlerThread = new HandlerThread("ca_radio_station");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.mHandler = handler;
        handler.post(new Runnable() { // from class: cerence.ark.assistant.api.dynamic.observer.RadioStationObserver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m55x6cb8a729();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$activate$0$cerence-ark-assistant-api-dynamic-observer-RadioStationObserver, reason: not valid java name */
    /* synthetic */ void m55x6cb8a729() {
        String str = TAG;
        Logger.info(str, "activate: DCC_STATION");
        ComponentName componentNameFindBroadcastRadio = findBroadcastRadio();
        if (componentNameFindBroadcastRadio == null) {
            Logger.error(str, "did not found broadcastRadio componentName");
            return;
        }
        Logger.info(str, "found broadcastRadio: " + componentNameFindBroadcastRadio);
        this.mDeactivated = false;
        MediaBrowser mediaBrowser = new MediaBrowser(Utils.getContext(), componentNameFindBroadcastRadio, new MediaBrowser.ConnectionCallback() { // from class: cerence.ark.assistant.api.dynamic.observer.RadioStationObserver.3
            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnected() {
                super.onConnected();
                if (RadioStationObserver.this.mDeactivated) {
                    Logger.info(RadioStationObserver.TAG, "onConnected: RadioStationObserver has been deactivated");
                    return;
                }
                Logger.info(RadioStationObserver.TAG, "onConnected");
                RadioStationObserver.this.onConnected();
                RadioStationObserver radioStationObserver = RadioStationObserver.this;
                radioStationObserver.mRootId = radioStationObserver.mMediaBrowser.getRoot();
                if (!TextUtils.isEmpty(RadioStationObserver.this.mRootId)) {
                    RadioStationObserver.this.mMediaBrowser.subscribe(RadioStationObserver.this.mRootId, Bundle.EMPTY, RadioStationObserver.this.mRootCallback);
                } else {
                    Logger.error(RadioStationObserver.TAG, "onConnected: rootId is empty");
                }
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionSuspended() {
                super.onConnectionSuspended();
                Logger.error(RadioStationObserver.TAG, "onConnectionSuspended");
            }

            @Override // android.media.browse.MediaBrowser.ConnectionCallback
            public void onConnectionFailed() {
                super.onConnectionFailed();
                Logger.error(RadioStationObserver.TAG, "onConnectionFailed");
            }
        }, null);
        this.mMediaBrowser = mediaBrowser;
        try {
            mediaBrowser.connect();
        } catch (Exception e) {
            Logger.error(TAG, "registerObserver: mediaBrowser connect exception", e);
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        this.mDeactivated = true;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: cerence.ark.assistant.api.dynamic.observer.RadioStationObserver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m56x2acb6b2b();
                }
            });
        } else {
            Logger.error(TAG, "deactivate: handler is null");
        }
    }

    /* JADX INFO: renamed from: lambda$deactivate$1$cerence-ark-assistant-api-dynamic-observer-RadioStationObserver, reason: not valid java name */
    /* synthetic */ void m56x2acb6b2b() {
        Logger.info(TAG, "deactivate: DCC_STATION");
        synchronized (this) {
            for (String str : this.mSubscriptMap.keySet()) {
                if (this.mMediaBrowser != null && !TextUtils.isEmpty(str)) {
                    this.mMediaBrowser.unsubscribe(str);
                }
            }
            this.mSubscriptMap.clear();
        }
        if (this.mMediaBrowser != null) {
            if (!TextUtils.isEmpty(this.mRootId)) {
                this.mMediaBrowser.unsubscribe(this.mRootId);
            } else {
                Logger.warn(TAG, "deactivate: rootId is empty");
            }
            this.mMediaBrowser.disconnect();
        }
        this.mRootId = null;
        this.mHandler.getLooper().quit();
    }

    protected void processRootMediaItem(List<MediaBrowser.MediaItem> list) {
        for (int i = 0; i < list.size(); i++) {
            MediaBrowser.MediaItem mediaItem = list.get(i);
            if (mediaItem == null) {
                Logger.error(TAG, "mediaItem is null, index = " + i);
            } else {
                String mediaId = mediaItem.getMediaId();
                String str = TAG;
                Logger.info(str, "processRootMediaItem: parentId = " + mediaId);
                if (!TextUtils.isEmpty(mediaId)) {
                    synchronized (this) {
                        this.mSubscriptMap.put(mediaId, new ArrayList());
                    }
                    this.mMediaBrowser.subscribe(mediaId, this.mChildCallback);
                } else {
                    Logger.error(str, "processRootMediaItem: mediaId is empty");
                }
            }
        }
    }

    protected synchronized void processChildMediaItem(String str, List<MediaBrowser.MediaItem> list) {
        if (list.isEmpty()) {
            Logger.error(TAG, "children list is empty");
            return;
        }
        if (PROGRAMS_ID.equals(str)) {
            ArrayList arrayList = new ArrayList();
            int i = 1;
            int i2 = 0;
            while (i2 < list.size()) {
                if (this.mDeactivated) {
                    return;
                }
                MediaBrowser.MediaItem mediaItem = list.get(i2);
                if (mediaItem == null) {
                    Logger.error(TAG, "mediaItem is null, index = " + i2);
                } else {
                    MediaDescription description = mediaItem.getDescription();
                    if (description == null) {
                        Logger.error(TAG, "description is null, index = " + i2);
                    } else if (TextUtils.isEmpty(description.getTitle())) {
                        Logger.error(TAG, "title is empty, index = " + i2);
                    } else {
                        arrayList.add(RadioStation.builder().setId(i).setName(description.getTitle().toString()).build());
                    }
                }
                i2++;
                i++;
            }
            this.mSubscriptMap.put(str, arrayList);
            onContentChanged(ContentType.RADIO_STATION);
        }
    }

    public synchronized List<RadioStation> getRadioStations() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<List<RadioStation>> it = this.mSubscriptMap.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next());
        }
        return arrayList;
    }

    protected ComponentName findBroadcastRadio() {
        List<ResolveInfo> listQueryIntentServices;
        PackageManager packageManager = Utils.getContext().getPackageManager();
        Intent intent = new Intent("android.car.intent.action.PLAY_BROADCASTRADIO");
        if (Build.VERSION.SDK_INT >= 33) {
            listQueryIntentServices = packageManager.queryIntentServices(intent, PackageManager.ResolveInfoFlags.of(0L));
        } else {
            listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        }
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            Logger.error(TAG, "resolveInfoList is empty");
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
        if (resolveInfo == null) {
            Logger.error(TAG, "resolveInfo is null");
            return null;
        }
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        if (serviceInfo == null) {
            Logger.error(TAG, "serviceInfo is null");
            return null;
        }
        return new ComponentName(serviceInfo.packageName, serviceInfo.name);
    }
}
