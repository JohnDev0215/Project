package com.neusoft.optimus.utility.thread;

import com.neusoft.optimus.utility.log.LogTool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public class ActionEngine {
    private static final int _count = (Runtime.getRuntime().availableProcessors() * 3) + 2;
    private static ActionEngine _sInstance = null;
    private static final String cTag = "ActionEngine";
    private ExecutorService _executorService = Executors.newFixedThreadPool(_count);
    private ActionThread _thread;

    public static abstract class ActionIntent<TParam> {
        private String _category;
        protected Future _future;
        private String _key;
        protected boolean _break = false;
        private IActionListener<TParam> _listener = null;

        public interface IActionListener<TParam> {
            boolean onAction(ActionIntent actionIntent, TParam tparam);

            void onActionBegin(ActionIntent actionIntent);

            void onActionEnd(ActionIntent actionIntent);
        }

        protected abstract void running(TParam tparam);

        public void setFuture(Future future) {
            this._future = future;
        }

        public ActionIntent(String str, String str2) {
            this._category = str;
            this._key = str2;
        }

        public String getCategory() {
            return this._category;
        }

        public String getKey() {
            return this._key;
        }

        public void doAction(IActionListener<TParam> iActionListener, TParam tparam) {
            this._listener = iActionListener;
            run(tparam);
        }

        private void run(TParam tparam) {
            IActionListener<TParam> iActionListener = this._listener;
            if (iActionListener != null) {
                iActionListener.onActionBegin(this);
            }
            running(tparam);
            IActionListener<TParam> iActionListener2 = this._listener;
            if (iActionListener2 != null) {
                iActionListener2.onActionEnd(this);
            }
        }

        public void cancel() {
            Future future = this._future;
            if (future != null) {
                future.cancel(true);
            }
            this._break = true;
        }

        public boolean isCancel() {
            return this._break;
        }

        protected boolean notify(TParam tparam) {
            IActionListener<TParam> iActionListener = this._listener;
            return iActionListener != null && iActionListener.onAction(this, tparam);
        }
    }

    static class RunnableProxy<TParam> implements Runnable {
        private ActionIntent<TParam> _action;
        private ActionIntent.IActionListener<TParam> _listener;
        private TParam _param;

        public RunnableProxy(ActionIntent<TParam> actionIntent, ActionIntent.IActionListener<TParam> iActionListener, TParam tparam) {
            this._action = actionIntent;
            this._listener = iActionListener;
            this._param = tparam;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionIntent<TParam> actionIntent = this._action;
            if (actionIntent != null) {
                actionIntent.doAction(this._listener, this._param);
            }
        }
    }

    private ActionEngine() {
        this._thread = null;
        this._thread = new ActionThread(cTag);
    }

    public static synchronized ActionEngine getInstance() {
        if (_sInstance == null) {
            _sInstance = new ActionEngine();
            LogTool.i(cTag, "线程池创建，线程大小%d", Integer.valueOf(_count));
        }
        return _sInstance;
    }

    public <TParam> void postOrderAction(ActionIntent<TParam> actionIntent, ActionIntent.IActionListener<TParam> iActionListener, TParam tparam) {
        this._thread.postAction(new RunnableProxy(actionIntent, iActionListener, tparam));
    }

    public <TParam> void postAction(ActionIntent<TParam> actionIntent, ActionIntent.IActionListener<TParam> iActionListener, TParam tparam) {
        actionIntent.setFuture(this._executorService.submit(new RunnableProxy(actionIntent, iActionListener, tparam)));
    }

    public void stopEngine() {
        this._thread.quit();
        this._executorService.shutdown();
    }
}
