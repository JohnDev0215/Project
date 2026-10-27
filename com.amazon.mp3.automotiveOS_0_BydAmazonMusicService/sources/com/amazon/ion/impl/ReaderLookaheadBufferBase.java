package com.amazon.ion.impl;

import com.amazon.ion.BufferConfiguration;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
abstract class ReaderLookaheadBufferBase {
    protected final BufferConfiguration.DataHandler dataHandler;
    private final InputStream input;
    private boolean isSkippingCurrentValue;
    private int markedAvailable;
    private int markedReadIndex;
    private final int maximumBufferSize;
    protected final BufferConfiguration.OversizedValueHandler oversizedValueHandler;
    protected final ResizingPipedInputStream pipe;

    protected abstract void fillInputHelper() throws Exception;

    abstract void truncateToEndOfPreviousValue() throws Exception;

    ReaderLookaheadBufferBase(BufferConfiguration<?> bufferConfiguration, InputStream inputStream) {
        this.input = inputStream;
        this.pipe = new ResizingPipedInputStream(bufferConfiguration.getInitialBufferSize(), bufferConfiguration.getMaximumBufferSize(), true);
        this.maximumBufferSize = bufferConfiguration.getMaximumBufferSize();
        this.oversizedValueHandler = bufferConfiguration.getOversizedValueHandler();
        this.dataHandler = bufferConfiguration.getDataHandler();
        clearMark();
    }

    public final void fillInput() throws Exception {
        clearMark();
        fillInputHelper();
    }

    public final int available() {
        return this.pipe.available();
    }

    public final void clearMark() {
        this.markedAvailable = -1;
        this.markedReadIndex = -1;
    }

    protected InputStream getPipe() {
        return this.pipe;
    }

    protected InputStream getInput() {
        return this.input;
    }

    protected void startNewValue() {
        this.isSkippingCurrentValue = false;
    }

    protected void startSkippingValue() throws Exception {
        if (this.isSkippingCurrentValue) {
            return;
        }
        this.isSkippingCurrentValue = true;
        truncateToEndOfPreviousValue();
    }

    protected boolean isSkippingCurrentValue() {
        return this.isSkippingCurrentValue;
    }

    protected int getMaximumBufferSize() {
        return this.maximumBufferSize;
    }
}
