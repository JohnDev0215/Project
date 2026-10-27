package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.compose.runtime.ComposerKt;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes6.dex */
public final class Cea608Decoder extends CeaDecoder {
    private static final String[] COLOR_NAMES;
    private static boolean DEBUG;
    private static final short[] DEBUG_ROW_INDICES;
    private static final String TAG;
    private static final Logger log;
    private int captionMode;
    private int captionRowCount;
    private List<Cue> cues;
    private CueBuilder currentCueBuilder;
    private boolean dropDataForUnsupportedMode;
    private int incomingDataTargetChannel;
    private long inputTimestampUs;
    private boolean isWideAspectRatio;
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private List<Cue> savedCues;
    private final int selectedChannel;
    private final int selectedField;
    private static final int[] ROW_INDICES = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] COLUMN_INDICES = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] COLORS = {-1, -16711936, -16776961, -16711681, SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -65281};
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, ContentCatalogStatus.PRIME_OR_NOT_BOUNDARY, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, ComposerKt.referenceKey, ComposerKt.reuseKey, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, 227, 205, ComposerKt.providerMapsKey, 236, ContentCatalogStatus.SONIC_RUSH_OR_NOT_BOUNDARY, 242, 213, 245, 123, 125, 92, 94, 95, 124, GeneratorAdapter.AND, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private static final boolean[] ODD_PARITY_BYTE_TABLE = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, false, true, false, false, false, false, true, false, false, false, true, false, true, false, false, true, false, false, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, false, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, false, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true};
    private boolean dataProcessedSinceLastRepeatableControl = true;
    private final int MAX_ALLOWED_DISTANCE_BETWEEN_CEA608_BYTES = 60;
    private int last608BytesDistance = 0;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList<>();

    private int getChannelBit(byte b) {
        return (b >> 3) & 1;
    }

    private String getMiscControlCodeMeaning(byte b) {
        switch (b) {
            case 32:
                return "RESUME_CAPTION_LOADING";
            case 33:
                return "BACKSPACE";
            case 34:
            case 35:
            case 36:
            case 40:
            case 42:
            case 43:
            default:
                return "UNKNOWN";
            case 37:
                return "ROLL_UP_CAPTIONS_2_ROWS";
            case 38:
                return "ROLL_UP_CAPTIONS_3_ROWS";
            case 39:
                return "ROLL_UP_CAPTIONS_4_ROWS";
            case 41:
                return "RESUME_DIRECT_CAPTIONING";
            case 44:
                return "ERASE_DISPLAYED_MEMORY";
            case 45:
                return "CARRIAGE_RETURN";
            case 46:
                return "ERASE_NON_DISPLAYED_MEMORY";
            case 47:
                return "END_OF_CAPTION";
        }
    }

    private int getPreambleAddressCodePositionColumn(byte b, byte b2) {
        if ((b2 & 16) == 0) {
            return 1;
        }
        return (((b2 & MqttWireMessage.MESSAGE_TYPE_DISCONNECT) >> 1) * 4) + 1;
    }

    private static boolean isControlCodeSwitchingToCaptionMode(byte b, byte b2) {
        if ((b & 247) != 20) {
            return false;
        }
        if (b2 == 32 || b2 == 41 || b2 == 47) {
            return true;
        }
        switch (b2) {
        }
        return false;
    }

    private static boolean isMidrowCtrlCode(byte b, byte b2) {
        return (b & 247) == 17 && (b2 & 240) == 32;
    }

    private static boolean isMiscCode(byte b, byte b2, int i) {
        return (b & CBORConstants.BYTE_NULL) == 20 && (b & 1) == i && (b2 & 240) == 32;
    }

    private static boolean isPreambleAddressCode(byte b, byte b2) {
        return (b & 240) == 16 && (b2 & 192) == 64;
    }

    private static boolean isRepeatable(byte b) {
        return (b & 240) == 16;
    }

    private static boolean isTabCtrlCode(byte b, byte b2) {
        return (b & 247) == 23 && b2 >= 33 && b2 <= 35;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void release() {
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void onNewSubtitleDataAvailable(long j) {
        super.onNewSubtitleDataAvailable(j);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    static {
        String simpleName = Cea608Decoder.class.getSimpleName();
        TAG = simpleName;
        Logger logger = new Logger(Logger.Module.Text, simpleName);
        log = logger;
        DEBUG = logger.allowDebug();
        DEBUG_ROW_INDICES = new short[]{11, 11, 1, 2, 3, 4, 12, 13, 14, 15, 5, 6, 7, 8, 9, 10};
        COLOR_NAMES = new String[]{"WHITE", "GREEN", "BLUE", "CYAN", "RED", "YELLOW", "MAGENTA", "KEEP PREVIOUS COLOR"};
    }

    public Cea608Decoder(String str, int i, boolean z) {
        this.isWideAspectRatio = z;
        this.currentCueBuilder = new CueBuilder(0, 4, z);
        this.packetLength = "application/x-mp4-cea-608".equals(str) ? 2 : 3;
        if (i == 1) {
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else if (i == 2) {
            this.selectedChannel = 1;
            this.selectedField = 0;
        } else if (i == 3) {
            this.selectedChannel = 0;
            this.selectedField = 1;
        } else if (i == 4) {
            this.selectedChannel = 1;
            this.selectedField = 1;
        } else {
            this.selectedChannel = 0;
            this.selectedField = 0;
            log.w("Subtitle channel was incorrectly set. Defaulting to CC1");
        }
        Logger logger = log;
        StringBuilder sb = new StringBuilder();
        sb.append("Cea608 channel selected: CC");
        sb.append(this.selectedChannel + 1 + (this.selectedField * 2));
        sb.append(z ? " wide" : "");
        logger.i(sb.toString());
        setCaptionMode(0);
        resetCueBuilders();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = (byte) 0;
        this.repeatableControlCc2 = (byte) 0;
        this.dataProcessedSinceLastRepeatableControl = true;
        this.dropDataForUnsupportedMode = false;
        this.incomingDataTargetChannel = 0;
        this.last608BytesDistance = 0;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle(list);
    }

    private boolean isBytePairForSelectedChannel(byte b) {
        return getChannelBit(b) == this.selectedChannel;
    }

    private boolean isCodeForUnsupportedMode(byte b, byte b2) {
        if (1 > b || b > 15) {
            if ((b & 247) == 20) {
                return b2 == 42 || b2 == 43;
            }
            return false;
        }
        if (this.selectedField != 1) {
            log.w("XDS control code met on FIELD 1. This is not allowed according to the CEA608 standard, the captions might be corrupted.");
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0102  */
    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void decode(com.google.android.exoplayer2.text.SubtitleInputBuffer r12) {
        /*
            Method dump skipped, instruction units count: 451
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea608Decoder.decode(com.google.android.exoplayer2.text.SubtitleInputBuffer):void");
    }

    private boolean handleCtrl(byte b, byte b2) {
        this.incomingDataTargetChannel = getChannelBit(b);
        boolean zIsRepeatable = isRepeatable(b);
        if (zIsRepeatable) {
            if (this.repeatableControlSet && this.repeatableControlCc1 == b && this.repeatableControlCc2 == b2) {
                this.repeatableControlSet = false;
                if (DEBUG) {
                    log.i(String.format("Dropping repeated Control Code 0x%02X - 0x%02X", Byte.valueOf(b), Byte.valueOf(b2)));
                }
                return true;
            }
            this.repeatableControlSet = true;
            this.dataProcessedSinceLastRepeatableControl = false;
            this.repeatableControlCc1 = b;
            this.repeatableControlCc2 = b2;
        }
        if (!isBytePairForSelectedChannel(b)) {
            return zIsRepeatable;
        }
        if (DEBUG) {
            debugPrintControlCode(b, b2);
        }
        if (isMidrowCtrlCode(b, b2)) {
            handleMidrowCtrl(b2);
        } else if (isPreambleAddressCode(b, b2)) {
            handlePreambleAddressCode(b, b2);
        } else if (isTabCtrlCode(b, b2)) {
            if (this.captionMode != 2 || this.currentCueBuilder.isEmpty()) {
                this.currentCueBuilder.setTab(b2 - 32);
            }
        } else if (isMiscCode(b, b2, this.selectedField)) {
            handleMiscCode(b2);
        }
        return zIsRepeatable;
    }

    private void debugPrintControlCode(byte b, byte b2) {
        if (isPreambleAddressCode(b, b2)) {
            log.d("PAC: " + getPreambleAddressCodeMeaning(b, b2));
            return;
        }
        if (isMidrowCtrlCode(b, b2)) {
            log.d("MRC: " + getMidRowCodeMeaning(b, b2));
            return;
        }
        if (isTabCtrlCode(b, b2)) {
            Logger logger = log;
            StringBuilder sb = new StringBuilder();
            sb.append("TAB: ");
            sb.append(b2 - 32);
            logger.d(sb.toString());
            return;
        }
        if (isMiscCode(b, b2, this.selectedField)) {
            log.d("MISC: " + getMiscControlCodeMeaning(b2));
            return;
        }
        log.i(String.format("Undefined Control Code 0x%02X - 0x%02X", Byte.valueOf(b), Byte.valueOf(b2)));
    }

    private int getPreambleAddressCodePositionRow(byte b, byte b2) {
        return DEBUG_ROW_INDICES[((b & 7) << 1) + ((b2 & 32) > 0 ? 1 : 0)];
    }

    private int getPreambleColorIdx(byte b) {
        if ((b & 16) > 0) {
            return 0;
        }
        return (b & 30) / 2;
    }

    private String getPreambleAddressCodeMeaning(byte b, byte b2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Row:");
        sb.append(getPreambleAddressCodePositionRow(b, b2));
        sb.append("; Col:");
        sb.append(getPreambleAddressCodePositionColumn(b, b2));
        sb.append("; Color:");
        sb.append(COLOR_NAMES[getPreambleColorIdx(b2)]);
        sb.append("; italic:");
        sb.append((b2 & 30) == 14 ? "true" : "false");
        sb.append("; underline:");
        sb.append((b2 & 1) == 1);
        return sb.toString();
    }

    private String getMidRowCodeMeaning(byte b, byte b2) {
        String str = COLOR_NAMES[(b2 >> 1) & 15];
        if (!((b2 & 1) == 1)) {
            return str;
        }
        return str + " + UNDERLINE";
    }

    private void handleMidrowCtrl(byte b) {
        this.currentCueBuilder.append(' ');
        this.currentCueBuilder.setUnderline((b & 1) == 1);
        int i = (b >> 1) & 15;
        if (i == 7) {
            this.currentCueBuilder.setMidrowStyle(new StyleSpan(2), 2);
            this.currentCueBuilder.setMidrowStyle(new ForegroundColorSpan(-1), 1);
        } else {
            this.currentCueBuilder.setMidrowStyle(new ForegroundColorSpan(COLORS[i]), 1);
        }
    }

    private void handlePreambleAddressCode(byte b, byte b2) {
        int i = ROW_INDICES[b & 7];
        if ((b2 & 32) != 0) {
            i++;
        }
        if (i != this.currentCueBuilder.getRow()) {
            if (this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                CueBuilder cueBuilder = new CueBuilder(this.captionMode, this.captionRowCount, this.isWideAspectRatio);
                this.currentCueBuilder = cueBuilder;
                this.cueBuilders.add(cueBuilder);
            }
            this.currentCueBuilder.setRow(i);
        } else if (this.captionMode == 2 && !this.currentCueBuilder.isEmpty()) {
            log.d("Dropping PAC repositioning cursor in the same row");
            return;
        }
        if ((b2 & 1) == 1) {
            this.currentCueBuilder.setPreambleStyle(new UnderlineSpan());
        }
        int i2 = (b2 >> 1) & 15;
        if (i2 > 7) {
            this.currentCueBuilder.setIndent(COLUMN_INDICES[i2 & 7]);
        } else if (i2 == 7) {
            this.currentCueBuilder.setPreambleStyle(new StyleSpan(2));
            this.currentCueBuilder.setPreambleStyle(new ForegroundColorSpan(-1));
        } else {
            this.currentCueBuilder.setPreambleStyle(new ForegroundColorSpan(COLORS[i2]));
        }
    }

    private void handleMiscCode(byte b) {
        if (b == 32) {
            setCaptionMode(2);
            return;
        }
        if (b != 41) {
            switch (b) {
                case 37:
                    setCaptionMode(1);
                    setCaptionRowCount(2);
                    break;
                case 38:
                    setCaptionMode(1);
                    setCaptionRowCount(3);
                    break;
                case 39:
                    setCaptionMode(1);
                    setCaptionRowCount(4);
                    break;
                default:
                    int i = this.captionMode;
                    if (i != 0) {
                        if (b != 33) {
                            switch (b) {
                                case 44:
                                    this.cues = null;
                                    this.savedCues = null;
                                    if (i == 1 || i == 3) {
                                        resetCueBuilders();
                                    }
                                    onNewSubtitleDataAvailable(this.inputTimestampUs);
                                    break;
                                case 45:
                                    if (i == 1 && !this.currentCueBuilder.isEmpty()) {
                                        this.currentCueBuilder.rollUp();
                                        break;
                                    }
                                    break;
                                case 46:
                                    resetCueBuilders();
                                    break;
                                case 47:
                                    this.cues = getDisplayCues();
                                    onNewSubtitleDataAvailable(this.inputTimestampUs);
                                    resetCueBuilders();
                                    break;
                            }
                        } else {
                            this.currentCueBuilder.backspace();
                            break;
                        }
                    }
                    break;
            }
            return;
        }
        setCaptionMode(3);
    }

    private List<Cue> getDisplayCues() {
        ArrayList arrayList = new ArrayList();
        int size = this.cueBuilders.size();
        int iMin = 2;
        for (int i = 0; i < size; i++) {
            iMin = Math.min(this.cueBuilders.get(i).calculatePreferredAlignment(), iMin);
        }
        for (int i2 = 0; i2 < size; i2++) {
            Cue cueBuild = this.cueBuilders.get(i2).build(iMin);
            if (cueBuild != null) {
                arrayList.add(cueBuild);
            }
        }
        return arrayList;
    }

    private void setCaptionMode(int i) {
        int i2 = this.captionMode;
        if (i2 == i) {
            return;
        }
        this.captionMode = i;
        if (i == 3) {
            this.savedCues = this.cues;
            Iterator<CueBuilder> it = this.cueBuilders.iterator();
            while (it.hasNext()) {
                it.next().setCaptionMode(i);
            }
        } else {
            this.savedCues = null;
            resetCueBuilders();
        }
        if (i2 == 3 || i == 1 || i == 0) {
            this.cues = null;
        }
    }

    private void setCaptionRowCount(int i) {
        this.captionRowCount = i;
        this.currentCueBuilder.setCaptionRowCount(i);
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private static char getChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & 127) - 32];
    }

    private static char getSpecialChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & 15];
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & 31];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & 31];
    }

    private static class CueBuilder {
        private int captionMode;
        private int captionRowCount;
        private int endPadding;
        private SpannableStringBuilder finalCueString;
        private int indent;
        private boolean isWideAspectRatio;
        private int row;
        private int startPadding;
        private int tabOffset;
        private int underlineStartPosition;
        private final List<CharacterStyle> preambleStyles = new ArrayList();
        private final List<CueStyle> midrowStyles = new ArrayList();
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();

        public CueBuilder(int i, int i2, boolean z) {
            this.isWideAspectRatio = z;
            reset(i);
            setCaptionRowCount(i2);
        }

        public void setCaptionMode(int i) {
            this.captionMode = i;
        }

        public void reset(int i) {
            this.captionMode = i;
            this.preambleStyles.clear();
            this.midrowStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
            this.underlineStartPosition = -1;
        }

        public void setCaptionRowCount(int i) {
            this.captionRowCount = i;
        }

        public boolean isEmpty() {
            return this.preambleStyles.isEmpty() && this.midrowStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int i) {
            this.row = i;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.clear();
            this.preambleStyles.clear();
            this.midrowStyles.clear();
            this.underlineStartPosition = -1;
            int iMin = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= iMin) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public void setIndent(int i) {
            this.indent = i;
        }

        public void setTab(int i) {
            this.tabOffset = i;
        }

        public void setPreambleStyle(CharacterStyle characterStyle) {
            this.preambleStyles.add(characterStyle);
        }

        public void setMidrowStyle(CharacterStyle characterStyle, int i) {
            this.midrowStyles.add(new CueStyle(characterStyle, this.captionStringBuilder.length(), i));
        }

        public void setUnderline(boolean z) {
            if (z) {
                this.underlineStartPosition = this.captionStringBuilder.length();
            } else if (this.underlineStartPosition != -1) {
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                this.underlineStartPosition = -1;
            }
        }

        public void append(char c) {
            if (Cea608Decoder.DEBUG) {
                Cea608Decoder.log.d("Incoming char: '" + c + "'");
            }
            this.captionStringBuilder.append(c);
        }

        public SpannableString buildSpannableString() {
            int length = this.captionStringBuilder.length();
            int i = 0;
            for (int i2 = 0; i2 < this.preambleStyles.size(); i2++) {
                this.captionStringBuilder.setSpan(this.preambleStyles.get(i2), 0, length, 33);
            }
            while (i < this.midrowStyles.size()) {
                CueStyle cueStyle = this.midrowStyles.get(i);
                int size = this.midrowStyles.size();
                int i3 = cueStyle.nextStyleIncrement;
                this.captionStringBuilder.setSpan(cueStyle.style, cueStyle.start, i < size - i3 ? this.midrowStyles.get(i3 + i).start : length, 33);
                i++;
            }
            if (this.underlineStartPosition != -1) {
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
            }
            return new SpannableString(this.captionStringBuilder);
        }

        public int calculatePreferredAlignment() {
            this.finalCueString = new SpannableStringBuilder();
            for (int i = 0; i < this.rolledUpCaptions.size(); i++) {
                this.finalCueString.append((CharSequence) this.rolledUpCaptions.get(i));
                this.finalCueString.append('\n');
            }
            this.finalCueString.append((CharSequence) buildSpannableString());
            if (this.finalCueString.length() == 0) {
                return 2;
            }
            int i2 = this.indent + this.tabOffset;
            this.startPadding = i2;
            int length = (32 - i2) - this.finalCueString.length();
            this.endPadding = length;
            int i3 = this.startPadding - length;
            if (this.captionMode != 2 || (Math.abs(i3) >= 3 && this.endPadding >= 0)) {
                return (this.captionMode != 2 || i3 <= 0 || this.endPadding >= 4) ? 0 : 2;
            }
            return 1;
        }

        public Cue build(int i) {
            float f;
            int i2;
            int i3;
            int i4;
            int i5;
            if (this.finalCueString == null) {
                calculatePreferredAlignment();
            }
            if (this.finalCueString.length() == 0) {
                return null;
            }
            if (i == 1) {
                f = 0.5f;
                i2 = 1;
            } else if (i == 2) {
                f = (32 - this.endPadding) / 32.0f;
                i2 = 2;
            } else {
                f = this.startPadding / 32.0f;
                i2 = 0;
            }
            if (this.captionMode == 1 || (i5 = this.row) > 7) {
                i3 = (this.row - 15) - 1;
                i4 = 2;
            } else {
                i3 = i5 - 1;
                i4 = 0;
            }
            Cue cue = new Cue(this.finalCueString, null, Layout.Alignment.ALIGN_NORMAL, null, i3, 1, i4, f, i2, Float.MIN_VALUE, Float.MIN_VALUE, false, ViewCompat.MEASURED_STATE_MASK, 0, 0, 0, 0, this.isWideAspectRatio, true);
            this.finalCueString = null;
            return cue;
        }

        public String toString() {
            return this.captionStringBuilder.toString();
        }

        private static class CueStyle {
            public final int nextStyleIncrement;
            public final int start;
            public final CharacterStyle style;

            public CueStyle(CharacterStyle characterStyle, int i, int i2) {
                this.style = characterStyle;
                this.start = i;
                this.nextStyleIncrement = i2;
            }
        }
    }
}
