package com.amazon.music.signin;

import androidx.annotation.NonNull;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
class RxSignInTaskExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(RxSignInTaskExecutor.class.getSimpleName());
    private Func1<Void, Void> executeTasks = new Func1<Void, Void>() { // from class: com.amazon.music.signin.RxSignInTaskExecutor.1
        @Override // rx.functions.Func1
        public Void call(Void r6) {
            try {
                for (int nextTaskPosition = RxSignInTaskExecutor.this.getNextTaskPosition(); nextTaskPosition < RxSignInTaskExecutor.this.signInTasks.size(); nextTaskPosition++) {
                    SignInTask signInTask = (SignInTask) RxSignInTaskExecutor.this.signInTasks.get(nextTaskPosition);
                    signInTask.execute();
                    RxSignInTaskExecutor.this.signInTaskCache.cacheLastExecutedTask(signInTask.getName());
                    SignInTaskId signInTaskId = new SignInTaskId(signInTask.getName(), nextTaskPosition);
                    RxSignInTaskExecutor.LOG.info("DMMSignIn executing task with ID {} {}", Integer.valueOf(signInTaskId.getTaskValue()), signInTaskId.getTaskName());
                    RxSignInTaskExecutor.this.rxSignInTaskIdStream.sendTaskId(signInTaskId);
                }
                return null;
            } catch (Exception e) {
                RxSignInTaskExecutor.this.rxSignInTaskIdStream.onError(e);
                return null;
            }
        }
    };
    private final RxSignInTaskIdStream rxSignInTaskIdStream;
    private final SignInTaskCache signInTaskCache;
    private final List<SignInTask> signInTasks;

    protected RxSignInTaskExecutor(@NonNull List<SignInTask> list, @NonNull RxSignInTaskIdStream rxSignInTaskIdStream, @Nonnull SignInTaskCache signInTaskCache) {
        Validate.notNull(list, "SignInTasks cannot be null", new Object[0]);
        Validate.notNull(rxSignInTaskIdStream, "RxSignInTaskIdStream cannot be null", new Object[0]);
        this.signInTasks = list;
        this.rxSignInTaskIdStream = rxSignInTaskIdStream;
        this.signInTaskCache = signInTaskCache;
    }

    int getCountOfRemainingTasksToExecute() {
        return this.signInTasks.size() - getNextTaskPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNextTaskPosition() {
        if (this.signInTaskCache.atLeastOneTaskExecuted()) {
            for (int i = 0; i < this.signInTasks.size(); i++) {
                if (this.signInTaskCache.isLastExecutedTask(this.signInTasks.get(i).getName())) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    protected Func1<Void, Void> executeTasks() {
        return this.executeTasks;
    }
}
