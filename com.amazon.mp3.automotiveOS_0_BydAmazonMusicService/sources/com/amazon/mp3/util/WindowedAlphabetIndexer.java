package com.amazon.mp3.util;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.util.SparseIntArray;
import android.widget.SectionIndexer;
import java.text.Collator;

/* JADX INFO: loaded from: classes4.dex */
public class WindowedAlphabetIndexer extends DataSetObserver implements SectionIndexer {
    private SparseIntArray mAlphaMap;
    protected CharSequence mAlphabet;
    private String[] mAlphabetArray;
    private int mAlphabetLength;
    private Collator mCollator;
    protected int mColumnIndex;
    protected Cursor mDataCursor;

    public WindowedAlphabetIndexer(Cursor cursor, int sortedColumnIndex, CharSequence alphabet) {
        this.mDataCursor = cursor;
        this.mColumnIndex = sortedColumnIndex;
        this.mAlphabet = alphabet;
        int length = alphabet.length();
        this.mAlphabetLength = length;
        this.mAlphabetArray = new String[length];
        for (int i = 0; i < this.mAlphabetLength; i++) {
            this.mAlphabetArray[i] = Character.toString(this.mAlphabet.charAt(i));
        }
        this.mAlphaMap = new SparseIntArray(this.mAlphabetLength);
        if (cursor != null) {
            cursor.registerDataSetObserver(this);
        }
        Collator collator = Collator.getInstance();
        this.mCollator = collator;
        collator.setStrength(0);
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        return this.mAlphabetArray;
    }

    protected int compare(final String word, final String letter) {
        String strSubstring = StringUtil.isNullOrWhiteSpaces(word) ? " " : word.substring(0, 1);
        if (StringUtil.isNullOrWhiteSpaces(letter)) {
            letter = " ";
        }
        return this.mCollator.compare(strSubstring, letter);
    }

    class IndexWalker {
        int mEnd;
        int mStart;

        IndexWalker(int ss, int ee) {
            this.mStart = ss;
            this.mEnd = ee;
        }

        void checkSectionPositions(int sectionIndex) {
            int i;
            SparseIntArray sparseIntArray = WindowedAlphabetIndexer.this.mAlphaMap;
            int i2 = sectionIndex - 1;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                int i3 = sparseIntArray.get(WindowedAlphabetIndexer.this.mAlphabet.charAt(i2), Integer.MIN_VALUE);
                if (i3 != Integer.MIN_VALUE) {
                    this.mStart = Math.abs(i3);
                    break;
                }
                i2--;
            }
            do {
                sectionIndex++;
                if (sectionIndex >= WindowedAlphabetIndexer.this.mAlphabetLength) {
                    return;
                } else {
                    i = sparseIntArray.get(WindowedAlphabetIndexer.this.mAlphabet.charAt(sectionIndex), Integer.MIN_VALUE);
                }
            } while (i == Integer.MIN_VALUE);
            this.mEnd = Math.abs(i);
        }

        void narrowSearchRange(int leap, Cursor cursor, String targetLetter) {
            if (leap <= 0) {
                return;
            }
            int i = this.mStart;
            while (true) {
                i += leap;
                while (i < this.mEnd) {
                    cursor.moveToPosition(i);
                    String string = cursor.getString(WindowedAlphabetIndexer.this.mColumnIndex);
                    if (string == null) {
                        if (i == 0) {
                            return;
                        } else {
                            i--;
                        }
                    } else if (WindowedAlphabetIndexer.this.compare(string, targetLetter) < 0) {
                        this.mStart = i;
                    } else {
                        this.mEnd = i;
                        return;
                    }
                }
                return;
            }
        }

        int findPosition(Cursor cursor, String targetLetter) {
            int count = cursor.getCount();
            int i = (this.mEnd + this.mStart) / 2;
            while (i < this.mEnd && i >= 0) {
                cursor.moveToPosition(i);
                String string = cursor.getString(WindowedAlphabetIndexer.this.mColumnIndex);
                if (string != null) {
                    int iCompare = WindowedAlphabetIndexer.this.compare(string, targetLetter);
                    if (iCompare != 0) {
                        if (iCompare < 0) {
                            int i2 = i + 1;
                            this.mStart = i2;
                            if (i2 >= count) {
                                return count;
                            }
                        } else {
                            this.mEnd = i;
                        }
                    } else {
                        if (this.mStart == i) {
                            break;
                        }
                        this.mEnd = i;
                    }
                    i = (this.mStart + this.mEnd) / 2;
                } else {
                    if (i == 0) {
                        break;
                    }
                    i--;
                }
            }
            return i;
        }
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int sectionIndex) {
        SparseIntArray sparseIntArray = this.mAlphaMap;
        Cursor cursor = this.mDataCursor;
        if (cursor == null || cursor.getCount() <= 0 || this.mAlphabet == null || sectionIndex <= 0) {
            return 0;
        }
        int i = this.mAlphabetLength;
        if (sectionIndex >= i) {
            sectionIndex = i - 1;
        }
        int position = cursor.getPosition();
        int count = cursor.getCount();
        int iMax = Math.max(count / this.mAlphabetLength, 1);
        char cCharAt = this.mAlphabet.charAt(sectionIndex);
        String string = Character.toString(cCharAt);
        int i2 = sparseIntArray.get(cCharAt, Integer.MIN_VALUE);
        if (Integer.MIN_VALUE != i2) {
            return i2;
        }
        IndexWalker indexWalker = new IndexWalker(0, count);
        indexWalker.checkSectionPositions(sectionIndex);
        indexWalker.narrowSearchRange(iMax, cursor, string);
        int iFindPosition = indexWalker.findPosition(cursor, string);
        sparseIntArray.put(cCharAt, iFindPosition);
        cursor.moveToPosition(position);
        return iFindPosition;
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int position) {
        int position2 = this.mDataCursor.getPosition();
        this.mDataCursor.moveToPosition(position);
        String string = this.mDataCursor.getString(this.mColumnIndex);
        this.mDataCursor.moveToPosition(position2);
        for (int i = 0; i < this.mAlphabetLength; i++) {
            if (compare(string, Character.toString(this.mAlphabet.charAt(i))) == 0) {
                return i;
            }
        }
        return 0;
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        super.onChanged();
        this.mAlphaMap.clear();
    }

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        super.onInvalidated();
        this.mAlphaMap.clear();
    }
}
