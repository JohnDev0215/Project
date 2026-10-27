package com.android.inputmethod.keyboard.internal;

import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class PointerTrackerQueue {
    private static final boolean DEBUG = false;
    private static final int INITIAL_CAPACITY = 10;
    private static final String TAG = "PointerTrackerQueue";
    private final ArrayList<Element> mExpandableArrayOfActivePointers = new ArrayList<>(10);
    private int mArraySize = 0;

    public interface Element {
        void cancelTrackingForAction();

        boolean isInDraggingFinger();

        boolean isModifier();

        void onPhantomUpEvent(long eventTime);
    }

    public int size() {
        int i;
        synchronized (this.mExpandableArrayOfActivePointers) {
            i = this.mArraySize;
        }
        return i;
    }

    public void add(final Element pointer) {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            if (i < arrayList.size()) {
                arrayList.set(i, pointer);
            } else {
                arrayList.add(pointer);
            }
            this.mArraySize = i + 1;
        }
    }

    public void remove(final Element pointer) {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Element element = arrayList.get(i3);
                if (element != pointer) {
                    if (i2 != i3) {
                        arrayList.set(i2, element);
                    }
                    i2++;
                } else if (i2 != i3) {
                    Log.w(TAG, "Found duplicated element in remove: " + pointer);
                }
            }
            this.mArraySize = i2;
        }
    }

    public Element getOldestElement() {
        Element element;
        synchronized (this.mExpandableArrayOfActivePointers) {
            element = this.mArraySize == 0 ? null : this.mExpandableArrayOfActivePointers.get(0);
        }
        return element;
    }

    public void releaseAllPointersOlderThan(final Element pointer, final long eventTime) {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                Element element = arrayList.get(i3);
                if (element == pointer) {
                    break;
                }
                if (element.isModifier()) {
                    if (i4 != i3) {
                        arrayList.set(i4, element);
                    }
                    i4++;
                } else {
                    element.onPhantomUpEvent(eventTime);
                }
                i3++;
            }
            while (i3 < i) {
                if (arrayList.get(i3) == pointer && (i2 = i2 + 1) > 1) {
                    Log.w(TAG, "Found duplicated element in releaseAllPointersOlderThan: " + pointer);
                }
                if (i4 != i3) {
                    arrayList.set(i4, arrayList.get(i3));
                }
                i4++;
                i3++;
            }
            this.mArraySize = i4;
        }
    }

    public void releaseAllPointers(final long eventTime) {
        releaseAllPointersExcept(null, eventTime);
    }

    public void releaseAllPointersExcept(final Element pointer, final long eventTime) {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                Element element = arrayList.get(i4);
                if (element == pointer) {
                    i3++;
                    if (i3 > 1) {
                        Log.w(TAG, "Found duplicated element in releaseAllPointersExcept: " + pointer);
                    }
                    if (i2 != i4) {
                        arrayList.set(i2, element);
                    }
                    i2++;
                } else {
                    element.onPhantomUpEvent(eventTime);
                }
            }
            this.mArraySize = i2;
        }
    }

    public boolean hasModifierKeyOlderThan(final Element pointer) {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            for (int i2 = 0; i2 < i; i2++) {
                Element element = arrayList.get(i2);
                if (element == pointer) {
                    return false;
                }
                if (element.isModifier()) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isAnyInDraggingFinger() {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            for (int i2 = 0; i2 < i; i2++) {
                if (arrayList.get(i2).isInDraggingFinger()) {
                    return true;
                }
            }
            return false;
        }
    }

    public void cancelAllPointerTrackers() {
        synchronized (this.mExpandableArrayOfActivePointers) {
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.get(i2).cancelTrackingForAction();
            }
        }
    }

    public String toString() {
        String str;
        synchronized (this.mExpandableArrayOfActivePointers) {
            StringBuilder sb = new StringBuilder();
            ArrayList<Element> arrayList = this.mExpandableArrayOfActivePointers;
            int i = this.mArraySize;
            for (int i2 = 0; i2 < i; i2++) {
                Element element = arrayList.get(i2);
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(element.toString());
            }
            str = "[" + sb.toString() + "]";
        }
        return str;
    }
}
