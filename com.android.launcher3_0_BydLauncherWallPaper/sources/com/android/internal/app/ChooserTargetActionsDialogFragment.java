package com.android.internal.app;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.ResolverListAdapter;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
public class ChooserTargetActionsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    protected List<DisplayResolveInfo> mTargetInfos;
    protected UserHandle mUserHandle;

    public ChooserTargetActionsDialogFragment() {
        this.mTargetInfos = new ArrayList();
    }

    public ChooserTargetActionsDialogFragment(List<DisplayResolveInfo> list, UserHandle userHandle) {
        this.mTargetInfos = new ArrayList();
        this.mUserHandle = userHandle;
        this.mTargetInfos = list;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Optional.of(getDialog()).map(new Function() { // from class: com.android.internal.app.-$$Lambda$0DQGzUFhULG4zmopXGvpkfSygMQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Dialog) obj).getWindow();
            }
        }).ifPresent(new Consumer() { // from class: com.android.internal.app.-$$Lambda$ChooserTargetActionsDialogFragment$0FcUMpuMcfMorDCpnBsi9Uxj2u4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Window) obj).setBackgroundDrawable(new ColorDrawable(0));
            }
        });
        List list = (List) this.mTargetInfos.stream().map(new Function() { // from class: com.android.internal.app.-$$Lambda$ChooserTargetActionsDialogFragment$fuG8_srCV8gPIPUAMIiED_j_LG4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onCreateView$1$ChooserTargetActionsDialogFragment((DisplayResolveInfo) obj);
            }
        }).collect(Collectors.toList());
        View viewInflate = layoutInflater.inflate(R.layout.chooser_dialog, viewGroup, false);
        TextView textView = (TextView) viewInflate.findViewById(16908310);
        ImageView imageView = (ImageView) viewInflate.findViewById(16908294);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.listContainer);
        ResolverListAdapter.ResolveInfoPresentationGetter providingAppPresentationGetter = getProvidingAppPresentationGetter();
        textView.setText(providingAppPresentationGetter.getLabel());
        imageView.setImageDrawable(providingAppPresentationGetter.getIcon(this.mUserHandle));
        recyclerView.setAdapter(new VHAdapter(list));
        return viewInflate;
    }

    public /* synthetic */ Pair lambda$onCreateView$1$ChooserTargetActionsDialogFragment(DisplayResolveInfo displayResolveInfo) {
        return new Pair(getItemIcon(displayResolveInfo), getItemLabel(displayResolveInfo));
    }

    class VHAdapter extends RecyclerView.Adapter<VH> {
        List<Pair<Drawable, CharSequence>> mItems;

        VHAdapter(List<Pair<Drawable, CharSequence>> list) {
            this.mItems = list;
        }

        @Override // com.android.internal.widget.RecyclerView.Adapter
        public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
            return ChooserTargetActionsDialogFragment.this.new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chooser_dialog_item, viewGroup, false));
        }

        @Override // com.android.internal.widget.RecyclerView.Adapter
        public void onBindViewHolder(VH vh, int i) {
            vh.bind(this.mItems.get(i), i);
        }

        @Override // com.android.internal.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView mIcon;
        TextView mLabel;

        VH(View view) {
            super(view);
            this.mLabel = (TextView) view.findViewById(R.id.text);
            this.mIcon = (ImageView) view.findViewById(16908294);
        }

        public void bind(Pair<Drawable, CharSequence> pair, final int i) {
            this.mLabel.setText(pair.second);
            if (pair.first == null) {
                this.mIcon.setVisibility(8);
            } else {
                this.mIcon.setVisibility(0);
                this.mIcon.setImageDrawable(pair.first);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.internal.app.-$$Lambda$ChooserTargetActionsDialogFragment$VH$w_E7x5Z7Qrjx7GxRT4NWXkpsuNI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bind$0$ChooserTargetActionsDialogFragment$VH(i, view);
                }
            });
        }

        public /* synthetic */ void lambda$bind$0$ChooserTargetActionsDialogFragment$VH(int i, View view) {
            ChooserTargetActionsDialogFragment chooserTargetActionsDialogFragment = ChooserTargetActionsDialogFragment.this;
            chooserTargetActionsDialogFragment.onClick(chooserTargetActionsDialogFragment.getDialog(), i);
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        pinComponent(this.mTargetInfos.get(i).getResolvedComponentName());
        ((ChooserActivity) getActivity()).handlePackagesChanged();
        dismiss();
    }

    private void pinComponent(ComponentName componentName) {
        SharedPreferences pinnedSharedPrefs = ChooserActivity.getPinnedSharedPrefs(getContext());
        String strFlattenToString = componentName.flattenToString();
        if (pinnedSharedPrefs.getBoolean(componentName.flattenToString(), false)) {
            pinnedSharedPrefs.edit().remove(strFlattenToString).apply();
        } else {
            pinnedSharedPrefs.edit().putBoolean(strFlattenToString, true).apply();
        }
    }

    private Drawable getPinIcon(boolean z) {
        if (z) {
            return getContext().getDrawable(R.drawable.ic_close);
        }
        return getContext().getDrawable(R.drawable.ic_chooser_pin_dialog);
    }

    private CharSequence getPinLabel(boolean z, CharSequence charSequence) {
        return z ? getResources().getString(R.string.unpin_specific_target, charSequence) : getResources().getString(R.string.pin_specific_target, charSequence);
    }

    protected CharSequence getItemLabel(DisplayResolveInfo displayResolveInfo) {
        return getPinLabel(displayResolveInfo.isPinned(), displayResolveInfo.getResolveInfo().loadLabel(getContext().getPackageManager()));
    }

    protected Drawable getItemIcon(DisplayResolveInfo displayResolveInfo) {
        return getPinIcon(displayResolveInfo.isPinned());
    }

    private ResolverListAdapter.ResolveInfoPresentationGetter getProvidingAppPresentationGetter() {
        return new ResolverListAdapter.ResolveInfoPresentationGetter(getContext(), ((ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE)).getLauncherLargeIconDensity(), this.mTargetInfos.get(0).getResolveInfo());
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        dismiss();
    }
}
