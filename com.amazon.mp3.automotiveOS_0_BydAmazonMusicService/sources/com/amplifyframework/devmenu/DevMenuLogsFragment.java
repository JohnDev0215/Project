package com.amplifyframework.devmenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amplifyframework.core.R;
import com.amplifyframework.logging.LogLevel;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public final class DevMenuLogsFragment extends Fragment {
    private DeveloperMenu developerMenu;
    private TextView logsText;
    private String searchQuery;
    private MenuItem selectedLogsFilterItem;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.dev_menu_fragment_logs, viewGroup, false);
        this.developerMenu = DeveloperMenu.singletonInstance(getContext());
        TextView textView = (TextView) viewInflate.findViewById(R.id.logs_text);
        this.logsText = textView;
        textView.setText(this.developerMenu.getLogs());
        ((SearchView) viewInflate.findViewById(R.id.search_logs_field)).setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.amplifyframework.devmenu.DevMenuLogsFragment.1
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                DevMenuLogsFragment.this.searchQuery = str;
                DevMenuLogsFragment.this.displayFilteredLogs();
                return true;
            }
        });
        Button button = (Button) viewInflate.findViewById(R.id.filter_logs);
        registerForContextMenu(button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuLogsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0(view);
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(View view) {
        requireActivity().openContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.dev_menu_logs_menu, contextMenu);
        if (this.selectedLogsFilterItem == null) {
            this.selectedLogsFilterItem = contextMenu.findItem(R.id.all_logs);
        }
        contextMenu.findItem(this.selectedLogsFilterItem.getItemId()).setChecked(true);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(@NonNull MenuItem menuItem) {
        this.selectedLogsFilterItem = menuItem;
        displayFilteredLogs();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayFilteredLogs() {
        this.logsText.setText(R.string.placeholder_logs);
        this.logsText.setText(this.developerMenu.getFilteredLogs(this.searchQuery, this.selectedLogsFilterItem.getItemId() != R.id.all_logs ? LogLevel.valueOf(this.selectedLogsFilterItem.getTitle().toString().toUpperCase(Locale.US)) : null));
    }
}
