package com.amazon.music.media.playback.sequencer;

import android.util.SparseIntArray;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
public class ShuffleMap {
    private int deckSize;
    private Throwable errorCause;
    private boolean overrideCreateShuffleIndex;
    private final SparseIntArray shuffleOrder = new SparseIntArray();
    private final SparseIntArray inverseShuffleOrder = new SparseIntArray();
    private final List<Integer> availableOriginalIndices = new ArrayList();
    private final List<Integer> availableShuffledIndices = new ArrayList();
    private final Random random = new Random();

    public String toString() {
        return "ShuffleMap{\n  shuffleOrder: " + this.shuffleOrder + "\n  inverseShuffleOrder: " + this.inverseShuffleOrder + "\n  availableOriginalIndices: " + StringUtils.toString(this.availableOriginalIndices) + "\n  availableShuffledIndices: " + StringUtils.toString(this.availableShuffledIndices) + "\n}";
    }

    public void clear() {
        this.shuffleOrder.clear();
        this.inverseShuffleOrder.clear();
        this.availableOriginalIndices.clear();
        this.availableShuffledIndices.clear();
        this.deckSize = 0;
    }

    public void setSize(int i) {
        if (i < this.deckSize) {
            throw new IllegalStateException("Cannot decrease the size except by clearing or removing items!");
        }
        if (i >= 2000 && this.errorCause == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("setSize(" + i + ")");
            this.errorCause = illegalArgumentException;
            illegalArgumentException.fillInStackTrace();
        }
        this.deckSize = i;
    }

    public void setErrorCause(Throwable th) {
        this.errorCause = th;
    }

    public Throwable getErrorCause() {
        return this.errorCause;
    }

    public int getSize() {
        return this.deckSize;
    }

    public void setShuffleOrder(List<Integer> list) {
        try {
            setSize(Math.max(this.deckSize, list.size()));
            this.shuffleOrder.clear();
            this.inverseShuffleOrder.clear();
            this.availableOriginalIndices.clear();
            this.availableShuffledIndices.clear();
            int i = 0;
            for (int i2 = 0; i2 < this.deckSize; i2++) {
                this.availableOriginalIndices.add(Integer.valueOf(i2));
            }
            for (Integer num : list) {
                if (num.intValue() < 0) {
                    this.availableShuffledIndices.add(Integer.valueOf(i));
                } else {
                    if (num.intValue() >= this.deckSize) {
                        throw new IllegalArgumentException("Illegal index: " + num + " for size " + this.deckSize + ".");
                    }
                    if (!this.availableOriginalIndices.remove(num)) {
                        throw new IllegalArgumentException("Shuffle order contains duplicate entries for " + num);
                    }
                    this.shuffleOrder.put(i, num.intValue());
                    this.inverseShuffleOrder.put(num.intValue(), i);
                }
                i++;
            }
        } catch (RuntimeException e) {
            StrictMode.crashIfStrict(e);
            this.shuffleOrder.clear();
            this.inverseShuffleOrder.clear();
            this.availableOriginalIndices.clear();
            this.availableShuffledIndices.clear();
            throw e;
        }
    }

    public List<Integer> getShuffleOrder() {
        ArrayList arrayList = new ArrayList(this.deckSize);
        for (int i = 0; i < this.deckSize; i++) {
            arrayList.add(Integer.valueOf(this.shuffleOrder.get(i, -1)));
        }
        return arrayList;
    }

    public int getUnshuffledIndex(int i) {
        return getMapping(i, this.shuffleOrder, this.inverseShuffleOrder, this.availableShuffledIndices, this.availableOriginalIndices);
    }

    public int getShuffledIndex(int i) {
        return getMapping(i, this.inverseShuffleOrder, this.shuffleOrder, this.availableOriginalIndices, this.availableShuffledIndices);
    }

