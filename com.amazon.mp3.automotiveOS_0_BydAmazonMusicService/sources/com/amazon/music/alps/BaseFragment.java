package com.amazon.music.alps;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/alps/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "associateTag", "", "getAssociateTag", "()Ljava/lang/String;", "setAssociateTag", "(Ljava/lang/String;)V", "refMarker", "getRefMarker", "setRefMarker", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseFragment extends Fragment {
    protected String associateTag;
    protected String refMarker;

    protected final String getRefMarker() {
        String str = this.refMarker;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("refMarker");
        return null;
    }

    protected final void setRefMarker(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.refMarker = str;
    }

    protected final String getAssociateTag() {
        String str = this.associateTag;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("associateTag");
        return null;
    }

    protected final void setAssociateTag(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.associateTag = str;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("refMarker");
        if (string == null) {
            string = "";
        }
        setRefMarker(string);
        Bundle arguments2 = getArguments();
        String string2 = arguments2 != null ? arguments2.getString("associateTag") : null;
        setAssociateTag(string2 != null ? string2 : "");
    }
}
