package com.smartcodeltd.jenkinsci.plugins.buildmonitor.viewmodel;

import com.cloudbees.hudson.plugins.folder.Folder;
import com.smartcodeltd.jenkinsci.plugins.buildmonitor.BuildMonitorLogger;
import com.smartcodeltd.jenkinsci.plugins.buildmonitor.facade.RelativeLocation;
import com.smartcodeltd.jenkinsci.plugins.buildmonitor.installation.BuildMonitorBuildProperties;
import hudson.model.FreeStyleProject;
import hudson.model.Item;
import hudson.model.ItemGroup;
import hudson.model.Job;

import java.util.Collections;
import java.util.Date;

public class ClusterTitleJobView extends JobView {

    private static final BuildMonitorLogger logger = BuildMonitorLogger.forClass(BuildMonitorBuildProperties.class);

    private String url;

    public static ClusterTitleJobView create(ItemGroup parent) {
        ItemGroup topLevelItem = getTopLevelItem(parent);
        Job dummyJob = new FreeStyleProject(topLevelItem, "");
        String url = topLevelItem instanceof Folder ? ((Folder) topLevelItem).getShortUrl() : topLevelItem.getUrl();
        return new ClusterTitleJobView(dummyJob, url);
    }

    private static ItemGroup getTopLevelItem(ItemGroup job) {
        ItemGroup ig = job;
        while(ig instanceof Item) {
            ItemGroup parent = ((Item) ig).getParent();
            if (!(parent instanceof Item)) { // root found
                return ig;
            }
            ig = parent;
        }
        logger.warning("constructor", "Build Monitor could not find top level item of {0}", job.getFullName());
        return ig;
    }

    private ClusterTitleJobView(Job<?, ?> dummyJob, String url) {
        super(dummyJob, Collections.emptyList(), false, RelativeLocation.of(dummyJob), new Date(), false);
        this.url = url;
    }

    @Override
    public String url() {
        return url;
    }
}
