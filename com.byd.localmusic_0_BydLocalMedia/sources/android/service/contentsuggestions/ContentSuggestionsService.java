package android.service.contentsuggestions;

import android.annotation.SystemApi;
import android.app.Service;
import android.app.contentsuggestions.ClassificationsRequest;
import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.IClassificationsCallback;
import android.app.contentsuggestions.ISelectionsCallback;
import android.app.contentsuggestions.SelectionsRequest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.GraphicBuffer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.contentsuggestions.IContentSuggestionsService;
import android.util.Log;
import android.util.Slog;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public abstract class ContentSuggestionsService extends Service {
    public static final String SERVICE_INTERFACE = "android.service.contentsuggestions.ContentSuggestionsService";
    private static final String TAG = ContentSuggestionsService.class.getSimpleName();
    private Handler mHandler;
    private final IContentSuggestionsService mInterface = new IContentSuggestionsService.Stub() { // from class: android.service.contentsuggestions.ContentSuggestionsService.1
        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void provideContextImage(int i, GraphicBuffer graphicBuffer, int i2, Bundle bundle) {
            if (bundle.containsKey(ContentSuggestionsManager.EXTRA_BITMAP) && graphicBuffer != null) {
                throw new IllegalArgumentException("Two bitmaps provided; expected one.");
            }
            Bitmap bitmapWrapHardwareBuffer = null;
            colorSpace = null;
            ColorSpace colorSpace = null;
            if (bundle.containsKey(ContentSuggestionsManager.EXTRA_BITMAP)) {
                bitmapWrapHardwareBuffer = (Bitmap) bundle.getParcelable(ContentSuggestionsManager.EXTRA_BITMAP);
            } else if (graphicBuffer != null) {
                if (i2 >= 0 && i2 < ColorSpace.Named.values().length) {
                    colorSpace = ColorSpace.get(ColorSpace.Named.values()[i2]);
                }
                bitmapWrapHardwareBuffer = Bitmap.wrapHardwareBuffer(graphicBuffer, colorSpace);
            }
            ContentSuggestionsService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new QuadConsumer() { // from class: android.service.contentsuggestions.-$$Lambda$Mv-op4AGm9iWERwfXEFnqOVKWt0
                @Override // com.android.internal.util.function.QuadConsumer
                public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
                    ((ContentSuggestionsService) obj).onProcessContextImage(((Integer) obj2).intValue(), (Bitmap) obj3, (Bundle) obj4);
                }
            }, ContentSuggestionsService.this, Integer.valueOf(i), bitmapWrapHardwareBuffer, bundle));
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void suggestContentSelections(SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) {
            Handler handler = ContentSuggestionsService.this.mHandler;
            $$Lambda$yZSFRdNS_6TrQJ8NQKXAv0kSKzk __lambda_yzsfrdns_6trqj8nqkxav0kskzk = new TriConsumer() { // from class: android.service.contentsuggestions.-$$Lambda$yZSFRdNS_6TrQJ8NQKXAv0kSKzk
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((ContentSuggestionsService) obj).onSuggestContentSelections((SelectionsRequest) obj2, (ContentSuggestionsManager.SelectionsCallback) obj3);
                }
            };
            ContentSuggestionsService contentSuggestionsService = ContentSuggestionsService.this;
            handler.sendMessage(PooledLambda.obtainMessage(__lambda_yzsfrdns_6trqj8nqkxav0kskzk, contentSuggestionsService, selectionsRequest, contentSuggestionsService.wrapSelectionsCallback(iSelectionsCallback)));
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void classifyContentSelections(ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) {
            Handler handler = ContentSuggestionsService.this.mHandler;
            $$Lambda$5oRtA6f92le979Nv8bd2We4x10 __lambda_5orta6f92le979nv8bd2we4x10 = new TriConsumer() { // from class: android.service.contentsuggestions.-$$Lambda$5oRtA6f92le979Nv8-bd2We4x10
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((ContentSuggestionsService) obj).onClassifyContentSelections((ClassificationsRequest) obj2, (ContentSuggestionsManager.ClassificationsCallback) obj3);
                }
            };
            ContentSuggestionsService contentSuggestionsService = ContentSuggestionsService.this;
            handler.sendMessage(PooledLambda.obtainMessage(__lambda_5orta6f92le979nv8bd2we4x10, contentSuggestionsService, classificationsRequest, contentSuggestionsService.wrapClassificationCallback(iClassificationsCallback)));
        }

        @Override // android.service.contentsuggestions.IContentSuggestionsService
        public void notifyInteraction(String str, Bundle bundle) {
            ContentSuggestionsService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new TriConsumer() { // from class: android.service.contentsuggestions.-$$Lambda$XFxerYS8emT_xgiGwwUrQtqnPnc
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((ContentSuggestionsService) obj).onNotifyInteraction((String) obj2, (Bundle) obj3);
                }
            }, ContentSuggestionsService.this, str, bundle));
        }
    };

    public abstract void onClassifyContentSelections(ClassificationsRequest classificationsRequest, ContentSuggestionsManager.ClassificationsCallback classificationsCallback);

    public abstract void onNotifyInteraction(String str, Bundle bundle);

    public abstract void onProcessContextImage(int i, Bitmap bitmap, Bundle bundle);

    public abstract void onSuggestContentSelections(SelectionsRequest selectionsRequest, ContentSuggestionsManager.SelectionsCallback selectionsCallback);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mHandler = new Handler(Looper.getMainLooper(), null, true);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (SERVICE_INTERFACE.equals(intent.getAction())) {
            return this.mInterface.asBinder();
        }
        Log.w(TAG, "Tried to bind to wrong intent (should be android.service.contentsuggestions.ContentSuggestionsService: " + intent);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentSuggestionsManager.SelectionsCallback wrapSelectionsCallback(final ISelectionsCallback iSelectionsCallback) {
        return new ContentSuggestionsManager.SelectionsCallback() { // from class: android.service.contentsuggestions.-$$Lambda$ContentSuggestionsService$Cq6WuwbJQLqgS0UnqLBYUMft1GM
            @Override // android.app.contentsuggestions.ContentSuggestionsManager.SelectionsCallback
            public final void onContentSelectionsAvailable(int i, List list) {
                ContentSuggestionsService.lambda$wrapSelectionsCallback$0(iSelectionsCallback, i, list);
            }
        };
    }

    static /* synthetic */ void lambda$wrapSelectionsCallback$0(ISelectionsCallback iSelectionsCallback, int i, List list) {
        try {
            iSelectionsCallback.onContentSelectionsAvailable(i, list);
        } catch (RemoteException e) {
            Slog.e(TAG, "Error sending result: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentSuggestionsManager.ClassificationsCallback wrapClassificationCallback(final IClassificationsCallback iClassificationsCallback) {
        return new ContentSuggestionsManager.ClassificationsCallback() { // from class: android.service.contentsuggestions.-$$Lambda$ContentSuggestionsService$EMLezZyRGdfK3m-N1TAvrHKUEII
            @Override // android.app.contentsuggestions.ContentSuggestionsManager.ClassificationsCallback
            public final void onContentClassificationsAvailable(int i, List list) {
                ContentSuggestionsService.lambda$wrapClassificationCallback$1(iClassificationsCallback, i, list);
            }
        };
    }

    static /* synthetic */ void lambda$wrapClassificationCallback$1(IClassificationsCallback iClassificationsCallback, int i, List list) {
        try {
            iClassificationsCallback.onContentClassificationsAvailable(i, list);
        } catch (RemoteException e) {
            Slog.e(TAG, "Error sending result: " + e);
        }
    }
}
