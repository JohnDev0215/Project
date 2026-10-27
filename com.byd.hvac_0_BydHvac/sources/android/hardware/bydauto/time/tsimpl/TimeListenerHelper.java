package android.hardware.bydauto.time.tsimpl;

import a.b.c;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.time.AbsBYDAutoTimeListener;
import android.util.ArraySet;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
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

    public void notifyDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void notifyError(int i, String str) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onError(i, str);
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

    public void notifyTimeFormatChanged(int i) {
        ArraySet arraySet = new ArraySet();
        synchronized (this) {
            arraySet.addAll((ArraySet) this.mListenerArraySet);
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoTimeListener) it.next()).onTimeFormatChanged(i);
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
