package com.amazon.ion.impl;

import com.amazon.ion.IonBufferConfiguration;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.impl.ResizingPipedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes3.dex */
public final class IonReaderLookaheadBuffer extends ReaderLookaheadBufferBase {
    private long additionalBytesNeeded;
    private final List<Integer> annotationSids;
    private long currentNumberOfAnnotations;
    private boolean handlerNeedsToBeNotifiedOfOversizedValue;
    private final VarUInt inProgressVarUInt;
    private boolean isSymbolTableAnnotationFirst;
    private boolean isSystemValue;
    private int ivmSecondByteIndex;
    private int nopPadStartIndex;
    private long numberOfAnnotationSidBytesRemaining;
    private final IonBufferConfiguration.OversizedSymbolTableHandler oversizedSymbolTableHandler;
    private final int pageSize;
    private int peekIndex;
    private State state;
    private final List<SymbolTableMarker> symbolTableMarkers;
    private int valueEndIndex;
    private int valuePostHeaderIndex;
    private int valuePreHeaderIndex;
    private int valueStartAvailable;
    private int valueStartWriteIndex;
    private IonTypeID valueTid;

    private enum ReadTypeIdResult {
        STRUCT,
        NOT_STRUCT,
        NO_DATA
    }

    private enum State {
        BEFORE_TYPE_ID,
        READING_TYPE_ID,
        READING_HEADER,
        SKIPPING_VALUE,
        READING_VALUE_WITH_SYMBOL_TABLE_ANNOTATION,
        READING_SYMBOL_TABLE_LENGTH,
        DONE
    }

    private static final class VarUInt {
        private boolean isComplete;
        private Location location;
        private int numberOfBytesRead;
        private long value;

        private enum Location {
            VALUE_LENGTH,
            ANNOTATION_WRAPPER_LENGTH,
            ANNOTATION_WRAPPER_SIDS_LENGTH,
            ANNOTATION_WRAPPER_SID
        }

        static /* synthetic */ int access$308(VarUInt varUInt) {
            int i = varUInt.numberOfBytesRead;
            varUInt.numberOfBytesRead = i + 1;
            return i;
        }

        private VarUInt() {
            reset(Location.VALUE_LENGTH);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset(Location location) {
            this.location = location;
            this.value = 0L;
            this.numberOfBytesRead = 0;
            this.isComplete = false;
        }
    }

    static class SymbolTableMarker {
        int endIndex;
        int startIndex;

        private SymbolTableMarker(int i, int i2) {
            this.startIndex = i;
            this.endIndex = i + i2;
        }
    }

    private void reset() {
        this.additionalBytesNeeded = 0L;
        this.isSystemValue = false;
        this.isSymbolTableAnnotationFirst = false;
        this.numberOfAnnotationSidBytesRemaining = 0L;
        this.currentNumberOfAnnotations = 0L;
        this.valuePreHeaderIndex = -1;
        this.valuePostHeaderIndex = -1;
        this.valueTid = null;
        this.valueEndIndex = -1;
        this.annotationSids.clear();
        this.valueStartAvailable = this.pipe.available();
        startNewValue();
    }

    public IonReaderLookaheadBuffer(IonBufferConfiguration ionBufferConfiguration, InputStream inputStream) {
        super(ionBufferConfiguration, inputStream);
        this.symbolTableMarkers = new ArrayList(2);
        this.annotationSids = new ArrayList(3);
        this.state = State.BEFORE_TYPE_ID;
        this.nopPadStartIndex = -1;
        this.ivmSecondByteIndex = -1;
        this.peekIndex = 0;
        this.handlerNeedsToBeNotifiedOfOversizedValue = true;
        this.pipe.registerNotificationConsumer(new ResizingPipedInputStream.NotificationConsumer() { // from class: com.amazon.ion.impl.IonReaderLookaheadBuffer.1
            @Override // com.amazon.ion.impl.ResizingPipedInputStream.NotificationConsumer
            public void bytesConsolidatedToStartOfBuffer(int i) {
                IonReaderLookaheadBuffer.this.shiftIndicesLeft(-1, i);
            }
        });
        this.pageSize = ionBufferConfiguration.getInitialBufferSize();
        this.oversizedSymbolTableHandler = ionBufferConfiguration.getOversizedSymbolTableHandler();
        this.inProgressVarUInt = new VarUInt();
        reset();
    }

    private void initializeVarUInt(VarUInt.Location location) {
        this.inProgressVarUInt.reset(location);
        this.state = State.READING_HEADER;
    }

