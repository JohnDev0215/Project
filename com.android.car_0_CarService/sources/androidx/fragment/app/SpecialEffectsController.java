package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class SpecialEffectsController {
    private final ViewGroup mContainer;
    final ArrayList<Operation> mPendingOperations = new ArrayList<>();
    final HashMap<Fragment, Operation> mAwaitingCompletionOperations = new HashMap<>();
    boolean mOperationDirectionIsPop = false;
    boolean mIsContainerPostponed = false;

    abstract void executeOperations(List<Operation> list, boolean z);

    static SpecialEffectsController getOrCreateController(ViewGroup container, FragmentManager fragmentManager) {
        SpecialEffectsControllerFactory factory = fragmentManager.getSpecialEffectsControllerFactory();
        return getOrCreateController(container, factory);
    }

    static SpecialEffectsController getOrCreateController(ViewGroup container, SpecialEffectsControllerFactory factory) {
        Object controller = container.getTag(R.id.special_effects_controller_view_tag);
        if (controller instanceof SpecialEffectsController) {
            return (SpecialEffectsController) controller;
        }
        SpecialEffectsController newController = factory.createController(container);
        container.setTag(R.id.special_effects_controller_view_tag, newController);
        return newController;
    }

    SpecialEffectsController(ViewGroup container) {
        this.mContainer = container;
    }

    public ViewGroup getContainer() {
        return this.mContainer;
    }

    Operation.Type getAwaitingCompletionType(FragmentStateManager fragmentStateManager) {
        Operation operation = this.mAwaitingCompletionOperations.get(fragmentStateManager.getFragment());
        if (operation != null) {
            return operation.getType();
        }
        return null;
    }

    void enqueueAdd(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.Type.ADD, fragmentStateManager, cancellationSignal);
    }

    void enqueueShow(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.Type.SHOW, fragmentStateManager, cancellationSignal);
    }

    void enqueueHide(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.Type.HIDE, fragmentStateManager, cancellationSignal);
    }

    void enqueueRemove(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.Type.REMOVE, fragmentStateManager, cancellationSignal);
    }

    private void enqueue(Operation.Type type, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        if (cancellationSignal.isCanceled()) {
            return;
        }
        synchronized (this.mPendingOperations) {
            final CancellationSignal signal = new CancellationSignal();
            final FragmentStateManagerOperation operation = new FragmentStateManagerOperation(type, fragmentStateManager, signal);
            this.mPendingOperations.add(operation);
            this.mAwaitingCompletionOperations.put(operation.getFragment(), operation);
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    synchronized (SpecialEffectsController.this.mPendingOperations) {
                        SpecialEffectsController.this.mPendingOperations.remove(operation);
                        SpecialEffectsController.this.mAwaitingCompletionOperations.remove(operation.getFragment());
                        signal.cancel();
                    }
                }
            });
            operation.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.2
                @Override // java.lang.Runnable
                public void run() {
                    SpecialEffectsController.this.mAwaitingCompletionOperations.remove(operation.getFragment());
                }
            });
        }
    }

    void updateOperationDirection(boolean isPop) {
        this.mOperationDirectionIsPop = isPop;
    }

    void markPostponedState() {
        synchronized (this.mPendingOperations) {
            this.mIsContainerPostponed = false;
            for (int index = this.mPendingOperations.size() - 1; index >= 0; index--) {
                Operation operation = this.mPendingOperations.get(index);
                if (operation.getType() != Operation.Type.ADD && operation.getType() != Operation.Type.SHOW) {
                }
                Fragment fragment = operation.getFragment();
                this.mIsContainerPostponed = fragment.isPostponed();
                break;
            }
        }
    }

    void forcePostponedExecutePendingOperations() {
        if (this.mIsContainerPostponed) {
            this.mIsContainerPostponed = false;
            executePendingOperations();
        }
    }

    void executePendingOperations() {
        if (this.mIsContainerPostponed) {
            return;
        }
        synchronized (this.mPendingOperations) {
            executeOperations(new ArrayList(this.mPendingOperations), this.mOperationDirectionIsPop);
            this.mPendingOperations.clear();
            this.mOperationDirectionIsPop = false;
        }
    }

    void cancelAllOperations() {
        synchronized (this.mPendingOperations) {
            for (Operation operation : this.mAwaitingCompletionOperations.values()) {
                operation.getCancellationSignal().cancel();
            }
            this.mAwaitingCompletionOperations.clear();
            this.mPendingOperations.clear();
        }
    }

    static class Operation {
        private final CancellationSignal mCancellationSignal;
        private final List<Runnable> mCompletionListeners = new ArrayList();
        private final Fragment mFragment;
        private final Type mType;

        enum Type {
            ADD,
            REMOVE,
            SHOW,
            HIDE
        }

        Operation(Type type, Fragment fragment, CancellationSignal cancellationSignal) {
            this.mType = type;
            this.mFragment = fragment;
            this.mCancellationSignal = cancellationSignal;
        }

        public final Type getType() {
            return this.mType;
        }

        public final Fragment getFragment() {
            return this.mFragment;
        }

        public final CancellationSignal getCancellationSignal() {
            return this.mCancellationSignal;
        }

        final void addCompletionListener(Runnable listener) {
            this.mCompletionListeners.add(listener);
        }

        public void complete() {
            for (Runnable listener : this.mCompletionListeners) {
                listener.run();
            }
        }
    }

    private static class FragmentStateManagerOperation extends Operation {
        private final FragmentStateManager mFragmentStateManager;

        FragmentStateManagerOperation(Operation.Type type, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
            super(type, fragmentStateManager.getFragment(), cancellationSignal);
            this.mFragmentStateManager = fragmentStateManager;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.moveToExpectedState();
        }
    }
}
