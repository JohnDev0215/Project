package com.vivid.spotify.queue;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import com.vivid.spotify.common.base.BaseViewModel;
import i3.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QueueViewModel extends BaseViewModel {
    public static final String TAG = "QueueViewModel";
    private final MediaControllerCompat.a callback;
    private final m mediaPlayController;
    public androidx.databinding.k<k> queueItems;

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void f(List<MediaSessionCompat.QueueItem> list) {
            QueueViewModel.this.handleQueue(list);
        }
    }

    public QueueViewModel(Application application, m mVar) {
        super(application);
        this.queueItems = new androidx.databinding.k<>();
        a aVar = new a();
        this.callback = aVar;
        this.mediaPlayController = mVar;
        mVar.h(aVar);
    }

    private k getMediaItemsByTitle(String str, List<k> list) {
        if (str != null && list != null && !list.isEmpty()) {
            for (k kVar : list) {
                if (str.equals(kVar.b())) {
                    return kVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQueue(List<MediaSessionCompat.QueueItem> list) {
        h3.i.b(TAG).b("queue size is:" + list.size(), new Object[0]);
        ArrayList arrayList = new ArrayList();
        for (MediaSessionCompat.QueueItem queueItem : list) {
            Bundle bundleC = queueItem.h().c();
            if (bundleC != null) {
                String string = bundleC.getString("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT", "");
                if (getMediaItemsByTitle(string, arrayList) == null && !TextUtils.isEmpty(string)) {
                    arrayList.add(new k(string));
                }
                arrayList.add(new k(queueItem));
            }
        }
        this.queueItems.clear();
        this.queueItems.addAll(arrayList);
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, androidx.lifecycle.a0
    protected void onCleared() {
        super.onCleared();
        this.mediaPlayController.M(this.callback);
    }
}
