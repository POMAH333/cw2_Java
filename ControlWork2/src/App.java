import Controller.ControllerClass;
import Controller.Interfaces.iGetController;
import Controller.Interfaces.iGetModel;
import Controller.Interfaces.iGetView;
import Model.ModelClass;
import View.ViewClass;

public class App {
    public static void main(String[] args) throws Exception {

        iGetModel model = new ModelClass();
        iGetView view = new ViewClass();
        iGetController controller = new ControllerClass(model, view);

        view.setContr(controller);

        view.ViewRun();

    }
}