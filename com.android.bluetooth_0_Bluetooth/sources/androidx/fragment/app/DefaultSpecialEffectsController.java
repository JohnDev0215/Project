package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    private final HashMap<SpecialEffectsController.Operation, HashSet<CancellationSignal>> mRunningOperations;

    DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        this.mRunningOperations = new HashMap<>();
    }

    private void addCancellationSignal(SpecialEffectsController.Operation operation, CancellationSignal signal) {
        if (this.mRunningOperations.get(operation) == null) {
            this.mRunningOperations.put(operation, new HashSet<>());
        }
        this.mRunningOperations.get(operation).add(signal);
    }

    void removeCancellationSignal(SpecialEffectsController.Operation operation, CancellationSignal signal) {
        HashSet<CancellationSignal> signals = this.mRunningOperations.get(operation);
        if (signals != null && signals.remove(signal) && signals.isEmpty()) {
            this.mRunningOperations.remove(operation);
            operation.complete();
        }
    }

    void cancelAllSpecialEffects(SpecialEffectsController.Operation operation) {
        HashSet<CancellationSignal> signals = this.mRunningOperations.remove(operation);
        if (signals != null) {
            for (CancellationSignal signal : signals) {
                signal.cancel();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void executeOperations(java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r13, boolean r14) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            java.util.Iterator r2 = r13.iterator()
        L6:
            boolean r3 = r2.hasNext()
            r4 = 1
            if (r3 == 0) goto L31
            java.lang.Object r3 = r2.next()
            androidx.fragment.app.SpecialEffectsController$Operation r3 = (androidx.fragment.app.SpecialEffectsController.Operation) r3
            int[] r5 = androidx.fragment.app.DefaultSpecialEffectsController.AnonymousClass8.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type
            androidx.fragment.app.SpecialEffectsController$Operation$Type r6 = r3.getType()
            int r6 = r6.ordinal()
            r5 = r5[r6]
            if (r5 == r4) goto L2d
            r4 = 2
            if (r5 == r4) goto L2d
            r4 = 3
            if (r5 == r4) goto L2b
            r4 = 4
            if (r5 == r4) goto L2b
            goto L30
        L2b:
            r1 = r3
            goto L30
        L2d:
            if (r0 != 0) goto L30
            r0 = r3
        L30:
            goto L6
        L31:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r13)
            java.util.Iterator r6 = r13.iterator()
        L44:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L8e
            java.lang.Object r7 = r6.next()
            androidx.fragment.app.SpecialEffectsController$Operation r7 = (androidx.fragment.app.SpecialEffectsController.Operation) r7
            androidx.core.os.CancellationSignal r8 = new androidx.core.os.CancellationSignal
            r8.<init>()
            r12.addCancellationSignal(r7, r8)
            androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo r9 = new androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo
            r9.<init>(r7, r8)
            r2.add(r9)
            androidx.core.os.CancellationSignal r9 = new androidx.core.os.CancellationSignal
            r9.<init>()
            r12.addCancellationSignal(r7, r9)
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r10 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo
            r11 = 0
            if (r14 == 0) goto L70
            if (r7 != r0) goto L73
            goto L72
        L70:
            if (r7 != r1) goto L73
        L72:
            r11 = r4
        L73:
            r10.<init>(r7, r9, r14, r11)
            r3.add(r10)
            androidx.fragment.app.DefaultSpecialEffectsController$1 r10 = new androidx.fragment.app.DefaultSpecialEffectsController$1
            r10.<init>()
            r7.addCompletionListener(r10)
            androidx.core.os.CancellationSignal r10 = r7.getCancellationSignal()
            androidx.fragment.app.DefaultSpecialEffectsController$2 r11 = new androidx.fragment.app.DefaultSpecialEffectsController$2
            r11.<init>()
            r10.setOnCancelListener(r11)
            goto L44
        L8e:
            r12.startTransitions(r3, r14, r0, r1)
            java.util.Iterator r4 = r2.iterator()
        L95:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto Lad
            java.lang.Object r6 = r4.next()
            androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo r6 = (androidx.fragment.app.DefaultSpecialEffectsController.AnimationInfo) r6
            androidx.fragment.app.SpecialEffectsController$Operation r7 = r6.getOperation()
            androidx.core.os.CancellationSignal r8 = r6.getSignal()
            r12.startAnimation(r7, r8)
            goto L95
        Lad:
            java.util.Iterator r4 = r5.iterator()
        Lb1:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto Lc1
            java.lang.Object r6 = r4.next()
            androidx.fragment.app.SpecialEffectsController$Operation r6 = (androidx.fragment.app.SpecialEffectsController.Operation) r6
            r12.applyContainerChanges(r6)
            goto Lb1
        Lc1:
            r5.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    /* JADX INFO: renamed from: androidx.fragment.app.DefaultSpecialEffectsController$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.Type.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type = iArr;
            try {
                iArr[SpecialEffectsController.Operation.Type.HIDE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[SpecialEffectsController.Operation.Type.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[SpecialEffectsController.Operation.Type.SHOW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[SpecialEffectsController.Operation.Type.ADD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private void startAnimation(final SpecialEffectsController.Operation operation, final CancellationSignal signal) {
        Animation enterViewTransitionAnimation;
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        Fragment fragment = operation.getFragment();
        final View viewToAnimate = fragment.mView;
        FragmentAnim.AnimationOrAnimator anim = FragmentAnim.loadAnimation(context, fragment, operation.getType() == SpecialEffectsController.Operation.Type.ADD || operation.getType() == SpecialEffectsController.Operation.Type.SHOW);
        if (anim == null) {
            removeCancellationSignal(operation, signal);
            return;
        }
        container.startViewTransition(viewToAnimate);
        if (anim.animation != null) {
            if (operation.getType() == SpecialEffectsController.Operation.Type.ADD || operation.getType() == SpecialEffectsController.Operation.Type.SHOW) {
                enterViewTransitionAnimation = new FragmentAnim.EnterViewTransitionAnimation(anim.animation);
            } else {
                enterViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(anim.animation, container, viewToAnimate);
            }
            Animation animation = enterViewTransitionAnimation;
            animation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            container.endViewTransition(viewToAnimate);
                            DefaultSpecialEffectsController.this.removeCancellationSignal(operation, signal);
                        }
                    });
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }
            });
            viewToAnimate.startAnimation(animation);
        } else {
            anim.animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator anim2) {
                    container.endViewTransition(viewToAnimate);
                    DefaultSpecialEffectsController.this.removeCancellationSignal(operation, signal);
                }
            });
            anim.animator.setTarget(viewToAnimate);
            anim.animator.start();
        }
        signal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                viewToAnimate.clearAnimation();
            }
        });
    }

    private void startTransitions(List<TransitionInfo> transitionInfos, boolean isPop, SpecialEffectsController.Operation firstOut, SpecialEffectsController.Operation lastIn) {
        Iterator<TransitionInfo> it;
        ArrayMap<String, String> sharedElementNameMapping;
        Iterator<TransitionInfo> it2;
        ArrayList<String> exitingNames;
        ArrayList<String> enteringNames;
        SpecialEffectsController.Operation operation = firstOut;
        SpecialEffectsController.Operation operation2 = lastIn;
        FragmentTransitionImpl transitionImpl = null;
        for (TransitionInfo transitionInfo : transitionInfos) {
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (transitionImpl == null) {
                transitionImpl = handlingImpl;
            } else if (handlingImpl != null && transitionImpl != handlingImpl) {
                throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
            }
        }
        if (transitionImpl == null) {
            for (TransitionInfo transitionInfo2 : transitionInfos) {
                removeCancellationSignal(transitionInfo2.getOperation(), transitionInfo2.getSignal());
            }
            return;
        }
        View nonExistentView = new View(getContainer().getContext());
        final Rect lastInEpicenterRect = new Rect();
        ArrayList<View> sharedElementFirstOutViews = new ArrayList<>();
        ArrayList<View> sharedElementLastInViews = new ArrayList<>();
        ArrayMap<String, String> sharedElementNameMapping2 = new ArrayMap<>();
        Iterator<TransitionInfo> it3 = transitionInfos.iterator();
        Object sharedElementTransition = null;
        View firstOutEpicenterView = null;
        boolean hasLastInEpicenter = false;
        while (it3.hasNext()) {
            TransitionInfo transitionInfo3 = it3.next();
            boolean hasSharedElementTransition = transitionInfo3.hasSharedElementTransition();
            if (!hasSharedElementTransition || operation == null || operation2 == null) {
                it2 = it3;
            } else {
                Object sharedElementTransition2 = transitionImpl.wrapTransitionInSet(transitionImpl.cloneTransition(transitionInfo3.getSharedElementTransition()));
                Fragment sharedElementFragment = transitionInfo3.getOperation().getFragment();
                if (!isPop) {
                    ArrayList<String> exitingNames2 = sharedElementFragment.getSharedElementSourceNames();
                    exitingNames = exitingNames2;
                    enteringNames = sharedElementFragment.getSharedElementTargetNames();
                } else {
                    ArrayList<String> exitingNames3 = sharedElementFragment.getSharedElementTargetNames();
                    exitingNames = exitingNames3;
                    enteringNames = sharedElementFragment.getSharedElementSourceNames();
                }
                int numSharedElements = exitingNames.size();
                int i = 0;
                while (i < numSharedElements) {
                    int numSharedElements2 = numSharedElements;
                    ArrayList<String> exitingNames4 = exitingNames;
                    Iterator<TransitionInfo> it4 = it3;
                    String exitingName = exitingNames4.get(i);
                    Fragment sharedElementFragment2 = sharedElementFragment;
                    String enteringName = enteringNames.get(i);
                    sharedElementNameMapping2.put(exitingName, enteringName);
                    i++;
                    exitingNames = exitingNames4;
                    numSharedElements = numSharedElements2;
                    it3 = it4;
                    sharedElementFragment = sharedElementFragment2;
                }
                it2 = it3;
                ArrayList<String> exitingNames5 = exitingNames;
                ArrayMap<String, View> firstOutViews = new ArrayMap<>();
                findNamedViews(firstOutViews, firstOut.getFragment().mView);
                firstOutViews.retainAll(exitingNames5);
                sharedElementNameMapping2.retainAll(firstOutViews.keySet());
                ArrayMap<String, View> lastInViews = new ArrayMap<>();
                findNamedViews(lastInViews, lastIn.getFragment().mView);
                lastInViews.retainAll(enteringNames);
                FragmentTransition.retainValues(sharedElementNameMapping2, lastInViews);
                firstOutViews.retainAll(sharedElementNameMapping2.keySet());
                lastInViews.retainAll(sharedElementNameMapping2.values());
                if (sharedElementNameMapping2.isEmpty()) {
                    sharedElementFirstOutViews.clear();
                    sharedElementLastInViews.clear();
                    sharedElementTransition = null;
                } else {
                    Iterator<View> it5 = firstOutViews.values().iterator();
                    while (it5.hasNext()) {
                        Iterator<View> it6 = it5;
                        View sharedElementView = it5.next();
                        captureTransitioningViews(sharedElementFirstOutViews, sharedElementView);
                        it5 = it6;
                    }
                    if (!exitingNames5.isEmpty()) {
                        String epicenterViewName = exitingNames5.get(0);
                        View firstOutEpicenterView2 = firstOutViews.get(epicenterViewName);
                        firstOutEpicenterView = firstOutEpicenterView2;
                        transitionImpl.setEpicenter(sharedElementTransition2, firstOutEpicenterView);
                    }
                    for (View sharedElementView2 : lastInViews.values()) {
                        ArrayList<String> exitingNames6 = exitingNames5;
                        captureTransitioningViews(sharedElementLastInViews, sharedElementView2);
                        exitingNames5 = exitingNames6;
                    }
                    if (!enteringNames.isEmpty()) {
                        String epicenterViewName2 = enteringNames.get(0);
                        final View lastInEpicenterView = lastInViews.get(epicenterViewName2);
                        if (lastInEpicenterView != null) {
                            hasLastInEpicenter = true;
                            final FragmentTransitionImpl impl = transitionImpl;
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                                @Override // java.lang.Runnable
                                public void run() {
                                    impl.getBoundsOnScreen(lastInEpicenterView, lastInEpicenterRect);
                                }
                            });
                        }
                    }
                    transitionImpl.addTargets(sharedElementTransition2, sharedElementFirstOutViews);
                    sharedElementTransition = sharedElementTransition2;
                }
            }
            it3 = it2;
        }
        ArrayList<View> enteringViews = new ArrayList<>();
        Object mergedTransition = null;
        Object mergedNonOverlappingTransition = null;
        Iterator<TransitionInfo> it7 = transitionInfos.iterator();
        while (it7.hasNext()) {
            TransitionInfo transitionInfo4 = it7.next();
            Object transition = transitionImpl.cloneTransition(transitionInfo4.getTransition());
            Iterator<TransitionInfo> it8 = it7;
            SpecialEffectsController.Operation operation3 = transitionInfo4.getOperation();
            boolean involvedInSharedElementTransition = sharedElementTransition != null && (operation3 == operation || operation3 == operation2);
            if (transition == null) {
                if (involvedInSharedElementTransition) {
                    sharedElementNameMapping = sharedElementNameMapping2;
                } else {
                    sharedElementNameMapping = sharedElementNameMapping2;
                    removeCancellationSignal(transitionInfo4.getOperation(), transitionInfo4.getSignal());
                }
            } else {
                sharedElementNameMapping = sharedElementNameMapping2;
                ArrayList<View> transitioningViews = new ArrayList<>();
                captureTransitioningViews(transitioningViews, transitionInfo4.getOperation().getFragment().mView);
                if (involvedInSharedElementTransition) {
                    if (operation3 == operation) {
                        transitioningViews.removeAll(sharedElementFirstOutViews);
                    } else {
                        transitioningViews.removeAll(sharedElementLastInViews);
                    }
                }
                if (transitioningViews.isEmpty()) {
                    transitionImpl.addTarget(transition, nonExistentView);
                } else {
                    transitionImpl.addTargets(transition, transitioningViews);
                }
                if (transitionInfo4.getOperation().getType().equals(SpecialEffectsController.Operation.Type.ADD)) {
                    enteringViews.addAll(transitioningViews);
                    if (hasLastInEpicenter) {
                        transitionImpl.setEpicenter(transition, lastInEpicenterRect);
                    }
                } else {
                    transitionImpl.setEpicenter(transition, firstOutEpicenterView);
                }
                if (transitionInfo4.isOverlapAllowed()) {
                    mergedTransition = transitionImpl.mergeTransitionsTogether(mergedTransition, transition, null);
                } else {
                    Object mergedTransition2 = transitionImpl.mergeTransitionsTogether(mergedNonOverlappingTransition, transition, null);
                    mergedNonOverlappingTransition = mergedTransition2;
                }
            }
            operation = firstOut;
            operation2 = lastIn;
            it7 = it8;
            sharedElementNameMapping2 = sharedElementNameMapping;
        }
        ArrayMap<String, String> sharedElementNameMapping3 = sharedElementNameMapping2;
        Object mergedTransition3 = transitionImpl.mergeTransitionsInSequence(mergedTransition, mergedNonOverlappingTransition, sharedElementTransition);
        Iterator<TransitionInfo> it9 = transitionInfos.iterator();
        while (it9.hasNext()) {
            final TransitionInfo transitionInfo5 = it9.next();
            if (transitionInfo5.getTransition() == null) {
                it = it9;
            } else {
                it = it9;
                transitionImpl.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), mergedTransition3, transitionInfo5.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultSpecialEffectsController.this.removeCancellationSignal(transitionInfo5.getOperation(), transitionInfo5.getSignal());
                    }
                });
            }
            it9 = it;
        }
        FragmentTransition.setViewVisibility(enteringViews, 4);
        ArrayList<String> inNames = transitionImpl.prepareSetNameOverridesReordered(sharedElementLastInViews);
        transitionImpl.beginDelayedTransition(getContainer(), mergedTransition3);
        transitionImpl.setNameOverridesReordered(getContainer(), sharedElementFirstOutViews, sharedElementLastInViews, inNames, sharedElementNameMapping3);
        FragmentTransition.setViewVisibility(enteringViews, 0);
        transitionImpl.swapSharedElementTargets(sharedElementTransition, sharedElementFirstOutViews, sharedElementLastInViews);
    }

    void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                transitioningViews.add(viewGroup);
                return;
            }
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    captureTransitioningViews(transitioningViews, child);
                }
            }
            return;
        }
        transitioningViews.add(view);
    }

    void findNamedViews(Map<String, View> namedViews, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            namedViews.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    findNamedViews(namedViews, child);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        int i = AnonymousClass8.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$Type[operation.getType().ordinal()];
        if (i == 1) {
            view.setVisibility(8);
            return;
        }
        if (i == 2) {
            getContainer().removeView(view);
        } else if (i == 3 || i == 4) {
            view.setVisibility(0);
        }
    }

    private static class AnimationInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal signal) {
            this.mOperation = operation;
            this.mSignal = signal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }
    }

    private static class TransitionInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final CancellationSignal mSignal;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean isPop, boolean providesSharedElementTransition) {
            Object enterTransition;
            boolean allowReturnTransitionOverlap;
            Object exitTransition;
            this.mOperation = operation;
            this.mSignal = signal;
            if (operation.getType() == SpecialEffectsController.Operation.Type.ADD || operation.getType() == SpecialEffectsController.Operation.Type.SHOW) {
                if (isPop) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (isPop) {
                    allowReturnTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                } else {
                    allowReturnTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                }
                this.mOverlapAllowed = allowReturnTransitionOverlap;
            } else {
                if (isPop) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (providesSharedElementTransition) {
                if (isPop) {
                    this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
                    return;
                } else {
                    this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
                    return;
                }
            }
            this.mSharedElementTransition = null;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl transitionImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl sharedElementTransitionImpl = getHandlingImpl(this.mSharedElementTransition);
            if (transitionImpl == null || sharedElementTransitionImpl == null || transitionImpl == sharedElementTransitionImpl) {
                return transitionImpl != null ? transitionImpl : sharedElementTransitionImpl;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.mOperation.getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object transition) {
            if (transition == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(transition)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(transition)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + transition + " for fragment " + this.mOperation.getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
