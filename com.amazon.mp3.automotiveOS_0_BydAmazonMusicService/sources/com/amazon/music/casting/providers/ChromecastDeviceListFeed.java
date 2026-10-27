package com.amazon.music.casting.providers;

import android.content.Context;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.amazon.music.chromecast.ChromecastController;
import com.google.android.gms.cast.CastMediaControlIntent;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes4.dex */
public class ChromecastDeviceListFeed {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ChromecastDeviceListFeed.class);
    private final MediaRouter.Callback callback;
    private final MediaRouteSelector mediaRouteSelector;
    private final MediaRouter mediaRouter;
    private final PublishSubject<List<MediaRouter.RouteInfo>> subject = PublishSubject.create();

    public ChromecastDeviceListFeed(Context context) {
        Validate.notNull(context, "context can't be null", new Object[0]);
        String receiverApplicationId = ChromecastController.getInstance().getReceiverApplicationId();
        this.mediaRouter = MediaRouter.getInstance(context);
        if (receiverApplicationId != null) {
            this.mediaRouteSelector = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(receiverApplicationId)).build();
        } else {
            this.mediaRouteSelector = new MediaRouteSelector.Builder().build();
        }
        this.callback = new MediaRouter.Callback() { // from class: com.amazon.music.casting.providers.ChromecastDeviceListFeed.1
            @Override // androidx.mediarouter.media.MediaRouter.Callback
            public void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                ChromecastDeviceListFeed.this.subject.onNext(ChromecastDeviceListFeed.this.getDeviceList());
            }

            @Override // androidx.mediarouter.media.MediaRouter.Callback
            public void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                ChromecastDeviceListFeed.this.subject.onNext(ChromecastDeviceListFeed.this.getDeviceList());
            }
        };
    }

    public Observable getFeed() {
        return this.subject;
    }

    public void start() {
        this.mediaRouter.addCallback(this.mediaRouteSelector, this.callback, 1);
        this.subject.onNext(getDeviceList());
    }

    public void stop() {
        this.mediaRouter.removeCallback(this.callback);
    }

    public List<MediaRouter.RouteInfo> getDeviceList() {
        ArrayList arrayList = new ArrayList();
        for (MediaRouter.RouteInfo routeInfo : this.mediaRouter.getRoutes()) {
            if (!routeInfo.isDefault() && routeInfo.matchesSelector(this.mediaRouteSelector)) {
                arrayList.add(routeInfo);
            }
        }
        return arrayList;
    }
}