    public void setUnshuffledIndex(int i, int i2) {
        int i3 = this.deckSize;
        if (i >= i3 || i2 >= i3 || ((i < 0 && i2 < 0) || ((i < 0 && i != -1) || (i2 < 0 && i2 != -1)))) {
            throw new IndexOutOfBoundsException("illegal values for setUnshuffledIndex: shuffledIndex=" + i + ", unshuffledIndex=" + i2 + ", deckSize=" + this.deckSize);
        }
        addAvailableIndices();
        if (i >= 0) {
            int i4 = this.shuffleOrder.get(i, -1);
            if (i4 == i2) {
                return;
            }
            if (i4 != -1) {
                removeMapping(i4, this.inverseShuffleOrder, this.availableOriginalIndices);
            }
            if (i2 == -1) {
                removeMapping(i, this.shuffleOrder, this.availableShuffledIndices);
                return;
            } else if (i4 == -1) {
                this.availableShuffledIndices.remove(Collections.binarySearch(this.availableShuffledIndices, Integer.valueOf(i)));
            }
        }
        if (i2 >= 0) {
            int i5 = this.inverseShuffleOrder.get(i2, -1);
            if (i5 == i) {
                return;
            }
            if (i5 != -1) {
                removeMapping(i5, this.shuffleOrder, this.availableShuffledIndices);
            }
            if (i == -1) {
                removeMapping(i2, this.inverseShuffleOrder, this.availableOriginalIndices);
                return;
            } else if (i5 == -1) {
                this.availableOriginalIndices.remove(Collections.binarySearch(this.availableOriginalIndices, Integer.valueOf(i2)));
            }
        }
        this.shuffleOrder.put(i, i2);
        this.inverseShuffleOrder.put(i2, i);
    }

    public void onItemRemoved(int i) {
        int iIntValue;
        int iIntValue2;
        if (i < 0 || i >= this.deckSize) {
            throw new IndexOutOfBoundsException("invalid unshuffledIndex: " + i + " for deckSize: " + this.deckSize);
        }
        addAvailableIndices();
        int iIntValue3 = this.inverseShuffleOrder.get(i, -1);
        if (iIntValue3 == -1) {
            iIntValue3 = this.availableShuffledIndices.remove(r0.size() - 1).intValue();
            this.availableOriginalIndices.remove(Collections.binarySearch(this.availableOriginalIndices, Integer.valueOf(i)));
        }
        this.shuffleOrder.delete(iIntValue3);
        this.inverseShuffleOrder.delete(i);
        int size = this.availableOriginalIndices.size();
        while (true) {
            size--;
            if (size < 0 || (iIntValue2 = this.availableOriginalIndices.get(size).intValue()) <= i) {
                break;
            } else {
                this.availableOriginalIndices.set(size, Integer.valueOf(iIntValue2 - 1));
            }
        }
        int size2 = this.availableShuffledIndices.size();
        while (true) {
            size2--;
            if (size2 < 0 || (iIntValue = this.availableShuffledIndices.get(size2).intValue()) <= iIntValue3) {
                break;
            } else {
                this.availableShuffledIndices.set(size2, Integer.valueOf(iIntValue - 1));
            }
        }
        int size3 = this.shuffleOrder.size();
        for (int i2 = 0; i2 < size3; i2++) {
            int iKeyAt = this.shuffleOrder.keyAt(i2);
            int iValueAt = this.shuffleOrder.valueAt(i2);
            if (iKeyAt > iIntValue3 && iValueAt > i) {
                this.shuffleOrder.removeAt(i2);
                this.shuffleOrder.put(iKeyAt - 1, iValueAt - 1);
            } else if (iKeyAt > iIntValue3) {
                this.shuffleOrder.removeAt(i2);
                this.shuffleOrder.put(iKeyAt - 1, iValueAt);
            } else if (iValueAt > i) {
                this.shuffleOrder.put(iKeyAt, iValueAt - 1);
            }
        }
        for (int i3 = 0; i3 < size3; i3++) {
            int iKeyAt2 = this.inverseShuffleOrder.keyAt(i3);
            int iValueAt2 = this.inverseShuffleOrder.valueAt(i3);
            if (iKeyAt2 > i && iValueAt2 > iIntValue3) {
                this.inverseShuffleOrder.removeAt(i3);
                this.inverseShuffleOrder.put(iKeyAt2 - 1, iValueAt2 - 1);
            } else if (iKeyAt2 > i) {
                this.inverseShuffleOrder.removeAt(i3);
                this.inverseShuffleOrder.put(iKeyAt2 - 1, iValueAt2);
            } else if (iValueAt2 > iIntValue3) {
                this.inverseShuffleOrder.put(iKeyAt2, iValueAt2 - 1);
            }
        }
        this.deckSize--;
    }

