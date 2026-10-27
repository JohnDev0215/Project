package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.util.Pair;
import com.google.android.exoplayer2.text.Cue;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
final class Cea708Cue extends Cue implements Comparable<Cea708Cue> {
    public final int priority;

    public Cea708Cue(List<Pair<Integer, CharSequence>> list, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2) {
        super(list, alignment, f, i, i2, f2, i3, f3, z, i4, i5, i6, i8, i9, z2, true);
        this.priority = i7;
    }

    @Override // java.lang.Comparable
    public int compareTo(Cea708Cue cea708Cue) {
        int i = cea708Cue.priority;
        int i2 = this.priority;
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("X:" + this.position);
        sb.append("; " + this.textAlignment);
        sb.append("; maxWindowRowCount:" + this.maxWindowRowCount);
        sb.append("; maxWindowColumnCount:" + this.maxWindowColumnCount);
        sb.append("; textRows: Line Count " + this.textRows.size() + ":");
        Iterator<Pair<Integer, CharSequence>> it = this.textRows.iterator();
        while (it.hasNext()) {
            sb.append(it.next().second + "\n");
        }
        return sb.toString();
    }
}
