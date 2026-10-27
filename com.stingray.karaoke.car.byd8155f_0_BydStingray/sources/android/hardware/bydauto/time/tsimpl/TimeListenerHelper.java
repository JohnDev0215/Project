package android.hardware.bydauto.time.tsimpl;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.time.AbsBYDAutoTimeListener;
import android.util.ArraySet;
import b.c;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class TimeListenerHelper {
    private ArraySet<AbsBYDAutoTimeListener> mListenerArraySet = new ArraySet<>();

    public synchronized int getListenerSize() {
        return this.mListenerArraySet.size();
    }

    public final void notifyDataChanged(c cVar) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onDataChanged(cVar);
        }
    }

    public void notifyDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void notifyError(int i7, String str) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onError(i7, str);
        }
    }

    public void notifyTimeChanged(int[] iArr) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onTimeChanged(iArr);
        }
    }

    public void notifyTimeFormatChanged(int i7) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onTimeFormatChanged(i7);
        }
    }

    public synchronized boolean registerListener(AbsBYDAutoTimeListener absBYDAutoTimeListener) {
        if (absBYDAutoTimeListener == null) {
            return false;
        }
        return this.mListenerArraySet.add(absBYDAutoTimeListener);
    }

    public synchronized boolean unregisterListener(AbsBYDAutoTimeListener absBYDAutoTimeListener) {
        if (absBYDAutoTimeListener == null) {
            return false;
        }
        return this.mListenerArraySet.remove(absBYDAutoTimeListener);
    }
}
