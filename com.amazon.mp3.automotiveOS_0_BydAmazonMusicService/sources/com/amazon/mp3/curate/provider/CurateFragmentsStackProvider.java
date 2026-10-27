package com.amazon.mp3.curate.provider;

import androidx.fragment.app.FragmentManager;
import com.amazon.music.curate.provider.FragmentsStackProvider;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateFragmentsStackProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0016J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateFragmentsStackProvider;", "Lcom/amazon/music/curate/provider/FragmentsStackProvider;", "()V", "fragmentId", "", "Ljava/lang/Integer;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "listeners", "Ljava/util/HashSet;", "Lcom/amazon/music/curate/provider/FragmentsStackProvider$FragmentManagerListener;", "Lkotlin/collections/HashSet;", "addFragmentManagerListener", "", "fragmentManagerListener", "fragmentContainerId", "removeFragmentListener", "setFragmentId", "setFragmentManager", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateFragmentsStackProvider extends FragmentsStackProvider {
    private static Integer fragmentId;
    private static FragmentManager fragmentManager;
    public static final CurateFragmentsStackProvider INSTANCE = new CurateFragmentsStackProvider();
    private static HashSet<FragmentsStackProvider.FragmentManagerListener> listeners = new HashSet<>();

    private CurateFragmentsStackProvider() {
    }

    public final void setFragmentManager(FragmentManager fragmentManager2) {
        Intrinsics.checkNotNullParameter(fragmentManager2, "fragmentManager");
        fragmentManager = fragmentManager2;
        Iterator<FragmentsStackProvider.FragmentManagerListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().onFragmentManagerUpdated(fragmentManager2);
        }
    }

    public final void setFragmentId(int fragmentId2) {
        fragmentId = Integer.valueOf(fragmentId2);
    }

    @Override // com.amazon.music.curate.provider.FragmentsStackProvider
    public int fragmentContainerId() {
        Integer num = fragmentId;
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    @Override // com.amazon.music.curate.provider.FragmentsStackProvider
    public FragmentManager fragmentManager() {
        FragmentManager fragmentManager2 = fragmentManager;
        if (fragmentManager2 != null) {
            return fragmentManager2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fragmentManager");
        return null;
    }

    @Override // com.amazon.music.curate.provider.FragmentsStackProvider
    public void addFragmentManagerListener(FragmentsStackProvider.FragmentManagerListener fragmentManagerListener) {
        if (fragmentManagerListener == null) {
            return;
        }
        listeners.add(fragmentManagerListener);
    }

    @Override // com.amazon.music.curate.provider.FragmentsStackProvider
    public void removeFragmentListener(FragmentsStackProvider.FragmentManagerListener fragmentManagerListener) {
        listeners.remove(fragmentManagerListener);
    }
}
