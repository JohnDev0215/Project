package com.android.car.ui.toolbar;

import android.annotation.TargetApi;
import android.car.drivingstate.XCarUxRestrictions;
import android.car.utils.Logs;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.ui.R$dimen;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$string;
import com.android.car.ui.toolbar.SearchView;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import spi.b2;
import spi.cb;
import spi.db;
import spi.e2;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(30)
public class SearchView extends ConstraintLayout {
    public static final /* synthetic */ int r = 0;
    public final InputMethodManager a;
    public final f b;
    public final ImageView c;
    public final EditText d;
    public CharSequence e;
    public boolean f;
    public final View g;
    public final int h;
    public final int i;
    public final int j;
    public Set<Consumer<String>> k;
    public Set<Runnable> l;
    public Set<db> m;
    public Set<cb> n;
    public final a o;
    public boolean p;
    public boolean q;

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            SearchView searchView = SearchView.this;
            String string = editable.toString();
            searchView.g.setVisibility(TextUtils.isEmpty(string) ? 8 : 0);
            Iterator<Consumer<String>> it = searchView.k.iterator();
            while (it.hasNext()) {
                it.next().accept(string);
            }
            Iterator<db> it2 = searchView.m.iterator();
            while (it2.hasNext()) {
                it2.next().a();
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b implements e2.a {
        private b() {
        }

        @Override // spi.e2.a
        public final void a(@NonNull XCarUxRestrictions xCarUxRestrictions) {
            if ((xCarUxRestrictions.getActiveRestrictions() & 8) != 0) {
                SearchView searchView = SearchView.this;
                searchView.f = true;
                EditText editText = searchView.d;
                if (editText == null) {
                    return;
                }
                editText.setHint(editText.getContext().getString(R$string.car_ui_restricted_while_driving));
                searchView.d.setEnabled(false);
                return;
            }
            SearchView searchView2 = SearchView.this;
            searchView2.f = false;
            EditText editText2 = searchView2.d;
            if (editText2 == null) {
                return;
            }
            editText2.setHint(searchView2.e);
            searchView2.d.setEnabled(true);
        }

        public /* synthetic */ b(SearchView searchView, a aVar) {
            this();
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public final void e() {
        this.d.clearFocus();
        setPlainText(true);
        Iterator<Runnable> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        Iterator<cb> it2 = this.n.iterator();
        while (it2.hasNext()) {
            it2.next().a();
        }
    }

    public EditText getEdit() {
        return this.d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean zIsShown = isShown();
        if (zIsShown && !this.q) {
            this.g.setVisibility(this.d.getText().length() > 0 ? 0 : 8);
            this.d.requestFocus();
            this.a.showSoftInput(this.d, 0);
        }
        this.q = zIsShown;
    }

    public void setHint(CharSequence charSequence) {
        this.e = charSequence;
        if (this.f) {
            return;
        }
        this.d.setHint(charSequence);
    }

    public void setIcon(Drawable drawable) {
        if (drawable == null) {
            this.c.setImageResource(R$drawable.car_ui_icon_search);
        } else {
            this.c.setImageDrawable(drawable);
        }
    }

    public void setPlainText(boolean z) {
        if (z != this.p) {
            if (z) {
                this.d.setPaddingRelative(this.h, 0, this.j, 0);
                this.d.setImeOptions(6);
                this.c.setVisibility(8);
            } else {
                this.d.setPaddingRelative(this.i, 0, this.j, 0);
                this.d.setImeOptions(3);
                this.c.setVisibility(0);
            }
            this.p = z;
            this.a.restartInput(this.d);
        }
    }

    public void setSearchConfig(e eVar) {
        f fVar = this.b;
        Objects.requireNonNull(fVar);
        if (eVar == null) {
            eVar = e.a().a();
        }
        fVar.k = eVar;
    }

    public void setSearchQuery(String str) {
        Logs.e(androidx.appcompat.widget.SearchView.LOG_TAG, "setSearchQuery " + str);
        this.d.removeTextChangedListener(this.o);
        this.d.setText(str);
        EditText editText = this.d;
        editText.setSelection(editText.getText().length());
        this.d.addTextChangedListener(this.o);
    }

    public void setmSearchTextBack(int i) {
        this.d.setBackgroundResource(i);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        new b(this, null);
        this.k = Collections.emptySet();
        this.l = Collections.emptySet();
        this.m = Collections.emptySet();
        this.n = Collections.emptySet();
        a aVar = new a();
        this.o = aVar;
        final int i2 = 0;
        this.p = false;
        this.q = false;
        this.a = (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
        final int i3 = 1;
        LayoutInflater.from(context).inflate(R$layout.car_ui_toolbar_search_view, (ViewGroup) this, true);
        EditText editText = (EditText) b2.n(this, R$id.car_ui_toolbar_search_bar);
        this.d = editText;
        this.c = (ImageView) b2.n(this, R$id.car_ui_toolbar_search_icon);
        View viewN = b2.n(this, R$id.car_ui_toolbar_search_close);
        this.g = viewN;
        viewN.setOnClickListener(new View.OnClickListener(this) { // from class: spi.w9
            public final /* synthetic */ SearchView b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        SearchView searchView = this.b;
                        int i4 = SearchView.r;
                        Objects.requireNonNull(searchView);
                        if (view.isFocused()) {
                            searchView.d.requestFocus();
                            searchView.a.showSoftInput(searchView.d, 0);
                        }
                        searchView.d.getText().clear();
                        break;
                    default:
                        this.b.a.showSoftInput(view, 0);
                        break;
                }
            }
        });
        viewN.setVisibility(8);
        editText.setBackgroundResource(R$drawable.byd_car_toolbar_search_bg_selector);
        editText.setPadding(20, 0, 0, 0);
        this.h = editText.getPaddingStart();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.car_ui_toolbar_search_search_icon_container_width);
        this.i = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.car_ui_toolbar_search_close_icon_container_width);
        this.j = dimensionPixelSize2;
        editText.setSaveEnabled(false);
        editText.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize2, 0);
        editText.setOnClickListener(new View.OnClickListener(this) { // from class: spi.w9
            public final /* synthetic */ SearchView b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i3) {
                    case 0:
                        SearchView searchView = this.b;
                        int i4 = SearchView.r;
                        Objects.requireNonNull(searchView);
                        if (view.isFocused()) {
                            searchView.d.requestFocus();
                            searchView.a.showSoftInput(searchView.d, 0);
                        }
                        searchView.d.getText().clear();
                        break;
                    default:
                        this.b.a.showSoftInput(view, 0);
                        break;
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: spi.x9
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SearchView searchView = this.a;
                int i4 = SearchView.r;
                if (!z) {
                    searchView.a.hideSoftInputFromWindow(view.getWindowToken(), 0);
                } else {
                    searchView.setPlainText(false);
                    searchView.a.showSoftInput(view, 0);
                }
            }
        });
        editText.addTextChangedListener(aVar);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: spi.y9
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i4, KeyEvent keyEvent) {
                int keyCode;
                SearchView searchView = this.a;
                int i5 = SearchView.r;
                Objects.requireNonNull(searchView);
                if (i4 == 6 || i4 == 3) {
                    searchView.e();
                    return false;
                }
                if (!(keyEvent != null && ((keyCode = keyEvent.getKeyCode()) == 66 || keyCode == 160 || keyCode == 84))) {
                    return false;
                }
                if (keyEvent.getAction() == 1) {
                    searchView.e();
                }
                return true;
            }
        });
        final f fVar = new f(context);
        this.b = fVar;
        fVar.g(editText);
        if (editText instanceof CarUiEditText) {
            ((CarUiEditText) editText).a = new BiConsumer() { // from class: spi.z9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    com.android.car.ui.toolbar.f.b(fVar, (String) obj, (Bundle) obj2);
                }
            };
        }
    }
}
