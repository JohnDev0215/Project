package b.d.a.a.v;

import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import b.d.a.a.v.f;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class q implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MaterialCalendarGridView f2116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r f2117b;

    public q(r rVar, MaterialCalendarGridView materialCalendarGridView) {
        this.f2117b = rVar;
        this.f2116a = materialCalendarGridView;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        p adapter = this.f2116a.getAdapter();
        if (i >= adapter.b() && i <= adapter.d()) {
            f.InterfaceC0044f interfaceC0044f = this.f2117b.f2121f;
            long jLongValue = this.f2116a.getAdapter().getItem(i).longValue();
            f.d dVar = (f.d) interfaceC0044f;
            if (f.this.Y.f2067c.g(jLongValue)) {
                f.this.X.d(jLongValue);
                Iterator it = f.this.V.iterator();
                while (it.hasNext()) {
                    ((s) it.next()).a(f.this.X.a());
                }
                f.this.d0.getAdapter().f1636a.b();
                RecyclerView recyclerView = f.this.c0;
                if (recyclerView != null) {
                    recyclerView.getAdapter().f1636a.b();
                }
            }
        }
    }
}
