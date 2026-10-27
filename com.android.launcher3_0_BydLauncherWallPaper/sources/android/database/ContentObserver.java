package android.database;

import android.app.compat.CompatChanges;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContentObserver {
    private static final long ADD_CONTENT_OBSERVER_FLAGS = 150939131;
    Handler mHandler;
    private final Object mLock = new Object();
    private Transport mTransport;

    public boolean deliverSelfNotifications() {
        return false;
    }

    public void onChange(boolean z) {
    }

    public void onChange(boolean z, String str, int i) {
    }

    public ContentObserver(Handler handler) {
        this.mHandler = handler;
    }

    public IContentObserver getContentObserver() {
        Transport transport;
        synchronized (this.mLock) {
            if (this.mTransport == null) {
                this.mTransport = new Transport(this);
            }
            transport = this.mTransport;
        }
        return transport;
    }

    public IContentObserver releaseContentObserver() {
        Transport transport;
        synchronized (this.mLock) {
            transport = this.mTransport;
            if (transport != null) {
                transport.releaseContentObserver();
                this.mTransport = null;
            }
        }
        return transport;
    }

    public void onChange(boolean z, Uri uri) {
        onChange(z);
    }

    public void onChange(boolean z, Uri uri, String str, int i) {
        onChange(z, str, i);
    }

    public void onChange(boolean z, Uri uri, int i) {
        onChange(z, uri);
    }

    public void onChange(boolean z, Uri uri, int i, String str, int i2) {
        onChange(z, uri, str, i2);
    }

    public void onChange(boolean z, Collection<Uri> collection, int i) {
        Iterator<Uri> it = collection.iterator();
        while (it.hasNext()) {
            onChange(z, it.next(), i);
        }
    }

    public void onChange(boolean z, Collection<Uri> collection, int i, String str, int i2) {
        Iterator<Uri> it = collection.iterator();
        while (it.hasNext()) {
            onChange(z, it.next(), i, str, i2);
        }
    }

    public void onChange(boolean z, Collection<Uri> collection, int i, int i2) {
        if (!CompatChanges.isChangeEnabled(ADD_CONTENT_OBSERVER_FLAGS) || Process.myUid() == 1000) {
            onChange(z, collection, i2);
        } else {
            onChange(z, collection, i);
        }
    }

    /* JADX INFO: renamed from: onChange, reason: merged with bridge method [inline-methods] */
    public void lambda$dispatchChange$1$ContentObserver(boolean z, Collection<Uri> collection, int i, int i2, String str, int i3) {
        if (!CompatChanges.isChangeEnabled(ADD_CONTENT_OBSERVER_FLAGS) || Process.myUid() == 1000) {
            onChange(z, collection, i2, str, i3);
        } else {
            onChange(z, collection, i, str, i3);
        }
    }

    @Deprecated
    public final void dispatchChange(boolean z) {
        dispatchChange(z, null);
    }

    public final void dispatchChange(boolean z, Uri uri) {
        dispatchChange(z, uri, 0);
    }

    public final void dispatchChange(boolean z, Uri uri, int i) {
        dispatchChange(z, Arrays.asList(uri), i);
    }

    public final void dispatchChange(boolean z, Collection<Uri> collection, int i) {
        dispatchChange(z, collection, i, UserHandle.getCallingUserId());
    }

    public final void dispatchChange(final boolean z, final Collection<Uri> collection, final int i, final int i2) {
        Handler handler = this.mHandler;
        if (handler == null) {
            onChange(z, collection, i, i2);
        } else {
            handler.post(new Runnable() { // from class: android.database.-$$Lambda$ContentObserver$BIjdB2rlaOfumlw9NxpnbViPkII
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$dispatchChange$0$ContentObserver(z, collection, i, i2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$dispatchChange$0$ContentObserver(boolean z, Collection collection, int i, int i2) {
        onChange(z, (Collection<Uri>) collection, i, i2);
    }

    public final void dispatchChange(final boolean z, final Collection<Uri> collection, final int i, final int i2, final String str, final int i3) {
        Handler handler = this.mHandler;
        if (handler == null) {
            lambda$dispatchChange$1$ContentObserver(z, collection, i, i2, str, i3);
        } else {
            handler.post(new Runnable() { // from class: android.database.-$$Lambda$ContentObserver$tRufj058qQMu4dp9fkPnfFQDxiI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$dispatchChange$1$ContentObserver(z, collection, i, i2, str, i3);
                }
            });
        }
    }

    private static final class Transport extends IContentObserver.Stub {
        private ContentObserver mContentObserver;

        public Transport(ContentObserver contentObserver) {
            this.mContentObserver = contentObserver;
        }

        @Override // android.database.IContentObserver
        public void onChange(boolean z, Uri uri, int i) {
            onChangeEtc(z, new Uri[]{uri}, 0, i);
        }

        @Override // android.database.IContentObserver
        public void onChangeEtc(boolean z, Uri[] uriArr, int i, int i2) {
            ContentObserver contentObserver = this.mContentObserver;
            if (contentObserver != null) {
                contentObserver.dispatchChange(z, Arrays.asList(uriArr), i, i2);
            }
        }

        @Override // android.database.IContentObserver
        public void onChangeEtcAsUser(boolean z, Uri[] uriArr, int i, int i2, String str, int i3) {
            ContentObserver contentObserver = this.mContentObserver;
            if (contentObserver != null) {
                contentObserver.dispatchChange(z, Arrays.asList(uriArr), i, i2, str, i3);
            }
        }

        public void releaseContentObserver() {
            this.mContentObserver = null;
        }
    }
}
