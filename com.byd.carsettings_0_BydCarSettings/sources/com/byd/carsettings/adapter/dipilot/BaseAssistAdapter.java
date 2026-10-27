package com.byd.carsettings.adapter.dipilot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.f;
import b.b.g.n.q.b;
import b.b.k.d.a.a;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.databinding.ItemDipilotAssistBinding;
import com.byd.dipilot.model.ActiveSafeModel;
import com.byd.dipilot.model.DriverAssistModel;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseAssistAdapter<T extends DriverAssistModel> extends RecyclerView.Adapter<AssistViewHolder> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2759a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2760b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a<T> f2761c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<T> f2762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f2763e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LifecycleOwner f2764f;

    public static class AssistViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ItemDipilotAssistBinding f2765a;

        public AssistViewHolder(ItemDipilotAssistBinding itemDipilotAssistBinding) {
            super(itemDipilotAssistBinding.getRoot());
            this.f2765a = itemDipilotAssistBinding;
        }

        public ItemDipilotAssistBinding a() {
            return this.f2765a;
        }
    }

    public BaseAssistAdapter() {
    }

    public static int b(int i) {
        if (b.b.a.i.a.f533c == 1) {
            return d(i);
        }
        if (f2759a < 0) {
            f2759a = b.c().a();
        }
        return f2759a == 143 ? e(i) : f(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0045 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int d(int r1) {
        /*
            r0 = 80
            if (r1 == r0) goto L5f
            r0 = 81
            if (r1 == r0) goto L5b
            switch(r1) {
                case 0: goto L57;
                case 1: goto L53;
                case 2: goto L4f;
                case 3: goto L4b;
                case 4: goto L37;
                case 5: goto L33;
                case 6: goto L2f;
                case 7: goto L2b;
                default: goto Lb;
            }
        Lb:
            switch(r1) {
                case 64: goto L27;
                case 65: goto L23;
                case 66: goto L1f;
                case 67: goto L1b;
                case 68: goto L17;
                case 69: goto L13;
                case 70: goto Lf;
                default: goto Le;
            }
        Le:
            goto L45
        Lf:
            r1 = 2131689479(0x7f0f0007, float:1.9007975E38)
            goto L62
        L13:
            r1 = 2131689492(0x7f0f0014, float:1.9008E38)
            goto L62
        L17:
            r1 = 2131689490(0x7f0f0012, float:1.9007997E38)
            goto L62
        L1b:
            r1 = 2131689491(0x7f0f0013, float:1.9007999E38)
            goto L62
        L1f:
            r1 = 2131689482(0x7f0f000a, float:1.900798E38)
            goto L62
        L23:
            r1 = 2131689488(0x7f0f0010, float:1.9007993E38)
            goto L62
        L27:
            r1 = 2131689489(0x7f0f0011, float:1.9007995E38)
            goto L62
        L2b:
            r1 = 2131689486(0x7f0f000e, float:1.9007989E38)
            goto L62
        L2f:
            r1 = 2131689487(0x7f0f000f, float:1.900799E38)
            goto L62
        L33:
            r1 = 2131689485(0x7f0f000d, float:1.9007987E38)
            goto L62
        L37:
            com.byd.base.hilt.Router r0 = com.byd.base.hilt.Router.b()
            com.byd.base.hilt.inter.IStudyConfig r0 = r0.f()
            boolean r1 = r0.d(r1)
            if (r1 == 0) goto L47
        L45:
            r1 = 0
            goto L62
        L47:
            r1 = 2131689493(0x7f0f0015, float:1.9008003E38)
            goto L62
        L4b:
            r1 = 2131689495(0x7f0f0017, float:1.9008007E38)
            goto L62
        L4f:
            r1 = 2131689483(0x7f0f000b, float:1.9007983E38)
            goto L62
        L53:
            r1 = 2131689481(0x7f0f0009, float:1.9007979E38)
            goto L62
        L57:
            r1 = 2131689484(0x7f0f000c, float:1.9007985E38)
            goto L62
        L5b:
            r1 = 2131689494(0x7f0f0016, float:1.9008005E38)
            goto L62
        L5f:
            r1 = 2131689480(0x7f0f0008, float:1.9007977E38)
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.adapter.dipilot.BaseAssistAdapter.d(int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0045 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int e(int r1) {
        /*
            r0 = 80
            if (r1 == r0) goto L5f
            r0 = 81
            if (r1 == r0) goto L5b
            switch(r1) {
                case 0: goto L57;
                case 1: goto L53;
                case 2: goto L4f;
                case 3: goto L4b;
                case 4: goto L37;
                case 5: goto L33;
                case 6: goto L2f;
                case 7: goto L2b;
                default: goto Lb;
            }
        Lb:
            switch(r1) {
                case 64: goto L27;
                case 65: goto L23;
                case 66: goto L1f;
                case 67: goto L1b;
                case 68: goto L17;
                case 69: goto L13;
                case 70: goto Lf;
                default: goto Le;
            }
        Le:
            goto L45
        Lf:
            r1 = 2131689496(0x7f0f0018, float:1.900801E38)
            goto L62
        L13:
            r1 = 2131689509(0x7f0f0025, float:1.9008035E38)
            goto L62
        L17:
            r1 = 2131689507(0x7f0f0023, float:1.9008031E38)
            goto L62
        L1b:
            r1 = 2131689508(0x7f0f0024, float:1.9008033E38)
            goto L62
        L1f:
            r1 = 2131689499(0x7f0f001b, float:1.9008015E38)
            goto L62
        L23:
            r1 = 2131689505(0x7f0f0021, float:1.9008027E38)
            goto L62
        L27:
            r1 = 2131689506(0x7f0f0022, float:1.900803E38)
            goto L62
        L2b:
            r1 = 2131689503(0x7f0f001f, float:1.9008023E38)
            goto L62
        L2f:
            r1 = 2131689504(0x7f0f0020, float:1.9008025E38)
            goto L62
        L33:
            r1 = 2131689502(0x7f0f001e, float:1.9008021E38)
            goto L62
        L37:
            com.byd.base.hilt.Router r0 = com.byd.base.hilt.Router.b()
            com.byd.base.hilt.inter.IStudyConfig r0 = r0.f()
            boolean r1 = r0.d(r1)
            if (r1 == 0) goto L47
        L45:
            r1 = 0
            goto L62
        L47:
            r1 = 2131689510(0x7f0f0026, float:1.9008037E38)
            goto L62
        L4b:
            r1 = 2131689512(0x7f0f0028, float:1.9008041E38)
            goto L62
        L4f:
            r1 = 2131689500(0x7f0f001c, float:1.9008017E38)
            goto L62
        L53:
            r1 = 2131689498(0x7f0f001a, float:1.9008013E38)
            goto L62
        L57:
            r1 = 2131689501(0x7f0f001d, float:1.900802E38)
            goto L62
        L5b:
            r1 = 2131689511(0x7f0f0027, float:1.900804E38)
            goto L62
        L5f:
            r1 = 2131689497(0x7f0f0019, float:1.9008011E38)
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.adapter.dipilot.BaseAssistAdapter.e(int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0045 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int f(int r1) {
        /*
            r0 = 80
            if (r1 == r0) goto L5f
            r0 = 81
            if (r1 == r0) goto L5b
            switch(r1) {
                case 0: goto L57;
                case 1: goto L53;
                case 2: goto L4f;
                case 3: goto L4b;
                case 4: goto L37;
                case 5: goto L33;
                case 6: goto L2f;
                case 7: goto L2b;
                default: goto Lb;
            }
        Lb:
            switch(r1) {
                case 64: goto L27;
                case 65: goto L23;
                case 66: goto L1f;
                case 67: goto L1b;
                case 68: goto L17;
                case 69: goto L13;
                case 70: goto Lf;
                default: goto Le;
            }
        Le:
            goto L45
        Lf:
            r1 = 2131689513(0x7f0f0029, float:1.9008043E38)
            goto L62
        L13:
            r1 = 2131689526(0x7f0f0036, float:1.900807E38)
            goto L62
        L17:
            r1 = 2131689524(0x7f0f0034, float:1.9008066E38)
            goto L62
        L1b:
            r1 = 2131689525(0x7f0f0035, float:1.9008068E38)
            goto L62
        L1f:
            r1 = 2131689516(0x7f0f002c, float:1.900805E38)
            goto L62
        L23:
            r1 = 2131689522(0x7f0f0032, float:1.9008062E38)
            goto L62
        L27:
            r1 = 2131689523(0x7f0f0033, float:1.9008064E38)
            goto L62
        L2b:
            r1 = 2131689520(0x7f0f0030, float:1.9008058E38)
            goto L62
        L2f:
            r1 = 2131689521(0x7f0f0031, float:1.900806E38)
            goto L62
        L33:
            r1 = 2131689519(0x7f0f002f, float:1.9008056E38)
            goto L62
        L37:
            com.byd.base.hilt.Router r0 = com.byd.base.hilt.Router.b()
            com.byd.base.hilt.inter.IStudyConfig r0 = r0.f()
            boolean r1 = r0.d(r1)
            if (r1 == 0) goto L47
        L45:
            r1 = 0
            goto L62
        L47:
            r1 = 2131689527(0x7f0f0037, float:1.9008072E38)
            goto L62
        L4b:
            r1 = 2131689529(0x7f0f0039, float:1.9008076E38)
            goto L62
        L4f:
            r1 = 2131689517(0x7f0f002d, float:1.9008052E38)
            goto L62
        L53:
            r1 = 2131689515(0x7f0f002b, float:1.9008048E38)
            goto L62
        L57:
            r1 = 2131689518(0x7f0f002e, float:1.9008054E38)
            goto L62
        L5b:
            r1 = 2131689528(0x7f0f0038, float:1.9008074E38)
            goto L62
        L5f:
            r1 = 2131689514(0x7f0f002a, float:1.9008046E38)
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.adapter.dipilot.BaseAssistAdapter.f(int):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(DriverAssistModel driverAssistModel, AssistViewHolder assistViewHolder, View view) {
        if (this.f2761c == null || f.b(view, 500L)) {
            return;
        }
        this.f2761c.p(driverAssistModel, assistViewHolder.getLayoutPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l(int i, ItemDipilotAssistBinding itemDipilotAssistBinding, DriverAssistModel driverAssistModel, AssistViewHolder assistViewHolder, CompoundButton compoundButton, boolean z) {
        if (this.f2761c != null) {
            if (z && i != 5) {
                itemDipilotAssistBinding.f2832b.playAnimation();
            }
            this.f2761c.b(itemDipilotAssistBinding.f2832b, driverAssistModel, z, assistViewHolder.getLayoutPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(DriverAssistModel driverAssistModel, AssistViewHolder assistViewHolder, View view) {
        a<T> aVar = this.f2761c;
        if (aVar != null) {
            aVar.p(driverAssistModel, assistViewHolder.getLayoutPosition());
        }
    }

    public void a() {
        this.f2763e = null;
        this.f2762d = null;
        this.f2764f = null;
    }

    public int g() {
        return R.layout.item_dipilot_assist;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f2762d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull final AssistViewHolder assistViewHolder, int i) {
        final ItemDipilotAssistBinding itemDipilotAssistBindingA = assistViewHolder.a();
        final T t = this.f2762d.get(i);
        itemDipilotAssistBindingA.b(t);
        itemDipilotAssistBindingA.setLifecycleOwner(this.f2764f);
        final int iConvertTagFromTitle = t.convertTagFromTitle();
        int iB = b(iConvertTagFromTitle);
        if (!itemDipilotAssistBindingA.f2832b.isAnimating() && iB > 800000) {
            itemDipilotAssistBindingA.f2832b.setAnimation(iB);
            itemDipilotAssistBindingA.f2832b.setProgress(1.0f);
        }
        itemDipilotAssistBindingA.f2833c.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.c.b.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f732a.j(t, assistViewHolder, view);
            }
        });
        BydSwitch bydSwitch = itemDipilotAssistBindingA.f2834d;
        bydSwitch.setVisibility(iConvertTagFromTitle == 80 ? 8 : 0);
        bydSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: b.b.g.c.b.a
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f727a.l(iConvertTagFromTitle, itemDipilotAssistBindingA, t, assistViewHolder, compoundButton, z);
            }
        });
        if (iConvertTagFromTitle == 80) {
            itemDipilotAssistBindingA.f2831a.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.c.b.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f735a.o(t, assistViewHolder, view);
                }
            });
        }
        if (t instanceof ActiveSafeModel) {
            itemDipilotAssistBindingA.f2833c.setVisibility(t.getTag() == R.string.dipilot_smart_matrix_light || iConvertTagFromTitle == 7 || iConvertTagFromTitle == 6 || iConvertTagFromTitle == 80 ? 8 : 0);
        }
        itemDipilotAssistBindingA.executePendingBindings();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public AssistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDipilotAssistBinding itemDipilotAssistBinding = (ItemDipilotAssistBinding) DataBindingUtil.inflate(LayoutInflater.from(this.f2763e), g(), viewGroup, false);
        r(itemDipilotAssistBinding);
        return new AssistViewHolder(itemDipilotAssistBinding);
    }

    public final void r(ItemDipilotAssistBinding itemDipilotAssistBinding) {
        if (b.b.a.i.a.f531a) {
            int id = itemDipilotAssistBinding.f2831a.getId();
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) itemDipilotAssistBinding.f2834d.getLayoutParams();
            layoutParams.startToStart = -1;
            layoutParams.endToEnd = id;
            layoutParams.setMarginEnd(this.f2763e.getResources().getDimensionPixelSize(R.dimen.dp_16));
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) itemDipilotAssistBinding.f2832b.getLayoutParams();
            layoutParams2.startToStart = id;
            layoutParams2.endToEnd = -1;
        }
    }

    public void s(List<T> list) {
        this.f2762d = list;
        notifyDataSetChanged();
    }

    public void t(a<T> aVar) {
        this.f2761c = aVar;
    }

    public BaseAssistAdapter(Context context, List<T> list, LifecycleOwner lifecycleOwner) {
        this.f2763e = context;
        this.f2762d = list;
        this.f2764f = lifecycleOwner;
    }
}
