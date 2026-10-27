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
public class RealTimeMTSEventHandler extends EventHandler<RealTimeMTSEvent, Exception> {
    private static final Logger LOG = LoggerFactory.getLogger(RealTimeMTSEventHandler.class.getSimpleName());
    private static final ExecutorService errorHandleService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.DiscardPolicy());
    final MTSEventDataProvider dataProvider;
    private final MTSEventHandler mFallbackEventHandler;
    private final MTSEventUploaderFactory mUploaderfactory;

    public RealTimeMTSEventHandler(MTSEventUploaderFactory mTSEventUploaderFactory, MTSEventHandler mTSEventHandler, MTSEventDataProvider mTSEventDataProvider) {
        super(RealTimeMTSEvent.class);
        this.mUploaderfactory = mTSEventUploaderFactory;
        this.mFallbackEventHandler = mTSEventHandler;
        this.dataProvider = mTSEventDataProvider;
    }

    @Override // com.amazon.music.metrics.event.EventHandler
    public void handleEvent(RealTimeMTSEvent realTimeMTSEvent) throws Exception {
        MTSEvent mtsEvent = realTimeMTSEvent.getMtsEvent();
        MTSEventDataProvider mTSEventDataProvider = this.dataProvider;
        if (mTSEventDataProvider != null) {
            mtsEvent.addData(mTSEventDataProvider);
        }
        this.mUploaderfactory.createUploader().uploadEvents(Collections.singletonList(realTimeMTSEvent.getMtsEvent()), new RealTimeEventHandlerCallback(mtsEvent, this.mFallbackEventHandler));
    }

    static class RealTimeEventHandlerCallback implements MTSEventUploaderCallback {
        private final MTSEventHandler mFallbackEventHandler;
        private final MTSEvent mMtsEvent;

        RealTimeEventHandlerCallback(MTSEvent mTSEvent, MTSEventHandler mTSEventHandler) {
            this.mMtsEvent = mTSEvent;
            this.mFallbackEventHandler = mTSEventHandler;
        }

        @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
        public void onSuccess() {
            RealTimeMTSEventHandler.LOG.info("Successfully uploaded MTSEvent in real time");
        }

        @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
        public void onError(Exception exc) {
            RealTimeMTSEventHandler.LOG.error("Received error trying to upload an MTSEvent in real time, delegating to fallback handler", (Throwable) exc);
            RealTimeMTSEventHandler.errorHandleService.execute(new Runnable() { // from class: com.amazon.music.metrics.mts.RealTimeMTSEventHandler.RealTimeEventHandlerCallback.1
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    try {
                        RealTimeEventHandlerCallback.this.mFallbackEventHandler.handleEvent(RealTimeEventHandlerCallback.this.mMtsEvent);
                    } catch (Exception e) {
                        RealTimeMTSEventHandler.LOG.error("Fallback MTSEventHandler unable to handle event, dropping event", (Throwable) e);
                    }
                }
            });
        }

        @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
        public void onUnrecoverableError(Exception exc) {
            RealTimeMTSEventHandler.LOG.error("Received unrecoverable error trying to upload MTSEvent in real time", (Throwable) exc);
        }
    }
}
