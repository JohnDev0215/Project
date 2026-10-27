package com.engine.tbox.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.item.TBoxConnection;
import com.engine.tbox.state.State;
import com.engine.tbox.state.StateMachine;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class AuthStateMachine extends StateMachine {
    private static final int MSG_AUTH_FAILED = 6;
    private static final int MSG_AUTH_SUCCESS = 7;
    private static final int MSG_DATA_DECRYPT_FAILED = 11;
    private static final int MSG_HELLO_ERROR = 9;
    private static final int MSG_HELLO_RESULT = 5;
    private static final int MSG_RESET = 10;
    private static final int MSG_RSA_PUBLIC_KEY = 3;
    private static final int MSG_RSA_PUBLIC_KEY_ERROR = 8;
    private static final int MSG_START_AUTH = 1;
    private static final int MSG_WAIT_FOR_RSA_KEY_TIMEOUT = 2;
    private static final int MSG_WAIT_HELLO_TIMEOUT = 4;
    private static final String TAG = "AuthStateMachine";
    private static final int TIMEOUT = 120000;
    private boolean mAuth;
    private StartAuthState mAuthState;
    private AuthResultCallback mCallback;
    private TBoxConnection mConn;
    private FailedState mFailedState;
    private IdleState mIdleState;
    private SuccessState mSuccessState;
    private WaitForHelloState mWaitForHelloState;

    public interface AuthResultCallback {
        void authFailed();

        void authSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String msgToString(int msg) {
        switch (msg) {
            case 1:
                return "MSG_START_AUTH";
            case 2:
                return "MSG_WAIT_FOR_RSA_KEY_TIMEOUT";
            case 3:
                return "MSG_RSA_PUBLIC_KEY";
            case 4:
                return "MSG_WAIT_HELLO_TIMEOUT";
            case 5:
                return "MSG_HELLO_RESULT";
            case 6:
                return "MSG_AUTH_FAILED";
            case 7:
                return "MSG_AUTH_SUCCESS";
            case 8:
                return "MSG_RSA_PUBLIC_KEY_ERROR";
            case 9:
                return "MSG_HELLO_ERROR";
            case 10:
                return "MSG_RESET";
            case 11:
                return "MSG_DATA_DECRYPT_FAILED";
            default:
                return "MSG_AUTH_UNKNOWN";
        }
    }

    public AuthStateMachine(String name) {
        super(name);
        this.mIdleState = new IdleState();
        this.mAuthState = new StartAuthState();
        this.mWaitForHelloState = new WaitForHelloState();
        this.mFailedState = new FailedState();
        this.mSuccessState = new SuccessState();
        this.mAuth = false;
        initAuthStateMachine();
    }

    protected AuthStateMachine(String name, Looper looper) {
        super(name, looper);
        this.mIdleState = new IdleState();
        this.mAuthState = new StartAuthState();
        this.mWaitForHelloState = new WaitForHelloState();
        this.mFailedState = new FailedState();
        this.mSuccessState = new SuccessState();
        this.mAuth = false;
        initAuthStateMachine();
    }

    protected AuthStateMachine(String name, Handler handler) {
        super(name, handler);
        this.mIdleState = new IdleState();
        this.mAuthState = new StartAuthState();
        this.mWaitForHelloState = new WaitForHelloState();
        this.mFailedState = new FailedState();
        this.mSuccessState = new SuccessState();
        this.mAuth = false;
        initAuthStateMachine();
    }

    void initAuthStateMachine() {
        addState(this.mIdleState);
        addState(this.mAuthState, this.mIdleState);
        addState(this.mWaitForHelloState, this.mIdleState);
        addState(this.mFailedState, this.mIdleState);
        addState(this.mSuccessState, this.mIdleState);
        setInitialState(this.mIdleState);
        setDbg(true);
    }

    public void addTboxConnection(TBoxConnection conn) {
        this.mConn = conn;
    }

    public void startAuth() {
        LogUtils.i(TAG, getName() + " startAuth", new Object[0]);
        sendMessage(1);
    }

    public void helloAuthResult(boolean z) {
        LogUtils.i(TAG, getName() + " helloAuthResult:" + z, new Object[0]);
        sendMessage(5, !z ? 1 : 0);
    }

    public void sendRsaPublicKey(byte[] data) {
        LogUtils.i(TAG, getName() + " sendRsaPublicKey", new Object[0]);
        LogUtils.v(TAG, getName() + " sendRsaPublicKey:" + Arrays.toString(data), new Object[0]);
        sendMessage(3, data);
    }

    public void sendRsaPublicKeyError() {
        LogUtils.i(TAG, getName() + " sendRsaPublicKeyError", new Object[0]);
        sendMessage(8);
    }

    public void sendHelloError() {
        LogUtils.i(TAG, getName() + " sendHelloError", new Object[0]);
        sendMessage(9);
    }

    public void sendAuth() {
        LogUtils.i(TAG, getName() + " sendAuth:" + this.mAuth, new Object[0]);
        if (this.mAuth) {
            sendMessage(7);
        } else {
            sendMessage(6);
        }
    }

    public void resetAuth() {
        LogUtils.i(TAG, getName() + " resetAuth", new Object[0]);
        sendMessage(10);
    }

    public void sendMessageDescryptedFailed() {
        LogUtils.i(TAG, getName() + " sendMessageDescryptedFailed", new Object[0]);
        sendMessage(11);
    }

    public void registerAuthResultCallback(AuthResultCallback callback) {
        this.mCallback = callback;
    }

    public void unregisterAuthResultCallback() {
        this.mCallback = null;
    }

    class IdleState extends State {
        IdleState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " enter", new Object[0]);
            super.enter();
            AuthStateMachine.this.mAuth = false;
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " exit", new Object[0]);
            super.exit();
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(AuthStateMachine.TAG, getName() + " processMessage " + AuthStateMachine.msgToString(msg.what), new Object[0]);
            if (msg.what == 1) {
                AuthStateMachine.this.mConn.sendExchangeReq();
                AuthStateMachine authStateMachine = AuthStateMachine.this;
                authStateMachine.transitionTo(authStateMachine.mAuthState);
            }
            return true;
        }
    }

    class StartAuthState extends State {
        StartAuthState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " enter", new Object[0]);
            super.enter();
            AuthStateMachine.this.sendMessageDelayed(2, 120000L);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " exit", new Object[0]);
            super.exit();
            if (AuthStateMachine.this.hasMessages(2)) {
                AuthStateMachine.this.removeMessages(2);
            }
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(AuthStateMachine.TAG, getName() + " processMessage " + AuthStateMachine.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i == 2) {
                AuthStateMachine authStateMachine = AuthStateMachine.this;
                authStateMachine.transitionTo(authStateMachine.mFailedState);
            } else if (i == 3) {
                AuthStateMachine.this.removeMessages(2);
                byte[] data = (byte[]) msg.obj;
                if (!AuthStateMachine.this.mConn.sendAesKey(data)) {
                    AuthStateMachine.this.mConn.sendExchangeResult(false);
                    AuthStateMachine authStateMachine2 = AuthStateMachine.this;
                    authStateMachine2.transitionTo(authStateMachine2.mFailedState);
                } else {
                    AuthStateMachine authStateMachine3 = AuthStateMachine.this;
                    authStateMachine3.transitionTo(authStateMachine3.mWaitForHelloState);
                }
            } else if (i == 5) {
                AuthStateMachine.this.removeMessages(2);
                if (msg.arg1 == 1) {
                    AuthStateMachine authStateMachine4 = AuthStateMachine.this;
                    authStateMachine4.transitionTo(authStateMachine4.mFailedState);
                } else {
                    AuthStateMachine authStateMachine5 = AuthStateMachine.this;
                    authStateMachine5.transitionTo(authStateMachine5.mSuccessState);
                }
            } else if (i == 8) {
                AuthStateMachine authStateMachine6 = AuthStateMachine.this;
                authStateMachine6.transitionTo(authStateMachine6.mFailedState);
            } else if (i == 10) {
                AuthStateMachine authStateMachine7 = AuthStateMachine.this;
                authStateMachine7.transitionTo(authStateMachine7.mIdleState);
            }
            return true;
        }
    }

    class WaitForHelloState extends State {
        WaitForHelloState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " enter", new Object[0]);
            super.enter();
            AuthStateMachine.this.sendMessageDelayed(4, 120000L);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " exit", new Object[0]);
            super.exit();
            if (AuthStateMachine.this.hasMessages(4)) {
                AuthStateMachine.this.removeMessages(4);
            }
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(AuthStateMachine.TAG, getName() + " processMessage " + AuthStateMachine.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i == 4) {
                AuthStateMachine authStateMachine = AuthStateMachine.this;
                authStateMachine.transitionTo(authStateMachine.mFailedState);
            } else if (i == 5) {
                AuthStateMachine.this.removeMessages(4);
                if (msg.arg1 == 1) {
                    AuthStateMachine authStateMachine2 = AuthStateMachine.this;
                    authStateMachine2.transitionTo(authStateMachine2.mFailedState);
                } else {
                    AuthStateMachine authStateMachine3 = AuthStateMachine.this;
                    authStateMachine3.transitionTo(authStateMachine3.mSuccessState);
                }
            } else if (i == 10) {
                AuthStateMachine authStateMachine4 = AuthStateMachine.this;
                authStateMachine4.transitionTo(authStateMachine4.mIdleState);
            }
            return true;
        }
    }

    class FailedState extends State {
        FailedState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " enter", new Object[0]);
            super.enter();
            AuthStateMachine.this.sendMessage(6);
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " exit", new Object[0]);
            super.exit();
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(AuthStateMachine.TAG, getName() + " processMessage " + AuthStateMachine.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i == 6) {
                AuthStateMachine authStateMachine = AuthStateMachine.this;
                authStateMachine.deferMessage(authStateMachine.obtainMessage(1));
                if (AuthStateMachine.this.mCallback != null) {
                    AuthStateMachine.this.mCallback.authFailed();
                }
                AuthStateMachine authStateMachine2 = AuthStateMachine.this;
                authStateMachine2.transitionTo(authStateMachine2.mIdleState);
            } else if (i == 10) {
                AuthStateMachine authStateMachine3 = AuthStateMachine.this;
                authStateMachine3.transitionTo(authStateMachine3.mIdleState);
            }
            return true;
        }
    }

    class SuccessState extends State {
        SuccessState() {
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void enter() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " enter", new Object[0]);
            super.enter();
            AuthStateMachine.this.mAuth = true;
            if (AuthStateMachine.this.mCallback != null) {
                AuthStateMachine.this.mCallback.authSuccess();
            }
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public void exit() {
            LogUtils.d(AuthStateMachine.TAG, getName() + " exit", new Object[0]);
            super.exit();
        }

        @Override // com.engine.tbox.state.State, com.engine.tbox.state.IState
        public boolean processMessage(Message msg) {
            LogUtils.i(AuthStateMachine.TAG, getName() + " processMessage " + AuthStateMachine.msgToString(msg.what), new Object[0]);
            int i = msg.what;
            if (i == 10) {
                AuthStateMachine authStateMachine = AuthStateMachine.this;
                authStateMachine.transitionTo(authStateMachine.mIdleState);
            } else if (i == 11) {
                AuthStateMachine authStateMachine2 = AuthStateMachine.this;
                authStateMachine2.deferMessage(authStateMachine2.obtainMessage(1));
                AuthStateMachine authStateMachine3 = AuthStateMachine.this;
                authStateMachine3.transitionTo(authStateMachine3.mIdleState);
            } else {
                LogUtils.i(AuthStateMachine.TAG, getName() + " don't need to handle:" + AuthStateMachine.msgToString(msg.what), new Object[0]);
            }
            return true;
        }
    }
}
