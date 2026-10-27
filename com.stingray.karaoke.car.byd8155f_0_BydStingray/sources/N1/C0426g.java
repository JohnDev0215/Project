package N1;

import android.content.Context;
import android.content.SharedPreferences;
import h8.AbstractC1386c;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0426g extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q1.d f4524m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0426g(Q1.d dVar, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4524m = dVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0426g) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0426g(this.f4524m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Context context;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            Q1.d dVar = this.f4524m;
            SharedPreferences.Editor editorEdit = ((SharedPreferences) dVar.d.getValue()).edit();
            Set set = dVar.f6113e;
            if (set == null) {
                editorEdit.clear();
            } else {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    editorEdit.remove((String) it.next());
                }
            }
            if (!editorEdit.commit()) {
                throw new IOException("Unable to delete migrated keys from SharedPreferences.");
            }
            if (((SharedPreferences) dVar.d.getValue()).getAll().isEmpty() && (context = dVar.c) != null) {
                Q1.b.a(context, "userSettings");
            }
            if (set != null) {
                set.clear();
            }
            if (qVar == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
