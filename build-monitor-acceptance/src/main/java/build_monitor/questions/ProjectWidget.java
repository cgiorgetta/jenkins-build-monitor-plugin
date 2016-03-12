package build_monitor.questions;

import build_monitor.model.ProjectInformation;
import build_monitor.questions.project_widget.ProjectWidgetInformation;
import build_monitor.questions.project_widget.ProjectWidgetState;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.screenplay.Question;

public class ProjectWidget {

    public static ProjectWidget of(String projectOfInterest) {
        return new ProjectWidget(projectOfInterest);
    }

    public Question<ProjectInformation> information() {
        return new ProjectWidgetInformation(projectOfInterest);
    }

    public Question<WebElementState> state() {
        return new ProjectWidgetState(projectOfInterest);
    }

    public ProjectWidget(String projectOfInterest) {
        this.projectOfInterest = projectOfInterest;
    }

    private final String projectOfInterest;
}
