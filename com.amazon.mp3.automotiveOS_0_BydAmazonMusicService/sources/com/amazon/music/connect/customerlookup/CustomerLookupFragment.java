package com.amazon.music.connect.customerlookup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.connect.R$id;
import com.amazon.music.connect.R$layout;
import com.amazon.music.connect.R$string;
import com.amazon.music.connect.customerlookup.CustomerLookupEvent;
import com.amazon.music.connect.customerlookup.adapters.CustomerLookupAdapter;
import com.amazon.music.connect.customerlookup.adapters.CustomerLookupSuggestionsAdapter;
import com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: CustomerLookupFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0003J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\u0002H\u0002J\b\u0010\r\u001a\u00020\u0002H\u0002J\b\u0010\u000e\u001a\u00020\u0002H\u0002J\b\u0010\u000f\u001a\u00020\u0002H\u0002J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0002H\u0002J$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u001c\u001a\u00020\u00022\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0002H\u0016J\b\u0010 \u001a\u00020\u0002H\u0016R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b$\u0010#R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020%8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b(\u0010'R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020:8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010>\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?¨\u0006@"}, d2 = {"Lcom/amazon/music/connect/customerlookup/CustomerLookupFragment;", "Landroidx/fragment/app/Fragment;", "", "hideKeyboard", "setupSearchBarSubmit", "setupClearClick", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "", "drawablePosition", "", "findSearchBarDrawablePosition", "setupSearchWatcher", "updateResultsList", "setupCancelClick", "clearData", "", "input", "noResultsFound", "resetNoResultsFound", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "onCreate", "view", "onViewCreated", "onResume", "onDestroyView", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "resultsViewManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "suggestionsViewManager", "Landroidx/recyclerview/widget/RecyclerView;", "resultsRecycler", "Landroidx/recyclerview/widget/RecyclerView;", "suggestionsRecycler", "Landroid/widget/EditText;", "customerSearchBar", "Landroid/widget/EditText;", "searchInput", "Ljava/lang/String;", "Lcom/amazon/music/connect/customerlookup/viewmodels/CustomerLookupViewModel;", "lookupViewModel", "Lcom/amazon/music/connect/customerlookup/viewmodels/CustomerLookupViewModel;", "Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupAdapter;", "resultsAdapter", "Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupAdapter;", "Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupSuggestionsAdapter;", "suggestionsAdapter", "Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupSuggestionsAdapter;", "Lcom/amazon/ui/foundations/views/BaseButton;", "cancelButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Landroid/widget/TextView;", "noResultsText", "Landroid/widget/TextView;", "noResultsQuery", "restoreSearchResults", "Z", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class CustomerLookupFragment extends Fragment {
    private BaseButton cancelButton;
    private EditText customerSearchBar;
    private CustomerLookupViewModel lookupViewModel;
    private TextView noResultsQuery;
    private TextView noResultsText;
    private boolean restoreSearchResults;
    private CustomerLookupAdapter resultsAdapter;
    private RecyclerView resultsRecycler;
    private RecyclerView.LayoutManager resultsViewManager;
    private String searchInput;
    private CustomerLookupSuggestionsAdapter suggestionsAdapter;
    private RecyclerView suggestionsRecycler;
    private RecyclerView.LayoutManager suggestionsViewManager;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R$layout.customer_lookup_layout, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…layout, container, false)");
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observer<? super List<String>> observer = new Observer() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CustomerLookupFragment.m1900onCreate$lambda0(this.f$0, (List) obj);
            }
        };
        Observer<? super List<CustomerLookupResult>> observer2 = new Observer() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CustomerLookupFragment.m1901onCreate$lambda1(this.f$0, (List) obj);
            }
        };
        this.lookupViewModel.getCustomerSuggestions().observe(this, observer);
        this.lookupViewModel.getLookupResults().observe(this, observer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m1900onCreate$lambda0(CustomerLookupFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = this$0.suggestionsAdapter;
        if (customerLookupSuggestionsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
            customerLookupSuggestionsAdapter = null;
        }
        if (list == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
        }
        customerLookupSuggestionsAdapter.replaceAll((ArrayList) list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m1901onCreate$lambda1(CustomerLookupFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            CustomerLookupAdapter customerLookupAdapter = null;
            EditText editText = null;
            if (((ArrayList) list).isEmpty()) {
                EditText editText2 = this$0.customerSearchBar;
                if (editText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                } else {
                    editText = editText2;
                }
                this$0.noResultsFound(editText.getText().toString());
                return;
            }
            CustomerLookupAdapter customerLookupAdapter2 = this$0.resultsAdapter;
            if (customerLookupAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resultsAdapter");
            } else {
                customerLookupAdapter = customerLookupAdapter2;
            }
            customerLookupAdapter.clear();
            this$0.updateResultsList();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<com.amazon.music.connect.customerlookup.CustomerLookupResult>{ kotlin.collections.TypeAliasesKt.ArrayList<com.amazon.music.connect.customerlookup.CustomerLookupResult> }");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View viewFindViewById = view.findViewById(R$id.customer_search_bar);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.EditText");
        }
        this.customerSearchBar = (EditText) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R$id.profile_search_cancel);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.ui.foundations.views.BaseButton");
        }
        BaseButton baseButton = (BaseButton) viewFindViewById2;
        this.cancelButton = baseButton;
        int i = R$string.customer_lookup_cancel;
        String string = getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.customer_lookup_cancel)");
        baseButton.setText(string);
        BaseButton baseButton2 = this.cancelButton;
        RecyclerView recyclerView = null;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
            baseButton2 = null;
        }
        String string2 = getString(i);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.customer_lookup_cancel)");
        baseButton2.setActiveText(string2);
        setupSearchWatcher();
        setupSearchBarSubmit();
        setupClearClick();
        setupCancelClick();
        View viewFindViewById3 = view.findViewById(R$id.profile_results);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.profile_results)");
        this.resultsRecycler = (RecyclerView) viewFindViewById3;
        this.resultsAdapter = new CustomerLookupAdapter(new Function1<CustomerLookupResult, Unit>() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment.onViewCreated.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CustomerLookupResult customerLookupResult) {
                invoke2(customerLookupResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CustomerLookupResult searchResult) {
                Intrinsics.checkNotNullParameter(searchResult, "searchResult");
                CustomerLookupFragment.this.restoreSearchResults = true;
                CustomerLookupFragment.this.lookupViewModel.postEvent(CustomerLookupEvent.FragmentAction.PROFILE_NAVIGATION, searchResult.getProfileId());
            }
        }, this.lookupViewModel.getStyleSheet());
        RecyclerView recyclerView2 = this.resultsRecycler;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
            recyclerView2 = null;
        }
        CustomerLookupAdapter customerLookupAdapter = this.resultsAdapter;
        if (customerLookupAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsAdapter");
            customerLookupAdapter = null;
        }
        recyclerView2.setAdapter(customerLookupAdapter);
        this.resultsViewManager = new LinearLayoutManager(view.getContext());
        RecyclerView recyclerView3 = this.resultsRecycler;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
            recyclerView3 = null;
        }
        RecyclerView.LayoutManager layoutManager = this.resultsViewManager;
        if (layoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsViewManager");
            layoutManager = null;
        }
        recyclerView3.setLayoutManager(layoutManager);
        View viewFindViewById4 = view.findViewById(R$id.profile_suggestions);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.profile_suggestions)");
        this.suggestionsRecycler = (RecyclerView) viewFindViewById4;
        this.suggestionsAdapter = new CustomerLookupSuggestionsAdapter(new Function1<String, Unit>() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment.onViewCreated.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String suggestion) {
                Intrinsics.checkNotNullParameter(suggestion, "suggestion");
                EditText editText = CustomerLookupFragment.this.customerSearchBar;
                EditText editText2 = null;
                if (editText == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                    editText = null;
                }
                editText.setText(suggestion);
                CustomerLookupFragment.this.hideKeyboard();
                RecyclerView recyclerView4 = CustomerLookupFragment.this.suggestionsRecycler;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
                    recyclerView4 = null;
                }
                recyclerView4.setVisibility(8);
                RecyclerView recyclerView5 = CustomerLookupFragment.this.resultsRecycler;
                if (recyclerView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
                    recyclerView5 = null;
                }
                recyclerView5.setVisibility(0);
                CustomerLookupFragment.this.lookupViewModel.fetchCustomerResults(suggestion);
                EditText editText3 = CustomerLookupFragment.this.customerSearchBar;
                if (editText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                } else {
                    editText2 = editText3;
                }
                editText2.clearFocus();
            }
        });
        RecyclerView recyclerView4 = this.suggestionsRecycler;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
            recyclerView4 = null;
        }
        CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = this.suggestionsAdapter;
        if (customerLookupSuggestionsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
            customerLookupSuggestionsAdapter = null;
        }
        recyclerView4.setAdapter(customerLookupSuggestionsAdapter);
        this.suggestionsViewManager = new LinearLayoutManager(view.getContext());
        RecyclerView recyclerView5 = this.suggestionsRecycler;
        if (recyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
            recyclerView5 = null;
        }
        RecyclerView.LayoutManager layoutManager2 = this.suggestionsViewManager;
        if (layoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsViewManager");
            layoutManager2 = null;
        }
        recyclerView5.setLayoutManager(layoutManager2);
        RecyclerView recyclerView6 = this.suggestionsRecycler;
        if (recyclerView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
            recyclerView6 = null;
        }
        recyclerView6.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return CustomerLookupFragment.m1902onViewCreated$lambda2(this.f$0, view2, motionEvent);
            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
        RecyclerView recyclerView7 = this.suggestionsRecycler;
        if (recyclerView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
        } else {
            recyclerView = recyclerView7;
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
        View viewFindViewById5 = view.findViewById(R$id.no_results_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.no_results_text)");
        this.noResultsText = (TextView) viewFindViewById5;
        View viewFindViewById6 = view.findViewById(R$id.no_results_query);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "view.findViewById(R.id.no_results_query)");
        this.noResultsQuery = (TextView) viewFindViewById6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final boolean m1902onViewCreated$lambda2(CustomerLookupFragment this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = this$0.customerSearchBar;
        RecyclerView recyclerView = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        if (editText.hasFocus()) {
            this$0.hideKeyboard();
            EditText editText2 = this$0.customerSearchBar;
            if (editText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                editText2 = null;
            }
            editText2.clearFocus();
            CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = this$0.suggestionsAdapter;
            if (customerLookupSuggestionsAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
                customerLookupSuggestionsAdapter = null;
            }
            if (customerLookupSuggestionsAdapter.getItemCount() == 0) {
                CustomerLookupAdapter customerLookupAdapter = this$0.resultsAdapter;
                if (customerLookupAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("resultsAdapter");
                    customerLookupAdapter = null;
                }
                if (customerLookupAdapter.getItemCount() > 0) {
                    RecyclerView recyclerView2 = this$0.suggestionsRecycler;
                    if (recyclerView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
                        recyclerView2 = null;
                    }
                    recyclerView2.setVisibility(8);
                    RecyclerView recyclerView3 = this$0.resultsRecycler;
                    if (recyclerView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
                    } else {
                        recyclerView = recyclerView3;
                    }
                    recyclerView.setVisibility(0);
                }
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = null;
        CustomerLookupViewModel.postEvent$default(this.lookupViewModel, CustomerLookupEvent.FragmentAction.FRAGMENT_RESUMED, null, 2, null);
        if ((!this.lookupViewModel.getLookupResultsValue().isEmpty()) && this.restoreSearchResults) {
            RecyclerView recyclerView2 = this.suggestionsRecycler;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
                recyclerView2 = null;
            }
            recyclerView2.setVisibility(8);
            this.restoreSearchResults = false;
            CustomerLookupAdapter customerLookupAdapter = this.resultsAdapter;
            if (customerLookupAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resultsAdapter");
                customerLookupAdapter = null;
            }
            customerLookupAdapter.replaceAll(this.lookupViewModel.getLookupResultsValue());
        } else {
            clearData();
        }
        RecyclerView recyclerView3 = this.resultsRecycler;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        RecyclerView recyclerView = this.resultsRecycler;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
            recyclerView = null;
        }
        recyclerView.setAdapter(null);
        CustomerLookupViewModel.postEvent$default(this.lookupViewModel, CustomerLookupEvent.FragmentAction.FRAGMENT_DESTROYED, null, 2, null);
        clearData();
        hideKeyboard();
        super.onDestroyView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideKeyboard() {
        Context context = getContext();
        EditText editText = null;
        Object systemService = context == null ? null : context.getSystemService("input_method");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        EditText editText2 = this.customerSearchBar;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
        } else {
            editText = editText2;
        }
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private final void setupSearchBarSubmit() {
        EditText editText = this.customerSearchBar;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.setOnKeyListener(new View.OnKeyListener() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return CustomerLookupFragment.m1905setupSearchBarSubmit$lambda3(this.f$0, view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSearchBarSubmit$lambda-3, reason: not valid java name */
    public static final boolean m1905setupSearchBarSubmit$lambda3(CustomerLookupFragment this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 66 || keyEvent.getAction() != 1) {
            return false;
        }
        EditText editText = this$0.customerSearchBar;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        String string = editText.getText().toString();
        this$0.searchInput = string;
        this$0.lookupViewModel.fetchCustomerResults(string);
        return true;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private final void setupClearClick() {
        EditText editText = this.customerSearchBar;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CustomerLookupFragment.m1904setupClearClick$lambda4(this.f$0, view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupClearClick$lambda-4, reason: not valid java name */
    public static final boolean m1904setupClearClick$lambda4(CustomerLookupFragment this$0, View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (event.getAction() != 1) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(event, "event");
        if (!this$0.findSearchBarDrawablePosition(event, 2)) {
            return false;
        }
        this$0.clearData();
        EditText editText = this$0.customerSearchBar;
        RecyclerView recyclerView = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.clearFocus();
        RecyclerView recyclerView2 = this$0.suggestionsRecycler;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
        } else {
            recyclerView = recyclerView2;
        }
        recyclerView.requestFocus();
        this$0.hideKeyboard();
        this$0.resetNoResultsFound();
        return true;
    }

    private final boolean findSearchBarDrawablePosition(MotionEvent event, int drawablePosition) {
        float x = event.getX();
        EditText editText = this.customerSearchBar;
        EditText editText2 = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        int width = editText.getWidth();
        EditText editText3 = this.customerSearchBar;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText3 = null;
        }
        int iWidth = width - editText3.getCompoundDrawables()[drawablePosition].getBounds().width();
        EditText editText4 = this.customerSearchBar;
        if (editText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
        } else {
            editText2 = editText4;
        }
        return x >= ((float) (iWidth - editText2.getPaddingEnd()));
    }

    private final void setupSearchWatcher() {
        EditText editText = this.customerSearchBar;
        EditText editText2 = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                CustomerLookupFragment.m1906setupSearchWatcher$lambda5(this.f$0, view, z);
            }
        });
        EditText editText3 = this.customerSearchBar;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
        } else {
            editText2 = editText3;
        }
        editText2.addTextChangedListener(new TextWatcher() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment.setupSearchWatcher.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable p0) {
                EditText editText4 = CustomerLookupFragment.this.customerSearchBar;
                CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = null;
                EditText editText5 = null;
                if (editText4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                    editText4 = null;
                }
                if (editText4.getText().toString().length() > 0) {
                    CustomerLookupViewModel customerLookupViewModel = CustomerLookupFragment.this.lookupViewModel;
                    EditText editText6 = CustomerLookupFragment.this.customerSearchBar;
                    if (editText6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                    } else {
                        editText5 = editText6;
                    }
                    customerLookupViewModel.fetchCustomerSuggestions(editText5.getText().toString());
                    return;
                }
                EditText editText7 = CustomerLookupFragment.this.customerSearchBar;
                if (editText7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
                    editText7 = null;
                }
                if (StringsKt__StringsJVMKt.isBlank(editText7.getText().toString())) {
                    CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter2 = CustomerLookupFragment.this.suggestionsAdapter;
                    if (customerLookupSuggestionsAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
                    } else {
                        customerLookupSuggestionsAdapter = customerLookupSuggestionsAdapter2;
                    }
                    customerLookupSuggestionsAdapter.clear();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupSearchWatcher$lambda-5, reason: not valid java name */
    public static final void m1906setupSearchWatcher$lambda5(CustomerLookupFragment this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RecyclerView recyclerView = null;
        if (!z) {
            BaseButton baseButton = this$0.cancelButton;
            if (baseButton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
                baseButton = null;
            }
            baseButton.setVisibility(8);
        }
        if (z) {
            BaseButton baseButton2 = this$0.cancelButton;
            if (baseButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
                baseButton2 = null;
            }
            baseButton2.setVisibility(0);
            CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = this$0.suggestionsAdapter;
            if (customerLookupSuggestionsAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
                customerLookupSuggestionsAdapter = null;
            }
            customerLookupSuggestionsAdapter.clear();
            RecyclerView recyclerView2 = this$0.resultsRecycler;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
                recyclerView2 = null;
            }
            recyclerView2.setVisibility(8);
            this$0.resetNoResultsFound();
            RecyclerView recyclerView3 = this$0.suggestionsRecycler;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
            } else {
                recyclerView = recyclerView3;
            }
            recyclerView.setVisibility(0);
        }
    }

    private final void updateResultsList() {
        hideKeyboard();
        EditText editText = this.customerSearchBar;
        CustomerLookupAdapter customerLookupAdapter = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.clearFocus();
        BaseButton baseButton = this.cancelButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
            baseButton = null;
        }
        baseButton.setVisibility(8);
        RecyclerView recyclerView = this.resultsRecycler;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
            recyclerView = null;
        }
        if (recyclerView.getVisibility() == 8) {
            RecyclerView recyclerView2 = this.suggestionsRecycler;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
                recyclerView2 = null;
            }
            recyclerView2.setVisibility(8);
            resetNoResultsFound();
            RecyclerView recyclerView3 = this.suggestionsRecycler;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
                recyclerView3 = null;
            }
            recyclerView3.requestFocus();
            RecyclerView recyclerView4 = this.resultsRecycler;
            if (recyclerView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
                recyclerView4 = null;
            }
            recyclerView4.setVisibility(0);
        }
        CustomerLookupAdapter customerLookupAdapter2 = this.resultsAdapter;
        if (customerLookupAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsAdapter");
        } else {
            customerLookupAdapter = customerLookupAdapter2;
        }
        customerLookupAdapter.replaceAll(this.lookupViewModel.getLookupResultsValue());
    }

    private final void setupCancelClick() {
        BaseButton baseButton = this.cancelButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.connect.customerlookup.CustomerLookupFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerLookupFragment.m1903setupCancelClick$lambda6(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupCancelClick$lambda-6, reason: not valid java name */
    public static final void m1903setupCancelClick$lambda6(CustomerLookupFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideKeyboard();
        EditText editText = this$0.customerSearchBar;
        RecyclerView recyclerView = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.setText("");
        EditText editText2 = this$0.customerSearchBar;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText2 = null;
        }
        editText2.clearFocus();
        BaseButton baseButton = this$0.cancelButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
            baseButton = null;
        }
        baseButton.setVisibility(8);
        CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = this$0.suggestionsAdapter;
        if (customerLookupSuggestionsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
            customerLookupSuggestionsAdapter = null;
        }
        customerLookupSuggestionsAdapter.clear();
        RecyclerView recyclerView2 = this$0.suggestionsRecycler;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
            recyclerView2 = null;
        }
        if (recyclerView2.getVisibility() == 0) {
            RecyclerView recyclerView3 = this$0.suggestionsRecycler;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
                recyclerView3 = null;
            }
            recyclerView3.setVisibility(8);
            RecyclerView recyclerView4 = this$0.resultsRecycler;
            if (recyclerView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resultsRecycler");
            } else {
                recyclerView = recyclerView4;
            }
            recyclerView.setVisibility(0);
        }
    }

    private final void clearData() {
        CustomerLookupAdapter customerLookupAdapter = this.resultsAdapter;
        EditText editText = null;
        if (customerLookupAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultsAdapter");
            customerLookupAdapter = null;
        }
        customerLookupAdapter.clear();
        CustomerLookupSuggestionsAdapter customerLookupSuggestionsAdapter = this.suggestionsAdapter;
        if (customerLookupSuggestionsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsAdapter");
            customerLookupSuggestionsAdapter = null;
        }
        customerLookupSuggestionsAdapter.clear();
        EditText editText2 = this.customerSearchBar;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
        } else {
            editText = editText2;
        }
        editText.setText("");
    }

    private final void noResultsFound(String input) {
        if (StringsKt__StringsJVMKt.isBlank(input)) {
            return;
        }
        TextView textView = this.noResultsQuery;
        RecyclerView recyclerView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsQuery");
            textView = null;
        }
        textView.setText(input);
        TextView textView2 = this.noResultsText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsText");
            textView2 = null;
        }
        textView2.setText(getString(R$string.search_nothing_found));
        RecyclerView recyclerView2 = this.suggestionsRecycler;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
            recyclerView2 = null;
        }
        recyclerView2.setVisibility(8);
        TextView textView3 = this.noResultsText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsText");
            textView3 = null;
        }
        textView3.setVisibility(0);
        TextView textView4 = this.noResultsQuery;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsQuery");
            textView4 = null;
        }
        textView4.setVisibility(0);
        EditText editText = this.customerSearchBar;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("customerSearchBar");
            editText = null;
        }
        editText.clearFocus();
        RecyclerView recyclerView3 = this.suggestionsRecycler;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("suggestionsRecycler");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.requestFocus();
        hideKeyboard();
    }

    private final void resetNoResultsFound() {
        TextView textView = this.noResultsQuery;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsQuery");
            textView = null;
        }
        textView.setText("");
        TextView textView3 = this.noResultsText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsText");
            textView3 = null;
        }
        textView3.setText("");
        TextView textView4 = this.noResultsText;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsText");
            textView4 = null;
        }
        textView4.setVisibility(8);
        TextView textView5 = this.noResultsQuery;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noResultsQuery");
        } else {
            textView2 = textView5;
        }
        textView2.setVisibility(8);
    }
}
