package V0;

import N0.L;
import N0.t;
import N0.y;
import N1.C0420a;
import O0.m;
import S0.InterfaceC0702n;
import T.Q0;
import android.text.Layout;
import android.text.TextPaint;
import java.text.BreakIterator;
import java.util.List;
import java.util.PriorityQueue;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class c implements t {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final L f9290m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f9291n;
    public final List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC0702n f9292p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final a1.b f9293q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final d f9294r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final CharSequence f9295s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final m f9296t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public P f9297u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f9298v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f9299w;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x078d  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x080a  */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v20, types: [java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c(java.lang.String r38, N0.L r39, java.util.List r40, java.util.List r41, S0.InterfaceC0702n r42, a1.b r43) {
        /*
            Method dump skipped, instruction units count: 2127
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V0.c.<init>(java.lang.String, N0.L, java.util.List, java.util.List, S0.n, a1.b):void");
    }

    @Override // N0.t
    public final float b() {
        m mVar = this.f9296t;
        if (!Float.isNaN(mVar.f5023e)) {
            return mVar.f5023e;
        }
        TextPaint textPaint = mVar.f5022b;
        BreakIterator lineInstance = BreakIterator.getLineInstance(textPaint.getTextLocale());
        CharSequence charSequence = mVar.f5021a;
        lineInstance.setText(new O0.j(charSequence.length(), charSequence));
        PriorityQueue<m7.i> priorityQueue = new PriorityQueue(10, new C1.b(3));
        int i7 = 0;
        for (int next = lineInstance.next(); next != -1; next = lineInstance.next()) {
            if (priorityQueue.size() < 10) {
                priorityQueue.add(new m7.i(Integer.valueOf(i7), Integer.valueOf(next)));
            } else {
                m7.i iVar = (m7.i) priorityQueue.peek();
                if (iVar != null && ((Number) iVar.f19259m).intValue() - ((Number) iVar.l).intValue() < next - i7) {
                    priorityQueue.poll();
                    priorityQueue.add(new m7.i(Integer.valueOf(i7), Integer.valueOf(next)));
                }
            }
            i7 = next;
        }
        float fMax = 0.0f;
        for (m7.i iVar2 : priorityQueue) {
            fMax = Math.max(fMax, Layout.getDesiredWidth(charSequence, ((Number) iVar2.l).intValue(), ((Number) iVar2.f19259m).intValue(), textPaint));
        }
        mVar.f5023e = fMax;
        return fMax;
    }

    @Override // N0.t
    public final boolean c() {
        P p10 = this.f9297u;
        if (!(p10 != null ? p10.v() : false)) {
            if (this.f9298v) {
                return false;
            }
            y yVar = this.f9290m.c;
            C0420a c0420a = h.f9309a;
            C0420a c0420a2 = h.f9309a;
            Q0 q0K = (Q0) c0420a2.l;
            if (q0K == null) {
                if (T1.i.c()) {
                    q0K = c0420a2.k();
                    c0420a2.l = q0K;
                } else {
                    q0K = i.f9310a;
                }
            }
            if (!((Boolean) q0K.getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @Override // N0.t
    public final float e() {
        return this.f9296t.b();
    }
}
