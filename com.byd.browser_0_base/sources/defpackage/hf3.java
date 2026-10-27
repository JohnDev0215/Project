package defpackage;

import J.N;
import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Locale;
import org.chromium.chrome.browser.infobar.TranslateCompactInfoBar;
import org.chromium.components.infobars.InfoBarCompactLayout;
import org.chromium.components.translate.R$dimen;
import org.chromium.components.translate.R$drawable;
import org.chromium.components.translate.R$id;
import org.chromium.components.translate.R$layout;
import org.chromium.components.translate.R$string;
import org.chromium.components.translate.R$style;
import org.chromium.components.translate.TranslateTabContent;
import org.chromium.components.translate.TranslateTabLayout;

/* JADX INFO: compiled from: TranslateMenuHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hf3 implements AdapterView.OnItemClickListener {
    public final b e;
    public final if3 f;
    public final ContextThemeWrapper g;
    public a h;
    public final View i;
    public ListPopupWindow j;
    public final boolean k;
    public final boolean l;

    /* JADX INFO: compiled from: TranslateMenuHelper.java */
    public final class a extends ArrayAdapter<gf3> {
        public static final /* synthetic */ int h = 0;
        public final LayoutInflater e;
        public int f;

        public a(int i) {
            super(hf3.this.g, R$layout.translate_menu_item, hf3.a(hf3.this, i));
            this.e = LayoutInflater.from(hf3.this.g);
            this.f = i;
        }

        public final View a(int i, int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.e.inflate(i2, viewGroup, false);
            }
            ((TextView) view.findViewById(R$id.menu_item_text)).setText(c(getItem(i)));
            return view;
        }

        public final String c(gf3 gf3Var) {
            if (this.f != 0) {
                return hf3.this.f.a(gf3Var.c);
            }
            String strC = hf3.this.f.c();
            int i = gf3Var.b;
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : hf3.this.g.getString(R$string.translate_option_not_source_language, strC) : hf3.this.g.getString(R$string.translate_option_never_translate, strC) : hf3.this.g.getString(R$string.translate_never_translate_site) : hf3.this.g.getString(R$string.translate_option_always_translate, strC) : hf3.this.g.getString(R$string.translate_option_more_language);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final int getItemViewType(int i) {
            return getItem(i).a;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            String displayName;
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                return a(i, R$layout.translate_menu_item, view, viewGroup);
            }
            hf3 hf3Var = hf3.this;
            if (itemViewType != 1) {
                if (itemViewType != 2) {
                    return view;
                }
                int i2 = R$layout.translate_menu_extended_item;
                if (view == null) {
                    view = this.e.inflate(i2, viewGroup, false);
                }
                gf3 item = getItem(i);
                ((TextView) view.findViewById(R$id.menu_item_text)).setText(c(item));
                TextView textView = (TextView) view.findViewById(R$id.menu_item_secondary_text);
                if3 if3Var = hf3Var.f;
                String str = item.c;
                if (if3Var.b(str)) {
                    Locale localeForLanguageTag = Locale.forLanguageTag(str);
                    displayName = localeForLanguageTag.getDisplayName(localeForLanguageTag);
                } else {
                    displayName = "";
                }
                textView.setText(displayName);
                view.findViewById(R$id.menu_item_list_divider).setVisibility(item.d ? 0 : 8);
                return view;
            }
            View viewA = a(i, R$layout.translate_menu_item_checked, view, viewGroup);
            ImageView imageView = (ImageView) viewA.findViewById(R$id.menu_item_icon);
            if (getItem(i).b == 1 && hf3Var.f.h[2]) {
                imageView.setVisibility(0);
            } else if (getItem(i).b == 3 && hf3Var.f.h[0]) {
                imageView.setVisibility(0);
            } else if (getItem(i).b == 2 && hf3Var.f.h[1]) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(4);
            }
            View viewFindViewById = viewA.findViewById(R$id.menu_item_divider);
            if (!getItem(i).d) {
                return viewA;
            }
            viewFindViewById.setVisibility(0);
            return viewA;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final int getViewTypeCount() {
            return 3;
        }
    }

    /* JADX INFO: compiled from: TranslateMenuHelper.java */
    public interface b {
    }

    public hf3(Context context, ImageButton imageButton, if3 if3Var, b bVar, boolean z, boolean z2) {
        this.g = new ContextThemeWrapper(context, R$style.OverflowMenuThemeOverlay);
        this.i = imageButton;
        this.f = if3Var;
        this.e = bVar;
        this.k = z;
        this.l = z2;
    }

    public static ArrayList a(hf3 hf3Var, int i) {
        int size;
        String[] strArr;
        hf3Var.getClass();
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new gf3(0, true));
            boolean z = hf3Var.k;
            boolean z2 = hf3Var.l;
            if (!z && !z2) {
                arrayList2.add(new gf3(1, false));
            }
            if (!z2) {
                arrayList2.add(new gf3(3, false));
            }
            arrayList2.add(new gf3(2, false));
            arrayList2.add(new gf3(4, false));
            arrayList.addAll(arrayList2);
        } else {
            boolean zMI_WWqtz = N.MI_WWqtz("ContentLanguagesInLanguagePicker");
            if3 if3Var = hf3Var.f;
            if (zMI_WWqtz && i == 1 && (strArr = if3Var.d) != null) {
                int length = strArr.length;
                boolean z3 = length > 0 && !hf3Var.c(i, strArr[length + (-1)]);
                int i2 = 0;
                while (i2 < length) {
                    String str = if3Var.d[i2];
                    if (!hf3Var.c(i, str)) {
                        arrayList.add(new gf3(2, i2, (i2 == length + (-1) && z3) || (i2 == length + (-2) && !z3), str));
                    }
                    i2++;
                }
                size = arrayList.size();
            } else {
                size = 0;
            }
            for (int i3 = 0; i3 < if3Var.c.size(); i3++) {
                if (i3 != 0 || i != 1) {
                    String str2 = if3Var.c.get(i3).a;
                    if (!hf3Var.c(i, str2)) {
                        int i4 = size + i3;
                        if (i == 1) {
                            i4--;
                        }
                        arrayList.add(new gf3(0, i4, false, str2));
                    }
                }
            }
        }
        return arrayList;
    }

    public final void b() {
        ListPopupWindow listPopupWindow = this.j;
        if (listPopupWindow == null ? false : listPopupWindow.isShowing()) {
            this.j.dismiss();
        }
    }

    public final boolean c(int i, String str) {
        if3 if3Var = this.f;
        if (i == 2 && str.equals(if3Var.a)) {
            return true;
        }
        return i == 1 && str.equals(if3Var.b);
    }

    public final void d(int i, int i2) {
        ListPopupWindow listPopupWindow = this.j;
        ContextThemeWrapper contextThemeWrapper = this.g;
        View view = this.i;
        if (listPopupWindow == null) {
            ListPopupWindow listPopupWindow2 = new ListPopupWindow(contextThemeWrapper, null, R.attr.popupMenuStyle);
            this.j = listPopupWindow2;
            listPopupWindow2.setModal(true);
            this.j.setAnchorView(view);
            this.j.setInputMethodMode(2);
            ListPopupWindow listPopupWindow3 = this.j;
            int i3 = R$drawable.menu_bg_tinted;
            Object obj = bz.a;
            listPopupWindow3.setBackgroundDrawable(contextThemeWrapper.getDrawable(i3));
            this.j.setOnItemClickListener(this);
            this.j.setVerticalOffset(view.getHeight());
            a aVar = new a(i);
            this.h = aVar;
            this.j.setAdapter(aVar);
        } else {
            a aVar2 = this.h;
            int i4 = a.h;
            if (i == 0) {
                aVar2.getClass();
            } else {
                aVar2.clear();
                aVar2.f = i;
                aVar2.addAll(a(hf3.this, i));
                aVar2.notifyDataSetChanged();
            }
        }
        if (i == 0) {
            Rect rect = new Rect();
            this.j.getBackground().getPadding(rect);
            a aVar3 = this.h;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = aVar3.getCount();
            int iMax = 0;
            int i5 = 0;
            View view2 = null;
            for (int i6 = 0; i6 < count; i6++) {
                int itemViewType = aVar3.getItemViewType(i6);
                if (itemViewType != i5) {
                    i5 = itemViewType;
                    view2 = null;
                }
                view2 = aVar3.getView(i6, view2, null);
                view2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view2.getMeasuredWidth());
            }
            int i7 = iMax + rect.left + rect.right;
            ListPopupWindow listPopupWindow4 = this.j;
            if (i2 > 0 && i7 > i2) {
                i7 = i2;
            }
            listPopupWindow4.setWidth(i7);
        } else {
            this.j.setWidth(contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.infobar_translate_menu_width));
        }
        if (view.getLayoutDirection() == 1) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            this.j.setHorizontalOffset(-iArr[0]);
        }
        if (this.j.isShowing()) {
            return;
        }
        this.j.show();
        this.j.getListView().setItemsCanFocus(true);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        b();
        gf3 item = this.h.getItem(i);
        int i2 = this.h.f;
        b bVar = this.e;
        if (i2 == 0) {
            int i3 = item.b;
            TranslateCompactInfoBar translateCompactInfoBar = (TranslateCompactInfoBar) bVar;
            if (i3 == 0) {
                translateCompactInfoBar.getClass();
                TranslateCompactInfoBar.z(4);
                translateCompactInfoBar.y(1);
                hf3 hf3Var = translateCompactInfoBar.u;
                InfoBarCompactLayout infoBarCompactLayout = translateCompactInfoBar.w;
                hf3Var.d(1, infoBarCompactLayout != null ? infoBarCompactLayout.getWidth() : 0);
                return;
            }
            if3 if3Var = translateCompactInfoBar.q;
            if (i3 == 1) {
                if (if3Var.h[2]) {
                    TranslateCompactInfoBar.z(19);
                    translateCompactInfoBar.x(0);
                    return;
                } else {
                    TranslateCompactInfoBar.z(7);
                    translateCompactInfoBar.A("Translate.CompactInfobar.Language.AlwaysTranslate", if3Var.a);
                    translateCompactInfoBar.w(18, 0, translateCompactInfoBar.k.getString(org.chromium.chrome.R$string.translate_snackbar_always_translate, if3Var.c(), if3Var.a(if3Var.b)));
                    return;
                }
            }
            if (i3 == 2) {
                if (if3Var.h[1]) {
                    TranslateCompactInfoBar.z(27);
                    translateCompactInfoBar.x(1);
                    return;
                } else {
                    TranslateCompactInfoBar.z(9);
                    translateCompactInfoBar.w(20, 1, translateCompactInfoBar.k.getString(org.chromium.chrome.R$string.translate_snackbar_site_never));
                    return;
                }
            }
            if (i3 != 3) {
                if (i3 != 4) {
                    return;
                }
                TranslateCompactInfoBar.z(6);
                translateCompactInfoBar.y(2);
                hf3 hf3Var2 = translateCompactInfoBar.u;
                InfoBarCompactLayout infoBarCompactLayout2 = translateCompactInfoBar.w;
                hf3Var2.d(2, infoBarCompactLayout2 != null ? infoBarCompactLayout2.getWidth() : 0);
                return;
            }
            if (if3Var.h[0]) {
                TranslateCompactInfoBar.z(26);
                translateCompactInfoBar.x(2);
                return;
            } else {
                TranslateCompactInfoBar.z(8);
                translateCompactInfoBar.A("Translate.CompactInfobar.Language.NeverTranslate", if3Var.a);
                translateCompactInfoBar.w(19, 2, translateCompactInfoBar.k.getString(org.chromium.chrome.R$string.translate_snackbar_language_never, if3Var.c()));
                return;
            }
        }
        if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
            String str = item.c;
            TranslateCompactInfoBar translateCompactInfoBar2 = (TranslateCompactInfoBar) bVar;
            if (translateCompactInfoBar2.r != 0) {
                if3 if3Var2 = translateCompactInfoBar2.q;
                boolean z = if3Var2.b(str) && if3Var2.b(if3Var2.b);
                if (z) {
                    if3Var2.a = str;
                }
                if (z) {
                    translateCompactInfoBar2.A("Translate.CompactInfobar.Language.PageNotIn", if3Var2.a);
                    N.Mcr$d_0m(translateCompactInfoBar2.r, translateCompactInfoBar2, 0, str);
                    TranslateTabLayout translateTabLayout = translateCompactInfoBar2.s;
                    String strA = if3Var2.a(str);
                    if (translateTabLayout.getTabCount() > 0) {
                        TabLayout.g gVarK = translateTabLayout.k(0);
                        ((TranslateTabContent) gVarK.f).setText(strA);
                        gVarK.d = strA;
                        gVarK.e();
                    }
                    translateCompactInfoBar2.B = true;
                    translateCompactInfoBar2.s(3);
                    return;
                }
                return;
            }
            return;
        }
        String str2 = item.c;
        TranslateCompactInfoBar translateCompactInfoBar3 = (TranslateCompactInfoBar) bVar;
        if (translateCompactInfoBar3.r != 0) {
            if3 if3Var3 = translateCompactInfoBar3.q;
            boolean z2 = if3Var3.b(if3Var3.a) && if3Var3.b(str2);
            if (z2) {
                if3Var3.b = str2;
            }
            if (z2) {
                TranslateTabLayout translateTabLayout2 = translateCompactInfoBar3.s;
                String strA2 = if3Var3.a(str2);
                if (1 < translateTabLayout2.getTabCount()) {
                    TabLayout.g gVarK2 = translateTabLayout2.k(1);
                    ((TranslateTabContent) gVarK2.f).setText(strA2);
                    gVarK2.d = strA2;
                    gVarK2.e();
                }
                i = 1;
            }
            if (i != 0) {
                TranslateCompactInfoBar.z(5);
                translateCompactInfoBar3.A("Translate.CompactInfobar.Language.MoreLanguages", if3Var3.b);
                N.Mcr$d_0m(translateCompactInfoBar3.r, translateCompactInfoBar3, 1, str2);
                translateCompactInfoBar3.B = true;
                translateCompactInfoBar3.s(3);
            }
        }
    }
}
