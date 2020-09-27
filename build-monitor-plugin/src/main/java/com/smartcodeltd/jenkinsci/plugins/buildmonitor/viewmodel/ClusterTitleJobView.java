package com.smartcodeltd.jenkinsci.plugins.buildmonitor.viewmodel;

import com.cloudbees.hudson.plugins.folder.Folder;
import com.smartcodeltd.jenkinsci.plugins.buildmonitor.facade.RelativeLocation;
import hudson.model.FreeStyleProject;
import hudson.model.ItemGroup;
import hudson.model.Job;

import java.util.Collections;
import java.util.Date;

public class ClusterTitleJobView extends JobView {

    private String url;

    public static ClusterTitleJobView create(ItemGroup parent) {
        Job job = new FreeStyleProject(parent, "");
        String url = parent instanceof Folder ? ((Folder) parent).getShortUrl() : parent.getUrl();
        return new ClusterTitleJobView(job, url);
    }

    private ClusterTitleJobView(Job<?, ?> job, String url) {
        super(job, Collections.emptyList(), false, RelativeLocation.of(job), new Date(), false);
        this.url = url;
    }

    @Override
    public String url() {
        return url;
    }
}
