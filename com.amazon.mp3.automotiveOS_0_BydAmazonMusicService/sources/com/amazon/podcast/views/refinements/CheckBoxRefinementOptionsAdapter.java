package com.amazon.podcast.views.refinements;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
final class CheckBoxRefinementOptionsAdapter extends RecyclerView.Adapter<CheckBoxRefinementOptionViewHolder> {
    private boolean isPopUp;
    private final OnOptionSelectedListener listener;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private CheckBoxRefinementOptionElement refinementOptionElement;
    private final Resources resources;
    private String selectedOption;

    public interface OnOptionSelectedListener {
        void onOptionSelected(final CheckBoxRefinementOptionItemElement item);
    }

    public CheckBoxRefinementOptionsAdapter(final Resources resources, final OnOptionSelectedListener listener) {
        this.resources = resources;
        this.listener = listener;
    }

    public void bind(final CheckBoxRefinementOptionElement refinementOptionElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final String selectedOption, final boolean isPopUp) {
        this.refinementOptionElement = refinementOptionElement;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerId = ownerId;
        this.selectedOption = selectedOption;
        this.isPopUp = isPopUp;
        notifyDataSetChanged();
    }

    public CheckBoxRefinementOptionItemElement getSelectedItem() {
        for (CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement : this.refinementOptionElement.getOptions()) {
            if (checkBoxRefinementOptionItemElement.getId().equals(this.selectedOption)) {
                return checkBoxRefinementOptionItemElement;
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public CheckBoxRefinementOptionViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        return new CheckBoxRefinementOptionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_refinement_option, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final CheckBoxRefinementOptionViewHolder viewHolder, final int position) {
        final CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement = this.refinementOptionElement.getOptions().get(position);
        String defaultState = this.refinementOptionElement.getDefaultState();
        final String id = checkBoxRefinementOptionItemElement.getId();
        viewHolder.bind(checkBoxRefinementOptionItemElement.getText(), this.isPopUp, id.equals(defaultState), id.equals(this.selectedOption));
        viewHolder.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckBoxRefinementOptionsAdapter.this.selectedOption = id;
                CheckBoxRefinementOptionsAdapter.this.methodsDispatcher.dispatch(CheckBoxRefinementOptionsAdapter.this.ownerId, checkBoxRefinementOptionItemElement.getOnItemSelected());
                CheckBoxRefinementOptionsAdapter.this.listener.onOptionSelected(checkBoxRefinementOptionItemElement);
                CheckBoxRefinementOptionsAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.refinementOptionElement.getOptions().size();
    }
}
