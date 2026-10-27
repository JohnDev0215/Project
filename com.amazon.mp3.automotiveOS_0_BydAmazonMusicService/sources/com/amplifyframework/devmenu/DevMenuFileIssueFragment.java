package com.amplifyframework.devmenu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.amplifyframework.core.R;

/* JADX INFO: loaded from: classes5.dex */
public final class DevMenuFileIssueFragment extends Fragment {
    private static final String DESCRIPTION_LENGTH_ERROR = "Issue description must be at least 20 characters.";
    private static final int MIN_DESCRIPTION_LENGTH = 20;
    private static final String NEW_ISSUE_URL = "https://github.com/aws-amplify/amplify-android/issues/new";
    private DeveloperMenu developerMenu;
    private View fileIssueView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.fileIssueView = layoutInflater.inflate(R.layout.dev_menu_fragment_file_issue, viewGroup, false);
        final Context applicationContext = requireContext().getApplicationContext();
        this.developerMenu = DeveloperMenu.singletonInstance(applicationContext);
        this.fileIssueView.findViewById(R.id.file_issue).setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuFileIssueFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0(view);
            }
        });
        this.fileIssueView.findViewById(R.id.copy_issue).setOnClickListener(new View.OnClickListener() { // from class: com.amplifyframework.devmenu.DevMenuFileIssueFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$1(applicationContext, view);
            }
        });
        return this.fileIssueView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(View view) {
        fileIssue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$1(Context context, View view) {
        String issueBody = getIssueBody();
        if (issueBody.isEmpty()) {
            return;
        }
        this.developerMenu.copyToClipboard(issueBody);
        Toast.makeText(context, "Copied issue body to clipboard.", 0).show();
    }

    private String getIssueBody() {
        EditText editText = (EditText) this.fileIssueView.findViewById(R.id.issue_description);
        String string = editText.getText().toString();
        if (string.length() < 20) {
            editText.setError(DESCRIPTION_LENGTH_ERROR);
            return "";
        }
        return this.developerMenu.createIssueBody(string, false);
    }

    private void fileIssue() {
        String issueBody = getIssueBody();
        if (issueBody.isEmpty()) {
            return;
        }
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(NEW_ISSUE_URL).buildUpon().appendQueryParameter("title", "").appendQueryParameter("body", issueBody).build()));
    }
}
