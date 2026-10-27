package com.swmansion.rnscreens;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.R;
import androidx.appcompat.widget.SearchView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchViewFormatter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b-\u0010\u0018J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u0006J\u0017\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fR\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010!\u001a\u0004\u0018\u00010\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001c\u0010&\u001a\n #*\u0004\u0018\u00010\"0\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001c\u0010*\u001a\n #*\u0004\u0018\u00010'0'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u001c\u0010,\u001a\n #*\u0004\u0018\u00010'0'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)¨\u0006."}, d2 = {"Lcom/swmansion/rnscreens/SearchViewFormatter;", "", "", "textColor", "", "setTextColor", "(Ljava/lang/Integer;)V", "tintColor", "setTintColor", "headerIconColor", "setHeaderIconColor", "hintTextColor", "setHintTextColor", "", "placeholder", "", "shouldShowHintSearchIcon", "setPlaceholder", "Landroidx/appcompat/widget/SearchView;", "searchView", "Landroidx/appcompat/widget/SearchView;", "getSearchView", "()Landroidx/appcompat/widget/SearchView;", "setSearchView", "(Landroidx/appcompat/widget/SearchView;)V", "mDefaultTextColor", "Ljava/lang/Integer;", "Landroid/graphics/drawable/Drawable;", "mDefaultTintBackground", "Landroid/graphics/drawable/Drawable;", "Landroid/widget/EditText;", "getSearchEditText", "()Landroid/widget/EditText;", "searchEditText", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getSearchTextPlate", "()Landroid/view/View;", "searchTextPlate", "Landroid/widget/ImageView;", "getSearchIcon", "()Landroid/widget/ImageView;", "searchIcon", "getSearchCloseIcon", "searchCloseIcon", "<init>", "react-native-screens_release"}, k = 1, mv = {1, 6, 0})
public final class SearchViewFormatter {
    private Integer mDefaultTextColor;
    private Drawable mDefaultTintBackground;
    private SearchView searchView;

    public SearchViewFormatter(SearchView searchView) {
        Intrinsics.checkNotNullParameter(searchView, "searchView");
        this.searchView = searchView;
    }

    private final EditText getSearchEditText() {
        View viewFindViewById = this.searchView.findViewById(R.id.search_src_text);
        if (viewFindViewById instanceof EditText) {
            return (EditText) viewFindViewById;
        }
        return null;
    }

    private final View getSearchTextPlate() {
        return this.searchView.findViewById(R.id.search_plate);
    }

    private final ImageView getSearchIcon() {
        return (ImageView) this.searchView.findViewById(R.id.search_button);
    }

    private final ImageView getSearchCloseIcon() {
        return (ImageView) this.searchView.findViewById(R.id.search_close_btn);
    }

    public final void setTextColor(Integer textColor) {
        EditText searchEditText;
        ColorStateList textColors;
        Integer num = this.mDefaultTextColor;
        if (textColor == null) {
            if (num == null || (searchEditText = getSearchEditText()) == null) {
                return;
            }
            searchEditText.setTextColor(num.intValue());
            return;
        }
        if (num == null) {
            EditText searchEditText2 = getSearchEditText();
            Integer numValueOf = null;
            if (searchEditText2 != null && (textColors = searchEditText2.getTextColors()) != null) {
                numValueOf = Integer.valueOf(textColors.getDefaultColor());
            }
            this.mDefaultTextColor = numValueOf;
        }
        EditText searchEditText3 = getSearchEditText();
        if (searchEditText3 == null) {
            return;
        }
        searchEditText3.setTextColor(textColor.intValue());
    }

    public final void setTintColor(Integer tintColor) {
        Drawable drawable = this.mDefaultTintBackground;
        if (tintColor != null) {
            if (drawable == null) {
                this.mDefaultTintBackground = getSearchTextPlate().getBackground();
            }
            getSearchTextPlate().setBackgroundColor(tintColor.intValue());
        } else if (drawable != null) {
            getSearchTextPlate().setBackground(drawable);
        }
    }

    public final void setHeaderIconColor(Integer headerIconColor) {
        if (headerIconColor == null) {
            return;
        }
        int iIntValue = headerIconColor.intValue();
        getSearchIcon().setColorFilter(iIntValue);
        getSearchCloseIcon().setColorFilter(iIntValue);
    }

    public final void setHintTextColor(Integer hintTextColor) {
        if (hintTextColor == null) {
            return;
        }
        int iIntValue = hintTextColor.intValue();
        EditText searchEditText = getSearchEditText();
        if (searchEditText == null) {
            return;
        }
        searchEditText.setHintTextColor(iIntValue);
    }

    public final void setPlaceholder(String placeholder, boolean shouldShowHintSearchIcon) {
        Intrinsics.checkNotNullParameter(placeholder, "placeholder");
        if (shouldShowHintSearchIcon) {
            this.searchView.setQueryHint(placeholder);
            return;
        }
        EditText searchEditText = getSearchEditText();
        if (searchEditText == null) {
            return;
        }
        searchEditText.setHint(placeholder);
    }
}
