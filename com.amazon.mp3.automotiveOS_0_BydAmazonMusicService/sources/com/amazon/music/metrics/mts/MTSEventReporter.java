package com.amazon.music.metrics.mts;

import com.amazon.music.file.EmptyFileContentsException;
import com.amazon.music.file.FileReader;
import com.amazon.music.file.FileWriterConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class MTSEventReporter implements MTSEventUploaderCallback {
    private static final Logger LOG = LoggerFactory.getLogger(MTSEventReporter.class.getSimpleName());
    private FileWriterConfig mConfig;
    private MTSEventHandlerErrorReporter mErrorReporter;
    private FileReader mFileReader;
    private MTSEventTransformer mTransformer;
    private MTSEventUploaderFactory mUploaderFactory;

    public MTSEventReporter(FileWriterConfig fileWriterConfig, MTSEventUploaderFactory mTSEventUploaderFactory, MTSEventTransformer mTSEventTransformer, MTSEventHandlerErrorReporter mTSEventHandlerErrorReporter) {
        this.mConfig = fileWriterConfig;
        this.mFileReader = new FileReader(fileWriterConfig);
        this.mUploaderFactory = mTSEventUploaderFactory;
        this.mTransformer = mTSEventTransformer;
        this.mErrorReporter = mTSEventHandlerErrorReporter;
    }

    public void reportEvents() throws Throwable {
        LOG.trace("reportEvents");
        try {
            try {
                this.mUploaderFactory.createUploader().uploadEvents(reconstructEvents(this.mFileReader.readFile(), this.mTransformer), this);
            } catch (IOException e) {
                LOG.error("Unable to read events from file, deleting", (Throwable) e);
                this.mFileReader.deleteFile();
                throw e;
            }
        } catch (EmptyFileContentsException unused) {
        }
    }

    private List<MTSEvent> reconstructEvents(String str, MTSEventTransformer mTSEventTransformer) {
        String[] strArrSplit = str.split(this.mConfig.getDelimiter());
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str2 : strArrSplit) {
            try {
                arrayList.add(mTSEventTransformer.transform(str2));
            } catch (JSONException e) {
                LOG.error("Unable to deserialize event", (Throwable) e);
            }
        }
        return arrayList;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
    public void onSuccess() {
        LOG.info("Metrics successfully uploaded");
        try {
            this.mFileReader.deleteFile();
        } catch (IOException e) {
            LOG.error("Upload succeeded, but unable to delete file!", (Throwable) e);
            this.mErrorReporter.reportError(e);
        }
    }

    @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
    public void onError(Exception exc) {
        LOG.warn("Received an error while attempting to upload metrics", (Throwable) exc);
        this.mErrorReporter.reportError(exc);
    }

    @Override // com.amazon.music.metrics.mts.MTSEventUploaderCallback
    public void onUnrecoverableError(Exception exc) {
        LOG.error("Critical Error while uploading, deleting metrics file", (Throwable) exc);
        try {
            this.mFileReader.deleteFile();
        } catch (IOException e) {
            LOG.error("Unable to remove metrics file!");
            this.mErrorReporter.reportError(e);
        }
    }
}
