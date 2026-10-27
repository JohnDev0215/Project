package com.android.inputmethod.event;

import android.text.TextUtils;
import android.util.SparseIntArray;
import java.text.Normalizer;
import java.util.ArrayList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DeadKeyCombiner implements Combiner {
    final StringBuilder mDeadSequence = new StringBuilder();

    private static class Data {
        private static final int ACCENT_ACUTE = 180;
        private static final int ACCENT_BREVE = 728;
        private static final int ACCENT_CARON = 711;
        private static final int ACCENT_CEDILLA = 184;
        private static final int ACCENT_CIRCUMFLEX = 710;
        private static final int ACCENT_CIRCUMFLEX_LEGACY = 94;
        private static final int ACCENT_COMMA_ABOVE = 8125;
        private static final int ACCENT_COMMA_ABOVE_RIGHT = 700;
        private static final int ACCENT_DOT_ABOVE = 729;
        private static final int ACCENT_DOT_BELOW = 46;
        private static final int ACCENT_DOUBLE_ACUTE = 733;
        private static final int ACCENT_GRAVE = 715;
        private static final int ACCENT_GRAVE_LEGACY = 96;
        private static final int ACCENT_HOOK_ABOVE = 704;
        private static final int ACCENT_HORN = 39;
        private static final int ACCENT_MACRON = 175;
        private static final int ACCENT_MACRON_BELOW = 717;
        private static final int ACCENT_OGONEK = 731;
        private static final int ACCENT_REVERSED_COMMA_ABOVE = 701;
        private static final int ACCENT_RING_ABOVE = 730;
        private static final int ACCENT_STROKE = 45;
        private static final int ACCENT_TILDE = 732;
        private static final int ACCENT_TILDE_LEGACY = 126;
        private static final int ACCENT_TURNED_COMMA_ABOVE = 699;
        private static final int ACCENT_UMLAUT = 168;
        private static final int ACCENT_VERTICAL_LINE_ABOVE = 712;
        private static final int ACCENT_VERTICAL_LINE_BELOW = 716;
        public static final int BITS_TO_SHIFT_DEAD_CODE_POINT_FOR_NON_STANDARD_COMBINATION = 16;
        public static final int NOT_A_CHAR = 0;
        static final SparseIntArray sAccentToCombining;
        static final SparseIntArray sCombiningToAccent;
        private static final SparseIntArray sNonstandardDeadCombinations;

        private Data() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sCombiningToAccent = sparseIntArray;
            SparseIntArray sparseIntArray2 = new SparseIntArray();
            sAccentToCombining = sparseIntArray2;
            addCombining(768, 715);
            addCombining(769, 180);
            addCombining(770, 710);
            addCombining(771, 732);
            addCombining(772, 175);
            addCombining(774, 728);
            addCombining(775, 729);
            addCombining(776, 168);
            addCombining(777, 704);
            addCombining(778, 730);
            addCombining(779, 733);
            addCombining(780, 711);
            addCombining(781, 712);
            addCombining(786, 699);
            addCombining(787, ACCENT_COMMA_ABOVE);
            addCombining(788, 701);
            addCombining(789, 700);
            addCombining(795, 39);
            addCombining(803, 46);
            addCombining(807, 184);
            addCombining(808, 731);
            addCombining(809, 716);
            addCombining(817, 717);
            addCombining(821, 45);
            sparseIntArray.append(832, 715);
            sparseIntArray.append(833, 180);
            sparseIntArray.append(835, ACCENT_COMMA_ABOVE);
            sparseIntArray2.append(96, 768);
            sparseIntArray2.append(94, 770);
            sparseIntArray2.append(126, 771);
            sNonstandardDeadCombinations = new SparseIntArray();
            addNonStandardDeadCombination(45, 68, 272);
            addNonStandardDeadCombination(45, 71, 484);
            addNonStandardDeadCombination(45, 72, 294);
            addNonStandardDeadCombination(45, 73, 407);
            addNonStandardDeadCombination(45, 76, 321);
            addNonStandardDeadCombination(45, 79, 216);
            addNonStandardDeadCombination(45, 84, 358);
            addNonStandardDeadCombination(45, 100, 273);
            addNonStandardDeadCombination(45, 103, 485);
            addNonStandardDeadCombination(45, 104, 295);
            addNonStandardDeadCombination(45, 105, 616);
            addNonStandardDeadCombination(45, 108, 322);
            addNonStandardDeadCombination(45, 111, 248);
            addNonStandardDeadCombination(45, 116, 359);
        }

        private static void addCombining(int combining, int accent) {
            sCombiningToAccent.append(combining, accent);
            sAccentToCombining.append(accent, combining);
        }

        private static void addNonStandardDeadCombination(final int deadCodePoint, final int spacingCodePoint, final int result) {
            sNonstandardDeadCombinations.put((deadCodePoint << 16) | spacingCodePoint, result);
        }

        public static char getNonstandardCombination(final int deadCodePoint, final int spacingCodePoint) {
            return (char) sNonstandardDeadCombinations.get((deadCodePoint << 16) | spacingCodePoint, 0);
        }
    }

    @Nonnull
    private static Event createEventChainFromSequence(@Nonnull final CharSequence text, @Nonnull final Event originalEvent) {
        int length = text.length();
        if (length <= 0) {
            return originalEvent;
        }
        Event eventCreateHardwareKeypressEvent = null;
        do {
            int iCodePointBefore = Character.codePointBefore(text, length);
            eventCreateHardwareKeypressEvent = Event.createHardwareKeypressEvent(iCodePointBefore, originalEvent.mKeyCode, eventCreateHardwareKeypressEvent, false);
            length -= Character.charCount(iCodePointBefore);
        } while (length > 0);
        return eventCreateHardwareKeypressEvent;
    }

    @Override // com.android.inputmethod.event.Combiner
    @Nonnull
    public Event processEvent(final ArrayList<Event> previousEvents, final Event event) {
        if (TextUtils.isEmpty(this.mDeadSequence)) {
            if (!event.isDead()) {
                return event;
            }
            this.mDeadSequence.appendCodePoint(event.mCodePoint);
            return Event.createConsumedEvent(event);
        }
        if (!Character.isWhitespace(event.mCodePoint)) {
            int i = event.mCodePoint;
            StringBuilder sb = this.mDeadSequence;
            if (i != sb.codePointBefore(sb.length())) {
                if (event.isFunctionalKeyEvent()) {
                    if (-5 != event.mKeyCode) {
                        return event;
                    }
                    int length = this.mDeadSequence.length();
                    StringBuilder sb2 = this.mDeadSequence;
                    this.mDeadSequence.setLength(length - Character.charCount(sb2.codePointBefore(sb2.length())));
                    return Event.createConsumedEvent(event);
                }
                if (event.isDead()) {
                    this.mDeadSequence.appendCodePoint(event.mCodePoint);
                    return Event.createConsumedEvent(event);
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.appendCodePoint(event.mCodePoint);
                int i2 = 0;
                while (i2 < this.mDeadSequence.length()) {
                    int iCodePointAt = this.mDeadSequence.codePointAt(i2);
                    char nonstandardCombination = Data.getNonstandardCombination(iCodePointAt, event.mCodePoint);
                    if (nonstandardCombination != 0) {
                        sb3.setCharAt(0, nonstandardCombination);
                    } else {
                        int i3 = Data.sAccentToCombining.get(iCodePointAt);
                        if (i3 == 0) {
                            i3 = iCodePointAt;
                        }
                        sb3.appendCodePoint(i3);
                    }
                    i2 += Character.isSupplementaryCodePoint(iCodePointAt) ? 2 : 1;
                }
                Event eventCreateEventChainFromSequence = createEventChainFromSequence(Normalizer.normalize(sb3, Normalizer.Form.NFC), event);
                this.mDeadSequence.setLength(0);
                return eventCreateEventChainFromSequence;
            }
        }
        Event eventCreateEventChainFromSequence2 = createEventChainFromSequence(this.mDeadSequence.toString(), event);
        this.mDeadSequence.setLength(0);
        return eventCreateEventChainFromSequence2;
    }

    @Override // com.android.inputmethod.event.Combiner
    public void reset() {
        this.mDeadSequence.setLength(0);
    }

    @Override // com.android.inputmethod.event.Combiner
    public CharSequence getCombiningStateFeedback() {
        return this.mDeadSequence;
    }
}
