package com.android.car.ui.recyclerview;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.SecureView;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.widget.CarUiTextView;
import java.util.List;
import spi.b2;
import spi.f1;
import spi.l1;
import spi.u1;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CarUiRecyclerView.a {
    public final List<? extends l1> a;
    public final boolean b;
    public int c;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[a.EnumC0011a.values().length];
            b = iArr;
            try {
                iArr[a.EnumC0011a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[a.EnumC0011a.SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[a.EnumC0011a.CHECK_BOX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[a.EnumC0011a.RADIO_BUTTON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[a.EnumC0011a.CHEVRON.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[a.EnumC0011a.ICON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[a.b.values().length];
            a = iArr2;
            try {
                iArr2[a.b.CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[a.b.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[a.b.AVATAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static class b extends RecyclerView.ViewHolder {
        public final TextView a;
        public final TextView b;

        public b(@NonNull View view) {
            super(view);
            this.a = (TextView) b2.n(view, R$id.car_ui_list_item_title);
            this.b = (TextView) b2.n(view, R$id.car_ui_list_item_body);
        }
    }

    public static class c extends RecyclerView.ViewHolder {
        public final CarUiTextView a;
        public final CarUiTextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;
        public final ViewGroup f;
        public final ViewGroup g;
        public final View h;
        public final Switch i;
        public final CheckBox j;
        public final RadioButton k;
        public final ImageView l;
        public final View m;
        public final View n;
        public final View o;

        public c(@NonNull View view) {
            super(view);
            this.a = (CarUiTextView) b2.n(view, R$id.car_ui_list_item_title);
            this.b = (CarUiTextView) b2.n(view, R$id.car_ui_list_item_body);
            this.c = (ImageView) b2.n(view, R$id.car_ui_list_item_icon);
            this.d = (ImageView) b2.n(view, R$id.car_ui_list_item_content_icon);
            this.e = (ImageView) b2.n(view, R$id.car_ui_list_item_avatar_icon);
            this.f = (ViewGroup) b2.n(view, R$id.car_ui_list_item_icon_container);
            this.g = (ViewGroup) b2.n(view, R$id.car_ui_list_item_action_container);
            this.h = b2.n(view, R$id.car_ui_list_item_action_divider);
            this.i = (Switch) b2.n(view, R$id.car_ui_list_item_switch_widget);
            this.j = (CheckBox) b2.n(view, R$id.car_ui_list_item_checkbox_widget);
            this.k = (RadioButton) b2.n(view, R$id.car_ui_list_item_radio_button_widget);
            this.l = (ImageView) b2.n(view, R$id.car_ui_list_item_supplemental_icon);
            this.n = b2.n(view, R$id.car_ui_list_item_reduced_touch_interceptor);
            this.m = b2.n(view, R$id.car_ui_list_item_touch_interceptor);
            this.o = b2.n(view, R$id.car_ui_list_item_action_container_touch_interceptor);
        }

        public void a(@NonNull final com.android.car.ui.recyclerview.a aVar) {
            boolean z;
            boolean z2;
            boolean z3;
            u1 u1Var = aVar.b;
            if (u1Var != null) {
                this.a.setText(u1Var);
                this.a.setVisibility(0);
            } else {
                this.a.setVisibility(8);
            }
            this.b.setVisibility(8);
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            Drawable drawable = aVar.a;
            final int i = 2;
            final int i2 = 1;
            if (drawable != null) {
                this.f.setVisibility(0);
                int i3 = a.a[aVar.d.ordinal()];
                if (i3 == 1) {
                    this.d.setVisibility(0);
                    this.d.setImageDrawable(drawable);
                } else if (i3 == 2) {
                    this.c.setVisibility(0);
                    this.c.setImageDrawable(drawable);
                } else if (i3 == 3) {
                    this.e.setVisibility(0);
                    this.e.setImageDrawable(drawable);
                    this.e.setClipToOutline(true);
                }
            } else {
                this.f.setVisibility(8);
            }
            View view = this.m;
            if (view instanceof SecureView) {
                ((SecureView) view).setSecure(false);
                z = false;
            } else {
                z = true;
            }
            View view2 = this.n;
            if (view2 instanceof SecureView) {
                ((SecureView) view2).setSecure(false);
                z2 = z;
            } else {
                z2 = true;
            }
            View view3 = this.o;
            if (view3 instanceof SecureView) {
                ((SecureView) view3).setSecure(false);
                z3 = z2;
            } else {
                z3 = true;
            }
            if (z3) {
                Log.w("carui", "List item doesn't have a SecureView, but security was requested!");
            }
            this.h.setVisibility(8);
            this.i.setVisibility(8);
            this.j.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            final a.d dVar = aVar.h;
            switch (a.b[aVar.c.ordinal()]) {
                case 1:
                    this.g.setVisibility(8);
                    this.m.setVisibility(0);
                    View view4 = this.m;
                    final int i4 = z ? 1 : 0;
                    view4.setOnClickListener(new View.OnClickListener() { // from class: spi.m1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view5) {
                            switch (i4) {
                                case 0:
                                    a.d dVar2 = dVar;
                                    if (dVar2 != null) {
                                        ((b6) dVar2).d();
                                    }
                                    break;
                                case 1:
                                    a.d dVar3 = dVar;
                                    if (dVar3 != null) {
                                        ((b6) dVar3).d();
                                    }
                                    break;
                                default:
                                    a.d dVar4 = dVar;
                                    if (dVar4 != null) {
                                        ((b6) dVar4).d();
                                    }
                                    break;
                            }
                        }
                    });
                    this.m.setClickable(dVar != null);
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    break;
                case 2:
                    b(aVar, this.i, dVar);
                    break;
                case 3:
                    b(aVar, this.j, dVar);
                    break;
                case 4:
                    b(aVar, this.k, dVar);
                    break;
                case 5:
                    this.l.setVisibility(0);
                    this.l.setImageDrawable(this.itemView.getContext().getDrawable(R$drawable.car_ui_preference_icon_chevron));
                    this.g.setVisibility(0);
                    this.m.setVisibility(0);
                    this.m.setOnClickListener(new View.OnClickListener() { // from class: spi.m1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view5) {
                            switch (i2) {
                                case 0:
                                    a.d dVar2 = dVar;
                                    if (dVar2 != null) {
                                        ((b6) dVar2).d();
                                    }
                                    break;
                                case 1:
                                    a.d dVar3 = dVar;
                                    if (dVar3 != null) {
                                        ((b6) dVar3).d();
                                    }
                                    break;
                                default:
                                    a.d dVar4 = dVar;
                                    if (dVar4 != null) {
                                        ((b6) dVar4).d();
                                    }
                                    break;
                            }
                        }
                    });
                    this.m.setClickable(dVar != null);
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    break;
                case 6:
                    this.l.setVisibility(0);
                    ImageView imageView = this.l;
                    a.EnumC0011a enumC0011a = a.EnumC0011a.ICON;
                    imageView.setImageDrawable(null);
                    this.g.setVisibility(0);
                    this.m.setVisibility(0);
                    this.m.setOnClickListener(new View.OnClickListener() { // from class: spi.m1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view5) {
                            switch (i) {
                                case 0:
                                    a.d dVar2 = dVar;
                                    if (dVar2 != null) {
                                        ((b6) dVar2).d();
                                    }
                                    break;
                                case 1:
                                    a.d dVar3 = dVar;
                                    if (dVar3 != null) {
                                        ((b6) dVar3).d();
                                    }
                                    break;
                                default:
                                    a.d dVar4 = dVar;
                                    if (dVar4 != null) {
                                        ((b6) dVar4).d();
                                    }
                                    break;
                            }
                        }
                    });
                    this.m.setClickable(dVar != null);
                    this.n.setVisibility(8);
                    this.o.setVisibility(8);
                    break;
                default:
                    throw new IllegalStateException("Unknown secondary action type.");
            }
            this.itemView.setActivated(aVar.g);
            c(this.itemView, aVar.f);
        }

        public final void b(@NonNull final com.android.car.ui.recyclerview.a aVar, @NonNull final CompoundButton compoundButton, @Nullable final a.d dVar) {
            compoundButton.setVisibility(0);
            compoundButton.setOnCheckedChangeListener(null);
            compoundButton.setChecked(aVar.e);
            compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: spi.o1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton2, boolean z) {
                    aVar.a(z);
                }
            });
            this.m.setVisibility(0);
            this.m.setOnClickListener(new View.OnClickListener() { // from class: spi.n1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CompoundButton compoundButton2 = compoundButton;
                    a.d dVar2 = dVar;
                    compoundButton2.toggle();
                    if (dVar2 != null) {
                        ((b6) dVar2).d();
                    }
                }
            });
            this.m.setClickable(true);
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.g.setVisibility(0);
            this.g.setClickable(false);
        }

        public final void c(View view, boolean z) {
            view.setEnabled(z);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    c(viewGroup.getChildAt(i), z);
                }
            }
        }
    }

    public CarUiListItemAdapter(List<? extends l1> list) {
        this(list, false);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView.a
    public final void a(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.c == -1 ? this.a.size() : Math.min(this.a.size(), this.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (this.a.get(i) instanceof com.android.car.ui.recyclerview.a) {
            return 1;
        }
        if (this.a.get(i) instanceof f1) {
            return 2;
        }
        throw new IllegalStateException("Unknown view type.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType == 1) {
            if (!(viewHolder instanceof c)) {
                throw new IllegalStateException("Incorrect view holder type for list item.");
            }
            l1 l1Var = this.a.get(i);
            if (!(l1Var instanceof com.android.car.ui.recyclerview.a)) {
                throw new IllegalStateException("Expected item to be bound to viewHolder to be instance of CarUiContentListItem.");
            }
            ((c) viewHolder).a((com.android.car.ui.recyclerview.a) l1Var);
            return;
        }
        if (itemViewType != 2) {
            throw new IllegalStateException("Unknown item view type.");
        }
        if (!(viewHolder instanceof b)) {
            throw new IllegalStateException("Incorrect view holder type for list item.");
        }
        l1 l1Var2 = this.a.get(i);
        if (!(l1Var2 instanceof f1)) {
            throw new IllegalStateException("Expected item to be bound to viewHolder to be instance of CarUiHeaderListItem.");
        }
        b bVar = (b) viewHolder;
        f1 f1Var = (f1) l1Var2;
        bVar.a.setText(f1Var.a);
        CharSequence charSequence = f1Var.b;
        if (TextUtils.isEmpty(charSequence)) {
            bVar.b.setVisibility(8);
        } else {
            bVar.b.setText(charSequence);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return this.b ? new c(layoutInflaterFrom.inflate(R$layout.car_ui_list_item_compact, viewGroup, false)) : new c(layoutInflaterFrom.inflate(R$layout.car_ui_list_item, viewGroup, false));
        }
        if (i == 2) {
            return new b(layoutInflaterFrom.inflate(R$layout.car_ui_header_list_item, viewGroup, false));
        }
        throw new IllegalStateException("Unknown item type.");
    }

    public CarUiListItemAdapter(List<? extends l1> list, boolean z) {
        this.c = -1;
        this.a = list;
        this.b = z;
    }
}
