package com.amazon.music.metrics.mts;

import com.amazon.music.file.FileWriter;
import com.amazon.music.file.FileWriterConfig;
import java.io.IOException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class MTSEventRecorder {
    private static final Logger LOG = LoggerFactory.getLogger(MTSEventRecorder.class.getSimpleName());
    private FileWriter<MTSEvent, JSONException> mFileWriter;

    public MTSEventRecorder(FileWriterConfig fileWriterConfig, MTSEventTransformer mTSEventTransformer) {
        this(new FileWriter(fileWriterConfig, mTSEventTransformer));
    }

    public MTSEventRecorder(FileWriter<MTSEvent, JSONException> fileWriter) {
        this.mFileWriter = fileWriter;
    }

    public void recordEvent(MTSEvent mTSEvent) throws Exception {
        LOG.trace("recordEvent: {}", mTSEvent.getEventName());
        try {
            this.mFileWriter.record(mTSEvent);
        } catch (IOException e) {
            LOG.error("IOException when trying to record.  Deleting metrics file", (Throwable) e);
            this.mFileWriter.deleteFile();
        }
    }
}
