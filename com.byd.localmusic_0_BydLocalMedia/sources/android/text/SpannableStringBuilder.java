package android.text;

import android.graphics.BaseCanvas;
import android.graphics.Paint;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.lang.reflect.Array;
import java.util.IdentityHashMap;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes3.dex */
public class SpannableStringBuilder implements CharSequence, GetChars, Spannable, Editable, Appendable, GraphicsOperations {
    private static final int END_MASK = 15;
    private static final int MARK = 1;
    private static final int PARAGRAPH = 3;
    private static final int POINT = 2;
    private static final int SPAN_ADDED = 2048;
    private static final int SPAN_END_AT_END = 32768;
    private static final int SPAN_END_AT_START = 16384;
    private static final int SPAN_START_AT_END = 8192;
    private static final int SPAN_START_AT_START = 4096;
    private static final int SPAN_START_END_MASK = 61440;
    private static final int START_MASK = 240;
    private static final int START_SHIFT = 4;
    private static final String TAG = "SpannableStringBuilder";
    private InputFilter[] mFilters;
    private int mGapLength;
    private int mGapStart;
    private IdentityHashMap<Object, Integer> mIndexOfSpan;
    private int mLowWaterMark;
    private int mSpanCount;
    private int[] mSpanEnds;
    private int[] mSpanFlags;
    private int mSpanInsertCount;
    private int[] mSpanMax;
    private int[] mSpanOrder;
    private int[] mSpanStarts;
    private Object[] mSpans;
    private char[] mText;
    private int mTextWatcherDepth;
    private static final InputFilter[] NO_FILTERS = new InputFilter[0];
    private static final int[][] sCachedIntBuffer = (int[][]) Array.newInstance((Class<?>) int.class, 6, 0);

    private static int leftChild(int i) {
        return i - (((i + 1) & (~i)) >> 1);
    }

    private static int rightChild(int i) {
        return i + (((i + 1) & (~i)) >> 1);
    }

    public SpannableStringBuilder() {
        this("");
    }

    public SpannableStringBuilder(CharSequence charSequence) {
        this(charSequence, 0, charSequence.length());
    }

