package com.amazon.music.voice.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.voice.ui.ControllerTransition;
import com.amazon.music.voice.ui.transitions.InstantTransition;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class Router {
    private static final String EXTRA_BACKSTACK;
    private static final String EXTRA_PREFIX;
    private static final Logger LOG;
    private final Deque<ControllerTransaction> backstack;
    private final Component component;
    private ViewGroup container;
    private Context context;
    private final List<OnTransactionListener> popTransitionListeners;

    public interface OnTransactionListener {
        void onAfter(ControllerTransaction controllerTransaction);

        void onAfterTransition(ControllerTransaction controllerTransaction);

        void onBefore(ControllerTransaction controllerTransaction);
    }

    static {
        String str = Router.class.getName() + '.';
        EXTRA_PREFIX = str;
        EXTRA_BACKSTACK = str + "backstack";
        LOG = LoggerFactory.getLogger(Router.class.getSimpleName());
    }

    public Router(@NonNull Context context, @Nullable Component component) {
        this(context, component, null);
    }

    public Router(@NonNull Context context, @Nullable Component component, @Nullable Bundle bundle) {
        this.context = context;
        this.component = component;
        this.backstack = new ArrayDeque();
        this.popTransitionListeners = new ArrayList();
        restoreInstanceState(bundle);
    }

    @Nullable
    public Component getComponent() {
        return this.component;
    }

    public void attach(@NonNull ViewGroup viewGroup) {
        ViewGroup viewGroup2 = this.container;
        if (viewGroup2 == viewGroup) {
            LOG.warn("The container is the same. Please check the container parameter.");
            return;
        }
        if (viewGroup2 != null) {
            LOG.error("Router is already attached to a different container. Please close that first before proceeding.");
            return;
        }
        this.container = viewGroup;
        ControllerTransaction controllerTransactionPeek = this.backstack.peek();
        if (controllerTransactionPeek == null) {
            return;
        }
        ViewController controller = controllerTransactionPeek.getController();
        ControllerTransition pushTransition = controllerTransactionPeek.getPushTransition();
        if (pushTransition == null) {
            pushTransition = new InstantTransition();
        }
        controller.create(this);
        controller.attach(viewGroup);
        pushTransition.performTransition(viewGroup, null, controller.getView(), ControllerTransition.CompletionListener.NONE);
        pushTransition.completeTransition();
    }

    public void detach() {
        if (this.container == null) {
            return;
        }
        Iterator<ControllerTransaction> itDescendingIterator = this.backstack.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            itDescendingIterator.next().getController().destroyView();
        }
        this.container = null;
    }

    public void destroy() {
        detach();
        Iterator<ControllerTransaction> itDescendingIterator = this.backstack.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            itDescendingIterator.next().getController().destroy();
        }
    }

    public void saveInstanceState(@NonNull Bundle bundle) {
        bundle.putParcelableArrayList(EXTRA_BACKSTACK, new ArrayList<>(this.backstack));
    }

    public void restoreInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (this.container != null) {
            LOG.error("Router attempting to restore instance state when attached. Calling detach(). Please make sure to call detach() before calling restoreInstanceState()");
            detach();
        }
        if (!this.backstack.isEmpty()) {
            LOG.error("Router cannot restore instance state unless backstack is empty. Emptying backstack");
            this.backstack.clear();
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(EXTRA_BACKSTACK);
        if (parcelableArrayList == null) {
            return;
        }
        this.backstack.addAll(parcelableArrayList);
    }

    public int getBackStackSize() {
        return this.backstack.size();
    }

    public void pushController(@NonNull ControllerTransaction controllerTransaction) {
        pushTransaction(controllerTransaction, false);
    }

    private void pushTransaction(ControllerTransaction controllerTransaction, final boolean z) {
        if (this.container == null) {
            LOG.error("Router is not attached to a layout. Please call attach() before pushing a transaction.");
            return;
        }
        ViewController controller = controllerTransaction.getController();
        if (controller.isCreated()) {
            LOG.error("Controller is already in use. Exiting pushTransaction method.");
            return;
        }
        ControllerTransition pushTransition = controllerTransaction.getPushTransition();
        if (pushTransition == null) {
            pushTransition = new InstantTransition();
        }
        final ControllerTransaction controllerTransactionPeek = this.backstack.peek();
        View view = null;
        if (controllerTransactionPeek != null) {
            view = controllerTransactionPeek.getController().getView();
            if (z) {
                notifyOnBeforeTransaction(this.popTransitionListeners, controllerTransactionPeek);
                this.backstack.remove(controllerTransactionPeek);
                notifyOnAfterTransaction(this.popTransitionListeners, controllerTransactionPeek);
            }
        }
        this.backstack.push(controllerTransaction);
        controller.create(this);
        controller.attach(this.container);
        pushTransition.performTransition(this.container, view, controller.getView(), new ControllerTransition.CompletionListener() { // from class: com.amazon.music.voice.ui.Router.1
            @Override // com.amazon.music.voice.ui.ControllerTransition.CompletionListener
            public void completeTransition() {
                ControllerTransaction controllerTransaction2 = controllerTransactionPeek;
                if (controllerTransaction2 != null) {
                    if (z) {
                        controllerTransaction2.getController().destroy();
                        Router router = Router.this;
                        router.notifyOnAfterTransitionTransaction(router.popTransitionListeners, controllerTransactionPeek);
                    } else {
                        if (controllerTransaction2.getController().keepViewWhenInBackStack()) {
                            return;
                        }
                        controllerTransactionPeek.getController().destroyView();
                    }
                }
            }
        });
    }

    public boolean popCurrentController() {
        ControllerTransaction controllerTransactionPeek = this.backstack.peek();
        if (controllerTransactionPeek == null) {
            return false;
        }
        notifyOnBeforeTransaction(this.popTransitionListeners, controllerTransactionPeek);
        this.backstack.remove(controllerTransactionPeek);
        notifyOnAfterTransaction(this.popTransitionListeners, controllerTransactionPeek);
        popTransaction(controllerTransactionPeek, null);
        return true;
    }

    @Nullable
    public ViewController getCurrentController() {
        ControllerTransaction controllerTransactionPeek = this.backstack.peek();
        if (controllerTransactionPeek == null) {
            return null;
        }
        return controllerTransactionPeek.getController();
    }

    private void popTransaction(final ControllerTransaction controllerTransaction, ControllerTransition controllerTransition) {
        final ViewController controller = controllerTransaction.getController();
        if (this.container == null) {
            controller.destroy();
            notifyOnAfterTransitionTransaction(this.popTransitionListeners, controllerTransaction);
            return;
        }
        if (controllerTransition == null) {
            controllerTransition = controllerTransaction.getPopTransition();
        }
        if (controllerTransition == null) {
            controllerTransition = new InstantTransition();
        }
        ControllerTransaction controllerTransactionPeek = this.backstack.peek();
        View view = null;
        if (controllerTransactionPeek != null) {
            ViewController controller2 = controllerTransactionPeek.getController();
            if (!controller2.isAttached()) {
                controller2.create(this);
                ViewGroup viewGroup = this.container;
                controller2.attach(viewGroup, viewGroup.indexOfChild(controller.getView()));
                ControllerTransition pushTransition = controllerTransactionPeek.getPushTransition();
                if (pushTransition == null) {
                    pushTransition = new InstantTransition();
                }
                pushTransition.performTransition(this.container, null, controller.getView(), null);
                pushTransition.completeTransition();
            }
            view = controller2.getView();
        }
        controllerTransition.performTransition(this.container, controller.getView(), view, new ControllerTransition.CompletionListener() { // from class: com.amazon.music.voice.ui.Router.2
            @Override // com.amazon.music.voice.ui.ControllerTransition.CompletionListener
            public void completeTransition() {
                controller.destroy();
                Router router = Router.this;
                router.notifyOnAfterTransitionTransaction(router.popTransitionListeners, controllerTransaction);
            }
        });
    }

    private void notifyOnBeforeTransaction(List<OnTransactionListener> list, ControllerTransaction controllerTransaction) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).onBefore(controllerTransaction);
        }
    }

    private void notifyOnAfterTransaction(List<OnTransactionListener> list, ControllerTransaction controllerTransaction) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).onAfter(controllerTransaction);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOnAfterTransitionTransaction(List<OnTransactionListener> list, ControllerTransaction controllerTransaction) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).onAfterTransition(controllerTransaction);
        }
    }
}