    public void onUnshuffledItemMoved(int i, int i2) {
        onItemMoved(i, i2, false);
    }

    public void onShuffledItemMoved(int i, int i2) {
        onItemMoved(i, i2, true);
    }

    public void onItemsInserted(int i, int i2, int i3) {
        int i4;
        if (i >= 0 && i <= (i4 = this.deckSize) && i2 >= 0 && i2 <= i4 && i3 >= 0) {
            if (i3 == 0) {
                return;
            }
            this.deckSize = i4 + i3;
            insert(this.shuffleOrder, this.availableOriginalIndices, i, i2, i3);
            insert(this.inverseShuffleOrder, this.availableShuffledIndices, i2, i, i3);
            addAvailableIndices();
            return;
        }
        throw new IndexOutOfBoundsException("invalid values - shuffledInsertionIndex: " + i + ", unshuffledInsertionIndex: " + i2 + ", insertionCount: " + i3 + " for deckSize: " + this.deckSize);
    }

    private void insert(SparseIntArray sparseIntArray, List<Integer> list, int i, int i2, int i3) {
        int iIntValue;
        int size = sparseIntArray.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            int iKeyAt = sparseIntArray.keyAt(size);
            int iValueAt = sparseIntArray.valueAt(size);
            if (iValueAt >= i2) {
                iValueAt += i3;
            }
            if (iKeyAt >= i) {
                sparseIntArray.removeAt(size);
                sparseIntArray.put(iKeyAt + i3, iValueAt);
            } else if (iValueAt >= i2) {
                sparseIntArray.put(iKeyAt, iValueAt);
            }
        }
        int size2 = list.size();
        int size3 = list.size();
        while (true) {
            size3--;
            if (size3 < 0 || (iIntValue = list.get(size3).intValue()) < i2) {
                break;
            }
            list.set(size3, Integer.valueOf(iIntValue + i3));
            size2 = size3;
        }
        if (sparseIntArray.size() + list.size() > i2) {
            for (int i4 = 0; i4 < i3; i4++) {
                list.add(size2 + i4, Integer.valueOf(i2 + i4));
            }
        }
    }

    private void onItemMoved(int i, int i2, boolean z) {
        int i3;
        SparseIntArray sparseIntArray;
        SparseIntArray sparseIntArray2;
        List<Integer> list;
        int iKeyAt;
        int i4;
        if (i < 0 || i >= (i3 = this.deckSize) || i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException("invalid values - oldIndex: " + i + ", newIndex: " + i2 + " for deckSize: " + this.deckSize);
        }
        if (i == i2) {
            return;
        }
        addAvailableIndices();
        if (z) {
            sparseIntArray = this.shuffleOrder;
            sparseIntArray2 = this.inverseShuffleOrder;
            list = this.availableShuffledIndices;
        } else {
            sparseIntArray = this.inverseShuffleOrder;
            sparseIntArray2 = this.shuffleOrder;
            list = this.availableOriginalIndices;
        }
        boolean z2 = sparseIntArray.get(i, -1) == -1;
        this.overrideCreateShuffleIndex = z2;
        int unshuffledIndex = z ? getUnshuffledIndex(i) : getShuffledIndex(i);
        this.overrideCreateShuffleIndex = false;
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            int iIntValue = list.get(size).intValue();
            if (iIntValue <= i || iIntValue > i2) {
                if (iIntValue >= i || iIntValue < i2) {
                    if (iIntValue < i) {
                        break;
                    }
                } else {
                    list.set(size, Integer.valueOf(iIntValue + 1));
                }
            } else {
                list.set(size, Integer.valueOf(iIntValue - 1));
            }
        }
        int size2 = sparseIntArray2.size();
        for (int i5 = 0; i5 < size2; i5++) {
            int iKeyAt2 = sparseIntArray2.keyAt(i5);
            int iValueAt = sparseIntArray2.valueAt(i5);
            if (iValueAt == i) {
                i4 = i2;
            } else if (iValueAt > i && iValueAt <= i2) {
                i4 = iValueAt - 1;
            } else if (iValueAt < i && iValueAt >= i2) {
                i4 = iValueAt + 1;
            }
            sparseIntArray2.put(iKeyAt2, i4);
        }
        sparseIntArray.delete(i);
        int size3 = sparseIntArray.size();
        if (i2 > i) {
            for (int i6 = 0; i6 < size3; i6++) {
                int iKeyAt3 = sparseIntArray.keyAt(i6);
                if (iKeyAt3 > i2) {
                    break;
                }
                if (iKeyAt3 > i) {
                    int iValueAt2 = sparseIntArray.valueAt(i6);
                    sparseIntArray.removeAt(i6);
                    sparseIntArray.put(iKeyAt3 - 1, iValueAt2);
                }
            }
        } else {
            while (true) {
                size3--;
                if (size3 < 0 || (iKeyAt = sparseIntArray.keyAt(size3)) < i2) {
                    break;
                }
                if (iKeyAt < i) {
                    int iValueAt3 = sparseIntArray.valueAt(size3);
                    sparseIntArray.removeAt(size3);
                    sparseIntArray.put(iKeyAt + 1, iValueAt3);
                }
            }
        }
        sparseIntArray.put(i2, unshuffledIndex);
        if (z2) {
            if (z) {
                setUnshuffledIndex(-1, unshuffledIndex);
            } else {
                setUnshuffledIndex(unshuffledIndex, -1);
            }
        }
    }

    private int createMapping(int i, SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2, List<Integer> list, List<Integer> list2) {
        int iBinarySearch = Collections.binarySearch(list, Integer.valueOf(i));
        if (iBinarySearch < 0) {
            return -1;
        }
        int iCreateShuffleIndex = this.overrideCreateShuffleIndex ? 0 : createShuffleIndex(list2.size());
        if (iCreateShuffleIndex < 0) {
            return -1;
        }
        list.remove(iBinarySearch);
        int iIntValue = list2.remove(iCreateShuffleIndex).intValue();
        sparseIntArray.put(i, iIntValue);
        sparseIntArray2.put(iIntValue, i);
        return iIntValue;
    }

    private void removeMapping(int i, SparseIntArray sparseIntArray, List<Integer> list) {
        sparseIntArray.delete(i);
        list.add((-1) - Collections.binarySearch(list, Integer.valueOf(i)), Integer.valueOf(i));
    }

    private int getMapping(int i, SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2, List<Integer> list, List<Integer> list2) {
        if (i < 0 || i >= this.deckSize) {
            return -1;
        }
        int i2 = sparseIntArray.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        addAvailableIndices();
        return createMapping(i, sparseIntArray, sparseIntArray2, list, list2);
    }

    @VisibleForTesting(otherwise = 2)
    void addAvailableIndices() {
        try {
            for (int size = this.shuffleOrder.size() + this.availableShuffledIndices.size(); size < this.deckSize; size++) {
                this.availableShuffledIndices.add(Integer.valueOf(size));
            }
            for (int size2 = this.inverseShuffleOrder.size() + this.availableOriginalIndices.size(); size2 < this.deckSize; size2++) {
                this.availableOriginalIndices.add(Integer.valueOf(size2));
            }
        } catch (OutOfMemoryError unused) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("addAvailableIndices - " + this.deckSize + ", " + this.shuffleOrder.size() + ", " + this.inverseShuffleOrder.size() + ", " + this.availableShuffledIndices.size() + ", " + this.availableOriginalIndices.size());
            outOfMemoryError.initCause(this.errorCause);
            throw outOfMemoryError;
        }
    }

    protected int createShuffleIndex(int i) {
        return this.random.nextInt(i);
    }
}
