package com.amazon.mp3.net.task;

import com.amazon.mp3.library.data.ContributorManager;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.task.Task;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RetrieveContributorImagesTask extends Task {
    private List<ContributorAccessObject.ContributorData> mContributorDataList;
    private ContributorManager mContributorService = ContributorManagerImpl.INSTANCE;

    public RetrieveContributorImagesTask(List<ContributorAccessObject.ContributorData> contributorDataList) {
        this.mContributorDataList = contributorDataList;
    }

    @Override // com.amazon.mp3.task.Task
    public void execute() throws Throwable {
        this.mContributorService.update(this.mContributorDataList);
    }
}
