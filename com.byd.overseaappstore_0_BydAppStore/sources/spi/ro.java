package spi;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ro implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ ro(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r6v17 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    @Override // android.view.View.OnClickListener
    public final void onClick(android.view.View r6) {
        /*
            r5 = this;
            int r6 = r5.a
            r0 = 8
            java.lang.String r1 = "$it"
            java.lang.String r2 = "this$0"
            switch(r6) {
                case 0: goto L68;
                case 1: goto L38;
                case 2: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L80
        Lc:
            java.lang.Object r6 = r5.b
            com.byd.overseaappstore.databinding.FragmentSoftwareDetailBinding r6 = (com.byd.overseaappstore.databinding.FragmentSoftwareDetailBinding) r6
            java.lang.Object r3 = r5.c
            com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment r3 = (com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment) r3
            int r4 = com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment.o
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView r1 = r6.b
            r1.setVisibility(r0)
            com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r6 = r6.l
            r6.setVisibility(r0)
            com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel r6 = r3.j()
            com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragmentArgs r0 = r3.i()
            int r0 = r0.a
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r3)
            r6.a(r0, r1)
            return
        L38:
            java.lang.Object r6 = r5.b
            com.byd.overseaappstore.ui.view.base.BaseRvAdapter r6 = (com.byd.overseaappstore.ui.view.base.BaseRvAdapter) r6
            java.lang.Object r0 = r5.c
            com.byd.overseaappstore.ui.view.base.AbsHolder r0 = (com.byd.overseaappstore.ui.view.base.AbsHolder) r0
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
            java.lang.String r1 = "$holder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlin.jvm.functions.Function3<? super Data, ? super ContentBinding extends androidx.viewbinding.ViewBinding, ? super java.lang.Integer, kotlin.Unit> r1 = r6.a
            if (r1 == 0) goto L67
            java.util.List r6 = r6.getCurrentList()
            com.byd.overseaappstore.ui.view.base.ContentViewHolder r0 = (com.byd.overseaappstore.ui.view.base.ContentViewHolder) r0
            int r2 = r0.getAdapterPosition()
            java.lang.Object r6 = r6.get(r2)
            Binding extends androidx.viewbinding.ViewBinding r2 = r0.a
            int r0 = r0.getAdapterPosition()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.invoke(r6, r2, r0)
        L67:
            return
        L68:
            java.lang.Object r6 = r5.b
            com.byd.overseaappstore.ui.view.adapter.SearchResultAdapter r6 = (com.byd.overseaappstore.ui.view.adapter.SearchResultAdapter) r6
            java.lang.Object r0 = r5.c
            com.byd.overseaappstore.module.ability.data.upper.Software r0 = (com.byd.overseaappstore.module.ability.data.upper.Software) r0
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
            java.lang.String r1 = "$itemData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlin.jvm.functions.Function1<? super com.byd.overseaappstore.module.ability.data.upper.Software, kotlin.Unit> r6 = r6.d
            if (r6 == 0) goto L7f
            r6.invoke(r0)
        L7f:
            return
        L80:
            java.lang.Object r6 = r5.b
            com.byd.overseaappstore.databinding.FragmentTopicDetailBinding r6 = (com.byd.overseaappstore.databinding.FragmentTopicDetailBinding) r6
            java.lang.Object r3 = r5.c
            com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment r3 = (com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment) r3
            int r4 = com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment.n
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView r1 = r6.b
            r1.setVisibility(r0)
            com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView r6 = r6.f
            r6.setVisibility(r0)
            com.byd.overseaappstore.ui.vm.TopicDetailViewModel r6 = r3.j()
            com.byd.overseaappstore.ui.view.fragment.TopicDetailFragmentArgs r0 = r3.i()
            int r0 = r0.topicGroupId
            com.byd.overseaappstore.ui.view.fragment.TopicDetailFragmentArgs r1 = r3.i()
            java.lang.String r1 = r1.topicGroupTitle
            androidx.lifecycle.LifecycleCoroutineScope r2 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r3)
            r6.b(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ro.onClick(android.view.View):void");
    }
}
