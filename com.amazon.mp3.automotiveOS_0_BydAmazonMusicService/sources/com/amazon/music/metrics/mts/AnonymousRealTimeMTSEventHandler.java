package com.amazon.music.metrics.mts;

import com.amazon.music.metrics.event.EventHandler;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AnonymousRealTimeMTSEventHandler extends EventHandler<AnonymousRealTimeMTSEvent, Exception> {
    private static final Logger LOG = LoggerFactory.getLogger(AnonymousRealTimeMTSEventHandler.class.getSimpleName());
    private static final ExecutorService errorHandleService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.DiscardPolicy());
    final MTSEventDataProvider mDataProvider;
    private final MTSEventHandler mFallbackEventHandler;
    private final MTSEventUploaderFactory mUploaderfactory;

    public AnonymousRealTimeMTSEventHandler(MTSEventUploaderFactory mTSEventUploaderFactory, MTSEventHandler mTSEventHandler, MTSEventDataProvider mTSEventDataProvider) {
        super(AnonymousRealTimeMTSEvent.class);
        this.mUploaderfactory = mTSEventUploaderFactory;
        this.mFallbackEventHandler = mTSEventHandler;
        this.mDataProvider = mTSEventDataProvider;
    }

    @Override // com.amazon.music.metrics.event.EventHandler
    public void handleEvent(AnonymousRealTimeMTSEvent anonymousRealTimeMTSEvent) throws Exception {
        MTSEvent mtsEvent = anonymousRealTimeMTSEvent.getMtsEvent();
        MTSEventDataProvider mTSEventDataProvider = this.mDataProvider;
        if (mTSEventDataProvider != null) {
            mtsEvent.addData(mTSEventDataProvider);
        }
        this.mUploaderfactory.createUploader().uploadEvents(Collections.singletonList(anonymousRealTimeMTSEvent.getMtsEvent()), new AnonymousEventHandlerCallback(mtsEvent, this.mFallbackEventHandler));
    }

    static class AnonymousEventHandlerCallback implements MTSEventUploaderCallback {
        private final MTSEventHandler mFallbackEventHandler;
        private final MTSEvent mMtsEvent;

        AnonymousEventHandlerCallback(MTSEvent mTSEvent, MTSEventHandler mTSEventHandler) {
            this.mMtsEvent = mTSEvent;
            this.mFallbackEventHandler = mTSEventHandler;
        }

        @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
        public void onSuccess() {
            AnonymousRealTimeMTSEventHandler.LOG.info("Successfully uploaded anonymous MTSEvent in real time");
        }

        @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
        public void onError(Exception exc) {
            AnonymousRealTimeMTSEventHandler.LOG.error("Received error trying to upload an anonymous MTSEvent in real time, delegating to fallback handler", (Throwable) exc);
            AnonymousRealTimeMTSEventHandler.errorHandleService.execute(new Runnable() { // from class: com.amazon.music.metrics.mts.AnonymousRealTimeMTSEventHandler.AnonymousEventHandlerCallback.1
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    try {
                        AnonymousEventHandlerCallback.this.mFallbackEventHandler.handleEvent(AnonymousEventHandlerCallback.this.mMtsEvent);
                    } catch (Exception e) {
                        AnonymousRealTimeMTSEventHandler.LOG.error("Fallback MTSEventHandler unable to handle anonymous real time event, dropping event", (Throwable) e);
                    }
                }
            });
        }

        @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
        public void onUnrecoverableError(Exception exc) {
            AnonymousRealTimeMTSEventHandler.LOG.error("Received unrecoverable error trying to upload anonymous MTSEvent in real time", (Throwable) exc);
        }
    }
}