    private int readByte() throws Exception {
        if (this.pipe.availableBeyondBoundary() == 0 && fillPage(1) < 1) {
            return -1;
        }
        if (isSkippingCurrentValue()) {
            return getInput().read();
        }
        int iPeek = this.pipe.peek(this.peekIndex);
        this.pipe.extendBoundary(1);
        this.peekIndex++;
        return iPeek;
    }

    private void readVarUInt() throws Exception {
        while (this.inProgressVarUInt.numberOfBytesRead < 9) {
            int i = readByte();
            if (i < 0) {
                return;
            }
            VarUInt.access$308(this.inProgressVarUInt);
            VarUInt varUInt = this.inProgressVarUInt;
            varUInt.value = (varUInt.value << 7) | ((long) (i & WorkQueueKt.MASK));
            if ((i & 128) != 0) {
                this.inProgressVarUInt.isComplete = true;
                this.dataHandler.onData(this.inProgressVarUInt.numberOfBytesRead);
                return;
            }
        }
        throw new IonException("Found a VarUInt that was too large to fit in a `long`");
    }

    private void setAdditionalBytesNeeded(long j, boolean z) {
        if (z) {
            this.additionalBytesNeeded = j;
        }
    }

    private ReadTypeIdResult readTypeID(boolean z) throws Exception {
        int i = readByte();
        if (i < 0) {
            return ReadTypeIdResult.NO_DATA;
        }
        this.valueTid = IonTypeID.TYPE_IDS[i];
        this.dataHandler.onData(1);
        if (i != 224) {
            IonTypeID ionTypeID = this.valueTid;
            if (!ionTypeID.isValid) {
                throw new IonException("Invalid type ID.");
            }
            IonType ionType = ionTypeID.type;
            if (ionType == IonType.BOOL) {
                this.state = State.BEFORE_TYPE_ID;
            } else if (ionType == IonTypeID.ION_TYPE_ANNOTATION_WRAPPER) {
                if (ionTypeID.variableLength) {
                    initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_LENGTH);
                } else {
                    setAdditionalBytesNeeded(ionTypeID.length, z);
                    initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SIDS_LENGTH);
                }
            } else if (ionTypeID.isNull) {
                this.state = State.BEFORE_TYPE_ID;
            } else if (ionTypeID.variableLength) {
                initializeVarUInt(VarUInt.Location.VALUE_LENGTH);
            } else {
                setAdditionalBytesNeeded(ionTypeID.length, z);
                this.state = State.SKIPPING_VALUE;
            }
        } else {
            if (!z) {
                throw new IonException("Invalid annotation header.");
            }
            this.additionalBytesNeeded = 3L;
            this.isSystemValue = true;
            resetSymbolTableMarkers();
            this.ivmSecondByteIndex = this.peekIndex;
            this.state = State.SKIPPING_VALUE;
        }
        if (this.valueTid.type == IonType.STRUCT) {
            return ReadTypeIdResult.STRUCT;
        }
        return ReadTypeIdResult.NOT_STRUCT;
    }

    private void readHeader() throws Exception {
        if (this.inProgressVarUInt.location != VarUInt.Location.VALUE_LENGTH) {
            if (this.inProgressVarUInt.location == VarUInt.Location.ANNOTATION_WRAPPER_LENGTH) {
                readVarUInt();
                if (!this.inProgressVarUInt.isComplete) {
                    return;
                }
                this.additionalBytesNeeded = this.inProgressVarUInt.value;
                initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SIDS_LENGTH);
            }
            if (this.inProgressVarUInt.location == VarUInt.Location.ANNOTATION_WRAPPER_SIDS_LENGTH) {
                readVarUInt();
                if (!this.inProgressVarUInt.isComplete) {
                    return;
                }
                this.additionalBytesNeeded -= (long) this.inProgressVarUInt.numberOfBytesRead;
                this.numberOfAnnotationSidBytesRemaining = this.inProgressVarUInt.value;
                initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SID);
            }
            if (this.inProgressVarUInt.location != VarUInt.Location.ANNOTATION_WRAPPER_SID) {
                return;
            }
            while (true) {
                readVarUInt();
                if (!this.inProgressVarUInt.isComplete) {
                    return;
                }
                long j = this.currentNumberOfAnnotations + 1;
                this.currentNumberOfAnnotations = j;
                if (j == 1 && this.inProgressVarUInt.value == 3) {
                    this.isSymbolTableAnnotationFirst = true;
                }
                this.annotationSids.add(Integer.valueOf((int) this.inProgressVarUInt.value));
                this.numberOfAnnotationSidBytesRemaining -= (long) this.inProgressVarUInt.numberOfBytesRead;
                this.additionalBytesNeeded -= (long) this.inProgressVarUInt.numberOfBytesRead;
                if (this.numberOfAnnotationSidBytesRemaining <= 0) {
                    this.state = State.SKIPPING_VALUE;
                    if (this.isSymbolTableAnnotationFirst) {
                        this.state = State.READING_VALUE_WITH_SYMBOL_TABLE_ANNOTATION;
                        return;
                    }
                    return;
                }
                initializeVarUInt(VarUInt.Location.ANNOTATION_WRAPPER_SID);
            }
        } else {
            readVarUInt();
            if (this.inProgressVarUInt.isComplete) {
                this.additionalBytesNeeded = this.inProgressVarUInt.value;
                this.state = State.SKIPPING_VALUE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shiftIndicesLeft(int i, int i2) {
        this.peekIndex = Math.max(this.peekIndex - i2, 0);
        this.valuePreHeaderIndex -= i2;
        this.valuePostHeaderIndex -= i2;
        for (SymbolTableMarker symbolTableMarker : this.symbolTableMarkers) {
            int i3 = symbolTableMarker.startIndex;
            if (i3 > i) {
                symbolTableMarker.startIndex = i3 - i2;
                symbolTableMarker.endIndex -= i2;
            }
        }
        int i4 = this.ivmSecondByteIndex;
        if (i4 > i) {
            this.ivmSecondByteIndex = i4 - i2;
        }
    }

    private void reclaimNopPadding() {
        this.pipe.consolidate(this.valuePreHeaderIndex, this.nopPadStartIndex);
        int i = this.nopPadStartIndex;
        shiftIndicesLeft(i, this.valuePreHeaderIndex - i);
        resetNopPadIndex();
    }

    private int skipBytesFromInput(int i) throws IOException {
        try {
            return (int) getInput().skip(i);
        } catch (EOFException unused) {
            return 0;
        }
    }

    private int fillPage(int i) throws Exception {
        int iCapacity = this.pipe.capacity() - this.pipe.size();
        if (iCapacity <= 0) {
            int maximumBufferSize = getMaximumBufferSize() - this.pipe.capacity();
            if (i > maximumBufferSize) {
                int i2 = this.nopPadStartIndex;
                if (i2 > -1 && this.valuePreHeaderIndex - i2 >= i) {
                    reclaimNopPadding();
                } else {
                    startSkippingValue();
                }
            } else {
                i = Math.min(this.pageSize, maximumBufferSize);
            }
        } else {
            i = iCapacity;
        }
        if (isSkippingCurrentValue()) {
            return this.state == State.SKIPPING_VALUE ? skipBytesFromInput(i) : i;
        }
        return this.pipe.receive(getInput(), i);
    }

    private void notifyHandlerOfOversizedValue() throws Exception {
        if (this.handlerNeedsToBeNotifiedOfOversizedValue) {
            if (this.isSystemValue) {
                this.oversizedSymbolTableHandler.onOversizedSymbolTable();
            } else {
                this.oversizedValueHandler.onOversizedValue();
            }
        }
        this.handlerNeedsToBeNotifiedOfOversizedValue = false;
    }

    private int fillOrSkip() throws Exception {
        int iFillPage;
        int iAvailableBeyondBoundary = ((int) this.additionalBytesNeeded) - this.pipe.availableBeyondBoundary();
        if (isSkippingCurrentValue()) {
            iFillPage = skipBytesFromInput(iAvailableBeyondBoundary);
        } else {
            iFillPage = fillPage(iAvailableBeyondBoundary);
        }
        if (iFillPage < 1) {
            return 0;
        }
        if (isSkippingCurrentValue()) {
            notifyHandlerOfOversizedValue();
            return iFillPage + (((int) this.additionalBytesNeeded) - iAvailableBeyondBoundary);
        }
        int iMin = (int) Math.min(this.additionalBytesNeeded, iFillPage);
        this.pipe.extendBoundary(iMin);
        this.peekIndex += iMin;
        return iMin;
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBufferBase
    protected void fillInputHelper() throws Exception {
        int iFillOrSkip;
        while (true) {
            State state = this.state;
            if (state == State.BEFORE_TYPE_ID || state == State.READING_TYPE_ID) {
                reset();
                this.state = State.READING_TYPE_ID;
                if (readTypeID(true) != ReadTypeIdResult.NO_DATA) {
                    int i = this.peekIndex;
                    this.valuePostHeaderIndex = i;
                    int i2 = i - 1;
                    this.valuePreHeaderIndex = i2;
                    this.valueStartWriteIndex = i2;
                }
            }
            if (this.state == State.READING_HEADER) {
                readHeader();
                if (!this.inProgressVarUInt.isComplete) {
                    return;
                } else {
                    this.valuePostHeaderIndex = this.peekIndex;
                }
            }
            if (this.state == State.READING_VALUE_WITH_SYMBOL_TABLE_ANNOTATION) {
                ReadTypeIdResult typeID = readTypeID(false);
                if (typeID == ReadTypeIdResult.NO_DATA) {
                    return;
                }
                this.additionalBytesNeeded--;
                if (typeID == ReadTypeIdResult.STRUCT) {
                    this.state = State.READING_SYMBOL_TABLE_LENGTH;
                } else {
                    this.state = State.SKIPPING_VALUE;
                }
            }
            if (this.state == State.READING_SYMBOL_TABLE_LENGTH) {
                this.isSystemValue = true;
                if (this.inProgressVarUInt.location == VarUInt.Location.VALUE_LENGTH) {
                    readVarUInt();
                    if (!this.inProgressVarUInt.isComplete) {
                        return;
                    } else {
                        this.additionalBytesNeeded = this.inProgressVarUInt.value;
                    }
                }
                this.symbolTableMarkers.add(new SymbolTableMarker(this.peekIndex, (int) this.additionalBytesNeeded));
                this.state = State.SKIPPING_VALUE;
            }
            if (this.state == State.SKIPPING_VALUE) {
                if (this.valueTid.isNopPad) {
                    long jAvailableBeyondBoundary = this.pipe.availableBeyondBoundary();
                    long j = this.additionalBytesNeeded;
                    if (jAvailableBeyondBoundary <= j) {
                        this.additionalBytesNeeded = j - ((long) this.pipe.availableBeyondBoundary());
                        startSkippingValue();
                        this.handlerNeedsToBeNotifiedOfOversizedValue = false;
                    }
                }
                while (this.additionalBytesNeeded > 0) {
                    long jAvailableBeyondBoundary2 = this.pipe.availableBeyondBoundary();
                    long j2 = this.additionalBytesNeeded;
                    if (jAvailableBeyondBoundary2 >= j2) {
                        iFillOrSkip = (int) j2;
                        this.pipe.extendBoundary(iFillOrSkip);
                        this.peekIndex += iFillOrSkip;
                    } else {
                        iFillOrSkip = fillOrSkip();
                        if (iFillOrSkip < 1) {
                            return;
                        }
                    }
                    this.dataHandler.onData(iFillOrSkip);
                    this.additionalBytesNeeded -= (long) iFillOrSkip;
                }
                this.state = State.BEFORE_TYPE_ID;
            }
            if (this.state != State.BEFORE_TYPE_ID) {
                return;
            }
            this.valueEndIndex = this.peekIndex;
            if (!this.isSystemValue && !isSkippingCurrentValue() && !this.valueTid.isNopPad) {
                return;
            }
            if (this.valueTid.isNopPad && this.nopPadStartIndex < 0) {
                this.nopPadStartIndex = this.valuePreHeaderIndex;
            }
            if (this.isSystemValue && isSkippingCurrentValue()) {
                reset();
                this.state = State.DONE;
                return;
            } else if (this.isSystemValue && this.nopPadStartIndex > -1) {
                reclaimNopPadding();
            }
        }
    }

    @Override // com.amazon.ion.impl.ReaderLookaheadBufferBase
    void truncateToEndOfPreviousValue() {
        int i = this.valueStartWriteIndex;
        this.peekIndex = i;
        this.pipe.truncate(i, this.valueStartAvailable);
        this.handlerNeedsToBeNotifiedOfOversizedValue = true;
    }

    public boolean moreDataRequired() {
        return this.pipe.available() <= 0 || this.state != State.BEFORE_TYPE_ID;
    }

    int getIvmIndex() {
        return this.ivmSecondByteIndex;
    }

    void resetIvmIndex() {
        this.ivmSecondByteIndex = -1;
    }

    void resetNopPadIndex() {
        this.nopPadStartIndex = -1;
    }

    int getValueStart() {
        return this.valuePostHeaderIndex;
    }

    IonTypeID getValueTid() {
        return this.valueTid;
    }

    int getValueEnd() {
        return this.valueEndIndex;
    }

    List<SymbolTableMarker> getSymbolTableMarkers() {
        return this.symbolTableMarkers;
    }

    void resetSymbolTableMarkers() {
        this.symbolTableMarkers.clear();
    }

    List<Integer> getAnnotationSids() {
        return this.annotationSids;
    }
}