    public SpannableStringBuilder(CharSequence charSequence, int i, int i2) {
        this.mFilters = NO_FILTERS;
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new StringIndexOutOfBoundsException();
        }
        char[] cArrNewUnpaddedCharArray = ArrayUtils.newUnpaddedCharArray(GrowingArrayUtils.growSize(i3));
        this.mText = cArrNewUnpaddedCharArray;
        this.mGapStart = i3;
        this.mGapLength = cArrNewUnpaddedCharArray.length - i3;
        TextUtils.getChars(charSequence, i, i2, cArrNewUnpaddedCharArray, 0);
        this.mSpanCount = 0;
        this.mSpanInsertCount = 0;
        this.mSpans = EmptyArray.OBJECT;
        this.mSpanStarts = EmptyArray.INT;
        this.mSpanEnds = EmptyArray.INT;
        this.mSpanFlags = EmptyArray.INT;
        this.mSpanMax = EmptyArray.INT;
        this.mSpanOrder = EmptyArray.INT;
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            Object[] spans = spanned.getSpans(i, i2, Object.class);
            for (int i4 = 0; i4 < spans.length; i4++) {
                if (!(spans[i4] instanceof NoCopySpan)) {
                    int spanStart = spanned.getSpanStart(spans[i4]) - i;
                    int spanEnd = spanned.getSpanEnd(spans[i4]) - i;
                    int spanFlags = spanned.getSpanFlags(spans[i4]);
                    spanStart = spanStart < 0 ? 0 : spanStart;
                    int i5 = spanStart > i3 ? i3 : spanStart;
                    spanEnd = spanEnd < 0 ? 0 : spanEnd;
                    setSpan(false, spans[i4], i5, spanEnd > i3 ? i3 : spanEnd, spanFlags, false);
                }
            }
            restoreInvariants();
        }
    }

    public static SpannableStringBuilder valueOf(CharSequence charSequence) {
        if (charSequence instanceof SpannableStringBuilder) {
            return (SpannableStringBuilder) charSequence;
        }
        return new SpannableStringBuilder(charSequence);
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        int length = length();
        if (i < 0) {
            throw new IndexOutOfBoundsException("charAt: " + i + " < 0");
        }
        if (i >= length) {
            throw new IndexOutOfBoundsException("charAt: " + i + " >= length " + length);
        }
        if (i >= this.mGapStart) {
            return this.mText[i + this.mGapLength];
        }
        return this.mText[i];
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.mText.length - this.mGapLength;
    }

    private void resizeFor(int i) {
        int length = this.mText.length;
        if (i + 1 <= length) {
            return;
        }
        char[] cArrNewUnpaddedCharArray = ArrayUtils.newUnpaddedCharArray(GrowingArrayUtils.growSize(i));
        System.arraycopy(this.mText, 0, cArrNewUnpaddedCharArray, 0, this.mGapStart);
        int length2 = cArrNewUnpaddedCharArray.length;
        int i2 = length2 - length;
        int i3 = length - (this.mGapStart + this.mGapLength);
        System.arraycopy(this.mText, length - i3, cArrNewUnpaddedCharArray, length2 - i3, i3);
        this.mText = cArrNewUnpaddedCharArray;
        int i4 = this.mGapLength + i2;
        this.mGapLength = i4;
        if (i4 < 1) {
            new Exception("mGapLength < 1").printStackTrace();
        }
        if (this.mSpanCount != 0) {
            for (int i5 = 0; i5 < this.mSpanCount; i5++) {
                int[] iArr = this.mSpanStarts;
                if (iArr[i5] > this.mGapStart) {
                    iArr[i5] = iArr[i5] + i2;
                }
                int[] iArr2 = this.mSpanEnds;
                if (iArr2[i5] > this.mGapStart) {
                    iArr2[i5] = iArr2[i5] + i2;
                }
            }
            calcMax(treeRoot());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void moveGapTo(int r8) {
        /*
            r7 = this;
            int r0 = r7.mGapStart
            if (r8 != r0) goto L5
            return
        L5:
            int r0 = r7.length()
            r1 = 0
            if (r8 != r0) goto Le
            r0 = 1
            goto Lf
        Le:
            r0 = r1
        Lf:
            int r2 = r7.mGapStart
            if (r8 >= r2) goto L1f
            int r3 = r2 - r8
            char[] r4 = r7.mText
            int r5 = r7.mGapLength
            int r2 = r2 + r5
            int r2 = r2 - r3
            java.lang.System.arraycopy(r4, r8, r4, r2, r3)
            goto L2a
        L1f:
            int r3 = r8 - r2
            char[] r4 = r7.mText
            int r5 = r7.mGapLength
            int r5 = r5 + r8
            int r5 = r5 - r3
            java.lang.System.arraycopy(r4, r5, r4, r2, r3)
        L2a:
            int r2 = r7.mSpanCount
            if (r2 == 0) goto L8c
        L2e:
            int r2 = r7.mSpanCount
            if (r1 >= r2) goto L85
            int[] r2 = r7.mSpanStarts
            r2 = r2[r1]
            int[] r3 = r7.mSpanEnds
            r3 = r3[r1]
            int r4 = r7.mGapStart
            if (r2 <= r4) goto L41
            int r4 = r7.mGapLength
            int r2 = r2 - r4
        L41:
            r4 = 3
            r5 = 2
            if (r2 <= r8) goto L49
            int r6 = r7.mGapLength
        L47:
            int r2 = r2 + r6
            goto L5c
        L49:
            if (r2 != r8) goto L5c
            int[] r6 = r7.mSpanFlags
            r6 = r6[r1]
            r6 = r6 & 240(0xf0, float:3.36E-43)
            int r6 = r6 >> 4
            if (r6 == r5) goto L59
            if (r0 == 0) goto L5c
            if (r6 != r4) goto L5c
        L59:
            int r6 = r7.mGapLength
            goto L47
        L5c:
            int r6 = r7.mGapStart
            if (r3 <= r6) goto L63
            int r6 = r7.mGapLength
            int r3 = r3 - r6
        L63:
            if (r3 <= r8) goto L69
            int r4 = r7.mGapLength
        L67:
            int r3 = r3 + r4
            goto L7a
        L69:
            if (r3 != r8) goto L7a
            int[] r6 = r7.mSpanFlags
            r6 = r6[r1]
            r6 = r6 & 15
            if (r6 == r5) goto L77
            if (r0 == 0) goto L7a
            if (r6 != r4) goto L7a
        L77:
            int r4 = r7.mGapLength
            goto L67
        L7a:
            int[] r4 = r7.mSpanStarts
            r4[r1] = r2
            int[] r2 = r7.mSpanEnds
            r2[r1] = r3
            int r1 = r1 + 1
            goto L2e
        L85:
            int r0 = r7.treeRoot()
            r7.calcMax(r0)
        L8c:
            r7.mGapStart = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.SpannableStringBuilder.moveGapTo(int):void");
    }

    @Override // android.text.Editable
    public SpannableStringBuilder insert(int i, CharSequence charSequence, int i2, int i3) {
        return replace(i, i, charSequence, i2, i3);
    }

    @Override // android.text.Editable
    public SpannableStringBuilder insert(int i, CharSequence charSequence) {
        return replace(i, i, charSequence, 0, charSequence.length());
    }

    @Override // android.text.Editable
    public SpannableStringBuilder delete(int i, int i2) {
        SpannableStringBuilder spannableStringBuilderReplace = replace(i, i2, "", 0, 0);
        if (this.mGapLength > length() * 2) {
            resizeFor(length());
        }
        return spannableStringBuilderReplace;
    }

    @Override // android.text.Editable
    public void clear() {
        replace(0, length(), "", 0, 0);
        this.mSpanInsertCount = 0;
    }

    @Override // android.text.Editable
    public void clearSpans() {
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            Object obj = this.mSpans[i];
            int i2 = this.mSpanStarts[i];
            int i3 = this.mSpanEnds[i];
            if (i2 > this.mGapStart) {
                i2 -= this.mGapLength;
            }
            if (i3 > this.mGapStart) {
                i3 -= this.mGapLength;
            }
            this.mSpanCount = i;
            this.mSpans[i] = null;
            sendSpanRemoved(obj, i2, i3);
        }
        IdentityHashMap<Object, Integer> identityHashMap = this.mIndexOfSpan;
        if (identityHashMap != null) {
            identityHashMap.clear();
        }
        this.mSpanInsertCount = 0;
    }

    @Override // android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(CharSequence charSequence) {
        int length = length();
        return replace(length, length, charSequence, 0, charSequence.length());
    }

    public SpannableStringBuilder append(CharSequence charSequence, Object obj, int i) {
        int length = length();
        append(charSequence);
        setSpan(obj, length, length(), i);
        return this;
    }

    @Override // android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(CharSequence charSequence, int i, int i2) {
        int length = length();
        return replace(length, length, charSequence, i, i2);
    }

    @Override // android.text.Editable, java.lang.Appendable
    public SpannableStringBuilder append(char c) {
        return append((CharSequence) String.valueOf(c));
    }

    private boolean removeSpansForChange(int i, int i2, boolean z, int i3) {
        int i4 = i3 & 1;
        if (i4 != 0 && resolveGap(this.mSpanMax[i3]) >= i && removeSpansForChange(i, i2, z, leftChild(i3))) {
            return true;
        }
        if (i3 >= this.mSpanCount) {
            return false;
        }
        if ((this.mSpanFlags[i3] & 33) == 33) {
            int[] iArr = this.mSpanStarts;
            if (iArr[i3] >= i) {
                int i5 = iArr[i3];
                int i6 = this.mGapStart;
                int i7 = this.mGapLength;
                if (i5 < i6 + i7) {
                    int[] iArr2 = this.mSpanEnds;
                    if (iArr2[i3] >= i && iArr2[i3] < i7 + i6 && (z || iArr[i3] > i || iArr2[i3] < i6)) {
                        this.mIndexOfSpan.remove(this.mSpans[i3]);
                        removeSpan(i3, 0);
                        return true;
                    }
                }
            }
        }
        return resolveGap(this.mSpanStarts[i3]) <= i2 && i4 != 0 && removeSpansForChange(i, i2, z, rightChild(i3));
    }

    private void change(int i, int i2, CharSequence charSequence, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = i2 - i;
        int i10 = i4 - i3;
        int i11 = i10 - i9;
        int i12 = 1;
        int i13 = this.mSpanCount - 1;
        boolean z = false;
        while (i13 >= 0) {
            int i14 = this.mSpanStarts[i13];
            if (i14 > this.mGapStart) {
                i14 -= this.mGapLength;
            }
            int i15 = this.mSpanEnds[i13];
            if (i15 > this.mGapStart) {
                i15 -= this.mGapLength;
            }
            if ((this.mSpanFlags[i13] & 51) == 51) {
                int length = length();
                if (i14 <= i || i14 > i2) {
                    i6 = i14;
                } else {
                    int i16 = i2;
                    while (i16 < length && (i16 <= i2 || charAt(i16 - 1) != '\n')) {
                        i16++;
                    }
                    i6 = i16;
                }
                if (i15 <= i || i15 > i2) {
                    i7 = i15;
                } else {
                    i7 = i2;
                    while (i7 < length) {
                        if (i7 > i2) {
                            i8 = length;
                            if (charAt(i7 - 1) == '\n') {
                                break;
                            }
                        } else {
                            i8 = length;
                        }
                        i7++;
                        length = i8;
                    }
                }
                if (i6 == i14 && i7 == i15) {
                    i15 = i7;
                    i14 = i6;
                    i5 = 1;
                } else {
                    i5 = 1;
                    setSpan(false, this.mSpans[i13], i6, i7, this.mSpanFlags[i13], true);
                    z = true;
                    i14 = i6;
                    i15 = i7;
                }
            } else {
                i5 = i12;
            }
            int i17 = i14 == i ? 4096 : i14 == i2 + i11 ? 8192 : 0;
            if (i15 == i) {
                i17 |= 16384;
            } else if (i15 == i2 + i11) {
                i17 |= 32768;
            }
            int[] iArr = this.mSpanFlags;
            iArr[i13] = i17 | iArr[i13];
            i13--;
            i12 = i5;
        }
        int i18 = i12;
        if (z) {
            restoreInvariants();
        }
        moveGapTo(i2);
        int i19 = this.mGapLength;
        if (i11 >= i19) {
            resizeFor((this.mText.length + i11) - i19);
        }
        boolean z2 = i10 == 0 ? i18 : false;
        if (i9 > 0) {
            while (this.mSpanCount > 0 && removeSpansForChange(i, i2, z2, treeRoot())) {
            }
        }
        this.mGapStart += i11;
        int i20 = this.mGapLength - i11;
        this.mGapLength = i20;
        if (i20 < i18) {
            new Exception("mGapLength < 1").printStackTrace();
        }
        TextUtils.getChars(charSequence, i3, i4, this.mText, i);
        if (i9 > 0) {
            int i21 = this.mGapStart + this.mGapLength == this.mText.length ? i18 : 0;
            for (int i22 = 0; i22 < this.mSpanCount; i22++) {
                int i23 = (this.mSpanFlags[i22] & 240) >> 4;
                int[] iArr2 = this.mSpanStarts;
                boolean z3 = i21;
                iArr2[i22] = updatedIntervalBound(iArr2[i22], i, i11, i23, z3, z2);
                int i24 = this.mSpanFlags[i22] & 15;
                int[] iArr3 = this.mSpanEnds;
                iArr3[i22] = updatedIntervalBound(iArr3[i22], i, i11, i24, z3, z2);
            }
            restoreInvariants();
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            Object[] spans = spanned.getSpans(i3, i4, Object.class);
            for (int i25 = 0; i25 < spans.length; i25++) {
                int spanStart = spanned.getSpanStart(spans[i25]);
                int spanEnd = spanned.getSpanEnd(spans[i25]);
                if (spanStart < i3) {
                    spanStart = i3;
                }
                if (spanEnd > i4) {
                    spanEnd = i4;
                }
                if (getSpanStart(spans[i25]) < 0) {
                    setSpan(false, spans[i25], (spanStart - i3) + i, (spanEnd - i3) + i, spanned.getSpanFlags(spans[i25]) | 2048, false);
                }
            }
            restoreInvariants();
        }
    }

    private int updatedIntervalBound(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (i >= i2) {
            int i5 = this.mGapStart;
            int i6 = this.mGapLength;
            if (i < i5 + i6) {
                if (i4 == 2) {
                    if (z2 || i > i2) {
                        return this.mGapStart + this.mGapLength;
                    }
                } else {
                    if (i4 != 3) {
                        return (z2 || i < i5 - i3) ? i2 : i5;
                    }
                    if (z) {
                        return i5 + i6;
                    }
                }
            }
        }
        return i;
    }

    private void removeSpan(int i, int i2) {
        Object obj = this.mSpans[i];
        int i3 = this.mSpanStarts[i];
        int i4 = this.mSpanEnds[i];
        if (i3 > this.mGapStart) {
            i3 -= this.mGapLength;
        }
        if (i4 > this.mGapStart) {
            i4 -= this.mGapLength;
        }
        int i5 = i + 1;
        int i6 = this.mSpanCount - i5;
        Object[] objArr = this.mSpans;
        System.arraycopy(objArr, i5, objArr, i, i6);
        int[] iArr = this.mSpanStarts;
        System.arraycopy(iArr, i5, iArr, i, i6);
        int[] iArr2 = this.mSpanEnds;
        System.arraycopy(iArr2, i5, iArr2, i, i6);
        int[] iArr3 = this.mSpanFlags;
        System.arraycopy(iArr3, i5, iArr3, i, i6);
        int[] iArr4 = this.mSpanOrder;
        System.arraycopy(iArr4, i5, iArr4, i, i6);
        this.mSpanCount--;
        invalidateIndex(i);
        this.mSpans[this.mSpanCount] = null;
        restoreInvariants();
        if ((i2 & 512) == 0) {
            sendSpanRemoved(obj, i3, i4);
        }
    }

    @Override // android.text.Editable
    public SpannableStringBuilder replace(int i, int i2, CharSequence charSequence) {
        return replace(i, i2, charSequence, 0, charSequence.length());
    }

    @Override // android.text.Editable
    public SpannableStringBuilder replace(int i, int i2, CharSequence charSequence, int i3, int i4) {
        int selectionEnd;
        int selectionStart;
        TextWatcher[] textWatcherArr;
        checkRange("replace", i, i2);
        int length = this.mFilters.length;
        boolean z = false;
        CharSequence charSequence2 = charSequence;
        int i5 = i3;
        int length2 = i4;
        for (int i6 = 0; i6 < length; i6++) {
            CharSequence charSequenceFilter = this.mFilters[i6].filter(charSequence2, i5, length2, this, i, i2);
            if (charSequenceFilter != null) {
                charSequence2 = charSequenceFilter;
                length2 = charSequenceFilter.length();
                i5 = 0;
            }
        }
        int i7 = i2 - i;
        int i8 = length2 - i5;
        if (i7 == 0 && i8 == 0 && !hasNonExclusiveExclusiveSpanAt(charSequence2, i5)) {
            return this;
        }
        TextWatcher[] textWatcherArr2 = (TextWatcher[]) getSpans(i, i + i7, TextWatcher.class);
        sendBeforeTextChanged(textWatcherArr2, i, i7, i8);
        boolean z2 = true;
        boolean z3 = (i7 == 0 || i8 == 0) ? false : true;
        if (z3) {
            selectionStart = Selection.getSelectionStart(this);
            selectionEnd = Selection.getSelectionEnd(this);
        } else {
            selectionEnd = 0;
            selectionStart = 0;
        }
        CharSequence charSequence3 = charSequence2;
        int i9 = selectionEnd;
        int i10 = i5;
        int i11 = selectionStart;
        change(i, i2, charSequence3, i10, length2);
        if (z3) {
            if (i11 <= i || i11 >= i2) {
                textWatcherArr = textWatcherArr2;
            } else {
                int intExact = i + Math.toIntExact((((long) (i11 - i)) * ((long) i8)) / ((long) i7));
                textWatcherArr = textWatcherArr2;
                setSpan(false, Selection.SELECTION_START, intExact, intExact, 34, true);
                z = true;
            }
            if (i9 <= i || i9 >= i2) {
                z2 = z;
            } else {
                int intExact2 = i + Math.toIntExact((((long) (i9 - i)) * ((long) i8)) / ((long) i7));
                setSpan(false, Selection.SELECTION_END, intExact2, intExact2, 34, true);
            }
            if (z2) {
                restoreInvariants();
            }
        } else {
            textWatcherArr = textWatcherArr2;
        }
        sendTextChanged(textWatcherArr, i, i7, i8);
        sendAfterTextChanged(textWatcherArr);
        sendToSpanWatchers(i, i2, i8 - i7);
        return this;
    }

    private static boolean hasNonExclusiveExclusiveSpanAt(CharSequence charSequence, int i) {
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            for (Object obj : spanned.getSpans(i, i, Object.class)) {
                if (spanned.getSpanFlags(obj) != 33) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void sendToSpanWatchers(int r12, int r13, int r14) {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r11.mSpanCount
            if (r1 >= r2) goto L7e
            int[] r2 = r11.mSpanFlags
            r2 = r2[r1]
            r3 = r2 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L10
            goto L7b
        L10:
            int[] r3 = r11.mSpanStarts
            r3 = r3[r1]
            int[] r4 = r11.mSpanEnds
            r4 = r4[r1]
            int r5 = r11.mGapStart
            if (r3 <= r5) goto L1f
            int r5 = r11.mGapLength
            int r3 = r3 - r5
        L1f:
            r9 = r3
            int r3 = r11.mGapStart
            if (r4 <= r3) goto L27
            int r3 = r11.mGapLength
            int r4 = r4 - r3
        L27:
            r10 = r4
            int r3 = r13 + r14
            r4 = 1
            if (r9 <= r3) goto L34
            if (r14 == 0) goto L48
            int r5 = r9 - r14
            r7 = r5
            r5 = r4
            goto L4a
        L34:
            if (r9 < r12) goto L48
            if (r9 != r12) goto L3e
            r5 = r2 & 4096(0x1000, float:5.74E-42)
            r6 = 4096(0x1000, float:5.74E-42)
            if (r5 == r6) goto L48
        L3e:
            if (r9 != r3) goto L46
            r5 = r2 & 8192(0x2000, float:1.148E-41)
            r6 = 8192(0x2000, float:1.148E-41)
            if (r5 == r6) goto L48
        L46:
            r5 = r4
            goto L49
        L48:
            r5 = r0
        L49:
            r7 = r9
        L4a:
            if (r10 <= r3) goto L52
            if (r14 == 0) goto L65
            int r2 = r10 - r14
            r8 = r2
            goto L67
        L52:
            if (r10 < r12) goto L65
            if (r10 != r12) goto L5c
            r6 = r2 & 16384(0x4000, float:2.2959E-41)
            r8 = 16384(0x4000, float:2.2959E-41)
            if (r6 == r8) goto L65
        L5c:
            if (r10 != r3) goto L66
            r3 = 32768(0x8000, float:4.5918E-41)
            r2 = r2 & r3
            if (r2 == r3) goto L65
            goto L66
        L65:
            r4 = r5
        L66:
            r8 = r10
        L67:
            if (r4 == 0) goto L71
            java.lang.Object[] r2 = r11.mSpans
            r6 = r2[r1]
            r5 = r11
            r5.sendSpanChanged(r6, r7, r8, r9, r10)
        L71:
            int[] r2 = r11.mSpanFlags
            r3 = r2[r1]
            r4 = -61441(0xffffffffffff0fff, float:NaN)
            r3 = r3 & r4
            r2[r1] = r3
        L7b:
            int r1 = r1 + 1
            goto L2
        L7e:
            int r12 = r11.mSpanCount
            if (r0 >= r12) goto Lb0
            int[] r12 = r11.mSpanFlags
            r13 = r12[r0]
            r13 = r13 & 2048(0x800, float:2.87E-42)
            if (r13 == 0) goto Lad
            r13 = r12[r0]
            r13 = r13 & (-2049(0xfffffffffffff7ff, float:NaN))
            r12[r0] = r13
            int[] r12 = r11.mSpanStarts
            r12 = r12[r0]
            int[] r13 = r11.mSpanEnds
            r13 = r13[r0]
            int r14 = r11.mGapStart
            if (r12 <= r14) goto L9f
            int r14 = r11.mGapLength
            int r12 = r12 - r14
        L9f:
            int r14 = r11.mGapStart
            if (r13 <= r14) goto La6
            int r14 = r11.mGapLength
            int r13 = r13 - r14
        La6:
            java.lang.Object[] r14 = r11.mSpans
            r14 = r14[r0]
            r11.sendSpanAdded(r14, r12, r13)
        Lad:
            int r0 = r0 + 1
            goto L7e
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.SpannableStringBuilder.sendToSpanWatchers(int, int, int):void");
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i, int i2, int i3) {
        setSpan(true, obj, i, i2, i3, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setSpan(boolean r12, java.lang.Object r13, int r14, int r15, int r16, boolean r17) {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.SpannableStringBuilder.setSpan(boolean, java.lang.Object, int, int, int, boolean):void");
    }

    private boolean isInvalidParagraph(int i, int i2) {
        return (i2 != 3 || i == 0 || i == length() || charAt(i - 1) == '\n') ? false : true;
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        removeSpan(obj, 0);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj, int i) {
        Integer numRemove;
        IdentityHashMap<Object, Integer> identityHashMap = this.mIndexOfSpan;
        if (identityHashMap == null || (numRemove = identityHashMap.remove(obj)) == null) {
            return;
        }
        removeSpan(numRemove.intValue(), i);
    }

    private int resolveGap(int i) {
        return i > this.mGapStart ? i - this.mGapLength : i;
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        Integer num;
        IdentityHashMap<Object, Integer> identityHashMap = this.mIndexOfSpan;
        if (identityHashMap == null || (num = identityHashMap.get(obj)) == null) {
            return -1;
        }
        return resolveGap(this.mSpanStarts[num.intValue()]);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        Integer num;
        IdentityHashMap<Object, Integer> identityHashMap = this.mIndexOfSpan;
        if (identityHashMap == null || (num = identityHashMap.get(obj)) == null) {
            return -1;
        }
        return resolveGap(this.mSpanEnds[num.intValue()]);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        Integer num;
        IdentityHashMap<Object, Integer> identityHashMap = this.mIndexOfSpan;
        if (identityHashMap == null || (num = identityHashMap.get(obj)) == null) {
            return 0;
        }
        return this.mSpanFlags[num.intValue()];
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return (T[]) getSpans(i, i2, cls, true);
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls, boolean z) {
        if (cls == null) {
            return (T[]) ArrayUtils.emptyArray(Object.class);
        }
        if (this.mSpanCount == 0) {
            return (T[]) ArrayUtils.emptyArray(cls);
        }
        int iCountSpans = countSpans(i, i2, cls, treeRoot());
        if (iCountSpans == 0) {
            return (T[]) ArrayUtils.emptyArray(cls);
        }
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, iCountSpans));
        int[] iArrObtain = z ? obtain(iCountSpans) : EmptyArray.INT;
        int[] iArrObtain2 = z ? obtain(iCountSpans) : EmptyArray.INT;
        getSpansRec(i, i2, cls, treeRoot(), tArr, iArrObtain, iArrObtain2, 0, z);
        if (z) {
            sort(tArr, iArrObtain, iArrObtain2);
            recycle(iArrObtain);
            recycle(iArrObtain2);
        }
        return tArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int countSpans(int r6, int r7, java.lang.Class r8, int r9) {
        /*
            r5 = this;
            r0 = r9 & 1
            if (r0 == 0) goto L1a
            int r1 = leftChild(r9)
            int[] r2 = r5.mSpanMax
            r2 = r2[r1]
            int r3 = r5.mGapStart
            if (r2 <= r3) goto L13
            int r3 = r5.mGapLength
            int r2 = r2 - r3
        L13:
            if (r2 < r6) goto L1a
            int r1 = r5.countSpans(r6, r7, r8, r1)
            goto L1b
        L1a:
            r1 = 0
        L1b:
            int r2 = r5.mSpanCount
            if (r9 >= r2) goto L5c
            int[] r2 = r5.mSpanStarts
            r2 = r2[r9]
            int r3 = r5.mGapStart
            if (r2 <= r3) goto L2a
            int r3 = r5.mGapLength
            int r2 = r2 - r3
        L2a:
            if (r2 > r7) goto L5c
            int[] r3 = r5.mSpanEnds
            r3 = r3[r9]
            int r4 = r5.mGapStart
            if (r3 <= r4) goto L37
            int r4 = r5.mGapLength
            int r3 = r3 - r4
        L37:
            if (r3 < r6) goto L51
            if (r2 == r3) goto L41
            if (r6 == r7) goto L41
            if (r2 == r7) goto L51
            if (r3 == r6) goto L51
        L41:
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r2 == r8) goto L4f
            java.lang.Object[] r2 = r5.mSpans
            r2 = r2[r9]
            boolean r2 = r8.isInstance(r2)
            if (r2 == 0) goto L51
        L4f:
            int r1 = r1 + 1
        L51:
            if (r0 == 0) goto L5c
            int r9 = rightChild(r9)
            int r6 = r5.countSpans(r6, r7, r8, r9)
            int r1 = r1 + r6
        L5c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.SpannableStringBuilder.countSpans(int, int, java.lang.Class, int):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> int getSpansRec(int r18, int r19, java.lang.Class<T> r20, int r21, T[] r22, int[] r23, int[] r24, int r25, boolean r26) {
        /*
            r17 = this;
            r10 = r17
            r11 = r18
            r12 = r19
            r13 = r20
            r14 = r21
            r15 = r22
            r16 = r14 & 1
            if (r16 == 0) goto L38
            int r4 = leftChild(r21)
            int[] r0 = r10.mSpanMax
            r0 = r0[r4]
            int r1 = r10.mGapStart
            if (r0 <= r1) goto L1f
            int r1 = r10.mGapLength
            int r0 = r0 - r1
        L1f:
            if (r0 < r11) goto L38
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r25
            r9 = r26
            int r0 = r0.getSpansRec(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            goto L3a
        L38:
            r0 = r25
        L3a:
            int r1 = r10.mSpanCount
            if (r14 < r1) goto L3f
            return r0
        L3f:
            int[] r1 = r10.mSpanStarts
            r1 = r1[r14]
            int r2 = r10.mGapStart
            if (r1 <= r2) goto L4a
            int r2 = r10.mGapLength
            int r1 = r1 - r2
        L4a:
            if (r1 > r12) goto Lc4
            int[] r2 = r10.mSpanEnds
            r2 = r2[r14]
            int r3 = r10.mGapStart
            if (r2 <= r3) goto L57
            int r3 = r10.mGapLength
            int r2 = r2 - r3
        L57:
            if (r2 < r11) goto La4
            if (r1 == r2) goto L61
            if (r11 == r12) goto L61
            if (r1 == r12) goto La4
            if (r2 == r11) goto La4
        L61:
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            if (r1 == r13) goto L6f
            java.lang.Object[] r1 = r10.mSpans
            r1 = r1[r14]
            boolean r1 = r13.isInstance(r1)
            if (r1 == 0) goto La4
        L6f:
            int[] r1 = r10.mSpanFlags
            r1 = r1[r14]
            r2 = 16711680(0xff0000, float:2.3418052E-38)
            r1 = r1 & r2
            if (r26 == 0) goto L81
            r23[r0] = r1
            int[] r1 = r10.mSpanOrder
            r1 = r1[r14]
            r24[r0] = r1
            goto L9b
        L81:
            if (r1 == 0) goto L9b
            r3 = 0
        L84:
            if (r3 >= r0) goto L93
            r4 = r15[r3]
            int r4 = r10.getSpanFlags(r4)
            r4 = r4 & r2
            if (r1 <= r4) goto L90
            goto L93
        L90:
            int r3 = r3 + 1
            goto L84
        L93:
            int r1 = r3 + 1
            int r2 = r0 - r3
            java.lang.System.arraycopy(r15, r3, r15, r1, r2)
            goto L9c
        L9b:
            r3 = r0
        L9c:
            java.lang.Object[] r1 = r10.mSpans
            r1 = r1[r14]
            r15[r3] = r1
            int r0 = r0 + 1
        La4:
            r8 = r0
            int r0 = r15.length
            if (r8 >= r0) goto Lc3
            if (r16 == 0) goto Lc3
            int r4 = rightChild(r21)
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r5 = r22
            r6 = r23
            r7 = r24
            r9 = r26
            int r0 = r0.getSpansRec(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            goto Lc4
        Lc3:
            r0 = r8
        Lc4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.SpannableStringBuilder.getSpansRec(int, int, java.lang.Class, int, java.lang.Object[], int[], int[], int, boolean):int");
    }

    private static int[] obtain(int i) {
        int[] iArr;
        synchronized (sCachedIntBuffer) {
            int length = sCachedIntBuffer.length - 1;
            int i2 = -1;
            while (true) {
                if (length < 0) {
                    length = i2;
                    break;
                }
                if (sCachedIntBuffer[length] != null) {
                    if (sCachedIntBuffer[length].length >= i) {
                        break;
                    }
                    if (i2 == -1) {
                        i2 = length;
                    }
                }
                length--;
            }
            iArr = null;
            if (length != -1) {
                int[] iArr2 = sCachedIntBuffer[length];
                sCachedIntBuffer[length] = null;
                iArr = iArr2;
            }
        }
        return checkSortBuffer(iArr, i);
    }

    private static void recycle(int[] iArr) {
        synchronized (sCachedIntBuffer) {
            for (int i = 0; i < sCachedIntBuffer.length; i++) {
                if (sCachedIntBuffer[i] != null && iArr.length <= sCachedIntBuffer[i].length) {
                }
                sCachedIntBuffer[i] = iArr;
            }
        }
    }

    private static int[] checkSortBuffer(int[] iArr, int i) {
        return (iArr == null || i > iArr.length) ? ArrayUtils.newUnpaddedIntArray(GrowingArrayUtils.growSize(i)) : iArr;
    }

    private final <T> void sort(T[] tArr, int[] iArr, int[] iArr2) {
        int length = tArr.length;
        for (int i = (length / 2) - 1; i >= 0; i--) {
            siftDown(i, tArr, length, iArr, iArr2);
        }
        for (int i2 = length - 1; i2 > 0; i2--) {
            T t = tArr[0];
            tArr[0] = tArr[i2];
            tArr[i2] = t;
            int i3 = iArr[0];
            iArr[0] = iArr[i2];
            iArr[i2] = i3;
            int i4 = iArr2[0];
            iArr2[0] = iArr2[i2];
            iArr2[i2] = i4;
            siftDown(0, tArr, i2, iArr, iArr2);
        }
    }

    private final <T> void siftDown(int i, T[] tArr, int i2, int[] iArr, int[] iArr2) {
        int i3 = (i * 2) + 1;
        while (i3 < i2) {
            if (i3 < i2 - 1) {
                int i4 = i3 + 1;
                if (compareSpans(i3, i4, iArr, iArr2) < 0) {
                    i3 = i4;
                }
            }
            if (compareSpans(i, i3, iArr, iArr2) >= 0) {
                return;
            }
            T t = tArr[i];
            tArr[i] = tArr[i3];
            tArr[i3] = t;
            int i5 = iArr[i];
            iArr[i] = iArr[i3];
            iArr[i3] = i5;
            int i6 = iArr2[i];
            iArr2[i] = iArr2[i3];
            iArr2[i3] = i6;
            int i7 = i3;
            i3 = (i3 * 2) + 1;
            i = i7;
        }
    }

    private final int compareSpans(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = iArr[i];
        int i4 = iArr[i2];
        if (i3 == i4) {
            return Integer.compare(iArr2[i], iArr2[i2]);
        }
        return Integer.compare(i4, i3);
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        if (this.mSpanCount == 0) {
            return i2;
        }
        if (cls == null) {
            cls = Object.class;
        }
        return nextSpanTransitionRec(i, i2, cls, treeRoot());
    }

    private int nextSpanTransitionRec(int i, int i2, Class cls, int i3) {
        int i4 = i3 & 1;
        if (i4 != 0) {
            int iLeftChild = leftChild(i3);
            if (resolveGap(this.mSpanMax[iLeftChild]) > i) {
                i2 = nextSpanTransitionRec(i, i2, cls, iLeftChild);
            }
        }
        if (i3 >= this.mSpanCount) {
            return i2;
        }
        int iResolveGap = resolveGap(this.mSpanStarts[i3]);
        int iResolveGap2 = resolveGap(this.mSpanEnds[i3]);
        if (iResolveGap > i && iResolveGap < i2 && cls.isInstance(this.mSpans[i3])) {
            i2 = iResolveGap;
        }
        if (iResolveGap2 > i && iResolveGap2 < i2 && cls.isInstance(this.mSpans[i3])) {
            i2 = iResolveGap2;
        }
        return (iResolveGap >= i2 || i4 == 0) ? i2 : nextSpanTransitionRec(i, i2, cls, rightChild(i3));
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return new SpannableStringBuilder(this, i, i2);
    }

    @Override // android.text.GetChars
    public void getChars(int i, int i2, char[] cArr, int i3) {
        checkRange("getChars", i, i2);
        int i4 = this.mGapStart;
        if (i2 <= i4) {
            System.arraycopy(this.mText, i, cArr, i3, i2 - i);
            return;
        }
        if (i >= i4) {
            System.arraycopy(this.mText, this.mGapLength + i, cArr, i3, i2 - i);
            return;
        }
        System.arraycopy(this.mText, i, cArr, i3, i4 - i);
        char[] cArr2 = this.mText;
        int i5 = this.mGapStart;
        System.arraycopy(cArr2, this.mGapLength + i5, cArr, i3 + (i5 - i), i2 - i5);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        int length = length();
        char[] cArr = new char[length];
        getChars(0, length, cArr, 0);
        return new String(cArr);
    }

    public String substring(int i, int i2) {
        char[] cArr = new char[i2 - i];
        getChars(i, i2, cArr, 0);
        return new String(cArr);
    }

    public int getTextWatcherDepth() {
        return this.mTextWatcherDepth;
    }

    private void sendBeforeTextChanged(TextWatcher[] textWatcherArr, int i, int i2, int i3) {
        this.mTextWatcherDepth++;
        for (TextWatcher textWatcher : textWatcherArr) {
            textWatcher.beforeTextChanged(this, i, i2, i3);
        }
        this.mTextWatcherDepth--;
    }

    private void sendTextChanged(TextWatcher[] textWatcherArr, int i, int i2, int i3) {
        this.mTextWatcherDepth++;
        for (TextWatcher textWatcher : textWatcherArr) {
            textWatcher.onTextChanged(this, i, i2, i3);
        }
        this.mTextWatcherDepth--;
    }

    private void sendAfterTextChanged(TextWatcher[] textWatcherArr) {
        this.mTextWatcherDepth++;
        for (TextWatcher textWatcher : textWatcherArr) {
            textWatcher.afterTextChanged(this);
        }
        this.mTextWatcherDepth--;
    }

    private void sendSpanAdded(Object obj, int i, int i2) {
        for (SpanWatcher spanWatcher : (SpanWatcher[]) getSpans(i, i2, SpanWatcher.class)) {
            spanWatcher.onSpanAdded(this, obj, i, i2);
        }
    }

    private void sendSpanRemoved(Object obj, int i, int i2) {
        for (SpanWatcher spanWatcher : (SpanWatcher[]) getSpans(i, i2, SpanWatcher.class)) {
            spanWatcher.onSpanRemoved(this, obj, i, i2);
        }
    }

    private void sendSpanChanged(Object obj, int i, int i2, int i3, int i4) {
        for (SpanWatcher spanWatcher : (SpanWatcher[]) getSpans(Math.min(i, i3), Math.min(Math.max(i2, i4), length()), SpanWatcher.class)) {
            spanWatcher.onSpanChanged(this, obj, i, i2, i3, i4);
        }
    }

    private static String region(int i, int i2) {
        return "(" + i + " ... " + i2 + ")";
    }

    private void checkRange(String str, int i, int i2) {
        if (i2 < i) {
            throw new IndexOutOfBoundsException(str + " " + region(i, i2) + " has end before start");
        }
        int length = length();
        if (i > length || i2 > length) {
            throw new IndexOutOfBoundsException(str + " " + region(i, i2) + " ends beyond length " + length);
        }
        if (i < 0 || i2 < 0) {
            throw new IndexOutOfBoundsException(str + " " + region(i, i2) + " starts before 0");
        }
    }

    @Override // android.text.GraphicsOperations
    public void drawText(BaseCanvas baseCanvas, int i, int i2, float f, float f2, Paint paint) {
        checkRange("drawText", i, i2);
        int i3 = this.mGapStart;
        if (i2 <= i3) {
            baseCanvas.drawText(this.mText, i, i2 - i, f, f2, paint);
            return;
        }
        if (i >= i3) {
            baseCanvas.drawText(this.mText, i + this.mGapLength, i2 - i, f, f2, paint);
            return;
        }
        int i4 = i2 - i;
        char[] cArrObtain = TextUtils.obtain(i4);
        getChars(i, i2, cArrObtain, 0);
        baseCanvas.drawText(cArrObtain, 0, i4, f, f2, paint);
        TextUtils.recycle(cArrObtain);
    }

    @Override // android.text.GraphicsOperations
    public void drawTextRun(BaseCanvas baseCanvas, int i, int i2, int i3, int i4, float f, float f2, boolean z, Paint paint) {
        checkRange("drawTextRun", i, i2);
        int i5 = i4 - i3;
        int i6 = i2 - i;
        int i7 = this.mGapStart;
        if (i4 <= i7) {
            baseCanvas.drawTextRun(this.mText, i, i6, i3, i5, f, f2, z, paint);
            return;
        }
        if (i3 >= i7) {
            char[] cArr = this.mText;
            int i8 = this.mGapLength;
            baseCanvas.drawTextRun(cArr, i + i8, i6, i3 + i8, i5, f, f2, z, paint);
        } else {
            char[] cArrObtain = TextUtils.obtain(i5);
            getChars(i3, i4, cArrObtain, 0);
            baseCanvas.drawTextRun(cArrObtain, i - i3, i6, 0, i5, f, f2, z, paint);
            TextUtils.recycle(cArrObtain);
        }
    }

    @Override // android.text.GraphicsOperations
    public float measureText(int i, int i2, Paint paint) {
        checkRange("measureText", i, i2);
        int i3 = this.mGapStart;
        if (i2 <= i3) {
            return paint.measureText(this.mText, i, i2 - i);
        }
        if (i >= i3) {
            return paint.measureText(this.mText, this.mGapLength + i, i2 - i);
        }
        int i4 = i2 - i;
        char[] cArrObtain = TextUtils.obtain(i4);
        getChars(i, i2, cArrObtain, 0);
        float fMeasureText = paint.measureText(cArrObtain, 0, i4);
        TextUtils.recycle(cArrObtain);
        return fMeasureText;
    }

    @Override // android.text.GraphicsOperations
    public int getTextWidths(int i, int i2, float[] fArr, Paint paint) {
        checkRange("getTextWidths", i, i2);
        int i3 = this.mGapStart;
        if (i2 <= i3) {
            return paint.getTextWidths(this.mText, i, i2 - i, fArr);
        }
        if (i >= i3) {
            return paint.getTextWidths(this.mText, this.mGapLength + i, i2 - i, fArr);
        }
        int i4 = i2 - i;
        char[] cArrObtain = TextUtils.obtain(i4);
        getChars(i, i2, cArrObtain, 0);
        int textWidths = paint.getTextWidths(cArrObtain, 0, i4, fArr);
        TextUtils.recycle(cArrObtain);
        return textWidths;
    }

    @Override // android.text.GraphicsOperations
    public float getTextRunAdvances(int i, int i2, int i3, int i4, boolean z, float[] fArr, int i5, Paint paint) {
        int i6 = i4 - i3;
        int i7 = i2 - i;
        int i8 = this.mGapStart;
        if (i2 <= i8) {
            return paint.getTextRunAdvances(this.mText, i, i7, i3, i6, z, fArr, i5);
        }
        if (i >= i8) {
            char[] cArr = this.mText;
            int i9 = this.mGapLength;
            return paint.getTextRunAdvances(cArr, i + i9, i7, i3 + i9, i6, z, fArr, i5);
        }
        char[] cArrObtain = TextUtils.obtain(i6);
        getChars(i3, i4, cArrObtain, 0);
        float textRunAdvances = paint.getTextRunAdvances(cArrObtain, i - i3, i7, 0, i6, z, fArr, i5);
        TextUtils.recycle(cArrObtain);
        return textRunAdvances;
    }

    @Deprecated
    public int getTextRunCursor(int i, int i2, int i3, int i4, int i5, Paint paint) {
        return getTextRunCursor(i, i2, i3 == 1, i4, i5, paint);
    }

    @Override // android.text.GraphicsOperations
    public int getTextRunCursor(int i, int i2, boolean z, int i3, int i4, Paint paint) {
        int i5 = i2 - i;
        int i6 = this.mGapStart;
        if (i2 <= i6) {
            return paint.getTextRunCursor(this.mText, i, i5, z, i3, i4);
        }
        if (i >= i6) {
            char[] cArr = this.mText;
            int i7 = this.mGapLength;
            return paint.getTextRunCursor(cArr, i + i7, i5, z, i3 + i7, i4) - this.mGapLength;
        }
        char[] cArrObtain = TextUtils.obtain(i5);
        getChars(i, i2, cArrObtain, 0);
        int textRunCursor = i + paint.getTextRunCursor(cArrObtain, 0, i5, z, i3 - i, i4);
        TextUtils.recycle(cArrObtain);
        return textRunCursor;
    }

    @Override // android.text.Editable
    public void setFilters(InputFilter[] inputFilterArr) {
        if (inputFilterArr == null) {
            throw new IllegalArgumentException();
        }
        this.mFilters = inputFilterArr;
    }

    @Override // android.text.Editable
    public InputFilter[] getFilters() {
        return this.mFilters;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof Spanned) && toString().equals(obj.toString())) {
            Spanned spanned = (Spanned) obj;
            Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
            Object[] spans2 = getSpans(0, length(), Object.class);
            if (this.mSpanCount == spans.length) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    Object obj2 = spans2[i];
                    Object obj3 = spans[i];
                    if (obj2 == this) {
                        if (spanned != obj3 || getSpanStart(obj2) != spanned.getSpanStart(obj3) || getSpanEnd(obj2) != spanned.getSpanEnd(obj3) || getSpanFlags(obj2) != spanned.getSpanFlags(obj3)) {
                            return false;
                        }
                    } else if (!obj2.equals(obj3) || getSpanStart(obj2) != spanned.getSpanStart(obj3) || getSpanEnd(obj2) != spanned.getSpanEnd(obj3) || getSpanFlags(obj2) != spanned.getSpanFlags(obj3)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (toString().hashCode() * 31) + this.mSpanCount;
        for (int i = 0; i < this.mSpanCount; i++) {
            Object obj = this.mSpans[i];
            if (obj != this) {
                iHashCode = (iHashCode * 31) + obj.hashCode();
            }
            iHashCode = (((((iHashCode * 31) + getSpanStart(obj)) * 31) + getSpanEnd(obj)) * 31) + getSpanFlags(obj);
        }
        return iHashCode;
    }

    private int treeRoot() {
        return Integer.highestOneBit(this.mSpanCount) - 1;
    }

    private int calcMax(int i) {
        int i2 = i & 1;
        int iCalcMax = i2 != 0 ? calcMax(leftChild(i)) : 0;
        if (i < this.mSpanCount) {
            iCalcMax = Math.max(iCalcMax, this.mSpanEnds[i]);
            if (i2 != 0) {
                iCalcMax = Math.max(iCalcMax, calcMax(rightChild(i)));
            }
        }
        this.mSpanMax[i] = iCalcMax;
        return iCalcMax;
    }

    private void restoreInvariants() {
        int[] iArr;
        if (this.mSpanCount == 0) {
            return;
        }
        for (int i = 1; i < this.mSpanCount; i++) {
            int[] iArr2 = this.mSpanStarts;
            if (iArr2[i] < iArr2[i - 1]) {
                Object obj = this.mSpans[i];
                int i2 = iArr2[i];
                int i3 = this.mSpanEnds[i];
                int i4 = this.mSpanFlags[i];
                int i5 = this.mSpanOrder[i];
                int i6 = i;
                do {
                    Object[] objArr = this.mSpans;
                    int i7 = i6 - 1;
                    objArr[i6] = objArr[i7];
                    iArr = this.mSpanStarts;
                    iArr[i6] = iArr[i7];
                    int[] iArr3 = this.mSpanEnds;
                    iArr3[i6] = iArr3[i7];
                    int[] iArr4 = this.mSpanFlags;
                    iArr4[i6] = iArr4[i7];
                    int[] iArr5 = this.mSpanOrder;
                    iArr5[i6] = iArr5[i7];
                    i6--;
                    if (i6 <= 0) {
                        break;
                    }
                } while (i2 < iArr[i6 - 1]);
                this.mSpans[i6] = obj;
                this.mSpanStarts[i6] = i2;
                this.mSpanEnds[i6] = i3;
                this.mSpanFlags[i6] = i4;
                this.mSpanOrder[i6] = i5;
                invalidateIndex(i6);
            }
        }
        calcMax(treeRoot());
        if (this.mIndexOfSpan == null) {
            this.mIndexOfSpan = new IdentityHashMap<>();
        }
        for (int i8 = this.mLowWaterMark; i8 < this.mSpanCount; i8++) {
            Integer num = this.mIndexOfSpan.get(this.mSpans[i8]);
            if (num == null || num.intValue() != i8) {
                this.mIndexOfSpan.put(this.mSpans[i8], Integer.valueOf(i8));
            }
        }
        this.mLowWaterMark = Integer.MAX_VALUE;
    }

    private void invalidateIndex(int i) {
        this.mLowWaterMark = Math.min(i, this.mLowWaterMark);
    }
}
