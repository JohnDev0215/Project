package com.amazon.podcast;

import androidx.fragment.app.FragmentManager;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.podcast.bootstrap.FragmentManagerListener;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001f\u0010 J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0002J\u0017\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0012\u0010\u0011\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0012\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\n\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\u0015R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\u0015R\u001e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R&\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u001aj\b\u0012\u0004\u0012\u00020\u000f`\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR&\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u001aj\b\u0012\u0004\u0012\u00020\u000f`\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001d¨\u0006!"}, d2 = {"Lcom/amazon/podcast/FragmentInfo;", "Lcom/amazon/podcast/bootstrap/FragmentInfoProvider;", "Landroidx/fragment/app/FragmentManager;", "fragmentManager", "", "setFragmentManager", "", "fragmentId", "setFragmentId", "childFragmentManager", "setChildFragmentManager", "childFragmentId", "setChildFragmentId", "(Ljava/lang/Integer;)V", "fragmentContainerId", "Lcom/amazon/podcast/bootstrap/FragmentManagerListener;", "fragmentManagerListener", "addFragmentManagerListener", "removeFragmentListener", "childFragmentContainerId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "Landroidx/fragment/app/FragmentManager;", "Ljava/lang/ref/SoftReference;", "childFragmentManagerReference", "Ljava/lang/ref/SoftReference;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "listeners", "Ljava/util/HashSet;", "childFragmentListeners", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FragmentInfo extends FragmentInfoProvider {
    private static Integer childFragmentId;
    private static SoftReference<FragmentManager> childFragmentManagerReference;
    private static Integer fragmentId;
    private static FragmentManager fragmentManager;
    public static final FragmentInfo INSTANCE = new FragmentInfo();
    private static HashSet<FragmentManagerListener> listeners = new HashSet<>();
    private static HashSet<FragmentManagerListener> childFragmentListeners = new HashSet<>();

    private FragmentInfo() {
    }

    public final void setFragmentManager(FragmentManager fragmentManager2) {
        Intrinsics.checkNotNullParameter(fragmentManager2, "fragmentManager");
        fragmentManager = fragmentManager2;
        Iterator<FragmentManagerListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().onFragmentManagerUpdated(fragmentManager2);
        }
    }

    public final void setFragmentId(int fragmentId2) {
        fragmentId = Integer.valueOf(fragmentId2);
    }

    public final void setChildFragmentManager(FragmentManager childFragmentManager) {
        childFragmentManagerReference = new SoftReference<>(childFragmentManager);
        Iterator<FragmentManagerListener> it = childFragmentListeners.iterator();
        while (it.hasNext()) {
            it.next().onFragmentManagerUpdated(childFragmentManager);
        }
    }

    public final void setChildFragmentId(Integer childFragmentId2) {
        childFragmentId = childFragmentId2;
    }

    @Override // com.amazon.podcast.bootstrap.FragmentInfoProvider
    public int fragmentContainerId() {
        Integer num = fragmentId;
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    @Override // com.amazon.podcast.bootstrap.FragmentInfoProvider
    public FragmentManager fragmentManager() {
        FragmentManager fragmentManager2 = fragmentManager;
        Intrinsics.checkNotNull(fragmentManager2);
        return fragmentManager2;
    }

    @Override // com.amazon.podcast.bootstrap.FragmentInfoProvider
    public void addFragmentManagerListener(FragmentManagerListener fragmentManagerListener) {
        if (fragmentManagerListener == null) {
            return;
        }
        listeners.add(fragmentManagerListener);
    }

    @Override // com.amazon.podcast.bootstrap.FragmentInfoProvider
    public void removeFragmentListener(FragmentManagerListener fragmentManagerListener) {
        listeners.remove(fragmentManagerListener);
    }

    @Override // com.amazon.podcast.bootstrap.FragmentInfoProvider
    public Integer childFragmentContainerId() {
        return childFragmentId;
    }

    @Override // com.amazon.podcast.bootstrap.FragmentInfoProvider
    public FragmentManager childFragmentManager() {
        SoftReference<FragmentManager> softReference = childFragmentManagerReference;
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }
}
