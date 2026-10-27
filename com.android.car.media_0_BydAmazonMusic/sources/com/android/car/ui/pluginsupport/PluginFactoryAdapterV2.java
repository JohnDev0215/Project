package com.android.car.ui.pluginsupport;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.FocusAreaAdapterV1;
import com.android.car.ui.FocusParkingViewAdapterV1;
import com.android.car.ui.R$styleable;
import com.android.car.ui.appstyledview.AppStyledViewControllerAdapterV1;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiListItemAdapterAdapterV1;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.CarUiRecyclerViewImpl;
import com.android.car.ui.recyclerview.RecyclerViewAdapterV1;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.rotary.FocusArea;
import com.android.car.ui.rotary.FocusParkingView;
import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import com.android.car.ui.toolbar.j;
import com.android.car.ui.widget.CarUiTextView;
import com.android.car.ui.widget.CarUiTextViewImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import spi.a5;
import spi.a9;
import spi.b2;
import spi.c4;
import spi.e;
import spi.f1;
import spi.kb;
import spi.l1;
import spi.n4;
import spi.o;
import spi.o9;
import spi.p;
import spi.p9;
import spi.q;
import spi.u1;
import spi.w5;
import spi.w8;
import spi.wa;
import spi.x2;
import spi.y8;
import spi.z3;
import spi.z4;
import spi.z5;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class PluginFactoryAdapterV2 implements w8 {

    @NonNull
    private final a9 mFactoryStub = new a9();

    @NonNull
    private final y8 mOem;

    /* JADX INFO: renamed from: com.android.car.ui.pluginsupport.PluginFactoryAdapterV2$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.AdapterDataObserver {
        public final /* synthetic */ List val$items;
        public final /* synthetic */ List val$oemItems;

        public AnonymousClass1(List list, List list2) {
            this.val$oemItems = list;
            this.val$items = list2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            this.val$oemItems.clear();
            this.val$oemItems.addAll(b2.c(this.val$items, b.b));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            for (int i3 = i; i3 <= i + i2; i3++) {
                this.val$oemItems.set(i3, PluginFactoryAdapterV2.toOemListItem((l1) this.val$items.get(i3)));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            for (int i3 = i; i3 <= i + i2; i3++) {
                this.val$oemItems.add(i3, PluginFactoryAdapterV2.toOemListItem((l1) this.val$items.get(i3)));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            for (int i4 = i; i4 <= i + i3; i4++) {
                this.val$oemItems.add(i2, (z5) this.val$oemItems.remove(i4));
                i2++;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            for (int i3 = i; i3 <= i + i2; i3++) {
                this.val$oemItems.remove(i3);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            for (int i3 = i; i3 <= i + i2; i3++) {
                this.val$oemItems.set(i3, PluginFactoryAdapterV2.toOemListItem((l1) this.val$items.get(i3)));
            }
        }
    }

    /* JADX INFO: renamed from: com.android.car.ui.pluginsupport.PluginFactoryAdapterV2$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action;
        public static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType;

        static {
            int[] iArr = new int[a.b.values().length];
            $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType = iArr;
            try {
                iArr[a.b.CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType[a.b.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType[a.b.AVATAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[a.EnumC0011a.values().length];
            $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action = iArr2;
            try {
                iArr2[a.EnumC0011a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[a.EnumC0011a.SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[a.EnumC0011a.CHECK_BOX.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[a.EnumC0011a.RADIO_BUTTON.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[a.EnumC0011a.ICON.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[a.EnumC0011a.CHEVRON.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public PluginFactoryAdapterV2(@NonNull y8 y8Var) {
        this.mOem = y8Var;
        y8Var.c();
    }

    public static /* synthetic */ z5 access$000(l1 l1Var) {
        return toOemListItem(l1Var);
    }

    private Insets adaptInsets(a5 a5Var) {
        return new Insets(a5Var.a, a5Var.c, a5Var.b, a5Var.d);
    }

    private static o9 from(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CarUiRecyclerView, 0, 0);
        final int i = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_layoutStyle, 0);
        final int i2 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_numOfColumns, 1);
        final boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiRecyclerView_rotaryScrollEnabled, false);
        final int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_android_orientation, 1);
        final boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiRecyclerView_reverseLayout, false);
        final int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_carUiSize, 2);
        typedArrayObtainStyledAttributes.recycle();
        int[] iArr = {R.attr.layout_width, R.attr.layout_height, R.attr.minWidth, R.attr.minHeight, R.attr.paddingStart, R.attr.paddingLeft, R.attr.paddingEnd, R.attr.paddingRight, R.attr.paddingTop, R.attr.paddingBottom, R.attr.layout_marginStart, R.attr.layout_marginLeft, R.attr.layout_marginEnd, R.attr.layout_marginRight, R.attr.layout_marginTop, R.attr.layout_marginBottom, R.attr.background};
        Arrays.sort(iArr);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        final int layoutDimension = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_width), -1);
        final int layoutDimension2 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_height), -2);
        final int layoutDimension3 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.minWidth), 0);
        final int layoutDimension4 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.minHeight), 0);
        final int layoutDimension5 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.paddingLeft), 0);
        final int layoutDimension6 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.paddingRight), 0);
        final int layoutDimension7 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.paddingStart), 0);
        final int layoutDimension8 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.paddingEnd), 0);
        final int layoutDimension9 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.paddingTop), 0);
        final int layoutDimension10 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.paddingBottom), 0);
        final int layoutDimension11 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_marginLeft), 0);
        final int layoutDimension12 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_marginRight), 0);
        final int layoutDimension13 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_marginStart), 0);
        final int layoutDimension14 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_marginEnd), 0);
        final int layoutDimension15 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_marginTop), 0);
        final int layoutDimension16 = typedArrayObtainStyledAttributes2.getLayoutDimension(Arrays.binarySearch(iArr, R.attr.layout_marginBottom), 0);
        final Drawable drawable = typedArrayObtainStyledAttributes2.getDrawable(Arrays.binarySearch(iArr, R.attr.background));
        typedArrayObtainStyledAttributes2.recycle();
        final w5 w5Var = new w5() { // from class: com.android.car.ui.pluginsupport.PluginFactoryAdapterV2.2
            @Override // spi.w5
            public int getLayoutType() {
                return i != 1 ? 0 : 1;
            }

            @Override // spi.w5
            public int getOrientation() {
                return i3 != 0 ? 1 : 0;
            }

            @Override // spi.w5
            public boolean getReverseLayout() {
                return z2;
            }

            @Override // spi.w5
            public int getSpanCount() {
                return i2;
            }

            public /* bridge */ /* synthetic */ int getSpanSize(int i5) {
                return 1;
            }
        };
        final boolean z3 = context.getResources().getConfiguration().getLayoutDirection() == 0;
        return new o9() { // from class: com.android.car.ui.pluginsupport.PluginFactoryAdapterV2.3
            public int geMinWidth() {
                return layoutDimension3;
            }

            public Drawable getBackground() {
                return drawable;
            }

            public int getLayoutHeight() {
                return layoutDimension2;
            }

            public w5 getLayoutStyle() {
                return w5Var;
            }

            public int getLayoutWidth() {
                return layoutDimension;
            }

            public int getMarginBottom() {
                return layoutDimension16;
            }

            public int getMarginLeft() {
                int i5 = layoutDimension11;
                return i5 != 0 ? i5 : z3 ? layoutDimension13 : layoutDimension14;
            }

            public int getMarginRight() {
                int i5 = layoutDimension12;
                return i5 != 0 ? i5 : z3 ? layoutDimension14 : layoutDimension13;
            }

            public int getMarginTop() {
                return layoutDimension15;
            }

            public int getMinHeight() {
                return layoutDimension4;
            }

            public int getPaddingBottom() {
                return layoutDimension10;
            }

            public int getPaddingLeft() {
                int i5 = layoutDimension5;
                return i5 != 0 ? i5 : z3 ? layoutDimension7 : layoutDimension8;
            }

            public int getPaddingRight() {
                int i5 = layoutDimension6;
                return i5 != 0 ? i5 : z3 ? layoutDimension8 : layoutDimension7;
            }

            public int getPaddingTop() {
                return layoutDimension9;
            }

            @Override // spi.o9
            public int getSize() {
                int i5 = i4;
                if (i5 != 0) {
                    return i5 != 1 ? 2 : 1;
                }
                return 0;
            }

            public boolean isRotaryScrollEnabled() {
                return z;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$installBaseLayoutAround$2(z4 z4Var, a5 a5Var) {
        z4Var.b(adaptInsets(a5Var));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ c4 lambda$new$0(Context context) {
        return new FocusParkingViewAdapterV1(new FocusParkingView(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ z3 lambda$new$1(Context context) {
        return new FocusAreaAdapterV1(new FocusArea(context));
    }

    private static /* synthetic */ void lambda$toOemListItem$3(com.android.car.ui.recyclerview.a aVar, x2 x2Var) {
        Objects.requireNonNull(aVar);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static z5 toOemListItem(l1 l1Var) {
        if (l1Var instanceof f1) {
            f1 f1Var = (f1) l1Var;
            n4.a aVar = new n4.a(new SpannableString(f1Var.a));
            new SpannableString(f1Var.b);
            return new n4(aVar);
        }
        if (!(l1Var instanceof com.android.car.ui.recyclerview.a)) {
            throw new IllegalStateException("Unknown view type.");
        }
        com.android.car.ui.recyclerview.a aVar2 = (com.android.car.ui.recyclerview.a) l1Var;
        x2.b bVar = new x2.b(toOemListItemAction(aVar2.c));
        u1 u1Var = aVar2.b;
        if (u1Var != null) {
            toOemText(u1Var);
        }
        toOemListItemIconType(aVar2.d);
        if (aVar2.c == a.EnumC0011a.ICON && bVar.a != x2.a.ICON) {
            throw new IllegalStateException("Cannot set supplemental icon on list item that does not have an action of type ICON");
        }
        a.d dVar = aVar2.h;
        bVar.b = aVar2.e;
        return new x2(bVar);
    }

    private static x2.a toOemListItemAction(a.EnumC0011a enumC0011a) {
        switch (AnonymousClass4.$SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$Action[enumC0011a.ordinal()]) {
            case 1:
                return x2.a.NONE;
            case 2:
                return x2.a.SWITCH;
            case 3:
                return x2.a.CHECK_BOX;
            case 4:
                return x2.a.RADIO_BUTTON;
            case 5:
                return x2.a.ICON;
            case 6:
                return x2.a.CHEVRON;
            default:
                throw new IllegalStateException("Unexpected list item action type");
        }
    }

    private static x2.c toOemListItemIconType(a.b bVar) {
        int i = AnonymousClass4.$SwitchMap$com$android$car$ui$recyclerview$CarUiContentListItem$IconType[bVar.ordinal()];
        if (i == 1) {
            return x2.c.CONTENT;
        }
        if (i == 2) {
            return x2.c.STANDARD;
        }
        if (i == 3) {
            return x2.c.AVATAR;
        }
        throw new IllegalStateException("Unexpected list item icon type");
    }

    private static wa toOemText(u1 u1Var) {
        return new wa(new wa.b(u1Var.c));
    }

    public o createAppStyledView(Context context) {
        q qVarB = this.mOem.b();
        return qVarB == null ? new p(context) : new AppStyledViewControllerAdapterV1(qVarB);
    }

    public RecyclerView.Adapter<? extends RecyclerView.ViewHolder> createListItemAdapter(List<? extends l1> list) {
        List listC = b2.c(list, b.e);
        e eVarE = this.mOem.e();
        if (eVarE == null) {
            Objects.requireNonNull(this.mFactoryStub);
            return new CarUiListItemAdapter(list);
        }
        CarUiListItemAdapterAdapterV1 carUiListItemAdapterAdapterV1 = new CarUiListItemAdapterAdapterV1(eVarE);
        carUiListItemAdapterAdapterV1.registerAdapterDataObserver(new AnonymousClass1(listC, list));
        return carUiListItemAdapterAdapterV1;
    }

    @Override // spi.w8
    public CarUiRecyclerView createRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        o9 o9VarFrom = from(context, attributeSet);
        p9 p9VarF = this.mOem.f();
        if (p9VarF == null) {
            Objects.requireNonNull(this.mFactoryStub);
            return new CarUiRecyclerViewImpl(context, attributeSet);
        }
        RecyclerViewAdapterV1 recyclerViewAdapterV1 = new RecyclerViewAdapterV1(context, attributeSet, 0);
        recyclerViewAdapterV1.setRecyclerViewOEMV1(p9VarF, o9VarFrom);
        return recyclerViewAdapterV1;
    }

    @Override // spi.w8
    @NonNull
    public CarUiTextView createTextView(Context context, AttributeSet attributeSet) {
        Objects.requireNonNull(this.mFactoryStub);
        return new CarUiTextViewImpl(context, attributeSet);
    }

    @Override // spi.w8
    @Nullable
    public j installBaseLayoutAround(View view, z4 z4Var, boolean z, boolean z2) {
        if (!this.mOem.a()) {
            return this.mFactoryStub.installBaseLayoutAround(view, z4Var, z, z2);
        }
        y8 y8Var = this.mOem;
        view.getContext();
        kb kbVarD = y8Var.d();
        if (kbVarD != null) {
            return new ToolbarControllerAdapterV1(view.getContext(), kbVarD);
        }
        return null;
    }

    private static List<wa> toOemText(List<u1> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<u1> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new wa(new wa.b(it.next().c)));
        }
        return arrayList;
    }
}
