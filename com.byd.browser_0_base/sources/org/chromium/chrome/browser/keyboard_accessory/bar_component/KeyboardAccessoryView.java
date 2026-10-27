package org.chromium.chrome.browser.keyboard_accessory.bar_component;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.bq;
import defpackage.in3;
import defpackage.om3;
import defpackage.sk;
import java.util.WeakHashMap;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: loaded from: classes.dex */
class KeyboardAccessoryView extends LinearLayout {
    public static final /* synthetic */ int f = 0;
    public RecyclerView e;

    public static class a extends RecyclerView.l {
        public final int a;

        public a(int i) {
            this.a = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            if (LocalizationUtils.isLayoutRtl()) {
                rect.right = j(view, recyclerView, wVar);
            } else {
                rect.left = j(view, recyclerView, wVar);
            }
        }

        public int j(View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            return this.a;
        }
    }

    public KeyboardAccessoryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

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
    @Override // android.view.View
    public void onFinishInflate() {
        TraceEvent.q("KeyboardAccessoryView#onFinishInflate", null);
        super.onFinishInflate();
        sendAccessibilityEvent(32);
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.bar_items_view);
        this.e = recyclerView;
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(0));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.keyboard_accessory_horizontal_padding);
        sk skVar = bq.a;
        if (!N.M09VlOh_("AutofillKeyboardAccessory")) {
            recyclerView.g(new a(dimensionPixelSize));
        }
        recyclerView.setItemAnimator(null);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(recyclerView, dimensionPixelSize, 0, 0, 0);
        boolean zIsLayoutRtl = LocalizationUtils.isLayoutRtl();
        findViewById(R$id.accessory_bar_contents).setLayoutDirection(zIsLayoutRtl ? 1 : 0);
        this.e.setLayoutDirection(zIsLayoutRtl ? 1 : 0);
        setOnTouchListener(new View.OnTouchListener() { // from class: org.chromium.chrome.browser.keyboard_accessory.bar_component.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                int i = KeyboardAccessoryView.f;
                this.e.performClick();
                return true;
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: org.chromium.chrome.browser.keyboard_accessory.bar_component.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i = KeyboardAccessoryView.f;
            }
        });
        setClickable(false);
        setSoundEffectsEnabled(false);
        TraceEvent.C("KeyboardAccessoryView#onFinishInflate");
    }
}
